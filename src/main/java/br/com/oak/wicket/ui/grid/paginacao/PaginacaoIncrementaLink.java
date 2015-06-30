package br.com.oak.wicket.ui.grid.paginacao;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.navigation.paging.IPageable;

public class PaginacaoIncrementaLink extends AjaxLink<Void> {

	private static final long serialVersionUID = 3981752636664652159L;

	private final int increment;

	protected final IPageable pageable;

	public PaginacaoIncrementaLink(final String id, final IPageable pageable,
			final int increment) {
		super(id);
		this.increment = increment;
		this.pageable = pageable;
	}

	public final int getPageNumber() {
		int idx = pageable.getCurrentPage() + increment;

		return Math.max(0, Math.min(pageable.getPageCount() - 1, idx));
	}

	public boolean isFirst() {
		return pageable.getCurrentPage() <= 0;
	}

	public boolean isLast() {
		return pageable.getCurrentPage() >= (pageable.getPageCount() - 1);
	}

	public boolean linksTo(final Page page) {
		pageable.getCurrentPage();
		return ((increment < 0) && isFirst()) || ((increment > 0) && isLast());
	}

	@Override
	public boolean isEnabled() {
		return !linksTo(getPage());
	}

	@Override
	public void onClick(AjaxRequestTarget target) {
		pageable.setCurrentPage(getPageNumber());
	}
}