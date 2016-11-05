package org.example.exchange.client.coded;

import java.io.IOException;

import org.example.exchange.client.AbstractExchangeClient;
import org.example.exchange.client.OrderFactory;
import org.example.exchange.model.CodedOrder;
import org.example.exchange.model.Order;

public class CodedExchangeClient extends AbstractExchangeClient {

  public CodedExchangeClient(String address) {
    super(address);
  }

  public String createOrderString(Order order) {
    CodedOrder codedOrder = (CodedOrder) order;

    String orderString = codedOrder.getId() + "," + codedOrder.getCode() + "," + codedOrder.getOrderType()
                         + "," + codedOrder.getPrice();
    return orderString;
  }

  public void simulateOrders(int count) throws IOException, InterruptedException {
    for (int id = 0; id < count; id++) {
      CodedOrder order = new CodedOrder(id);
      OrderFactory.newRandomCodedOrder(order);

      process(order);
    }
  }
}
