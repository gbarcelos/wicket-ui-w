package br.com.oak.wicket.ui.mascara;

import com.google.code.jqwicket.api.AbstractJQOptions;
import com.google.code.jqwicket.api.IJQFunction;
import com.google.code.jqwicket.api.IJQStatement;
import com.google.code.jqwicket.api.JQuery;

public class MascaraOptions extends AbstractJQOptions {

	private static final long serialVersionUID = 3539852545232926672L;

	private CharSequence mask;

	public MascaraOptions(CharSequence mask) {
		this.mask = mask;
	}

	public CharSequence getMask() {
		return mask;
	}

	public MascaraOptions placeholder(CharSequence placeholder) {
		super.put("placeholder", placeholder);
		return this;
	}

	public MascaraOptions completedEvent(CharSequence callbackBody) {
		return completedEvent(JQuery.js(callbackBody, new Object[0]));
	}

	public MascaraOptions completedEvent(IJQStatement callbackBody) {
		return completedEvent(JQuery.$f(new CharSequence[] { callbackBody }));
	}

	public MascaraOptions completedEvent(IJQFunction callback) {
		super.put("completed", callback);
		return this;
	}
}