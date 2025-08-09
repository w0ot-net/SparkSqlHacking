package org.sparkproject.jetty.server.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.Request;

public class HandlerList extends HandlerCollection {
   public HandlerList() {
   }

   public HandlerList(Handler... handlers) {
      super(handlers);
   }

   public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      Handler[] handlers = this.getHandlers();
      if (handlers != null && this.isStarted()) {
         for(int i = 0; i < handlers.length; ++i) {
            handlers[i].handle(target, baseRequest, request, response);
            if (baseRequest.isHandled()) {
               return;
            }
         }
      }

   }
}
