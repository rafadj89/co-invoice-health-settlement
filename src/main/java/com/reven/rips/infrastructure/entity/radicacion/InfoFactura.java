package com.reven.rips.infrastructure.entity.radicacion;

import com.reven.rips.infrastructure.entity.factura.XmlData;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InfoFactura {
    private EstadoValidacionArchivo validacion = new EstadoValidacionArchivo("PENDIENTE");
    private List<XmlData> xmls = new ArrayList<XmlData>();
}