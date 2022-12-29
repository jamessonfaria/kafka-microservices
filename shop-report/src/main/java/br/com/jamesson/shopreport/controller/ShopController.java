package br.com.jamesson.shopreport.controller;

import br.com.jamesson.shopreport.dto.ShopReporterDTO;
import br.com.jamesson.shopreport.repository.ReporterRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shop_report")
public class ShopController {

  private final ReporterRepository reporterRepository;

  @GetMapping
  public ResponseEntity<List<ShopReporterDTO>> getShopReporter() {
    List<ShopReporterDTO> shopReporterDTOS = reporterRepository.findAll().stream()
        .map(ShopReporterDTO::convert).toList();
    return ResponseEntity.ok(shopReporterDTOS);
  }


}
