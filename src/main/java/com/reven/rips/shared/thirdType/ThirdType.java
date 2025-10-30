package com.reven.rips.shared.thirdType;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;

/** @author Jhon Baron */
@Getter
public enum ThirdType {
  PROVIDER(ThirdType.PROVIDER_CODE, "Provedor"),
  LENDER(ThirdType.LENDER_CODE, "Prestador"),
  NOT_APPLY(ThirdType.NOT_APPLY_CODE, "No aplica.");

  private static final String PROVIDER_CODE = "1";
  private static final String LENDER_CODE = "2";
  private static final String NOT_APPLY_CODE = "99";

  private static final HashMap<String, ThirdType> ENUM_MAP_BY_CODE = new HashMap<>();

  @JsonValue private final String id;
  private final String description;

  static {
    ENUM_MAP_BY_CODE.put(PROVIDER_CODE, PROVIDER);
    ENUM_MAP_BY_CODE.put(LENDER_CODE, LENDER);
    ENUM_MAP_BY_CODE.put(NOT_APPLY_CODE, NOT_APPLY);
  }

  ThirdType(String id, String description) {
    this.id = id;
    this.description = description;
  }

  public static ThirdType findByPrimaryKey(String id) {
    if (id != null && ENUM_MAP_BY_CODE.containsKey(id)) {
      return ENUM_MAP_BY_CODE.get(id);
    } else {
      return NOT_APPLY;
    }
  }

  public static Collection<ThirdType> getList() {
    return ENUM_MAP_BY_CODE.values();
  }
}
