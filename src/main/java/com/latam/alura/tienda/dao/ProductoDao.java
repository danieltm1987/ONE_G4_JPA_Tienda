package com.latam.alura.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

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
	
	public Producto consultaPorId(Long id) {
		return em.find(Producto.class, id);
	}
	
	public List<Producto> consultaTodos(){
		String jpql = "SELECT P FROM Producto AS P";		
		return em.createQuery(jpql,Producto.class).getResultList();
	}
	
	public List<Producto> consultaPorNombe(String nombre){
		String jpql = "SELECT P FROM Producto AS P Where P.nombre=:nombre";		
		return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
	}
	
	public List<Producto> consultaPorNombeDeCategoria(String nombre){
		String jpql = "SELECT P FROM Producto AS P Where P.categoria.nombre=:nombre";		
		return em.createQuery(jpql,Producto.class).setParameter("nombre", nombre).getResultList();
	}
	
	public BigDecimal consultarPrecioPorNombreDeProducto(String nombre) {
		String jpql = "SELECT P.precio FROM Producto AS P Where P.nombre=:nombre";
		return em.createQuery(jpql,BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
	}
	
}
