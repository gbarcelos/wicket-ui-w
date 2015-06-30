package br.com.oak.wicket.ui.mascara;

import com.google.code.jqwicket.JQHeaderContributionTarget;
import com.google.code.jqwicket.api.IJQStatement;
import com.google.code.jqwicket.api.JQuery;
import com.google.code.jqwicket.ui.JQComponentBehavior;

public class Mascara extends JQComponentBehavior {

	private static final long serialVersionUID = -5618416585439287956L;

	public Mascara(CharSequence mask) {
		this(new MascaraOptions(mask));
	}

	public Mascara(MascaraOptions options) {
		super(options);
	}

	@Override
	public CharSequence getName() {
		return "mask";
	}

	@Override
	protected void contributeInternal(JQHeaderContributionTarget target) {
		target.addJQStatements(new IJQStatement[] { JQuery.$(component).chain(
				getName(),
				new CharSequence[] { ((MascaraOptions) options).getMask(),
						options }) });
	}
}