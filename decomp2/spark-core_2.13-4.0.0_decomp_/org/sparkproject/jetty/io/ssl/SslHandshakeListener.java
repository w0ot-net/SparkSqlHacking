package org.sparkproject.jetty.io.ssl;

import java.util.EventListener;
import java.util.EventObject;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;

public interface SslHandshakeListener extends EventListener {
   default void handshakeSucceeded(Event event) throws SSLException {
   }

   default void handshakeFailed(Event event, Throwable failure) {
   }

   public static class Event extends EventObject {
      public Event(Object source) {
         super(source);
      }

      public SSLEngine getSSLEngine() {
         return (SSLEngine)this.getSource();
      }
   }
}
