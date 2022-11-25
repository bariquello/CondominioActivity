package com.app.condominioactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class AtualizarPerfilActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth user = FirebaseAuth.getInstance();
    private String idPredio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_perfil);

        ((TextView)findViewById(R.id.edEmail)).setText(user.getCurrentUser().getEmail());

        DocumentReference documentUser = db.collection("usuario").document(user.getCurrentUser().getUid());
        documentUser.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                ((TextView)findViewById(R.id.edNome)).setText(value.getString("nome"));
                idPredio = value.getString("predio");
                recuperarDadosPredrio(idPredio);
            }
        });

    }

    public void recuperarDadosPredrio(String idPredio){
        DocumentReference documentPredio = db.collection("predio").document(idPredio);
        documentPredio.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                ((TextView)findViewById(R.id.edBloco)).setText(value.getString("bloco"));
                ((TextView)findViewById(R.id.edApartamento)).setText(value.getString("apartamento"));
            }
        });
    }

    public void atualizarCampos(View view){
        try {
            FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
            usuarioAtual.updateEmail(((TextView)findViewById(R.id.edEmail)).getText().toString());
            usuarioAtual.updatePassword(((TextView)findViewById(R.id.edSenha)).getText().toString());

            String nome = ((TextView)findViewById(R.id.edNome)).getText().toString();
            String bloco = ((TextView)findViewById(R.id.edBloco)).getText().toString();
            String apartamento = ((TextView)findViewById(R.id.edApartamento)).getText().toString();

            DocumentReference documentUser = db.collection("usuario").document(user.getCurrentUser().getUid());
            documentUser.update(
                    "nome", nome
            );

            DocumentReference documentPredio = db.collection("predio").document(idPredio);
            documentPredio.update(
                    "bloco", bloco,
                    "apartamento", apartamento
            );

            Toast.makeText(AtualizarPerfilActivity.this, "Usuario atualizado com sucesso!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception ex){
            Toast.makeText(AtualizarPerfilActivity.this, "Erro ao atualizar!",
                    Toast.LENGTH_SHORT).show();
        }

    }
}