package com.app.condominioactivity;

public class Predio {
    private String bloco;
    private String apartamento;

    public Predio(){

    }

    public Predio(String bloco, String apartamento) {
        this.bloco = bloco;
        this.apartamento = apartamento;
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
