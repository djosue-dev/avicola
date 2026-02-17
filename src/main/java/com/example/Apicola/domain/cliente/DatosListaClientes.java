package com.example.Apicola.domain.cliente;

public record DatosListaClientes(
        Long id,
        String nombreComercial,
        String nombrePropietario,
        String documento,
        String telefono,
        String direccion,
        String email
) {
    public DatosListaClientes(Cliente cliente){
        this(
                cliente.getId(),
                cliente.getNombreComercial(),
                cliente.getNombrePropietario(),
                cliente.getDocumento(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getEmail()
        );
    }
}
