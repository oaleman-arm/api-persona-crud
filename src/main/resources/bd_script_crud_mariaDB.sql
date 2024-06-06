CREATE DATABASE crudPersona

use crudPersona

create table tbl_persona(
    idPersona int not null auto_increment  primary key,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    edad int not null,
    direccion varchar(400) not null,
    telefono varchar(20) null,
    estado char(1) default '1' not null 
);

INSERT INTO tbl_persona (nombre,apellido,edad,direccion,telefono,estado) VALUES ('Juan','Perez',18,'Managua, Nicaragua','50587654321','1');
INSERT INTO tbl_persona (nombre,apellido,edad,direccion,telefono,estado) VALUES ('Maria','Lopez',20,'Carazo, Nicaragua','50587564871','1');