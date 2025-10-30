package com.reven.rips.application.assembler.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LocalDateFromStringMapper {

  @Named("parseDateFromString")
  public static LocalDate parseDateFromString(String date) {
    try {
      return StringUtils.isNotEmpty(date) ? LocalDate.parse(date) : null;
    } catch (DateTimeParseException dtE) {
      return null;
    }
  }
}
