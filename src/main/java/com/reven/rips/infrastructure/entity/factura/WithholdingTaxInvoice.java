package com.reven.rips.infrastructure.entity.factura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class WithholdingTaxInvoice {

  @Column(name = "porcentaje")
  private BigDecimal percent;

  @Column(name = "valor")
  private BigDecimal amount;

  @Column(name = "total")
  private BigDecimal total;

  @Column(name = "base_imponible")
  private BigDecimal taxableAmount;

  private WithholdingTax withholdingTax;


}
