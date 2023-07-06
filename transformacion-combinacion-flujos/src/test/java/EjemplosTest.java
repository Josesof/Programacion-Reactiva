
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class EjemplosTest {

  @Test
  public void testTransforMap(){
    List<String> listaNombres = Arrays.asList("google", "abc", "fb", "notifications");
    Flux<String> nombresFlux = Flux.fromIterable(listaNombres)
      .filter(nombre -> nombre.length() > 5)
      .map(nombre -> nombre.toUpperCase())
      .log();

    StepVerifier.create(nombresFlux)
      .expectNext("GOOGLE", "NOTIFICATIONS")
      .verifyComplete();

  }

  @Test
  public void testTransformUsingFlatMap(){
    List<String> listaNombres = Arrays.asList("google", "abc", "fb", "notifications");
    Flux<String> nombresFlux = Flux.fromIterable(listaNombres)
      .filter(nombre -> nombre.length() > 5)
      .flatMap(nombre -> {
        return Mono.just(nombre.toUpperCase());
        }

      )
      .log();

    StepVerifier.create(nombresFlux)
      .expectNext("GOOGLE", "NOTIFICATIONS")
      .verifyComplete();
  }

  @Test
  public void testCombinarFlujosUsandoMerge(){
    Flux<String> flux1 = Flux.just("leon", "luis", "luna");
    Flux<String> flux2 = Flux.just("clave1", "clave2", "clave3");

    Flux<String> fluxMerge = Flux.merge(flux1, flux2).log();

    StepVerifier.create(fluxMerge)
      .expectSubscription()
      .expectNext("leon", "luis", "luna", "clave1", "clave2", "clave3")
      .verifyComplete();

  }

  @Test
  public void testCombinarFlujosUsandoMergeConDemora(){
    Flux<String> flux1 = Flux.just("leon", "luis", "luna")
      .delayElements(Duration.ofSeconds(1));
    Flux<String> flux2 = Flux.just("clave1", "clave2", "clave3")
      .delayElements(Duration.ofSeconds(1));

    Flux<String> fluxMerge = Flux.merge(flux1, flux2).log();

    StepVerifier.create(fluxMerge)
      .expectSubscription()
      .expectNextCount(6)
      .verifyComplete();

  }

  @Test
  public void testCombinarFlujosUsandoConDemoraConOperadorConcat(){
    Flux<String> flux1 = Flux.just("leon", "luis", "luna")
      .delayElements(Duration.ofSeconds(1));
    Flux<String> flux2 = Flux.just("clave1", "clave2", "clave3")
      .delayElements(Duration.ofSeconds(1));

    Flux<String> fluxConcat = Flux.concat(flux1, flux2).log();

    StepVerifier.create(fluxConcat)
      .expectSubscription()
      .expectNext("leon", "luis", "luna", "clave1", "clave2", "clave3")
      .verifyComplete();

  }

}
