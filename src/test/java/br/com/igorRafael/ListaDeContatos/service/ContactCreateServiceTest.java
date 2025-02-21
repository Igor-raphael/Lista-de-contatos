package br.com.igorRafael.ListaDeContatos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.igorRafael.ListaDeContatos.entity.Contact;
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
		
		
		
	}
	

}
