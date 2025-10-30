package com.reven.rips.infrastructure.entity.radicacion;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Errores {

    private String mensaje;
    private String code;
}
