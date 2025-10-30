package com.reven.rips.shared.documentType;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;

/** @author Jhon Barón */
@Getter
public enum DocumentType {
  RC(DocumentType.REGISTRO_CIVIL_CODE, "Registro Civil"),
  TI(DocumentType.TARJETA_DE_IDENTIDAD_CODE, "Tarjeta de identidad"),
  CC(DocumentType.CEDULA_DE_CIUDADANIA_CODE, "Cédula de ciudadanía"),
  TE(DocumentType.TARJETA_DE_EXTRANJERÍA_CODE, "Tarjeta de Extrangeria"),
  CE(DocumentType.CEDULA_DE_EXTRANJERIA_CODE, "Cedula de extrangeria"),
  NIT(DocumentType.NIT_CODE, "NIT"),
  PS(DocumentType.PASAPORTE_CODE, "Pasaporte"),
  DIE(
      DocumentType.DOCUMENTO_DE_IDENTIFICACION_EXTANJERO_CODE,
      "Documento de identificacion extranjero"),
  NITOP(DocumentType.NIT_DE_OTRO_PAIS_CODE, "NIT otro pais"),
  NUIP(DocumentType.NUIP_CODE, "NUIP*"),
  NOT_APPLY(DocumentType.NO_APLICA_CODE, "No aplica");

  private static final String REGISTRO_CIVIL_CODE = "11";
  private static final String TARJETA_DE_IDENTIDAD_CODE = "12";
  private static final String CEDULA_DE_CIUDADANIA_CODE = "13";
  private static final String TARJETA_DE_EXTRANJERÍA_CODE = "21";
  private static final String CEDULA_DE_EXTRANJERIA_CODE = "22";
  private static final String NIT_CODE = "31";
  private static final String PASAPORTE_CODE = "41";
  private static final String DOCUMENTO_DE_IDENTIFICACION_EXTANJERO_CODE = "42";
  private static final String NIT_DE_OTRO_PAIS_CODE = "50";
  private static final String NUIP_CODE = "91";
  private static final String NO_APLICA_CODE = "99";

  private static final HashMap<String, DocumentType> ENUM_MAP_BY_CODE = new HashMap<>();

  @JsonValue private final String id;
  private final String description;

  static {
    ENUM_MAP_BY_CODE.put(REGISTRO_CIVIL_CODE, RC);
    ENUM_MAP_BY_CODE.put(TARJETA_DE_IDENTIDAD_CODE, TI);
    ENUM_MAP_BY_CODE.put(CEDULA_DE_CIUDADANIA_CODE, CC);
    ENUM_MAP_BY_CODE.put(TARJETA_DE_EXTRANJERÍA_CODE, TE);
    ENUM_MAP_BY_CODE.put(CEDULA_DE_EXTRANJERIA_CODE, CE);
    ENUM_MAP_BY_CODE.put(NIT_CODE, NIT);
    ENUM_MAP_BY_CODE.put(PASAPORTE_CODE, PS);
    ENUM_MAP_BY_CODE.put(DOCUMENTO_DE_IDENTIFICACION_EXTANJERO_CODE, DIE);
    ENUM_MAP_BY_CODE.put(NIT_DE_OTRO_PAIS_CODE, NITOP);
    ENUM_MAP_BY_CODE.put(NUIP_CODE, NUIP);
    ENUM_MAP_BY_CODE.put(NO_APLICA_CODE, NOT_APPLY);
  }

  DocumentType(String id, String description) {
    this.id = id;
    this.description = description;
  }

  public static DocumentType findByPrimaryKey(String id) {
    if (id != null && ENUM_MAP_BY_CODE.containsKey(id)) {
      return ENUM_MAP_BY_CODE.get(id);
    } else {
      return null;
    }
  }

  public static Collection<DocumentType> getList() {
    return ENUM_MAP_BY_CODE.values();
  }
}
