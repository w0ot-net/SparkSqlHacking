package org.sparkproject.jetty.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Connection;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.LeakDetector;
import org.sparkproject.jetty.util.component.LifeCycle;

public class LeakTrackingConnectionPool extends DuplexConnectionPool {
   private static final Logger LOG = LoggerFactory.getLogger(LeakTrackingConnectionPool.class);
   private final LeakDetector leakDetector = new LeakDetector() {
      protected void leaked(LeakDetector.LeakInfo leakInfo) {
         LeakTrackingConnectionPool.this.leaked(leakInfo);
      }
   };

   public LeakTrackingConnectionPool(HttpDestination destination, int maxConnections, Callback requester) {
      super(destination, maxConnections, requester);
      this.addBean(this.leakDetector);
   }

   public void close() {
      super.close();
      LifeCycle.stop(this);
   }

   protected void acquired(Connection connection) {
      if (!this.leakDetector.acquired(connection)) {
         LOG.info("Connection {}@{} not tracked", connection, this.leakDetector.id(connection));
      }

   }

   protected void released(Connection connection) {
      if (!this.leakDetector.released(connection)) {
         LOG.info("Connection {}@{} released but not acquired", connection, this.leakDetector.id(connection));
      }

   }

   protected void leaked(LeakDetector.LeakInfo leakInfo) {
      LOG.info("Connection {} leaked at:", leakInfo.getResourceDescription(), leakInfo.getStackFrames());
   }
}
