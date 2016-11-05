package org.example.exchange.client.normal;

import java.io.IOException;

import org.example.exchange.client.AbstractExchangeClient;
import org.example.exchange.client.OrderFactory;
import org.example.exchange.model.NormalOrder;
import org.example.exchange.model.Order;

public class NormalExchangeClient extends AbstractExchangeClient {

  public NormalExchangeClient(String address) {
    super(address);
  }
  
  @Override
  public void simulateOrders(int count) throws IOException, InterruptedException {
    for (int id = 0; id < count; id++) {
      NormalOrder order = new NormalOrder(id);
      OrderFactory.newRandomNormalOrder(order);
      process(order);
    }
  }

  @Override
  public String createOrderString(Order order) {
    NormalOrder normalOrder = (NormalOrder) order;

    String orderString = normalOrder.getId() + "," + normalOrder.getBank() + ","
                         + normalOrder.getCurrencyPair() + "," + normalOrder.getOrderType() + ","
                         + normalOrder.getPrice();
    return orderString;
  }
}