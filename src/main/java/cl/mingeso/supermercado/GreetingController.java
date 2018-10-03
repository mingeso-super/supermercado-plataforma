package cl.mingeso.supermercado;


public class GreetingController {

  @GetMapping("/supemercado/api/v1/products/{name}")

  public String hi(@PathVariable String name) {
  	System.out.println( "Hi " + name);
  }

}