package org.glassfish.jersey.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.Configuration;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
import org.glassfish.jersey.client.internal.HttpUrlConnector;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.client.spi.Connector;
import org.glassfish.jersey.client.spi.ConnectorProvider;

public class HttpUrlConnectorProvider implements ConnectorProvider {
   public static final String USE_FIXED_LENGTH_STREAMING = "jersey.config.client.httpUrlConnector.useFixedLengthStreaming";
   public static final String SET_METHOD_WORKAROUND = "jersey.config.client.httpUrlConnection.setMethodWorkaround";
   private static final ConnectionFactory DEFAULT_CONNECTION_FACTORY = new DefaultConnectionFactory();
   private static final Logger LOGGER = Logger.getLogger(HttpUrlConnectorProvider.class.getName());
   private ConnectionFactory connectionFactory;
   private int chunkSize;
   private boolean useFixedLengthStreaming;
   private boolean useSetMethodWorkaround;

   public HttpUrlConnectorProvider() {
      this.connectionFactory = DEFAULT_CONNECTION_FACTORY;
      this.chunkSize = 4096;
      this.useFixedLengthStreaming = false;
      this.useSetMethodWorkaround = false;
   }

   public HttpUrlConnectorProvider connectionFactory(ConnectionFactory connectionFactory) {
      if (connectionFactory == null) {
         throw new NullPointerException(LocalizationMessages.NULL_INPUT_PARAMETER("connectionFactory"));
      } else {
         this.connectionFactory = connectionFactory;
         return this;
      }
   }

   public HttpUrlConnectorProvider chunkSize(int chunkSize) {
      if (chunkSize < 0) {
         throw new IllegalArgumentException(LocalizationMessages.NEGATIVE_INPUT_PARAMETER("chunkSize"));
      } else {
         this.chunkSize = chunkSize;
         return this;
      }
   }

   public HttpUrlConnectorProvider useFixedLengthStreaming() {
      this.useFixedLengthStreaming = true;
      return this;
   }

   public HttpUrlConnectorProvider useSetMethodWorkaround() {
      this.useSetMethodWorkaround = true;
      return this;
   }

   public Connector getConnector(Client client, Configuration config) {
      Map<String, Object> properties = config.getProperties();
      int computedChunkSize = (Integer)ClientProperties.getValue(properties, "jersey.config.client.chunkedEncodingSize", this.chunkSize, Integer.class);
      if (computedChunkSize < 0) {
         LOGGER.warning(LocalizationMessages.NEGATIVE_CHUNK_SIZE(computedChunkSize, this.chunkSize));
         computedChunkSize = this.chunkSize;
      }

      boolean computedUseFixedLengthStreaming = (Boolean)ClientProperties.getValue(properties, "jersey.config.client.httpUrlConnector.useFixedLengthStreaming", this.useFixedLengthStreaming, Boolean.class);
      boolean computedUseSetMethodWorkaround = (Boolean)ClientProperties.getValue(properties, "jersey.config.client.httpUrlConnection.setMethodWorkaround", this.useSetMethodWorkaround, Boolean.class);
      return this.createHttpUrlConnector(client, this.connectionFactory, computedChunkSize, computedUseFixedLengthStreaming, computedUseSetMethodWorkaround);
   }

   protected Connector createHttpUrlConnector(Client client, ConnectionFactory connectionFactory, int chunkSize, boolean fixLengthStreaming, boolean setMethodWorkaround) {
      return new HttpUrlConnector(client, connectionFactory, chunkSize, fixLengthStreaming, setMethodWorkaround);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         HttpUrlConnectorProvider that = (HttpUrlConnectorProvider)o;
         if (this.chunkSize != that.chunkSize) {
            return false;
         } else {
            return this.useFixedLengthStreaming != that.useFixedLengthStreaming ? false : this.connectionFactory.equals(that.connectionFactory);
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.connectionFactory.hashCode();
      result = 31 * result + this.chunkSize;
      result = 31 * result + (this.useFixedLengthStreaming ? 1 : 0);
      return result;
   }

   public interface ConnectionFactory {
      HttpURLConnection getConnection(URL var1) throws IOException;

      default HttpURLConnection getConnection(URL url, Proxy proxy) throws IOException {
         synchronized(this) {
            return proxy == null ? this.getConnection(url) : (HttpURLConnection)url.openConnection(proxy);
         }
      }
   }

   private static class DefaultConnectionFactory implements ConnectionFactory {
      private final ConcurrentHashMap locks;

      private DefaultConnectionFactory() {
         this.locks = new ConcurrentHashMap();
      }

      public HttpURLConnection getConnection(URL url) throws IOException {
         return this.connect(url, (Proxy)null);
      }

      public HttpURLConnection getConnection(URL url, Proxy proxy) throws IOException {
         return this.connect(url, proxy);
      }

      private HttpURLConnection connect(URL url, Proxy proxy) throws IOException {
         Lock lock = (Lock)this.locks.computeIfAbsent(url, (u) -> new ReentrantLock());
         lock.lock();

         HttpURLConnection var4;
         try {
            var4 = proxy == null ? (HttpURLConnection)url.openConnection() : (HttpURLConnection)url.openConnection(proxy);
         } finally {
            lock.unlock();
         }

         return var4;
      }
   }
}
