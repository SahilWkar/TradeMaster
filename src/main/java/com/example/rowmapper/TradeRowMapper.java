package com.example.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tradestore.dao.TradeDao;

public class TradeRowMapper implements RowMapper<TradeDao>{

	@Override
    public TradeDao mapRow(ResultSet rs, int rowNum) throws SQLException {
		TradeDao tradeDao = new TradeDao();
		
		tradeDao.setVersion(rs.getString("version"));    
		tradeDao.setBookId(rs.getString("bookid"));       
		tradeDao.setCounterPartyId(rs.getString("counterpartyid")); 
		tradeDao.setExpired(rs.getString("expired"));
		tradeDao.setTradeId(rs.getString("tradeId"));
		tradeDao.setMaturityDate(rs.getDate("maturitydate"));
		
        return tradeDao;
    }
	
}
