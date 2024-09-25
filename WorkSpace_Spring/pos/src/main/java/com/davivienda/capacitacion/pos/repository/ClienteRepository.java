package com.davivienda.capacitacion.pos.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.davivienda.capacitacion.pos.modelo.Cliente;
import com.davivienda.capacitacion.pos.modelo.ClientePK;

public interface ClienteRepository extends JpaRepository<Cliente, String>{

}
