package br.com.oak.wicket.util;

import org.apache.wicket.protocol.http.WebSession;

public class WebUtil {
	
	public static String identificaAgente() {
		final String agente = WebSession.get().getClientInfo().getUserAgent();
		if (agente.contains("MSIE")) {
			return "onblur";
		}
		return "onchange";
	}
}