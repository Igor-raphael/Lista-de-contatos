package br.com.igorRafael.ListaDeContatos.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.igorRafael.ListaDeContatos.entity.Contact;
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
		
		Contact oldContact = new Contact("Igor", "012345678901", "igor@gmail.com");
		
		
		
	}
	

}
