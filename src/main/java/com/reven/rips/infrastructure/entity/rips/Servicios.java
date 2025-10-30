package com.reven.rips.infrastructure.entity.rips;

import lombok.Data;

import java.util.List;

@Data
public class Servicios {

    private List<Consulta> consultas;
    private List<Procedimiento> procedimientos;
    private List<OtroServicio> otrosServicios;
    private List<Medicamento> medicamentos;
    private List<Hospitalizacion> hospitalizacion;
    private List<Urgencia> urgencias;
    private List<RecienNacido> recienNacidos;

}