package com.tradestore.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tradestore.exception.TradeStoreException;
import com.tradestore.model.TradeResponse;
import com.tradestore.model.TradeStoreRequest;
import com.tradestore.service.TradeService;

@RestController
public class TradeStoreController {

		@Autowired
		TradeService tradeService;
	  
		@PostMapping("/addtrades")
		public ResponseEntity<TradeResponse> addTrades(@RequestBody TradeStoreRequest tradeStoreRequest) throws TradeStoreException {
			//return repository.save(newEmployee);
			TradeResponse tradeResponse=new TradeResponse();
			try{

				SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
				Date todayDate = dateFormatter.parse(dateFormatter.format(new Date()));

				if(dateFormatter.parse(tradeStoreRequest.getMaturityDate()).before(todayDate)) {
					tradeResponse.setRespMessage("Trade not allowed as maturity date is less than current date");
					tradeResponse.setRespStatus("SUCCESS");
				}
				else {
					tradeService.addtrade(tradeStoreRequest);
					tradeResponse.setRespMessage("Trade Added/updated succesfully");
					tradeResponse.setRespStatus("SUCCESS");
				}

			}
			catch(TradeStoreException e ) {
				e.printStackTrace();
				tradeResponse.setRespMessage(e.getMessage());
				tradeResponse.setRespStatus("FAILURE");
				//throw new TradeStoreException(e.getMessage());
			}
			catch(ParseException e) {
				e.printStackTrace();
				tradeResponse.setRespMessage("Invalid maturity date format.Please specify dd/mm/yyyy");
				tradeResponse.setRespStatus("FAILURE");
			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}

			return ResponseEntity.ok(tradeResponse);
		}

}
