package com.reven.rips.shared.paymenMeansType;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;

/** @author Jhon Baron */
@Getter
public enum PaymentMeansType {
  COUNTED(PaymentMeansType.COUNTED_CODE, "De contado"),
  CREDIT(PaymentMeansType.CREDIT_CODE, "Credito"),
  NOT_APPLY(PaymentMeansType.NOT_APPLY_CODE, "No aplica.");

  private static final String COUNTED_CODE = "1";
  private static final String CREDIT_CODE = "2";
  private static final String NOT_APPLY_CODE = "99";

  private static final HashMap<String, PaymentMeansType> ENUM_MAP_BY_CODE = new HashMap<>();

  @JsonValue private final String id;
  private final String description;

  static {
    ENUM_MAP_BY_CODE.put(COUNTED_CODE, COUNTED);
    ENUM_MAP_BY_CODE.put(CREDIT_CODE, CREDIT);
    ENUM_MAP_BY_CODE.put(NOT_APPLY_CODE, NOT_APPLY);
  }

  PaymentMeansType(String id, String description) {
    this.id = id;
    this.description = description;
  }

  public static PaymentMeansType findByPrimaryKey(String id) {
    if (id != null && ENUM_MAP_BY_CODE.containsKey(id)) {
      return ENUM_MAP_BY_CODE.get(id);
    } else {
      return NOT_APPLY;
    }
  }

  public static Collection<PaymentMeansType> getList() {
    return ENUM_MAP_BY_CODE.values();
  }
}
