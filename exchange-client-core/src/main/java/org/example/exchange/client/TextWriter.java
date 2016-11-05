package org.example.exchange.client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import org.example.exchange.model.Order;
import org.example.exchange.model.OrderRoundTrip;

public class TextWriter {

  public static void writeCsvFromHashMap(Map<Integer, OrderRoundTrip> orderRoundTrips) {
    try {
      DateFormat df = new SimpleDateFormat("yyyy.MM.dd:HH.mm.ss.SSS");

      File file = new File("c:/data.csv");

      if (!file.exists()) {
        file.createNewFile();
      }

      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);

      bw.write("ID,Bank, Currencypair,Request Time,Response Time,Round Trip, Time On Server");
      bw.newLine();

      for (int id = 0; id < orderRoundTrips.size(); id++) {
        OrderRoundTrip orderRoundTrip = orderRoundTrips.get(id);
        Order order = orderRoundTrip.getOrder();
        bw.write(id + "," + order.getBank() + "," + order.getCurrencyPair() + ","
                 + df.format(orderRoundTrip.getRequestTime()) + ","
                 + df.format(orderRoundTrip.getResponseTime()) + "," + orderRoundTrip.getRoundTrip() + ","
                 + df.format(orderRoundTrip.getTimeOnServer()));
        bw.newLine();
      }
      bw.close();

      System.out.println("Done!");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}