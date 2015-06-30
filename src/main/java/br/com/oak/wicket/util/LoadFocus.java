package br.com.oak.wicket.util;

import org.apache.wicket.Component;

public final class LoadFocus {

	private LoadFocus() {
	}

	public static String getFocusScript(final Component component) {
		return "$('#" + component.getMarkupId() + "').focus();";
	}

	public static String getFocusScript(final String id) {
		return "$('#" + id + "').focus();";
	}

	public static String getFocusScriptId(final Component component){
	    return "<script>document.getElementById('" + component.getMarkupId() + "').focus();</script>";
	}
}