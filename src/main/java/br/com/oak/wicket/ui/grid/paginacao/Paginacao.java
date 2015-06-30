package br.com.oak.wicket.ui.grid.paginacao;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.data.DataView;

import br.com.oak.wicket.ui.grid.provider.IDataProvider;

public class Paginacao extends PagingNavigator {

	private static final long serialVersionUID = 3887749190767021036L;

	public Paginacao(final String id, final DataView<?> dataView,
			final IDataProvider<?> provider) {
		super(id, dataView);

		add(new HeadLine("headline", provider));
	}

	class HeadLine extends WebComponent {

		private static final long serialVersionUID = -7244536990183569963L;

		private final IDataProvider<?> provider;

		public HeadLine(String id, IDataProvider<?> provider) {
			super(id);
			this.provider = provider;
		}

		public void onComponentTagBody(final MarkupStream markupStream,
				final ComponentTag openTag) {

			String text = getHeadlineText();
			replaceComponentTagBody(markupStream, openTag, text);
		}

		public String getHeadlineText() {

			provider.setPagina(getPageable().getCurrentPage() + 1);

			StringBuilder sb = new StringBuilder();
			sb.append("Página ").append(provider.getPagina())
					.append(" de ")
					.append(provider.getNumeroPaginas());

			return sb.toString();
		}
	}
}