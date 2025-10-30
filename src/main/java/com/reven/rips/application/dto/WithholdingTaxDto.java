package com.reven.rips.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class WithholdingTaxDto {

  private BigDecimal total;
  private BigDecimal taxableAmount;
  private BigDecimal amount;
  private BigDecimal percent;
  private String taxId;
  private String taxName;

}
