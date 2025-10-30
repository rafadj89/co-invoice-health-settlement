package com.reven.rips.application.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AttachmentDto implements Serializable {

  private static final long serialVersionUID = -1964512805903935678L;
    private String name;
    private byte[] content;
    private String contentType;
    private String fileId;
    private String stringData;
    private String zipString;
    private int size;

    public byte[] getContent() {
        if (content == null) {
            return new byte[0];
        }
        return content.clone();
    }


}
