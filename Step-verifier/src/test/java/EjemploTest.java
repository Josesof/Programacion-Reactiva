import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class EjemploTest {

  @Test
  public void testFlux(){
    Flux<Integer> fluxToTest = Flux.just(1,2,3,4,5);

   //Creamos la prueba
    StepVerifier.create(fluxToTest)
      .expectNext(1)
      .expectNext(2)
      .expectNext(3)
      .expectNext(4)
      .expectNext(5)
      .expectComplete()
      .verify();

  }

  @Test
  public void testFluxString() {
    Flux<String> fluxToTest = Flux.just("ramon", "luis","doel","Daniel", "Armando", "Maximiliano","Alan")
      .filter(nombre -> nombre.length() <= 5)
      .map(String::toUpperCase);

    StepVerifier.create(fluxToTest)
      .expectNext("RAMON")
      .expectNext("LUIS")
      .expectNextMatches(nombre -> nombre.startsWith("DO"))
      .expectNext("ALAN")
      .expectComplete()
      .verify();
  }
}
