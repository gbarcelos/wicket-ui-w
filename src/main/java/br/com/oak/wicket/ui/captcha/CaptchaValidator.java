package br.com.oak.wicket.ui.captcha;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.validation.IValidatable;

import br.com.oak.wicket.ui.validadores.ValidadorCampoString;

public class CaptchaValidator extends ValidadorCampoString {

	private static final long serialVersionUID = -2851258398713067495L;

	private String mensagemErro;

	public CaptchaValidator(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	@Override
	protected void onValidate(IValidatable<String> validatable) {

		String kaptchaReceived = (String) validatable.getValue();

		Request request = RequestCycle.get().getRequest();

		HttpServletRequest httpRequest = (HttpServletRequest) request
				.getContainerRequest();

		String kaptchaExpected = (String) httpRequest.getSession()
				.getAttribute(
						com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

		if (kaptchaReceived == null
				|| !kaptchaReceived.equalsIgnoreCase(kaptchaExpected)) {

			addErro(validatable, mensagemErro);
		}
	}
}