package org.example.exchange.start;

import org.example.exchange.server.ExchangeServer;
import org.example.exchange.server.ExchangeServlet;

public class StartExchangeServer {

  public static void main(String[] args) throws Exception {

    ExchangeServer server = new ExchangeServer(new ExchangeServlet());
    server.start();
    server.join();
  }
}