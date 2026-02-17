package com.example.Apicola.domain.DetalleVenta;

import com.example.Apicola.domain.venta.Venta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name ="DetalleVenta")
@Table(name = "Detalle_venta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    Venta venta;
    int numeroTina;
    Double peso;
    Double precioKilo;
    Double subtotal;

    public DetalleVenta(DatosResgitroDetalleVenta datos){

    }
}
