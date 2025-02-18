package br.com.igorRafael.ListaDeContatos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.igorRafael.ListaDeContatos.entity.Contact;
import br.com.igorRafael.ListaDeContatos.exception.NotFoundException;
import br.com.igorRafael.ListaDeContatos.repository.LdcRepository;

@Service
public class ContactListService {

	
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
	
	
}
