package com.latam.alura.tienda.vo;

import java.time.LocalDate;

public class RelatorioDeVenta {
	
	private String nombreDelProducto;
	private Long CantidadDeProducto;
	private LocalDate FechaDeUltimaVenta;
	
	public RelatorioDeVenta() {
		
	}

	public RelatorioDeVenta(String nombreDelProducto, Long cantidadDeProducto, LocalDate fechaDeUltimaVenta) {		
		this.nombreDelProducto = nombreDelProducto;
		this.CantidadDeProducto = cantidadDeProducto;
		this.FechaDeUltimaVenta = fechaDeUltimaVenta;
	}

	public String getNombreDelProducto() {
		return nombreDelProducto;
	}

	public void setNombreDelProducto(String nombreDelProducto) {
		this.nombreDelProducto = nombreDelProducto;
	}

	public Long getCantidadDeProducto() {
		return CantidadDeProducto;
	}

	public void setCantidadDeProducto(Long cantidadDeProducto) {
		this.CantidadDeProducto = cantidadDeProducto;
	}

	public LocalDate getFechaDeUltimaVenta() {
		return FechaDeUltimaVenta;
	}

	public void setFechaDeUltimaVenta(LocalDate fechaDeUltimaVenta) {
		this.FechaDeUltimaVenta = fechaDeUltimaVenta;
	}
	
/*
	@Override
	public String toString() {
		return "RelatorioDeVenta [nombreDelProducto=" + nombreDelProducto + ", cantidadDeProducto=" + CantidadDeProducto
				+ ", fechaDeUltimaVenta=" + FechaDeUltimaVenta + "]";
	}
	*/
	
	
	
	

}
