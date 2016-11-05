package org.example.exchange.server;

import javax.servlet.http.HttpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * The server application.
 */
public class ExchangeServer extends Server {

  public ExchangeServer(ExchangeServlet servlet) {
    super(8080);
    this.setHandler(makeHandler(servlet));
  }

  private static ServletContextHandler makeHandler(HttpServlet servlet) {
    ServletContextHandler handler = new ServletContextHandler();
    handler.setContextPath("/");
    handler.addServlet(new ServletHolder(servlet), "/*");
    return handler;
  }
}