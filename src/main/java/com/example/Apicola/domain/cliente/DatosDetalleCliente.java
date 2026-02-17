package com.example.Apicola.domain.cliente;

public record DatosDetalleCliente(

         String nombreComercial,
         String nombrePropietario,
         String documento,
         String telefono,
         String direccion,
         String email
) {
public DatosDetalleCliente(Cliente cliente)
{
    this(

            cliente.getNombreComercial(),
            cliente.getNombrePropietario(),
            cliente.getDocumento(),
            cliente.getTelefono(),
            cliente.getDireccion(),
            cliente.getEmail()

    );
}}
