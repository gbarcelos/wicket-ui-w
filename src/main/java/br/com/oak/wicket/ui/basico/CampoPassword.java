package br.com.oak.wicket.ui.basico;

import org.apache.wicket.markup.html.form.PasswordTextField;

import com.google.code.jqwicket.JQBehavior;

public class CampoPassword extends PasswordTextField {

	private static final long serialVersionUID = 2618045014564576792L;

	public CampoPassword(String id) {
		super(id);
		setRequired(false);
	}

	public CampoPassword(final String id, final boolean obrigatorio) {
		super(id);
		setRequired(obrigatorio);
	}

	public void adicionaLimit(int maxlength) {
		if (maxlength != 0) {
			String idContador = getId() + "-contador";
			JQBehavior jq = new JQBehavior("$('#" + getMarkupId()
					+ "').maxlength({max:" + maxlength
					+ ", feedbackText: '{r} caracteres restantes', feedbackTarget: '#"
					+ idContador + "'})");
			add(jq);
		}
	}
}