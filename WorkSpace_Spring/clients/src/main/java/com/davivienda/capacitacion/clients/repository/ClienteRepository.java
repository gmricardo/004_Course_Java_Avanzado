package com.davivienda.capacitacion.clients.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davivienda.capacitacion.clients.modelo.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
