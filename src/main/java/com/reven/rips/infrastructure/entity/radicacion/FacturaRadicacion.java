package com.reven.rips.infrastructure.entity.radicacion;

import com.reven.rips.application.dto.XmlDataDTO;
import com.reven.rips.infrastructure.entity.factura.XmlData;
import lombok.Data;

@Data
public class FacturaRadicacion {

    private XmlData xmlData;
    private Errores errores;

}
