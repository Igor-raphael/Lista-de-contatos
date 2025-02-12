package br.com.igorRafael.ListaDeContatos.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Contact")
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(hidden = true)
	private Long id;
	
	
	private String name;
	
    @Pattern(regexp = "^\\d+$", message = "The mobile number must contain only digits.")
	@Size(min = 10, max = 15, message = "The mobile number must be between 10 and 15 characters long.")
    private String number;
	
	@Email(message = "The e-mail address provided is not valid.")
    private String email;
    
    public Contact() {
    	
    }
    
    public Contact(String name,  String number, String email) {
    	this.name = name;
    	this.number = number;
    	this.email = email;
    	
    }
    
    public Contact( String name, String number) {
    	this.name = name;
    	this.number = number;
    }
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber( String number) {
		this.number = number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
    

}
