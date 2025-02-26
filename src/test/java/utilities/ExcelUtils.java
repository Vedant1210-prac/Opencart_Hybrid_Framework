package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	private static final int rownum = 0;
	public FileInputStream fi;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	public String path;
	
	public ExcelUtils(String path) {
		this.path = path;
	}

	public int getRowcount(String sheetName) throws IOException {
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet= wb.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}
	
	public int getCellcount(String sheetName, int rowNum) throws IOException {
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
		
	}
	
	public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException {
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		
		String Data;
		try {
			Data = cell.toString();
		}catch(Exception e) {
			Data = "";
		}
		wb.close();
		fi.close();
		return Data;
		
		
		
	}
}
