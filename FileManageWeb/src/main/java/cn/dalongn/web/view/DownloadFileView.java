package cn.dalongn.web.view;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class DownloadFileView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition",
				"attachment;fileName=" + java.net.URLEncoder.encode(model.get("name").toString(), "UTF-8"));
		byte[] bs = (byte[]) model.get("file");
		OutputStream os = response.getOutputStream();
		os.write(bs);
		os.flush();
		os.close();
	}

}
