package cl.mingeso.supermercado.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.mingeso.supermercado.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}