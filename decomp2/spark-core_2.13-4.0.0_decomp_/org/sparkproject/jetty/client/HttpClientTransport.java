package org.sparkproject.jetty.client;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;
import org.sparkproject.jetty.io.ClientConnectionFactory;

public interface HttpClientTransport extends ClientConnectionFactory {
   String HTTP_DESTINATION_CONTEXT_KEY = "org.sparkproject.jetty.client.destination";
   String HTTP_CONNECTION_PROMISE_CONTEXT_KEY = "org.sparkproject.jetty.client.connection.promise";

   void setHttpClient(HttpClient var1);

   Origin newOrigin(HttpRequest var1);

   HttpDestination newHttpDestination(Origin var1);

   /** @deprecated */
   @Deprecated
   void connect(InetSocketAddress var1, Map var2);

   default void connect(SocketAddress address, Map context) {
      if (address instanceof InetSocketAddress) {
         this.connect((InetSocketAddress)address, context);
      } else {
         throw new UnsupportedOperationException("Unsupported SocketAddress " + String.valueOf(address));
      }
   }

   ConnectionPool.Factory getConnectionPoolFactory();

   void setConnectionPoolFactory(ConnectionPool.Factory var1);
}
