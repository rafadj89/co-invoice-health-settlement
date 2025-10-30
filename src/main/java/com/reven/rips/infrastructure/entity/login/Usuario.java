package com.reven.rips.infrastructure.entity.login;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
/*

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usuario")
public class Usuario implements UserDetails {

    @Id
    private String id;
    private String nit;
    private String nombre;
    private String apellido;
    private String correo;
    private String razonSocial;
    private String telefono;
    private String contrasenia;
    @CreatedDate
    private LocalDateTime fechaCreacion;
    @LastModifiedDate
    private LocalDateTime fechaActualizacion;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return correo;
    }


}

 */
