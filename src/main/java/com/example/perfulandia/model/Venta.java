package com.example.perfulandia.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "VENTA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa la Entidad de Venta")
public class Venta {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDVENTA")
    @Schema(description = "Codigo de la venta autogenerado", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idventa;


    @Column(nullable = false)
    @Schema(description = "El Rut de usuario", example = "25820817-k")
    private String runusuario;

    @Column(nullable = false)
    @Schema(description = "El tipo de pago", example = "Debito-Credito")
    private String metodoPago;

    @Column(nullable = false)
    @Schema(description = "La direccion del usuario", example = "av Lastarria 943")
    private String direccionEnvio;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Schema(description = "La fecha de la Venta", example = "2023-08-06")
    private LocalDate fechaVenta;



}
