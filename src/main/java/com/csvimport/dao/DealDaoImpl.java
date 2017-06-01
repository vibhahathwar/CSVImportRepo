package com.csvimport.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import com.csvimport.model.Deal;
import com.csvimport.validator.CommonUtils;

@Component
public class DealDaoImpl implements DealDao{
	
	public static final Logger logger = Logger.getLogger(DealDaoImpl.class);
	
	NamedParameterJdbcTemplate nameParameterJDBCTemplate;
	
	@Autowired
	public void setNameParameterJDBCTemplate(
			NamedParameterJdbcTemplate nameParameterJDBCTemplate) {
		this.nameParameterJDBCTemplate = nameParameterJDBCTemplate;
	}
	
	public void process(List<String> filesPath) {
		List<Deal> list = new ArrayList<Deal>();
		for(String filepath : filesPath){
			if(CommonUtils.checkExtension(filepath).equals("csv")){
				
				list.addAll(CommonUtils.readCsv(filepath));
			}
		}
		try {
			logger.info("upload data into database");
			importData(list);
		} catch (SQLException e) {
			
			logger.error(e.getMessage());
		}		
	}
	
	
	public void importData(List<Deal> list) throws SQLException{
		logger.info("jdbc call to insert data.");
		String sql = "insert into DealDetails(FileName,DealId,fromCurrency,toCurrency,dealdate,Amount) values (:filename,:dealId,:fromCurrency,:toCurrency,:date,:amount)";
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list.toArray());
		nameParameterJDBCTemplate.batchUpdate(sql, batch);
		
	}	
		
	}

