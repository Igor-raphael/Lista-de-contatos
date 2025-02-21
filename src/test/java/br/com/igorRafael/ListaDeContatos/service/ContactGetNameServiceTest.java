package br.com.igorRafael.ListaDeContatos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

import br.com.igorRafael.ListaDeContatos.entity.Contact;
import br.com.igorRafael.ListaDeContatos.exception.NotFoundException;
import br.com.igorRafael.ListaDeContatos.repository.LdcRepository;


@ExtendWith(MockitoExtension.class)
public class ContactGetNameServiceTest {
	
	
	@Mock
	LdcRepository ldcRepository;
	
	
	@InjectMocks
	private ContactGetNameService contactGetNameService;
	
	
	@Test
	@DisplayName("Case of sucess.")
	void ContactGetNameServiceTestSucess() {
		
		String name = "mathias";
		
		Contact contact = new Contact("Igor", "00000000001", "");
		Contact contact2 = new Contact("Mathias", "00000000002", "Mathias@gmail.com");
		
		List<Contact> contacts = new ArrayList<>();
		
		contacts.add(contact);
		contacts.add(contact2);
		
		List<Contact> containing = new ArrayList<>(); 
		
		when(ldcRepository.findByNameIgnoreCaseContaining(any())).thenReturn(containing);
		
	   for(Contact a : contacts){
		   if(a.getName().equalsIgnoreCase(name)) {
			    containing.add(a);
		   }
	   }

		List<Contact> result = contactGetNameService.getContact(name); 
		
		assertEquals(1, result.size());
		assertTrue(result.get(0).getName().equalsIgnoreCase(name));
		
		verify(ldcRepository, times(1)).findByNameIgnoreCaseContaining(any());
		
	}
	
	@Test
	@DisplayName("Case of failed.")
	void ContactGetNameServiceTestFailed() {
		
		String name = " ";
		
		Contact contact = new Contact("Igor", "00000000001", "");
		Contact contact2 = new Contact("Mathias", "00000000002", "Mathias@gmail.com");
		
		List<Contact> contacts = new ArrayList<>();
		
		contacts.add(contact);
		contacts.add(contact2);
		
		List<Contact> containing = new ArrayList<>(); 
		
		when(ldcRepository.findByNameIgnoreCaseContaining(any())).thenReturn(containing);
		
	   for(Contact a : contacts){
		   if(a.getName().equalsIgnoreCase(name)) {
			    containing.add(a);
		   }
	   }
	   
	   NotFoundException thrown = Assertions.assertThrows(NotFoundException.class, () -> {
		   
		   List<Contact> result = contactGetNameService.getContact(name); 
		   
		   assertEquals(0, result.size());
		   assertTrue(result.isEmpty());
	  
	   });

		Assertions.assertEquals("This " + name + " does not exist", thrown.getMessage());
		
		verify(ldcRepository, times(1)).findByNameIgnoreCaseContaining(any());
		
	}
	
	

}
