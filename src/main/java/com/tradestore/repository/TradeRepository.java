package com.tradestore.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.rowmapper.TradeRowMapper;
import com.tradestore.dao.TradeDao;
import com.tradestore.model.TradeStoreRequest;

@Repository
public class TradeRepository {
	
	@Autowired
	JdbcTemplate jdbctemplate;
	
	public List<TradeDao> getTradeRecord(String tradeId){
		String query = "SELECT * FROM trade_log WHERE tradeid = ?";
		List<TradeDao> listtradeDao=jdbctemplate.query(query, new Object[] { tradeId }, new TradeRowMapper());
		//TradeDao tradeDao = jdbctemplate.queryForObject(
		  //query, new Object[] { tradeId }, new TradeRowMapper());
		return listtradeDao;
	}
	
	
	 public int updateTradeRecord(TradeStoreRequest tradeStoreRequest){
	      String SQL = "update trade_log set bookid = ?,counterpartyid=?,maturitydate=? where tradeid = ?";
	      Date maturitydate=null;
			 try {
					 maturitydate=new SimpleDateFormat("dd/MM/yyyy").parse(tradeStoreRequest.getMaturityDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    
	      
	      int count=jdbctemplate.update(SQL, tradeStoreRequest.getBookId(), tradeStoreRequest.getCounterPartyId(),
	    		  maturitydate,tradeStoreRequest.getTradeId());
	     // System.out.println("Updated Record with ID = " + id );
	      return count;
	   }
	 
	 public int addTradeRecord(TradeStoreRequest tradeStoreRequest) {
		 Date maturitydate=null;
		 try {
				 maturitydate=new SimpleDateFormat("dd/MM/yyyy").parse(tradeStoreRequest.getMaturityDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		 
		 int  insertcount =jdbctemplate.update(
		      "INSERT INTO trade_log VALUES (?,?,?,?,?,?,?)", tradeStoreRequest.getTradeId(),tradeStoreRequest.getVersion(),
		      tradeStoreRequest.getCounterPartyId(),
		      tradeStoreRequest.getBookId(),maturitydate,new Date(),'Y');
		  return insertcount;
		}
	 

	 public int updateExpDateTrade(){
	    
		 String SQL = "update trade_log set expired= 'N'  where maturitydate is not null and  maturitydate< sysdate";
	     	      
	      int count=jdbctemplate.update(SQL);
	     // System.out.println("Updated Record with ID = " + id );
	      return count;
	   }
}
