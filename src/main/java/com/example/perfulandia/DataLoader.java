package com.example.perfulandia;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.perfulandia.model.Venta;
import com.example.perfulandia.service.VentaService;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner {

    private final Faker faker = new Faker(new Locale("es", "cl"));
    private final Random random = new Random();

    @Autowired
    private  VentaService ventaService;

    @Override
    public void run(String... args) throws Exception{
        for(int i=0; i<15; i++){
            Venta ventaFicticia = new Venta();
            ventaFicticia.setIdventa(generarVenta());
            ventaFicticia.setRunusuario(generarRutUsuario());
            ventaFicticia.setMetodoPago(generarMetodoPago());
            ventaFicticia.setDireccionEnvio(faker.address().streetAddress());
            ventaFicticia.setFechaVenta(LocalDate.now().minusDays(random.nextInt(60)));
            ventaService.Guadar(ventaFicticia);
            System.out.println("Venta Registrada: "+ventaFicticia.getIdventa());
        }


    }

    private static long idVentaActual = 10; // valor inicial
    private Long generarVenta() {
        idVentaActual += 2;
        return idVentaActual;
    }

    private String generarRutUsuario(){
        int cuerpo = 10000000 + random.nextInt(8999999);
        String dv = calcularDv(cuerpo);
        return cuerpo + "-"+dv;
    }

    private String  calcularDv(int cuerpo){
        int m = 0, s = 1;
        while (cuerpo != 0) {
            s = (s + cuerpo % 10 * (9 - m++ % 6)) % 11;
            cuerpo /= 10;
        }

        if (s == 0) return "K";
        if (s == 1) return "0";
        return String.valueOf(11 - s);
    }



    private String generarMetodoPago() {
        String[] metodos = {"Tarjeta de Crédito", "Tarjeta de Débito", "Transferencia Bancaria", "Efectivo", "PayPal"};
        return metodos[new Random().nextInt(metodos.length)];
    }

    // private String generarDireccionEnvio() {
    //     String[] calles = {"Av. Providencia", "Calle Los Pinos", "Av. Libertador", "Pasaje Las Flores", "Camino Real"};
    //     int numero = new Random().nextInt(1000) + 1;
    //     String ciudad = new String[] {"Santiago", "Valparaíso", "Concepción", "La Serena", "Antofagasta"}[new Random().nextInt(5)];
    //     return calles[new Random().nextInt(calles.length)] + " #" + numero + ", " + ciudad;
    // }



}
