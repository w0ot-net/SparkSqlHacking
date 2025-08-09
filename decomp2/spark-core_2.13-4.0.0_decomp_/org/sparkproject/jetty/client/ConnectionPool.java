package org.sparkproject.jetty.client;

import java.io.Closeable;
import java.util.concurrent.CompletableFuture;
import org.sparkproject.jetty.client.api.Connection;

public interface ConnectionPool extends Closeable {
   default CompletableFuture preCreateConnections(int connectionCount) {
      return CompletableFuture.completedFuture((Object)null);
   }

   boolean isActive(Connection var1);

   boolean isEmpty();

   boolean isClosed();

   Connection acquire(boolean var1);

   boolean accept(Connection var1);

   boolean release(Connection var1);

   boolean remove(Connection var1);

   void close();

   public interface Multiplexable {
      int getMaxMultiplex();

      /** @deprecated */
      @Deprecated
      default void setMaxMultiplex(int maxMultiplex) {
      }
   }

   public interface Factory {
      ConnectionPool newConnectionPool(HttpDestination var1);
   }

   public interface MaxUsable {
      int getMaxUsageCount();
   }
}
