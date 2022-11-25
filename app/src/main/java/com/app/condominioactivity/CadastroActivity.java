package com.app.condominioactivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CadastroActivity extends AppCompatActivity {
    private static final String TAG = "EmailSenha";
    private FirebaseAuth mAuth;
    private EditText edEmail, edSenha, edNome, edBloco, edApartamento;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mAuth = FirebaseAuth.getInstance();
        edEmail = findViewById(R.id.edEmail);
        edSenha = findViewById(R.id.edSenha);
        edNome = findViewById(R.id.edNome);
        edBloco = findViewById(R.id.edBloco);
        edApartamento = findViewById(R.id.edApartamento);
    }

    public void criarUsuario(View view){
        mAuth.createUserWithEmailAndPassword(edEmail.getText().toString(), edSenha.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            salvarDadosPredio();
                            Toast.makeText(CadastroActivity.this, "Usuário criado com sucesso!",
                                Toast.LENGTH_SHORT).show();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CadastroActivity.this, "Falha na criação do usuário!"+
                                    "\n"+ task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void salvarDadosPredio(){
        Predio predio = new Predio();
        DocumentReference document = db.collection("predio").document();
        predio.setBloco(edBloco.getText().toString());
        predio.setApartamento(edApartamento.getText().toString());
        document.set(predio).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                String idPredio = document.getId();
                salvarDadosUsuario(idPredio);
            }
        });
    }

    public void salvarDadosUsuario(String idPredio){
        Usuario usuario = new Usuario();
        DocumentReference document = db.collection("usuario").document();
        usuario.setNome(edNome.getText().toString());
        usuario.setPredio(idPredio);
        document.set(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
            }
        });
    }
}