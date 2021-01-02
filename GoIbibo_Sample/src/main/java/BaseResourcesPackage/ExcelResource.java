package BaseResourcesPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelResource
{
public static void Excelconfig() throws IOException
{
    File srcFile = new File("F://DataSheet.xlsx");
	FileInputStream fis = new FileInputStream(srcFile);
	XSSFWorkbook Wrkbook =new XSSFWorkbook(fis);
	XSSFSheet SheetIndex = Wrkbook.getSheetAt(0);
  int RowsCount=SheetIndex.getPhysicalNumberOfRows();
  System.out.println("Rows kooo"+RowsCount);
for(int i=0;i<RowsCount;i++)
{
	XSSFRow RowData =SheetIndex.getRow(i);
	int CellsCountorcolumnscount = RowData.getPhysicalNumberOfCells();
		for(int j=0;j<CellsCountorcolumnscount;j++)
		{
			System.out.print("||"+RowData.getCell(j).getStringCellValue());
		}
		System.out.println();
}
Wrkbook.close(); 
//System.out.println("value 1:"+RowData);
} 
}