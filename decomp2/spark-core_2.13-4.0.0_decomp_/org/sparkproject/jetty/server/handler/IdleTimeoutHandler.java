package org.sparkproject.jetty.server.handler;

import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.sparkproject.jetty.server.HttpChannel;
import org.sparkproject.jetty.server.Request;

public class IdleTimeoutHandler extends HandlerWrapper {
   private long _idleTimeoutMs = 1000L;
   private boolean _applyToAsync = false;

   public boolean isApplyToAsync() {
      return this._applyToAsync;
   }

   public void setApplyToAsync(boolean applyToAsync) {
      this._applyToAsync = applyToAsync;
   }

   public long getIdleTimeoutMs() {
      return this._idleTimeoutMs;
   }

   public void setIdleTimeoutMs(long idleTimeoutMs) {
      this._idleTimeoutMs = idleTimeoutMs;
   }

   public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      final HttpChannel channel = baseRequest.getHttpChannel();
      final long idle_timeout = baseRequest.getHttpChannel().getIdleTimeout();
      channel.setIdleTimeout(this._idleTimeoutMs);

      try {
         super.handle(target, baseRequest, request, response);
      } finally {
         if (this._applyToAsync && request.isAsyncStarted()) {
            request.getAsyncContext().addListener(new AsyncListener() {
               public void onTimeout(AsyncEvent event) throws IOException {
               }

               public void onStartAsync(AsyncEvent event) throws IOException {
               }

               public void onError(AsyncEvent event) throws IOException {
                  channel.setIdleTimeout(idle_timeout);
               }

               public void onComplete(AsyncEvent event) throws IOException {
                  channel.setIdleTimeout(idle_timeout);
               }
            });
         } else {
            channel.setIdleTimeout(idle_timeout);
         }

      }

   }
}
