package br.com.oak.wicket.ui.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.request.Response;

/**
 * Adiciona marcador de campo obrigatório antes de cada campos AUTOMAGICAMENTE
 * 
 * @author gustavo.barcelos
 *
 */
public class RedAsteriskBehavior extends Behavior {

	private static final long serialVersionUID = 8323782113670932101L;

	@SuppressWarnings("rawtypes")
	@Override
	public void beforeRender(Component component) {

		Response response = component.getResponse();

		StringBuffer asterisktHtml = new StringBuffer(200);

		if (component instanceof FormComponent
				&& ((FormComponent) component).isRequired()) {

			asterisktHtml.append("<span style=\"color:red;\">*</span>");
		}

		response.write(asterisktHtml);
	}
}