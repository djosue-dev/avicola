package com.example.Apicola.controllers;

import com.example.Apicola.domain.DetalleVenta.DatosResgitroDetalleVenta;
import com.example.Apicola.domain.DetalleVenta.DetalleVenta;
import com.example.Apicola.domain.cliente.ClienteRepository;
import com.example.Apicola.domain.venta.DatosRegistroVenta;
import com.example.Apicola.domain.venta.Venta;
import com.example.Apicola.domain.venta.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.Apicola.domain.DetalleVenta.DatosDetalleVentaInput;
import com.example.Apicola.domain.venta.DatosListaVenta;
import java.util.stream.Collectors;

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

    @Transactional
    @PostMapping("/{ventaId}/detalle")
    public Venta agregarDetalle(@PathVariable Long ventaId,
            @RequestBody List<DatosDetalleVentaInput> datosDetalles) {

        Venta venta = repo.findById(ventaId).orElseThrow();

        System.out.println("Recibidos " + datosDetalles.size() + " detalles.");

        for (DatosDetalleVentaInput datos : datosDetalles) {
            System.out.println("Detalle: Peso=" + datos.peso() + ", Precio=" + datos.precioKilo());
            var detalle = new DetalleVenta(datos, venta);
            venta.getDetalles().add(detalle);
        }

        venta.recalcularTotales();

        return repo.save(venta);
    }

    @GetMapping
    public List<DatosListaVenta> listar() {
        return repo.findAll().stream()
                .map(DatosListaVenta::new)
                .collect(Collectors.toList());
    }

}
