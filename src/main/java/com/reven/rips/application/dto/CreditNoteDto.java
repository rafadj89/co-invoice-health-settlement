package com.reven.rips.application.dto;

import com.reven.rips.shared.noteResponseCodeType.CreditNoteResponseCodeType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditNoteDto extends NoteDto {

  private CreditNoteResponseCodeType responseCode;
  private String documentCode;
}
