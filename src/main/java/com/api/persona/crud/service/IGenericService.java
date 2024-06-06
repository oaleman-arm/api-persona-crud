package com.api.persona.crud.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGenericService<T> {

    Optional<T> findById(T t) throws ServiceException;

    Page<T> findLikeObject(Pageable pageable, T t) throws ServiceException;

    T save(T t) throws ServiceException;

    T update(T t) throws ServiceException;

    Boolean delete(T t) throws ServiceException;
}
