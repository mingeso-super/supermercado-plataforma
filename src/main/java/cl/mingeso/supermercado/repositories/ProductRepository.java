package cl.mingeso.supermercado.repositories;

import org.springframework.data.repository.CrudRepository;

import cl.mingeso.supermercado.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}