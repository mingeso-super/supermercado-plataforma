@RestController
public class GreetingController {

  @GetMapping("/supemercado/api/v1/products/{name}")
  public String hi(@PathVariable String name) {
  	return "Hi " + name;
  }

}