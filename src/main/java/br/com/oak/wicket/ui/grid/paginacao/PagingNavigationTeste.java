package br.com.oak.wicket.ui.grid.paginacao;

import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;

public abstract class PagingNavigationTeste extends PagingNavigation{

	private static final long serialVersionUID = 952451705904434339L;

	public PagingNavigationTeste(final String id, final IPageable pageable,
			final IPagingLabelProvider labelProvider) {
		super(id, pageable, labelProvider);
	}
	
	protected abstract void populateItem(final LoopItem loopItem);

	
}
