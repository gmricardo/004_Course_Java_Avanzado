package com.davivienda.capacitacion.web.dtos.soap;

public class RequestDto {
	private HeaderRequestDto header;
	
	private DataRequestDto data;

	public HeaderRequestDto getHeader() {
		return header;
	}

	public void setHeader(HeaderRequestDto header) {
		this.header = header;
	}

	public DataRequestDto getData() {
		return data;
	}

	public void setData(DataRequestDto data) {
		this.data = data;
	}
	
	

}
