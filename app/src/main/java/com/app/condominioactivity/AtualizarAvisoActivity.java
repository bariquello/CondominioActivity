package com.app.condominioactivity;


import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
        db.collection("aviso").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ((TextView)findViewById(R.id.edTituloAviso)).setText(avisoAtual.getTitulo());
                ((TextView)findViewById(R.id.edDescricaoAviso)).setText(avisoAtual.getDescricao());
            }
        });
    }
}