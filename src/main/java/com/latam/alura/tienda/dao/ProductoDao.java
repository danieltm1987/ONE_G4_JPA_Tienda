package com.latam.alura.tienda.dao;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;

public class ProductoDao {

	private EntityManager em;
	
	public ProductoDao(EntityManager em) {
		this.em = em;		
	}
	
	public void guardar(Producto producto){		
		this.em.persist(producto);
	}
	
	public void actualizar(Producto producto) {
		this.em.merge(producto);
	}
	
	public void remover(Producto producto) {
		producto = this.em.merge(producto);
		this.em.remove(producto);
	}
}
