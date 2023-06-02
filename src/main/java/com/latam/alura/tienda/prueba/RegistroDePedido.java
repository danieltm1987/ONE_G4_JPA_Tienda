package com.latam.alura.tienda.prueba;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ClienteDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.dao.PedidoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Cliente;
import com.latam.alura.tienda.modelo.ItemsPedido;
import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;
import com.latam.alura.tienda.vo.RelatorioDeVenta;

public class RegistroDePedido {

	public static void main(String[] args) {
		
		registrarProducto();
		
		EntityManager em = JPAUtils.getEntityManager();		
		ProductoDao productoDao = new ProductoDao(em);		
		Producto producto = productoDao.consultaPorId(1l);
		
		ClienteDao clienteDao = new ClienteDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);		
		
		Cliente cliente = new Cliente("Daniel","k6757kjb");
		Pedido pedido = new Pedido(cliente);
		pedido.agregarItems(new ItemsPedido(5, producto, pedido));
		
		em.getTransaction().begin();
		clienteDao.guardar(cliente);
		pedidoDao.guardar(pedido);
		em.getTransaction().commit();
		
		
		BigDecimal valorTotal = pedidoDao.valorTotalVendido();
		System.out.println("Valore Total vendido es :"+valorTotal);
				
		Double valorPromedio = pedidoDao.valorPromedioVendido();
		System.out.println("Valor Promedio Vendido es :"+valorPromedio);
		
		
				
		List<Object[]> relatorio =	pedidoDao.relatorioDeVentas();
		
		for(Object[] obj:relatorio) {
			System.out.println("Nombre :"+obj[0]);
			System.out.println("Suma Cantidad: "+obj[1]);
			System.out.println("Fecha: "+obj[2]);
		}
		
		
		System.out.println("\nRelatorio de Ventas VO \n");
		
		//TODO se genera error en este metodo
		//List<RelatorioDeVenta> relatorioVo = pedidoDao.relatorioDeVentasVO();
		//relatorioVo.forEach(System.out::println);
		
		
		
		System.out.println("nombre del producto: "+producto.getNombre());
		
		List<Producto> productos = productoDao.consultaTodos();
		productos.forEach(prod -> System.out.println("Descripcion: "+prod.getDescripcion()));
		
		productos = productoDao.consultaPorNombe("Samsung");
		productos.forEach(prod -> System.out.println("Consulta Por Nombre: "+prod.getNombre()));
		
		productos = productoDao.consultaPorNombeDeCategoria("CELULARES");
		productos.forEach(prod -> System.out.println("Consulta Por Nombre De Categora: "+prod.getNombre()));
		
		BigDecimal precio = productoDao.consultarPrecioPorNombreDeProducto("Samsung");
		System.out.println("El precio del Producto es :"+precio);
		
		
		
	}

	private static void registrarProducto() {
		Categoria celulares = new Categoria("CELULARES");
		Producto celular = new Producto("Samsung", "Telefono Usado", new BigDecimal("52000"), celulares);
		
		EntityManager em = JPAUtils.getEntityManager();
		
		ProductoDao productoDao = new ProductoDao(em);
		CategoriaDao categoriaDAO = new CategoriaDao(em);
		
		em.getTransaction().begin();
		categoriaDAO.guardar(celulares);
		productoDao.guardar(celular);
		em.getTransaction().commit();
		em.close();
	}
}
