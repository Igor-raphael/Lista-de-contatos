package br.com.igorRafael.ListaDeContatos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igorRafael.ListaDeContatos.entity.Contact;
import br.com.igorRafael.ListaDeContatos.exception.NotFoundException;
import br.com.igorRafael.ListaDeContatos.repository.LdcRepository;

@Service
public class ContactGetNameService {

	@Autowired
	LdcRepository ldcRepository;
	
	public List<Contact> getContact(String name){
		
		List<Contact> existingContact = ldcRepository.findByNameIgnoreCaseContaining(name);
		
		if(existingContact.isEmpty()) {
			throw new NotFoundException("This " + name + " does not exist");
		}
		
		return existingContact;
		
	}
}
