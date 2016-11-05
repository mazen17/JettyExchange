package org.example.exchange.client.start;

import org.example.exchange.client.AbstractExchangeClient;
import org.example.exchange.client.OrderExchange;
import org.example.exchange.client.coded.CodedExchangeClient;
import org.example.exchange.client.normal.NormalExchangeClient;

public class StartExchangeClient {

  public static void main(String[] args) throws Exception {

    AbstractExchangeClient client;
    String address = args[0];

    if (args.length == 2 && args[1].equals("coded")) {
      client = new CodedExchangeClient(address);
    }
    else {
      client = new NormalExchangeClient(address);
    }

    client.start();
    System.out.println("The client was started.");
    int count = OrderExchange.COUNT;
    client.simulateOrders(count);
    System.out.println("The simulation was done.");

    client.waitForDone(count);
    client.postProcess();

  }
}
