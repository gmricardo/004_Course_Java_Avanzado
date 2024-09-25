package com.davivienda.capacitacion.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davivienda.capacitacion.pos.modelo.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

}
