package br.com.oak.wicket.ui.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;

public class MenuItem implements Serializable {

	private static final long serialVersionUID = -7217136256567924926L;

	private TipoDestino destinationType;

	private String menuText;

	private Class<? extends WebPage> responsePageClass;

	private WebPage responsePage;

	private List<MenuItem> subMenuItemList = new ArrayList<MenuItem>();

	public MenuItem(String submenuTitle) {
		setMenuText(submenuTitle);
		setDestinationType(TipoDestino.NONE);
	}

	public MenuItem(String menuText,
			Class<? extends WebPage> destinationPageClass) {
		setMenuText(menuText);
		setResponsePageClass(destinationPageClass);
		setSubMenuItemList(new ArrayList<MenuItem>());
		setDestinationType(TipoDestino.WEB_PAGE_CLASS);
	}

	public String getMenuText() {
		return menuText;
	}

	public void setMenuText(String text) {
		menuText = text;
	}

	public WebPage getResponsePage() {
		return responsePage;
	}

	public void addSubmenu(MenuItem subMenuItem) {
		getSubMenuItemList().add(subMenuItem);
	}

	public List<MenuItem> getSubMenuItemList() {
		return subMenuItemList;
	}

	public void setSubMenuItemList(List<MenuItem> subMenuItemList) {
		this.subMenuItemList = subMenuItemList;
	}

	public TipoDestino getDestinationType() {
		return destinationType;
	}

	public void setDestinationType(TipoDestino tipoDeDestino) {
		destinationType = tipoDeDestino;
	}

	public Class<? extends WebPage> getResponsePageClass() {
		return responsePageClass;
	}

	public void setResponsePageClass(
			Class<? extends WebPage> destinationPageClass) {
		responsePageClass = destinationPageClass;
	}
}