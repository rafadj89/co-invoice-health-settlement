package com.reven.rips.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/** @author Jhon Baron */
@Setter
@Getter
@Builder
@ToString
public class EmailFolderStatusDTO implements Serializable {
  private String emailId;
  private String labelTarget;
  private String labelActual;
}
