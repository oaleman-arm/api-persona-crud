package com.api.persona.crud.controller;

import com.api.persona.crud.controller.exceptions.GlobalNotFoundException;
import com.api.persona.crud.entity.PersonaEntity;
import com.api.persona.crud.service.IPersonaService;
import com.api.persona.crud.service.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/personas")
public class ProductoRestController {
    private final IPersonaService productoService;

    public ProductoRestController(IPersonaService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaEntity> findById(@PathVariable Long id) {
        try {
            PersonaEntity personaEntity = new PersonaEntity();
            personaEntity.setIdPersona(id);
            Optional<PersonaEntity> personaEntityOptional = productoService.findById(personaEntity);
            if (personaEntityOptional.isPresent()) {
                return ResponseEntity.ok(personaEntityOptional.get());
            } else {
                throw new GlobalNotFoundException("No existe el registro");
            }
        } catch (ServiceException e) {
            throw new GlobalNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/by-nombre")
    public ResponseEntity<Page<PersonaEntity>> findByNombre(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "nombre", defaultValue = "") String nombre) {
        try {
            if (page < 0 || size < 0) {
                throw new GlobalNotFoundException("Los valores de page y size deben ser mayores a 0");
            }

            Pageable pageable = PageRequest.of(page, size);
            Page<PersonaEntity> personaEntityPage = productoService.findLikeObject(pageable, nombre);

            if (personaEntityPage.isEmpty()) {
                throw new GlobalNotFoundException("No existen registros");
            }
            return ResponseEntity.ok(personaEntityPage);
        } catch (ServiceException e) {
            throw new GlobalNotFoundException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<PersonaEntity> save(@RequestBody PersonaEntity personaEntity) {
        try {
            PersonaEntity personaEntitySave = productoService.save(personaEntity);
            if (personaEntitySave == null) {
                throw new GlobalNotFoundException("No se pudo guardar el registro");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(personaEntitySave);
        } catch (ServiceException e) {
            throw new GlobalNotFoundException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaEntity> update(@PathVariable Long id, @RequestBody PersonaEntity personaEntity) {
        try {
            personaEntity.setIdPersona(id);
            PersonaEntity personaEntityUpdate = productoService.update(personaEntity);
            if (personaEntityUpdate == null) {
                throw new GlobalNotFoundException("No se pudo actualizar el registro");
            }
           // return ResponseEntity.ok(personaEntityUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(personaEntityUpdate);
        } catch (ServiceException e) {
            throw new GlobalNotFoundException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        try {
            PersonaEntity personaEntity = new PersonaEntity();
            personaEntity.setIdPersona(id);
            Boolean isDelete = productoService.delete(personaEntity);
            if (!isDelete) {
                throw new GlobalNotFoundException("No se pudo eliminar el registro");
            }
            return ResponseEntity.ok(isDelete);
        } catch (ServiceException e) {
            throw new GlobalNotFoundException(e.getMessage());
        }
    }
}
