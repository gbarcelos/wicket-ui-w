package br.com.oak.wicket.ui.grid.paginacao;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.navigation.paging.IPageable;

public class PaginacaoLink<T> extends AjaxLink<T> {

	private static final long serialVersionUID = 6021443720234342019L;

	protected final IPageable pageable;

	private final int pageNumber;

	public PaginacaoLink(final String id, final IPageable pageable,
			final int pageNumber) {
		super(id);

		this.pageNumber = pageNumber;
		this.pageable = pageable;
	}

	public final int getPageNumber() {
		return cullPageNumber(pageNumber);
	}

	protected int cullPageNumber(int pageNumber) {
		int idx = pageNumber;
		if (idx < 0) {
			idx = pageable.getPageCount() + idx;
		}

		if (idx > (pageable.getPageCount() - 1)) {
			idx = pageable.getPageCount() - 1;
		}

		if (idx < 0) {
			idx = 0;
		}

		return idx;
	}

	public final boolean isFirst() {
		return getPageNumber() == 0;
	}

	public final boolean isLast() {
		return getPageNumber() == (pageable.getPageCount() - 1);
	}

	public final boolean linksTo(final Page page) {
		return getPageNumber() == pageable.getCurrentPage();
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
