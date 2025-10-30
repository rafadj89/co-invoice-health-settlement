package com.reven.rips.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AiuAmountDto {

  private BigDecimal administration;
  private BigDecimal unforeseen;
  private BigDecimal utility;

}
