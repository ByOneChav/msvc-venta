package com.example.perfulandia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.perfulandia.DTO.UsuarioDTO;
import com.example.perfulandia.model.Venta;
import com.example.perfulandia.repository.VentaRepository;

import jakarta.transaction.Transactional;

    // AVISO IMPORTANTE PROFE PARA REALIZAR LOS TESTING, SE TIENE QUE ELIMININAR O COMENTA EL @Autowired

@Service
@Transactional
public class VentaService {

    // @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    public VentaService(VentaRepository ventaRepository){
        this.ventaRepository = ventaRepository;
    }

    

    public List<Venta> BuscarTodo(){
        return ventaRepository.findAll();
    }

    public Venta Buscar(Long idventa){
        return ventaRepository.findById(idventa).get();
    }

    public Venta Guadar(Venta venta){
        return ventaRepository.save(venta);
    }

    public void Eliminar(Long idventa){
        ventaRepository.deleteById(idventa);
    }


    private  WebClient webClient;

    public VentaService(WebClient webClient) {
        this.webClient = webClient;
    }


    public UsuarioDTO BuscarUsuario(String rut){
        UsuarioDTO usuario = webClient
        .get()
        .uri("/{rut}", rut)
        .retrieve()
        .bodyToMono(UsuarioDTO.class)
        .block();
        return usuario;
    }
    


}
