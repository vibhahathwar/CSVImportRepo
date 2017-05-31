package com.csvimport.validator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import com.csvimport.model.Deal;

public class CommonUtils {
	static NamedParameterJdbcTemplate nameParameterJDBCTemplate;
	
	public static String checkExtension(String name){
		if(name.lastIndexOf(".")!=-1 && (name.lastIndexOf(".")!=0)){
			return name.substring(name.lastIndexOf(".")+1);
		}else{
			return "";
		}
	}
	
	
	public static List<Deal> readCsv(String filePath){
		List<Deal> list = new ArrayList<Deal>();
	
		
		BufferedReader buff = null;
		String line = "";
		String splitby = ",";
		try {
			buff = new BufferedReader(new FileReader(filePath));
			try {
				String filename = null;
				filename=filePath.substring(filePath.lastIndexOf("\\")+1);
				while((line = buff.readLine()) != null){
					String [] data = line.split(splitby);
					Deal deal = new Deal();
					deal.setDealId(data[0]);
					deal.setFromCurrency(data[1]);
						deal.setToCurrency(data[2]);
						deal.setDate(data[3]);
						deal.setAmount(Integer.valueOf(data[4]));
					
						deal.setFilename(filename);
						
					list.add(deal);
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(buff != null){
				try{
					buff.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		
		return list;
		
	}


	

	

}
