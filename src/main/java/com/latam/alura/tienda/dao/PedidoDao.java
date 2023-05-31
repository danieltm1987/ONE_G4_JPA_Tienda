package com.latam.alura.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Pedido;

public class PedidoDao {

	private EntityManager em;
	
	public PedidoDao(EntityManager em) {
		this.em = em;		
	}
	
	public void guardar(Pedido Pedido){		
		this.em.persist(Pedido);
	}
	
	public void actualizar(Pedido Pedido) {
		this.em.merge(Pedido);
	}
	
	public void remover(Pedido Pedido) {
		Pedido = this.em.merge(Pedido);
		this.em.remove(Pedido);
	}
	
	public Pedido consultaPorId(Long id) {
		return em.find(Pedido.class, id);
	}
	
	public List<Pedido> consultaTodos(){
		String jpql = "SELECT P FROM Pedido AS P";		
		return em.createQuery(jpql,Pedido.class).getResultList();
	}
	
	public List<Pedido> consultaPorNombe(String nombre){
		String jpql = "SELECT P FROM Pedido AS P Where P.nombre=:nombre";		
		return em.createQuery(jpql, Pedido.class).setParameter("nombre", nombre).getResultList();
	}
	
	public List<Pedido> consultaPorNombeDeCategoria(String nombre){
		String jpql = "SELECT P FROM Pedido AS P Where P.categoria.nombre=:nombre";		
		return em.createQuery(jpql,Pedido.class).setParameter("nombre", nombre).getResultList();
	}
	
	public BigDecimal consultarPrecioPorNombreDePedido(String nombre) {
		String jpql = "SELECT P.precio FROM Pedido AS P Where P.nombre=:nombre";
		return em.createQuery(jpql,BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	public Double valorPromedioVendido() {
		String jpql = "SELECT AVG(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, Double.class).getSingleResult();
	}
}
