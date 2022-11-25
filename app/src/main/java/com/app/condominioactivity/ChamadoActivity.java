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

public class ChamadoActivity extends AppCompatActivity {
    private EditText edTitulo, edDescricao;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamado);

        edTitulo = findViewById(R.id.edTituloChamado);
        edDescricao = findViewById(R.id.edDescricaoChamado);
        user = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void criarChamado(View view){
        Chamado chamado = new Chamado();
        DocumentReference document = db.collection("chamado").document();
        chamado.setTitulo(edTitulo.getText().toString());
        chamado.setDescricao(edDescricao.getText().toString());
        chamado.setIdUsuario(user);
        document.set(chamado).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ChamadoActivity.this, "Chamado criado com sucesso!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}