package com.davivienda.capacitacion.posrest.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.davivienda.capacitacion.posrest.dto.FacturaDto;
import com.davivienda.capacitacion.posrest.error.PosRestError;
import com.davivienda.capacitacion.posrest.modelo.Factura;
import com.davivienda.capacitacion.posrest.modelo.ProductoFactura;
import com.davivienda.capacitacion.posrest.repository.FacturaRepository;
import com.davivienda.capacitacion.posrest.repository.ProductoFacturaRepository;
import java.util.Optional;

@Service
public class FacturaService {
	
	@Autowired
	private FacturaRepository facturaDao;
	
	@Autowired
	private ProductoFacturaRepository productoFacturaDao;
	
	@Autowired
    private ModelMapper modelMapper;
	
	public FacturaDto findByPrimaryKey(Integer numero) {
		
		Optional<Factura> optFact = facturaDao.findById(numero);
		
		if (optFact.isPresent()) {
			
			Factura factura = optFact.get();
			
			FacturaDto fDto = this.modelMapper.map(factura, FacturaDto.class);
			
			return fDto;
		}
		
		throw new PosRestError("Factura no encontrada", HttpStatus.NOT_FOUND);
		
	}
	
	public void modifyFactura(FacturaDto factDto) {
		Factura factMem = this.modelMapper.map(factDto, Factura.class);
		
		Factura factBd = this.facturaDao.getById(factDto.getNumero());
		
		for(ProductoFactura pf: factBd.getLstProductosFactura()) {
			if(!factMem.getLstProductosFactura().contains(pf)) {
				this.productoFacturaDao.delete(pf);
			}
		}
		
		for(ProductoFactura pf: factMem.getLstProductosFactura()) {
			this.productoFacturaDao.save(pf);
		}
		
		this.facturaDao.save(factMem);
	}
}
