create table detalle_venta(
id INT AUTO_INCREMENT,
venta_id bigint not null,
numero_tina int not null,
peso double not null,
precio_kilo double not null,
subtotal double not null,
primary key(id),
constraint fk_DetalleVenta_venta_id foreign key(venta_id) references ventas(id)
)

