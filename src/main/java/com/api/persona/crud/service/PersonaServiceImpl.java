package com.api.persona.crud.service;

import com.api.persona.crud.entity.PersonaEntity;
import com.api.persona.crud.repository.IPersonaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaServiceImpl implements IPersonaService {

    private final IPersonaRepository personaRepository;

    public PersonaServiceImpl(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Optional<PersonaEntity> findById(PersonaEntity personaEntity) throws ServiceException {
       try {
            return personaRepository.findById(personaEntity.getIdPersona());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Page<PersonaEntity> findLikeObject(Pageable pageable, String nombre) throws ServiceException {
        try {
            return personaRepository.findByLikeNombre(pageable, "%" +nombre+ "%");
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public PersonaEntity save(PersonaEntity personaEntity) throws ServiceException {
        try {
            return personaRepository.save(personaEntity);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public PersonaEntity update(PersonaEntity personaEntity) throws ServiceException {
       try {
           //Verificar si existe el registro
            Optional<PersonaEntity> personaEntityOptional = personaRepository.findById(personaEntity.getIdPersona());
            if (personaEntityOptional.isPresent()) {
                PersonaEntity personaEntityUpdate = personaEntityOptional.get();
                BeanUtils.copyProperties(personaEntity, personaEntityUpdate);
                return personaRepository.save(personaEntity);
            }else {
                throw new ServiceException("No existe el registro");
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Boolean delete(PersonaEntity personaEntity) throws ServiceException {
        try {
            //Verificar si existe el registro
            Optional<PersonaEntity> personaEntityOptional = personaRepository.findById(personaEntity.getIdPersona());
            if (!personaEntityOptional.isPresent()) {
                throw new ServiceException("No existe el registro");
            }else {
                personaRepository.delete(personaEntity);
                return true;
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
