package br.com.oak.wicket.util.application;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.markup.html.WebPage;

import br.com.oak.wicket.util.PaginaWeb;

public class MapWebPage extends HashMap<String, Class<? extends WebPage>> {

	private static final long serialVersionUID = -5101796589890868295L;

	private static Set<Class<? extends WebPage>> classes;

	public static MapWebPage get(String pacoteRaiz, String pacotePaginas) {
		return new MapWebPage(pacoteRaiz, pacotePaginas);
	}

	public MapWebPage(String pacoteRaiz, String pacotePaginas) {
		super();
		criaMapaWebPage(pacoteRaiz, pacotePaginas);
	}

	public void criaMapaWebPage(String pacoteRaiz, String pacotePaginas) {

		final Set<Class<? extends WebPage>> classes = buscaClasses(pacoteRaiz,
				pacotePaginas);

		for (final Class<? extends WebPage> classe : classes) {

			final Annotation[] anotacoes = classe.getAnnotations();

			for (final Annotation anotacao : anotacoes) {
				adicionaClasseAoMapa(classe, anotacao);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Set<Class<? extends WebPage>> buscaClasses(String pacoteRaiz, String pacotePaginas) {

		if (classes == null) {
			BuscaContextoPaginaWeb contexto = new BuscaContextoPaginaWeb<Class>(
					pacoteRaiz, pacotePaginas);
			classes = contexto.getClasses();
		}
		return classes;
	}

	private void adicionaClasseAoMapa(Class<? extends WebPage> classe,
			Annotation anotacao) {

		if (anotacao instanceof PaginaWeb) {

			PaginaWeb anotacaoPg = (PaginaWeb) anotacao;

			if (StringUtils.isNotBlank(anotacaoPg.nome())) {
				this.put(anotacaoPg.nome(), classe);
			} else {
				this.put(classe.toString(), classe);
			}
		}
	}
}