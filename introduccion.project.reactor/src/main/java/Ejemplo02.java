import reactor.core.publisher.Mono;

public class Ejemplo02 {
  public static void main(String[] args){

    Mono<String> mono = Mono.just("curso programacion reactiva");
    mono.subscribe(
      data ->  System.out.println(data),    //onNext,
      err -> System.out.println(err), //onError
      () -> System.out.println("Completo !")  //onComplete
    );

  }
}
