package com.example.Apicola.controllers;

import com.example.Apicola.domain.cliente.*;
import com.example.Apicola.services.ServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ControllerCliente {

    @Autowired
    private ServiceCliente service;
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    @PostMapping
    public Cliente guardar(@RequestBody DatosRegistroCliente datos) {
        var cli = new Cliente(datos);
        return service.guarda(cli);


    }

    @GetMapping("/{id}")
    public DatosDetalleCliente detallar(@PathVariable Long id) {
        Cliente cli = service.obtener(id);
        return new DatosDetalleCliente(cli);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public DatosDetalleCliente elliminar(@PathVariable Long id){
        var topico = clienteRepository.getReferenceById(id);
        topico.eliminar();
        return new DatosDetalleCliente(topico);
    }
    @GetMapping
    public List<DatosListaClientes> listar(){
        return clienteRepository.findAllByEstadoTrue().stream()
                .map(DatosListaClientes::new)
                .toList();

    }
}
