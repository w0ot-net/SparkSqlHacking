package org.sparkproject.jetty.server;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Set;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.Attributes;

public class ProxyCustomizer implements HttpConfiguration.Customizer {
   public static final String REMOTE_ADDRESS_ATTRIBUTE_NAME = "org.sparkproject.jetty.proxy.remote.address";
   public static final String REMOTE_PORT_ATTRIBUTE_NAME = "org.sparkproject.jetty.proxy.remote.port";
   public static final String LOCAL_ADDRESS_ATTRIBUTE_NAME = "org.sparkproject.jetty.proxy.local.address";
   public static final String LOCAL_PORT_ATTRIBUTE_NAME = "org.sparkproject.jetty.proxy.local.port";

   public void customize(Connector connector, HttpConfiguration channelConfig, Request request) {
      EndPoint endPoint = request.getHttpChannel().getEndPoint();
      if (endPoint instanceof ProxyConnectionFactory.ProxyEndPoint) {
         EndPoint underlyingEndpoint = ((ProxyConnectionFactory.ProxyEndPoint)endPoint).unwrap();
         request.setAttributes(new ProxyAttributes(underlyingEndpoint.getLocalSocketAddress(), underlyingEndpoint.getRemoteSocketAddress(), request.getAttributes()));
      }

   }

   private static class ProxyAttributes extends Attributes.Wrapper {
      private final String _remoteAddress;
      private final String _localAddress;
      private final int _remotePort;
      private final int _localPort;

      private ProxyAttributes(SocketAddress local, SocketAddress remote, Attributes attributes) {
         super(attributes);
         InetSocketAddress inetLocal = local instanceof InetSocketAddress ? (InetSocketAddress)local : null;
         InetSocketAddress inetRemote = remote instanceof InetSocketAddress ? (InetSocketAddress)remote : null;
         this._localAddress = inetLocal == null ? null : inetLocal.getAddress().getHostAddress();
         this._remoteAddress = inetRemote == null ? null : inetRemote.getAddress().getHostAddress();
         this._localPort = inetLocal == null ? 0 : inetLocal.getPort();
         this._remotePort = inetRemote == null ? 0 : inetRemote.getPort();
      }

      public Object getAttribute(String name) {
         byte var3 = -1;
         switch (name.hashCode()) {
            case -1784563155:
               if (name.equals("org.sparkproject.jetty.proxy.remote.address")) {
                  var3 = 0;
               }
               break;
            case -1145130619:
               if (name.equals("org.sparkproject.jetty.proxy.local.port")) {
                  var3 = 3;
               }
               break;
            case -416500624:
               if (name.equals("org.sparkproject.jetty.proxy.local.address")) {
                  var3 = 2;
               }
               break;
            case 687656168:
               if (name.equals("org.sparkproject.jetty.proxy.remote.port")) {
                  var3 = 1;
               }
         }

         switch (var3) {
            case 0:
               return this._remoteAddress;
            case 1:
               return this._remotePort;
            case 2:
               return this._localAddress;
            case 3:
               return this._localPort;
            default:
               return super.getAttribute(name);
         }
      }

      public Set getAttributeNameSet() {
         Set<String> names = new HashSet(this._attributes.getAttributeNameSet());
         names.remove("org.sparkproject.jetty.proxy.remote.address");
         names.remove("org.sparkproject.jetty.proxy.local.address");
         if (this._remoteAddress != null) {
            names.add("org.sparkproject.jetty.proxy.remote.address");
         }

         if (this._localAddress != null) {
            names.add("org.sparkproject.jetty.proxy.local.address");
         }

         names.add("org.sparkproject.jetty.proxy.remote.port");
         names.add("org.sparkproject.jetty.proxy.local.port");
         return names;
      }
   }
}
