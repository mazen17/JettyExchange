package org.example.exchange.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Map;

import org.eclipse.jetty.client.Address;
import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.example.exchange.model.Order;
import org.example.exchange.model.OrderRoundTrip;

public abstract class AbstractExchangeClient extends HttpClient {

  private final String address;

  public AbstractExchangeClient(String address) {
    this.address = address;
  }

  public final void process(Order order) throws IOException, InterruptedException {
    ContentExchange exchange = prepareExchange(order);
    send(exchange);
  }

  private ContentExchange prepareExchange(Order order) throws UnsupportedEncodingException {
    ContentExchange exchange = new OrderExchange(order);
    String orderString = createOrderString(order);
    configureExchange(exchange, orderString);
    return exchange;
  }

  private void configureExchange(ContentExchange exchange, String orderString) throws UnsupportedEncodingException {
    exchange.setMethod("POST");
    exchange.setAddress(new Address(address, 8080));
    exchange.setRequestURI("/");
    exchange.setRequestContentType("text/plain");
    exchange.setRequestContentSource(new ByteArrayInputStream(orderString.getBytes("UTF-8")));
    exchange.setRequestHeader("Content-Length", String.valueOf(orderString.length()));
  }

  public final void waitForDone(int count) throws InterruptedException {
    while (OrderExchange.getOrderRoundTripCount() != count) {
      Thread.sleep(100);
    }
  }

  public final void postProcess() {
    Map<Integer, OrderRoundTrip> orderRoundTripMap = OrderExchange.getOrderRoundTrips();
    double[] d = MathStatistics.getRoundTripTimes(orderRoundTripMap);
    Map<String, BigDecimal> statistics = MathStatistics.getStatistics(d);
    MathStatistics.writeMathStatistics(statistics);
    TextWriter.writeCsvFromHashMap(orderRoundTripMap);
  }

  public abstract void simulateOrders(int count) throws IOException, InterruptedException;

  public abstract String createOrderString(Order order);

}
