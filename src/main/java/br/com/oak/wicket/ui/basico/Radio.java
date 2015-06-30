package br.com.oak.wicket.ui.basico;

import java.util.List;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.IModel;

public class Radio<T> extends RadioChoice<T> {

	private static final long serialVersionUID = -2215383055892650137L;

	public Radio(final String id, boolean horizontal) {
		super(id);
		criaHorizontalVertical(horizontal);
	}

	public Radio(final String id, final List<? extends T> choices,
			boolean horizontal) {
		super(id, choices);
	}

	public Radio(final String id, final List<? extends T> choices,
			final IChoiceRenderer<? super T> renderer, boolean horizontal) {
		super(id, choices, renderer);
		criaHorizontalVertical(horizontal);
	}

	public Radio(final String id, IModel<T> model,
			final List<? extends T> choices, boolean horizontal) {
		super(id, model, choices);
		criaHorizontalVertical(horizontal);
	}

	public Radio(final String id, IModel<T> model,
			final List<? extends T> choices,
			final IChoiceRenderer<? super T> renderer, boolean horizontal) {
		super(id, model, choices, renderer);
		criaHorizontalVertical(horizontal);
	}

	public Radio(String id, IModel<? extends List<? extends T>> choices,
			boolean horizontal) {
		super(id, choices);
		criaHorizontalVertical(horizontal);
	}

	public Radio(String id, IModel<T> model,
			IModel<? extends List<? extends T>> choices, boolean horizontal) {
		super(id, model, choices);
		criaHorizontalVertical(horizontal);
	}

	public Radio(String id, IModel<? extends List<? extends T>> choices,
			IChoiceRenderer<? super T> renderer, boolean horizontal) {
		super(id, choices, renderer);
		criaHorizontalVertical(horizontal);
	}

	public Radio(String id, IModel<T> model,
			IModel<? extends List<? extends T>> choices,
			IChoiceRenderer<? super T> renderer, boolean horizontal) {
		super(id, model, choices, renderer);
		criaHorizontalVertical(horizontal);
	}

	private void criaHorizontalVertical(boolean horizontal) {
		if (horizontal) {
			setSuffix("");
		}
	}
}