package com.latam.alura.tienda.prueba;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;

public class RegistroDeProducto2 {

	public static void main(String[] args) {
		
		registrarProducto();
		EntityManager em = JPAUtils.getEntityManager();		
		ProductoDao productoDao = new ProductoDao(em);
		
		Producto producto = productoDao.consultaPorId(1l);
		System.out.println("nombre del producto: "+producto.getNombre());
		
		List<Producto> productos = productoDao.consultaTodos();
		productos.forEach(prod -> System.out.println("Descripcion: "+prod.getDescripcion()));
		
		productos = productoDao.consultaPorNombe("Samsung");
		productos.forEach(prod -> System.out.println("Consulta Por Nombre: "+prod.getNombre()));
		
		productos = productoDao.consultaPorNombeDeCategoria("CELULARES");
		productos.forEach(prod -> System.out.println("Consulta Por Nombre De Categora: "+prod.getNombre()));
		
	}

	private static void registrarProducto() {
		Categoria celulares = new Categoria("CELULARES");
		Producto celular = new Producto("Samsung", "Telefono Usado", new BigDecimal("1000"), celulares);
		
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
