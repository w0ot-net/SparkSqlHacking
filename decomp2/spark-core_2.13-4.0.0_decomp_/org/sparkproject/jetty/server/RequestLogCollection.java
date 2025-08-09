package org.sparkproject.jetty.server;

import java.util.ArrayList;
import java.util.Arrays;

class RequestLogCollection implements RequestLog {
   private final ArrayList delegates;

   public RequestLogCollection(RequestLog... requestLogs) {
      this.delegates = new ArrayList(Arrays.asList(requestLogs));
   }

   public void add(RequestLog requestLog) {
      this.delegates.add(requestLog);
   }

   public void log(Request request, Response response) {
      for(RequestLog delegate : this.delegates) {
         delegate.log(request, response);
      }

   }
}
