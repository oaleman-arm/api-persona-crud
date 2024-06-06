package com.api.persona.crud.repository;

import com.api.persona.crud.entity.PersonaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface IPersonaRepository extends JpaRepository<PersonaEntity, Long>{
    // JPQL(Java Persistence Query Language)
    //Paginado
    @Query("select p from PersonaEntity p where upper(p.nombre) like upper(:nombre) and p.estado='1'")
    Page<PersonaEntity> findByLikeNombre(Pageable pageable, @Param("nombre") String nombre);

    @Query("select p from PersonaEntity p where p.estado='1'")
    Page<PersonaEntity> findAllByEstado();
}
