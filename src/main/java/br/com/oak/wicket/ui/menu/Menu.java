package br.com.oak.wicket.ui.menu;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

public class Menu extends Panel implements IHeaderContributor {

	private static final long serialVersionUID = -7723932577898009231L;

	public Menu(final String id, final List<MenuItem> menuItemList) {
		super(id);
		setRenderBodyOnly(true);
		setOutputMarkupId(true);

		final MultiLevelMenu multiLevelMenu = new MultiLevelMenu("multiLevelMenu",
				menuItemList);
		multiLevelMenu.setRenderBodyOnly(true);
		multiLevelMenu.setOutputMarkupId(true);

		add(multiLevelMenu);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
	}

	private void processResponse(final MenuItem menuItem) {

		switch (menuItem.getDestinationType()) {

		case WEB_PAGE_CLASS:
			processPageClass(menuItem);
			break;

		case WEB_PAGE_INSTANCE:
			processPageInstance(menuItem);
			break;

		case EXTERNAL_LINK:
			break;

		case NONE:
			break;

		default:
			break;
		}
	}

	private void processPageInstance(final MenuItem menuItem) {

		final WebPage pagina = menuItem.getResponsePage();

		if (pagina instanceof IMenu) {
			setResponsePage(((IMenu) pagina).setEntradaMenu(true));
		} else {
			setResponsePage(pagina);
		}
	}

	private void processPageClass(final MenuItem menuItem) {
		try {
			final WebPage pagina = menuItem.getResponsePageClass()
					.newInstance();

			if (pagina instanceof IMenu) {
				setResponsePage(((IMenu) pagina).setEntradaMenu(true));
			} else {
				setResponsePage(menuItem.getResponsePageClass());
			}

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	class MultiLevelMenu extends Panel {

		private static final long serialVersionUID = 8181977692553684179L;

		public MultiLevelMenu(final String id, final List<MenuItem> menuItemList) {

			super(id);

			if ((menuItemList == null) || (menuItemList.size() == 0)) {
				return;
			}

			final ListView<MenuItem> menu = buildMultiLevelMenu("menuList",
					menuItemList);
			menu.setReuseItems(true);

			add(menu);
		}

		private ListView<MenuItem> buildMultiLevelMenu(final String id,
				final List<MenuItem> menuItemList) {

			return new ListView<MenuItem>(id, menuItemList) {

				private static final long serialVersionUID = 3894361326825281159L;

				@Override
				public void populateItem(final ListItem<MenuItem> item) {

					final MenuItem menuItem = item.getModelObject();

					criaLinkText(item, menuItem, newLink(menuItem));

					criaSubMenuItem(item, menuItem);
				}

				private Link<MenuItem> newLink(final MenuItem menuItem) {

					return new Link<MenuItem>("menuLink") {

						private static final long serialVersionUID = -672534477365895988L;

						@Override
						public void onClick() {

							if (menuItem != null) {
								processResponse(menuItem);
							}
						}
					};
				}

				private void criaSubMenuItem(final ListItem<MenuItem> item,
						final MenuItem menuItem) {

					final List<MenuItem> submenuItemList = menuItem
							.getSubMenuItemList();

					if ((submenuItemList != null)
							&& (submenuItemList.size() > 0)) {

						final MultiLevelSubMenu subLevelMenu = new MultiLevelSubMenu(
								"submenuListContainer", submenuItemList);
						subLevelMenu.setRenderBodyOnly(true);

						item.add(subLevelMenu);

					} else {
						final WebMarkupContainer submenuMarkupContainer = new WebMarkupContainer(
								"submenuListContainer");
						submenuMarkupContainer.setRenderBodyOnly(true);
						item.add(submenuMarkupContainer);
					}
				}

				private void criaLinkText(final ListItem<MenuItem> item,
						final MenuItem menuItem, Link<MenuItem> link) {

					final Label linkText = new Label("menuLinkText",
							menuItem.getMenuText());
					linkText.setRenderBodyOnly(true);
					link.add(linkText);

					item.add(link);

					link.add(new AttributeModifier("alt", menuItem
							.getMenuText()));
				}
			};
		}
	}

	class MultiLevelSubMenu extends Panel {

		private static final long serialVersionUID = 3603924236484271553L;

		public MultiLevelSubMenu(String id, List<MenuItem> menuItemList) {
			super(id);

			if ((menuItemList == null) || (menuItemList.size() == 0)) {
				return;
			}

			final ListView<MenuItem> menu = buildMultiLevelMenu("menuList",
					menuItemList);
			menu.setReuseItems(true);

			add(menu);
		}

		private ListView<MenuItem> buildMultiLevelMenu(final String id,
				final List<MenuItem> menuItemList) {

			return new ListView<MenuItem>(id, menuItemList) {

				private static final long serialVersionUID = -1409923124794588432L;

				@Override
				public void populateItem(final ListItem<MenuItem> item) {

					final MenuItem menuItem = item.getModelObject();

					criaLinkText(item, menuItem, newMenuItem(menuItem));

					criaSubMenuItem(item, menuItem);
				}

				private void criaSubMenuItem(final ListItem<MenuItem> item,
						final MenuItem menuItem) {

					final List<MenuItem> submenuItemList = menuItem
							.getSubMenuItemList();

					if ((submenuItemList != null)
							&& (submenuItemList.size() > 0)) {

						final MultiLevelSubMenu subLevelMenu = new MultiLevelSubMenu(
								"submenuListContainer", submenuItemList);
						subLevelMenu.setRenderBodyOnly(true);
						item.add(subLevelMenu);

					} else {

						final WebMarkupContainer submenuMarkupContainer = new WebMarkupContainer(
								"submenuListContainer");
						submenuMarkupContainer.setRenderBodyOnly(true);
						item.add(submenuMarkupContainer);
					}
				}

				private void criaLinkText(final ListItem<MenuItem> item,
						final MenuItem menuItem, Link<MenuItem> link) {

					final Label linkText = new Label("menuLinkText",
							menuItem.getMenuText());
					linkText.setRenderBodyOnly(true);

					link.add(linkText);

					item.add(link);

					link.add(new AttributeModifier("alt", menuItem
							.getMenuText()));
				}

				private Link<MenuItem> newMenuItem(final MenuItem menuItem) {

					return new Link<MenuItem>("menuLink") {

						private static final long serialVersionUID = 482993143012492298L;

						@Override
						public void onClick() {
							if (menuItem != null) {
								processResponse(menuItem);
							}
						}
					};
				}
			};
		}
	}
}