package com.claim.checker.consumer.clinkGeneratedFile;

import java.util.ArrayList;
import java.util.List;

public class ClaimResponse {
private List<Claims> claims;

public List<Claims> getClaims() {
	if(claims == null) {
		claims=new ArrayList<>();
	}
	return claims;
}

public void setClaims(List<Claims> claims) {
	this.claims = claims;
}


}
