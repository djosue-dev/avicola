package com.example.Apicola.domain.DetalleVenta;

import com.example.Apicola.domain.venta.Venta;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "DetalleVenta")
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
    @Column(name = "numero_tina")
    int numeroTina;
    @Column(name = "peso")
    Double peso;
    @Column(name = "precio_kilo")
    Double precioKilo;
    @Column(name = "subtotal")
    Double subtotal;

    public DetalleVenta(DatosResgitroDetalleVenta datos, Venta venta) {
        this.peso = datos.peso();
        this.precioKilo = datos.precioKilo();
        this.venta = venta;
        this.numeroTina = datos.numeroTina();
        calcularTotal();
    }

    public DetalleVenta(DatosDetalleVentaInput datos, Venta venta) {
        this.peso = datos.peso();
        this.precioKilo = datos.precioKilo();
        this.venta = venta;
        this.numeroTina = datos.numeroTina() != null ? datos.numeroTina() : 0;
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
