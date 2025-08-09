package org.sparkproject.jetty.servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sparkproject.jetty.server.Request;

public class CloseableDoSFilter extends DoSFilter {
   protected void onRequestTimeout(HttpServletRequest request, HttpServletResponse response, Thread handlingThread) {
      Request baseRequest = Request.getBaseRequest(request);
      baseRequest.getHttpChannel().getEndPoint().close();
   }
}
