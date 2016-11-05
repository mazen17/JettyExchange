package org.example.exchange.model;

import java.math.BigDecimal;

public class CodedOrder implements Order {

  private static final byte BANK_MASK = (byte) 0b00000011;
  private static final byte CURRENCY_MASK = (byte) 0b00011100;

  private byte code;
  private BigDecimal price; //current price update
  private String orderType;
  private int id;

  public CodedOrder(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public int getCode() {
    return code;
  }

  public void setCode(byte code) {
    this.code = code;
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

  @Override
  public String getCurrencyPair() {
    int currencyPairId = (code & CURRENCY_MASK);
    String currencyPair = CurrenciesPairs.getCurrencyPair(currencyPairId);
    return currencyPair.substring(0, 3) + "/" + currencyPair.substring(3);
  }

  @Override
  public String getBank() {
    int bankId = (code & BANK_MASK);
    return Banks.values()[bankId].name();
  }
}
