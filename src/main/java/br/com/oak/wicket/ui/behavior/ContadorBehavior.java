package br.com.oak.wicket.ui.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.request.Response;

/**
 * Adiciona span com contador de carateres remanecentes de cada campo que possui
 * limite de caracteres definido.
 * 
 * @author gustavo.barcelos
 *
 */
public class ContadorBehavior extends Behavior {

	private static final long serialVersionUID = -8848996349932280031L;

	@SuppressWarnings("rawtypes")
	@Override
	public void afterRender(Component component) {

		Response response = component.getResponse();

		StringBuffer contadortHtml = new StringBuffer(200);

		if (component instanceof FormComponent
				&& ((FormComponent) component).isRequired()) {

			String id = component.getId() + "-contador";

			contadortHtml.append("<span id=\"" + id + "\"/>");
		}
		response.write(contadortHtml);
	}
}