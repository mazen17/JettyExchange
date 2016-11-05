package org.example.exchange.client;

import java.math.BigDecimal;
import org.example.exchange.model.Banks;
import org.example.exchange.model.CodedOrder;
import org.example.exchange.model.Currencies;
import org.example.exchange.model.NormalOrder;
import org.example.exchange.model.TypeOrders;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderFactoryTest {

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void testNewRandomNormalOrder() throws Exception {

    NormalOrder normalOrder = OrderFactory.newRandomNormalOrder(new NormalOrder(0));
    Assert.assertNotNull(normalOrder);

    Assert.assertTrue(normalOrder.getId() == 0);
    Assert.assertTrue(normalOrder.getBank().length() == 3);
    Assert.assertTrue(normalOrder.getCurrencyPair().length() == 7);
    Assert.assertTrue(normalOrder.getCurrencyPair().contains("/"));
    Assert.assertTrue(normalOrder.getOrderType().length() == 2);
    Assert.assertTrue(normalOrder.getPrice() instanceof BigDecimal);

  }

  @Test
  public void testNewRandomCodedOrder() throws Exception {

    CodedOrder codedOrder = OrderFactory.newRandomCodedOrder(new CodedOrder(0));
    Assert.assertNotNull(codedOrder);

    Assert.assertTrue(codedOrder.getId() == 0);
    Assert.assertTrue(codedOrder.getBank().length() == 3);
    Assert.assertTrue(codedOrder.getCurrencyPair().length() == 7);
    Assert.assertTrue(codedOrder.getCurrencyPair().contains("/"));
    Assert.assertTrue(codedOrder.getOrderType().length() == 2);
    Assert.assertTrue(codedOrder.getPrice() instanceof BigDecimal);

  }

  @Test
  public void testGetRandomBanks() throws Exception {

    String randomBank = OrderFactory.getRandomBank(Banks.values());
    Assert.assertNotNull(randomBank);
    Assert.assertTrue(randomBank.length() == 3);
  }

  @Test
  public void testGetRandomTypeOrder() throws Exception {

    String randomTypeOrder = OrderFactory.getRandomTypeOrder(TypeOrders.values());
    Assert.assertNotNull(randomTypeOrder);
    Assert.assertTrue(randomTypeOrder.length() == 2);
  }

  @Test
  public void testGetRandomCurrencyPair() throws Exception {

    String randomCurrencyPair = OrderFactory.getRandomCurrencyPair(Currencies.values());
    Assert.assertNotNull(randomCurrencyPair);
    Assert.assertTrue(randomCurrencyPair.length() == 7);
    String[] currencies = randomCurrencyPair.split("/");
    Assert.assertNotEquals(currencies[0], currencies[1]);

  }

  @Test
  public void testGetSecondDifferent() throws Exception {
    int first = 1;
    int second = 2;
    int n = 7;

    int returnedSecond = OrderFactory.getSecond(first, second, n);
    Assert.assertEquals(second, returnedSecond);

  }

  @Test
  public void testGetSecondEquals() throws Exception {
    int first = 2;
    int second = 2;
    int n = 7;

    int returnedSecond = OrderFactory.getSecond(first, second, n);
    Assert.assertEquals(second + 1, returnedSecond);

  }

  @Test
  public void testGetSecondEquals7() throws Exception {
    int first = 7;
    int second = 7;
    int n = 8;

    int returnedSecond = OrderFactory.getSecond(first, second, n);
    Assert.assertEquals(returnedSecond, 0);

  }

}
