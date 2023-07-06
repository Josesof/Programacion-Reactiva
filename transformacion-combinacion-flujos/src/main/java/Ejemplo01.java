import reactor.core.publisher.Flux;

public class Ejemplo01 {
  public static void main(String[] args) {
    Flux.fromArray(new String[] {"Jose", "Nelson", "Maria", "Luisa", "Manuel"})
      .map(String::toUpperCase)
      .subscribe(System.out::println);
  }
}
