package org.sparkproject.jetty.client.http;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.AbstractConnectorHttpClientTransport;
import org.sparkproject.jetty.client.DuplexConnectionPool;
import org.sparkproject.jetty.client.DuplexHttpDestination;
import org.sparkproject.jetty.client.HttpDestination;
import org.sparkproject.jetty.client.HttpRequest;
import org.sparkproject.jetty.client.Origin;
import org.sparkproject.jetty.io.ClientConnectionFactory;
import org.sparkproject.jetty.io.ClientConnector;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.ProcessorUtils;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject("The HTTP/1.1 client transport")
public class HttpClientTransportOverHTTP extends AbstractConnectorHttpClientTransport {
   public static final Origin.Protocol HTTP11 = new Origin.Protocol(List.of("http/1.1"), false);
   private static final Logger LOG = LoggerFactory.getLogger(HttpClientTransportOverHTTP.class);
   private final ClientConnectionFactory factory;
   private int headerCacheSize;
   private boolean headerCacheCaseSensitive;

   public HttpClientTransportOverHTTP() {
      this(Math.max(1, ProcessorUtils.availableProcessors() / 2));
   }

   public HttpClientTransportOverHTTP(int selectors) {
      this(new ClientConnector());
      this.getClientConnector().setSelectors(selectors);
   }

   public HttpClientTransportOverHTTP(ClientConnector connector) {
      super(connector);
      this.factory = new HttpClientConnectionFactory();
      this.headerCacheSize = 1024;
      this.setConnectionPoolFactory((destination) -> new DuplexConnectionPool(destination, this.getHttpClient().getMaxConnectionsPerDestination(), destination));
   }

   public Origin newOrigin(HttpRequest request) {
      return this.getHttpClient().createOrigin(request, HTTP11);
   }

   public HttpDestination newHttpDestination(Origin origin) {
      SocketAddress address = origin.getAddress().getSocketAddress();
      return new DuplexHttpDestination(this.getHttpClient(), origin, this.getClientConnector().isIntrinsicallySecure(address));
   }

   public Connection newConnection(EndPoint endPoint, Map context) throws IOException {
      Connection connection = this.factory.newConnection(endPoint, context);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Created {}", connection);
      }

      return connection;
   }

   @ManagedAttribute("The maximum allowed size in bytes for an HTTP header field cache")
   public int getHeaderCacheSize() {
      return this.headerCacheSize;
   }

   public void setHeaderCacheSize(int headerCacheSize) {
      this.headerCacheSize = headerCacheSize;
   }

   @ManagedAttribute("Whether the header field cache is case sensitive")
   public boolean isHeaderCacheCaseSensitive() {
      return this.headerCacheCaseSensitive;
   }

   public void setHeaderCacheCaseSensitive(boolean headerCacheCaseSensitive) {
      this.headerCacheCaseSensitive = headerCacheCaseSensitive;
   }
}
