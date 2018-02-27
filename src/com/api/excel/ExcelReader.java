package com.api.excel;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelReader {
	
	  public static void main(String[] args) {
		  String[][] data = getExcelData("TestData/exceldata.xls","sheet1");
		  for(int i=0;i<data.length;i++){
			  for(int j=0;j<data[i].length;j++){  
                  
	                System.out.print(data[i][j]+"\n");
			  }
		  }
	}
	
	

	/** 
     * ��ȡexcel�ļ���excel�в����ϲ���Ԫ�� 
     * @param path 
     * @param sheetName 
     * excel���Ҫע��ÿ�е����һ����Ϊ�� 
     * ���ҪΪ�գ�����Ҫ�ֶ������д�ַ���Ȼ���ٰ�del��ɾ���ſ��� 
     * Ŀǰû�ҵ��������취��������������ҽ���취 
     * @return 
     */  
    @SuppressWarnings("resource")
	public static String[][] getExcelData(String path, String sheetName) {  
        try {  
            File file = new File(path);  
            FileInputStream fis = new FileInputStream(file);  
            POIFSFileSystem POIStream = new POIFSFileSystem(fis);  
            HSSFWorkbook workBook = new HSSFWorkbook(POIStream);  
            //�õ�������  
            HSSFSheet sheet1 = workBook.getSheet(sheetName);  
            //�õ�������  
            int rowNum = sheet1.getLastRowNum();  
            List<String[]> results = new ArrayList<String[]>();  
            for (int i=1;i<=rowNum;i++){  
                //��ǰ��  
                HSSFRow row = sheet1.getRow(i);  
                int colNum = row.getLastCellNum();  
                String[] data = new String[colNum];  
                //��ǰ��������  
                for (int j = 0; j < colNum; j++) {  
                    try {  
                        data[j] = getCellValue(row.getCell(j));  
                    }catch (NullPointerException e){ //�����Ԫ��Ϊ�յ�ʱ���������������  
                        data[j] = "";  
                    }  
                }  
                //��data[]��������ݴ���list<[]>��  
                results.add(data);  
            }  
            fis.close();  
  
            String[][] returnArray = new String[results.size()][rowNum];  
            for (int i = 0; i < returnArray.length; i++) {  
                returnArray[i] = (String[]) results.get(i);  
            }  
            return returnArray;  
        }catch (Exception e){  
            return null;  
        }  
    } 
	
	
	 /** 
     * ��Excel�ĸ�����Ԫ��ĸ�ʽ�����жϲ�ת�� 
     */  
    public static String getCellValue(HSSFCell cell) {  
        String cellValue = "";  
        DecimalFormat df = new DecimalFormat("#");  
        switch (cell.getCellType()) {  
            case HSSFCell.CELL_TYPE_STRING:  
                cellValue =cell.getRichStringCellValue().getString().trim();  
                break;  
            case HSSFCell.CELL_TYPE_NUMERIC:  
                cellValue =df.format(cell.getNumericCellValue()).toString();  
                break;  
            case HSSFCell.CELL_TYPE_BOOLEAN:  
                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();  
                break;  
            case HSSFCell.CELL_TYPE_FORMULA:  
                cellValue =cell.getCellFormula();  
                break;  
            default:  
                cellValue = "";  
        }  
        return cellValue;  
    }  
}
