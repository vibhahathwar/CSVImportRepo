package com.csvimport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csvimport.dao.DealDao;

@Service
public class DealServiceImpl implements DealService{

	@Autowired
	DealDao dealdao;
	@Override
	public void process(List<String> filePath) {
		// TODO Auto-generated method stub
		dealdao.process(filePath);
	}
	

}
