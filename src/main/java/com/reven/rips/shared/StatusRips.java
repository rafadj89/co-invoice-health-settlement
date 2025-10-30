package com.reven.rips.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum StatusRips {
    PROCESANDO("PROCESANDO", "Estado del rips mientras se esta validando"),
    VALIDADO("VALIDADO", "Estado del rips cuando finalizaron sus validaciones");

    private final String value;
    private final String description;


}
