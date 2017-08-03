package baeldung.userarch.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import baeldung.userarch.library.model.Book;

@RestController 
public class LibraryController {

	@GetMapping("book")
	public ResponseEntity<?> getBook(){
		Book book = new Book();
		book.setAuthor("Julio Cortazar");
		book.setId(1L);
		book.setTitle("El silencio de los inocentes");
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	
	@RequestMapping(value = "libroses", method = RequestMethod.GET)
	public ResponseEntity<Object> getBookOther(){
		Book book = new Book();
		book.setAuthor("Octavio Paz");
		book.setId(2L);
		book.setTitle("100 años de soledad");
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	
	
}
