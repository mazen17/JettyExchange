package org.example.exchange.model;

public class OrderRoundTrip {

  private Order order;
  private long requestTime;
  private long responseTime;
  private long timeOnServer;

  public long getRequestTime() {
    return requestTime;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public void setRequestTime(long requestTime) {
    this.requestTime = requestTime;
  }

  public long getResponseTime() {
    return responseTime;
  }

  public void setResponseTime(long responseTime) {
    this.responseTime = responseTime;
  }

  public long getTimeOnServer() {
    return timeOnServer;
  }

  public void setTimeOnServer(long timeOnServer) {
    this.timeOnServer = timeOnServer;
  }

  public long getRoundTrip() {
    if (requestTime != 0 && responseTime != 0) {
      return responseTime - requestTime;
    }
    return -1;
  }
}
