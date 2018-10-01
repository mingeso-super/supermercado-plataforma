package cl.mingeso.supermercado;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SupermercadoApplication.class)
@DataJpaTest
public class SupermercadoApplicationTests {

	@Test
	public void contextLoads() {
	}

}
