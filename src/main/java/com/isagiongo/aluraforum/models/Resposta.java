package com.isagiongo.aluraforum.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    @ManyToOne
    private Topico topico;

    private LocalDateTime dataCriacao;

    @ManyToOne
    private Usuario autor;

    private Boolean solucao = false;

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Topico getTopico() {
        return topico;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Boolean getSolucao() {
        return solucao;
    }
}
