package model;

import javax.validation.constraints.NotNull;

public class Wrapper {
	
	private int bookId;
	private String descripcion;
	
	@NotNull
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	@NotNull
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
