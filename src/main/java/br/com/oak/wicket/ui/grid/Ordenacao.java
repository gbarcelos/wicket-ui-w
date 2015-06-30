package br.com.oak.wicket.ui.grid;

import java.io.Serializable;

public class Ordenacao implements Serializable {

	private static final long serialVersionUID = 1808055322262790482L;

	private String campo;

	private String ordenacao;

	public Ordenacao(String campo, String ordenacao) {
		this.campo = campo;
		this.ordenacao = ordenacao;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(String ordenacao) {
		this.ordenacao = ordenacao;
	}
}