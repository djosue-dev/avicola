package com.example.Apicola.controllers;

import com.example.Apicola.domain.cliente.Cliente;
import com.example.Apicola.domain.cliente.ClienteRepository;
import com.example.Apicola.domain.cliente.DatosRegistroCliente;
import com.example.Apicola.domain.venta.DatosRegistroVenta;
import com.example.Apicola.domain.venta.Venta;
import com.example.Apicola.domain.venta.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ventas")
public class ControllerVenta {

    @Autowired
    private VentaRepository repo;
    @Autowired
    private ClienteRepository cli;
    @Transactional
    @PostMapping
    public Venta guardar(@RequestBody DatosRegistroVenta datos) {
        var cliente = cli.getReferenceById(datos.cliente());
        var venta = new Venta(datos, cliente);
        return repo.save(venta);


    }

}
