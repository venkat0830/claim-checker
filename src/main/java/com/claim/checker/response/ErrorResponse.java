package com.claim.checker.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
private String status;
private String message;
private List<Error> errors;
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public List<Error> getErrors() {
	return errors;
}
public void setErrors(List<Error> errors) {
	this.errors = errors;
}

}
