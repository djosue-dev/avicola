package com.example.Apicola.domain.venta;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record DatosRegistroVenta(


        Long cliente,
        LocalDateTime fecha,
        Double totalKilos


) {
}
