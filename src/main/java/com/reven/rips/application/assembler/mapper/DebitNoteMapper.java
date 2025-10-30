package com.reven.rips.application.assembler.mapper;

import com.reven.rips.application.assembler.model.DebitNote;
import com.reven.rips.application.dto.DebitNoteDto;
import com.reven.rips.application.dto.NoteDto;
import com.reven.rips.application.dto.ThirdPartyDto;
import com.reven.rips.shared.InvoiceDocumentType;
import com.reven.rips.shared.documentType.DocumentType;
import com.reven.rips.shared.noteResponseCodeType.DebitNoteResponseCodeType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, imports = {
    DocumentType.class, DebitNoteResponseCodeType.class}, uses = LocalDateFromStringMapper.class)
public interface DebitNoteMapper {

  @Mapping(target = "uniqueElectronicDocumentCode", source = "debitNote.uniqueElectronicDocumentCode")
  @Mapping(target = "documentId", source = "debitNote.documentId")
  @Mapping(target = "issueDate", source = "debitNote.issueDate", qualifiedByName = "parseDateFromString")
  @Mapping(target = "payableAmount", source = "debitNote.amount")
  @Mapping(target = "thirdParty", source = "debitNote", qualifiedByName = "mapThirdPartyDto")
  @Mapping(target = "legalThirdParty", source = "debitNote", qualifiedByName = "mapLegalThirdPartyDto")
  @Mapping(target = "billingReference", source = "debitNote")
  @Mapping(target = "invoiceDocumentType", source = "invoiceDocumentType")
  @Mapping(target = "description", source = "debitNote.responseDescription")
  @Mapping(target = "reference", source = "debitNote.reference")
  @Mapping(target = "responseCode",
      expression = "java(DebitNoteResponseCodeType.findByPrimaryKey(debitNote.getResponseCode()))")
  DebitNoteDto mapDebitNoteDto(DebitNote debitNote, InvoiceDocumentType invoiceDocumentType);

  @Mapping(target = "id", source = "invoiceId")
  @Mapping(target = "issueDate", source = "invoiceIssueDate", qualifiedByName = "parseDateFromString")
  @Mapping(target = "uniqueElectronicInvoiceCode", source = "uniqueElectronicInvoiceCode")
  NoteDto.Invoice mapInvoice(DebitNote debitNote);

  @Named("mapThirdPartyDto")
  @Mapping(target = "documentNumber", source = "thirdPartyDocumentNumber")
  @Mapping(target = "name", source = "thirdPartyName")
  @Mapping(target = "documentType",
          expression = "java(DocumentType.findByPrimaryKey(debitNote.getThirdPartyDocumentType()))")
  ThirdPartyDto mapThirdParty(DebitNote debitNote);

  @Named("mapLegalThirdPartyDto")
  @Mapping(target = "documentNumber", source = "partyLegalEntityDocumentNumber")
  @Mapping(target = "name", source = "partyLegalEntityName")
  @Mapping(target = "documentType",
          expression = "java(DocumentType.findByPrimaryKey(debitNote.getPartyLegalEntityDocumentType()))")
  ThirdPartyDto mapLegalThirdPartyDto(DebitNote debitNote);
}
