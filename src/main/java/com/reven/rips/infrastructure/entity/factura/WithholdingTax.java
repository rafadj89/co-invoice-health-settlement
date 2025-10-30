package com.reven.rips.infrastructure.entity.factura;

import com.reven.rips.shared.withholdingtaxtype.WithholdingTaxType;
import com.reven.rips.shared.withholdingtaxtype.WithholdingTaxTypeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
public class WithholdingTax {

  @Column(name = "codigo")
  private String taxId;

  @Column(name = "nombre")
  private String taxName;

  @Column(name = "tipo")
  @Convert(converter = WithholdingTaxTypeConverter.class)
  private WithholdingTaxType taxType;

  @Column(name = "descripcion")
  private String description;


}
