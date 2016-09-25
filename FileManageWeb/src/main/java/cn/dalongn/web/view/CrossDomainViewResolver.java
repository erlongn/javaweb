package cn.dalongn.web.view;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

import cn.dalongn.web.constants.WebDefalut;

public class CrossDomainViewResolver extends AbstractCachingViewResolver {

	private CrossDomainView crossDomainView;

	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {

		CrossDomainView view = null;
		if (WebDefalut.CROSS_DOMAIN.equals(viewName)) {
			view = crossDomainView;
		}
		return view;
	}

	public CrossDomainView getCrossDomainView() {
		return crossDomainView;
	}

	public void setCrossDomainView(CrossDomainView crossDomainView) {
		this.crossDomainView = crossDomainView;
	}

}
