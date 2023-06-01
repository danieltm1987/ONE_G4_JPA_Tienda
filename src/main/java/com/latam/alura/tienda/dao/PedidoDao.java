package com.latam.alura.tienda.dao;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.vo.RelatorioDeVenta;

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
	
	public List<Object[]> relatorioDeVentas(){
		String jpql = "SELECT producto.nombre, "
				+ "SUM(item.cantidad), "
				+ "MAX(pedido.fecha) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.items item "
				+ "JOIN item.producto producto "
				+ "GROUP BY producto.nombre "
				+ "ORDER BY item.cantidad DESC";
		
		return em.createQuery(jpql,Object[].class).getResultList();
	}
	
	/*
	 * VO = Value Object
	 * Uno de los patrones más útiles en mi día a día son los value objects. 
	 * Los value objects (VO) son objetos que se identifican por su contenido y 
	 * nos ayudan a modelar conceptos de negocio.
	 * 
	 */
	
	public List<RelatorioDeVenta> relatorioDeVentasVO(){
		String jpql = "SELECT new com.latam.alura.tienda.vo.RelatorioDeVenta(producto.nombre, "
				+ "SUM(item.cantidad), "
				+ "MAX(pedido.fecha)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.items item "
				+ "JOIN item.producto producto "
				+ "GROUP BY producto.nombre "
				+ "ORDER BY item.cantidad DESC ";
				
		
		return (List<RelatorioDeVenta>) em.createQuery(jpql,RelatorioDeVenta.class).getSingleResult();
	}
	
	
}
