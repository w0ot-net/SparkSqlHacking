package org.sparkproject.jetty.client;

import org.sparkproject.jetty.client.api.Connection;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.Pool;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject
public class MultiplexConnectionPool extends AbstractConnectionPool {
   public MultiplexConnectionPool(HttpDestination destination, int maxConnections, Callback requester, int maxMultiplex) {
      this(destination, maxConnections, false, requester, maxMultiplex);
   }

   public MultiplexConnectionPool(HttpDestination destination, int maxConnections, boolean cache, Callback requester, int maxMultiplex) {
      this(destination, Pool.StrategyType.FIRST, maxConnections, cache, requester, maxMultiplex);
   }

   public MultiplexConnectionPool(HttpDestination destination, Pool.StrategyType strategy, int maxConnections, boolean cache, Callback requester, int maxMultiplex) {
      super(destination, new Pool(strategy, maxConnections, cache) {
         protected int getMaxUsageCount(Connection connection) {
            int maxUsage = connection instanceof ConnectionPool.MaxUsable ? ((ConnectionPool.MaxUsable)connection).getMaxUsageCount() : super.getMaxUsageCount(connection);
            return maxUsage > 0 ? maxUsage : -1;
         }

         protected int getMaxMultiplex(Connection connection) {
            int multiplex = connection instanceof ConnectionPool.Multiplexable ? ((ConnectionPool.Multiplexable)connection).getMaxMultiplex() : super.getMaxMultiplex(connection);
            return multiplex > 0 ? multiplex : 1;
         }
      }, requester);
      this.setMaxMultiplex(maxMultiplex);
   }

   /** @deprecated */
   @Deprecated
   public MultiplexConnectionPool(HttpDestination destination, Pool pool, Callback requester, int maxMultiplex) {
      super(destination, pool, requester);
      this.setMaxMultiplex(maxMultiplex);
   }

   @ManagedAttribute("The multiplexing factor of connections")
   public int getMaxMultiplex() {
      return super.getMaxMultiplex();
   }

   public void setMaxMultiplex(int maxMultiplex) {
      super.setMaxMultiplex(maxMultiplex);
   }

   @ManagedAttribute("The maximum amount of times a connection is used before it gets closed")
   public int getMaxUsageCount() {
      return super.getMaxUsageCount();
   }

   public void setMaxUsageCount(int maxUsageCount) {
      super.setMaxUsageCount(maxUsageCount);
   }
}
