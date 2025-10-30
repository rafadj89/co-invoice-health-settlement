package com.reven.rips.application.assembler.strategy;

import com.reven.rips.shared.InvoiceDocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class InvoiceDocumentAssemblerStrategyFactory {

  private final Map<InvoiceDocumentType, InvoiceDocumentAssemblerStrategy> strategyMap;

  @Autowired
  public InvoiceDocumentAssemblerStrategyFactory(Set<InvoiceDocumentAssemblerStrategy> strategySet) {
    strategyMap = new HashMap<>();
    strategySet.forEach(
        strategy -> strategyMap.put(strategy.getInvoiceDocumentType(), strategy));
  }

  public InvoiceDocumentAssemblerStrategy findStrategy(String invoiceDocumentTypeCode) {
    return strategyMap
        .get(InvoiceDocumentType.getInvoiceDocumentTypeByCode(invoiceDocumentTypeCode));
  }

}
