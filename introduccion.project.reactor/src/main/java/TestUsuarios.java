import reactor.core.publisher.Flux;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Locale;


public class TestUsuarios {

  private static final Logger log = LoggerFactory.getLogger(TestUsuarios.class);

  public static void main(String[] args){
Flux<String> nombres = Flux.just("Joe Johuar", "Minic Johuar", "Big Johuar", "Rembran Martin", "Corin Johuar");
Flux<Usuario> usuarios = nombres.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase()))
  .filter(usuario -> !usuario.getApellido().equalsIgnoreCase("Martin"))
  .doOnNext(usuario -> {
    if(usuario == null){
      throw new RuntimeException("Los nombres no pueden estar vacion");
    }
    System.out.println(usuario.getNombre().concat(" ").concat(usuario.getApellido()));
  })
  .map(usuario ->  {
    String nombre =  usuario.getNombre().toLowerCase();
    usuario.setNombre(nombre);
    return usuario;
  });

  usuarios.subscribe(e -> System.out.println(e.toString()), error -> System.out.println(error.getMessage()), new Runnable() {
    @Override
    public void run() {
      System.out.println("Se a finalizado la ejecucion del observable con exito !");
    }
  });

  }
}
