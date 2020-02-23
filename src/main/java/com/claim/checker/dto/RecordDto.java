package com.claim.checker.dto;

import com.claim.checker.model.ClaimDetails;
import com.claim.checker.model.MemberDetails;
import com.claim.checker.model.ProviderDetails;
import com.claim.checker.model.RecordInfo;

public class RecordDto {
	private RecordInfo recordInfo;
	private ProviderDetails providerDetails;
	private MemberDetails memberDetails;
	private ClaimDetails claimDetails;
	public RecordInfo getRecordInfo() {
		return recordInfo;
	}
	public void setRecordInfo(RecordInfo recordInfo) {
		this.recordInfo = recordInfo;
	}
	public ProviderDetails getProviderDetails() {
		return providerDetails;
	}
	public void setProviderDetails(ProviderDetails providerDetails) {
		this.providerDetails = providerDetails;
	}
	public MemberDetails getMemberDetails() {
		return memberDetails;
	}
	public void setMemberDetails(MemberDetails memberDetails) {
		this.memberDetails = memberDetails;
	}
	public ClaimDetails getClaimDetails() {
		return claimDetails;
	}
	public void setClaimDetails(ClaimDetails claimDetails) {
		this.claimDetails = claimDetails;
	}
	
	
}
