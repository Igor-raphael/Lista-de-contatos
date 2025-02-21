package br.com.igorRafael.ListaDeContatos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.igorRafael.ListaDeContatos.entity.Contact;
import br.com.igorRafael.ListaDeContatos.exception.BadRequestException;
import br.com.igorRafael.ListaDeContatos.repository.LdcRepository;

@ExtendWith(MockitoExtension.class)
public class ContactDeleteServiceTest {

	@Mock
	LdcRepository ldcRepository;
	
	@Mock
	ContactListService cListService;
	
	
	@InjectMocks
	ContactDeleteService contactDeleteService;
	
	
	@Test
	@DisplayName("Test for deleteByName - Contact Exists.")
	void ContactDeleteServiceTestSucess() {
		
		String name = "Baiano";
		
		Contact contact = new Contact("Baiano", "00000000001", "");
		Contact contact2 = new Contact("Mathias", "00000000002", "mathias@gmail.com");
		Contact contact3 = new Contact("Nascimento", "00000000003", "nascimento@gmail.com");
		
		List<Contact> contacts = new ArrayList<Contact>();
		
		contacts.add(contact);
		contacts.add(contact2);
		contacts.add(contact3);
	
		
		when(ldcRepository.findByName(name)).thenReturn(Optional.of(contact));
		when(cListService.list()).thenReturn(contacts);
		
		doAnswer(invocation -> {
			Contact contact1 = invocation.getArgument(0);
			
			contacts.removeIf( c -> c.getName().equals(name));
				
			when(cListService.list()).thenReturn(contacts);
	
			return null;
			
			
		}).when(ldcRepository).delete(any(Contact.class));
		
		 List<Contact> result = contactDeleteService.deleteByName(name);
			
		 
		    assertEquals(2, result.size());
		    assertEquals("Mathias", result.get(0).getName());
		    assertEquals("Nascimento", result.get(1).getName());
		    verify(ldcRepository, times(1)).findByName(name);
		    verify(cListService, times(1)).list();
			
		}
	
	
	@Test
	@DisplayName("Test for deleteByName - Contact dons't Exists.")
	void ContactDeleteServiceTestFailed() {
		
		String name = "Fábio";
		
		Contact contact = new Contact("Baiano", "00000000001", "");
		Contact contact2 = new Contact("Mathias", "00000000002", "mathias@gmail.com");
		Contact contact3 = new Contact("Nascimento", "00000000003", "nascimento@gmail.com");
		
		List<Contact> contacts = new ArrayList<Contact>();
		
		contacts.add(contact);
		contacts.add(contact2);
		contacts.add(contact3);
	
		
		when(ldcRepository.findByName(name)).thenReturn(Optional.empty());
		
		BadRequestException thrown = Assertions.assertThrows(BadRequestException.class, () ->{
			
			List<Contact> result = contactDeleteService.deleteByName(name);
			
			    assertEquals(3, result.size());
			    assertEquals("Baiano", result.get(0).getName());
			    assertEquals("Mathias", result.get(1).getName());
			    assertEquals("Nascimento", result.get(2).getName());
		});
		 
			Assertions.assertEquals("This " + name + " does not exist", thrown.getMessage());
		 
		   
		    verify(ldcRepository, times(1)).findByName(name);
			
		}
		
		
		
	}


