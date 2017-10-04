package br.com.pcmaker.spring.view.excel.support;

import java.io.Serializable;

public class Merge implements Serializable {

	private static final long serialVersionUID = 1L;

	private int rows = 1;

	private int cols = 1;

	public Merge(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public GipExcelFormater.TipoMerge getTipo() {

		if (rows > 1 && cols == 1) {
			return GipExcelFormater.TipoMerge.LINHA;
		} else if (rows == 1 && cols > 1) {
			return GipExcelFormater.TipoMerge.COLUNA;
		} else if (rows > 1 && cols > 1) {
			return GipExcelFormater.TipoMerge.AMBOS;
		} else {
			return null;
		}
	}

}
