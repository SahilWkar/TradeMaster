package com.example.demo;

import java.net.URL;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tradestore.exception.TradeStoreException;
import com.tradestore.model.TradeResponse;
import com.tradestore.service.TradeAsyncService;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TradeStoreApplicationTests {

		@LocalServerPort
		private int port;

		
		
		@Autowired
	    private TestRestTemplate restTemplate;
		
		@Autowired
		private TradeAsyncService tradeAsyncService;

	    @Test
	    public void addtrade() throws Exception {
	    
	    //	ResponseEntity<TradeResponse> tradeResponse=restTemplate.getForEntity(new URL("http://localhost:"+ port+ "/addtrades").toString(), TradeResponse.class);
	    	
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        JSONObject jsontradeRequest = new JSONObject();
	        jsontradeRequest.put("tradeId", "3");
	        jsontradeRequest.put("version", "2");
	        jsontradeRequest.put("counterPartyId", "CP-12");
	        jsontradeRequest.put("bookId", "B1");
	        jsontradeRequest.put("maturityDate", "15/05/2021");
	        
	        HttpEntity<String> tradeRequest = new HttpEntity<String>(jsontradeRequest.toString(), headers);
	        ResponseEntity<TradeResponse> tradeResponse= restTemplate.postForEntity(new URL("http://localhost:"+ port+ "/addtrades").toString(), tradeRequest, TradeResponse.class);
	    	
	        assertEquals("Trade Added/updated succesfully", tradeResponse.getBody().getRespMessage(),"Trade should be added succesfully");

	       
	        
	    }
	    
	    @Test
	    public void checkforMaturityDate() throws Exception {
	    
	    //	ResponseEntity<TradeResponse> tradeResponse=restTemplate.getForEntity(new URL("http://localhost:"+ port+ "/addtrades").toString(), TradeResponse.class);
	    	
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        JSONObject jsontradeRequest = new JSONObject();
	        jsontradeRequest.put("tradeId", "3");
	        jsontradeRequest.put("version", "2");
	        jsontradeRequest.put("counterPartyId", "CP-56");
	        jsontradeRequest.put("bookId", "B1");
	        jsontradeRequest.put("maturityDate", "20/05/2020");
	        
	        HttpEntity<String> tradeRequest = new HttpEntity<String>(jsontradeRequest.toString(), headers);
	        
	        ResponseEntity<TradeResponse> tradeResponse= restTemplate.postForEntity(new URL("http://localhost:"+ port+ "/addtrades").toString(), tradeRequest, TradeResponse.class);
	    	
	        assertEquals("Trade not allowed as maturity date is less than current date", tradeResponse.getBody().getRespMessage(),"Trade should not be allowed as maturity date is less than current date");

	    }
	    
	    @Test
	    public void updateExistingTrade() throws Exception {
	    
	    //	ResponseEntity<TradeResponse> tradeResponse=restTemplate.getForEntity(new URL("http://localhost:"+ port+ "/addtrades").toString(), TradeResponse.class);
	    	
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        JSONObject jsontradeRequest = new JSONObject();
	        jsontradeRequest.put("tradeId", "3");
	        jsontradeRequest.put("version", "2");
	        jsontradeRequest.put("counterPartyId", "CP-57");
	        jsontradeRequest.put("bookId", "B1");
	        jsontradeRequest.put("maturityDate", "20/06/2021");
	        
	        HttpEntity<String> tradeRequest = new HttpEntity<String>(jsontradeRequest.toString(), headers);
	        
	        ResponseEntity<TradeResponse> tradeResponse= restTemplate.postForEntity(new URL("http://localhost:"+ port+ "/addtrades").toString(), tradeRequest, TradeResponse.class);
	    	
	        assertEquals("Trade Added/updated succesfully", tradeResponse.getBody().getRespMessage(),"Trade should be updated succesfully if same version already exists");

	    }
	    
    
	    @Test
	    public void rejectInvalidTrade() throws Exception {
	    
	    		HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_JSON);
		        JSONObject jsontradeRequest = new JSONObject();
		        jsontradeRequest.put("tradeId", "3");
		        jsontradeRequest.put("version", "1");
		        jsontradeRequest.put("counterPartyId", "CP-13");
		        jsontradeRequest.put("bookId", "B1");
		        jsontradeRequest.put("maturityDate", "20/05/2021");
		        
		        HttpEntity<String> tradeRequest  = new HttpEntity<String>(jsontradeRequest.toString(), headers);
		        
		        ResponseEntity<TradeResponse> tradeResponse= restTemplate.postForEntity(new URL("http://localhost:"+ port+ "/addtrades").toString(), tradeRequest, TradeResponse.class);
		    	
		      assertEquals("Specfied Version is less than current trade version", tradeResponse.getBody().getRespMessage(),"Trade should be rejected");
		        
		       // assertThrows(com.tradestore.exception.TradeStoreException.class, 
		        //		()->restTemplate.postForEntity(new URL("http://localhost:"+ port+ "/addtrades").toString(), tradeRequest, TradeResponse.class),
		        	//	  "If trade version is small service should reject trade and throw exception");
	    }
	    
	    @Test
	    public void  updateExpireFlagForTrades() throws Exception  {
	    	assertEquals(true ,tradeAsyncService.updateTrades(),"Expiry flag should be updated for trades crossing maturity date");
	   }

	    
}
