create table ventas(
id INT AUTO_INCREMENT,
cliente_id
fecha datatime not null,
total_kilos double not null,
total_importe double not null,
estado TINYINT(1) NOT NULL DEFAULT 1,

primary key(id),
constraint fk_ventas_cliente_id foreign key(cliente_id) references clientes(id)
)