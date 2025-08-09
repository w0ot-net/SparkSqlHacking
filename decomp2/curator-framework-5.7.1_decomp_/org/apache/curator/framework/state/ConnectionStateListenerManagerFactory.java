package org.apache.curator.framework.state;

import java.util.concurrent.ScheduledExecutorService;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.listen.StandardListenerManager;
import org.apache.curator.framework.listen.UnaryListenerManager;

@FunctionalInterface
public interface ConnectionStateListenerManagerFactory {
   ConnectionStateListenerManagerFactory standard = (__) -> StandardListenerManager.standard();

   UnaryListenerManager newManager(CuratorFramework var1);

   static ConnectionStateListenerManagerFactory circuitBreaking(RetryPolicy retryPolicy) {
      return (client) -> new CircuitBreakingManager(client, CircuitBreaker.build(retryPolicy));
   }

   static ConnectionStateListenerManagerFactory circuitBreaking(RetryPolicy retryPolicy, ScheduledExecutorService service) {
      return (client) -> new CircuitBreakingManager(client, CircuitBreaker.build(retryPolicy, service));
   }
}
