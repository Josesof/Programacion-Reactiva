package com.api.rest.reactiva.controller;

import com.api.rest.reactiva.document.Contacto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;

@SpringBootTest
@AutoConfigureWebTestClient //Pruebas a controladores asincronos
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactoControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  private Contacto contactoGuardado;

  @Test
  @Order(0)
  public void testGuardadContacto(){
    Flux<Contacto> contactoFlux = webTestClient.post()
      .uri("/api/contactos")
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON)
      .body(BodyInserters.fromValue(new Contacto("prueba1","prueba@gmail.com","555-55-555","2")))
      .exchange()
      .expectStatus().isAccepted()
      .returnResult(Contacto.class).getResponseBody()
      .log();

    contactoFlux.next().subscribe(contacto -> {
      this.contactoGuardado = contacto;
    });
    Assertions.assertNotNull(contactoGuardado);
  }

}
