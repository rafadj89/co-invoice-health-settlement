package com.reven.rips.application.dto;

import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Builder
@ToString
public class FileInsideZipDTO implements Serializable {
    private byte[] data;
    private String fileName;

    public String getXmlString() {
        return xmlString;
    }

    private String xmlString;

    public byte[] getData() {
        if (Objects.isNull(data)) return new byte[0];
        return data.clone();
    }

    public String getFileName() {
        return fileName;
    }
}
