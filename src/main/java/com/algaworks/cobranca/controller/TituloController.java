package com.algaworks.cobranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.cobranca.model.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired //Injetando o objeto
	private Titulos titulos;

	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		//StatusTitulo.values() retorna um Array com os elementos do Enum StatusTitulo
		mv.addObject("todosStatusTitulo",StatusTitulo.values());
		return mv;
	}
	
	//@RequestMapping(value="/titulos", method = RequestMethod.POST)
	@RequestMapping(method = RequestMethod.POST)
	//Novo método que retorna alem da View um atributo de mensagem
	public ModelAndView salvar(Titulo titulo) {
		
			titulos.save(titulo);
			
			ModelAndView mv = new ModelAndView("CadastroTitulo");
			mv.addObject("mensagem", "Título salvo com sucesso!");
			return mv; // pagina que eu quero retornar
		 }
	
	// Esse método retornava só a View
	//public String salvar(Titulo titulo) {
	//	System.out.println("--->>> " + titulo.getDescricao());
	//	titulos.save(titulo);
	//	return "CadastroTitulo"; // pagina que eu quero retornar
	// }
	


}
