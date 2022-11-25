package com.app.condominioactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private FirebaseUser user;
    private TextView edNome;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        user = FirebaseAuth.getInstance().getCurrentUser();
        edNome = findViewById(R.id.edNome);

        listView = findViewById(R.id.listViewAviso);
    }

    @Override
    protected void onStart() {
        super.onStart();

        DocumentReference document = db.collection("usuario").document(user.getUid());
        document.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                edNome.setText(value.getString("nome"));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listarAviso();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aviso aviso = (Aviso) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), AtualizarAvisoActivity.class);
                intent.putExtra("aviso", aviso);
                startActivity(intent);
            }
        });
    }

    public void listarAviso(){
        List<Aviso> lista = new ArrayList<>();
        db.collection("aviso").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    Aviso aviso;
                    for(QueryDocumentSnapshot document : task.getResult()){
                        aviso = document.toObject(Aviso.class);
                        lista.add(aviso);
                    }
                    ArrayAdapter<Aviso> arrayAdapter = new ArrayAdapter<>(MenuActivity.this, android.R.layout.simple_list_item_1, lista);
                    listView.setAdapter(arrayAdapter);
                }
            }
        });
    }

    public void abriMenuPerfil(View view){
        Intent intent = new Intent(getApplicationContext(), AtualizarPerfilActivity.class);
        startActivity(intent);
    }

    public void abrirMenuAviso(View view){
        Intent intent = new Intent(getApplicationContext(), AvisoActivity.class);
        startActivity(intent);
    }

    public void abrirMenuCadastroChamado(View view){
        Intent intent = new Intent(getApplicationContext(), ChamadoActivity.class);
        startActivity(intent);
    }

    public void abrirMenuListChamado(View view){
        Intent intent = new Intent(getApplicationContext(), ListarChamadoActivity.class);
        startActivity(intent);
    }

    public void sairUsuario(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}