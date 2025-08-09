package org.sparkproject.jetty.server;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.sparkproject.jetty.io.AbstractConnection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.ArrayUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;
import org.sparkproject.jetty.util.ssl.SslContextFactory;

@ManagedObject
public abstract class AbstractConnectionFactory extends ContainerLifeCycle implements ConnectionFactory {
   private final String _protocol;
   private final List _protocols;
   private int _inputBufferSize = 8192;

   protected AbstractConnectionFactory(String protocol) {
      this._protocol = protocol;
      this._protocols = List.of(protocol);
   }

   protected AbstractConnectionFactory(String... protocols) {
      this._protocol = protocols[0];
      this._protocols = List.of(protocols);
   }

   @ManagedAttribute(
      value = "The protocol name",
      readonly = true
   )
   public String getProtocol() {
      return this._protocol;
   }

   public List getProtocols() {
      return this._protocols;
   }

   @ManagedAttribute("The buffer size used to read from the network")
   public int getInputBufferSize() {
      return this._inputBufferSize;
   }

   public void setInputBufferSize(int size) {
      this._inputBufferSize = size;
   }

   protected String findNextProtocol(Connector connector) {
      return findNextProtocol(connector, this.getProtocol());
   }

   protected static String findNextProtocol(Connector connector, String currentProtocol) {
      String nextProtocol = null;
      Iterator<String> it = connector.getProtocols().iterator();

      while(it.hasNext()) {
         String protocol = (String)it.next();
         if (currentProtocol.equalsIgnoreCase(protocol)) {
            nextProtocol = it.hasNext() ? (String)it.next() : null;
            break;
         }
      }

      return nextProtocol;
   }

   protected AbstractConnection configure(AbstractConnection connection, Connector connector, EndPoint endPoint) {
      connection.setInputBufferSize(this.getInputBufferSize());
      List var10000 = connector.getEventListeners();
      Objects.requireNonNull(connection);
      var10000.forEach(connection::addEventListener);
      var10000 = this.getEventListeners();
      Objects.requireNonNull(connection);
      var10000.forEach(connection::addEventListener);
      return connection;
   }

   public String toString() {
      return String.format("%s@%x%s", this.getClass().getSimpleName(), this.hashCode(), this.getProtocols());
   }

   public static ConnectionFactory[] getFactories(SslContextFactory.Server sslContextFactory, ConnectionFactory... factories) {
      factories = (ConnectionFactory[])ArrayUtil.removeNulls(factories);
      if (sslContextFactory == null) {
         return factories;
      } else {
         for(ConnectionFactory factory : factories) {
            if (factory instanceof HttpConfiguration.ConnectionFactory) {
               HttpConfiguration config = ((HttpConfiguration.ConnectionFactory)factory).getHttpConfiguration();
               if (config.getCustomizer(SecureRequestCustomizer.class) == null) {
                  config.addCustomizer(new SecureRequestCustomizer());
               }
            }
         }

         return (ConnectionFactory[])ArrayUtil.prependToArray(new SslConnectionFactory(sslContextFactory, factories[0].getProtocol()), factories, ConnectionFactory.class);
      }
   }
}
