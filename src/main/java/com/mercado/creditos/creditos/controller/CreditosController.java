package com.mercado.creditos.creditos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mercado.creditos.creditos.model.Banco;
import com.mercado.creditos.creditos.model.Credito;
import com.mercado.creditos.creditos.repository.Creditos;
import com.mercado.creditos.creditos.service.CadastroCreditoService;

@Controller
@RequestMapping("/creditos")
public class CreditosController {
	
	@Autowired
	private Creditos creditos;
	
	@Autowired
	private CadastroCreditoService cadastroCreditoService;
	
	//@Autowired
	//private FotoStorage fotoStorage;
	
	@RequestMapping
	public ModelAndView pesquisa() {
		ModelAndView mv = new ModelAndView("/credito/ListagemCreditos");
		mv.addObject("creditos", creditos.findAll());
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Credito credito) {
		ModelAndView mv = new ModelAndView("/credito/CadastroCredito");
		mv.addObject("bancos", Banco.values());
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Credito credito, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(credito);
		}
		
		cadastroCreditoService.salvar(credito);
		attributes.addFlashAttribute("mensagem", "Credito salvo com sucesso!");
		return new ModelAndView("redirect:/creditos/novo");
	}
	
	@RequestMapping("/{codigo}")
	public ModelAndView visualizar(@PathVariable("codigo") Credito credito) {
		ModelAndView mv = new ModelAndView("/credito/VisualizacaoCredito");

		mv.addObject("credito", credito);
		return mv;
	}
	
}
