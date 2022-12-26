package br.com.jamesson.shopapi.repository;

import br.com.jamesson.shopapi.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
