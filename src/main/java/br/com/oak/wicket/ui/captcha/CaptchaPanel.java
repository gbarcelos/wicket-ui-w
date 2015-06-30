package br.com.oak.wicket.ui.captcha;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.oak.wicket.ui.basico.CampoTexto;

public class CaptchaPanel extends Panel {

	private static final long serialVersionUID = 964472282290872389L;

	private String captchaInput;

	public CaptchaPanel(final String idPanel, final String mensagemErro,
			final String labelLink, final String textoLink,
			final String tituloLink, final String labelCampo) {

		super(idPanel);

		inicializar(mensagemErro, labelLink, textoLink, tituloLink, labelCampo);
	}

	private void inicializar(final String mensagemErro, final String labelLink,
			final String textoLink, final String tituloLink,
			final String labelCampo) {

		criarCampoCaptchaImage(labelLink, textoLink, tituloLink);
		criarCampoCaptchaInput(mensagemErro, labelCampo);
	}

	private void criarCampoCaptchaImage(final String labelLink,
			final String textoLink, final String tituloLink) {

		final CaptchaImage captchaImage = new CaptchaImage("captchaImage");
		captchaImage.setOutputMarkupId(true);
		addOrReplace(captchaImage);

		addOrReplace(new Label("labelTxtLinkAtualizar", labelLink));

		final AjaxFallbackLink<Object> linkAtualizar = new AjaxFallbackLink<Object>(
				"linkAtualizar") {

			private static final long serialVersionUID = -1405948895854363781L;

			@Override
			public void onClick(final AjaxRequestTarget target) {

				captchaImage.detach();

				target.add(captchaImage);
			}
		};

		linkAtualizar.add(new Label("labelLinkAtualizar", textoLink));
		linkAtualizar.add(new AttributeModifier("title", tituloLink));

		linkAtualizar.add(new AttributeModifier("width", "200"));
		linkAtualizar.add(new AttributeModifier("height", "50"));

		addOrReplace(linkAtualizar);
	}

	private void criarCampoCaptchaInput(final String mensagemErro,
			final String labelCampo) {

		final CampoTexto<String> campoCaptcha = new CampoTexto<String>(
				"txtCaptcha", new PropertyModel<String>(this, "captchaInput"),
				true);

		campoCaptcha.setLabel(new Model<String>(labelCampo));
		campoCaptcha.add(new AttributeModifier("placeholder", labelCampo));
		campoCaptcha.add(new CaptchaValidator(mensagemErro));

		add(campoCaptcha);
	}

	public String getCaptchaInput() {
		return captchaInput;
	}

	public void setCaptchaInput(String captchaInput) {
		this.captchaInput = captchaInput;
	}
}