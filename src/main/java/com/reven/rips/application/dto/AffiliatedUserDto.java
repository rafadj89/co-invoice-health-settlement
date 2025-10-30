package com.reven.rips.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class AffiliatedUserDto {

  private String type;
  private String documentType;
  private String documentNumber;
  private String surname;
  private String secondSurname;
  private String firstName;
  private String secondName;

  public boolean isEmpty() {
    return Stream
        .of(this.type, this.documentType, this.documentNumber, this.surname, this.secondSurname,
            this.firstName, this.secondName)
        .allMatch(Objects::isNull);
  }

}
