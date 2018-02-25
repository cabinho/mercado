package com.mercado.creditos.creditos.service;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mercado.creditos.creditos.model.Credito;
import com.mercado.creditos.creditos.repository.Creditos;



@Service
public class CadastroCreditoService {

	@Autowired
	private Creditos creditos;

		public void salvar(Credito credito) {

		this.creditos.save(credito);
	}
	
		
	}
