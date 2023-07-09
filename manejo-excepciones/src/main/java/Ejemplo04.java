import reactor.core.publisher.Flux;

public class Ejemplo04 {
  public static void main(String[] args) {

    Flux.just(2,3,4,5,23,0,43)
      .map(element -> {
        if(element == 235){
          throw  new RuntimeException("Exception ocurred");
        }
        return  element;
      }).onErrorMap((ex) -> {
        System.out.println("Exception : " +ex);
        return new CustomException(ex.getMessage(),ex);
      })
      .log().subscribe();
  }


}
