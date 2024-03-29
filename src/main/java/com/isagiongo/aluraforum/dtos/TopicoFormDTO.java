package com.isagiongo.aluraforum.dtos;

import com.isagiongo.aluraforum.models.Curso;
import com.isagiongo.aluraforum.models.Topico;
import com.isagiongo.aluraforum.repositories.CursoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TopicoFormDTO {

    @NotNull @NotEmpty @Length(min = 5)
    private String titulo;

    @NotNull @NotEmpty @Length(min = 5)
    private String mensagem;

    @NotNull @NotEmpty
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
