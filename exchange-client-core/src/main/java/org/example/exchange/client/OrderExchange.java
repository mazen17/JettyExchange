package org.example.exchange.client;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.jetty.client.ContentExchange;
import org.example.exchange.model.Order;
import org.example.exchange.model.OrderRoundTrip;

public class OrderExchange extends ContentExchange {

  public static final int COUNT = 10000;
  private int expiredConnections;
  private int failedConnections;
  private static final int HTTP_OK = 200;
  private static long simulationStartTime;
  private static long simulationEndTime;
  private static final Map<Integer, OrderRoundTrip> orderRoundTrips = new HashMap<Integer, OrderRoundTrip>();

  private final Order order;
  private OrderRoundTrip orderRoundTrip;

  public OrderExchange(Order order) {
    this.order = order;
  }

  public static Map<Integer, OrderRoundTrip> getOrderRoundTrips() {
    synchronized (orderRoundTrips) {
      return new HashMap<Integer, OrderRoundTrip>(orderRoundTrips);
    }
  }

  @Override
  protected void onExpire() {
    synchronized (orderRoundTrips) {
      expiredConnections++;
    }
  }

  @Override
  protected void onConnectionFailed(Throwable x) {
    failedConnections++;
  }

  @Override
  protected void onRequestCommitted() throws IOException {
    synchronized (orderRoundTrips) {
      long requestTime = new Date().getTime();

      if (orderRoundTrips.isEmpty()) {
        simulationStartTime = requestTime;
      }

      orderRoundTrip = new OrderRoundTrip();
      orderRoundTrip.setRequestTime(requestTime);
    }
  }

  protected void onResponseComplete() throws IOException {
    synchronized (orderRoundTrips) {
      Date responseDate = new Date();

      if (orderRoundTrips.size() == COUNT - expiredConnections - failedConnections - 1) {
        simulationEndTime = responseDate.getTime();
      }

      int status = getResponseStatus();
      if (status == HTTP_OK) {
        long responseTimeLong = responseDate.getTime();

        String[] split = getResponseContent().split(";");

        orderRoundTrip.setResponseTime(responseTimeLong);
        orderRoundTrip.setTimeOnServer(Long.valueOf(split[1]));
        orderRoundTrip.setOrder(order);

        orderRoundTrips.put(order.getId(), orderRoundTrip);
      }
    }
  }

  public static long getTotalTime() {
    synchronized (orderRoundTrips) {
      return simulationEndTime - simulationStartTime;
    }
  }

  public static int getOrderRoundTripCount() {
    synchronized (orderRoundTrips) {
      return orderRoundTrips.size();
    }
  }
}