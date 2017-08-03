package com.baeldung.userarch.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.userarch.service.BookService;
import com.baeldung.userarch.service.Libros;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import model.Book;
import model.Wrapper;

/**
 * 
 * 
 * @author Erick Garcia
 * @Book Controller
 *
 */
@RestController
@RequestMapping("/books")
@Api(value = "/books", consumes="application/json", description="Book Controller")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private Libros libros;
	
	@ApiOperation(value = "getAllBooks", produces="application/json" )
	@GetMapping
	public List<Book> findAllBooks() {
		return new ArrayList<Book>();
//		return bookService.findAllBooks();
	}

	@ApiOperation(value = "getBooks", produces="application/json")
	@GetMapping("/{bookId}")
	public ResponseEntity<?> findBook(@ApiParam(name = "bookId", value = "id in long format for finding books", required = true) @PathVariable Long bookId){
//		return new Book();
		ResponseEntity<?> book= bookService.findBookById(bookId);
		return new ResponseEntity<>(book,null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "getLibros", produces="application/json")
	@GetMapping("/libro")
	public ResponseEntity<?> findlibros(){
		ResponseEntity<Object> libro = libros.getBookOther();
		return libro;
	}

	@ApiOperation(value = "postCreateBook", produces="application/json")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Book.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal server error")
            })
	@PostMapping("/createBook")
	public Book createBook(@RequestBody @Valid Book book) {
		return book;
	}
	
	@ApiOperation(produces="application/json", value = "deleteBook")
	@DeleteMapping("/{bookId}")
	public void deleteBook(@PathVariable Long bookId) {
//		bookService.deleteBook(bookId);
	}

	@PutMapping("/{bookId}")
	public Book updateBook(@RequestBody Book book, @PathVariable Long bookId) {
//		return bookService.updateBook(book, bookId);
		return book;
	}

	@PatchMapping("/{bookId}")
	public Book updateBook(@RequestBody Map<String, String> updates, @PathVariable Long bookId) {
		return new Book();
//		return bookService.updateBook(updates, bookId);
	}
}
