package com.app.condominioactivity;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Aviso implements Serializable {
    @Exclude private String id;

    private String titulo;
    private String descricao;
    private String idUsuario;

    public Aviso() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
