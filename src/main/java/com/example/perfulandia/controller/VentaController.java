package com.example.perfulandia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.perfulandia.DTO.UsuarioDTO;
import com.example.perfulandia.DTO.VentaUsuarioDTO;
import com.example.perfulandia.assembler.VentaModelAssembler;
import com.example.perfulandia.model.Venta;
import com.example.perfulandia.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/perfulandia/ventas")
@Tag(
    name = "VENTA",
    description = "Endpoints para gestionar ventas: crear, listar, actualizar, eliminar y obtener detalles de ventas con o sin datos de usuario.")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private VentaModelAssembler assembler;

    
    // ENPOINT LISTAR TODAS LAS VENTAS
    @GetMapping
    @Operation(summary = "Lista todas las Ventas", description = "Devuelve una lista completa de todas las ventas registradas en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente todas las ventas",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))),
        @ApiResponse(responseCode = "404", description = "No se encontró ninguna venta",
            content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran datos"))),
        @ApiResponse(responseCode = "500", description = "Error al listar las ventas")
    })
    public ResponseEntity<?> ListarVentas(){
        List<Venta> ventas = ventaService.BuscarTodo();
        if (ventas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra Ventas");
        } else {
            return ResponseEntity.ok(assembler.toCollectionModel(ventas));
        }
    }



    // ENDPOINT QUE  BUSCA UNA VENTA POR ID
    @GetMapping("/{idventa}")
    @Operation(summary = "Obtener Venta por ID", description = "Recupera la información de una venta específica mediante su identificador único (idventa)." )
    @Parameters(value = {
        @Parameter(name = "idventa", description = "ID de la venta que se requiere buscar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venta actualizada correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos para actualizar la venta",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Error del servidor al actualizar venta",
            content = @Content)
    })
    public ResponseEntity<?> BuscarVenta(@PathVariable Long idventa){
        try {
            Venta venta = ventaService.Buscar(idventa);
            
            // AKI SE AGRAGA ASSEMBLER
            return ResponseEntity.ok(assembler.toModel(venta));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro Venta con el ID");
        }
    }




    // ENPOINT QUE GUARDAR UNA VENTA
    @PostMapping
    @Operation(summary = "Guardar nueva Venta", description = "Registra una nueva venta en el sistema a partir de los datos enviados en el cuerpo de la solicitud.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description= "Objeto venta que va a registrar", required = true, content = @Content(schema = @Schema(implementation = Venta.class))))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Venta creada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos para crear la venta"),
        @ApiResponse(responseCode = "500", description = "Error al crear la venta")
    })
    public ResponseEntity<?> GuadarVenta(@RequestBody Venta venta){
        try {
            Venta nuevaVenta = ventaService.Guadar(venta);
            return ResponseEntity.ok(assembler.toModel(nuevaVenta));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No se pudo registrar la Nueva Venta");
        }
    }

    // ENPOINT QUE ELIMINA UNA VENTA POR ID
    @DeleteMapping("/{idventa}")
    @Operation(summary = "Eliminar Venta por ID", description = "Elimina una venta registrada del Sistema, usando su ID como referencia. Esta acción es irreversible.")
    @Parameters(value = {
        @Parameter(name = "idventa", description = "Ingrese el ID de la venta a Eliminar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venta eliminada correctamente",
        content = @Content(mediaType = "application/json",
        schema = @Schema(type = "string", example = "Se elimina venta"))),

        @ApiResponse(responseCode = "404", description = "Venta no encontrada",
            content = @Content(mediaType = "application/json",
            schema = @Schema(type = "string", example = "Venta no registrada"))),
        @ApiResponse(responseCode = "500", description = "Error al eliminar la venta")
    })
    public ResponseEntity<String> EliminarVenta(@PathVariable Long idventa){
        try{
            ventaService.Eliminar(idventa);
            return ResponseEntity.status(HttpStatus.OK).body("SE ELIMINÓ LA VENTA");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LA VENTA NO ESTA REGISTRADA PARA PODER SER ELIMINADA");
        }
    }
    

    // ENPOINT QUE ACTUALIZA UNA VENTA POR ID
    @PutMapping("/{idventa}")
    @Operation(summary = "Actualizar Venta por ID", description = "Modifica los datos de una venta existente, identificada por su ID. Requiere el cuerpo de la venta actualizado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venta actualizada correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos para actualizar la venta",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Error del servidor al actualizar venta",
            content = @Content)
    })
    public ResponseEntity<?> ActualizarVenta(@PathVariable Long idventa, @RequestBody Venta ventaActualizar){
        try{
            Venta ventaBuscada = ventaService.Buscar(idventa);
            ventaBuscada.setRunusuario(ventaActualizar.getRunusuario());
            ventaBuscada.setMetodoPago(ventaActualizar.getMetodoPago());
            ventaBuscada.setDireccionEnvio(ventaActualizar.getDireccionEnvio());
            ventaBuscada.setFechaVenta(ventaActualizar.getFechaVenta());
            ventaService.Guadar(ventaBuscada);
            
            return ResponseEntity.ok(assembler.toModel(ventaBuscada));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EL ID DE LA VENTA NO ESTÁ REGISTRADOA EN LA BASE DE DATO PARA PODER ACTUALIZAR");
        }
        
    }
    
    
    @GetMapping("/VentaUsuario/{idventa}")
    @Operation(summary = "Obtener venta con datos de usuario", description = "Recupera una venta y muestra información adicional del usuario asociado a dicha venta, mediante su ID.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Venta y datos de usuario obtenidos correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = VentaUsuarioDTO.class))),
    @ApiResponse(responseCode = "404", description = "No se encontró la venta o el usuario",
        content = @Content),
    @ApiResponse(responseCode = "500", description = "Error al obtener los datos")
    })
    public ResponseEntity<?> DatosVentaUsuario(@PathVariable Long idventa){
        try {
            Venta ventaBuscada = ventaService.Buscar(idventa);
            UsuarioDTO usuarioDTO = ventaService.BuscarUsuario(ventaBuscada.getRunusuario());
            VentaUsuarioDTO ventaUsuarioDTO = new VentaUsuarioDTO();

            
            ventaUsuarioDTO.setIdventa(ventaBuscada.getIdventa());
            ventaUsuarioDTO.setRunusuario(ventaBuscada.getRunusuario());
            ventaUsuarioDTO.setMetodoPago(ventaBuscada.getMetodoPago());
            ventaUsuarioDTO.setDireccionEnvio(ventaBuscada.getDireccionEnvio());
            ventaUsuarioDTO.setFechaVenta(ventaBuscada.getFechaVenta());

            ventaUsuarioDTO.setNombre(usuarioDTO.getNombre());
            ventaUsuarioDTO.setApellido(usuarioDTO.getApellido());
            ventaUsuarioDTO.setDireccion(usuarioDTO.getDireccion());
            ventaUsuarioDTO.setContacto(usuarioDTO.getContacto());
            ventaUsuarioDTO.setEmail(usuarioDTO.getEmail());

            return ResponseEntity.ok(ventaUsuarioDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra VENTA o USUARIO no està registrado");
        }
    }

}
