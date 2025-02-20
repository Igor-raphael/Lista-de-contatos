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
import org.springframework.beans.factory.annotation.Autowired;

import br.com.igorRafael.ListaDeContatos.entity.Contact;
import br.com.igorRafael.ListaDeContatos.repository.LdcRepository;

@ExtendWith(MockitoExtension.class)
public class ContactDeleteServiceTest {

	@Mock
	LdcRepository ldcRepository;
	
	@Mock
	ContactListService cListService;
	
	@Autowired
	@InjectMocks
	ContactDeleteService contactDeleteService;
	
	
	@Test
	@DisplayName("Test for deleteByName - Contact Exists.")
	void ContactDeleteServiceTestSucess() {
		
		
	}
	
}	


