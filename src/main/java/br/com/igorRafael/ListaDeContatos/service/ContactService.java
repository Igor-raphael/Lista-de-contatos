package br.com.igorRafael.ListaDeContatos.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.igorRafael.ListaDeContatos.entity.Contact;
import br.com.igorRafael.ListaDeContatos.exception.BadRequestException;
import br.com.igorRafael.ListaDeContatos.exception.NotFoundException;
import br.com.igorRafael.ListaDeContatos.repository.LdcRepository;
import jakarta.transaction.Transactional;

@Service
public class ContactService {
	
	@Autowired
	private LdcRepository ldcRepository;
	
	
	
	
	
	public List<Contact> list(){
		Sort sort = Sort.by("name").ascending();
		
		List<Contact> result = ldcRepository.findAll(sort);
		
		if (result.isEmpty()) {
			throw new NotFoundException("There are no saved contacts yet. ");
		}
		
		return result;
		
	}
	
	
	
	
	
	
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
	
	
	
	 
	
	public List<Contact> getContact(String name){
		
		List<Contact> existingContact = ldcRepository.findByNameIgnoreCaseContaining(name);
		
		if(existingContact.isEmpty()) {
			throw new NotFoundException("This " + name + " does not exist");
		}
		
		return existingContact;
		
	}
	
	
	
	
	@Transactional
 	public List<Contact> updateByContact(String name, Contact contact){
			
		ldcRepository.findByName(name).ifPresentOrElse(existingContact -> {
			
			validateNullOrBlank(contact.getName(), existingContact::setName, "Check if the name field has been entered or if it is empty.");
			
			validateNullOrBlank(contact.getNumber(), existingContact::setNumber, "Check if the number field has been entered or if it is empty.");
			
			existingContact.setEmail(contact.getEmail());
			
			ldcRepository.save(existingContact);
						
		}, () -> {
			
				throw new BadRequestException("This " + name + " does not exist");
			
		});
		
			return list();
		}
    


 	
 	
	@Transactional
    public List<Contact> deleteByName(String name){
		
		ldcRepository.findByName(name).ifPresentOrElse(existingContact -> {
			
			ldcRepository.delete(existingContact);
			
		}, () ->{
			
				throw new BadRequestException("This " + name + " does not exist");
				
		});
		
		return list();
    }
	
	
	
	
	
	private void validateNullOrBlank(String value, Consumer<String> setter, String errorMessage) {
		if(value != null && !value.trim().isBlank()) {
			setter.accept(value);;
		
		}else {
			throw new BadRequestException(errorMessage);
		}
		
	}
	
	
    
}
