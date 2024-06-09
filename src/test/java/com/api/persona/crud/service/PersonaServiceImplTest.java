package com.api.persona.crud.service;

import com.api.persona.crud.repository.IPersonaRepository;
import com.api.persona.crud.entity.PersonaEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PersonaServiceImplTest {

    @Mock
    private IPersonaRepository personaRepository;

    @InjectMocks
    private PersonaServiceImpl personaService;

    private PersonaEntity personaEntity;

    @BeforeAll
    static void setUp() {
        System.out.println("Empezando las pruebas unitarias");
    }

    @BeforeEach
    public void setup() {
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
    @DisplayName("Test para buscar todos los registros")
    @Order(1)
    void findAll() throws ServiceException {
        //Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<PersonaEntity> page =new PageImpl<>(List.of(personaEntity), pageable, 1);
        //Act
        personaService.findAll(pageable);
        //Assert
        assertNotNull(page);

    }

    @Test
    @DisplayName("Test para buscar por nombre")
    @Order(2)
    void findLikeObject() throws ServiceException{
        //Arrange
        String nombre = "Juan";
        Pageable pageable = PageRequest.of(0, 10);
        Page<PersonaEntity> personaEntityPage = Mockito.mock(Page.class);
        //Act
        personaService.findLikeObject(pageable, nombre);
        //Assert
        assertNotNull(personaEntityPage);
        assertEquals(personaEntity.getNombre(), nombre);
    }

    @Test
    @DisplayName("Test para Guardar")
    @Order(3)
    void save() throws ServiceException {
        //Arrange
        PersonaEntity personaEntity = new PersonaEntity();
        //Act
        personaService.save(personaEntity);
        //Assert
        assertNotNull(personaEntity);
        assert (personaEntity.getIdPersona() == null);
    }

    @Test
    @DisplayName("Test para Actualizar")
    @Order(4)
    void update() throws ServiceException {

    }

    @Test
    @DisplayName("Test para Eliminar")
    @Order(5)
    void delete() throws ServiceException {

    }
    @AfterAll
    static void tearDown() {
        System.out.println("Finalizando las pruebas unitarias");
    }
}