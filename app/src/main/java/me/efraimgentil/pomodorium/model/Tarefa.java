package me.efraimgentil.pomodorium.model;

import java.io.Serializable;

/**
 * Created by efraimgentil on 29/05/16.
 */
public class Tarefa implements Serializable {

    public static final String CONCLUIDO = "concluido";
    public static final String TABELA = "tarefa";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String DESCRICAO = "descricao";
    public static final String CICLOS = "ciclos";

    private Integer id;
    private String nome;
    private String descricao;
    private Integer ciclos;
    private boolean concluido;


    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", ciclos='" + ciclos + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCiclos() {
        return ciclos;
    }

    public void setCiclos(Integer ciclos) {
        this.ciclos = ciclos;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }
}

