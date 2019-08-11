package com.isagiongo.aluraforum.repositories;

import com.isagiongo.aluraforum.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNome(String nomeCurso);
}
