package org.example.exchange.client;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.stat.StatUtils;
import org.example.exchange.model.OrderRoundTrip;

public class MathStatistics {

  public static Map<String, BigDecimal> getStatistics(double[] values) {

    Map<String, BigDecimal> statisticsMap = null;

    if (values != null) {
      statisticsMap = new HashMap<String, BigDecimal>();
      MathContext mathContext = new MathContext(2);

      BigDecimal mean = new BigDecimal(StatUtils.mean(values), mathContext);
      statisticsMap.put("mean", mean);
      BigDecimal std = new BigDecimal(Math.sqrt(StatUtils.variance(values)), mathContext);
      statisticsMap.put("std", std);
      BigDecimal max = new BigDecimal(StatUtils.max(values), mathContext);
      statisticsMap.put("max", max);
      BigDecimal min = new BigDecimal(StatUtils.min(values));
      statisticsMap.put("min", min);
    }
    return statisticsMap;
  }

  public static double[] getRoundTripTimes(Map<Integer, OrderRoundTrip> orderRoundTripMap) {
    int count = orderRoundTripMap.size();
    double[] d = new double[count];
    for (int id = 0; id < count; id++) {
      OrderRoundTrip orderRoundTrip = orderRoundTripMap.get(id);
      d[id] = orderRoundTrip.getRoundTrip();
    }
    return d;
  }

  public static void writeMathStatistics(Map<String, BigDecimal> statistics) {

    DecimalFormat df = new DecimalFormat();
    df.setMaximumFractionDigits(2);
    df.setMinimumFractionDigits(0);

    System.out.println("--------------------------------------------------------");
    System.out.println("General Statistics in milliseconds");
    System.out.println("Mean: " + df.format(statistics.get("mean")) + " Std: "
                       + df.format(statistics.get("std")) + " Min: " + df.format(statistics.get("min"))
                       + " Max: " + df.format(statistics.get("max")) + " Total time: "
                       + (df.format(OrderExchange.getTotalTime())));
    System.out.println("--------------------------------------------------------");
  }
}
