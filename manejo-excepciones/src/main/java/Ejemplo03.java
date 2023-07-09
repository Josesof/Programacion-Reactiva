import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo03 {
  public static void main(String[] args) {

    Flux.just(2,3,4,5,23,0,43)
      .map(element -> {
        if(element == 0){
          throw  new RuntimeException("Exception ocurred");
        }
        return  element;
      }).onErrorContinue((ex,element) -> {
        System.out.println("Exception : " +ex);
        System.out.println("El elemento que causa la exception " + element);
      })
      .log().subscribe();
  }
}
