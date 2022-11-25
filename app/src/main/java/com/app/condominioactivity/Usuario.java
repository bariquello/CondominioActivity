package com.app.condominioactivity;

public class Usuario {
    private String nome;
    private String predio;

    public Usuario(){
    }

    public Usuario(String nome, String predio) {
        this.nome = nome;
        this.predio = predio;
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
