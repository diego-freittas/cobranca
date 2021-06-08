package com.algaworks.cobranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.Titulos;

@Service
public class CadastoTituloService {

	@Autowired
	private Titulos titulos;

	public void salvar(Titulo titulo) {
		
		try {
		titulos.save(titulo);
		
	}catch (DataIntegrityViolationException e) {
		// TODO: handle exception
		throw new IllegalArgumentException("Formato de data invalido.");
	}
	}

	public void excluir(Long codigo) {
		// TODO Auto-generated method stub
		titulos.deleteById(codigo);
		
	}
	
	
	
	
}
