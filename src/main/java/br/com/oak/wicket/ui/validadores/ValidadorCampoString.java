package br.com.oak.wicket.ui.validadores;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.AbstractValidator;

/**
 * Classe pai dos validadores, adiciona uma mensagem ao validatable. evita
 * repetição do addErro.
 * 
 * @author gustavo.barcelos
 *
 */
public abstract class ValidadorCampoString extends AbstractValidator<String> {

	private static final long serialVersionUID = -5967836856202784522L;

	protected void addErro(final IValidatable<String> validatable,
			final String message) {
		ValidationError error = new ValidationError();
		error.setMessage(message);
		validatable.error(error);
	}
}