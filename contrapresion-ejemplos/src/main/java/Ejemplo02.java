import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;

public class Ejemplo02 {
  public static void main(String[] args) {
    //Contrapresion es la capacidad de indicarle al programa cuantos datos
    //de un flujo quiero ver

    Flux<Integer> flux = Flux.range(1, 100).log();
    flux.subscribe(new BaseSubscriber<Integer>() {

      //Con este metodo estamos indicando que solo nos muestre 5 elementos

      @Override
      protected void hookOnSubscribe(Subscription subscription) {
        request(12);
      }

      @Override
      protected void hookOnNext(Integer value){
        if( value == 11){
          cancel();
        }
      }
    });
  }
}
