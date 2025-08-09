package org.sparkproject.jetty.client;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import org.sparkproject.jetty.client.api.Connection;
import org.sparkproject.jetty.io.ClientConnector;
import org.sparkproject.jetty.util.Promise;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject
public abstract class AbstractConnectorHttpClientTransport extends AbstractHttpClientTransport {
   private final ClientConnector connector;

   protected AbstractConnectorHttpClientTransport(ClientConnector connector) {
      this.connector = (ClientConnector)Objects.requireNonNull(connector);
      this.addBean(connector);
   }

   public ClientConnector getClientConnector() {
      return this.connector;
   }

   @ManagedAttribute(
      value = "The number of selectors",
      readonly = true
   )
   public int getSelectors() {
      return this.connector.getSelectors();
   }

   protected void doStart() throws Exception {
      HttpClient httpClient = this.getHttpClient();
      this.connector.setBindAddress(httpClient.getBindAddress());
      this.connector.setByteBufferPool(httpClient.getByteBufferPool());
      this.connector.setConnectBlocking(httpClient.isConnectBlocking());
      this.connector.setConnectTimeout(Duration.ofMillis(httpClient.getConnectTimeout()));
      this.connector.setExecutor(httpClient.getExecutor());
      this.connector.setIdleTimeout(Duration.ofMillis(httpClient.getIdleTimeout()));
      this.connector.setScheduler(httpClient.getScheduler());
      this.connector.setSslContextFactory(httpClient.getSslContextFactory());
      super.doStart();
   }

   public void connect(SocketAddress address, Map context) {
      HttpDestination destination = (HttpDestination)context.get("org.sparkproject.jetty.client.destination");
      context.put("org.sparkproject.jetty.client.connector.clientConnectionFactory", destination.getClientConnectionFactory());
      Promise<Connection> promise = (Promise)context.get("org.sparkproject.jetty.client.connection.promise");
      Consumer var10002 = (ioConnection) -> {
      };
      Objects.requireNonNull(promise);
      context.put("org.sparkproject.jetty.client.connector.connectionPromise", Promise.from(var10002, promise::failed));
      this.connector.connect(address, context);
   }

   public void connect(InetSocketAddress address, Map context) {
      this.connect((SocketAddress)address, context);
   }
}
