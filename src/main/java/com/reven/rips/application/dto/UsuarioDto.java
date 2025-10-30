package com.reven.rips.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {


    private String nit;
    private String nombre;
    private String apellido;
    private String correo;
    private String razonSocial;
    private String telefono;
    private String contrasenia;

}
