package br.com.pcmaker.spring.view.excel;

import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.AbstractView;

import br.com.pcmaker.common.util.ConfiguracaoUtils;

public abstract class GipAbstractExcelView extends AbstractView{

	private static final String GIP_WEB_RESOURCES_PATH = "GIP_WebResources";
	
	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		super.prepareResponse(request, response);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + getReportName() + EXTENSION+"\"");
	}
	
	protected String getTemplateWebResources(){
		return null;
	}
	
	private String getUrlWebResources(){
		return ConfiguracaoUtils.get(GIP_WEB_RESOURCES_PATH) + getTemplateWebResources();
	}

	protected abstract String getReportName();

	private static final String CONTENT_TYPE = "application/vnd.ms-excel";

	private static final String EXTENSION = ".xls";

	public GipAbstractExcelView() {
		setContentType(CONTENT_TYPE);
	}

	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}

	@Override
	protected final void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HSSFWorkbook workbook;
		if (this.getTemplateWebResources() != null) {
			workbook = getTemplateSource(this.getUrlWebResources(), request);
		}else {
			workbook = new HSSFWorkbook();
		}
		buildExcelDocument(model, workbook);
		response.setContentType(getContentType());
		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		out.flush();
	}

	protected HSSFWorkbook getTemplateSource(String url, HttpServletRequest request) throws Exception {
		LocalizedResourceHelper helper = new LocalizedResourceHelper(getApplicationContext());
		Locale userLocale = RequestContextUtils.getLocale(request);
		Resource inputFile = helper.findLocalizedResource(url, EXTENSION, userLocale);
		return new HSSFWorkbook(inputFile.getInputStream());
	}

	protected abstract void buildExcelDocument( Map<String, Object> model, HSSFWorkbook workbook) throws Exception;


	protected HSSFCell getCell(HSSFSheet sheet, int row, int col) {
		HSSFRow sheetRow = sheet.getRow(row);
		if (sheetRow == null) {
			sheetRow = sheet.createRow(row);
		}
		HSSFCell cell = sheetRow.getCell(col);
		if (cell == null) {
			cell = sheetRow.createCell(col);
		}
		return cell;
	}

	protected void setText(HSSFCell cell, String text) {
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(text);
	}
}
