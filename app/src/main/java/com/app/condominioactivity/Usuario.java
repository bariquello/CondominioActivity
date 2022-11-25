package com.app.condominioactivity;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Usuario implements Serializable {
    @Exclude private String id;

    private String nome;
    private String predio;

    public Usuario(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPredio() {
        return predio;
    }

    public void setPredio(String predio) {
        this.predio = predio;
    }
}
