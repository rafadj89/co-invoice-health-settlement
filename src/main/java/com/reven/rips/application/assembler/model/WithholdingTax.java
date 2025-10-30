package com.reven.rips.application.assembler.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WithholdingTax {

  private BigDecimal total;
  private BigDecimal taxableAmount;
  private BigDecimal amount;
  private BigDecimal percent;
  private String taxId;
  private String taxName;

}
