package com.reven.rips.application.assembler.mapper.legacy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reven.rips.application.assembler.model.*;
import com.reven.rips.application.assembler.model.legacy.Invoice;
import com.reven.rips.application.assembler.model.legacy.InvoiceMessage;
import com.reven.rips.application.dto.*;
import com.reven.rips.shared.Utility;
import com.reven.rips.shared.documentType.DocumentType;
import com.reven.rips.shared.paymenMeansType.PaymentMeansType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;
import com.reven.rips.application.assembler.model.AdditionalInformation.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Slf4j
@Mapper(componentModel = "spring", imports = {Date.class, PaymentMeansType.class,
        DocumentType.class, Objects.class, StringUtils.class})
public abstract class XmlDataMapper {

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final String RETEFUENTE_TAX_NAME = "retefuente";
    private static final String RETEICA_TAX_NAME = "reteica";
    private static final String RETEIVA_TAX_NAME = "reteiva";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String ADMINISTRATION_AIU_DESCRIPTION = "administracion";
    private static final String UNFORESEEN_AIU_DESCRIPTION = "imprevisto";
    private static final String UTILITY_AIU_DESCRIPTION = "utilidad";

    @Mapping(target = "billId", source = "invoice.billId")
    @Mapping(target = "resolutionId", source = "invoice.resolutionId")
    @Mapping(target = "startRange", source = "invoice.startRange", defaultValue = "0L")
    @Mapping(target = "finalRange", source = "invoice.finalRange", defaultValue = "0L")
    @Mapping(target = "documentType",
            expression =
                    "java(Objects.nonNull(invoiceMessage) && Objects.nonNull(invoiceMessage.getDocumentType()) "
                            + "? DocumentType.findByPrimaryKey(invoiceMessage.getDocumentType()) : null)")
    @Mapping(target = "providerNit",
            expression = "java(StringUtils.isNotEmpty(invoiceMessage.getProviderNit()) "
                    + "? Long.parseLong(invoiceMessage.getProviderNit()) : null)")
    @Mapping(target = "lenderId", source = "invoiceMessage.lenderId")
    @Mapping(target = "lenderDocumentType", source = "invoiceMessage.lenderDocumentType")
    @Mapping(target = "lenderName", source = "invoiceMessage.lenderName")
    @Mapping(target = "cufe", source = "invoice.cufe")
    @Mapping(target = "emissionDate", source = "invoice.emissionDate", qualifiedByName = "parseDateFromString")
    @Mapping(target = "expirationDate", source = "invoice.expirationDate", qualifiedByName = "parseDateFromString")
    @Mapping(target = "paymentMeansType",
            expression =
                    "java(Objects.nonNull(invoice) && Objects.nonNull(invoice.getPaymentMeansType()) "
                            + "? PaymentMeansType.findByPrimaryKey(invoice.getPaymentMeansType()) : null)")
    @Mapping(target = "dianValidationDescription", source = "dianValidationDescription")
    @Mapping(target = "amount", source = "invoice.amount", defaultValue = "0")
    @Mapping(target = "unitValue", source = "invoice.unitValue", defaultValue = "0")
    @Mapping(target = "subTotal", source = "invoice.subTotal", defaultValue = "0")
    @Mapping(target = "iva", source = "invoice.iva", defaultValue = "0")
    @Mapping(target = "rtefte", source = "invoice.rtefte", defaultValue = "0")
    @Mapping(target = "totalValue", source = "invoice.totalValue", defaultValue = "0")
    @Mapping(target = "createdDate", expression = "java(new Date())")
    @Mapping(target = "createdBy", constant = Utility.DEFAULT_USER)
    @Mapping(target = "startDate", source = "invoice.startDate", qualifiedByName = "parseDateFromString")
    @Mapping(target = "finalDate", source = "invoice.finalDate", qualifiedByName = "parseDateFromString")
    @Mapping(target = "expeditionDate", source = "invoice.expeditionDate", qualifiedByName = "parseDateFromString")
    @Mapping(target = "tax", source = "invoice.tax")
    @Mapping(target = "taxPercent", source = "invoice.taxPercent", defaultValue = "0")
    @Mapping(target = "taxValue", source = "invoice.taxValue", defaultValue = "0")
    @Mapping(target = "expirationDays", constant = "0")
    @Mapping(target = "healthServices", source = "invoice.additionalInformation.users")
    @Mapping(target = "description", source = "invoice.description")
    @Mapping(target = "validationDate", source = "invoiceMessage.validationDate", qualifiedByName = "parseDateFromString")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorizationId", ignore = true)
    @Mapping(target = "invoiceNotificationStatusType", ignore = true)
    @Mapping(target = "invoiceStatusType", ignore = true)
    @Mapping(target = "purchaseOrder", ignore = true)
    @Mapping(target = "retefuente", ignore = true)
    @Mapping(target = "reteica", ignore = true)
    @Mapping(target = "reteiva", ignore = true)
    @Mapping(target = "taxes", ignore = true)
    @Mapping(target = "thirdEmail", ignore = true)
    @Mapping(target = "thirdType", ignore = true)
    @Mapping(target = "withholdings", ignore = true)
    @Mapping(target = "withholdingTax", source = "invoice.withholding")
    @Mapping(target = "taxTotal", source = "invoice.taxTotal")
    @Mapping(target = "providerNitPartyTaxScheme", source = "invoice.providerNitPartyTaxScheme")
    @Mapping(target = "providerNitPartyLegalEntity", source = "invoice.providerNitPartyLegalEntity")
    @Mapping(target = "providerNitApplicationResponse", source = "applicationResponse.providerNit")
    @Mapping(target = "aiuAmounts", expression = "java(mapAiuAmountDto(invoice.getAiuElements()))")
    @Mapping(target = "baseIva", source = "invoice.baseIva", defaultValue = "0")
    @Mapping(target = "billInitialDate", source = "invoice.billInitialDate", qualifiedByName = "parseDateFromString")
    @Mapping(target = "billFinalDate", source = "invoice.billFinalDate", qualifiedByName = "parseDateFromString")
    @Mapping(target = "fromLoader", source = "invoice.fromLoader")
    @Mapping(target = "totalDiscount", source = "invoice.totalDiscount", defaultValue = "0")
    @Mapping(target = "totalTaxes", source = "invoice.totalTaxes", defaultValue = "0")
    public abstract XmlDataDTO mapXmlDataDto(InvoiceMessage invoiceMessage, Invoice invoice, DianValidation applicationResponse,
                                             String dianValidationDescription);

    @Mapping(target = "lenderCode", source = "lenderCode", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "policyNumber", source = "policyNumber", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "contractNumber", source = "contractNumber", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "supplyNumber", source = "supplyNumber", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "prescriptionNumber", source = "prescriptionNumber", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "authorizationNumber", source = "authorizationNumber", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "coverage", source = "coverage", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "contractingModality", source = "contractingModality", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "invoicingEndDate", source = "invoicingEndDate", qualifiedByName = "parseLocalDateFromString",
            nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "invoicingStartDate", source = "invoicingStartDate", qualifiedByName = "parseLocalDateFromString",
            nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "affiliatedUserDto", source = "user", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "paymentSharedDto", source = "user", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "paymentModality", source = "paymentModality", nullValuePropertyMappingStrategy = IGNORE)
    public abstract HealthServiceDto mapHealthServiceDto(User user);

    @Mapping(target = "type", source = "userType", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "documentType", source = "documentTypeUser", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "documentNumber", source = "documentNumberUser", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "surname", source = "surname", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "secondSurname", source = "secondSurname", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "firstName", source = "firstName", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "secondName", source = "secondName", nullValuePropertyMappingStrategy = IGNORE)
    public abstract AffiliatedUserDto mapAffiliatedUserDto(AdditionalInformation.User user);

    @Mapping(target = "coPayment", source = "coPayment", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "moderatingFee", source = "moderatingFee", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "recuperationFee", source = "recuperationFee", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "sharedPayment", source = "sharedPayment", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "advancePayment", source = "advancePayment", nullValuePropertyMappingStrategy = IGNORE)
    public abstract PaymentSharedDto mapPaymentSharedDto(User user);

    public abstract WithholdingTaxDto mapWithholdingTax(Withholding withholding);

    public abstract WithholdingTaxDto mapTaxTotal(TaxTotal taxTotal);

    @AfterMapping
    protected void validateHealthServices(@MappingTarget XmlDataDTO xmlDataDTO) {
        if (!CollectionUtils.isEmpty(xmlDataDTO.getHealthServices())) {
            List<HealthServiceDto> healthServiceDtoList = xmlDataDTO.getHealthServices()
                    .stream().filter(healthServiceDto -> !healthServiceDto
                            .isEmpty()).collect(Collectors.toList());
            xmlDataDTO.setHealthServices(healthServiceDtoList);
        }
    }

    @AfterMapping
    protected void setTaxes(@MappingTarget XmlDataDTO xmlDataDTO, Invoice invoice) {
        if (Objects.nonNull(invoice) && !CollectionUtils.isEmpty(invoice.getTaxes())) {
            invoice.getTaxes().forEach(tax -> extractTaxesInformation(xmlDataDTO, tax));
        }
    }

    @Named("parseLocalDateFromString")
    protected LocalDate parseLocalDateFromString(String date) {
        try {
            return StringUtils.isNotEmpty(date) ? LocalDate.parse(date) : null;
        } catch (DateTimeParseException dtE) {
            return null;
        }
    }

    @Named("parseDateFromString")
    protected Date parseDateFromString(String date) {
        try {
            return StringUtils.isNotEmpty(date) ? dateFormat.parse(date) : null;
        } catch (ParseException dtE) {
            return null;
        }
    }

    private void extractTaxesInformation(XmlDataDTO xmlDataDTO, Invoice.Tax tax) {
        try {
            switch (tax.getName().toLowerCase()) {
                case RETEFUENTE_TAX_NAME:
                    xmlDataDTO.setRetefuente(objectMapper.writeValueAsString(tax));
                    break;
                case RETEICA_TAX_NAME:
                    xmlDataDTO.setReteica(objectMapper.writeValueAsString(tax));
                    break;
                case RETEIVA_TAX_NAME:
                    xmlDataDTO.setReteiva(objectMapper.writeValueAsString(tax));
                    break;
            }
        } catch (JsonProcessingException e) {
            log.error("An error occurred processing the object taxes: {}", e.getMessage());
        }
    }

    protected AiuAmountDto mapAiuAmountDto(List<AiuElement> aiuElements) {
        final AiuAmountDto aiuAmountDto = new AiuAmountDto();
        if (!CollectionUtils.isEmpty(aiuElements)) {
            aiuElements.forEach(aiuElement -> extractAiuAmounts(aiuAmountDto, aiuElement));
        }
        return aiuAmountDto;
    }

    private void extractAiuAmounts(AiuAmountDto aiuAmountDto, AiuElement aiuElement) {
        switch (aiuElement.getDescription().toLowerCase()) {
            case ADMINISTRATION_AIU_DESCRIPTION:
                aiuAmountDto.setAdministration(aiuElement.getAmount());
                break;
            case UNFORESEEN_AIU_DESCRIPTION:
                aiuAmountDto.setUnforeseen(aiuElement.getAmount());
                break;
            case UTILITY_AIU_DESCRIPTION:
                aiuAmountDto.setUtility(aiuElement.getAmount());
                break;
        }
    }

}
