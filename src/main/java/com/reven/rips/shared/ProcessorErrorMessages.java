package com.reven.rips.shared;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProcessorErrorMessages {

    public final String XML_COULD_NOT_BE_READ_MSG_CODE = "xml.could.not.be.read";
    public final String GENERIC_TP_REJECTED_ERROR_XML_PDF_MSG_CODE = "generic.third.party.rejected.error.xml-pdf-not-present";
    public final String GENERIC_TP_REJECTED_ERROR_ZIP_CONTENT_MSG_CODE = "generic.third.party.rejected.error.zip-content";
    public final String GENERIC_TP_REJECTED_ERROR_ZIP_INNER_FILE_NAME_MSG_CODE = "generic.third.party.rejected.error.zip-inner-file-name";
    public final String GENERIC_TP_REJECTED_ERROR_INVOICE_NUMBER_DIFFERENT_IN_SUBJECT_MSG_CODE = "generic.third.party.rejected.error.invoice-number-different-in-subject";
    public final String DOCUMENT_TYPE_CODE_IN_SUBJECT_NOT_CORRESPOND_XML_ATTACHMENT = "document.type.code.in.subject.not-correspond-xml-attachment";
    public final String BILL_ERROR_NOT_EQUALS_ID_SESSION = "generic.sender.error.bill-error-not-equals-id-session";
    public final String DEBIT_NOTE_ERROR_NOT_EQUALS_ID_SESSION = "generic.sender.error.debit-note-error-not-equals-id-session";
    public final String CREDIT_NOTE_ERROR_NOT_EQUALS_ID_SESSION = "generic.sender.error.credit-note-error-not-equals-id-session";
    public final String CONTRACT_NUMBER_INVALID = "generic.contract.number.invalid";
    public final String CONTRACT_NOT_FOUND = "generic.contract.not.found";
    public final String INVOICE_TOTAL_VALUES_INVALID = "invoice.total.values.invalid";
    public final String INVOICE_HEALTH_SERVICE_INVALID = "invoice.health.service.object.invalid";
    public final String INVOICE_TOTAL_VALUES_INVALID_TAXES = "invoice.taxes.value.invalid";


}
