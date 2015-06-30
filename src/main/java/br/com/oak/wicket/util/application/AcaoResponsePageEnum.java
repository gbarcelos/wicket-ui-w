package br.com.oak.wicket.util.application;

public enum AcaoResponsePageEnum {

	INSERIR("Incluir"),

	ALTERAR("Alterar"),

	VISUALIZAR("Visualizar"),

	CONSULTAR("Consultar");

	private String descricao;

	private AcaoResponsePageEnum(final String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}