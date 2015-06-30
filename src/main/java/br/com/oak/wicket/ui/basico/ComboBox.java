package br.com.oak.wicket.ui.basico;

import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.AppendingStringBuffer;
import org.apache.wicket.util.string.Strings;

public class ComboBox<T> extends DropDownChoice<T> {

	private static final long serialVersionUID = -1736472281907472594L;

	private static final String SELECIONE = "Selecione";

	private static final String TODOS = "Todos";

	private boolean selectedTodos = false;

	public ComboBox(final String id) {
		super(id);
	}

	public ComboBox(final String id, final List<? extends T> choices) {
		super(id, choices);
	}

	public ComboBox(final String id, final List<? extends T> choices,
			final IChoiceRenderer<? super T> renderer) {
		super(id, choices, renderer);
	}

	public ComboBox(final String id, IModel<T> model,
			final List<? extends T> choices) {
		super(id, model, choices);
	}

	public ComboBox(final String id, IModel<T> model,
			final List<? extends T> choices,
			final IChoiceRenderer<? super T> renderer) {
		super(id, model, choices, renderer);
	}

	public ComboBox(String id, IModel<? extends List<? extends T>> choices) {
		super(id, choices);
	}

	public ComboBox(String id, IModel<T> model,
			IModel<? extends List<? extends T>> choices) {
		super(id, model, choices);
	}

	public ComboBox(String id, IModel<? extends List<? extends T>> choices,
			IChoiceRenderer<? super T> renderer) {
		super(id, choices, renderer);
	}

	public ComboBox(String id, IModel<T> model,
			IModel<? extends List<? extends T>> choices,
			IChoiceRenderer<? super T> renderer) {
		super(id, model, choices, renderer);
	}

	public ComboBox(final String id,
			final IModel<? extends List<? extends T>> choices,
			final IChoiceRenderer<? super T> renderer, boolean selectedTodos) {
		super(id, choices, renderer);
		this.selectedTodos = selectedTodos;
	}

	public ComboBox(final String id, final List<? extends T> choices,
			final IChoiceRenderer<? super T> renderer, boolean selectedTodos) {
		super(id, choices, renderer);
		this.selectedTodos = selectedTodos;
	}

	@Override
	protected CharSequence getDefaultChoice(String selectedValue) {
		if (isNullValid()) {
			return constroiBufferIsNullValid(selectedValue);
		} else {
			if ("".equals(selectedValue)) {
				String option = getLocalizer().getStringIgnoreSettings(
						getNullKey(), this, null, null);
				if (Strings.isEmpty(option)) {
					if (this.selectedTodos) {
						option = getLocalizer().getString(
								"selectValueComboTodos", this, TODOS);
					} else {
						option = getLocalizer().getString("null", this,
								SELECIONE);
					}
				}
				return "\n<option selected=\"selected\" value=\"\">" + option
						+ "</option>";
			}
		}
		String option = getLocalizer().getStringIgnoreSettings(getNullKey(),
				this, null, null);
		if (Strings.isEmpty(option)) {
			option = getLocalizer().getString("null", this, SELECIONE);
		}
		return "\n<option value=\"\">" + option + "</option>";
	}

	private CharSequence constroiBufferIsNullValid(String selectedValue) {
		String option = getLocalizer().getStringIgnoreSettings(
				getNullValidKey(), this, null, null);
		if (Strings.isEmpty(option)) {
			option = getLocalizer().getString("nullValid", this, "");
		}

		final AppendingStringBuffer buffer = new AppendingStringBuffer(
				64 + option.length());

		buffer.append("\n<option");

		if ("".equals(selectedValue)) {
			buffer.append(" selected=\"selected\"");
		}

		buffer.append(" value=\"\">").append(option).append("</option>");
		return buffer;
	}
}