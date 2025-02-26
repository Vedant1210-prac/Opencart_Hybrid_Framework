package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	//DataProvider 1
	
	@DataProvider(name ="LoginData")
	public String [][] getData() throws IOException{
	String path = System.getProperty("user.dir")+"/testData//TestData.xlsx";
	
	ExcelUtils xlutils = new ExcelUtils(path);
	
	int totalRows = xlutils.getRowcount("Sheet1");
	int totalCell = xlutils.getCellcount("Sheet1", 1);
	
	String loginData[][] = new String[totalRows][totalCell];
	
	for(int i = 1; i<=totalRows ; i++) {
		
		for(int j = 0 ; j<totalCell; j++) {
			
			loginData[i-1][j]= xlutils.getCellData("Sheet1", i, j);
			
		}
		
	}
	return loginData;

}
	
	//DataProvide 2
	
}
