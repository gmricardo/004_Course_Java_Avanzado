package com.davivienda.capacitacion.persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.davivienda.capacitacion.persistencia.entities.Producto;


public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UPCapacitacion");
        EntityManager em = emf.createEntityManager();
        
        
        
        /*System.out.println("_____________________");
        Producto p = em.find(Producto.class, "P01");
        System.out.println(p.getCodigo());
        System.out.println(p.getNombre());
        System.out.println(p.getValor());
        System.out.println(p.getUsuarioCrea());
        System.out.println(p.getFechaCreacion());
        System.out.println("_____________________");*/
        
        Producto p = em.find(Producto.class, "P01");
        p.setValor(2200.00);
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        
        /*Producto p1 = new Producto();
        p1.setCodigo("P03");
        p1.setNombre("Calculadora");
        p1.setValor(50000.00);
        p1.setUsuarioCrea("USR1");
        p1.setFechaCreacion(new Date());
        
        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();
        
        
        Producto p2 = em.find(Producto.class, "P03");
        em.getTransaction().begin();
        em.remove(em.merge(p2));
        em.getTransaction().commit();
        */
        
        /*Query q = em.createNamedQuery("Producto.findAll", Producto.class);
        List<Producto> lst = q.getResultList();
        for (Producto p: lst) {
        	System.out.println(p.getNombre());
        }*/
        
        /*Query q = em.createNamedQuery("Producto.findByUsuario", Producto.class);
        q.setParameter("x","USR01");
        List<Producto> lst = q.getResultList();
        for (Producto p: lst) {
        	System.out.println(p.getNombre());
        }*/
        
        em.close();
        emf.close();
        
    }
}
