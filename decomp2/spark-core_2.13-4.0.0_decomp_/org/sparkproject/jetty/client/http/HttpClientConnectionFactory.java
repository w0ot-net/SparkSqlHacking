package org.sparkproject.jetty.client.http;

import java.util.List;
import java.util.Map;
import org.sparkproject.jetty.io.ClientConnectionFactory;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;

public class HttpClientConnectionFactory implements ClientConnectionFactory {
   public static final ClientConnectionFactory.Info HTTP11 = new HTTP11(new HttpClientConnectionFactory());

   public Connection newConnection(EndPoint endPoint, Map context) {
      HttpConnectionOverHTTP connection = new HttpConnectionOverHTTP(endPoint, context);
      return this.customize(connection, context);
   }

   private static class HTTP11 extends ClientConnectionFactory.Info {
      private static final List protocols = List.of("http/1.1");

      private HTTP11(ClientConnectionFactory factory) {
         super(factory);
      }

      public List getProtocols(boolean secure) {
         return protocols;
      }

      public String toString() {
         return String.format("%s@%x%s", this.getClass().getSimpleName(), this.hashCode(), protocols);
      }
   }
}
