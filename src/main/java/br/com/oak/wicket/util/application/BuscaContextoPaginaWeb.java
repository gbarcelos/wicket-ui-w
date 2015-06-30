package br.com.oak.wicket.util.application;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

import br.com.oak.wicket.util.PaginaWeb;


public class BuscaContextoPaginaWeb<T> {

	private static final String JAR = ".jar";

	private static final String PONTO = ".";

	private static final String CLASS = ".class";

	private static final String SIFRAO_$ = "$";

	private final Set<Class<T>> classes;

	private String pacoteRaiz;

	private String pacotePaginas;

	public BuscaContextoPaginaWeb(String pacoteRaiz, String pacotePaginas) {
		this.classes = new HashSet<Class<T>>();
		this.pacoteRaiz = pacoteRaiz;
		this.pacotePaginas = pacotePaginas;
		this.carregaLoader();
	}

	private void carregaLoader() {
		final ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		final URLClassLoader urls = (URLClassLoader) Thread.currentThread()
				.getContextClassLoader();

		for (final URL urlLouder : urls.getURLs()) {

			if (!urlLouder.toString().contains(JAR)) {

				URL urlPathRaiz;

				try {
					urlPathRaiz = criaUrlPathRaiz(urlLouder.toString());
					lerClassPath(urlPathRaiz, classLoader);
				} catch (final MalformedURLException e) {
					urlPathRaiz = null;
				} catch (final UnsupportedEncodingException e) {
					urlPathRaiz = null;
				} catch (final IOException e) {
					urlPathRaiz = null;
				} catch (final ClassNotFoundException e) {
					urlPathRaiz = null;
				}
			}
		}
	}

	private void lerClassPath(final URL url, final ClassLoader classLoader)
			throws UnsupportedEncodingException, IOException,
			ClassNotFoundException {

		if (url.getFile() != null) {
			classLoaderLerArquivo(classLoader, new File(converteUrlUTF8(url)));
		}
	}

	private void classLoaderLerArquivo(final ClassLoader classLoader,
			final File file) {

		if (file.isDirectory()) {
			lerDiretorio(file, classLoader, file.getPath().length());
		}
	}

	private void lerDiretorio(final File dir, final ClassLoader classLoader,
			final int removePos) {

		final File[] files = dir.listFiles();

		for (final File file : files) {

			if (file.isDirectory()) {
				lerDiretorio(file, classLoader, removePos);
			} else if (file.isFile()) {

				final String nomeArquivo = criaPathArquivo(file.getPath());

				if (nomeArquivo.endsWith(CLASS)) {

					Class<?> forName;

					try {
						forName = Class.forName(criaNomeClasse(nomeArquivo),
								true, classLoader);
						if (forName != null) {
							checkClass(forName);
						}
					} catch (final ClassNotFoundException e) {
						return;
					}
				}
			}
		}
	}

	private String criaPathArquivo(final String path) {
		return path.substring(path.indexOf(pacoteRaiz));
	}

	private String criaNomeClasse(final String nomeArquivo) {

		String nomeClasse = nomeArquivo;

		if (nomeArquivo.contains(SIFRAO_$)) {
			nomeClasse = nomeClasse.substring(0, nomeClasse.indexOf(SIFRAO_$));
		}

		if (nomeClasse.contains(CLASS)) {
			nomeClasse = nomeClasse.substring(0, nomeClasse.indexOf(CLASS));
		}

		nomeClasse = nomeClasse.replace("//", PONTO).replace("\\", PONTO)
				.replace("/", PONTO).replace(File.separator, PONTO);

		return nomeClasse;
	}

	private URL criaUrlPathRaiz(String urlLoader) throws MalformedURLException {

		if (!(urlLoader.endsWith("\\") || (urlLoader.endsWith("/")) || (urlLoader
				.endsWith(File.separator)))) {
			urlLoader += File.separator;
		}

		final String[] partes = pacotePaginas.split("\\.");

		final StringBuilder sb = new StringBuilder(urlLoader);

		for (String parte : partes) {
			sb.append(parte);
			sb.append(File.separator);
		}
		return new URL(sb.toString());
	}

	private String converteUrlUTF8(final URL url)
			throws UnsupportedEncodingException {
		return URLDecoder.decode(url.getFile(), "UTF-8");
	}

	@SuppressWarnings("unchecked")
	private void checkClass(final Class<?> classe) {
		if (classe.isAnnotationPresent(PaginaWeb.class)) {
			classes.add((Class<T>) classe);
		}
	}

	public Set<Class<T>> getClasses() {
		return classes;
	}
}
