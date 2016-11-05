package org.example.exchange.client;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.stat.StatUtils;
import org.example.exchange.model.OrderRoundTrip;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MathStatisticsTest {

  Map<Integer, OrderRoundTrip> orderRoundTripMap;

  @Before
  public void setUp() throws Exception {
    orderRoundTripMap = new HashMap<Integer, OrderRoundTrip>();

    for (int i = 0; i < 3; i++) {
      OrderRoundTrip orderRoundTrip = new OrderRoundTrip();
      orderRoundTrip.setRequestTime(10);
      orderRoundTrip.setResponseTime(20);
      orderRoundTripMap.put(i, orderRoundTrip);
    }
  }

  @Test
  public void testCetRoundTripTimes() throws Exception {

    double[] roundTripTimes = MathStatistics.getRoundTripTimes(orderRoundTripMap);

    Assert.assertTrue(roundTripTimes.length == 3);
    Assert.assertTrue(StatUtils.sum(roundTripTimes) == 30d);
  }
}
