package com.reven.rips.shared.invoiceStatusType;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;

@Getter
public enum InvoiceStatusType {

  PENDING(InvoiceStatusType.PENDING_CODE, "Pendiente"),
  ACCEPTED(InvoiceStatusType.ACCEPTED_CODE, "Aceptado"),
  REJECTED(InvoiceStatusType.REJECTED_CODE, "Rechazado"),
  EMPTY(InvoiceStatusType.EMPTY_CODE, "No aplica");

  private static final String PENDING_CODE = "1";
  private static final String ACCEPTED_CODE = "2";
  private static final String REJECTED_CODE = "3";
  private static final String EMPTY_CODE = "99";

  private static final HashMap<String, InvoiceStatusType> ENUM_MAP_BY_CODE = new HashMap<>();

  @JsonValue
  private final String id;
  private final String description;

  static {
    ENUM_MAP_BY_CODE.put(PENDING_CODE, PENDING);
    ENUM_MAP_BY_CODE.put(ACCEPTED_CODE, ACCEPTED);
    ENUM_MAP_BY_CODE.put(REJECTED_CODE, REJECTED);
    ENUM_MAP_BY_CODE.put(EMPTY_CODE, EMPTY);
  }

  InvoiceStatusType(String id, String description) {
    this.id = id;
    this.description = description;
  }

  public static InvoiceStatusType findByPrimaryKey(String id) {
    if (id != null && ENUM_MAP_BY_CODE.containsKey(id)) {
      return ENUM_MAP_BY_CODE.get(id);
    } else {
      return null;
    }
  }

  public static Collection<InvoiceStatusType> getList() {
    return ENUM_MAP_BY_CODE.values();
  }
}


