package br.com.igorRafael.ListaDeContatos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.igorRafael.ListaDeContatos.entity.Contact;
import br.com.igorRafael.ListaDeContatos.exception.BadRequestException;
import br.com.igorRafael.ListaDeContatos.exception.NotExist;
import br.com.igorRafael.ListaDeContatos.repository.LdcRepository;

@Service
public class ContactService {
	
	@Autowired
	private LdcRepository ldcRepository;
	
	
	
	public List<Contact> list(){
		Sort sort = Sort.by("name").ascending();
		return ldcRepository.findAll(sort);
		
	}
	
	
	
	public Contact create(Contact contact){
		
		 List<Contact> existingContact = ldcRepository.findByName(contact.getName());
		 
		 if (existingContact.isEmpty()) {
			 ldcRepository.save(contact);
		
		 } else {
			 throw new BadRequestException(" This name already exists ");
			
		}
		 
		return contact;
	}
	
	
	
	public List<Contact> updateById(Long id, Contact contact){
		
		ldcRepository.findById(id).ifPresentOrElse(existingContact -> {
			contact.setId(id);
			ldcRepository.save(contact);
		
		}, () -> {
			
			throw new BadRequestException(" Contact %d does not exist ! ".formatted(id));
		
		});
		
		return list();
	}

	
	
	public List<Contact> updateByContact(String name, Contact contact){
			
		 List<Contact> existingContact = ldcRepository.findByName(name);
		
		if (existingContact.isEmpty()) {
			throw new NotExist("This name does not exist");
		
		} else {
			contact.setName(name);
			ldcRepository.save(contact);
		  }
		
			return list();
		}
}
