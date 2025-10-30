package com.reven.rips.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class NoteDto extends InvoiceDocumentDto {

  private ThirdPartyDto thirdParty;
  private ThirdPartyDto legalThirdParty;
  private String documentId;
  private String uniqueElectronicDocumentCode;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate issueDate;

  private Invoice billingReference;

  private String reference;
  private String description;
  private BigDecimal payableAmount;

  @Getter
  @Setter
  public static class Invoice {

    private String id;
    private String uniqueElectronicInvoiceCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate issueDate;
  }

}
