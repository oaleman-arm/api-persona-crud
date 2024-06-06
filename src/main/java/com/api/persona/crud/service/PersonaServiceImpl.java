package com.api.persona.crud.service;

import com.api.persona.crud.entity.PersonaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class PersonaServiceImpl implements IPersonaService {

    @Override
    public Optional<PersonaEntity> findById(PersonaEntity personaEntity) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public Page<PersonaEntity> findLikeObject(Pageable pageable, PersonaEntity personaEntity) throws ServiceException {
        return null;
    }

    @Override
    public PersonaEntity save(PersonaEntity personaEntity) throws ServiceException {
        return null;
    }

    @Override
    public PersonaEntity update(PersonaEntity personaEntity) throws ServiceException {
        return null;
    }

    @Override
    public Boolean delete(PersonaEntity personaEntity) throws ServiceException {
        return null;
    }
}
