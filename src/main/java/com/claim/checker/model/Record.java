package com.claim.checker.model;

public class Record {
	
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
