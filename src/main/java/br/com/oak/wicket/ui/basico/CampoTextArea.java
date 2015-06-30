package br.com.oak.wicket.ui.basico;

import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.google.code.jqwicket.JQBehavior;

public class CampoTextArea<T> extends TextArea<T> {

	private static final long serialVersionUID = -5348426648408591389L;

	public CampoTextArea(final String id, final boolean obrigatorio) {

		super(id);

		setRequired(obrigatorio);
	}

	@SuppressWarnings("unchecked")
	public CampoTextArea(String id, int maxLength) {
		super(id, (IModel<T>) Model.of(" "));
		adicionaLimit(maxLength);
	}

	public CampoTextArea(final String id, IModel<T> model, int maxLength) {
		super(id, model);
		adicionaLimit(maxLength);
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