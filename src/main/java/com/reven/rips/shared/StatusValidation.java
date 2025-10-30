package com.reven.rips.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum StatusValidation {
    PROCESANDO("PROCESANDO", "Estado del rips mientras se esta validando"),
    RECHAZADO("RECHAZADO", "Estado del rips cuando finalizaron sus validaciones de manera no exitoa"),
    ACEPTADO("ACEPTADO", "Estado del rips cuando finalizaron sus validaciones de manera exitosa");

    private final String value;
    private final String description;


}
