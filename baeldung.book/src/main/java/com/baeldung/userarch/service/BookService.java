package com.baeldung.userarch.service;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.baeldung.userarch.repository.BookRepository;
import com.google.common.base.Preconditions;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixRuntimeException;

import exception.BookNotFoundException;
import model.Book;

@Service
//@Transactional(readOnly = true)
public class BookService {
	
	  private final RestTemplate restTemplate;

	  public BookService(RestTemplate rest) {
	    this.restTemplate = rest;
	  }

//    @Autowired
//    private BookRepository bookRepository;
//
//    public List<Book> findAllBooks() {
//        return (List<Book>) bookRepository.findAll();
//    }
//
//    public Book findBookById(Long bookId) {
//        return Optional.ofNullable(bookRepository.findOne(bookId))
//        		.orElseThrow(() -> new BookNotFoundException("Book not found. ID: " + bookId));
//    }
	@HystrixCommand(fallbackMethod = "reliable" )
    public ResponseEntity<?> findBookById(Long bookId) {
		System.out.println("how are you..");
    	URI uri = URI.create("http://localhost:8084/book");
    	Book book= this.restTemplate.getForObject(uri, Book.class);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }
//
	public ResponseEntity<?> reliable(Long bookId, Throwable t){	
//			throw new RuntimeException("Hola");
			//throw new RuntimeException(t.getCause());
		String message = "Service could not be reached. Original exception: " + t.toString();
		System.out.println("Am Fine.. ");
		   //throw new HttpClientErrorException(HttpStatus.FAILED_DEPENDENCY, "Server Down.. ");
		//throw new HystrixRuntimeException(HystrixRuntimeException.FailureType.REJECTED_SEMAPHORE_EXECUTION, null, "how are you", t.getCause(), t.fillInStackTrace());
	//	throw new RuntimeException(message);
		
		return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);	
		//return book;
	}
//    @Transactional(propagation = Propagation.REQUIRED)
//    public Book createBook(Book book) {
//        final Book newBook = new Book();
//        newBook.setTitle(book.getTitle());
//        newBook.setAuthor(book.getAuthor());
//        return bookRepository.save(newBook);
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void deleteBook(Long bookId) {
//        bookRepository.delete(bookId);
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public Book updateBook(Map<String, String> updates, Long bookId) {
//        final Book book = findBookById(bookId);
//        updates.keySet()
//            .forEach(key -> {
//                switch (key) {
//                case "author":
//                    book.setAuthor(updates.get(key));
//                    break;
//                case "title":
//                    book.setTitle(updates.get(key));
//                }
//            });
//        return bookRepository.save(book);
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public Book updateBook(Book book, Long bookId) {
//        Preconditions.checkNotNull(book);
//        Preconditions.checkState(book.getId() == bookId);
//        Preconditions.checkNotNull(bookRepository.findOne(bookId));
//        return bookRepository.save(book);
//    }
}