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
                                @RequestBody List<DetalleVenta> detalles){

        Venta venta = repo.findById(ventaId).orElseThrow();

//        detalle.setVenta(venta);
//        detalle.calcularTotal();
//
//        venta.getDetalles().add(detalle);
//        venta.recalcularTotales();

        for (DetalleVenta detalle : detalles) {
            detalle.setVenta(venta);
            detalle.calcularTotal();
            venta.getDetalles().add(detalle);
            System.out.println(detalle.getNumeroTina());
        }


        venta.recalcularTotales();

        return repo.save(venta);
    }

}
