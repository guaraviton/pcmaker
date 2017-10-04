package br.com.pcmaker.spring.view.excel.support;

import java.awt.font.FontRenderContext;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.util.CellRangeAddress;

import br.com.pcmaker.common.util.DateUtils;
import br.com.pcmaker.common.util.IOUtils;
import br.com.pcmaker.spring.view.excel.support.GipExcelStylerBuilder.StylerBuilder;

/**
 * Created with IntelliJ IDEA. User: Y3OS Date: 19/03/14 Time: 16:28 To change
 * this template use File | Settings | File Templates.
 */
public class GipExcelFormater {

	
	
	protected static final Log LOG = LogFactory.getLog(GipExcelFormater.class);
	private static Map<String, Integer> fontDetailsMap = new HashMap<String, Integer>();	
	public static GipExcelFormaterUtil NOPRINT = GipExcelFormaterUtil.NOPRINT;
	public static final Alinhamento CENTRALIZADO = Alinhamento.CENTRALIZADO;
	
	private HSSFWorkbook workbook;
	protected HSSFSheet sheet;
	private Map<HSSFCellStyle, HSSFCellStyle> csTransparente = new HashMap<HSSFCellStyle, HSSFCellStyle>();
	private Cabecalhos cabecalhos;
	protected GipExcelStylerBuilder stylerBuilder;
	private int[] colunasUnique;
	protected int linhaAtual;
	private int modClaroCabecalho; // usado para fazer o "zebrado" no cabecalho
	protected int colunaInicial;
	protected int colunaAtual;
	private int linhaInicialDados;
	private int modClaroDado;
	private int colunaDadoCabecalho;
	protected int linhaInicialGrupo = -1;
	private int alturaLinhaAtual = 1;
	
	private enum GipExcelFormaterUtil {
		NOPRINT
	}
	
	public static enum Alinhamento {
		CENTRALIZADO, DIREITA
	}
	
	public enum TipoMerge {
		COLUNA, LINHA, AMBOS
	}

	public int getLinhaAtual() {
		return linhaAtual;
	}

	public void addJpgImage(String path, int linhas, int colunas) {

		try {
			byte[] bytes = IOUtils.toByteArray(path);
			addJpgImage(bytes, linhas, colunas);
		} catch (IOException e) {
			LOG.error(e);
		} finally {
			IOUtils.deleteFile(path);
		}
	}
	
	public void addJpgImage(URL url, int linhas, int colunas) {
		try {
			byte[] bytes = IOUtils.toByteArray(url);
			
			
			if (bytes != null) {
				workbook.getSheetAt(0).getDrawingPatriarch();
				int imgIndex = workbook.addPicture(bytes, HSSFWorkbook.PICTURE_TYPE_JPEG);
				HSSFClientAnchor anchor = new HSSFClientAnchor(1,1,50000, 8300,(short) 0, (short) 0, (short) 0, (short)0 );
				
				HSSFPatriarch drawing = workbook.getSheetAt(0).getDrawingPatriarch();
				
				drawing.createPicture(anchor, imgIndex);
			}
			
			linhaAtual = (linhaAtual + linhas);
			if (linhaInicialDados < linhaAtual) {
				linhaInicialDados = linhaAtual;
			}
			
			
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	public void addJpgImage(byte[] bytes , int linhas, int colunas) {
		
		if (bytes != null) {
			workbook.getSheetAt(0).getDrawingPatriarch();
			int imgIndex = workbook.addPicture(bytes, HSSFWorkbook.PICTURE_TYPE_JPEG);
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) colunaInicial, (short) linhaAtual, (short) (colunaInicial + colunas),
					(short) (linhaAtual + linhas));
			HSSFPatriarch drawing = workbook.getSheetAt(0).getDrawingPatriarch();
			drawing.createPicture(anchor, imgIndex);
		}
		
		linhaAtual = (linhaAtual + linhas);
		if (linhaInicialDados < linhaAtual) {
			linhaInicialDados = linhaAtual;
		}
		
	}

	public void pularLinha() {
		linhaAtual++;
	}

	public void novaColuna(int linha, int coluna) {
		linhaAtual = linha;
		colunaAtual = coluna;
	}

	public static GipExcelFormater create(HSSFWorkbook workbook) throws Exception {
		return new GipExcelFormater(workbook, 0, null);
	}

	public static GipExcelFormater create(HSSFWorkbook workbook, int sheetIndex, String sheetName) throws Exception {
		return new GipExcelFormater(workbook, sheetIndex, sheetName);
	}

	public static GipExcelFormater create(HSSFWorkbook workbook, byte r, byte g, byte b) throws Exception {
		return new GipExcelFormater(workbook, 0, null, HSSFColor.GOLD.index, r, g, b);
	}

	public GipExcelFormater linhaInicial(int linhaInicial) {
		this.setLinhaInicial(linhaInicial);
		this.linhaAtual = linhaInicial;
		return this;
	}

	public GipExcelFormater colunaInicial(int colunaInicial) {
		this.setColunaInicial(colunaInicial);
		return this;
	}

	public int colunaInicial() {
		return colunaInicial();
	}

	public GipExcelFormater colunasUnique(int... colunasUnique) {
		this.colunasUnique = colunasUnique;
		return this;
	}

	protected GipExcelFormater(HSSFWorkbook workbook, int sheetIndex, String sheetName) throws Exception {
		this(workbook, sheetIndex, sheetName, HSSFColor.BLUE_GREY.index, (byte) 0, (byte) 100, (byte) 152);
	}

	private GipExcelFormater(HSSFWorkbook workbook, int sheetIndex, String sheetName, short index, byte r, byte g, byte b) throws Exception {
		this.workbook = workbook;
		try {
			if (workbook.getNumberOfSheets() <= sheetIndex) {
				for (int i = workbook.getNumberOfSheets() - 1; i < sheetIndex; i++) {
					workbook.createSheet();
					HSSFSheet sheetTmp = workbook.getSheetAt(workbook.getNumberOfSheets() - 1);
					int last = sheetTmp.getLastRowNum();
					for (int j = last; j > 4; j--) {
						sheetTmp.removeRow(sheetTmp.getRow(sheetTmp.getLastRowNum()));
					}
					
					/*
					int numMergedRegions = sheetTmp.getNumMergedRegions();
					for(int w=0; w < numMergedRegions; w++) {
						sheetTmp.removeMergedRegion(0);
					}
					*/
				}
			}
			
			sheet = workbook.getSheetAt(sheetIndex);
			
			if (sheetName != null) {
				workbook.setSheetName(sheetIndex, sheetName);
			}
			
			sheet.setDefaultRowHeightInPoints(11);
			this.stylerBuilder = new GipExcelStylerBuilder(workbook, index, r, g, b);
		} catch (Exception e) {
			LOG.error("Erro ao gerar excel", e);
			throw e;
		}
	}
	
	public GipExcelFormater setSheet(int sheetIndex) {
		this.linhaInicial(3);
		this.colunaInicial(1);
		this.setLinhaInicialDados(3);
		sheet = workbook.getSheetAt(sheetIndex);
		return this;
	}

	public void setLinhaInicial(int linhaInicial) {
		this.linhaAtual = linhaInicial;
		this.modClaroCabecalho = linhaAtual % 2;
	}

	public void setColunaInicial(int colunaInicial) {
		this.colunaInicial = colunaInicial;
		this.colunaAtual = colunaInicial;
	}

	public void setTituloRelatorio(String titulo, int colunasMerge) {
		HSSFCellStyle csHeader = stylerBuilder.builder().fontName(getDefaultFont()).fontHeightInPoints(getDefaultFontHeightInPointsTitulo()).bold().wrapText().fundoBranco().textoPreto().build();

		setCell(linhaAtual, colunaInicial, titulo, csHeader);
		getCell(sheet, linhaAtual, colunaInicial).getRow().setHeightInPoints(getDefaultRowHeightInPointsTitulo());
		sheet.addMergedRegion(new CellRangeAddress(linhaAtual, linhaAtual, colunaInicial, colunaInicial + colunasMerge - 1));
		for (int i = 0; i < colunasMerge; i++) {
			setCell(linhaAtual, colunaInicial + i, csHeader);
		}
		linhaAtual++;
		getCell(sheet, linhaAtual, colunaInicial).getRow().setHeightInPoints(3);
		linhaAtual++;

	}

	public void setInfoCabecalho(String label, Object dado) {
		setInfoCabecalho(label, null, dado, null, null);
	}

	public void setInfoCabecalho(String label, int mergeLabel, Object dado) {
		setInfoCabecalho(label, mergeLabel, dado, null, null);
	}

	public void setInfoCabecalho(String label, Object dado, int mergeDado) {
		setInfoCabecalho(label, null, dado, mergeDado, null);
	}

	public void setInfoCabecalho(String label, int mergeLabel, Object dado, int mergeDado) {
		setInfoCabecalho(label, mergeLabel, dado, mergeDado, null);
	}

	public void setInfoCabecalho(String label, Object dado, String formato) {
		setInfoCabecalho(label, null, dado, null, formato);
	}

	public void setInfoCabecalho(String label, int mergeLabel, Object dado, String formato) {
		setInfoCabecalho(label, mergeLabel, dado, null, formato);
	}

	public void setInfoCabecalho(String label, Object dado, int mergeDado, String formato) {
		setInfoCabecalho(label, null, dado, mergeDado, formato);
	}

	public void setInfoCabecalho(String label, Integer mergeLabel, Object dado, Integer mergeDado, String formato) {
		getCell(sheet, linhaAtual, colunaInicial).getRow().setHeightInPoints(18);

		HSSFCellStyle csLabel = stylerBuilder.builder().fontName(getDefaultFont()).fontHeightInPoints(9).bold().wrapText().fundo(
				(linhaAtual % 2 == modClaroCabecalho) ? stylerBuilder.fundoClaro() : stylerBuilder.fundoEscuro()).identacao(1).bordaFina().bordaBranca()
				.centralizaVertical().build();

		short alinhamento;
		if (dado instanceof Number) {
			alinhamento = stylerBuilder.direita();
		} else {
			alinhamento = stylerBuilder.esquerca();
			if (dado instanceof Date) {
				dado = DateUtils.format((Date) dado);
			}
		}
		HSSFCellStyle csDado = stylerBuilder.builder().cloneDoEstilo(csLabel).fontName(getDefaultFont()).fontHeightInPoints(10).alinhamento(alinhamento).formato(
				formato).build();

		int larguraColunaLabel = 1;

		if (mergeLabel != null && mergeLabel > 1) {
			larguraColunaLabel = mergeLabel;
			int colInicio = colunaAtual;
			int colFim = colInicio + mergeLabel - 1;
			sheet.addMergedRegion(new CellRangeAddress(linhaAtual, linhaAtual, colInicio, colFim));
			for (int i = 0; i < mergeLabel; i++) {
				setCell(linhaAtual, colInicio + i, csLabel);
			}
		}
		setCell(linhaAtual, colunaAtual, label, csLabel);

		if (dado != NOPRINT) {
			if (mergeDado != null && mergeDado > 1) {
				int colInicio = colunaAtual + larguraColunaLabel;
				int colFim = colInicio + mergeDado - 1;
				sheet.addMergedRegion(new CellRangeAddress(linhaAtual, linhaAtual, colInicio, colFim));
				for (int i = 0; i < mergeDado; i++) {
					setCell(linhaAtual, colInicio + i, csDado);
				}
			}
			setCell(linhaAtual, colunaAtual + larguraColunaLabel, dado, csDado);

		}
		linhaAtual++;
		if (linhaInicialDados < linhaAtual)
			linhaInicialDados = linhaAtual;
	}

	public void setLinhaInicialDados(int linhaInicialDados) {
		this.linhaInicialDados = linhaInicialDados;
	}

	public int cabecalhoDados(String tituloDados, Cabecalhos cabecalhos) {
		return cabecalhoDados(tituloDados, cabecalhos, -1);
	}

	public int cabecalhoDados(String tituloDados, Cabecalhos cabecalhos, int alturaCabecalhoInicial) {
		this.cabecalhos = cabecalhos;
		linhaInicialDados += getDefaultLinhasInicialDadosCabecalho();
		linhaAtual = linhaInicialDados;
		int totalColunas = 0;
		for (Cabecalho cabecalho : cabecalhos.colunas) {
			if (cabecalho.larguras == null) {
				totalColunas++;
			} else {
				totalColunas += cabecalho.larguras.length;
			}
		}
		sheet.addMergedRegion(new CellRangeAddress(linhaAtual, linhaAtual, colunaInicial, colunaInicial + totalColunas - 1));
		HSSFCellStyle csTituloDados = stylerBuilder.builder().fontName(getDefaultFont()).fontHeightInPoints(getDefaultFontHeightInPointsTituloCabecalho()).alinhaEsquerda().bold().fundoBranco().build();

		setCell(linhaAtual, colunaInicial, tituloDados, csTituloDados);
		getCell(sheet, linhaAtual, colunaInicial).getRow().setHeightInPoints(18);
		for (int i = colunaInicial; i < colunaInicial + totalColunas; i++) {
			setCell(linhaAtual, i, csTituloDados);
		}

		linhaAtual++;

		getCell(sheet, linhaAtual, colunaInicial).getRow().setHeightInPoints(3);
		linhaAtual++;
		colunaAtual = colunaInicial;

		if (cabecalhos.grupos != null && cabecalhos.grupos.size() > 0) {
			printLinhaCabecalhos(cabecalhos.grupos, alturaCabecalhoInicial);
		}
		int alturaLinhaCabecalho = printLinhaCabecalhos(cabecalhos.colunas, alturaCabecalhoInicial);

		getCell(sheet, linhaAtual, colunaInicial).getRow().setHeightInPoints(18);
		this.modClaroDado = (linhaAtual + 1) % 2;
		linhaInicialDados = linhaAtual;
		return alturaLinhaCabecalho;
	}

	private int printLinhaCabecalhos(List<Cabecalho> colunas, int alturaCabecalhoInicial) {
		
		
		int alturaLinhaCabecalho = alturaCabecalhoInicial > 0 ? alturaCabecalhoInicial : 1;
		
		StylerBuilder style = stylerBuilder.builder().fontName(getDefaultFont())
		  .fontHeightInPoints(10)
		  .bold()
		  .wrapText()
		  .textoBranco()
		  .bordaMedia()
		  .bordaBranca()
		  .centraliza()
		  .centralizaVertical();
		  
		
		for (Cabecalho cabecalho : colunas) {
			
			if (cabecalho.corFundo != null) {
				style.fundo(cabecalho.corFundo);
			} else {
				style.fundoSubTitulo();
			}
			
			HSSFCellStyle styleSubHeader = style.build(); 

			HSSFFont fontAtual = styleSubHeader.getFont(workbook);
			
			int maxDigitsFont = maxWidthFont(fontAtual.getFontName(), fontAtual.getFontHeight());

			
			int larguraTotal = 0;
			if (cabecalho.larguras == null) {
				setCell(linhaAtual, colunaAtual, cabecalho.label, styleSubHeader);
				larguraTotal += sheet.getColumnWidth(colunaAtual);
				colunaAtual++;
			} else {
				int rangeInicial = colunaAtual;
				setCell(linhaAtual, colunaAtual, cabecalho.label, styleSubHeader);
				for (int largura : cabecalho.larguras) {
					setCell(linhaAtual, colunaAtual, styleSubHeader);
					if (largura > 0) {
						int larguraCalculada = (int) ((largura * maxDigitsFont + 5) * 1.4);
						sheet.setColumnWidth(colunaAtual, larguraCalculada);
					}
					larguraTotal += largura;
					colunaAtual++;
				}
				int rangeFinal = colunaAtual - 1;
				if (rangeInicial < rangeFinal) {
					sheet.addMergedRegion(new CellRangeAddress(linhaAtual - 1, linhaAtual - 1, rangeInicial, rangeFinal));
					sheet.addMergedRegion(new CellRangeAddress(linhaAtual, linhaAtual, rangeInicial, rangeFinal));
				}
			}
			
			int alturaEstimada = (int) Math.ceil((double) ((String) cabecalho.label).length() / larguraTotal);
			
			if (alturaLinhaCabecalho < alturaEstimada) {
				alturaLinhaCabecalho = alturaEstimada;
			}
		}

		HSSFRow row = getCell(sheet, linhaAtual, colunaInicial).getRow();
		row.setHeightInPoints(15 * alturaLinhaCabecalho);

		linhaAtual++;
		colunaAtual = colunaInicial;
		return alturaLinhaCabecalho;
	}

	public int novaLinhaDados() {
		return novaLinhaDados(-1);
	}

	public int novaLinhaDados(int alturaLinhaInicial) {
		// verifica se te colunas unicas
		if (colunasUnique != null && colunasUnique.length > 0) {
			// verifica se a linha anterior tem os dados iguais aos da atual
			if (linhaIgual()) {
				// troca o estilo da linha atual para um "transparente"
				HSSFRow row = sheet.getRow(linhaAtual);
				for (int i = colunaInicial; i < colunaAtual; i++) {
					if (row.getCell(i) == null)
						continue;
					HSSFCellStyle csDado = row.getCell(i).getCellStyle();
					HSSFCellStyle csDadoTrasparente = csTransparente.get(csDado);
					if (csDadoTrasparente == null) {
						HSSFFont font = csDado.getFont(workbook);
						csDadoTrasparente = stylerBuilder.builder().cloneDoEstilo(csDado).fontName(font.getFontName()).fontHeightInPoints(
								font.getFontHeightInPoints()).corTexto(csDado.getFillForegroundColor()).build();
						csTransparente.put(csDado, csDadoTrasparente);
					}
					row.getCell(i).setCellStyle(csDadoTrasparente);
				}
			}
		}

		int alturaLinhaCalcuada = 0;
		if (alturaLinhaInicial > alturaLinhaAtual) {
			alturaLinhaAtual = alturaLinhaInicial;
		}

		if (alturaLinhaAtual > 1) {
			sheet.getRow(linhaAtual).setHeight((short) (sheet.getDefaultRowHeight() * (alturaLinhaAtual + 1)));
			alturaLinhaCalcuada = alturaLinhaAtual;
			alturaLinhaAtual = 1;
		}
		linhaAtual++;
		colunaAtual = colunaInicial;
		this.colunaDadoCabecalho = 0;
		getCell(sheet, linhaAtual, colunaInicial).getRow().setHeightInPoints(18);
		linhaInicialDados = linhaAtual;
		return alturaLinhaCalcuada;
	}
	
	public void corrigirAltura(int linhaInicial, int merge) {
		short menorAltura = 0;
		for (int i = 19 - 1 ; i < linhaAtual ; i += merge ) {
			menorAltura = Short.MAX_VALUE;
			for (int j = i; j < merge + i; j++) {
				short altura = sheet.getRow(j).getHeight();
				if (altura < menorAltura) {
					menorAltura = altura;
				}
			}
			for (int j = i; j < merge + i; j++) {
				sheet.getRow(j).setHeight(menorAltura);
			}
		}
	}

	public void gerarColunasVazias(int colunas) {
		for (int i = 0; i < colunas; i++) {
			setDado("");
		}
	}
	
	public void setDado(Object dado) {
		setDado(dado, null, null, 0, null, null, null, null);
	}

	public void setDado(Object dado, GipExcelStylerBuilder.Cor cor) {
		setDado(dado, null, null, 0, null, null, cor, null);
	}

	public void setDado(Object dado, boolean negrito) {
		setDado(dado, null, null, 0, null, negrito, null, null);
	}

	public void setDado(Object dado, Hyperlink link) {
		setDado(dado, link, null, 0, null, null, null, null);
	}

	public void setDado(Object dado, Alinhamento alinhamento) {
		setDado(dado, null, alinhamento, 0, null, null, null, null);
	}

	public void setDado(Object dado, Alinhamento alinhamento, GipExcelStylerBuilder.Cor fundoForcado) {
		setDado(dado, null, alinhamento, 0, null, null, fundoForcado, null);
	}

	public void setDado(Object dado, Alinhamento alinhamento, GipExcelStylerBuilder.Cor fundoForcado, GipExcelStylerBuilder.Cor fontColor) {
		setDado(dado, null, alinhamento, 0, null, null, fundoForcado, fontColor);
	}

	public void setDado(Object dado, Alinhamento alinhamento, boolean negrito, GipExcelStylerBuilder.Cor fundoForcado, GipExcelStylerBuilder.Cor fontColor) {
		setDado(dado, null, alinhamento, 0, null, negrito, fundoForcado, fontColor);
	}
	
	public void setDado(Object dado, int merge, TipoMerge tipoMerge) {
		setDado(dado, null, null, merge, tipoMerge, null, null, null);
	}

	public void setDado(Object dado, int merge, TipoMerge tipoMerge, boolean negrito) {
		setDado(dado, null, null, merge, tipoMerge, negrito, null, null);
	}

	public void setDado(Object dado, int merge, boolean negrito) {
		setDado(dado, null, null, merge, null, negrito, null, null);
		for (int i = 0; i < merge - 1; i++) {
			setDado("");
		}
	}

	public void setDado(Object dado, Hyperlink link, Alinhamento alinhamento, int merge, TipoMerge tipoMerge, Boolean negritoForcado, GipExcelStylerBuilder.Cor fundoForcado, GipExcelStylerBuilder.Cor fontColor) {

		Cabecalho cabecalhoDado = cabecalhos.colunas.get(colunaDadoCabecalho);
		
		short fundo = processarCor(fundoForcado);
		short corTexto = processarCorFont(link, fontColor);
		short alinhamentoEstilo = processarAlinhamento(dado, alinhamento);
		boolean sublinhado = link != null;
		boolean negrito = negritoForcado == null ? false : negritoForcado;
		processarMerge(merge, tipoMerge);
		String formato = processarFormato(dado, cabecalhoDado);

		HSSFCellStyle csDado = stylerBuilder.builder()
				.fontName(getDefaultFont())
				.fontHeightInPoints(10)
				.wrapText()
				.sublinhado(sublinhado)
				.bordaBranca()
				.bordaFina()
				.fundo(fundo)
				.corTexto(corTexto)
				.alinhamento(alinhamentoEstilo)
				.centralizaVertical()
				.formato(formato)
				.bold(negrito)
				.build();

		setCell(linhaAtual, colunaAtual, dado, link, csDado);

		processaCabecalho(dado, cabecalhoDado, csDado);
		processaAlturaQuebra(dado);

		colunaAtual++;
		colunaDadoCabecalho++;

		if (linhaInicialDados < linhaAtual) {
			linhaInicialDados = linhaAtual;
		}
	}
	
	public void setDado(Object dado, Alinhamento alinhamento, Merge merge) {
		setDado(dado, null, alinhamento , merge, false , null, null);
	}
	
	public void setDado(Object dado, Hyperlink link, Alinhamento alinhamento, Merge merge, Boolean negritoForcado, GipExcelStylerBuilder.Cor fundoForcado, GipExcelStylerBuilder.Cor fontColor) {
		
		Cabecalho cabecalhoDado = cabecalhos.colunas.get(colunaDadoCabecalho);
		
		short fundo = processarCor(fundoForcado);
		short corTexto = processarCorFont(link, fontColor);
		short alinhamentoEstilo = processarAlinhamento(dado, alinhamento);
		boolean sublinhado = link != null;
		boolean negrito = negritoForcado == null ? false : negritoForcado;
		processarMerge(merge);
		String formato = processarFormato(dado, cabecalhoDado);
		
		HSSFCellStyle csDado = stylerBuilder.builder()
		.fontName(getDefaultFont())
		.fontHeightInPoints(10)
		.wrapText()
		.sublinhado(sublinhado)
		.bordaBranca()
		.bordaFina()
		.fundo(fundo)
		.corTexto(corTexto)
		.alinhamento(alinhamentoEstilo)
		.centralizaVertical()
		.formato(formato)
		.bold(negrito)
		.build();
		
		setCell(linhaAtual, colunaAtual, dado, link, csDado);
		
		processaCabecalho(dado, cabecalhoDado, csDado);
		processaAlturaQuebra(dado);
		
		colunaAtual++;
		colunaDadoCabecalho++;
		
		if (linhaInicialDados < linhaAtual) {
			linhaInicialDados = linhaAtual;
		}
		
		processarMergeFinal(merge);
	}

	private void processarMergeFinal(Merge merge) {
		if (merge != null && merge.getTipo() != null) {
			TipoMerge tipoMerge = merge.getTipo();
			if (tipoMerge == TipoMerge.COLUNA || tipoMerge == TipoMerge.AMBOS) {
				for (int i = 0; i < merge.getCols() - 1; i++) {
					setDado("");
				}
			}
		}
	}

	private void processarMerge(Merge merge) {

		if (merge != null && merge.getTipo() != null) {

			TipoMerge tipoMerge = merge.getTipo();
			
			if (tipoMerge == TipoMerge.COLUNA) {

				int colInicio = colunaAtual;
				int colFim = colInicio + merge.getCols() - 1;

				sheet.addMergedRegion(new CellRangeAddress(linhaAtual, linhaAtual, colInicio, colFim));

				for (int i = 0; i < merge.getCols() ; i++) {
					setCell(linhaAtual, colInicio + i, null);
				}

			} else if (tipoMerge == TipoMerge.LINHA) {

				int linFim = linhaAtual;
				int linInicio = linFim - (merge.getRows() - 1);

				sheet.addMergedRegion(new CellRangeAddress(linInicio, linFim, colunaAtual, colunaAtual));

			} else if (tipoMerge == TipoMerge.AMBOS) {

				int colInicio = colunaAtual;
				int colFim = colInicio + merge.getCols() - 1;
				
				int linFim = linhaAtual;
				int linInicio = linFim - (merge.getRows() - 1);

				sheet.addMergedRegion(new CellRangeAddress(linInicio, linFim, colInicio, colFim));
			}
		}

	}

	private void processaCabecalho(Object dado, Cabecalho cabecalhoDado, HSSFCellStyle csDado) {
		if (cabecalhos != null) {
			
			if (cabecalhoDado.larguras != null && cabecalhoDado.larguras.length > 1) {
				sheet.addMergedRegion(new CellRangeAddress(linhaAtual, linhaAtual, colunaAtual, colunaAtual + cabecalhoDado.larguras.length - 1));
				for (int i = 0; i < cabecalhoDado.larguras.length; i++) {
					setCell(linhaAtual, colunaAtual + i, csDado);
				}
				colunaAtual += cabecalhoDado.larguras.length - 1;
			}

			if (cabecalhoDado.realizarTotalizacao) {
				if (dado != null) {
					cabecalhoDado.total += ((Number) dado).doubleValue();
				}
			}
		}
	}

	private void processaAlturaQuebra(Object dado) {
		if (dado instanceof String) {

			int larguraTotal = 0;
			int[] larguras = cabecalhos != null ? cabecalhos.colunas.get(colunaDadoCabecalho).larguras : new int[0];
			for (int i = 0; i < larguras.length; i++) {
				larguraTotal += larguras[i];
			}
			
			int quebras = 1;
			dado = (((String) dado).replaceAll("<br/>", "\n").trim());
			if (((String) dado).indexOf("\n") > -1) {
				quebras++;
				char[] caracteres = ((String) dado).toCharArray();
				for (char c : caracteres) {
					if (c == '\n') {
						quebras++;
					}
				}
			}
			
			int alturaEstimada = (int) Math.ceil(((double) ((String) dado).length()) / (larguraTotal - 5));
			if (quebras > alturaEstimada)
				alturaEstimada = quebras + 1;
			if (alturaEstimada > 1 && (((String) dado).length() > (larguraTotal - 5)))
				alturaEstimada = (int) Math.ceil(((double) ((String) dado).length() * 1.4) / (larguraTotal - 5));
			if (alturaLinhaAtual < alturaEstimada) {
				alturaLinhaAtual = alturaEstimada;
			}
		}
	}

	private Short processarCorFont(Hyperlink link, GipExcelStylerBuilder.Cor fontColor) {
		Short corTexto;
		if (fontColor != null) {
			corTexto = fontColor.getIndiceCor();
		} else {
			if (link != null) {
				corTexto = stylerBuilder.azul();
			} else {
				corTexto = stylerBuilder.preto();
			}
		}
		return corTexto;
	}

	private String processarFormato(Object dado, Cabecalho cabecalhoDado) {
		String formato = null;
		if (dado instanceof Date) {
			formato = "m/d/yy";
		} else if (dado instanceof Double || dado instanceof Float) {
			formato = cabecalhoDado.formato == null ? "#,##0.00" : cabecalhoDado.formato;
		} else if (dado instanceof Long || dado instanceof Integer || dado instanceof Short) {
			formato = cabecalhoDado.formato == null ? "#,##0" : cabecalhoDado.formato;
		}
		return formato;
	}

	private void processarMerge(int merge, TipoMerge tipoMerge) {

		if (merge > 1) {

			if (tipoMerge == null || tipoMerge == TipoMerge.COLUNA) {

				int colInicio = colunaAtual;
				int colFim = colInicio + merge - 1;

				sheet.addMergedRegion(new CellRangeAddress(linhaAtual, linhaAtual, colInicio, colFim));

				for (int i = 0; i < merge; i++) {
					setCell(linhaAtual, colInicio + i, null);
				}

			} else if (tipoMerge == TipoMerge.LINHA) {

				int linFim = linhaAtual;
				int linInicio = linFim - (merge - 1);

				sheet.addMergedRegion(new CellRangeAddress(linInicio, linFim, colunaAtual, colunaAtual));
			} 
		}
	}

	private Short processarAlinhamento(Object dado, Alinhamento alinhamento) {
		Short alinhamentoEstilo = stylerBuilder.centralizado();
		if (alinhamento != null) {
			switch (alinhamento) {
			case CENTRALIZADO:
				alinhamentoEstilo = stylerBuilder.centralizado();
				break;
			case DIREITA:
				alinhamentoEstilo = stylerBuilder.direita();
				break;
			}
		} else if (dado instanceof Number) {
			alinhamentoEstilo = stylerBuilder.direita();
		}
		return alinhamentoEstilo;
	}

	private short processarCor(GipExcelStylerBuilder.Cor fundoForcado) {
		short fundo;

		if (fundoForcado != null) {
			fundo = fundoForcado.getIndiceCor();
		} else {
			fundo = (linhaAtual % 2 == modClaroDado) ? stylerBuilder.fundoClaro() : stylerBuilder.fundoEscuro();
		}
		return fundo;
	}

	private boolean linhaIgual() {
		HSSFRow row = sheet.getRow(linhaAtual);
		HSSFRow rowAnterior = sheet.getRow(linhaAtual - 1);
		for (int col : colunasUnique) {
			Object value = getValue(row.getCell(col));
			Object valueAnterior = getValue(rowAnterior.getCell(col));
			if ((valueAnterior == null && value != null) || (valueAnterior != null && value == null)) {
				return false;
			}
			if (valueAnterior == value) {
				continue;
			}
			if (!valueAnterior.equals(value)) {
				return false;
			}
		}
		return true;
	}

	private Object getValue(HSSFCell cell) {
		if (cell == null)
			return null;
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		case HSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		default:
			return cell.getStringCellValue();
		}
	}

	public Hyperlink createLink(String url) {
		if (url == null || url.trim().length() == 0)
			return null;
		CreationHelper createHelper = workbook.getCreationHelper();
		Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);
		link.setAddress(url);
		return link;
	}

	public void total() {
		short fundo = (linhaAtual % 2 == modClaroDado) ? stylerBuilder.fundoClaro() : stylerBuilder.fundoEscuro();

		HSSFCellStyle csLabel = stylerBuilder.builder().fontName(getDefaultFont()).fontHeightInPoints(10).fundo(fundo).centraliza().centralizaVertical().bordaBranca()
				.bordaFina().bold().build();

		colunaAtual = colunaInicial;
		for (int i = 0; i < cabecalhos.colunas.size(); i++) {
			Cabecalho cabecalho = cabecalhos.colunas.get(i);
			String formato = cabecalho.formato == null ? "#,##0.00" : cabecalho.formato;
			HSSFCellStyle csDado = stylerBuilder.builder().fontName(getDefaultFont()).fontHeightInPoints(10).bold().fundo(fundo).alinhamento(cabecalho.alinhamento).bordaBranca()
					.bordaFina().centralizaVertical().formato(formato).build();
			if (i < cabecalhos.colunas.size() - 1 && cabecalhos.colunas.get(i + 1).realizarTotalizacao && !cabecalhos.colunas.get(i).realizarTotalizacao) {
				if (cabecalho.larguras != null) {
					sheet.addMergedRegion(new CellRangeAddress(linhaAtual, linhaAtual, colunaAtual, colunaAtual + cabecalho.larguras.length - 1));
				}
				setCell(linhaAtual, colunaAtual, cabecalhos.getLabelTotal(), csLabel);
			} else if (cabecalho.realizarTotalizacao) {
				if (cabecalho.larguras != null) {
					sheet.addMergedRegion(new CellRangeAddress(linhaAtual, linhaAtual, colunaAtual, colunaAtual + cabecalho.larguras.length - 1));
				}
				setCell(linhaAtual, colunaAtual, cabecalho.total, csDado);
				cabecalho.total = 0;
			}
			colunaAtual = colunaAtual + (cabecalho.larguras != null ? cabecalho.larguras.length : 1);
		}
		linhaAtual++;

	}

	public void inicioGrupo() {
		this.linhaInicialGrupo = linhaAtual;
	}

	public void fimGrupo() {
		sheet.groupRow(this.linhaInicialGrupo, linhaAtual - 1);
		sheet.setRowGroupCollapsed(this.linhaInicialGrupo, true);
		this.linhaInicialGrupo = -1;
	}

	public void setCell(int row, int col, Object value, Hyperlink link) {
		setCell(row, col, value, null, link, null);
	}

	public void setCell(int row, int col, Object value) {
		setCell(row, col, value, null, null, null);
	}

	public void setCell(int row, int col, Object value, Hyperlink link, Integer type) {
		setCell(row, col, value, type, link, null);
	}

	public void setCell(int row, int col, Object value, Integer type) {
		setCell(row, col, value, type, null, null);
	}

	public void setCell(int row, int col, Object value, Hyperlink link, HSSFCellStyle style) {
		setCell(row, col, value, null, link, style);
	}

	public void setCell(int row, int col, Object value, HSSFCellStyle style) {
		setCell(row, col, value, null, null, style);
	}

	public void setCell(int row, int col, HSSFCellStyle style) {
		setCell(row, col, null, null, null, style);
	}

	public void setCell(int row, int col, Hyperlink link, HSSFCellStyle style) {
		setCell(row, col, null, null, link, style);
	}

	public void setCell(int row, int col, Object value, Integer type, Hyperlink link, HSSFCellStyle style) {
		
		HSSFCell cell = getCell(sheet, row, col);
		
		if (value != null) {
			if (value instanceof Date) {
				cell.setCellValue((Date) value);
			} else if (value instanceof String && ((String) value).indexOf("<br/>") > -1) {
				cell.setCellValue(((String) value).replaceAll("<br/>", "\n").trim());
			} else if (value instanceof String) {
				cell.setCellValue(((String) value).trim());
			} else if (value instanceof Double) {
				cell.setCellValue((Double) value);
			} else if (value instanceof Float) {
				cell.setCellValue((Float) value);
			} else if (value instanceof Integer) {
				cell.setCellValue((Integer) value);
			} else if (value instanceof Long) {
				cell.setCellValue((Long) value);
			} else {
				cell.setCellValue(value.toString());
			}
		}
		
		if (link != null) {
			cell.setHyperlink(link);
		}
		
		if (type != null) {
			cell.setCellType(type);
		}
		
		if (style != null) {
			cell.setCellStyle(style);
		}
	}

	protected HSSFCell getCell(HSSFSheet sheet, int row, int col) {
		HSSFRow sheetRow = sheet.getRow(row);
		if (sheetRow == null)
			sheetRow = sheet.createRow(row);
		HSSFCell cell = sheetRow.getCell(col);
		if (cell == null)
			cell = sheetRow.createCell(col);
		return cell;
	}

	protected static class Cabecalho {
		protected String label;
		protected int[] larguras;
		protected boolean realizarTotalizacao;
		protected double total = 0;
		protected Alinhamento alinhamento;
		protected String formato;
		protected Short corFundo;

		public Cabecalho(String label, int[] largurasColunas, Alinhamento alinhamento, boolean realizarTotalizacao, String formato, Short corFundo) {
			this.label = label;
			this.larguras = largurasColunas;
			this.realizarTotalizacao = realizarTotalizacao;
			this.alinhamento = alinhamento;
			this.formato = formato;
			this.corFundo = corFundo;
		}
	}

	public static class Cabecalhos {
		protected List<Cabecalho> colunas = new ArrayList<Cabecalho>();
		protected List<Cabecalho> grupos = new ArrayList<Cabecalho>();
		protected Cabecalho grupoAtual = null;
		private String labelTotal;

		private Cabecalhos() {
		}

		public Cabecalhos addCabecalho(String label) {
			this.colunas.add(new Cabecalho(label, null, null, false, null, null));
			addColunaAoGrupoAtual(new int[] { label.length() });
			return this;
		}

		public Cabecalhos addCabecalho(String label, int... largurasColunas) {
			this.addCabecalho(label, null, largurasColunas);
			return this;
		}

		public Cabecalhos addCabecalho(String label, Short corFundo, Alinhamento alinhamento, boolean realizarTotalizacao, String formato, int... largurasColunas) {
			this.colunas.add(new Cabecalho(label, largurasColunas, alinhamento, realizarTotalizacao, formato, corFundo));
			addColunaAoGrupoAtual(largurasColunas);
			return this;
		}
		
		public Cabecalhos addCabecalho(String label, String formato, int... largurasColunas) {
			this.colunas.add(new Cabecalho(label, largurasColunas, null, false, formato, null));
			addColunaAoGrupoAtual(largurasColunas);
			return this;
		}

		public Cabecalhos addCabecalhoCentralizado(String label) {
			this.addCabecalhoCentralizado(label, null, null);
			return this;
		}

		public Cabecalhos addCabecalhoCentralizado(String label, String formato) {
			this.colunas.add(new Cabecalho(label, null, CENTRALIZADO, false, formato, null));
			addColunaAoGrupoAtual(new int[] { label.length() });
			return this;
		}

		public Cabecalhos addCabecalhoCentralizado(String label, int... larguraColunas) {
			this.addCabecalhoCentralizado(label, null, larguraColunas);
			return this;
		}
		
		public Cabecalhos addCabecalhoCentralizadoColorido(String label, Short corFundo,  int... larguraColunas) {
			this.colunas.add(new Cabecalho(label, larguraColunas, CENTRALIZADO, false, null, corFundo));
			addColunaAoGrupoAtual(larguraColunas);
			return this;
		}

		public Cabecalhos addCabecalhoCentralizado(String label, String formato, int... larguraColunas) {
			this.colunas.add(new Cabecalho(label, larguraColunas, CENTRALIZADO, false, formato, null));
			addColunaAoGrupoAtual(larguraColunas);
			return this;
		}

		public Cabecalhos addCabecalhoComTotal(String label) {
			this.colunas.add(new Cabecalho(label, null, null, true, null, null));
			addColunaAoGrupoAtual(new int[] { label.length() });
			return this;
		}

		public Cabecalhos addCabecalhoComTotal(String label, String formato) {
			this.colunas.add(new Cabecalho(label, null, null, true, formato, null));
			addColunaAoGrupoAtual(new int[] { label.length() });
			return this;
		}

		public Cabecalhos addCabecalho(String label, Alinhamento alinhamento, boolean total, int... largurasColunas) {
			this.addCabecalho(label, alinhamento, total, null, largurasColunas);
			return this;
		}

		public Cabecalhos addCabecalho(String label, Alinhamento alinhamento, boolean total, String formato, int... largurasColunas) {
			this.colunas.add(new Cabecalho(label, largurasColunas, alinhamento, total, formato, null));
			addColunaAoGrupoAtual(largurasColunas);
			return this;
		}

		public Cabecalhos addCabecalhoComTotal(String label, int... largurasColunas) {
			this.addCabecalhoComTotal(label, null, largurasColunas);
			return this;

		}

		public Cabecalhos addCabecalhoComTotal(String label, String formato, int... largurasColunas) {
			this.colunas.add(new Cabecalho(label, largurasColunas, null, true, formato, null));
			addColunaAoGrupoAtual(largurasColunas);
			return this;
		}
		
		public Cabecalhos addCabecalhoComTotal(String label, Alinhamento alinhamento, String formato, int... largurasColunas) {
			this.colunas.add(new Cabecalho(label, largurasColunas, alinhamento, true, formato, null));
			addColunaAoGrupoAtual(largurasColunas);
			return this;
		}

		public Cabecalhos addGrupo(String label, Short corFundo) {
			grupoAtual = new Cabecalho(label, new int[0], null, false, null, corFundo);
			grupos.add(grupoAtual);
			return this;
		}
		
		public Cabecalhos addGrupo(String label) {
			return addGrupo(label, null);
		}

		private void addColunaAoGrupoAtual(int... larguras) {
			if (grupoAtual != null) {
				int i = grupoAtual.larguras.length;
				grupoAtual.larguras = Arrays.copyOf(grupoAtual.larguras, grupoAtual.larguras.length + larguras.length);
				for (int j = 0; j < larguras.length; j++) {
					grupoAtual.larguras[j + i] = larguras[j];
				}
			}
		}

		public Cabecalhos setLabelTotal(String labelTotal) {
			this.labelTotal = labelTotal;
			return this;
		}

		protected String getLabelTotal() {
			return labelTotal == null ? "Total" : labelTotal;
		}
	}

	public static Cabecalhos buildCabecalho() {
		return new Cabecalhos();
	}

	public int maxWidthFont(String fontName, int height) {
		String key = fontName + "|" + height;
		Integer maxWidth;
		if (fontDetailsMap.containsKey(key)) {
			maxWidth = fontDetailsMap.get(key);
		} else {
			java.awt.Font f = new java.awt.Font(fontName, 0, height);
			FontRenderContext fontContect = new FontRenderContext(null, true, true);
			maxWidth = (int) f.getStringBounds("W", fontContect).getWidth();
			fontDetailsMap.put(key, maxWidth);
		}
		return maxWidth;
	}

	protected String getDefaultFont(){
		return "Verdana";
	}

	protected int getDefaultLinhasInicialDadosCabecalho() {
		return 2;
	}

	protected int getDefaultFontHeightInPointsTitulo(){
		return 14;
	}
	
	protected int getDefaultRowHeightInPointsTitulo(){
		return 18;
	}
	
	protected int getDefaultFontHeightInPointsTituloCabecalho() {
		return 14;
	}
	
}