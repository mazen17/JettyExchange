package org.example.exchange.client.model;

import org.example.exchange.model.CodedOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CodedOrderTest {

  CodedOrder codedOrder;

  @Before
  public void setUp() throws Exception {
    codedOrder = new CodedOrder(0);
  }

  @Test
  public void testNewRandomNormalOrder() throws Exception {

    codedOrder.setCode((byte) 10);

    Assert.assertEquals(codedOrder.getBank(), "SGE");
    Assert.assertEquals(codedOrder.getCurrencyPair(), "USD/JPN");
  }
}
