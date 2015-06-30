package br.com.oak.wicket.util.application;

import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import com.google.code.jqwicket.JQContributionConfig;

public class JQContributionConfigCustom extends JQContributionConfig {

	private static final long serialVersionUID = -3547218898643131823L;

	private static final MetaDataKey<JQContributionConfigCustom> configKey = new MetaDataKey<JQContributionConfigCustom>() {
        private static final long serialVersionUID = 1L;
    };

    private static final CharSequence defaultJQueryUiJsUrl = "http://ajax.googleapis.com/ajax/libs/" +
            "jqueryui/1.8.16/jquery-ui.min.js";

    private static final CharSequence defaultJQueryUiCssUrl = "http://ajax.googleapis.com/ajax/libs/" +
            "jqueryui/1.8.16/themes/base/jquery-ui.css";

    private CharSequence jqueryCoreJsUrl;

    private JavaScriptResourceReference jqueryCoreJsResourceReference;

    private CharSequence jqueryUiJsUrl = "noJS";

	private JavaScriptResourceReference jqueryUiJsResourceReference;

    private CharSequence jqueryUiCssUrl = "noCSS";

    private CharSequence nonConflictAlias;

    private CssResourceReference jqueryUiCssResourceReference;

    private boolean useYuiJavascriptCompressor = false;

    private boolean renderJSRefsBeforeUrls = false;

    private boolean renderCRefsBeforeUrls = false;

    public static JQContributionConfigCustom get() {
        return Application.get().getMetaData(configKey);
    }

    public static void set(JQContributionConfigCustom config) {
        Application.get().setMetaData(configKey, config);
    }

    public JQContributionConfigCustom() {
        this.jqueryCoreJsUrl = jqueryUiJsUrl;
    }

    public JQContributionConfigCustom(CharSequence jqueryCoreJsUrl) {
    	super();
        this.jqueryCoreJsResourceReference = null;
        this.jqueryCoreJsUrl = jqueryCoreJsUrl;
    }

    public JQContributionConfigCustom(
            JavaScriptResourceReference jqueryCoreResourceReference) {
    	super();
        this.jqueryCoreJsResourceReference = jqueryCoreResourceReference;
        this.jqueryCoreJsUrl = null;
    }

    public JQContributionConfigCustom enableNonConflictMode(
            CharSequence nonConflictAlias) {
    	
        this.nonConflictAlias = nonConflictAlias;
        return this;
    }

    public JQContributionConfigCustom withDefaultJQueryUi() {
    
        this.jqueryUiJsUrl = defaultJQueryUiJsUrl;
        this.jqueryUiCssUrl = defaultJQueryUiCssUrl;
        this.jqueryUiJsResourceReference = null;
        this.jqueryUiCssResourceReference = null;
        return this;
    }

    public JQContributionConfigCustom withJQueryUiJs(CharSequence url) {
    	
        this.jqueryUiJsUrl = url;
        this.jqueryUiJsResourceReference = null;
        return this;
    }

    public JQContributionConfigCustom withJQueryUiJs(JavaScriptResourceReference ref) {
    
        this.jqueryUiJsUrl = null;
        this.jqueryUiJsResourceReference = ref;
        return this;
    }

    public JQContributionConfigCustom withJQueryUiCss(CharSequence url) {
    	
        this.jqueryUiCssUrl = url;
        this.jqueryUiCssResourceReference = null;
        return this;
    }

    public JQContributionConfigCustom withJQueryUiCss(CssResourceReference ref) {
    	
        this.jqueryUiCssUrl = null;
        this.jqueryUiCssResourceReference = ref;
        return this;
    }

    public JQContributionConfigCustom withoutJQueryUi() {
        this.jqueryUiJsUrl = null;
        this.jqueryUiCssUrl = null;
        this.jqueryUiJsResourceReference = null;
        this.jqueryUiCssResourceReference = null;
        return this;
    }

    public JQContributionConfigCustom useYuiJavascriptCompressor() {
        this.useYuiJavascriptCompressor = true;
        return this;
    }

    public JQContributionConfigCustom renderJavascriptResourceRefsBeforeUrls() {
        this.renderJSRefsBeforeUrls = true;
        return this;
    }

    public JQContributionConfigCustom renderCssResourceRefsBeforeUrls() {
        this.renderCRefsBeforeUrls = true;
        return this;
    }

    public CharSequence getJqueryCoreJsUrl() {
        return jqueryCoreJsUrl;
    }

    public JavaScriptResourceReference getJqueryCoreJsResourceReference() {
        return jqueryCoreJsResourceReference;
    }

    public CharSequence getJqueryUiJsUrl() {
        return jqueryUiJsUrl;
    }

    public JavaScriptResourceReference getJqueryUiJsResourceReference() {
        return jqueryUiJsResourceReference;
    }

    public CharSequence getJqueryUiCssUrl() {
        return jqueryUiCssUrl;
    }

    public CssResourceReference getJqueryUiCssResourceReference() {
        return jqueryUiCssResourceReference;
    }

    public CharSequence getNonConflictAlias() {
        return nonConflictAlias;
    }

    public boolean isUseYuiJavascriptCompressor() {
        return useYuiJavascriptCompressor;
    }

    public boolean isRenderJavascriptResourceRefsBeforeUrls() {
        return renderJSRefsBeforeUrls;
    }

    public boolean isRenderCssResourceRefsBeforeUrls() {
        return renderCRefsBeforeUrls;
    }

    public void setJqueryUiJsUrl(CharSequence jqueryUiJsUrl) {
		this.jqueryUiJsUrl = jqueryUiJsUrl;
	}

	public void setJqueryUiCssUrl(CharSequence jqueryUiCssUrl) {
		this.jqueryUiCssUrl = jqueryUiCssUrl;
	}
}