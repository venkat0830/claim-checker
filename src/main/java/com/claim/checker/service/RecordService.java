package com.claim.checker.service;

import com.claim.checker.model.ProcessHpcClaimsListReq;

public interface RecordService {
	
	public long processHpcClaims() throws Exception;
	public long processHpcClaimsV2(ProcessHpcClaimsListReq claimsList) throws Exception;

}
