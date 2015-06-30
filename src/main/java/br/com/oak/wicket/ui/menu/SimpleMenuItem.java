package br.com.oak.wicket.ui.menu;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class SimpleMenuItem implements Serializable {

	private static final long serialVersionUID = 1293758018139126057L;

	private String menuText;

	private String menuTitleText;

	private Class<? extends WebPage> responsePageClass;

	private Link<Void> internalLink;

	public String getMenuText() {
		return menuText;
	}

	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}

	public String getMenuTitleText() {
		return menuTitleText;
	}

	public void setMenuTitleText(String menuTitleText) {
		this.menuTitleText = menuTitleText;
	}

	public Class<? extends WebPage> getResponsePageClass() {
		return responsePageClass;
	}

	public void setResponsePageClass(Class<? extends WebPage> responsePageClass) {
		this.responsePageClass = responsePageClass;
	}

	public Link<Void> getInternalLink() {
		return internalLink;
	}

	public void setInternalLink(Link<Void> internalLink) {
		this.internalLink = internalLink;
	}
}