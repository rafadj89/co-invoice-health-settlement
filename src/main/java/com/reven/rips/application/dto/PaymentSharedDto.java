package com.reven.rips.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class PaymentSharedDto {

    private BigDecimal coPayment;
    private BigDecimal moderatingFee;
    private BigDecimal recuperationFee;
    private BigDecimal sharedPayment;
    private BigDecimal advancePayment;

    public boolean isEmpty() {
        return Stream.of(
                this.coPayment,
                this.moderatingFee,
                this.recuperationFee,
                this.sharedPayment,
                this.advancePayment
        ).allMatch(Objects::isNull);
    }
}
