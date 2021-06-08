package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.cobranca.model.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.Titulos;
import com.algaworks.cobranca.service.CadastoTituloService;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired //Injetando o objeto
	private Titulos titulos;
	
	@Autowired
	private CadastoTituloService cadastoTituloService;
	
	private static String CADASTRO_VIEW = "CadastroTitulo";

	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		//StatusTitulo.values() retorna um Array com os elementos do Enum StatusTitulo
		// mv.addObject("todosStatusTitulo",StatusTitulo.values()); Refatoramos criando uma lista. 
		// todosStatusTitulo nome do objeto que vai ser interado na viwer
		// StatusTitulo.values() valores.
		mv.addObject(new Titulo()); // Objeto que ta sendo utilizado no view Cadastro titulo, chamado no form. 
		// <form class="form-horizontal" method="POST" action="/titulos" th:object="${titulo}">
		return mv;
	}
	
	/*
	 * @RequestMapping("/principal") public ModelAndView principal() { ModelAndView
	 * mv = new ModelAndView("Principal"); mv.addObject(new Titulo()); return mv; }
	 */
	
	@RequestMapping("/principal")
	public ModelAndView pesquisarPrincipal() {
		List<Titulo> todosTitulos2 = titulos.findAll();
		ModelAndView mv = new ModelAndView("Principal");
		mv.addObject("titulos", todosTitulos2);
		mv.addObject(new Titulo());
		return mv;
	}
	
	//@RequestMapping(value="/titulos", method = RequestMethod.POST)
	@RequestMapping(method = RequestMethod.POST)
	//Novo método que retorna alem da View um atributo de mensagem
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		try {
			cadastoTituloService.salvar(titulo);
			attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
			return "redirect:/titulos/novo" ; // pagina que eu quero retornar
		 }catch (IllegalArgumentException e) {
			// TODO: handle exception
			 errors.rejectValue("dataVencimento", null, e.getMessage());
			 return CADASTRO_VIEW;
		}
	}
	
	@RequestMapping(value="/principal", method = RequestMethod.POST)
	//Novo método que retorna alem da View um atributo de mensagem
	public String salvarPrincipal(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return "Principal";
		}
			titulos.save(titulo);
			attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
			return "redirect:/titulos/principal" ; // pagina que eu quero retornar
		 }
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}
	

	
	// Esse método retornava só a View
	//public String salvar(Titulo titulo) {
	//	System.out.println("--->>> " + titulo.getDescricao());
	//	titulos.save(titulo);
	//	return "CadastroTitulo"; // pagina que eu quero retornar
	// }
	
	
	
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long codigoTitulo) {
		Titulo titulo = titulos.getOne(codigoTitulo);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW); 
		mv.addObject(titulo);
		return mv;
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastoTituloService.excluir(codigo);
		
		attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return "redirect:/titulos";
	}
	
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}

}
