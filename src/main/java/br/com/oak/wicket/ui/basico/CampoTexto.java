package br.com.oak.wicket.ui.basico;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

import com.google.code.jqwicket.JQBehavior;

public class CampoTexto<T> extends TextField<T> {

	private static final long serialVersionUID = -7238251592576625524L;

	public CampoTexto(final String id, boolean obrigatorio) {
		super(id);
		setRequired(obrigatorio);
	}

	public CampoTexto(final String id, final IModel<T> model,
			boolean obrigatorio) {
		super(id, model, null);
		setRequired(obrigatorio);
	}

	public CampoTexto(final String id, final Class<T> type, boolean obrigatorio) {
		super(id, type);
		setRequired(obrigatorio);
	}

	public CampoTexto(final String id, final IModel<T> model,
			final Class<T> type, boolean obrigatorio) {
		super(id, model, type);
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