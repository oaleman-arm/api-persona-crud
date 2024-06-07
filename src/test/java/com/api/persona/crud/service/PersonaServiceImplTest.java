package com.api.persona.crud.service;

import com.api.persona.crud.repository.IPersonaRepository;
import com.api.persona.crud.entity.PersonaEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PersonaServiceImplTest {

    @InjectMocks
    private PersonaServiceImpl personaService;

    @Mock
    private IPersonaRepository personaRepository;

    @BeforeAll
    static void setUp() {
        System.out.println("Empezando las pruebas unitarias");
    }

    @Test
    @DisplayName("Test para buscar todos los registros")
    @Order(0)
    void findAll() throws ServiceException {

        //Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<PersonaEntity> personaEntityPage = Mockito.mock(Page.class);
        //Mock
        Mockito.when(personaRepository.findAllByEstado(Mockito.any(Pageable.class))).thenReturn(personaEntityPage);
        //Act
        personaService.findAll(pageable);
        //Assert
        assertNotNull(personaEntityPage);

    }

    @Test
    @DisplayName("Test para buscar por nombre")
    @Order(1)
    void findLikeObject() throws ServiceException{
        //Arrange
        String nombre = "Juan";
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setNombre(nombre);
        Pageable pageable = PageRequest.of(0, 10);

        Page<PersonaEntity> personaEntityPage = Mockito.mock(Page.class);
        //Mock
        Mockito.when(personaRepository.findByLikeNombre(Mockito.any(Pageable.class),"%"+nombre+"%")).thenReturn(personaEntityPage);
        //Act
        personaService.findLikeObject(pageable, nombre) ;
        //Assert
        assertNotNull(personaEntityPage);
        assertEquals(personaEntity.getNombre(), nombre);
    }

    @Test
    @DisplayName("Test para Guardar")
    @Order(2)
    void save() throws ServiceException {
        //Arrange
        PersonaEntity personaEntity = new PersonaEntity();
        //Mock
        Mockito.when(personaRepository.save(Mockito.any(PersonaEntity.class))).thenReturn(personaEntity);
        //Act
        personaService.save(personaEntity);
        //Assert
        assertNotNull(personaEntity);
        assert (personaEntity.getIdPersona() == null);
    }

    @Test
    @DisplayName("Test para Actualizar")
    @Order(3)
    void update() throws ServiceException {
        //Arrange
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(1L);
        //Mock
        Mockito.when(personaRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(personaEntity));
        Mockito.when(personaRepository.save(Mockito.any(PersonaEntity.class))).thenReturn(personaEntity);
        //Act
        personaService.update(personaEntity);
        //Assert
        assertNotNull(personaEntity);
        assertEquals(personaEntity.getIdPersona(), 1L);
    }

    @Test
    @DisplayName("Test para Eliminar")
    @Order(4)
    void delete() throws ServiceException {
//Arrange
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(1L);
        //Mock
        Mockito.when(personaRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(personaEntity));
        //Act
        personaService.delete(personaEntity);
        //Assert
        assertNotNull(personaEntity);
        assertEquals(personaEntity.getIdPersona(), 1L);
    }
    @AfterAll
    static void tearDown() {
        System.out.println("Finalizando las pruebas unitarias");
    }
}