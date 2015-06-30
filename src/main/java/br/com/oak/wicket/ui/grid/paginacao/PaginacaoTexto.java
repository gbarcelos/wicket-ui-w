package br.com.oak.wicket.ui.grid.paginacao;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.data.DataView;

import br.com.oak.wicket.ui.grid.provider.IDataProvider;

public class PaginacaoTexto extends PagingNavigator {

	private static final long serialVersionUID = -765342233728432866L;

	private PagingNavigation pagingNavigation;

	private Label informativo;

	private IDataProvider<?> provider;

	private DataView<?> dataView;

	private Component tabela;

	public PaginacaoTexto(final String id, final DataView<?> dataView,
			final IDataProvider<?> provider, Component tabela) {

		super(id, dataView);
		this.dataView = dataView;
		this.provider = provider;
		this.tabela = tabela;
	}

	@Override
	protected void onBeforeRender() {

		if (get("first") == null) {

			pagingNavigation = newNavigation("navigation", dataView, null);
			add(pagingNavigation);

			criaLinkFirst();
			criaLinbkPrev();
			criaLinkNext();
			criaLinkLast();
		}
		carregaInformacaoPaginas();
		super.onBeforeRender();
	}

	@Override
	protected PagingNavigation newNavigation(String id, IPageable pageable,
			IPagingLabelProvider labelProvider) {

		return new PagingNavigationTeste(id, pageable, labelProvider) {

			private static final long serialVersionUID = -7171724651447926664L;

			@Override
			protected void populateItem(final LoopItem loopItem) {

				final int pageIndex = getStartIndex() + loopItem.getIndex();

				final AbstractLink link = PaginacaoTexto.this
						.newPagingNavigationLink("pageLink", pageable,
								pageIndex);

				String label = "";
				if (labelProvider != null) {
					label = labelProvider.getPageLabel(pageIndex);
				} else {
					label = String.valueOf(pageIndex + 1);
				}
				link.add(new Label("pageNumber", label));

				loopItem.add(link);
			}
		};
	}

	private void criaLinkFirst() {
		final AbstractLink first = newPagingNavigationLink("first", getPageable(), 0);
		first.add(new TitleAppender("PagingNavigator.first"));

		add(first);
	}

	private void criaLinbkPrev() {
		final AbstractLink prev = newPagingNavigationIncrementLink("prev",
				getPageable(), -1);
		prev.add(new TitleAppender("PagingNavigator.previous"));

		add(prev);
	}

	private void criaLinkNext() {
		final AbstractLink next = newPagingNavigationIncrementLink("next",
				getPageable(), 1);
		next.add(new TitleAppender("PagingNavigator.next"));

		add(next);
	}

	private void criaLinkLast() {
		final AbstractLink last = newPagingNavigationLink("last", getPageable(), -1);
		last.add(new TitleAppender("PagingNavigator.last"));

		add(last);
	}

	private void carregaInformacaoPaginas() {

		provider.setPagina(getPageable().getCurrentPage() + 1);

		StringBuilder sb = new StringBuilder();
		sb.append("Página ").append(provider.getPagina()).append(" de ")
				.append(provider.getNumeroPaginas());

		informativo = new Label("informativo", sb.toString());
		addOrReplace(informativo);
	}

	@Override
	protected AbstractLink newPagingNavigationIncrementLink(final String id,
			final IPageable pageable, final int increment) {

		return new PaginacaoIncrementaLink(id, pageable, increment) {

			private static final long serialVersionUID = -4606389130436804657L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				super.onClick(target);

				if (tabela != null) {
					target.add(tabela);
				} else {
					target.add(PaginacaoTexto.this.findPage());
				}
			}
		};
	}

	@Override
	public PaginacaoLink<Void> newPagingNavigationLink(final String id, final IPageable pageable,
			final int pageNumber) {

		return new PaginacaoLink<Void>(id, pageable, pageNumber) {

			private static final long serialVersionUID = 6810587228700276558L;

			@Override
			public void onClick(final AjaxRequestTarget target) {
				super.onClick(target);

				if (tabela != null) {
					target.add(tabela);
				} else {
					target.add(PaginacaoTexto.this.findPage());
				}
			}
		};
	}

	private final class TitleAppender extends Behavior {

		private static final long serialVersionUID = 3608358099478247459L;

		private final String resourceKey;

		public TitleAppender(final String resourceKey) {
			this.resourceKey = resourceKey;
		}

		@Override
		public void onComponentTag(final Component component, final ComponentTag tag) {
			tag.put("title", PaginacaoTexto.this.getString(resourceKey));
		}
	}
}