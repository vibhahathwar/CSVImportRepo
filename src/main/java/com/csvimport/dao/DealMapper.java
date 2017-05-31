package com.csvimport.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csvimport.model.Deal;

public class DealMapper implements RowMapper{

	@Override
	public Deal mapRow(ResultSet rs, int rowNum) throws SQLException {
		Deal deal = new Deal();
		
		
		deal.setFromCurrency(rs.getString("fromCurrency"));
		
		
		return deal;
	
	}

}
