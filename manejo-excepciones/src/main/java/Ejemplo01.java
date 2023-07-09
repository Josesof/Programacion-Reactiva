import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo01 {
  public static void main(String[] args) {

    Flux.just(2,3,4,5)
      .concatWith(Flux.error(new RuntimeException("Exception ocurred")))
      .concatWith(Mono.just(12))
      .onErrorReturn(00000000000)
      .log().subscribe();
  }
}
