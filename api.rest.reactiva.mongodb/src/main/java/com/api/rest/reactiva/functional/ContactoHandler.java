package com.api.rest.reactiva.functional;

import com.api.rest.reactiva.document.Contacto;
import com.api.rest.reactiva.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class ContactoHandler {

  @Autowired
  private ContactoRepository contactoRepository;

  public ContactoHandler(ContactoRepository contactoRepository) {
    this.contactoRepository = contactoRepository;
  }

  private Mono<ServerResponse> response404 = ServerResponse.notFound().build();
  private Mono<ServerResponse> response406 = ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();

  //Listar contactos
  public Mono<ServerResponse> listarContactos(ServerRequest request){
    return ServerResponse.ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(contactoRepository.findAll(), Contacto.class);
  }

  //Listar un solo contacto
  public Mono<ServerResponse> obtenerContactoPorId(ServerRequest request){
    String id = request.pathVariable("id");

    return  contactoRepository.findById(id)
             .flatMap(contacto ->
               ServerResponse.ok()
                 .contentType(MediaType.APPLICATION_JSON)
                 .body(fromValue(contacto)))
                 .switchIfEmpty(response404);



  }

  //Listar un solo contacto por email
  public Mono<ServerResponse> obtenerContactoPorEmail(ServerRequest request){
    String email = request.pathVariable("email");

    return  contactoRepository.findFirstByEmail(email)
      .flatMap(contacto ->
        ServerResponse.ok()
          .contentType(MediaType.APPLICATION_JSON)
          .body(fromValue(contacto)))
      .switchIfEmpty(response404);
  }

  //insertar un contacto
  public Mono<ServerResponse> insertarContacto(ServerRequest request){
    Mono<Contacto> contactoMono = request.bodyToMono(Contacto.class);

    return contactoMono
      .flatMap(contacto -> contactoRepository.save(contacto)
        .flatMap(contactoSave -> ServerResponse.accepted()
        .contentType(MediaType.APPLICATION_JSON)
          .body(fromValue(contactoSave))))
            .switchIfEmpty(response406);
  }


  //actualizar contacto
  public Mono<ServerResponse> actualizacContacto(ServerRequest request){
    Mono<Contacto> contactoMono = request.bodyToMono(Contacto.class);
    String id = request.pathVariable("id");

    Mono<Contacto> contactoActualizado = contactoMono.flatMap(contacto ->
                 contactoRepository.findById(id)
                   .flatMap(oldContacto -> {
                     oldContacto.setEmail(contacto.getTelefono());
                     oldContacto.setNombre(contacto.getNombre());
                     oldContacto.setTelefono(contacto.getTelefono());
                     return  contactoRepository.save(oldContacto);
                   }));

    return contactoActualizado.flatMap(contacto ->
      ServerResponse.accepted()
        .contentType(MediaType.APPLICATION_JSON)
        .body(fromValue(contacto)))
      .switchIfEmpty(response404);
  }

  //Eliminar contacto
  public Mono<ServerResponse> eliminarContacto(ServerRequest request){
    String id = request.pathVariable("id");
    Mono<Void> contactoEliminado = contactoRepository.deleteById(id);

    return ServerResponse.ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(contactoEliminado, Void.class);
  }


}
