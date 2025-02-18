package br.com.igorRafael.ListaDeContatos.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.igorRafael.ListaDeContatos.repository.LdcRepository;

@ExtendWith(MockitoExtension.class)
public class ContactGetNameServiceTest {
	
	@Mock
	LdcRepository ldcRepository;
	
	@Autowired
	@InjectMocks
	private ContactGetNameService contactGetNameService;
	
	
	@Test
	@DisplayName("case of success.")
	void ContactGetNameServiceTestSucess() {
		
		
		
	}
	

}
