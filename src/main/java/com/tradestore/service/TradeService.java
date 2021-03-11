package com.tradestore.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradestore.dao.TradeDao;
import com.tradestore.exception.TradeStoreException;
import com.tradestore.model.TradeStoreRequest;
import com.tradestore.repository.TradeRepository;

@Service
public class TradeService {

	
	@Autowired
	TradeRepository tradeRepository;
	public void addtrade(TradeStoreRequest tradeStoreRequest) throws TradeStoreException{
		
		//check for trade id and version in ddatabase 
		String tradeid=tradeStoreRequest.getTradeId();
		TradeDao tradeDao=null;
		System.out.println("tradeid is "+ tradeid);
		
		 
		
		List<TradeDao> tradeDaoList=tradeRepository.getTradeRecord(tradeid);
		if(tradeDaoList!=null && tradeDaoList.size()>0) {
			tradeDao=tradeDaoList.get(0);
		}
		
		if(tradeDao==null) {
			tradeRepository.addTradeRecord(tradeStoreRequest);
		}
		else if(Integer.valueOf(tradeStoreRequest.getVersion())==Integer.valueOf(tradeDao.getVersion())) {
			//if if version is same override the existing record
			tradeRepository.updateTradeRecord(tradeStoreRequest);
		}
		else if(Integer.valueOf(tradeStoreRequest.getVersion())<Integer.valueOf(tradeDao.getVersion())){
			throw new TradeStoreException("Specfied Version is less than current trade version");
		}	
		
		
		
	}

}
