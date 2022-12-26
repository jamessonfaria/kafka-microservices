package br.com.jamesson.shopvalidator.repository;

import br.com.jamesson.shopvalidator.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Product findByIdentifier(String identifier);

}
