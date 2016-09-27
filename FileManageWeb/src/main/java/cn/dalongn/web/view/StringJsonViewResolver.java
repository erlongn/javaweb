package cn.dalongn.web.view;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

import cn.dalongn.web.constants.WebViewDefalut;

public class StringJsonViewResolver extends AbstractCachingViewResolver {

	private StringJsonView stringJsonView;

	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {

		StringJsonView view = null;
		if (WebViewDefalut.STRING_JSON.equals(viewName)) {
			view = stringJsonView;
		}
		return view;
	}

	public StringJsonView getStringJsonView() {
		return stringJsonView;
	}

	public void setStringJsonView(StringJsonView stringJsonView) {
		this.stringJsonView = stringJsonView;
	}

}
