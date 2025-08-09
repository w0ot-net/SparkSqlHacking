package org.sparkproject.jetty.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Connection;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.io.CyclicTimeouts;
import org.sparkproject.jetty.util.thread.AutoLock;

public abstract class HttpChannel implements CyclicTimeouts.Expirable {
   private static final Logger LOG = LoggerFactory.getLogger(HttpChannel.class);
   private final AutoLock _lock = new AutoLock();
   private final HttpDestination _destination;
   private HttpExchange _exchange;

   protected HttpChannel(HttpDestination destination) {
      this._destination = destination;
   }

   public void destroy() {
   }

   public HttpDestination getHttpDestination() {
      return this._destination;
   }

   public boolean associate(HttpExchange exchange) {
      boolean result = false;
      boolean abort = true;

      try (AutoLock ignored = this._lock.lock()) {
         if (this._exchange == null) {
            abort = false;
            result = exchange.associate(this);
            if (result) {
               this._exchange = exchange;
            }
         }
      }

      HttpRequest request = exchange.getRequest();
      if (abort) {
         request.abort(new UnsupportedOperationException("Pipelined requests not supported"));
      } else {
         request.setConnection(this.getConnection());
         if (LOG.isDebugEnabled()) {
            LOG.debug("{} associated {} to {}", new Object[]{exchange, result, this});
         }
      }

      return result;
   }

   public boolean disassociate(HttpExchange exchange) {
      boolean result = false;

      try (AutoLock ignored = this._lock.lock()) {
         HttpExchange existing = this._exchange;
         this._exchange = null;
         if (existing == exchange) {
            existing.disassociate(this);
            result = true;
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("{} disassociated {} from {}", new Object[]{exchange, result, this});
      }

      return result;
   }

   public HttpExchange getHttpExchange() {
      try (AutoLock ignored = this._lock.lock()) {
         return this._exchange;
      }
   }

   protected abstract Connection getConnection();

   public long getExpireNanoTime() {
      HttpExchange exchange = this.getHttpExchange();
      return exchange != null ? exchange.getExpireNanoTime() : Long.MAX_VALUE;
   }

   protected abstract HttpSender getHttpSender();

   protected abstract HttpReceiver getHttpReceiver();

   public void send() {
      HttpExchange exchange = this.getHttpExchange();
      if (exchange != null) {
         this.send(exchange);
      }

   }

   public abstract void send(HttpExchange var1);

   public abstract void release();

   public void proceed(HttpExchange exchange, Throwable failure) {
      this.getHttpSender().proceed(exchange, failure);
   }

   public boolean abort(HttpExchange exchange, Throwable requestFailure, Throwable responseFailure) {
      boolean requestAborted = false;
      if (requestFailure != null) {
         requestAborted = this.getHttpSender().abort(exchange, requestFailure);
      }

      boolean responseAborted = false;
      if (responseFailure != null) {
         responseAborted = this.abortResponse(exchange, responseFailure);
      }

      return requestAborted || responseAborted;
   }

   public boolean abortResponse(HttpExchange exchange, Throwable failure) {
      return this.getHttpReceiver().abort(exchange, failure);
   }

   public Result exchangeTerminating(HttpExchange exchange, Result result) {
      return result;
   }

   public void exchangeTerminated(HttpExchange exchange, Result result) {
      this.disassociate(exchange);
   }

   public String toString() {
      return String.format("%s@%x(exchange=%s)", this.getClass().getSimpleName(), this.hashCode(), this.getHttpExchange());
   }
}
