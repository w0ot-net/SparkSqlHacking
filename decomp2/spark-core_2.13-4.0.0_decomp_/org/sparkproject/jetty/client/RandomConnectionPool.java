package org.sparkproject.jetty.client;

import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.Pool;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject
public class RandomConnectionPool extends MultiplexConnectionPool {
   public RandomConnectionPool(HttpDestination destination, int maxConnections, Callback requester, int maxMultiplex) {
      super(destination, Pool.StrategyType.RANDOM, maxConnections, false, requester, maxMultiplex);
   }
}
