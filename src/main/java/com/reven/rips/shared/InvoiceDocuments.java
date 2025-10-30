package com.reven.rips.shared;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum InvoiceDocuments {

    CODE_01("01", InvoiceDocumentType.ELECTRONIC_INVOICE, ProcessorErrorMessages.BILL_ERROR_NOT_EQUALS_ID_SESSION),
    CODE_02("02", InvoiceDocumentType.ELECTRONIC_INVOICE, ProcessorErrorMessages.BILL_ERROR_NOT_EQUALS_ID_SESSION),
    CODE_03("03", InvoiceDocumentType.ELECTRONIC_INVOICE, ProcessorErrorMessages.BILL_ERROR_NOT_EQUALS_ID_SESSION),
    CODE_04("04", InvoiceDocumentType.ELECTRONIC_INVOICE, ProcessorErrorMessages.BILL_ERROR_NOT_EQUALS_ID_SESSION),
    CREDIT_NOTE("91", InvoiceDocumentType.CREDIT_NOTE, ProcessorErrorMessages.CREDIT_NOTE_ERROR_NOT_EQUALS_ID_SESSION),
    DEBIT_NOTE("92", InvoiceDocumentType.DEBIT_NOTE, ProcessorErrorMessages.DEBIT_NOTE_ERROR_NOT_EQUALS_ID_SESSION),
    UNKNOWN("", null, "");

    private final String code;
    private final InvoiceDocumentType type;
    private final String message;

    public static InvoiceDocuments findByCode(String code) {
        return Arrays.stream(InvoiceDocuments.values())
                .filter(it -> it.getCode().equals(code))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
