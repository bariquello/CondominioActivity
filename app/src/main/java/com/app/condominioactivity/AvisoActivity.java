package com.app.condominioactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AvisoActivity extends AppCompatActivity {
    private EditText edTitulo, edDescricao;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso);

        edTitulo = findViewById(R.id.edTituloAviso);
        edDescricao = findViewById(R.id.edDescricaoAviso);
        user = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void criarAviso(View view){
        Aviso aviso = new Aviso();
        DocumentReference document = db.collection("aviso").document();
        aviso.setTitulo(edTitulo.getText().toString());
        aviso.setDescricao(edDescricao.getText().toString());
        aviso.setIdUsuario(user);
        document.set(aviso).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(AvisoActivity.this, "Aviso criado com sucesso!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}