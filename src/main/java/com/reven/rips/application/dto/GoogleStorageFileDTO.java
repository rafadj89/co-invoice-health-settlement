package com.reven.rips.application.dto;

import lombok.*;

import java.io.Serializable;

/** @author Jhon Baron */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GoogleStorageFileDTO implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = -1833569318434116678L;
private long id;
  private String googleStorageId;
  private boolean xml;
  private String fileName;
}
