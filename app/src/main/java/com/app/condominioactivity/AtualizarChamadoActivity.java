package com.app.condominioactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AtualizarChamadoActivity extends AppCompatActivity {
    private Chamado chamadoAtual;
    private EditText edTituloChamado, edDescricaoChamado;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_chamado);

        chamadoAtual = (Chamado) getIntent().getSerializableExtra("chamado");

        ((TextView)findViewById(R.id.edTituloChamado)).setText(chamadoAtual.getTitulo());
        ((TextView)findViewById(R.id.edDescricaoChamado)).setText(chamadoAtual.getDescricao());
    }

    public void atualizarChamado(View view){
        String titulo = edTituloChamado.getText().toString();
        String descricao = edDescricaoChamado.getText().toString();

        DocumentReference document = db.collection("chamado").document(chamadoAtual.getId());
        document.update(
                "titulo", titulo,
                "descricao", descricao
        )
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AtualizarChamadoActivity.this, "Chamado atualizado com sucesso!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}