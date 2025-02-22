package br.com.igorRafael.ListaDeContatos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
public class ContactCreateServiceTest {

	
	@Mock
	LdcRepository ldcRepository;
	
	@InjectMocks
	ContactCreateService contactCreateService;
	
	
	@Test
	@DisplayName("Test for Create - Contact is valid.")
	void ContactCreateServiceTestSucess() {
		
		
		Contact newContact = new Contact("Igor", "00000003000", "");
		
		
		when(ldcRepository.findByName(newContact.getName())).thenReturn(Optional.empty());
		when(ldcRepository.save(newContact)).thenReturn(newContact);
		
		
		Contact result = contactCreateService.create(newContact);
		
		
		assertEquals(newContact.getName(), result.getName());
		assertEquals(newContact.getNumber(), result.getNumber() );
		assertEquals(newContact.getEmail(), result.getEmail());
		
		verify(ldcRepository, times(1)).findByName(newContact.getName());
		verify(ldcRepository, times(1)).save(newContact);
		
	}
	
	
	
	@Test
	@DisplayName("Test for Create - Contact don't valid - Exception 1 = Contact already exists.")
	void ContactCreateServiceTestExceptionFailed() {
		
		
		Contact newContact = new Contact("Igor", "12345678901", "");
		
		when(ldcRepository.findByName(newContact.getName())).thenReturn(Optional.of(new Contact("Igor", "1234567890112", null)));
		
		
		BadRequestException thrown = Assertions.assertThrows(BadRequestException.class, () -> {
		
					contactCreateService.create(newContact);
					  
		});
		
		
		Assertions.assertEquals("Make sure that the " + newContact.getName() + " you're typing hasn't already been saved.", thrown.getMessage());
		
	}
	
	
	
	@Test
	@DisplayName("Test for Create - Contact don't valid - exception 2 = Invalid phone number format.")
	void ContactCreateServiceTestExceptionFailed2() {
		
		
		Contact newContact = new Contact("Igor", "12345678901as", "");
		
		
		BadRequestException thrown = Assertions.assertThrows(BadRequestException.class, () -> {
		
					contactCreateService.create(newContact);
					  
		});
		
		
		Assertions.assertEquals("Invalid phone number format. \n"
				+ "The mobile number must contain only digits.", thrown.getMessage());
		
	}
	
	
	@Test
	@DisplayName("Test for Create - Contact don't valid - Exception 3 = Field in blank or null.")
	void ContactCreateServiceTestExceptionFailed3() {
		
		
		Contact newContact = new Contact("", "12345678901", "");
		
		
		BadRequestException thrown = Assertions.assertThrows(BadRequestException.class, () -> {
		
					contactCreateService.create(newContact);
					  
		});
		
		
		Assertions.assertEquals("Name and phone number cannot be null or blank.", thrown.getMessage());
		
	}
	
	

}
