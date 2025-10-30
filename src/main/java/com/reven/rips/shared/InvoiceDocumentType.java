package com.reven.rips.shared;

public enum InvoiceDocumentType {
  DEBIT_NOTE,
  CREDIT_NOTE,
  ELECTRONIC_INVOICE,
  NOT_APPLY;

  private static final String ELECTRONIC_INVOICE_OF_SALE_CODE = "01";
  private static final String ELECTRONIC_INVOICE_OF_SALE_EXPORT_CODE = "02";
  private static final String ELECTRONIC_INVOICE_OF_TRANSMISSION_CODE = "03";
  private static final String ELECTRONIC_INVOICE_OF_SALE_TYPE_4_CODE = "04";
  private static final String DEBIT_NOTE_CODE = "92";
  private static final String CREDIT_NOTE_CODE = "91";

  public static InvoiceDocumentType getInvoiceDocumentTypeByCode(
      final String invoiceDocumentTypeCode) {
    switch (invoiceDocumentTypeCode) {
      case ELECTRONIC_INVOICE_OF_SALE_CODE:
      case ELECTRONIC_INVOICE_OF_SALE_EXPORT_CODE:
      case ELECTRONIC_INVOICE_OF_TRANSMISSION_CODE:
      case ELECTRONIC_INVOICE_OF_SALE_TYPE_4_CODE:
        return InvoiceDocumentType.ELECTRONIC_INVOICE;
      case DEBIT_NOTE_CODE:
        return InvoiceDocumentType.DEBIT_NOTE;
      case CREDIT_NOTE_CODE:
        return InvoiceDocumentType.CREDIT_NOTE;
      default:
        return InvoiceDocumentType.NOT_APPLY;
    }
  }

}
