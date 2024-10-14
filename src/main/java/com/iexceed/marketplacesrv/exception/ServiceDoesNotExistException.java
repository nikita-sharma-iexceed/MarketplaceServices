package com.iexceed.marketplacesrv.exception;

public class ServiceDoesNotExistException extends RuntimeException {

	public ServiceDoesNotExistException(String message) {
		super(message);
	}
}
