SET NAMES utf8;
SET
time_zone = '+00:00';
SET
foreign_key_checks = 0;
SET
sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP DATABASE IF EXISTS proyecto;
CREATE DATABASE proyecto;
USE proyecto;



DROP TABLE IF EXISTS Alumno;
CREATE TABLE Alumno(
    id int AUTO_INCREMENT,
    Dni varchar(9) NOT NULL,
    Nombre varchar(15) NOT NULL,
    Apellidos varchar(30) NOT NULL,
    Email varchar(20) NOT NULL,
    Telefono varchar(10) NOT NULL,
    Perdida_Evaluacion boolean DEFAULT false,
    Fecha_Matriculacion Date,
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



DROP TABLE IF EXISTS Calificacion
CREATE TABLE Calificacion(
    id int NOT NULL,
    id_Alumno int,
    Nota double(2,2),
    Entrega DATE,
    PRIMARY KEY(id),
    FOREIGN KEY (id_Alumno) References Alumno(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



DROP TABLE IF EXISTS Categoria;
CREATE TABLE Categoria(
    id int AUTO_INCREMENT,
    Nombre varchar(15),
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



DROP TABLE IF EXISTS Evaluacion;
CREATE TABLE Evaluacion(
    id int AUTO_INCREMENT,
    Fecha DATETIME NOT NULL,
    Descripcion varchar(50),
    id_Categoria int,
    Nota_Maxima double(2,2),
    Nota_Minima double(2,2),
    Porcentaje_Aprobados double(2,2),
    Porcentaje_Suspensos double(2,2),
    id_Calificaciones int NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (id_Categoria) References Categoria(id),
    FOREIGN KEY (id_Calificaciones) References Calificacion(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;