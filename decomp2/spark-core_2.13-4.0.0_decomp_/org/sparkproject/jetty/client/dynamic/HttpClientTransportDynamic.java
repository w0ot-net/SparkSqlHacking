package org.sparkproject.jetty.client.dynamic;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.alpn.client.ALPNClientConnection;
import org.sparkproject.jetty.alpn.client.ALPNClientConnectionFactory;
import org.sparkproject.jetty.client.AbstractConnectorHttpClientTransport;
import org.sparkproject.jetty.client.HttpClient;
import org.sparkproject.jetty.client.HttpDestination;
import org.sparkproject.jetty.client.HttpRequest;
import org.sparkproject.jetty.client.MultiplexConnectionPool;
import org.sparkproject.jetty.client.MultiplexHttpDestination;
import org.sparkproject.jetty.client.Origin;
import org.sparkproject.jetty.client.http.HttpClientConnectionFactory;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpVersion;
import org.sparkproject.jetty.io.ClientConnectionFactory;
import org.sparkproject.jetty.io.ClientConnector;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;

public class HttpClientTransportDynamic extends AbstractConnectorHttpClientTransport {
   private static final Logger LOG = LoggerFactory.getLogger(HttpClientTransportDynamic.class);
   private final List factoryInfos;
   private final List protocols;

   public HttpClientTransportDynamic() {
      this(HttpClientConnectionFactory.HTTP11);
   }

   public HttpClientTransportDynamic(ClientConnectionFactory.Info... factoryInfos) {
      this(findClientConnector(factoryInfos), factoryInfos);
   }

   public HttpClientTransportDynamic(ClientConnector connector, ClientConnectionFactory.Info... factoryInfos) {
      super(connector);
      if (factoryInfos.length == 0) {
         factoryInfos = new ClientConnectionFactory.Info[]{HttpClientConnectionFactory.HTTP11};
      }

      this.factoryInfos = Arrays.asList(factoryInfos);
      this.protocols = (List)Arrays.stream(factoryInfos).flatMap((info) -> Stream.concat(info.getProtocols(false).stream(), info.getProtocols(true).stream())).distinct().map((p) -> p.toLowerCase(Locale.ENGLISH)).collect(Collectors.toList());
      Arrays.stream(factoryInfos).forEach(this::addBean);
      this.setConnectionPoolFactory((destination) -> new MultiplexConnectionPool(destination, destination.getHttpClient().getMaxConnectionsPerDestination(), destination, 1));
   }

   private static ClientConnector findClientConnector(ClientConnectionFactory.Info[] infos) {
      return (ClientConnector)Arrays.stream(infos).flatMap((info) -> info.getContainedBeans(ClientConnector.class).stream()).findFirst().orElseGet(ClientConnector::new);
   }

   public Origin newOrigin(HttpRequest request) {
      boolean secure = HttpClient.isSchemeSecure(request.getScheme());
      String http1 = "http/1.1";
      String http2 = secure ? "h2" : "h2c";
      List<String> protocols = List.of();
      if (request.isVersionExplicit()) {
         HttpVersion version = request.getVersion();
         String desired = version == HttpVersion.HTTP_2 ? http2 : http1;
         if (this.protocols.contains(desired)) {
            protocols = List.of(desired);
         }
      } else if (secure) {
         List<String> http = List.of("http/1.1", "h2c", "h2");
         Stream var10000 = this.protocols.stream();
         Objects.requireNonNull(http);
         protocols = (List)var10000.filter(http::contains).collect(Collectors.toCollection(ArrayList::new));
         if (request.getHeaders().contains(HttpHeader.UPGRADE, "h2c")) {
            protocols.remove("h2");
         }
      } else {
         protocols = List.of((String)this.protocols.get(0));
      }

      Origin.Protocol protocol = null;
      if (!protocols.isEmpty()) {
         protocol = new Origin.Protocol(protocols, secure && protocols.contains(http2));
      }

      return this.getHttpClient().createOrigin(request, protocol);
   }

   public HttpDestination newHttpDestination(Origin origin) {
      SocketAddress address = origin.getAddress().getSocketAddress();
      return new MultiplexHttpDestination(this.getHttpClient(), origin, this.getClientConnector().isIntrinsicallySecure(address));
   }

   public Connection newConnection(EndPoint endPoint, Map context) throws IOException {
      HttpDestination destination = (HttpDestination)context.get("org.sparkproject.jetty.client.destination");
      Origin.Protocol protocol = destination.getOrigin().getProtocol();
      ClientConnectionFactory factory;
      if (protocol == null) {
         factory = ((ClientConnectionFactory.Info)this.factoryInfos.get(0)).getClientConnectionFactory();
      } else {
         SocketAddress address = destination.getOrigin().getAddress().getSocketAddress();
         boolean intrinsicallySecure = this.getClientConnector().isIntrinsicallySecure(address);
         if (!intrinsicallySecure && destination.isSecure() && protocol.isNegotiate()) {
            factory = new ALPNClientConnectionFactory(this.getClientConnector().getExecutor(), this::newNegotiatedConnection, protocol.getProtocols());
         } else {
            factory = ((ClientConnectionFactory.Info)this.findClientConnectionFactoryInfo(protocol.getProtocols(), destination.isSecure()).orElseThrow(() -> {
               String var10002 = ClientConnectionFactory.class.getSimpleName();
               return new IOException("Cannot find " + var10002 + " for " + String.valueOf(protocol));
            })).getClientConnectionFactory();
         }
      }

      return factory.newConnection(endPoint, context);
   }

   public void upgrade(EndPoint endPoint, Map context) {
      HttpDestination destination = (HttpDestination)context.get("org.sparkproject.jetty.client.destination");
      Origin.Protocol protocol = destination.getOrigin().getProtocol();
      ClientConnectionFactory.Info info = (ClientConnectionFactory.Info)this.findClientConnectionFactoryInfo(protocol.getProtocols(), destination.isSecure()).orElseThrow(() -> {
         String var10002 = ClientConnectionFactory.class.getSimpleName();
         return new IllegalStateException("Cannot find " + var10002 + " to upgrade to " + String.valueOf(protocol));
      });
      info.upgrade(endPoint, context);
   }

   protected Connection newNegotiatedConnection(EndPoint endPoint, Map context) throws IOException {
      try {
         ALPNClientConnection alpnConnection = (ALPNClientConnection)endPoint.getConnection();
         String protocol = alpnConnection.getProtocol();
         ClientConnectionFactory.Info factoryInfo;
         if (protocol != null) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("ALPN negotiated {} among {}", protocol, alpnConnection.getProtocols());
            }

            List<String> protocols = List.of(protocol);
            factoryInfo = (ClientConnectionFactory.Info)this.findClientConnectionFactoryInfo(protocols, true).orElseThrow(() -> {
               String var10002 = ClientConnectionFactory.class.getSimpleName();
               return new IOException("Cannot find " + var10002 + " for negotiated protocol " + protocol);
            });
         } else {
            factoryInfo = (ClientConnectionFactory.Info)this.factoryInfos.get(0);
            if (LOG.isDebugEnabled()) {
               LOG.debug("No ALPN protocol, using {}", factoryInfo);
            }
         }

         return factoryInfo.getClientConnectionFactory().newConnection(endPoint, context);
      } catch (Throwable failure) {
         this.connectFailed(context, failure);
         throw failure;
      }
   }

   private Optional findClientConnectionFactoryInfo(List protocols, boolean secure) {
      return this.factoryInfos.stream().filter((info) -> info.matches(protocols, secure)).findFirst();
   }
}
