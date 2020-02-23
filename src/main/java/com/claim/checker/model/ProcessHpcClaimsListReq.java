package com.claim.checker.model;

import java.util.List;

import com.claim.checker.dto.RecordDto;

public class ProcessHpcClaimsListReq {
	
	private List<RecordDto> claimsList;

	public List<RecordDto> getClaimsList() {
		return claimsList;
	}

	public void setClaimsList(List<RecordDto> claimsList) {
		this.claimsList = claimsList;
	}
	

}
