package br.com.pcmaker.spring.view.excel.support;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import br.com.pcmaker.spring.view.excel.support.GipExcelFormater.Alinhamento;

/**
 * Created with IntelliJ IDEA.
 * User: Y3OS
 * Date: 10/06/14
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
public class GipExcelStylerBuilder {

    private HSSFWorkbook workbook;
    Map<String,HSSFFont> mapFonts = new HashMap<String, HSSFFont>();
    Map<String,HSSFCellStyle> mapStyles = new HashMap<String, HSSFCellStyle>();
//    public  short CLARO =HSSFColor.CORAL.index;
//    public  short ESCURO =HSSFColor.CORNFLOWER_BLUE.index;
//    public short BEGE = HSSFColor.LIGHT_YELLOW.index;
    private  short SUBTITULO  ;
    private short BRANCO = HSSFColor.WHITE.index;
    private short PRETO = HSSFColor.BLACK.index;
    private short AZUL = HSSFColor.BLUE.index;
	private HSSFDataFormat dataFormat;


    public enum Cor {
    	
    	AZUL(HSSFColor.BLUE.index),
    	AZUL_CINZA(HSSFColor.BLUE_GREY.index),
    	AZUL_CLARO(HSSFColor.LIGHT_BLUE.index),
    	CLARO(HSSFColor.CORAL.index),
        ESCURO(HSSFColor.CORNFLOWER_BLUE.index),
        BEGE(HSSFColor.LIGHT_YELLOW.index),
        VERMELHO(HSSFColor.RED.index),
        VERMELHO_CLARO(HSSFColor.ROSE.index),
        AMARELO_CLARO(HSSFColor.LIGHT_YELLOW.index),
        AMARELO(HSSFColor.YELLOW.index),
        VERDE(HSSFColor.GREEN.index), 
        VERDE_CLARO(HSSFColor.LIGHT_GREEN.index), 
        PRETO(HSSFColor.BLACK.index), 
        CINZA(HSSFColor.GREY_40_PERCENT.index), 
        BRANCO(HSSFColor.WHITE.index);

        private short indiceCor;

        public short getIndiceCor(){
        	return indiceCor;
        }
        Cor(short indiceCor) {
            this.indiceCor=indiceCor;
        }
    }

    public GipExcelStylerBuilder(HSSFWorkbook workbook,short index, byte r, byte g, byte b){
        this.workbook=workbook;
        dataFormat = workbook.createDataFormat();
        HSSFPalette palette = workbook.getCustomPalette();
        palette.setColorAtIndex( Cor.CLARO.getIndiceCor(),(byte)220, (byte)230, (byte)241);
        palette.setColorAtIndex( Cor.ESCURO.getIndiceCor(),(byte)243, (byte)243, (byte)243);
        palette.setColorAtIndex( Cor.AMARELO_CLARO.getIndiceCor(),(byte)255, (byte)255, (byte)153);
        palette.setColorAtIndex( Cor.VERMELHO_CLARO.getIndiceCor(),(byte)255, (byte)192, (byte)192);
        palette.setColorAtIndex( Cor.AZUL_CLARO.getIndiceCor(),(byte) 0, (byte)135, (byte) 205);
        
        
        SUBTITULO = index;
        palette.setColorAtIndex(SUBTITULO, r, g, b);
    }

    public short fundoClaro(){
        return Cor.CLARO.getIndiceCor();
    }

    public short fundoBege(){
        return Cor.BEGE.getIndiceCor();
    }
    public short fundoEscuro(){
        return Cor.ESCURO.getIndiceCor();
    }

    public  short esquerca(){
        return HSSFCellStyle.ALIGN_LEFT;
    }
    public  short direita(){
        return HSSFCellStyle.ALIGN_RIGHT;
    }
    public  short centralizado(){
        return HSSFCellStyle.ALIGN_CENTER;
    }

    public StylerBuilder builder(){
        return new StylerBuilder();

    }

    public short azul() {
        return AZUL;
    }
    public short branco() {
        return BRANCO;
    }

    public short preto() {
        return PRETO;
    }

    public  class StylerBuilder{
        private String fontName;
        private short fontHeight;
        private boolean bold=false;
        private Short colorIndex;
        private Short borderColor;
        private Short borderStyle;
        private Short verticalAligment;
        private Boolean wrapText    ;
        private Short aligment;
        private String formato;
        private boolean underline;
        private Short fontColor;
        private HSSFCellStyle clone;
        private Short identacao;
        private Short bordaBaixo;
        private Short bordaBaixoCor;

        public StylerBuilder fontName(String fontName) {
            this.fontName=fontName;
            return this;
        }

        public StylerBuilder fontHeightInPoints(int fontHeight) {
            this.fontHeight=(short)fontHeight;
            return this;
        }
        public StylerBuilder fundo(short claroOuEscuro){
            this.colorIndex= claroOuEscuro;
            return this;
        }

        public StylerBuilder fundoSubTitulo(){
            this.colorIndex= SUBTITULO;
            return this;
        }
        public StylerBuilder fundoBranco(){
            this.colorIndex = HSSFColor.WHITE.index;
            return this;
        }
        public StylerBuilder textoBranco(){
            this.fontColor=HSSFColor.WHITE.index;
            return this;
        }
        public StylerBuilder textoAzul(){
            this.fontColor=AZUL;
            return this;
        }

        public StylerBuilder textoPreto(){
            this.fontColor=HSSFColor.BLACK.index;
            return this;
        }
        public StylerBuilder corTexto(Short corTexto) {
            this.fontColor=corTexto;
            return this;
        }
        public StylerBuilder bold(){
            this.bold=true;
            return this;
        }
        public StylerBuilder bold(boolean bold){
            this.bold=bold;
            return this;
        }
        public StylerBuilder wrapText(){
            wrapText=true;
            return this;
        }
        public StylerBuilder identacao(int identacao) {
            this.identacao = (short)identacao;
            return this;
        }
        public StylerBuilder  bordaBranca(){
            this.borderColor = BRANCO;
            return this;
        }
        public StylerBuilder  bordaFina(){
            this.borderStyle =  CellStyle.BORDER_THICK;
            return this;
        }

        public StylerBuilder bordaMedia(){
            this.borderStyle =  CellStyle.BORDER_MEDIUM;
            return this;
        }

        public StylerBuilder centralizaVertical(){
            this.verticalAligment=CellStyle.VERTICAL_CENTER;
            return this;
        }
        public StylerBuilder alinhamento( Short alinhamento) {
            this.aligment=alinhamento;
            return this;
        }
        
        public StylerBuilder alinhamento(Alinhamento alinhamento) {
        	if (alinhamento != null && alinhamento == Alinhamento.CENTRALIZADO) {
       			centraliza();
        	} else {
        		alinhaDireita();
        	}
        	return this;
        }

        public StylerBuilder centraliza(){
            this.aligment=CellStyle.ALIGN_CENTER;
            return this;
        }
        public StylerBuilder alinhaEsquerda(){
            this.aligment=CellStyle.ALIGN_LEFT;
            return this;
        }
        public StylerBuilder alinhaDireita(){
            this.aligment=CellStyle.ALIGN_RIGHT;
            return this;
        }
        public StylerBuilder formato(String formato) {
            this.formato = formato;
            return this;
        }

        public StylerBuilder cloneDoEstilo(HSSFCellStyle clone){
            this.clone = clone;
            return this;
        }
        public StylerBuilder sublinhado(){
            this.underline=true;
            return this;
        }
        public StylerBuilder sublinhado(boolean underline){
            this.underline=underline;
            return this;
        }
        public StylerBuilder bordaBaixoPreta() {
            this.bordaBaixo=HSSFBorderFormatting.BORDER_THIN;
            this.bordaBaixoCor=HSSFColor.BLACK.index;
            return this;
        }

        public HSSFCellStyle build(){
            HSSFFont font = null;
            String fontKey=null;
            if (fontName!=null) {
                fontKey = fontName+"|"+fontHeight+"|"+bold+"|"+underline+"|"+fontColor;
                font = mapFonts.get(fontKey);
                if (font==null){
                    font = workbook.createFont();
                    font.setFontName(fontName);
                    font.setFontHeightInPoints(fontHeight);
                    if (bold) {
                        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
                    } else {
                        font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
                    }
                    if (underline){
                        font.setUnderline(Font.U_SINGLE);
                    }
                    if (fontColor!=null){
                        font.setColor(fontColor);
                    }
                    mapFonts.put(fontKey,font);
                }
            }



            String styleKey = (fontKey==null?"NO_FONT":fontKey) + "|" + verticalAligment + "|" +aligment+ "|" + "|"+colorIndex+"|"+identacao+"|"+ borderColor + "|" + borderStyle +"|"+ wrapText + "|" +formato + "|" +bordaBaixo+"|"+bordaBaixoCor+"|"+ clone;
            HSSFCellStyle style = mapStyles.get(styleKey);
            if (style==null){
                style=workbook.createCellStyle();
                if (clone!=null) {
                    style.cloneStyleFrom(clone);
                }
                if (font!=null) {
                    style.setFont(font);
                }
                if (colorIndex!=null) {
                    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
                    style.setFillForegroundColor(colorIndex);
                }
                if (verticalAligment!=null){
                    style.setVerticalAlignment(verticalAligment);
                }
                if (aligment!=null){
                    style.setAlignment(aligment);
                }
                if (identacao!=null)
                    style.setIndention(identacao);
                if (borderColor!=null){
                    style.setBottomBorderColor(borderColor);
                    style.setTopBorderColor(borderColor);
                    style.setRightBorderColor(borderColor);
                    style.setLeftBorderColor(borderColor);
                } else {
                    if (bordaBaixoCor!=null) {
                        style.setBottomBorderColor(bordaBaixoCor);
                    }
                }
                if (borderStyle!=null){
                    style.setBorderBottom(borderStyle);
                    style.setBorderTop(borderStyle);
                    style.setBorderRight(borderStyle);
                    style.setBorderLeft(borderStyle);
                } else  {
                    if (bordaBaixo!=null) {
                        style.setBorderBottom(bordaBaixo);
                    }
                }
                if (wrapText!=null) {
                    style.setWrapText(wrapText);
                }
                if (formato!=null){
                    style.setDataFormat(dataFormat.getFormat(formato));
                }

                mapStyles.put(styleKey,style);
            }

            return style;

        }

    }


}
