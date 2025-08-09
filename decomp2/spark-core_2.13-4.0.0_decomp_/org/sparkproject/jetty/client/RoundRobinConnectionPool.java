package org.sparkproject.jetty.client;

import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.Pool;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject
public class RoundRobinConnectionPool extends MultiplexConnectionPool {
   public RoundRobinConnectionPool(HttpDestination destination, int maxConnections, Callback requester) {
      this(destination, maxConnections, requester, 1);
   }

   public RoundRobinConnectionPool(HttpDestination destination, int maxConnections, Callback requester, int maxMultiplex) {
      super(destination, Pool.StrategyType.ROUND_ROBIN, maxConnections, false, requester, maxMultiplex);
      this.setMaximizeConnections(true);
   }
}
