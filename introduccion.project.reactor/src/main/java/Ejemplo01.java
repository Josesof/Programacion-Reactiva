import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Ejemplo01 {
  public static void main(String[] args){

    List<Integer> elementosFromMono = new ArrayList<>();
    List<Integer> elementosFromFlux =  new ArrayList<>();

    //Creamos un mono
    Mono<Integer> mono = Mono.just(123);

      //Creamos un flux
    Flux<Integer> flux = Flux.just(1,2,3,43,56,78,65,23);

    //Nos suscribimos al Mono
    mono.subscribe(elementosFromMono::add);

    //Nos suscribimos al Flux
    flux.subscribe(elementosFromFlux::add);

    System.out.println("MONO :  "   +  elementosFromMono);
    System.out.println("FLUX :  "  +  elementosFromFlux);


  }
}
