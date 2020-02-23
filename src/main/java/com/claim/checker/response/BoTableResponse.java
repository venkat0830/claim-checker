package com.claim.checker.response;

import org.springframework.util.MultiValueMap;

import com.claim.checker.model.BoTable;

public class BoTableResponse {
	
	BoTable boTable;
	String status;
	ErrorResponse errorResponse;
	MultiValueMap<String, String> headerMap;
	public BoTable getBoTable() {
		return boTable;
	}
	public void setBoTable(BoTable boTable) {
		this.boTable = boTable;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	public MultiValueMap<String, String> getHeaderMap() {
		return headerMap;
	}
	public void setHeaderMap(MultiValueMap<String, String> headerMap) {
		this.headerMap = headerMap;
	}
	
	

}
