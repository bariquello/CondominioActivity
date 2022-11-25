package com.app.condominioactivity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class AtualizarAvisoActivity extends AppCompatActivity {
    private Aviso avisoAtual;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_aviso);

        avisoAtual = (Aviso) getIntent().getSerializableExtra("aviso");
        ((TextView)findViewById(R.id.edTituloAviso)).setText(avisoAtual.getTitulo());
        ((TextView)findViewById(R.id.edDescricaoAviso)).setText(avisoAtual.getDescricao());

    }

    public void atualizarAviso(View view){
        String titulo = ((TextView)findViewById(R.id.edTituloAviso)).getText().toString();
        String descricao = ((TextView)findViewById(R.id.edDescricaoAviso)).getText().toString();

        DocumentReference document = db.collection("aviso").document(avisoAtual.getId());
        document.update(
                        "titulo", titulo,
                        "descricao", descricao
                )
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AtualizarAvisoActivity.this, "Aviso atualizado com sucesso!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}