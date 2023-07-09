import reactor.core.publisher.Flux;

public class Ejemplo07 {
  public static void main(String[] args) {

    Flux<Integer> flux1 = Flux.just(6, 2, 3);
    Flux<Integer> flux2  = Flux.just(1, 2, 3);

    flux1.zipWith(flux2, (firts,second) -> firts *  second).subscribe(System.out::println);



  }

}
