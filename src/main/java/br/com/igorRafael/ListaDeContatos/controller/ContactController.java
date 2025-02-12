package br.com.igorRafael.ListaDeContatos.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.igorRafael.ListaDeContatos.entity.Contact;
import br.com.igorRafael.ListaDeContatos.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/list")
public class ContactController {

	@Autowired
	ContactService contactService;
	
	
	@GetMapping()
	public List<Contact> getList(){
		return contactService.list();
	}

	
	
	@GetMapping("/get/{name}")
	public List<Contact> getContact(@PathVariable("name") String name){
		return contactService.getContact(name);
		
	}
	
	
	
	
	@PostMapping("/newContact")
	public Contact newContact(@RequestBody @Valid Contact contact){     
		return contactService.create(contact);
	}
	
	
	
	
	
	@PutMapping("/update/{name}")
	public List<Contact> updateContactByName(@PathVariable("name") String name, @RequestBody @Valid Contact contact){
		return contactService.updateByContact(name, contact);
		
	}
	
	
	
	@DeleteMapping("/delete/{name}")
	public List<Contact> deleteById(@PathVariable("name") String name){
			return contactService.deleteByName(name);
	}
	

}
