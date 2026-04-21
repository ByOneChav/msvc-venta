package com.example.perfulandia.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long run;
    private String nombre;
    private String apellido;
    private String direccion;
    private Long contacto;
    private String email;

}
