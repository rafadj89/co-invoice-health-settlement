package com.reven.rips.application.assembler.mapper;

import com.reven.rips.application.assembler.model.AdditionalInformation;
import com.reven.rips.application.assembler.model.ElectronicInvoice;
import com.reven.rips.application.dto.AffiliatedUserDto;
import com.reven.rips.application.dto.ElectronicInvoiceDto;
import com.reven.rips.application.dto.HealthServiceDto;
import com.reven.rips.application.dto.PaymentSharedDto;
import com.reven.rips.shared.InvoiceDocumentType;
import com.reven.rips.shared.Utility;
import com.reven.rips.shared.paymenMeansType.PaymentMeansType;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, imports = {
    LocalDate.class, PaymentMeansType.class}, uses = LocalDateFromStringMapper.class)
public interface ElectronicInvoiceMapper {

  @Mapping(target = "createdBy", constant = Utility.DEFAULT_USER)
  @Mapping(target = "createdDate", expression = "java(LocalDate.now())")
  @Mapping(target = "expirationDays", constant = "0")
  @Mapping(target = "subTotal", source = "electronicInvoice.subTotal", defaultValue = "0")
  @Mapping(target = "iva", source = "electronicInvoice.iva", defaultValue = "0")
  @Mapping(target = "withholdingTax", source = "electronicInvoice.withholdingTax", defaultValue = "0")
  @Mapping(target = "startRange", source = "electronicInvoice.startRange", defaultValue = "0")
  @Mapping(target = "finalRange", source = "electronicInvoice.finalRange", defaultValue = "0")
  @Mapping(target = "totalValue", source = "electronicInvoice.totalValue", defaultValue = "0")
  @Mapping(target = "amount", source = "electronicInvoice.amount", defaultValue = "0")
  @Mapping(target = "unitValue", source = "electronicInvoice.unitValue", defaultValue = "0")
  @Mapping(target = "tax", source = "electronicInvoice.tax", defaultValue = "0")
  @Mapping(target = "taxPercent", source = "electronicInvoice.taxPercent", defaultValue = "0")
  @Mapping(target = "taxValue", source = "electronicInvoice.taxValue", defaultValue = "0")
  @Mapping(target = "paymentMeansType",
      expression = "java(PaymentMeansType.findByPrimaryKey(electronicInvoice.getPaymentMeansType()))")
  @Mapping(target = "startDate", source = "electronicInvoice.startDate", qualifiedByName = "parseDateFromString")
  @Mapping(target = "finalDate", source = "electronicInvoice.finalDate", qualifiedByName = "parseDateFromString")
  @Mapping(target = "emissionDate", source = "electronicInvoice.emissionDate", qualifiedByName = "parseDateFromString")
  @Mapping(target = "expirationDate", source = "electronicInvoice.expirationDate", qualifiedByName = "parseDateFromString")
  @Mapping(target = "expeditionDate", source = "electronicInvoice.expeditionDate", qualifiedByName = "parseDateFromString")
  @Mapping(target = "healthServices", source = "electronicInvoice.additionalInformation.users")
  ElectronicInvoiceDto mapElectronicInvoiceDto(ElectronicInvoice electronicInvoice,
                                               InvoiceDocumentType invoiceDocumentType);

  @Mapping(target = "lenderCode", source = "lenderCode", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "policyNumber", source = "policyNumber", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "contractNumber", source = "contractNumber", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "supplyNumber", source = "supplyNumber", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "prescriptionNumber", source = "prescriptionNumber", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "authorizationNumber", source = "authorizationNumber", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "coverage", source = "coverage", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "contractingModality", source = "contractingModality", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "invoicingEndDate", source = "invoicingEndDate", qualifiedByName = "parseDateFromString",
      nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "invoicingStartDate", source = "invoicingStartDate", qualifiedByName = "parseDateFromString",
      nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "affiliatedUserDto", source = "user", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "paymentSharedDto", source = "user", nullValuePropertyMappingStrategy = IGNORE)
  HealthServiceDto mapHealthServiceDto(AdditionalInformation.User user);

  @Mapping(target = "type", source = "userType", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "documentType", source = "documentTypeUser", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "documentNumber", source = "documentNumberUser", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "surname", source = "surname", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "secondSurname", source = "secondSurname", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "firstName", source = "firstName", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "secondName", source = "secondName", nullValuePropertyMappingStrategy = IGNORE)
  AffiliatedUserDto mapAffiliatedUserDto(AdditionalInformation.User user);

  @Mapping(target = "coPayment", source = "coPayment", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "moderatingFee", source = "moderatingFee", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "recuperationFee", source = "recuperationFee", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "sharedPayment", source = "sharedPayment", nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "advancePayment", source = "advancePayment", nullValuePropertyMappingStrategy = IGNORE)
  PaymentSharedDto mapPaymentSharedDto(AdditionalInformation.User user);

  @AfterMapping
  default void validateHealthServices(@MappingTarget ElectronicInvoiceDto electronicInvoiceDto) {
    if (!CollectionUtils.isEmpty(electronicInvoiceDto.getHealthServices())) {
      List<HealthServiceDto> healthServiceDtoList = electronicInvoiceDto.getHealthServices().stream().filter(healthServiceDto -> !healthServiceDto
          .isEmpty()).collect(Collectors.toList());
      electronicInvoiceDto.setHealthServices(healthServiceDtoList);
    }
  }

}
