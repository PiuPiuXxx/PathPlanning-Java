package excel;
/**
 * 读数据文件
 * @author chengweishao
 *
 */

import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException;

public class ReadExcel {
	
	private int[][] map;
	
	public int[][] getCellinfo() {
		return map;
	}

	public void showMap() {
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public void setCellinfo(int[][] cellinfo) {
		this.map = cellinfo;
	}

	public ReadExcel(File file) {  
        try {  
            // 打开excel  
            InputStream is = new FileInputStream(file.getAbsolutePath());  
            // 得到工作表  
            Workbook wb = Workbook.getWorkbook(is);  
            // 得到页数
            int sheet_size = wb.getNumberOfSheets();  
 
            // 页标签  
            Sheet sheet = wb.getSheet(0);  
            map=new int[sheet.getRows()][sheet.getColumns()];

            for(int i=0;i<sheet.getColumns();i++) {
            	    for(int j=0;j<sheet.getRows();j++) {
            		    int Cellinfo=Integer.parseInt(sheet.getCell(i,j).getContents());
            		    map[j][i]=Cellinfo;
                }
            }
             
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (BiffException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  	

//	public static void main(String[] args) {
//		File file=new File("/Users/chengweishao/PathPlanning/lab.xls");
//		ReadExcel e=new ReadExcel(file);
//		
//		int[][] cellinfo = e.getCellinfo();
//		e.showMap();
//	}
}
