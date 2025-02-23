package br.com.igorRafael.ListaDeContatos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class ContactUpdateServiceTest {

	
	@Mock
	LdcRepository ldcRepository;
	
	@Mock
	ContactListService cListService;
	
	@InjectMocks
	ContactUpdateService contactUpdateService;
	
	@Test
	@DisplayName("Test for Update - Contact has been updated successfully.")
	void ContactUpdateServiceSucess() {
		
		String name = "Igor";
		
		Contact oldContact = new Contact("Igor", "012345678901", "igor@gmail.com");
		
		Contact newContact = new Contact("Igor Rafael Silva Coelho", "012345678901", "");
		
		
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(oldContact);
		contacts.add(new Contact("Maria", "012345678901", ""));
		contacts.add(new Contact("João", "012345678901", ""));
		contacts.add(new Contact("Nilson", "012345678901", ""));
		contacts.add(new Contact("Kulen", "012345678901", ""));
		
		when(ldcRepository.findByName(name)).thenReturn(Optional.of(oldContact));
		when(ldcRepository.save(oldContact)).thenReturn(newContact);
		when(cListService.list()).thenReturn(contacts);
		
		
		   List<Contact> result = contactUpdateService.updateByContact(name, newContact);
		
		   assertEquals("Igor Rafael Silva Coelho", result.get(0).getName());
		   assertEquals("012345678901", result.get(0).getNumber());
		   assertEquals("", result.get(0).getEmail());
		   assertEquals("Maria", result.get(1).getName());
		   
		   assertEquals(5, result.size());
		   
		   
		   verify(ldcRepository, times(1)).findByName(name);
		   verify(ldcRepository, times(1)).save(oldContact);
		   verify(cListService, times(1)).list();
		
	}
	
	
	@Test
	@DisplayName("Test for Update - Contact was not updated successfully - Exception invalid name")
	void ContactUpdateServiceFailedException() {
		
        String name = "Igor";
		
		Contact oldContact = new Contact("Igor", "012345678901", "igor@gmail.com");
		
		Contact newContact = new Contact(" ", "012345678901", "");
		
		when(ldcRepository.findByName(name)).thenReturn(Optional.of(oldContact));
		
		BadRequestException thrown = Assertions.assertThrows(BadRequestException.class, () -> {
			
			contactUpdateService.updateByContact(name, newContact);
			
		});
		
		Assertions.assertEquals("Check if the name field has been entered or if it is empty.", thrown.getMessage());
		
		verify(ldcRepository, times(1)).findByName(name);
		
	}
	
	
	
	@Test
	@DisplayName("Test for Update - Contact was not updated successfully - Exception invalid number")
	void ContactUpdateServiceFailedException2() {
		
        String name = "Igor";
		
		Contact oldContact = new Contact("Igor", "012345678901", "igor@gmail.com");
		
		Contact newContact = new Contact("Igor Rafael", "", "");
		
		
		when(ldcRepository.findByName(name)).thenReturn(Optional.of(oldContact));
		
		
		BadRequestException thrown = Assertions.assertThrows(BadRequestException.class, () -> {
			
			contactUpdateService.updateByContact(name, newContact);
			
		});
		
		Assertions.assertEquals("Check if the number field has been entered or if it is empty.", thrown.getMessage());
		
		verify(ldcRepository, times(1)).findByName(name);
	}
	
	
	
	@Test
	@DisplayName("Test for Update - Contact was not updated successfully - Name does not exist.")
	void ContactUpdateServiceFailedException3() {
		
        String name = "Igor";
		
		Contact newContact = new Contact("Igor", "012345678901", "igor@gmail.com");
		
		when(ldcRepository.findByName(name)).thenReturn(Optional.empty());
		
		
		BadRequestException thrown = Assertions.assertThrows(BadRequestException.class, () -> {
			
			contactUpdateService.updateByContact(name, newContact);
			
		});
		
		Assertions.assertEquals("This " + name + " does not exist", thrown.getMessage());
		
		verify(ldcRepository, times(1)).findByName(name);
		
	}

}
