package br.com.igorRafael.ListaDeContatos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igorRafael.ListaDeContatos.entity.Contact;
import br.com.igorRafael.ListaDeContatos.exception.BadRequestException;
import br.com.igorRafael.ListaDeContatos.repository.LdcRepository;

@Service
public class ContactCreateService {

	@Autowired
	LdcRepository ldcRepository;
	
	public Contact create(Contact contact){
		
		 Optional<Contact> existingContact = ldcRepository.findByName(contact.getName());
		 
		 if (existingContact.isEmpty()) {
			 
			 if(contact.getName() != null && !contact.getName().trim().isBlank() 
				&& contact.getNumber() != null && !contact.getNumber().trim().isBlank()) {
				 
				 ldcRepository.save(contact);
			 }
			 	 
		 } else {
			 throw new BadRequestException("Make sure that the name you're typing hasn't already been saved.");
			
		}
		 
		return contact;
	}

}
