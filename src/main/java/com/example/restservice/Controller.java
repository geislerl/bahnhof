package com.example.restservice;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.opencsv.exceptions.CsvValidationException;



@RestController
public class Controller {
	

	public static String roundDecimalUsingBigDecimal(String strValue, int decimalPlace) {
		return new BigDecimal(strValue).setScale(decimalPlace, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
	  }  
	
	@GetMapping(path="/api/v1/distance/{from}/{to}")
	public Distance distance_from_to(@PathVariable String from, @PathVariable String to) throws CsvValidationException, IOException
	{
	//Read .csv----------------------------------------------------------------------------------------------------------------------------------------------------
	String line="";
	String splitBy=";";
	int i=0;
	String[][] data=new String[6520][8];
	String Start[]= new String[8];
	String Ende[]= new String[8];
	try{
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\LisaG\\aufgabe_bahnhöfe\\complete\\D_Bahnhof_2020_alle.CSV"));

		while((line=br.readLine())!=null){
			String[] bahnhof = line.split(splitBy);
			for (int j=0;j<8;j++){
			data[i][j]=bahnhof[j];
			
			} 
		i++;
	}
br.close();
}
	catch(IOException e)
	{
		e.printStackTrace();
	}
   
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------
for (int k=0; k<6520;k++){
	if (data[k][1].equals(from)){
		for(int m=0; m<8;m++){
		Start[m]=data[k][m];
		System.out.println(Start[m]);
		}
	}
}
for (int l=0; l<6520;l++){
	if (data[l][1].equals(to)){
		for(int n=0;n<8;n++){
		Ende[n]=data[l][n];

		}
	}
}
		
Double lat1=Double.parseDouble(Start[6].replace(",","."));
Double lat2=Double.parseDouble(Ende[6].replace(",","."));
Double lon1=Double.parseDouble(Start[5].replace(",","."));
Double lon2=Double.parseDouble(Ende[5].replace(",","."));
//Double lat=(lat1+lat2)/2*(lon1-lon2);
//Double dx=111.3*Math.cos(lat)*(lon1-lon2);
//Double dy=111.3*(lat1-lat2);
Double dx=71.5*(lon1-lon2);
Double dy=111.3*(lat1-lat2);
String distance=String.valueOf(Math.sqrt(dx*dx+dy*dy));
//String distance=String.valueOf(111.324*Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1)));
String Distance_round=roundDecimalUsingBigDecimal(distance,0);
		return new Distance(Start[3],Ende[3],Distance_round,"km");
	}
	
}
