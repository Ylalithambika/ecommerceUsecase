package com.example.ecommerce.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationsErrorResponse extends ErrorResponse {

	private Map<String, String> errorsMap = new HashMap<>();

	public Map<String, String> getErrorsMap() {
		return errorsMap;
	}

	public void setErrorsMap(Map<String, String> errorsMap) {
		this.errorsMap = errorsMap;
	}

}
