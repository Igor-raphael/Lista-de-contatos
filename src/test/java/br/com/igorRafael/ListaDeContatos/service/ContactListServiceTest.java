package br.com.igorRafael.ListaDeContatos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import br.com.igorRafael.ListaDeContatos.entity.Contact;
import br.com.igorRafael.ListaDeContatos.exception.NotFoundException;
import br.com.igorRafael.ListaDeContatos.repository.LdcRepository;

@ExtendWith(MockitoExtension.class)
public class ContactListServiceTest {
	
	
	
	@Mock
	private LdcRepository ldcRepository;
	
	@Autowired
	@InjectMocks
	private ContactListService contactListService;
	
	
	
	@Test
	@DisplayName("case of success.")
	void ContactListServiceSucess() {
		
		Contact contact = new Contact("Abel", "00000000001", "Abel@gmail.com");
		Contact contact2 = new Contact("Kayn", "00000000002", "Kayn@gmail.com");
			
		List<Contact> contacts = new ArrayList<>();
		
		contacts.add(contact);
		contacts.add(contact2);
		
		when(ldcRepository.findAll(Sort.by("name").ascending())).thenReturn(contacts);
		
		List<Contact> result = contactListService.list();
		
		assertNotNull(result);
		assertEquals(2, result.size()); 
		assertEquals(contact.getName(), result.get(0).getName());
		assertEquals(contact2.getName(), result.get(1).getName());
		
		verify(ldcRepository, times(1)).findAll(Sort.by("name").ascending());
			
	}
	
	
	
	@Test
	@DisplayName("Testing the regex.")
	void ContactListServiceSucess2() {
		
        String regex = "^\\d+$";
        
		Contact contact1 = new Contact("Abel", "00-000000001", "Abel@gmail.com");
		
		Contact contact2 = new Contact("Kayn", "00000000001", "Kayn@gmail.com");
		
		List<Contact> contacts = new ArrayList<>();
		
		if(contact1.getNumber().matches(regex)) {
			contacts.add(contact1);
		}
		
		if(contact2.getNumber().matches(regex)) {
			contacts.add(contact2);
		}
		
		when(ldcRepository.findAll(Sort.by("name").ascending())).thenReturn(contacts);
		
		List<Contact> result = contactListService.list();
		
		assertTrue(!contacts.isEmpty());
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(contact2.getName(), result.get(0).getName());
		
		verify(ldcRepository, times(1)).findAll(Sort.by("name").ascending());
			
	}
	
	
	
	
	@Test
	@DisplayName("case of failed.")
	void ContactListServiceFailed() {
		
		List<Contact> contacts = new ArrayList<>();

		when(ldcRepository.findAll(Sort.by("name").ascending())).thenReturn(contacts);
			
		NotFoundException Thrown = Assertions.assertThrows(NotFoundException.class, () -> {
		      
			contactListService.list();
			
		});
	
		Assertions.assertEquals("There are no saved contacts yet. ", Thrown.getMessage());
		
		verify(ldcRepository, times(1)).findAll(Sort.by("name").ascending());
		
	}


	
}
