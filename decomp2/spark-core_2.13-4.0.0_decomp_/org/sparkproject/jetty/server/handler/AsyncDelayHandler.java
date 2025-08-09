package org.sparkproject.jetty.server.handler;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.sparkproject.jetty.server.Request;

public class AsyncDelayHandler extends HandlerWrapper {
   public static final String AHW_ATTR = "o.e.j.s.h.AsyncHandlerWrapper";

   public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      if (this.isStarted() && this._handler != null) {
         DispatcherType ctype = baseRequest.getDispatcherType();
         DispatcherType dtype = (DispatcherType)baseRequest.getAttribute("o.e.j.s.h.AsyncHandlerWrapper");
         Object asyncContextPath = null;
         Object asyncPathInfo = null;
         Object asyncQueryString = null;
         Object asyncRequestUri = null;
         Object asyncServletPath = null;
         Object asyncHttpServletMapping = null;
         boolean restart = false;
         if (dtype != null) {
            baseRequest.setAttribute("o.e.j.s.h.AsyncHandlerWrapper", (Object)null);
            baseRequest.setDispatcherType(dtype);
            restart = true;
            asyncContextPath = baseRequest.getAttribute("jakarta.servlet.async.context_path");
            baseRequest.setAttribute("jakarta.servlet.async.context_path", (Object)null);
            asyncPathInfo = baseRequest.getAttribute("jakarta.servlet.async.path_info");
            baseRequest.setAttribute("jakarta.servlet.async.path_info", (Object)null);
            asyncQueryString = baseRequest.getAttribute("jakarta.servlet.async.query_string");
            baseRequest.setAttribute("jakarta.servlet.async.query_string", (Object)null);
            asyncRequestUri = baseRequest.getAttribute("jakarta.servlet.async.request_uri");
            baseRequest.setAttribute("jakarta.servlet.async.request_uri", (Object)null);
            asyncServletPath = baseRequest.getAttribute("jakarta.servlet.async.servlet_path");
            baseRequest.setAttribute("jakarta.servlet.async.servlet_path", (Object)null);
            asyncHttpServletMapping = baseRequest.getAttribute("jakarta.servlet.async.mapping");
            baseRequest.setAttribute("jakarta.servlet.async.mapping", (Object)null);
         }

         if (!this.startHandling(baseRequest, restart)) {
            AsyncContext context = baseRequest.startAsync();
            baseRequest.setAttribute("o.e.j.s.h.AsyncHandlerWrapper", ctype);
            this.delayHandling(baseRequest, context);
         } else {
            try {
               this._handler.handle(target, baseRequest, request, response);
            } finally {
               if (restart) {
                  baseRequest.setDispatcherType(ctype);
                  baseRequest.setAttribute("jakarta.servlet.async.context_path", asyncContextPath);
                  baseRequest.setAttribute("jakarta.servlet.async.path_info", asyncPathInfo);
                  baseRequest.setAttribute("jakarta.servlet.async.query_string", asyncQueryString);
                  baseRequest.setAttribute("jakarta.servlet.async.request_uri", asyncRequestUri);
                  baseRequest.setAttribute("jakarta.servlet.async.servlet_path", asyncServletPath);
                  baseRequest.setAttribute("jakarta.servlet.async.mapping", asyncHttpServletMapping);
               }

               this.endHandling(baseRequest);
            }

         }
      }
   }

   protected boolean startHandling(Request request, boolean restart) {
      return true;
   }

   protected void delayHandling(Request request, AsyncContext context) {
      context.dispatch();
   }

   protected void endHandling(Request request) {
   }
}
