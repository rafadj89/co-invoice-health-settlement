package com.reven.rips.application.assembler.model.legacy;

import com.reven.rips.application.assembler.model.AdditionalInformation;
import com.reven.rips.application.assembler.model.AiuElement;
import com.reven.rips.application.assembler.model.TaxTotal;
import com.reven.rips.application.assembler.model.Withholding;
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
@XmlRootElement(name = "invoice")
public class Invoice {

    @XmlElement
    private String billId;
    @XmlElement
    private String startDate;
    @XmlElement
    private String finalDate;
    @XmlElement
    private String emissionDate;
    @XmlElement
    private String resolutionId;
    @XmlElement
    private Long startRange;
    @XmlElement
    private Long finalRange;
    @XmlElement
    private Long providerNitPartyTaxScheme;
    @XmlElement
    private Long providerNitPartyLegalEntity;
    @XmlElement
    private String expirationDate;
    @XmlElement
    private String cufe;
    @XmlElement
    private String paymentMeansType;
    @XmlElement
    private String description;
    @XmlElement
    private BigDecimal totalValue;
    @XmlElement
    private BigDecimal amount;
    @XmlElement
    private BigDecimal unitValue;
    @XmlElement
    private BigDecimal subTotal;
    @XmlElement
    private BigDecimal baseIva;
    @Deprecated
    @XmlElement
    private BigDecimal iva;
    @Deprecated
    @XmlElement
    private BigDecimal rtefte;
    @XmlElement
    private String expeditionDate;
    @Deprecated
    @XmlElement
    private BigDecimal taxPercent;
    @Deprecated
    @XmlElement
    private String tax;
    @Deprecated
    @XmlElement
    private BigDecimal taxValue;
    @Deprecated
    @XmlElement
    private List<Tax> taxes;
    @XmlElement
    private AdditionalInformation additionalInformation;
    @XmlElement
    private List<Withholding> withholding;
    @XmlElement
    private List<TaxTotal> taxTotal;
    @XmlElement
    private List<AiuElement> aiuElements;
    @XmlElement
    private String billInitialDate;
    @XmlElement
    private String billFinalDate;
    @XmlElement
    private boolean fromLoader;
    @XmlElement
    private BigDecimal totalDiscount;
    @XmlElement
    private BigDecimal totalTaxes;

    @Deprecated
    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "taxes")
    public static class Tax {

        @XmlElement
        private String taxId;

        @XmlElement
        private String name;

        @XmlElement
        private String percent;
    }

}
