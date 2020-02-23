package com.claim.checker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoTable {
	
	@JsonProperty(value = "CLink-Call")
	String cLinkCall;

	public String getcLinkCall() {
		return cLinkCall;
	}

	public void setcLinkCall(String cLinkCall) {
		this.cLinkCall = cLinkCall;
	}
	
	

}
