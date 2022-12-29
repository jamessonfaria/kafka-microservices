package br.com.jamesson.shopreport.repository;

import br.com.jamesson.shopreport.model.ShopReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReporterRepository extends JpaRepository<ShopReport, Long> {

  @Modifying
  @Query(value = "update shop_report "
      + " set amount = amount + 1 "
      + " where identifier = :shopStatus",
      nativeQuery = true)
  void incrementShopStatus(@Param(value = "shopStatus") String shopStatus);

}
