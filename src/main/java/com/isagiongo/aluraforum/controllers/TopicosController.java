package com.isagiongo.aluraforum.controllers;

import com.isagiongo.aluraforum.dtos.AtualizacaoTopicoFormDTO;
import com.isagiongo.aluraforum.dtos.DetalhesTopicoDTO;
import com.isagiongo.aluraforum.dtos.TopicoDTO;
import com.isagiongo.aluraforum.dtos.TopicoFormDTO;
import com.isagiongo.aluraforum.models.Topico;
import com.isagiongo.aluraforum.repositories.CursoRepository;
import com.isagiongo.aluraforum.repositories.TopicoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    private TopicoRepository topicoRepository;

    private CursoRepository cursoRepository;

    public TopicosController(TopicoRepository topicoRepository, CursoRepository cursoRepository){
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public List<TopicoDTO> buscaPorNome(String nomeCurso) {
        if(nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDTO.converter(topicos);
        }
        List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
        return TopicoDTO.converter(topicos);
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> cadastra(@Valid @RequestBody TopicoFormDTO form, UriComponentsBuilder uriBuilder){
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public DetalhesTopicoDTO buscaPorId(@PathVariable Long id) {
        Topico topico = topicoRepository.getOne(id);
        return new DetalhesTopicoDTO(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualiza(@PathVariable Long id, @Valid @RequestBody AtualizacaoTopicoFormDTO topicoForm) {
        Topico topico = topicoForm.atualizar(id, topicoRepository);
        return ResponseEntity.ok(new TopicoDTO(topico));
    }

}