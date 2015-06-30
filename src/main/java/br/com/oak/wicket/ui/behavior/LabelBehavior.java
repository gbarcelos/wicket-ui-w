package br.com.oak.wicket.ui.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.string.Strings;

public class LabelBehavior extends Behavior {

	private static final long serialVersionUID = -6035697945860349271L;

	@Override
	public void beforeRender(final Component component) {

		final FormComponent<?> fc = (FormComponent<?>) component;
		final Response response = component.getResponse();

		final String label = (fc.getLabel() != null) ? fc.getLabel()
				.getObject() : null;

		if (label != null) {

			final StringBuffer htmlLabel = new StringBuffer();
			htmlLabel.append("<label for=\"");
			htmlLabel.append(fc.getMarkupId());
			htmlLabel.append("\">");
			htmlLabel.append(Strings.escapeMarkup(label));

			if (fc.isRequired()) {
				htmlLabel.append("<span style=\"color:red;\">*</span>");
			}
			htmlLabel.append("</label>");
			response.write(htmlLabel.toString());
		}
		super.beforeRender(component);
	}
}