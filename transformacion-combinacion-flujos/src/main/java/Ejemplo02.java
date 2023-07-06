import reactor.core.publisher.Flux;

public class Ejemplo02 {
  public static void main(String[] args) {
    Flux.fromArray(new String[] {"Josefina", "Nelson", "Maria", "Luisa", "Manuel","luz","Emilia"})
      .filter(nombre -> nombre.length() > 6)
      .map(String::toUpperCase)
      .subscribe(System.out::println);
  }
}
