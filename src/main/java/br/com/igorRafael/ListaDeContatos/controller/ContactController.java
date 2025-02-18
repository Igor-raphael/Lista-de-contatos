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
import br.com.igorRafael.ListaDeContatos.service.ContactCreateService;
import br.com.igorRafael.ListaDeContatos.service.ContactDeleteService;
import br.com.igorRafael.ListaDeContatos.service.ContactGetNameService;
import br.com.igorRafael.ListaDeContatos.service.ContactListService;
import br.com.igorRafael.ListaDeContatos.service.ContactUpdateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/list")
public class ContactController {

	private ContactListService cls;
	private ContactGetNameService cgns;
	private ContactCreateService ccs;
	private ContactUpdateService cus;
	private ContactDeleteService cds;
	
	@Autowired
	public ContactController(ContactListService cls, ContactGetNameService cgns, ContactCreateService ccs, ContactUpdateService cus, ContactDeleteService cds ) {
		this.cls = cls;
		this.cgns = cgns;
		this.ccs = ccs;
		this.cus = cus;
		this.cds = cds;
		
		
	}
	
	
	
	@GetMapping()
	public List<Contact> getList(){
		return cls.list();
	}

	
	
	@GetMapping("/get/{name}")
	public List<Contact> getContact(@PathVariable("name") String name){
		return cgns.getContact(name);
		
	}
	
	
	
	
	@PostMapping("/newContact")
	public Contact newContact(@RequestBody @Valid Contact contact){     
		return ccs.create(contact);
	}
	
	
	
	
	
	@PutMapping("/update/{name}")
	public List<Contact> updateContactByName(@PathVariable("name") String name, @RequestBody @Valid Contact contact){
		return cus.updateByContact(name, contact);
		
	}
	
	
	
	@DeleteMapping("/delete/{name}")
	public List<Contact> deleteById(@PathVariable("name") String name){
			return cds.deleteByName(name);
	}
	

}
