package com.example.Apicola.domain.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name ="Cliente")
@Table(name = "clientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreComercial;
    private String nombrePropietario;
    private String documento;
    private String telefono;
    private String direccion;
    private String email;
    private Boolean estado;

    public Cliente(DatosRegistroCliente datos){
        this.nombreComercial = datos.nombreComercial();
        this.nombrePropietario = datos.nombrePropietario();
        this.documento = datos.documento();
        this.telefono = datos.telefono();
        this.direccion = datos.direccion();
        this.email = datos.email();
        this.estado = true;

    }
    public void eliminar(){
        this.estado = false;
    }
}
