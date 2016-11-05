package org.example.exchange.server.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

public class RequestUtils {

  public static String getBody(HttpServletRequest request) throws ServletException, IOException {
    checkRequestContent(request);
    int contentLength = request.getContentLength();
    RequestUtils.checkContentLength(contentLength);
    InputStream in = request.getInputStream();
    return RequestUtils.readStream(contentLength, in);
  }

  private static String readStream(int contentLength, InputStream in) throws IOException, ServletException {
    try {
      byte[] stream = new byte[contentLength];
      int offset = 0;
      int len = contentLength;
      int byteCount;
      while (offset < contentLength) {
        byteCount = in.read(stream, offset, len);
        if (byteCount == -1) {
          throw new ServletException("Client did not send " + contentLength + " bytes as expected");
        }
        offset += byteCount;
        len -= byteCount;
      }
      return new String(stream, "UTF-8");
    }
    finally {
      if (in != null) {
        in.close();
      }
    }
  }

  private static void checkRequestContent(HttpServletRequest request) throws ServletException {
    String contentType = request.getContentType();
    boolean contentTypeIsWrong = true;

    if (contentType != null) {
      if (contentType.startsWith("text/plain")) {
        contentTypeIsWrong = false;
      }
    }
    if (contentTypeIsWrong) {
      throw new ServletException("Content-Type must be 'text/plain'");
    }
  }

  private static void checkContentLength(int contentLength) throws ServletException {
    if (contentLength == -1) {
      throw new ServletException("Content-Length must be specified");
    }
  }
}
