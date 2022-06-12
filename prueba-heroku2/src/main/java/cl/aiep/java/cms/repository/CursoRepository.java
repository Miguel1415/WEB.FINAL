package cl.aiep.java.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.aiep.java.cms.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
