package org.sparkproject.jetty.io.ssl;

import javax.net.ssl.SSLEngine;
import org.sparkproject.jetty.io.Connection;

public interface ALPNProcessor {
   default void init() {
   }

   default boolean appliesTo(SSLEngine sslEngine) {
      return false;
   }

   default void configure(SSLEngine sslEngine, Connection connection) {
   }

   public interface Client extends ALPNProcessor {
   }

   public interface Server extends ALPNProcessor {
   }
}
