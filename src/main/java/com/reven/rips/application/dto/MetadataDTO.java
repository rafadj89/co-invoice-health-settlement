package com.reven.rips.application.dto;

import lombok.Getter;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

@Getter
public class MetadataDTO implements Serializable {

	private static final long serialVersionUID = -4734181621569663833L;
	private final Map<String, String> values;

	public MetadataDTO(Map<String, String> metadata) {
    this.values = Collections.unmodifiableMap(metadata);
  }
}
