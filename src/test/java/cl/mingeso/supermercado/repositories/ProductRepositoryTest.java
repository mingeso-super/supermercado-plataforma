package cl.mingeso.supermercado.repositories;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cl.mingeso.supermercado.models.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductRepositoryTest {

    @Autowired
    static ProductRepository productRepository;
    static Product product = null;

    @BeforeClass
    public static void init() {
        product = new Product();

        product.setCategoria("Importado");
        product.setCodigo(0000001);
        product.setNombre("ProductoTest");
        product.setPrecio(3000);
        product.setVencimiento(new Date());
    }

    @Before
    public void preInfo() {
        System.out.println(
            "Product Repository Test Start"
        );
    }

    @After
    public void postInfo() {
        System.out.println(
            "Product Repository Test End"
        );
    }

    @Test
    public void A_createProduct() {
        
        Product createdProduct = productRepository.save(product);

        assertEquals(product.getCategoria(), createdProduct.getCategoria());
        assertEquals(product.getCodigo(), createdProduct.getCodigo());
        assertEquals(product.getNombre(), createdProduct.getNombre());
        assertEquals(product.getPrecio(), createdProduct.getPrecio());
        assertEquals(product.getVencimiento(), createdProduct.getVencimiento());

        product = createdProduct;
    }

    @Test
    public void B_getProduct() {
        
        Optional<Product> obtainedProduct = productRepository.findById(product.getId());

        assertTrue(obtainedProduct.isPresent());

        assertEquals(product, obtainedProduct);
    }


    @Test
    public void C_updateProduct() {

        String category = product.getCategoria();

        product.setCategoria("Nacional");
        
        Product updatedProduct = productRepository.save(product);

        assertEquals(product.getId(), updatedProduct.getId());
        assertNotEquals(category, updatedProduct.getCategoria());
        
        product = updatedProduct;
    }


    @Test
    public void D_createProduct() {
        
        productRepository.deleteById(product.getId());

        Optional<Product> obtainedProduct = productRepository.findById(product.getId());

        assertFalse(obtainedProduct.isPresent());
    }

}