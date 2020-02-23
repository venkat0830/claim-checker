package com.claim.checker.response;

import com.claim.checker.claimSummaryList.CHWYRequest;
import com.claim.checker.claimSummaryList.CHWYResponse;
import com.claim.checker.consumer.clinkGeneratedFile.ClaimResponse;
import com.claim.checker.consumer.clinkGeneratedFile.ClaimsRequest;
import com.claim.checker.dto.RecordDto;

public class HpcErrorResponse {
	
	RecordDto hpcRequest;
	CHWYRequest chwyRequest;
	CHWYResponse chResponse;
	String httpStatusCode;
	String chwyErrorCode;
	String appErrorCode;
	String platform;
	ClaimsRequest clinkRequest;
	ClaimResponse clResponse;
	String cLinkErrorCode;
	public RecordDto getHpcRequest() {
		return hpcRequest;
	}
	public void setHpcRequest(RecordDto hpcRequest) {
		this.hpcRequest = hpcRequest;
	}
	public CHWYRequest getChwyRequest() {
		return chwyRequest;
	}
	public void setChwyRequest(CHWYRequest chwyRequest) {
		this.chwyRequest = chwyRequest;
	}
	public CHWYResponse getChResponse() {
		return chResponse;
	}
	public void setChResponse(CHWYResponse chResponse) {
		this.chResponse = chResponse;
	}
	public String getHttpStatusCode() {
		return httpStatusCode;
	}
	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	public String getChwyErrorCode() {
		return chwyErrorCode;
	}
	public void setChwyErrorCode(String chwyErrorCode) {
		this.chwyErrorCode = chwyErrorCode;
	}
	public String getAppErrorCode() {
		return appErrorCode;
	}
	public void setAppErrorCode(String appErrorCode) {
		this.appErrorCode = appErrorCode;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	public ClaimsRequest getClinkRequest() {
		return clinkRequest;
	}
	public void setClinkRequest(ClaimsRequest clinkRequest) {
		this.clinkRequest = clinkRequest;
	}
	public ClaimResponse getClResponse() {
		return clResponse;
	}
	public void setClResponse(ClaimResponse clResponse) {
		this.clResponse = clResponse;
	}
	public String getcLinkErrorCode() {
		return cLinkErrorCode;
	}
	public void setcLinkErrorCode(String cLinkErrorCode) {
		this.cLinkErrorCode = cLinkErrorCode;
	}
	
	

}
