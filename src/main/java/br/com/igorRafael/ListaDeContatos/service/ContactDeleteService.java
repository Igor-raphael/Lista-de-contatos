package br.com.igorRafael.ListaDeContatos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igorRafael.ListaDeContatos.entity.Contact;
import br.com.igorRafael.ListaDeContatos.exception.BadRequestException;
import br.com.igorRafael.ListaDeContatos.repository.LdcRepository;
import jakarta.transaction.Transactional;

@Service
public class ContactDeleteService {
	
	@Autowired
	private LdcRepository ldcRepository;
	
	@Autowired
	private ContactListService cListService;
 	
 	
	@Transactional
    public List<Contact> deleteByName(String name){
		
		ldcRepository.findByName(name).ifPresentOrElse(existingContact -> {
			
			ldcRepository.delete(existingContact);
			
		}, () ->{
			
				throw new BadRequestException("This " + name + " does not exist");
				
		});
		
		return cListService.list();
    }
	
	
	
	
    
}
