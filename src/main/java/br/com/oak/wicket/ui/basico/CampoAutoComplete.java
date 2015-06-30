package br.com.oak.wicket.ui.basico;

import java.util.Iterator;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;

public abstract class CampoAutoComplete<T> extends AutoCompleteTextField<T> {

	private static final long serialVersionUID = 4792596383712462065L;

	public CampoAutoComplete(String id) {
		super(id);
	}
	
	@Override
	protected abstract Iterator<T> getChoices(String input);
}