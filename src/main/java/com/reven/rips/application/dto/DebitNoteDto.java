package com.reven.rips.application.dto;

import com.reven.rips.shared.noteResponseCodeType.DebitNoteResponseCodeType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebitNoteDto extends NoteDto {

  private DebitNoteResponseCodeType responseCode;
}
