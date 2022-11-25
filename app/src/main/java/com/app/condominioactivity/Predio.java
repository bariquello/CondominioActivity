package com.app.condominioactivity;

import com.google.firebase.firestore.Exclude;

public class Predio {
    @Exclude private String id;
    private String bloco;
    private String apartamento;

    public Predio(){
    }

    public Predio(String bloco, String apartamento) {
        this.bloco = bloco;
        this.apartamento = apartamento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }
}
