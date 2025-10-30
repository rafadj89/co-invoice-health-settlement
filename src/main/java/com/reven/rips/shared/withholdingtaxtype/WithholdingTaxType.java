package com.reven.rips.shared.withholdingtaxtype;

import lombok.Getter;

import java.util.HashMap;

@Getter
public enum WithholdingTaxType {

  TAX(WithholdingTaxType.TAX_NAME),
  WITHHOLDING(WithholdingTaxType.WITHHOLDING_NAME);

  private static final String TAX_NAME = "IMPUESTO";
  private static final String WITHHOLDING_NAME = "RETENCION";

  private static final HashMap<String, WithholdingTaxType> ENUM_MAP_BY_CODE = new HashMap<>();

  private final String name;

  static {
    ENUM_MAP_BY_CODE.put(TAX_NAME, TAX);
    ENUM_MAP_BY_CODE.put(WITHHOLDING_NAME, WITHHOLDING);
  }

  WithholdingTaxType(String name) {
    this.name = name;
  }

  public static WithholdingTaxType findByPrimaryKey(String name) {
    return ENUM_MAP_BY_CODE.get(name);
  }
}
