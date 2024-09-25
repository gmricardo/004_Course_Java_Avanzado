package com.davivienda.capacitacion.pos.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.davivienda.capacitacion.pos.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{

}
