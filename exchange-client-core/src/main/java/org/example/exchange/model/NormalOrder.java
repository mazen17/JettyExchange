package org.example.exchange.model;

import java.math.BigDecimal;

public class NormalOrder implements Order {

  private String currencyPair; //for example "EUR/USD"
  private String bank; //source bank who published the price
  private BigDecimal price; //current price update
  private String orderType;
  private int id;

  public NormalOrder(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getCurrencyPair() {
    return currencyPair;
  }

  public void setCurrencyPair(String tradingPair) {
    this.currencyPair = tradingPair;
  }

  public String getBank() {
    return bank;
  }

  public void setBank(String bank) {
    this.bank = bank;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }
}
