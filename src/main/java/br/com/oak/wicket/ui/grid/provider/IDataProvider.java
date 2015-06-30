package br.com.oak.wicket.ui.grid.provider;

public interface IDataProvider<T> extends
		org.apache.wicket.markup.repeater.data.IDataProvider<T> {

	int getRegistroFinalPaginacao();

	int getRegistroInicialPaginacao();

	int getRegistroTotal();
	
	int getNumeroPaginas();

	void setPagina(int pagina);
	
	int getPagina();

	void setItensPorPagina(int itensPorPagina);

	int getItensPorPagina();
}
