package com.reven.rips.application.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InvoiceMessageDTO implements Serializable {

  private String id;
  private SubjectDTO subject;
  private List<AttachmentDto> attachments;
  private MetadataDTO metadata;
  private XmlDataDTO invoiceData;
  private AttachmentDocumentDto attachmentDocumentDto;
  private List<String> messageErrorList;
  private EmailFolderStatusDTO emailFolderStatusDTO;
  private List<GoogleStorageFileDTO> attachmentFiles;
  private Map<String, String> headerAttributes;
  private boolean enExcepcion;
}
