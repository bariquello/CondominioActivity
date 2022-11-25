package com.app.condominioactivity;

public class Usuario {
    private String nome;
    private Predio predio;

    public Usuario(){
    }

    public Usuario(String nome, Predio predio) {
        this.nome = nome;
        this.predio = predio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }
}
