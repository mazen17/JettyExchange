package org.example.exchange.client;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;
import org.example.exchange.model.Banks;
import org.example.exchange.model.CodedOrder;
import org.example.exchange.model.Currencies;
import org.example.exchange.model.NormalOrder;
import org.example.exchange.model.TypeOrders;

public class OrderFactory {

  private static Random random = new Random();;
  private static MathContext mathContext = new MathContext(4);;

  public static NormalOrder newRandomNormalOrder(NormalOrder order) {

    String randomBank = OrderFactory.getRandomBank(Banks.values());
    String randomTypeOrder = OrderFactory.getRandomTypeOrder(TypeOrders.values());
    String randomCurrencyPair = OrderFactory.getRandomCurrencyPair(Currencies.values());

    order.setBank(randomBank);
    order.setOrderType(randomTypeOrder);
    order.setCurrencyPair(randomCurrencyPair);
    order.setPrice(new BigDecimal(Math.abs(random.nextGaussian()), mathContext));
    return order;
  }

  public static CodedOrder newRandomCodedOrder(CodedOrder order) {

    String randomTypeOrder = OrderFactory.getRandomTypeOrder(TypeOrders.values());

    order.setOrderType(randomTypeOrder);
    order.setPrice(new BigDecimal(Math.abs(random.nextGaussian()), mathContext));
    order.setCode((byte) random.nextInt(20));
    return order;
  }

  public static String getRandomBank(Banks[] values) {
    int i = random.nextInt(values.length);
    return values[i].name();
  }

  public static String getRandomTypeOrder(TypeOrders[] values) {
    int i = random.nextInt(values.length);
    return values[i].name();
  }

  public static String getRandomCurrencyPair(Currencies[] values) {

    int n = values.length;
    int first = random.nextInt(n);
    int second = random.nextInt(n);

    second = getSecond(first, second, n);

    String currencySymbol1 = values[first].name();
    String currencySymbol2 = values[second].name();

    return currencySymbol1 + "/" + currencySymbol2;
  }

  protected static int getSecond(int i1, int i2, int n) {
    if (i1 == i2) {
      if (i1 != n - 1) {
        i2 = i2 + 1;
      }
      else {
        i2 = 0;
      }
    }
    return i2;
  }
}