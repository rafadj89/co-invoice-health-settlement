package com.reven.rips.application.assembler.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "additionalInformation")
public class AdditionalInformation {

    @XmlElement
    private List<User> users;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "users")
    public static class User {

        @XmlElement
        private String lenderCode;

        @XmlElement
        private String documentTypeUser;

        @XmlElement
        private String documentNumberUser;

        @XmlElement
        private String surname;

        @XmlElement
        private String secondSurname;

        @XmlElement
        private String firstName;

        @XmlElement
        private String secondName;

        @XmlElement
        private String userType;

        @XmlElement
        private String contractingModality;

        @XmlElement
        private String coverage;

        @XmlElement
        private String authorizationNumber;

        @XmlElement
        private String prescriptionNumber;

        @XmlElement
        private String supplyNumber;

        @XmlElement
        private String contractNumber;

        @XmlElement
        private String policyNumber;

        @XmlElement
        private String invoicingStartDate;

        @XmlElement
        private String invoicingEndDate;

        @XmlElement
        private BigDecimal coPayment;

        @XmlElement
        private BigDecimal moderatingFee;

        @XmlElement
        private BigDecimal recuperationFee;

        @XmlElement
        private BigDecimal sharedPayment;

        @XmlElement
        private String paymentModality;

        @XmlElement
        private BigDecimal advancePayment;
    }
}