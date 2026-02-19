package com.example.Apicola.domain.venta;

import java.time.LocalDateTime;

public record DatosListaVenta(
        Long id,
        String cliente,
        LocalDateTime fecha,
        Double totalKilos,
        Double totalImporte) {
    public DatosListaVenta(Venta venta) {
        this(
                venta.getId(),
                venta.getCliente().getNombreComercial(),
                venta.getFecha(),
                venta.getTotalKilos(),
                venta.getTotalImporte());
    }
}
