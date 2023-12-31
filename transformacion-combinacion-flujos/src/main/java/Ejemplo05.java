import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo05 {
  public static void main(String[] args) {

    Flux<String> flux = Flux.fromArray(new String[]{"a","b","c"});
    Mono<String> mono = Mono.just("f");

    //concatWith Tiene la capacidad de combinar un flux con un mono
    Flux<String> fluxConcat = flux.concatWith(mono);
    fluxConcat.subscribe(element -> System.out.println(element + ""));

  }

}
