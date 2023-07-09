import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Ejemplo03 {
  //Cantidad minima de subscriptores
  public static void main(String[] args) throws InterruptedException {
    Flux<String> netFlux = Flux.fromStream(Ejemplo03::getVideo)
      .delayElements(Duration.ofSeconds(2)).publish()
      //Indicamos la cantidad minima de subscriptores que debe tener nuestro flujo
        .refCount(1);

    netFlux.subscribe(part -> System.out.println("Suscriber 1 : " + part));

    Thread.sleep(5000);

    netFlux.subscribe(part -> System.out.println("Suscriber 2 : " + part));

    Thread.sleep(60000);
  }

  private static Stream<String> getVideo(){
    System.out.println("Request para el video");
    return Stream.of("part1", "part2", "part3", "part4", "part5");
  }
}
