package com.davivienda.capacitacion.posrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davivienda.capacitacion.posrest.modelo.ProductoFactura;
import com.davivienda.capacitacion.posrest.modelo.ProductoFacturaPK;

@Repository
public interface ProductoFacturaRepository extends JpaRepository<ProductoFactura, ProductoFacturaPK>{

}
