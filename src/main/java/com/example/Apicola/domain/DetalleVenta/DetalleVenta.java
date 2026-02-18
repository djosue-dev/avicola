package com.example.Apicola.domain.DetalleVenta;

import com.example.Apicola.domain.venta.Venta;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity(name ="DetalleVenta")
@Table(name = "Detalle_venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id")
    @JsonBackReference
    Venta venta;
    int numeroTina;
    Double peso;//el peso de los kilos
    Double precioKilo;//aqui va el precio
    Double subtotal;

    public DetalleVenta(DatosResgitroDetalleVenta datos, Venta venta){
        this.peso = datos.peso();
        this.precioKilo = datos.precioKilo();
        this.venta = venta;
        calcularTotal();
    }

    public void calcularTotal() {
        if (this.peso == null) {
            this.peso = 0.0;
        }

        if (this.precioKilo == null) {
            this.precioKilo = 0.0;
        }

        this.subtotal = this.peso * this.precioKilo;
    }
}
