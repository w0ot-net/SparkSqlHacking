package org.sparkproject.jetty.client;

import org.sparkproject.jetty.util.annotation.ManagedAttribute;

public class MultiplexHttpDestination extends HttpDestination implements HttpDestination.Multiplexed {
   public MultiplexHttpDestination(HttpClient client, Origin origin) {
      this(client, origin, false);
   }

   public MultiplexHttpDestination(HttpClient client, Origin origin, boolean intrinsicallySecure) {
      super(client, origin, intrinsicallySecure);
   }

   @ManagedAttribute("The maximum number of concurrent requests per connection")
   public int getMaxRequestsPerConnection() {
      ConnectionPool connectionPool = this.getConnectionPool();
      return connectionPool instanceof AbstractConnectionPool ? ((AbstractConnectionPool)connectionPool).getMaxMultiplex() : 1;
   }

   public void setMaxRequestsPerConnection(int maxRequestsPerConnection) {
      ConnectionPool connectionPool = this.getConnectionPool();
      if (connectionPool instanceof AbstractConnectionPool) {
         ((AbstractConnectionPool)connectionPool).setMaxMultiplex(maxRequestsPerConnection);
      }

   }
}
