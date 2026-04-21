package com.example.perfulandia.DTO;


import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaUsuarioDTO {

    @Schema(description = "Codigo del ID de la venta", example = "001")
    private Long idventa;

    @Schema(description = "Rut de Usuario", example = "25.820.817-K")
    private String runusuario;

    @Schema(description = "Tipo de Pago", example = "Debito-Credito")
    private String metodoPago;

    @Schema(description = "Direccion de envio", example = "av Lastarria 943")
    private String direccionEnvio;

    @Schema(description = "Fecha de la venta realizada", example = "2023-08-06")
    private LocalDate fechaVenta;


    @Schema(description = "Nombre del Usuario", example = "Alcindo")
    private String nombre;

    @Schema(description = "Apellido del Usuario", example = "Chavarria Vera")
    private String apellido;

    @Schema(description = "Direccion del Usuario en donde vive", example = "av Lastarria 943")
    private String direccion;

    @Schema(description = "Contacto de la Persona", example = "9 40673637")
    private Long contacto;

    @Schema(description = "Email del Usuario", example = "alc.chavarria@duocuc.cl")
    private String email;

}
