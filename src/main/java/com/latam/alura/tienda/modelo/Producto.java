package com.latam.alura.tienda.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private String nombre;
	private String descripcion;
	private BigDecimal precio;
	private LocalDate fechadeRegistro = LocalDate.now();	
	
	@ManyToOne
	private Categoria categoria;
	
	public Producto() {
		
	}

	public Producto(String nombre, String descripcion, BigDecimal precio, Categoria categoria) {		
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public BigDecimal getPrecio() {
		return precio;
	}
	
	public void setPrecio(BigDecimal bigDecimal) {
		this.precio = bigDecimal;
	}
	
	public LocalDate getFechadeRegistro() {
		return fechadeRegistro;
	}

	public void setFechadeRegistro(LocalDate fechadeRegistro) {
		this.fechadeRegistro = fechadeRegistro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
