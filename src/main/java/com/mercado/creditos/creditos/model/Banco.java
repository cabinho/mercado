package com.mercado.creditos.creditos.model;

public enum Banco {

	BERGS("Banrisul"), BB("BancoBrasil"), SICREDI("Sicredi"), TRIBANCO("TriBanco");

	private String descricao;

	Banco(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
