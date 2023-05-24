package com.latam.alura.tienda.prueba;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;

public class RegistroDeProducto {

	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES");
		//Producto celular = new Producto("Samsung", "Telefono Usado", new BigDecimal("1000"), celulares);
		
		EntityManager em = JPAUtils.getEntityManager();
		
		
		//ProductoDao productoDao = new ProductoDao(em);
		//CategoriaDao categoriaDAO = new CategoriaDao(em);
		
		em.getTransaction().begin();
		em.persist(celulares);
		celulares.setNombre("LIBROS");
		//categoriaDAO.guardar(celulares);
		//productoDao.guardar(celular);
		em.getTransaction().commit();
		em.close();
		celulares.setNombre("SOFTWARE");
	}

}
