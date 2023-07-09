package com.co.api.anotaciones.webFlux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HelloRouter {

  @Bean
  public RouterFunction<ServerResponse> funtionelRoutes(HelloHandler helloHandler){
    return RouterFunctions
      .route(RequestPredicates.GET("/functional/mono"), helloHandler::mostrarMensajeMono)
      .andRoute(RequestPredicates.GET("/functional/flux"), helloHandler::mostrarMensajeFlux);
  }
}
