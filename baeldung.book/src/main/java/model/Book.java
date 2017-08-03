package model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull.List;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

//@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
public class Book {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Long id;
    @NotNull
   // @Pattern(regexp = "[a-zA-Z0-9]")
    private String author;
    
    @NotNull
    private String title;

    public Book() {
    }
    
    @ApiModelProperty(position = 1, required = false, value = "id del libro")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ApiModelProperty(position = 2, required = true, value = "Book author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @ApiModelProperty(position = 3, required = true, value = "Book title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
