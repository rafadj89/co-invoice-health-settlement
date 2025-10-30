package com.reven.rips.application.assembler.mapper;

import com.reven.rips.application.assembler.model.CreditNote;
import com.reven.rips.application.dto.CreditNoteDto;
import com.reven.rips.application.dto.NoteDto;
import com.reven.rips.application.dto.ThirdPartyDto;
import com.reven.rips.shared.InvoiceDocumentType;
import com.reven.rips.shared.documentType.DocumentType;
import com.reven.rips.shared.noteResponseCodeType.CreditNoteResponseCodeType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, imports = {
    DocumentType.class, CreditNoteResponseCodeType.class}, uses = LocalDateFromStringMapper.class)
public interface CreditNoteMapper {

  @Mapping(target = "uniqueElectronicDocumentCode", source = "creditNote.uniqueElectronicDocumentCode")
  @Mapping(target = "documentId", source = "creditNote.documentId")
  @Mapping(target = "issueDate", source = "creditNote.issueDate", qualifiedByName = "parseDateFromString")
  @Mapping(target = "payableAmount", source = "creditNote.amount")
  @Mapping(target = "thirdParty", source = "creditNote", qualifiedByName = "mapThirdPartyDto")
  @Mapping(target = "legalThirdParty", source = "creditNote", qualifiedByName = "mapLegalThirdPartyDto")
  @Mapping(target = "billingReference", source = "creditNote")
  @Mapping(target = "invoiceDocumentType", source = "invoiceDocumentType")
  @Mapping(target = "description", source = "creditNote.responseDescription")
  @Mapping(target = "reference", source = "creditNote.reference")
  @Mapping(target = "responseCode",
      expression = "java(CreditNoteResponseCodeType.findByPrimaryKey(creditNote.getResponseCode()))")
  @Mapping(target = "documentCode", source = "creditNote.documentCode")
  CreditNoteDto mapCreditNoteDto(CreditNote creditNote, InvoiceDocumentType invoiceDocumentType);

  @Mapping(target = "id", source = "invoiceId")
  @Mapping(target = "issueDate", source = "invoiceIssueDate", qualifiedByName = "parseDateFromString")
  @Mapping(target = "uniqueElectronicInvoiceCode", source = "uniqueElectronicInvoiceCode")
  NoteDto.Invoice mapInvoice(CreditNote creditNote);

  @Named("mapThirdPartyDto")
  @Mapping(target = "documentNumber", source = "thirdPartyDocumentNumber")
  @Mapping(target = "name", source = "thirdPartyName")
  @Mapping(target = "documentType",
      expression = "java(DocumentType.findByPrimaryKey(creditNote.getThirdPartyDocumentType()))")
  ThirdPartyDto mapThirdParty(CreditNote creditNote);

  @Named("mapLegalThirdPartyDto")
  @Mapping(target = "documentNumber", source = "partyLegalEntityDocumentNumber")
  @Mapping(target = "name", source = "partyLegalEntityName")
  @Mapping(target = "documentType",
          expression = "java(DocumentType.findByPrimaryKey(creditNote.getPartyLegalEntityDocumentType()))")
  ThirdPartyDto mapLegalThirdPartyDto(CreditNote creditNote);

}
