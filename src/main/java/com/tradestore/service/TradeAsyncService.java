package com.tradestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tradestore.repository.TradeRepository;

@Component
public class TradeAsyncService {
	
	@Autowired
	TradeRepository tradeRepository;
	
	@Scheduled(fixedRate = 500000)
	public boolean updateTrades() {
		
		try {
			int updatecount=tradeRepository.updateExpDateTrade();
			System.out.println("Updated count is "+ updatecount);
		} catch (Exception e) {		
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
