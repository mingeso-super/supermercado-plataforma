package cl.mingeso.supermercado.apis;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cl.mingeso.supermercado.exceptions.ProductNotFound;
import cl.mingeso.supermercado.models.Product;
import cl.mingeso.supermercado.repositories.ProductRepository;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsApi {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable long id) throws ProductNotFound {

        Optional<Product> product = productRepository.findById(id);
        
        if (!product.isPresent()) {
            throw new ProductNotFound("Product No." + id + "Not found.");
        }

        return product.get();
    }

    @PostMapping("/")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {

        Product storedProduct = productRepository.save(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                        .buildAndExpand(storedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable long id) {

        Optional<Product> currentProduct = productRepository.findById(id);

        if (!currentProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        product.setId(id);

        productRepository.save(product);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        productRepository.deleteById(id);
    }
    



}