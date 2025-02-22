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
		 
		 if (existingContact.isPresent()) {
			 throw new BadRequestException("Make sure that the " + contact.getName() + " you're typing hasn't already been saved.");
		}
		 
		String regex = "^\\d+$";
		if(contact.getNumber() == null || !contact.getNumber().matches(regex)) {
			throw new BadRequestException("Invalid phone number format. \n"
					+ "The mobile number must contain only digits.");
		}
		 
		 
			 
		 if(contact.getName() == null || contact.getName().trim().isBlank() 
				|| contact.getNumber() == null || contact.getNumber().trim().isBlank()) {
					 
		    throw new BadRequestException("Name and phone number cannot be null or blank.");
	    }
				 	 
		 ldcRepository.save(contact);
		 
		return contact;
	}

}
