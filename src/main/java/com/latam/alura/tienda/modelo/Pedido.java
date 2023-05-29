package com.latam.alura.tienda.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate fecha;
	private BigDecimal valorTotal;
	
	@ManyToOne
	private Cliente cliente;
	
	/*
	 *@ManyToMany
	 *@JoinTable(name="items_pedidox")
	 *private List<Producto> productos;
	 * 
	 */
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemsPedido> items = new ArrayList<>();
	
	public Pedido() {
		
	}
	
	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void agregarItems(ItemsPedido item) {
		item.setPedido(this);
		this.items.add(item);
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	


}
