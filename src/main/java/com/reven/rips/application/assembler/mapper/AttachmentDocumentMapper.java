package com.reven.rips.application.assembler.mapper;

import com.reven.rips.application.assembler.model.AttachmentDocument;
import com.reven.rips.application.dto.AttachmentDocumentDto;
import com.reven.rips.application.dto.ThirdPartyDto;
import com.reven.rips.shared.documentType.DocumentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, imports = {
    DocumentType.class}, uses = LocalDateFromStringMapper.class)
public interface AttachmentDocumentMapper {

  @Mapping(target = "thirdParty", source = "attachmentDocument", qualifiedByName = "mapThirdPartyDto")
  @Mapping(target = "receiverParty", source = "attachmentDocument", qualifiedByName = "mapSenderPartyDto")
  @Mapping(target = "validationDate", source = "attachmentDocument.validationDate",
      qualifiedByName = "parseDateFromString")
  @Mapping(target = "description", ignore = true)
  AttachmentDocumentDto mapAttachmentDataDto(AttachmentDocument attachmentDocument);

  @Named("mapThirdPartyDto")
  @Mapping(target = "documentType",
      expression = "java(DocumentType.findByPrimaryKey(attachmentDocument.getThirdPartyDocumentType()))")
  @Mapping(target = "documentNumber", source = "thirdPartyDocumentNumber")
  @Mapping(target = "name", source = "thirdPartyName")
  ThirdPartyDto mapThirdPartyDto(AttachmentDocument attachmentDocument);

  @Named("mapSenderPartyDto")
  @Mapping(target = "documentNumber", source = "receiverPartyDocumentNumber")
  @Mapping(target = "name", source = "receiverPartyName")
  @Mapping(target = "documentType",
      expression = "java(DocumentType.findByPrimaryKey(attachmentDocument.getReceiverPartyDocumentType()))")
  ThirdPartyDto mapSenderPartyDto(AttachmentDocument attachmentDocument);

}
