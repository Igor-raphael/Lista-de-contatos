package br.com.igorRafael.ListaDeContatos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.igorRafael.ListaDeContatos.entity.Contact;
import br.com.igorRafael.ListaDeContatos.exception.BadRequestException;
import br.com.igorRafael.ListaDeContatos.exception.NotExist;
import br.com.igorRafael.ListaDeContatos.repository.LdcRepository;
import jakarta.transaction.Transactional;

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
	
	
	//FAZER O UPDATE VIA NOME
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
    


	//FAZER O DELETE VIA NOME
	@Transactional
    public List<Contact> deleteById(Long id){
    	
		ldcRepository.findById(id).ifPresentOrElse(existingContact -> {
			
			ldcRepository.delete(existingContact);
			
			List<Contact> contacts = ldcRepository.findAllByOrderByIdAsc();
			
			boolean updateNext = false;
			
			for(Contact CT: contacts){
				
				if (updateNext) {
					CT.setId(CT.getId() -1 );
					ldcRepository.save(CT);
				}
				
				if (CT.getId().equals(id) ) {
					updateNext = true;
					
				}
				
			}
			
		}, () -> {
			
			throw new BadRequestException(" Contact %d does not exist ! ".formatted(id));
		
		});
		
		return list();
    	
    }
    
}
