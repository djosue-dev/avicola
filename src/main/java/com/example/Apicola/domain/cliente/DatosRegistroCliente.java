package com.example.Apicola.domain.cliente;

public record DatosRegistroCliente(
         String nombreComercial,
         String nombrePropietario,
         String documento,
         String telefono,
         String direccion,
         String email
) {
}
