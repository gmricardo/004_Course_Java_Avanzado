package com.davivienda.capacitacion.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.davivienda.capacitacion.persistencia.entities.Producto;


public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UPCapacitacion");
        EntityManager em = emf.createEntityManager();
        
        Producto p = em.find(Producto.class, "P01");
        
        System.out.println("_____________________");
        System.out.println(p.getCodigo());
        System.out.println(p.getNombre());
        System.out.println(p.getValor());
        System.out.println(p.getUsuarioCrea());
        System.out.println(p.getFechaCreacion());
        
        em.close();
        emf.close();
    }
}
