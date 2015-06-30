package br.com.oak.wicket.ui.validadores;

import java.util.Date;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;

public class PeriodoValidator extends AbstractFormValidator {

	private static final long serialVersionUID = -7667840760779893360L;

	private final FormComponent<?> campoDataInicio;

	private final FormComponent<?> campoDataFim;

	public PeriodoValidator(final FormComponent<?> campoDataInicio,
			final FormComponent<?> campoDataFim) {

		this.campoDataInicio = campoDataInicio;

		this.campoDataFim = campoDataFim;
	}

	@Override
	public FormComponent<?>[] getDependentFormComponents() {
		return new FormComponent[] { campoDataInicio, campoDataFim };
	}

	@Override
	public void validate(final Form<?> form) {

		final Date startDate = (Date) campoDataInicio.getConvertedInput();
		final Date endDate = (Date) campoDataFim.getConvertedInput();

		if (startDate == null && endDate != null) {
			error(campoDataFim, "PeriodoValidator.data.ini");
		}

		if (startDate != null && endDate == null) {
			error(campoDataFim, "PeriodoValidator.data.fim");
		}

		if (startDate != null && endDate != null) {

			if (endDate.before(startDate)) {
				error(campoDataFim, "PeriodoValidator.range");
			}
		}
	}
}