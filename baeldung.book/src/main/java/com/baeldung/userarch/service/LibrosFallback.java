package com.baeldung.userarch.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LibrosFallback implements Libros{

	@Override
	public ResponseEntity<Object> getBookOther() {
		// TODO Auto-generated method stubOP
	    Object ob = new Object();
	    Map mensaje = new HashMap<>();
	    mensaje.put("mensaje", "El servicio no esta disponible");
		return new ResponseEntity<Object>(mensaje,HttpStatus.GATEWAY_TIMEOUT);
	}

	
}
