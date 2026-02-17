package com.example.Apicola.domain.DetalleVenta;

import com.example.Apicola.domain.venta.Venta;

public record DatosResgitroDetalleVenta(
        Long venta,
        int numeroTina,
        Double peso,
        Double precioKilo,
        Double subtotal
) {
}
