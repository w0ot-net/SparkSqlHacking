package org.sparkproject.jetty.client;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Connection;
import org.sparkproject.jetty.util.Promise;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;

@ManagedObject
public abstract class AbstractHttpClientTransport extends ContainerLifeCycle implements HttpClientTransport {
   private static final Logger LOG = LoggerFactory.getLogger(HttpClientTransport.class);
   private HttpClient client;
   private ConnectionPool.Factory factory;

   protected HttpClient getHttpClient() {
      return this.client;
   }

   public void setHttpClient(HttpClient client) {
      this.client = client;
   }

   public ConnectionPool.Factory getConnectionPoolFactory() {
      return this.factory;
   }

   public void setConnectionPoolFactory(ConnectionPool.Factory factory) {
      this.factory = factory;
   }

   protected void connectFailed(Map context, Throwable failure) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Could not connect to {}", context.get("org.sparkproject.jetty.client.destination"));
      }

      Promise<Connection> promise = (Promise)context.get("org.sparkproject.jetty.client.connection.promise");
      promise.failed(failure);
   }
}
