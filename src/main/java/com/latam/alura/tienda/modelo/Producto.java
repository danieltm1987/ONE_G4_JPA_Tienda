package com.latam.alura.tienda.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ibm.icu.math.BigDecimal;

@Entity
@Table(name="productos")
public class Producto {
	private Long id;
	private String nombre;
	private String descripcion;
	private BigDecimal precio;

}
