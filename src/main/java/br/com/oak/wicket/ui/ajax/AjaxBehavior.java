package br.com.oak.wicket.ui.ajax;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.util.string.AppendingStringBuffer;

public abstract class AjaxBehavior extends AjaxEventBehavior {

	private static final long serialVersionUID = 1652662764655242263L;

	public AjaxBehavior(final String event) {
		super(event);
	}

	protected final FormComponent<?> getFormComponent() {
		return (FormComponent<?>) getComponent();
	}

	@Override
	protected final CharSequence getEventHandler() {
		return generateCallbackScript(new AppendingStringBuffer("wicketAjaxPost('").append(getCallbackUrl()).append(
				"', wicketSerialize(Wicket.$('" + getComponent().getMarkupId() + "'))"));
	}

	@Override
	protected void onCheckEvent(String event) {
		if ("href".equalsIgnoreCase(event)) {
			throw new IllegalArgumentException("this behavior cannot be attached to an 'href' event");
		}
	}

	@Override
	protected final void onEvent(final AjaxRequestTarget target) {
		final FormComponent<?> formComponent = getFormComponent();

		if (getEvent().equalsIgnoreCase("onblur")) {
			target.focusComponent(null);
		}

		formComponent.inputChanged();
		formComponent.setRequired(false);
		formComponent.validate();
		formComponent.updateModel();

		onUpdate(target);
	}

	protected abstract void onUpdate(AjaxRequestTarget target);

	protected void onError(AjaxRequestTarget target, RuntimeException e) {
		if (e != null) {
			throw e;
		}
	}
}