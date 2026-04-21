package com.example.perfulandia.assembler;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.perfulandia.controller.VentaController;
import com.example.perfulandia.model.Venta;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class VentaModelAssembler  implements RepresentationModelAssembler <Venta, EntityModel<Venta>>{

    @Override
    public EntityModel<Venta> toModel(Venta v){
        return EntityModel.of(
            v,
            linkTo(methodOn(VentaController.class).BuscarVenta(v.getIdventa())).withRel("Se Busca una venta por su ID"),
            linkTo(methodOn(VentaController.class).ListarVentas()).withRel("Se lista todas ventas del Sistema"),
            linkTo(methodOn(VentaController.class).EliminarVenta(v.getIdventa())).withRel("Se elimina una venta del Sistema por su ID"),
            linkTo(methodOn(VentaController.class).ActualizarVenta(v.getIdventa(),v)).withRel("Se actualiza la venta del Sistema por su ID")
        );
    }



}
