package com.isagiongo.aluraforum.dtos;

import com.isagiongo.aluraforum.enums.StatusTopicoEnum;
import com.isagiongo.aluraforum.models.Topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesTopicoDTO {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;
    private StatusTopicoEnum status;
    private List<RespostaDTO> respostas;

    public DetalhesTopicoDTO (Topico topico){
       this.id = topico.getId();
       this.titulo = topico.getTitulo();
       this.mensagem = topico.getMensagem();
       this.dataCriacao = topico.getDataCriacao();
       this.nomeAutor = topico.getAutor().getNome();
       this.status = topico.getStatusTopicoEnum();
       this.respostas = new ArrayList<>();
       this.respostas.addAll(topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public StatusTopicoEnum getStatus() {
        return status;
    }

    public List<RespostaDTO> getRespostas() {
        return respostas;
    }
}
