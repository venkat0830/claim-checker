package com.claim.checker.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.claim.checker.response.BoTableResponse;
import com.claim.checker.response.Error;
import com.claim.checker.response.ErrorResponse;

public class ErrorTranslationUtility {
	
	private static final String APP_ERROR_CODE = "app-error-code";
	
	public BoTableResponse generateBotableErrorresponse(String faultCode, String message, String exceptionMessage) {
		Error error =  new Error();
		ErrorResponse errorResponse =  new ErrorResponse();
		MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();
		BoTableResponse boTableResponse = new BoTableResponse();
		if (faultCode.equals("TITACHE")) {
			error.setCode(faultCode);
			error.setMessage(exceptionMessage);
		}
		errorResponse.getErrors().add(error);
		errorResponse.setMessage("ERROR");
		errorResponse.setMessage(message);
		headerMap.add(APP_ERROR_CODE, faultCode);
		boTableResponse.setErrorResponse(errorResponse);
		boTableResponse.setHeaderMap(headerMap);
		return boTableResponse;
	}

}
