package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.tradestore.service.TradeAsyncService;

@SpringBootApplication
@ComponentScan({"com.tradestore.controller","com.tradestore.repository","com.tradestore.service"})
//@EnableAsync
@EnableScheduling
public class TradeStoreApplication {

	@Autowired
	TradeAsyncService tradeAsyncService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(TradeStoreApplication.class, args);
	}
	
//		@Override
//	  public void run(String... args) throws Exception {
//		tradeAsyncService.updateTrades();
//		}

}
