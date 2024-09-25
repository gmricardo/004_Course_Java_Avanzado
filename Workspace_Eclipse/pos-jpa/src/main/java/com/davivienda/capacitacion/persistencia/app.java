package com.davivienda.capacitacion.persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.davivienda.capacitacion.persistencia.entities.Cliente;
import com.davivienda.capacitacion.persistencia.entities.ClientePK;
import com.davivienda.capacitacion.persistencia.entities.Factura;
import com.davivienda.capacitacion.persistencia.entities.ProductoFactura;
import com.davivienda.capacitacion.persistencia.entities.ProductoFacturaPK;

public class app {

	public static void main1(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UPCapacitacion");
        EntityManager em = emf.createEntityManager();
        
        ClientePK cPk = new ClientePK();
        cPk.setTipoIdentificacion("CC");
        cPk.setNroIdentificacion("456");
        
        Cliente c = em.find(Cliente.class, cPk);
        
        System.out.println("-----------------------------------------------");
        System.out.println("Nombre del cliente: "+c.getNombre());
        
        List<Factura> lstFact = c.getLstFacturas();
        System.out.println("Numero de facturas: "+lstFact.size());
        
        em.close();
        emf.close();
	}
	
	public static void main2(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UPCapacitacion");
        EntityManager em = emf.createEntityManager();
        
        ClientePK cPk = new ClientePK();
        cPk.setTipoIdentificacion("CC");
        cPk.setNroIdentificacion("456");
        
        Cliente cli = em.find(Cliente.class, cPk);
        
        Factura fact = new Factura();
        fact.setNumero(1);
        fact.setFecha(LocalDate.now());
        fact.setCliente(cli);
        fact.setLstProductosFactura(new ArrayList<ProductoFactura>());
        
        ProductoFacturaPK pfPk = new ProductoFacturaPK();
        pfPk.setCodigoProducto("P05");
        pfPk.setNumeroFactura(1);
        ProductoFactura pf = new ProductoFactura();
        pf.setId(pfPk);
        pf.setCantidadProducto(10);
        pf.setValorUnitario(200000d);
        
        fact.getLstProductosFactura().add(pf);
        
        pfPk = new ProductoFacturaPK();
        pfPk.setCodigoProducto("P06");
        pfPk.setNumeroFactura(1);
        pf = new ProductoFactura();
        pf.setId(pfPk);
        pf.setCantidadProducto(20);
        pf.setValorUnitario(5500d);
        
        fact.getLstProductosFactura().add(pf);
        
        em.getTransaction().begin();
        em.persist(fact);
        
        // Se evita el siguiente codigo porque en Factura esta cascade = CascadeType.ALL en productosFactura 
        /*for (ProductoFactura pf2 : fact.getLstProductosFactura()) {
        	em.persist(pf2);
        }*/
        
        em.getTransaction().commit();
        
        em.close();
        emf.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UPCapacitacion");
        EntityManager em = emf.createEntityManager();
        
        Factura f = em.find(Factura.class, 1);
        
        System.out.println("-----------------------------------------------");
        System.out.println("Nombre del cliente: "+f.getCliente().getNombre());
        System.out.println("Nombre del cliente: "+f.getFecha());
        
        List<ProductoFactura> lstProdFact = f.getLstProductosFactura();
        System.out.println("Numero de productos en la factura: "+ lstProdFact.size());
        
        em.close();
        emf.close();
	}

}
