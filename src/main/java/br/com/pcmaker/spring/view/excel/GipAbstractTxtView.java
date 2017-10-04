package br.com.pcmaker.spring.view.excel;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.view.AbstractView;

public abstract class GipAbstractTxtView extends AbstractView{

	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		super.prepareResponse(request, response);
		response.setHeader("Content-Disposition", "attachment; filename=\""+getReportName()+"."+EXTENSION+"\"");
	}
	
	protected abstract String getReportName();

	private static final String CONTENT_TYPE = "text/plain";

	private static final String EXTENSION = ".txt";

	public GipAbstractTxtView() {
		setContentType(CONTENT_TYPE);
	}

	@Override
	protected final void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType(getContentType());
		ServletOutputStream out = response.getOutputStream();
		IOUtils.write(buildTxtDocument(model), out, "UTF-8");
		out.flush();
	}

	protected abstract String buildTxtDocument(Map<String, Object> model);
	
}
