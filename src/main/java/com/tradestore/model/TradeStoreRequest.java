package com.tradestore.model;

public class TradeStoreRequest {

private String tradeId;

private String version;

private String counterPartyId;




public String getTradeId() {
	return tradeId;
}

public void setTradeId(String tradeId) {
	this.tradeId = tradeId;
}

public String getVersion() {
	return version;
}

public void setVersion(String version) {
	this.version = version;
}

public String getCounterPartyId() {
	return counterPartyId;
}

public void setCounterPartyId(String counterPartyId) {
	this.counterPartyId = counterPartyId;
}

public String getBookId() {
	return bookId;
}

public void setBookId(String bookId) {
	this.bookId = bookId;
}

public String getMaturityDate() {
	return maturityDate;
}

public void setMaturityDate(String maturityDate) {
	this.maturityDate = maturityDate;
}

private String bookId;

private String maturityDate;

	
}
