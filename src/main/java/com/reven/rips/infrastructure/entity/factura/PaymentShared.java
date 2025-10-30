package com.reven.rips.infrastructure.entity.factura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentShared  {

  @Column(name = "copago")
  private BigDecimal coPayment;

  @Column(name = "cuota_moderadora")
  private BigDecimal moderatingFee;

  @Column(name = "cuota_recuperacion")
  private BigDecimal recuperationFee;

  @Column(name = "pago_compartido_pvs")
  private BigDecimal sharedPayment;

  @OneToOne(mappedBy = "paymentShared", fetch = FetchType.LAZY)
  private HealthService healthService;

  @Column(name = "anticipo_pago")
  private BigDecimal advancePayment;
}
