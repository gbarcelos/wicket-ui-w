package br.com.oak.wicket.ui.grid.provider;

public abstract class DataProvider<T> implements IDataProvider<T> {

	private static final long serialVersionUID = -5976583287483812347L;

	private int pagina = 1;

	private int itensPorPagina;

	@Override
	public void setItensPorPagina(int itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	@Override
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	@Override
	public int getPagina() {
		return pagina;
	}

	@Override
	public int getRegistroTotal() {
		return size();
	}

	@Override
	public int getRegistroFinalPaginacao() {
		if (paginaAtualInvalida()) {
			this.setPagina((int) this.getNumeroPaginas(size()));
		}

		if (size() == 0) {
			return 0;
		}

		int numRegistros = size();
		double numPaginas = getNumeroPaginas(numRegistros);

		if (this.getPagina() < numPaginas) {
			return this.getPagina() * itensPorPagina;
		}

		if (this.getPagina() == numPaginas) {
			if ((numRegistros % itensPorPagina) == 0) {
				return this.getPagina() * itensPorPagina;
			} else if ((numRegistros % itensPorPagina) > 0) {
				return numRegistros;
			}
		}

		return 0;
	}

	@Override
	public int getRegistroInicialPaginacao() {
		if (paginaAtualInvalida()) {
			this.setPagina((int) this.getNumeroPaginas(size()));
		}

		if (size() == 0) {
			return 0;
		}

		return (((itensPorPagina * getPagina()) - itensPorPagina) + 1);
	}

	private double getNumeroPaginas(int numRegistros) {
		return Math.ceil(new Float(numRegistros) / new Float(itensPorPagina));
	}
	
	@Override
	public int getNumeroPaginas() {
		return (int) Math.ceil(new Float(size()) / new Float(itensPorPagina));
	}

	public boolean paginaAtualInvalida() {

		return this.getPagina() > this.getNumeroPaginas(size());
	}

	@Override
	public int getItensPorPagina() {
		return this.itensPorPagina;
	}
}