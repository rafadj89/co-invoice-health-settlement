package com.reven.rips.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

@Data
public class HealthServiceDto {

    private String lenderCode;
    private String policyNumber;
    private String contractNumber;
    private String supplyNumber;
    private String prescriptionNumber;
    private String authorizationNumber;
    private String coverage;
    private String contractingModality;
    private AffiliatedUserDto affiliatedUserDto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate invoicingStartDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate invoicingEndDate;
    private PaymentSharedDto paymentSharedDto;
    private String paymentModality;

    public boolean isEmpty() {
        return this.thisIsEmpty() && Objects.nonNull(this.affiliatedUserDto) && this.affiliatedUserDto
                .isEmpty()
                && Objects.nonNull(this.paymentSharedDto) && this.paymentSharedDto.isEmpty();
    }

    private boolean thisIsEmpty() {
        return Stream.of(this.coverage, this.prescriptionNumber, this.policyNumber, this.supplyNumber,
                        this.invoicingStartDate, this.invoicingEndDate, this.contractNumber,
                        this.authorizationNumber, this.contractingModality, this.lenderCode)
                .allMatch(Objects::isNull);
    }
}
