import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;

public class Ejemplo01 {
  public static void main(String[] args) {
    Flux<String> ciudades = Flux.fromIterable(
      new ArrayList<>(Arrays.asList("Medellin","Bogota","Mos-cu","Paris","Kingston","Lima"))
    );
    ciudades.log().subscribe();
  }
}
