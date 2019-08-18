package com.isagiongo.aluraforum.controllers;

import com.isagiongo.aluraforum.dtos.AtualizacaoTopicoFormDTO;
import com.isagiongo.aluraforum.dtos.DetalhesTopicoDTO;
import com.isagiongo.aluraforum.dtos.TopicoDTO;
import com.isagiongo.aluraforum.dtos.TopicoFormDTO;
import com.isagiongo.aluraforum.models.Topico;
import com.isagiongo.aluraforum.repositories.CursoRepository;
import com.isagiongo.aluraforum.repositories.TopicoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private TopicoRepository topicoRepository;

    private CursoRepository cursoRepository;

    public TopicoController(TopicoRepository topicoRepository, CursoRepository cursoRepository){
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    @Cacheable(value = "listaDeTopicos")
    public Page<TopicoDTO> busca(@RequestParam(required = false) String nomeCurso,
                                 @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC) Pageable paginacao) {
        if(nomeCurso == null) {
            Page<Topico> topicos = topicoRepository.findAll(paginacao);
            return TopicoDTO.converter(topicos);
        }
        Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
        return TopicoDTO.converter(topicos);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> cadastra(@Valid @RequestBody TopicoFormDTO form, UriComponentsBuilder uriBuilder){
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTopicoDTO> buscaPorId(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()) {
            return ResponseEntity.ok(new DetalhesTopicoDTO(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> atualiza(@PathVariable Long id, @Valid @RequestBody AtualizacaoTopicoFormDTO topicoForm) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if(optionalTopico.isPresent()) {
            Topico topico = topicoForm.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDTO(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity remove(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if(optionalTopico.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}