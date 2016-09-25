package cn.dalongn.web.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CrossDomainView extends AbstractView {

	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		response.setContentType("text/plain;charset=utf-8");
		
		String args = objectMapper.writeValueAsString(model.get("args"));
		String method = (String) model.get("method");
		response.getWriter().write(method + "(" + args +  ")");
	}

}
