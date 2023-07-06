package com.servisioSensillo.servicioSencillo.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ServiceSencillo {

  public Mono<String> buscarUno() {
    return Mono.just("latecnologiaavanza");
  }

  public Flux<String> buscarTodos(){
    return Flux.just("La","Tecnologia", "Avanza", "Por", "El", "Mundo");
  }

  public Flux<String> buscrTodosLentos(){
    return Flux.just("La","Tecnologia", "Avanza", "Por", "El", "Mundo").delaySequence(Duration.ofSeconds(10));
  }
}
