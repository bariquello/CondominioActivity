package com.app.condominioactivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListarChamadoActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_chamado);

        listView = findViewById(R.id.listViewChamado);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listarChamado();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Chamado chamado = (Chamado) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), AtualizarChamadoActivity.class);
                intent.putExtra("chamado", chamado);
                startActivity(intent);
            }
        });
    }

    public void listarChamado(){
        List<Chamado> lista = new ArrayList<>();
        db.collection("chamado").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    Chamado chamado;
                    for(QueryDocumentSnapshot document : task.getResult()){
                        chamado = document.toObject(Chamado.class);
                        lista.add(chamado);
                    }
                    ArrayAdapter<Chamado> arrayAdapter = new ArrayAdapter<>(ListarChamadoActivity.this, android.R.layout.simple_list_item_1, lista);
                    listView.setAdapter(arrayAdapter);
                }
            }
        });
    }
}