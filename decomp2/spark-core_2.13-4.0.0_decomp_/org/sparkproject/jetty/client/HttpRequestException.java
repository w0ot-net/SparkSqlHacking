package org.sparkproject.jetty.client;

import org.sparkproject.jetty.client.api.Request;

public class HttpRequestException extends RuntimeException {
   private final Request request;

   public HttpRequestException(String message, Request request) {
      super(message);
      this.request = request;
   }

   public Request getRequest() {
      return this.request;
   }
}
