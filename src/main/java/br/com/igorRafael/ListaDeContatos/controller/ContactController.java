package br.com.igorRafael.ListaDeContatos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.igorRafael.ListaDeContatos.entity.Contact;
import br.com.igorRafael.ListaDeContatos.service.ContactService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/list")
public class ContactController {

	@Autowired
	ContactService contactService;
	
	@GetMapping("/")
	public List<Contact> obterLista(){
		return contactService.list();
	}
	
	@PostMapping("/newContact")
	public List<Contact> newContact(@RequestBody @Valid Contact contact){
		     
		contactService.create(contact);
		return contactService.list();
		
	}
	
	

}
