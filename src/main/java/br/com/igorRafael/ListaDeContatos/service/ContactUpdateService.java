package br.com.igorRafael.ListaDeContatos.service;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igorRafael.ListaDeContatos.entity.Contact;
import br.com.igorRafael.ListaDeContatos.exception.BadRequestException;
import br.com.igorRafael.ListaDeContatos.repository.LdcRepository;
import jakarta.transaction.Transactional;

@Service
public class ContactUpdateService {

	@Autowired
	private LdcRepository ldcRepository;
	
	@Autowired
	private ContactListService cListService;
	
	
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
		
			return cListService.list();
		}
	
	
	
	
	protected void validateNullOrBlank(String value, Consumer<String> setter, String errorMessage) {
		if(value != null && !value.trim().isBlank()) {
			setter.accept(value);;
		
		}else {
			throw new BadRequestException(errorMessage);
		}
		
	}
	
	

}
