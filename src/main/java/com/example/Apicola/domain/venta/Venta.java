package com.example.Apicola.domain.venta;


import com.example.Apicola.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name ="Venta")
@Table(name = "ventas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    Cliente cliente;

    LocalDateTime fecha;
    Double totalKilos;
    Double totalImporte;
    boolean estado;


    public Venta(DatosRegistroVenta datos, Cliente cliente){
        this.cliente = cliente;
        this.fecha = datos.fecha();
        this.totalKilos = datos.totalKilos();
        this.totalImporte = cobro(datos);
        this.estado = true;

    }
    public Double cobro(DatosRegistroVenta datos){
        return  datos.totalKilos()*30;

    }
    @PrePersist
    public void prePersist() {
        this.fecha = LocalDateTime.now();
    }
}
