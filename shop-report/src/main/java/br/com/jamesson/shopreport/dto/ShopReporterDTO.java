package br.com.jamesson.shopreport.dto;

import br.com.jamesson.shopreport.model.ShopReport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopReporterDTO {

  private String identifier;
  private Integer amount;

  public static ShopReporterDTO convert(ShopReport shopReport) {
    ShopReporterDTO shopReporterDTO = new ShopReporterDTO();
    shopReporterDTO.setIdentifier(shopReport.getIdentifier());
    shopReporterDTO.setAmount(shopReport.getAmount());
    return shopReporterDTO;
  }


}
