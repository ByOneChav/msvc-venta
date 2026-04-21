package com.example.perfulandia.testVentaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.perfulandia.repository.VentaRepository;
import com.example.perfulandia.service.VentaService;
import com.example.perfulandia.model.Venta;



@ExtendWith(MockitoExtension.class)
public class TestVentaService {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaService ventaService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarTodo(){
        List<Venta> listaVenta = new ArrayList<>();


        Venta v1 = new Venta();
        Venta v2 = new  Venta();

        v1.setIdventa(1L);
        v1.setRunusuario("25820817-K");
        v1.setMetodoPago("Debito-Credito");
        v1.setDireccionEnvio("av Lastarria 943");
        v1.setFechaVenta(LocalDate.parse("2026-09-12"));

        v2.setIdventa(2L);
        v2.setRunusuario("17820817-8");
        v2.setMetodoPago("Debito-Credito");
        v2.setDireccionEnvio("av Temuco 1071");
        v2.setFechaVenta(LocalDate.parse("2026-10-12"));

        listaVenta.add(v1);
        listaVenta.add(v2);

        when(ventaRepository.findAll()).thenReturn(listaVenta);
        List<Venta> resultadoBucada = ventaService.BuscarTodo();
        assertEquals(2, resultadoBucada.size());
        verify(ventaRepository, times(1)).findAll();

    }

    @Test
    public void testBuscarUnaVenta(){

        Venta v1 = new Venta();
        v1.setIdventa(1L);
        v1.setRunusuario("25820817-K");
        v1.setMetodoPago("Debito-Credito");
        v1.setDireccionEnvio("av Lastarria 943");
        v1.setFechaVenta(LocalDate.parse("2026-09-12"));

        when(ventaRepository.findById(1L)).thenReturn(Optional.of(v1));
        Venta ventaBuscada = ventaService.Buscar(1L);
        assertEquals(1, ventaBuscada.getIdventa());
        verify(ventaRepository, times(1)).findById(1L);

    }

    @Test
    public void testGuardarVenta(){

        Venta v1 = new Venta();
        v1.setIdventa(1L);
        v1.setRunusuario("25820817-K");
        v1.setMetodoPago("Debito-Credito");
        v1.setDireccionEnvio("av Lastarria 943");
        v1.setFechaVenta(LocalDate.parse("2026-09-12"));

        when(ventaRepository.save(v1)).thenReturn(v1);
        Venta ventaGuardada = ventaService.Guadar(v1);
        assertEquals(1, ventaGuardada.getIdventa());
        verify(ventaRepository, times(1)).save(v1);
    }

    @Test
    public void testEliminarVenta(){
        Long idVenta = 1L; 
        doNothing().when(ventaRepository).deleteById(idVenta);

        ventaService.Eliminar(idVenta);
        verify(ventaRepository, times(1)).deleteById(idVenta);
    }

}
