package com.reven.rips.shared.noteResponseCodeType;

import lombok.Getter;

import java.util.HashMap;
import java.util.Objects;

@Getter
public enum CreditNoteResponseCodeType {
  REFUND_NOT_ACCEPTANCE(CreditNoteResponseCodeType.REFUND_NOT_ACCEPTANCE_CODE,
      "Devolución de parte de los bienes; no aceptación de partes del servicio"),
  CANCELLATION(CreditNoteResponseCodeType.CANCELLATION_CODE, "Anulación de factura electrónica"),
  TOTAL_DISCOUNT(CreditNoteResponseCodeType.TOTAL_DISCOUNT_CODE, "Descuento total aplicado"),
  RESCISSION(CreditNoteResponseCodeType.RESCISSION_CODE,
      "Rescisión: nulidad por falta de requisitos"),
  OTHERS(CreditNoteResponseCodeType.RESCISSION_CODE, "Otros"),
  NULL_VALUE(CreditNoteResponseCodeType.NULL_VALUE_CODE, "No se especifica");

  private static final String REFUND_NOT_ACCEPTANCE_CODE = "1";
  private static final String CANCELLATION_CODE = "2";
  private static final String TOTAL_DISCOUNT_CODE = "3";
  private static final String RESCISSION_CODE = "4";
  private static final String OTHERS_CODE = "5";
  private static final String NULL_VALUE_CODE = "0";

  private static final HashMap<String, CreditNoteResponseCodeType> ENUM_MAP_BY_CODE = new HashMap<>();

  private final String id;
  private final String description;

  static {
    ENUM_MAP_BY_CODE.put(REFUND_NOT_ACCEPTANCE_CODE, REFUND_NOT_ACCEPTANCE);
    ENUM_MAP_BY_CODE.put(CANCELLATION_CODE, CANCELLATION);
    ENUM_MAP_BY_CODE.put(TOTAL_DISCOUNT_CODE, TOTAL_DISCOUNT);
    ENUM_MAP_BY_CODE.put(RESCISSION_CODE, RESCISSION);
    ENUM_MAP_BY_CODE.put(NULL_VALUE_CODE, NULL_VALUE);
  }

  CreditNoteResponseCodeType(String id, String description) {
    this.id = id;
    this.description = description;
  }

  public static CreditNoteResponseCodeType findByPrimaryKey(String id) {
    return Objects.nonNull(id) && ENUM_MAP_BY_CODE.containsKey(id) ? ENUM_MAP_BY_CODE.get(id)
        : CreditNoteResponseCodeType.NULL_VALUE;
  }
}
