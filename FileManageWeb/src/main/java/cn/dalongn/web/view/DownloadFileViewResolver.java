package cn.dalongn.web.view;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

import cn.dalongn.web.constants.WebDefalut;

public class DownloadFileViewResolver extends AbstractCachingViewResolver {

	private DownloadFileView downloadFileView;

	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {

		DownloadFileView view = null;
		if (WebDefalut.DOWNLOAD_FILE.equals(viewName)) {
			view = downloadFileView;
		}
		return view;
	}

	public DownloadFileView getDownloadFileView() {
		return downloadFileView;
	}

	public void setDownloadFileView(DownloadFileView downloadFileView) {
		this.downloadFileView = downloadFileView;
	}

}
