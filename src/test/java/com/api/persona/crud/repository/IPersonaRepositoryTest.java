package com.api.persona.crud.repository;

import com.api.persona.crud.entity.PersonaEntity;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IPersonaRepositoryTest {
    @Mock
    private IPersonaRepository personaRepository;

    private PersonaEntity personaEntity;

    @BeforeAll
    static void setUp() {
        System.out.println("Empezando las pruebas unitarias");
    }
    @BeforeEach
    private void setup() {
        personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(1L);
        personaEntity.setNombre("Juan");
        personaEntity.setApellido("Perez");
        personaEntity.setEdad(25);
        personaEntity.setDireccion("Calle 123");
        personaEntity.setTelefono("123456789");
        personaEntity.setEstado("1");
    }

    @Test
    @DisplayName("Test para buscar todos los registros por nombre")
    @Order(2)
    void findByLikeNombre() {
        //Arrange
        String nombre = "Juan";
        Page<PersonaEntity> p = new PageImpl<>(Collections.singletonList(personaEntity));

        Mockito.when(personaRepository.findByLikeNombre(PageRequest.of(0, 10), nombre)).thenReturn(p);
        //Act
        Page<PersonaEntity> page = personaRepository.findByLikeNombre(PageRequest.of(0, 10), nombre);
        //Assert
        assertNotNull(page);
    }

    @Test
    @DisplayName("Test para buscar todos los registros por estado")
    @Order(1)
    void findAllByEstado() {
        //Arrange
        Page<PersonaEntity> p = new PageImpl<>(Collections.singletonList(personaEntity));
        Mockito.when(personaRepository.findAllByEstado(PageRequest.of(0, 10))).thenReturn(p);
        //Act
        Page<PersonaEntity> page = personaRepository.findAllByEstado(PageRequest.of(0, 10));
        //Assert
        assertNotNull(page);
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Finalizando las pruebas unitarias");
    }
}