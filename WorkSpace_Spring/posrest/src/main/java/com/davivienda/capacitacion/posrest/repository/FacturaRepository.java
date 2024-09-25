package com.davivienda.capacitacion.posrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davivienda.capacitacion.posrest.modelo.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer>{

}