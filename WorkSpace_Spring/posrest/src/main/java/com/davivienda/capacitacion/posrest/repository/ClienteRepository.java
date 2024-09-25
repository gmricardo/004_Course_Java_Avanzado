package com.davivienda.capacitacion.posrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davivienda.capacitacion.posrest.modelo.Cliente;
import com.davivienda.capacitacion.posrest.modelo.ClientePK;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, ClientePK> {

}
