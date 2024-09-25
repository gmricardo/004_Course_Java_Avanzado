package com.davivienda.capacitacion.web.dtos.soap;

public class ResponseDto {
	
	private HeaderResponseDto header;
	
	private DataResponseDto data;

	public HeaderResponseDto getHeader() {
		return header;
	}

	public void setHeader(HeaderResponseDto header) {
		this.header = header;
	}

	public DataResponseDto getData() {
		return data;
	}

	public void setData(DataResponseDto data) {
		this.data = data;
	}
	
}
