package org.sparkproject.jetty.client;

import org.sparkproject.jetty.client.api.Connection;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.Pool;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject
public class DuplexConnectionPool extends AbstractConnectionPool {
   public DuplexConnectionPool(HttpDestination destination, int maxConnections, Callback requester) {
      this(destination, maxConnections, false, requester);
   }

   public DuplexConnectionPool(HttpDestination destination, int maxConnections, boolean cache, Callback requester) {
      super(destination, Pool.StrategyType.FIRST, maxConnections, cache, requester);
   }

   /** @deprecated */
   @Deprecated
   public DuplexConnectionPool(HttpDestination destination, Pool pool, Callback requester) {
      super(destination, pool, requester);
   }

   @ManagedAttribute("The maximum amount of times a connection is used before it gets closed")
   public int getMaxUsageCount() {
      return super.getMaxUsageCount();
   }

   public void setMaxUsageCount(int maxUsageCount) {
      super.setMaxUsageCount(maxUsageCount);
   }
}
