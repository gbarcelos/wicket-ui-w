package br.com.oak.wicket.util;

import java.lang.annotation.Annotation;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.markup.html.WebPage;

public class GeradorUrl {

	private static final String SEPARADOR_URL = "/";
	
	private Class<? extends WebPage> classe;

	private String extensao;

	public GeradorUrl(final Class<? extends WebPage> classe) {
		this.classe = classe;
		this.extensao = null;
	}

	public GeradorUrl(final Class<? extends WebPage> classe,
			final String extensao) {
		this(classe);
		this.extensao = extensao;
	}

	public String gerar() {

		final StringBuilder url = new StringBuilder();

		final PaginaWeb anotacaoPaginaWeb = getAnotacaoPaginaWeb();

		if (anotacaoPaginaWeb != null
				&& StringUtils.isNotBlank(anotacaoPaginaWeb.parteUrl())) {

			url.append(anotacaoPaginaWeb.parteUrl());

			if (StringUtils.isNotBlank(anotacaoPaginaWeb.extensao())){
				url.append(anotacaoPaginaWeb.extensao());
			}

		} else {

			url.append(gerarUrlDefault(classe.toString()));
		}
		return url.toString();
	}

	private PaginaWeb getAnotacaoPaginaWeb() {

		PaginaWeb anotacaoPg = null;

		final Annotation[] anotacoes = classe.getAnnotations();

		for (final Annotation anotacao : anotacoes) {

			if (anotacao instanceof PaginaWeb) {

				anotacaoPg = (PaginaWeb) anotacao;
				break;
			}
		}
		return anotacaoPg;
	}

	private String gerarUrlDefault(final String nomeClasse) {

		final StringBuilder urlBase = new StringBuilder(
				SEPARADOR_URL);

		final String[] partesNomeClasse = nomeClasse.split("pages.");

		if (partesNomeClasse.length >= 2) {

			urlBase.append(gerarUrlAmigavel(partesNomeClasse[1].replaceAll(
					"\\.", SEPARADOR_URL).replaceAll("Page", "")));

		} else {
			urlBase.append(nomeClasse);
		}
		return urlBase.toString();
	}

	/**
	 * 
	 * @param url
	 *            visitante/conta/ConsultaContaUsuario
	 * 
	 * @return /visitante/conta/consulta-conta-usuario
	 */
	private String gerarUrlAmigavel(final String url) {

		final StringBuilder urlAmigavel = new StringBuilder();

		final String[] partesUrl = url.split(SEPARADOR_URL);

		for (int ix = 0; ix < partesUrl.length; ix++) {

			if (isUltimoItemArray(partesUrl, ix)) {

				urlAmigavel.append(separarCamelCase(partesUrl[ix]));

			} else {

				urlAmigavel.append(partesUrl[ix]);
				urlAmigavel.append(SEPARADOR_URL);
			}
		}

		if (StringUtils.isNotBlank(extensao)) {
			urlAmigavel.append(extensao);
		}
		return urlAmigavel.toString();
	}

	private boolean isUltimoItemArray(final String[] partesUrl, int i) {
		return i == (partesUrl.length - 1);
	}

	/**
	 * 
	 * @param parteUrl
	 *            ConsultaContaUsuario
	 * @return consulta-conta-usuario
	 */
	private String separarCamelCase(final String parteUrl) {

		final StringBuilder urlAmigavel = new StringBuilder();

		urlAmigavel.append(Character.toLowerCase(parteUrl.charAt(0)));

		for (int i = 1; i < parteUrl.length(); i++) {

			if (Character.isUpperCase(parteUrl.charAt(i))) {

				urlAmigavel.append("-");
				urlAmigavel.append(Character.toLowerCase(parteUrl.charAt(i)));

			} else {
				urlAmigavel.append(parteUrl.charAt(i));
			}
		}
		return urlAmigavel.toString();
	}
}