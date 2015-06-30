package br.com.oak.wicket.ui.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessages;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.string.Strings;

/**
 * Responsável por renderizar um label antes de cada campo. caso seja
 * obrigatório irá renderizar um asterisco. caso não esteja válido será
 * renderizado um panel abaixo do campo com a msg de erro.
 * 
 * ATENÇÃO
 * 
 * somente para botão submit.
 * 
 * @author gustavo.barcelos
 *
 */
public class FieldDecorator extends Behavior {

	private static final long serialVersionUID = 1033441193898577196L;

	public void bind(Component component) {
		component.setOutputMarkupId(true);
	}

	@Override
	public void beforeRender(Component component) {
		FormComponent<?> fc = (FormComponent<?>) component;
		Response r = component.getResponse();

		String label = (fc.getLabel() != null) ? fc.getLabel().getObject()
				: null;

		if (label != null) {
			r.write("<label for=\"");
			r.write(fc.getMarkupId());
			r.write("\"");
			if (fc.isValid() == false) {
				r.write(" class=\"error\"");
			}
			r.write(">");
			r.write(Strings.escapeMarkup(label));
			if (fc.isRequired()) {
				r.write("<span class=\"required\">*</span>");
			}
			r.write("</label>");
		}
		super.beforeRender(component);
	}

	@Override
	public void afterRender(Component component) {
		FormComponent<?> fc = (FormComponent<?>) component;

		Response r = component.getResponse();
		FeedbackMessages messages = fc.getSession().getFeedbackMessages();

		if (messages.hasMessageFor(component)) {
			r.write("<ul class=\"feedbackPanel\">");
			IFeedbackMessageFilter filter = new ComponentFeedbackMessageFilter(
					component);
			for (FeedbackMessage message : messages.messages(filter)) {
				r.write("<li class=\"feedbackPanel");
				r.write(message.getLevelAsString().toUpperCase());
				r.write("\">");
				r.write(Strings.escapeMarkup(message.getMessage().toString()));
				r.write("</li>");
			}
			r.write("</ul>");
		}
	}

	@Override
	public void onComponentTag(Component component, ComponentTag tag) {
		FormComponent<?> fc = (FormComponent<?>) component;
		if (fc.isValid() == false) {
			String cl = tag.getAttribute("class");
			if (cl == null) {
				tag.put("class", "et_contact_error");
			} else {
				tag.put("class", "et_contact_error " + cl);
			}
		}
	}

}
