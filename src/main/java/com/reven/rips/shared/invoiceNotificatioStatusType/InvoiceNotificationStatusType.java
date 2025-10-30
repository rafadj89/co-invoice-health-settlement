package com.reven.rips.shared.invoiceNotificatioStatusType;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;

/** @author Jhon Barón */
@Getter
public enum InvoiceNotificationStatusType {
  ACKNOWLEDGEMENT(InvoiceNotificationStatusType.ACKNOWLEDGEMENT_CODE, "Acuse de recibo"),
  ACCEPTED(InvoiceNotificationStatusType.ACCEPTED_CODE, "Aceptado"),
  REJECTED(InvoiceNotificationStatusType.REJECTED_CODE, "Rechazado"),
  EMPTY(InvoiceNotificationStatusType.EMPTY_CODE, "No aplica");

  private static final String ACKNOWLEDGEMENT_CODE = "1";
  private static final String ACCEPTED_CODE = "2";
  private static final String REJECTED_CODE = "3";
  private static final String EMPTY_CODE = "99";

  private static final HashMap<String, InvoiceNotificationStatusType> ENUM_MAP_BY_CODE = new HashMap<>();

  @JsonValue private final String id;
  private final String description;

  static {
    ENUM_MAP_BY_CODE.put(ACKNOWLEDGEMENT_CODE, ACKNOWLEDGEMENT);
    ENUM_MAP_BY_CODE.put(ACCEPTED_CODE, ACCEPTED);
    ENUM_MAP_BY_CODE.put(REJECTED_CODE, REJECTED);
    ENUM_MAP_BY_CODE.put(EMPTY_CODE, EMPTY);
  }

  InvoiceNotificationStatusType(String id, String description) {
    this.id = id;
    this.description = description;
  }

  public static InvoiceNotificationStatusType findByPrimaryKey(String id) {
    if (id != null && ENUM_MAP_BY_CODE.containsKey(id)) {
      return ENUM_MAP_BY_CODE.get(id);
    } else {
      return null;
    }
  }

  public static Collection<InvoiceNotificationStatusType> getList() {
    return ENUM_MAP_BY_CODE.values();
  }
}
