package org.example.exchange.server;

import org.example.exchange.server.utils.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class ExchangeServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final int NORMAL_ORDER_SIZE = 5;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                         IOException {
    long timeStamp = new Date().getTime();

    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);

    String requestAsString = RequestUtils.getBody(request);
    String responseString = prepareResponse(timeStamp, requestAsString);

    response.getWriter().println(responseString);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                        IOException {
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().println("GET OK");
  }

  private String prepareResponse(long timeStamp, String stream) {
    String[] requestAsArray = stream.split(",");
    String responseString = requestAsArray[0] + ";" + timeStamp + ";" + requestAsArray[1] + ";";

    if (requestAsArray.length == NORMAL_ORDER_SIZE) {
      responseString = responseString + requestAsArray[2] + ";";
    }
    return responseString;
  }
}