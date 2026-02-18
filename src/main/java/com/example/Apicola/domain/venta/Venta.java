package com.example.Apicola.domain.venta;


import com.example.Apicola.domain.DetalleVenta.DetalleVenta;
import com.example.Apicola.domain.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name ="Venta")
@Table(name = "ventas")
@Getter
@Setter
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
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DetalleVenta> detalles = new ArrayList<>();


    public Venta(DatosRegistroVenta datos, Cliente cliente){
        this.cliente = cliente;
        this.fecha = datos.fecha();
        this.estado = true;
        recalcularTotales();

    }
//    public Double cobro(DatosRegistroVenta datos){
//        return  datos.totalKilos()*30;
//
//    }
    @PrePersist
    public void prePersist() {
        this.fecha = LocalDateTime.now();
    }

    public void recalcularTotales(){
        this.totalKilos = detalles.stream()
                .mapToDouble(d -> d.getPeso() == null ? 0.0 : d.getPeso())
                .sum();

        this.totalImporte = detalles.stream()
                .mapToDouble(d -> d.getSubtotal() == null ? 0.0 : d.getSubtotal())
                .sum();
    }

}

