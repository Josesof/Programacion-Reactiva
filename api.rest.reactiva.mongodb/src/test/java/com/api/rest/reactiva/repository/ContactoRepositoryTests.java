package com.api.rest.reactiva.repository;

import com.api.rest.reactiva.document.Contacto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //Le decimos que solo cree una clase de instancia
public class ContactoRepositoryTests {

  @Autowired
  private ContactoRepository contactoRepository;

  @Autowired
  private ReactiveMongoOperations mongoOperations;

  @BeforeAll
  public void insertarDatos(){
    Contacto conacto1 = Contacto.builder()
      .nombre("Test1")
      .email("test1@gmail.com")
      .telefono("123456789")
      .build();
    Contacto conacto2 = Contacto.builder()

      .nombre("Test2")
      .email("test2@gmail.com")
      .telefono("223456789")
      .build();

    Contacto conacto3 = Contacto.builder()
      .nombre("Test3")
      .email("test3@gmail.com")
      .telefono("323456789")
      .build();

    Contacto conacto4 = Contacto.builder()

      .nombre("Test4")
      .email("test4@gmail.com")
      .telefono("423456789")
      .build();

    Contacto conacto5 = Contacto.builder()
      .nombre("Test5")
      .email("test5@gmail.com")
      .telefono("523456789")
      .build();

    //Guardamos los contactos

    StepVerifier.create(contactoRepository.insert(conacto1).log())
      .expectSubscription()
      .expectNextCount(1)
      .verifyComplete();

    StepVerifier.create(contactoRepository.save(conacto2).log())
      .expectSubscription()
      .expectNextCount(1)
      .verifyComplete();

    StepVerifier.create(contactoRepository.save(conacto3).log())
      .expectSubscription()
      .expectNextMatches(contacto -> (contacto.getId() != null))
      .verifyComplete();
  }

  @Test
  @Order(1)
  public void testListarContactos(){
    StepVerifier.create(contactoRepository.findAll().log())
      .expectSubscription()
      .expectNextCount(3)
      .expectComplete();
  }

  @Test
  @Order(2)
  public void testBuscarPorEmail(){
    StepVerifier.create(contactoRepository.findFirstByEmail("test1@gmail.com").log())
      .expectSubscription()
      .expectNextMatches(contacto -> contacto.getEmail().equals("test1@gmail.com"))
      .expectComplete();
  }

  @Test
  @Order(3)
  public void contactoActualizado(){
    Mono<Contacto> contactoActualizado = contactoRepository.findFirstByEmail("test1@gmail.com")
      .map(contacto -> {
        contacto.setTelefono("555-55-555");
        return contacto;
      }).flatMap(contacto -> {
        return contactoRepository.save(contacto);
      });

    StepVerifier.create(contactoActualizado.log())
      .expectSubscription()
      .expectNextMatches(contacto -> contacto.getTelefono().equals("555-55-555"))
      .expectComplete();

  }

  @Test
  @Order(4)
  public void testEliminarContactoPorId(){
    Mono<Void> contactoEliminado = contactoRepository.findFirstByEmail("test2@gmail.com")
      .flatMap(contacto -> {
        return contactoRepository.deleteById(contacto.getId());
      }).log();

    StepVerifier.create(contactoEliminado)
      .expectSubscription()
      .expectComplete();

  }

  @Test
  @Order(5)
  public void testEliminarContacto(){
    Mono<Void> contactoEliminado = contactoRepository.findFirstByEmail("test2@gmail.com")
      .flatMap(contacto -> {
        return contactoRepository.delete(contacto);
      }).log();

    StepVerifier.create(contactoEliminado)
      .expectSubscription()
      .expectComplete();

  }

  @AfterAll
public void limpiarContactos(){
    Mono<Void> elementosEliminados = contactoRepository.deleteAll();
    StepVerifier.create(elementosEliminados.log())
      .expectSubscription()
      .verifyComplete();
}

}
