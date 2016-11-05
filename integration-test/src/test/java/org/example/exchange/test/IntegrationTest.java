package org.example.exchange.test;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.example.exchange.client.coded.CodedExchangeClient;
import org.example.exchange.client.normal.NormalExchangeClient;
import org.example.exchange.server.ExchangeServer;
import org.example.exchange.server.ExchangeServlet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IntegrationTest {

  private final NormalExchangeClient normalClient = new NormalExchangeClient("localhost");
  private final CodedExchangeClient codedClient = new CodedExchangeClient("localhost");
  private ExchangeServer server;

  @Before
  public void setUp() throws Exception {
    server = new ExchangeServer(new ExchangeServlet());
    server.start();
    normalClient.start();
    codedClient.start();
  }

  @After
  public void tearDown() throws Exception {
    normalClient.stop();
    codedClient.stop();
    server.stop();
  }

  @Test
  public void whenServerIsStarted_thenExchangeServerIsRunning() throws Exception {
    assertTrue(server.isRunning());
  }

  @Test
  public void whenNormalExchangeClientIsStarted_thenNormalExchangeClientIsRunning() throws Exception {
    assertTrue(normalClient.isRunning());
  }

  @Test
  public void whenCodedExchangeClientIsStarted_thenCodedExchangeClientIsRunning() throws Exception {
    assertTrue(codedClient.isRunning());
  }

  @Test
  public void whenNormalExchangeClientSimulatesOrders_thenExchangerServerHandlesRequests() throws Exception {
    int count = 100;
    normalClient.simulateOrders(count);
    normalClient.waitForDone(count);
    normalClient.postProcess();

    File file = new File("c:/data.csv");
    assertTrue(file.exists());
    Thread.sleep(1000);
  }

  @Test
  public void whenCodedExchangeClientSimulatesOrders_thenExchangerServerHandlesRequests() throws Exception {
    int count = 100;
    codedClient.simulateOrders(count);
    codedClient.waitForDone(count);
    codedClient.postProcess();

    File file = new File("c:/data.csv");
    assertTrue(file.exists());
    Thread.sleep(1000);
  }

}