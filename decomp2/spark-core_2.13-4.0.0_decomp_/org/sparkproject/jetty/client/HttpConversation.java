package org.sparkproject.jetty.client;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.util.AttributesMap;

public class HttpConversation extends AttributesMap {
   private static final Logger LOG = LoggerFactory.getLogger(HttpConversation.class);
   private final Deque exchanges = new ConcurrentLinkedDeque();
   private volatile List listeners;

   public Deque getExchanges() {
      return this.exchanges;
   }

   public List getResponseListeners() {
      return this.listeners;
   }

   public void updateResponseListeners(Response.ResponseListener overrideListener) {
      HttpExchange firstExchange = (HttpExchange)this.exchanges.peekFirst();
      HttpExchange lastExchange = (HttpExchange)this.exchanges.peekLast();
      List<Response.ResponseListener> listeners = new ArrayList(firstExchange.getResponseListeners().size() + lastExchange.getResponseListeners().size());
      if (firstExchange == lastExchange) {
         if (overrideListener != null) {
            listeners.add(overrideListener);
         } else {
            listeners.addAll(firstExchange.getResponseListeners());
         }
      } else {
         listeners.addAll(lastExchange.getResponseListeners());
         if (overrideListener != null) {
            listeners.add(overrideListener);
         } else {
            listeners.addAll(firstExchange.getResponseListeners());
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Exchanges in conversation {}, override={}, listeners={}", new Object[]{this.exchanges.size(), overrideListener, listeners});
      }

      this.listeners = listeners;
   }

   public long getTimeout() {
      HttpExchange firstExchange = (HttpExchange)this.exchanges.peekFirst();
      return firstExchange == null ? 0L : firstExchange.getRequest().getTimeout();
   }

   public boolean abort(Throwable cause) {
      HttpExchange exchange = (HttpExchange)this.exchanges.peekLast();
      return exchange != null && exchange.abort(cause);
   }

   public String toString() {
      return String.format("%s[%x]", HttpConversation.class.getSimpleName(), this.hashCode());
   }
}
