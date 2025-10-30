package com.reven.rips.shared.noteResponseCodeType;

import lombok.Getter;

import java.util.HashMap;
import java.util.Objects;

@Getter
public enum DebitNoteResponseCodeType {
  INTERESTS(DebitNoteResponseCodeType.INTERESTS_CODE, "Intereses"),
  EXPENSES_RECEIVABLE(DebitNoteResponseCodeType.EXPENSES_RECEIVABLE_CODE, "Gastos por cobrar"),
  VALUE_CHANGE(DebitNoteResponseCodeType.VALUE_CHANGE_CODE, "Cambio del valor"),
  OTHERS(DebitNoteResponseCodeType.OTHERS_CODE, "Otros"),
  NULL_VALUE(DebitNoteResponseCodeType.NULL_VALUE_CODE, "No se especifica");

  private static final String INTERESTS_CODE = "1";
  private static final String EXPENSES_RECEIVABLE_CODE = "2";
  private static final String VALUE_CHANGE_CODE = "3";
  private static final String OTHERS_CODE = "4";
  private static final String NULL_VALUE_CODE = "0";

  private static final HashMap<String, DebitNoteResponseCodeType> ENUM_MAP_BY_CODE = new HashMap<>();

  private final String id;
  private final String description;

  static {
    ENUM_MAP_BY_CODE.put(INTERESTS_CODE, INTERESTS);
    ENUM_MAP_BY_CODE.put(EXPENSES_RECEIVABLE_CODE, EXPENSES_RECEIVABLE);
    ENUM_MAP_BY_CODE.put(VALUE_CHANGE_CODE, VALUE_CHANGE);
    ENUM_MAP_BY_CODE.put(OTHERS_CODE, OTHERS);
    ENUM_MAP_BY_CODE.put(NULL_VALUE_CODE, NULL_VALUE);
  }

  DebitNoteResponseCodeType(String id, String description) {
    this.id = id;
    this.description = description;
  }

  public static DebitNoteResponseCodeType findByPrimaryKey(String id) {
    return Objects.nonNull(id) && ENUM_MAP_BY_CODE.containsKey(id) ? ENUM_MAP_BY_CODE.get(id)
        : DebitNoteResponseCodeType.NULL_VALUE;
  }
}
