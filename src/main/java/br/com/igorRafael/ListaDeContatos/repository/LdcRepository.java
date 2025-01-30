package br.com.igorRafael.ListaDeContatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.igorRafael.ListaDeContatos.entity.Contact;

public interface LdcRepository extends JpaRepository<Contact, Long>{

	List<Contact> findByName(String name);
	
	List<Contact> findAllByOrderByIdAsc();
}
