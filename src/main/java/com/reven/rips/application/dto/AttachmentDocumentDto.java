package com.reven.rips.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AttachmentDocumentDto {

    private String billId;

    private ThirdPartyDto thirdParty;

    private ThirdPartyDto receiverParty;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate validationDate;

    private String dianValidationDescription;

    private InvoiceDocumentDto description;

    private DianValidationDto dianValidation;

    @Builder
    @Getter
    @Setter
    public static class DianValidationDto {

        private String description;

        private String providerNit;

    }

}

