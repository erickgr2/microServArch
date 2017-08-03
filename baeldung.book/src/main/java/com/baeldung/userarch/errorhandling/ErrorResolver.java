package com.baeldung.userarch.errorhandling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorResolver {

	private final Logger logger = LoggerFactory.getLogger(ErrorResolver.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorDescriptor resolveMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
		ErrorDescriptor errorDescriptor = new ErrorDescriptor();
		
		errorDescriptor.setTimestamp(System.currentTimeMillis());
		errorDescriptor.setStatus(HttpStatus.BAD_REQUEST.value());
		errorDescriptor.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorDescriptor.setPath(req.getRequestURI());
		
		Map<String, List<String>> groupedErrors = new HashMap<>();
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			String message = fieldError.getDefaultMessage();
			String field = fieldError.getField();
			
			List<String> fieldsByMessage = groupedErrors.get(message);
			if (fieldsByMessage == null) {
				fieldsByMessage = new ArrayList<>();
				groupedErrors.put(message, fieldsByMessage);
			}
			fieldsByMessage.add(field);
		}
		
		if (!groupedErrors.isEmpty()) {
			errorDescriptor.setMessage(groupedErrors.toString());
		}
		
		return errorDescriptor;
	}

	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorDescriptor resolveNotReadableException(HttpServletRequest req, HttpMessageNotReadableException e) {
		logger.error(e.getMessage(), e);

		String message = e.getMessage();    //Parseo
		message = message.substring(0, message.indexOf(':'));
		
		ErrorDescriptor errorDescriptor = new ErrorDescriptor();
		errorDescriptor.setTimestamp(System.currentTimeMillis());
		errorDescriptor.setStatus(HttpStatus.BAD_REQUEST.value());
		errorDescriptor.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorDescriptor.setMessage(message);
		errorDescriptor.setPath(req.getRequestURI());

		return errorDescriptor;
	}
	

}
