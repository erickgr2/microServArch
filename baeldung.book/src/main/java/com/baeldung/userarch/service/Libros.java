package com.baeldung.userarch.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FunctionalInterface
@FeignClient(value = "library-service", fallback = LibrosFallback.class)
public interface Libros {
	
	@RequestMapping(value = "libroses", method = RequestMethod.GET)
	public ResponseEntity<Object> getBookOther();
}
