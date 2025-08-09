package org.sparkproject.jetty.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Connection;
import org.sparkproject.jetty.client.api.Destination;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.io.ClientConnectionFactory;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.Attachable;
import org.sparkproject.jetty.util.Promise;
import org.sparkproject.jetty.util.ssl.SslContextFactory;

public class HttpProxy extends ProxyConfiguration.Proxy {
   private static final Logger LOG = LoggerFactory.getLogger(HttpProxy.class);

   public HttpProxy(String host, int port) {
      this(new Origin.Address(host, port), false);
   }

   public HttpProxy(Origin.Address address, boolean secure) {
      this(address, secure, (SslContextFactory.Client)null, new Origin.Protocol(List.of("http/1.1"), false));
   }

   public HttpProxy(Origin.Address address, boolean secure, Origin.Protocol protocol) {
      this(address, secure, (SslContextFactory.Client)null, (Origin.Protocol)Objects.requireNonNull(protocol));
   }

   public HttpProxy(Origin.Address address, SslContextFactory.Client sslContextFactory) {
      this(address, true, sslContextFactory, new Origin.Protocol(List.of("http/1.1"), false));
   }

   public HttpProxy(Origin.Address address, SslContextFactory.Client sslContextFactory, Origin.Protocol protocol) {
      this(address, true, sslContextFactory, (Origin.Protocol)Objects.requireNonNull(protocol));
   }

   private HttpProxy(Origin.Address address, boolean secure, SslContextFactory.Client sslContextFactory, Origin.Protocol protocol) {
      super(address, secure, sslContextFactory, (Origin.Protocol)Objects.requireNonNull(protocol));
   }

   public ClientConnectionFactory newClientConnectionFactory(ClientConnectionFactory connectionFactory) {
      return new HttpProxyClientConnectionFactory(connectionFactory);
   }

   public URI getURI() {
      return URI.create(this.getOrigin().asString());
   }

   boolean requiresTunnel(Origin serverOrigin) {
      if (HttpClient.isSchemeSecure(serverOrigin.getScheme())) {
         return true;
      } else {
         Origin.Protocol serverProtocol = serverOrigin.getProtocol();
         if (serverProtocol == null) {
            return true;
         } else {
            List<String> serverProtocols = serverProtocol.getProtocols();
            return this.getProtocol().getProtocols().stream().noneMatch((p) -> this.protocolMatches(p, serverProtocols));
         }
      }
   }

   private boolean protocolMatches(String protocol, List protocols) {
      return protocols.stream().anyMatch((p) -> protocol.equalsIgnoreCase(p) || this.isHTTP2(p) && this.isHTTP2(protocol));
   }

   private boolean isHTTP2(String protocol) {
      return "h2".equalsIgnoreCase(protocol) || "h2c".equalsIgnoreCase(protocol);
   }

   private class HttpProxyClientConnectionFactory implements ClientConnectionFactory {
      private final ClientConnectionFactory connectionFactory;

      private HttpProxyClientConnectionFactory(ClientConnectionFactory connectionFactory) {
         this.connectionFactory = connectionFactory;
      }

      public org.sparkproject.jetty.io.Connection newConnection(EndPoint endPoint, Map context) throws IOException {
         HttpDestination destination = (HttpDestination)context.get("org.sparkproject.jetty.client.destination");
         return HttpProxy.this.requiresTunnel(destination.getOrigin()) ? this.newProxyConnection(endPoint, context) : this.connectionFactory.newConnection(endPoint, context);
      }

      private org.sparkproject.jetty.io.Connection newProxyConnection(EndPoint endPoint, Map context) throws IOException {
         HttpDestination destination = (HttpDestination)context.get("org.sparkproject.jetty.client.destination");
         HttpClient client = destination.getHttpClient();
         HttpDestination proxyDestination = client.resolveDestination(HttpProxy.this.getOrigin());
         context.put("org.sparkproject.jetty.client.destination", proxyDestination);
         Promise<Connection> promise = (Promise)context.get("org.sparkproject.jetty.client.connection.promise");
         CreateTunnelPromise tunnelPromise = new CreateTunnelPromise(this.connectionFactory, endPoint, destination, promise, context);
         context.put("org.sparkproject.jetty.client.connection.promise", tunnelPromise);
         return this.connectionFactory.newConnection(endPoint, context);
      }
   }

   private static class CreateTunnelPromise implements Promise {
      private final ClientConnectionFactory connectionFactory;
      private final EndPoint endPoint;
      private final HttpDestination destination;
      private final Promise promise;
      private final Map context;

      private CreateTunnelPromise(ClientConnectionFactory connectionFactory, EndPoint endPoint, HttpDestination destination, Promise promise, Map context) {
         this.connectionFactory = connectionFactory;
         this.endPoint = endPoint;
         this.destination = destination;
         this.promise = promise;
         this.context = context;
      }

      public void succeeded(Connection connection) {
         this.context.put("org.sparkproject.jetty.client.destination", this.destination);
         this.context.put("org.sparkproject.jetty.client.connection.promise", this.promise);
         this.tunnel(connection);
      }

      public void failed(Throwable x) {
         this.tunnelFailed(this.endPoint, x);
      }

      private void tunnel(Connection connection) {
         String target = this.destination.getOrigin().getAddress().asString();
         HttpClient httpClient = this.destination.getHttpClient();
         long connectTimeout = httpClient.getConnectTimeout();
         Request connect = (new TunnelRequest(httpClient, this.destination.getProxy().getURI())).path(target).headers((headers) -> headers.put(HttpHeader.HOST, target)).timeout(connectTimeout, TimeUnit.MILLISECONDS);
         HttpDestination proxyDestination = httpClient.resolveDestination(this.destination.getProxy().getOrigin());
         connect.attribute(Connection.class.getName(), new ProxyConnection(proxyDestination, connection, this.promise));
         connection.send(connect, new TunnelListener(connect));
      }

      private void tunnelSucceeded(EndPoint endPoint) {
         try {
            HttpDestination destination = (HttpDestination)this.context.get("org.sparkproject.jetty.client.destination");
            ClientConnectionFactory factory = this.connectionFactory;
            if (destination.isSecure()) {
               InetSocketAddress address = InetSocketAddress.createUnresolved(destination.getHost(), destination.getPort());
               this.context.put("org.sparkproject.jetty.client.connector.remoteSocketAddress", address);
               factory = destination.newSslClientConnectionFactory((SslContextFactory.Client)null, factory);
            }

            org.sparkproject.jetty.io.Connection oldConnection = endPoint.getConnection();
            org.sparkproject.jetty.io.Connection newConnection = factory.newConnection(endPoint, this.context);
            if (HttpProxy.LOG.isDebugEnabled()) {
               HttpProxy.LOG.debug("HTTP tunnel established: {} over {}", oldConnection, newConnection);
            }

            endPoint.upgrade(newConnection);
         } catch (Throwable x) {
            this.tunnelFailed(endPoint, x);
         }

      }

      private void tunnelFailed(EndPoint endPoint, Throwable failure) {
         endPoint.close(failure);
         this.promise.failed(failure);
      }

      private class TunnelListener extends Response.Listener.Adapter {
         private final HttpConversation conversation;

         private TunnelListener(Request request) {
            this.conversation = ((HttpRequest)request).getConversation();
         }

         public void onHeaders(Response response) {
            EndPoint endPoint = (EndPoint)this.conversation.getAttribute(EndPoint.class.getName());
            if (response.getStatus() == 200) {
               CreateTunnelPromise.this.tunnelSucceeded(endPoint);
            } else {
               HttpResponseException failure = new HttpResponseException("Unexpected " + String.valueOf(response) + " for " + String.valueOf(response.getRequest()), response);
               CreateTunnelPromise.this.tunnelFailed(endPoint, failure);
            }

         }

         public void onComplete(Result result) {
            if (result.isFailed()) {
               CreateTunnelPromise.this.tunnelFailed(CreateTunnelPromise.this.endPoint, result.getFailure());
            }

         }
      }
   }

   private static class ProxyConnection implements Connection, Attachable {
      private final Destination destination;
      private final Connection connection;
      private final Promise promise;
      private Object attachment;

      private ProxyConnection(Destination destination, Connection connection, Promise promise) {
         this.destination = destination;
         this.connection = connection;
         this.promise = promise;
      }

      public SocketAddress getLocalSocketAddress() {
         return this.connection.getLocalSocketAddress();
      }

      public SocketAddress getRemoteSocketAddress() {
         return this.connection.getRemoteSocketAddress();
      }

      public void send(Request request, Response.CompleteListener listener) {
         if (this.connection.isClosed()) {
            this.destination.newConnection(new TunnelPromise(request, listener, this.promise));
         } else {
            this.connection.send(request, listener);
         }

      }

      public void close() {
         this.connection.close();
      }

      public boolean isClosed() {
         return this.connection.isClosed();
      }

      public void setAttachment(Object obj) {
         this.attachment = obj;
      }

      public Object getAttachment() {
         return this.attachment;
      }
   }

   private static class TunnelPromise implements Promise {
      private final Request request;
      private final Response.CompleteListener listener;
      private final Promise promise;

      private TunnelPromise(Request request, Response.CompleteListener listener, Promise promise) {
         this.request = request;
         this.listener = listener;
         this.promise = promise;
      }

      public void succeeded(Connection connection) {
         connection.send(this.request, this.listener);
      }

      public void failed(Throwable x) {
         this.promise.failed(x);
      }
   }

   public static class TunnelRequest extends HttpRequest {
      private final URI proxyURI;

      private TunnelRequest(HttpClient client, URI proxyURI) {
         this(client, new HttpConversation(), proxyURI);
      }

      private TunnelRequest(HttpClient client, HttpConversation conversation, URI proxyURI) {
         super(client, conversation, proxyURI);
         this.proxyURI = proxyURI;
         this.method(HttpMethod.CONNECT);
      }

      HttpRequest copyInstance(URI newURI) {
         return new TunnelRequest(this.getHttpClient(), this.getConversation(), newURI);
      }

      public URI getURI() {
         return this.proxyURI;
      }
   }
}
