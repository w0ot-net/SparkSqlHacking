package io.vertx.core.http.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebSocketConnectOptions;
import io.vertx.core.http.WebsocketVersion;
import io.vertx.core.impl.CloseFuture;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.SSLOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.NetClientBuilder;
import io.vertx.core.net.impl.NetClientImpl;
import io.vertx.core.net.impl.ProxyFilter;
import io.vertx.core.net.impl.pool.ConnectionManager;
import io.vertx.core.net.impl.pool.ConnectionPool;
import io.vertx.core.net.impl.pool.Endpoint;
import io.vertx.core.net.impl.pool.EndpointProvider;
import io.vertx.core.spi.metrics.ClientMetrics;
import io.vertx.core.spi.metrics.HttpClientMetrics;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.metrics.MetricsProvider;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HttpClientBase implements MetricsProvider, Closeable {
   protected final VertxInternal vertx;
   protected final HttpClientOptions options;
   private final ConnectionManager webSocketCM;
   protected final NetClientImpl netClient;
   protected final HttpClientMetrics metrics;
   private final boolean keepAlive;
   private final boolean pipelining;
   protected final CloseFuture closeFuture;
   private Predicate proxyFilter;
   private final Function contextProvider;
   final boolean useH2UniformStreamByteDistributor;

   public HttpClientBase(VertxInternal vertx, HttpClientOptions options, CloseFuture closeFuture) {
      this.vertx = vertx;
      this.metrics = vertx.metricsSPI() != null ? vertx.metricsSPI().createHttpClientMetrics(options) : null;
      this.options = new HttpClientOptions(options);
      this.closeFuture = closeFuture;
      List<HttpVersion> alpnVersions = options.getAlpnVersions();
      if (alpnVersions == null || alpnVersions.isEmpty()) {
         switch (options.getProtocolVersion()) {
            case HTTP_2:
               alpnVersions = Arrays.asList(HttpVersion.HTTP_2, HttpVersion.HTTP_1_1);
               break;
            default:
               alpnVersions = Collections.singletonList(options.getProtocolVersion());
         }
      }

      this.useH2UniformStreamByteDistributor = HttpUtils.useH2UniformStreamByteDistributor();
      this.keepAlive = options.isKeepAlive();
      this.pipelining = options.isPipelining();
      if (!this.keepAlive && this.pipelining) {
         throw new IllegalStateException("Cannot have pipelining with no keep alive");
      } else {
         this.proxyFilter = options.getNonProxyHosts() != null ? ProxyFilter.nonProxyHosts(options.getNonProxyHosts()) : ProxyFilter.DEFAULT_PROXY_FILTER;
         this.netClient = (NetClientImpl)(new NetClientBuilder(vertx, (new NetClientOptions(options)).setHostnameVerificationAlgorithm(options.isVerifyHost() ? "HTTPS" : "").setProxyOptions((ProxyOptions)null).setApplicationLayerProtocols((List)alpnVersions.stream().map(HttpVersion::alpnName).collect(Collectors.toList())))).metrics(this.metrics).closeFuture(closeFuture).build();
         this.webSocketCM = this.webSocketConnectionManager();
         int eventLoopSize = options.getPoolEventLoopSize();
         if (eventLoopSize > 0) {
            ContextInternal[] eventLoops = new ContextInternal[eventLoopSize];

            for(int i = 0; i < eventLoopSize; ++i) {
               eventLoops[i] = vertx.createEventLoopContext();
            }

            AtomicInteger idx = new AtomicInteger();
            this.contextProvider = (ctx) -> {
               int i = idx.getAndIncrement();
               return eventLoops[i % eventLoopSize];
            };
         } else {
            this.contextProvider = ConnectionPool.EVENT_LOOP_CONTEXT_PROVIDER;
         }

         closeFuture.add(this.netClient);
      }
   }

   public NetClient netClient() {
      return this.netClient;
   }

   private ConnectionManager webSocketConnectionManager() {
      return new ConnectionManager();
   }

   Function contextProvider() {
      return this.contextProvider;
   }

   protected final int getPort(RequestOptions request) {
      Integer port = request.getPort();
      if (port != null) {
         return port;
      } else {
         SocketAddress server = request.getServer();
         return server != null && server.isInetSocket() ? server.port() : this.options.getDefaultPort();
      }
   }

   private ProxyOptions getProxyOptions(ProxyOptions proxyOptions) {
      if (proxyOptions == null) {
         proxyOptions = this.options.getProxyOptions();
      }

      return proxyOptions;
   }

   protected final String getHost(RequestOptions request) {
      String host = request.getHost();
      if (host != null) {
         return host;
      } else {
         SocketAddress server = request.getServer();
         return server != null && server.isInetSocket() ? server.host() : this.options.getDefaultHost();
      }
   }

   protected final ProxyOptions resolveProxyOptions(ProxyOptions proxyOptions, SocketAddress addr) {
      proxyOptions = this.getProxyOptions(proxyOptions);
      if (this.proxyFilter != null && !this.proxyFilter.test(addr)) {
         proxyOptions = null;
      }

      return proxyOptions;
   }

   HttpClientMetrics metrics() {
      return this.metrics;
   }

   public void webSocket(WebSocketConnectOptions connectOptions, Handler handler) {
      PromiseInternal<WebSocket> promise = this.vertx.promise();
      promise.future().onComplete((ar) -> {
         if (ar.succeeded()) {
            ((WebSocket)ar.result()).resume();
         }

         handler.handle(ar);
      });
      this.webSocket(connectOptions, promise);
   }

   private void webSocket(WebSocketConnectOptions connectOptions, PromiseInternal promise) {
      ContextInternal ctx = promise.context();
      int port = this.getPort(connectOptions);
      String host = this.getHost(connectOptions);
      SocketAddress addr = SocketAddress.inetSocketAddress(port, host);
      final ProxyOptions proxyOptions = this.resolveProxyOptions(connectOptions.getProxyOptions(), addr);
      final EndpointKey key = new EndpointKey(connectOptions.isSsl() != null ? connectOptions.isSsl() : this.options.isSsl(), proxyOptions, addr, addr);
      ContextInternal eventLoopContext;
      if (ctx.isEventLoopContext()) {
         eventLoopContext = ctx;
      } else {
         eventLoopContext = this.vertx.createEventLoopContext(ctx.nettyEventLoop(), ctx.workerPool(), ctx.classLoader());
      }

      EndpointProvider<HttpClientConnection> provider = new EndpointProvider() {
         public Endpoint create(ContextInternal ctx, Runnable dispose) {
            int maxPoolSize = HttpClientBase.this.options.getMaxWebSockets();
            ClientMetrics metrics = HttpClientBase.this.metrics != null ? HttpClientBase.this.metrics.createEndpointMetrics(key.serverAddr, maxPoolSize) : null;
            HttpChannelConnector connector = new HttpChannelConnector(HttpClientBase.this, HttpClientBase.this.netClient, proxyOptions, metrics, HttpVersion.HTTP_1_1, key.ssl, false, key.peerAddr, key.serverAddr);
            return new WebSocketEndpoint((ClientMetrics)null, maxPoolSize, connector, dispose);
         }
      };
      this.webSocketCM.getConnection(eventLoopContext, key, provider, (ar) -> {
         if (ar.succeeded()) {
            Http1xClientConnection conn = (Http1xClientConnection)ar.result();
            long timeout = Math.max(connectOptions.getTimeout(), 0L);
            if (connectOptions.getIdleTimeout() >= 0L) {
               timeout = connectOptions.getIdleTimeout();
            }

            conn.toWebSocket(ctx, connectOptions.getURI(), connectOptions.getHeaders(), connectOptions.getAllowOriginHeader(), connectOptions.getVersion(), connectOptions.getSubProtocols(), timeout, connectOptions.isRegisterWriteHandlers(), this.options.getMaxWebSocketFrameSize(), promise);
         } else {
            promise.fail(ar.cause());
         }

      });
   }

   public Future webSocket(int port, String host, String requestURI) {
      Promise<WebSocket> promise = this.vertx.promise();
      this.webSocket(port, host, requestURI, promise);
      return promise.future();
   }

   public Future webSocket(String host, String requestURI) {
      Promise<WebSocket> promise = this.vertx.promise();
      this.webSocket(host, requestURI, promise);
      return promise.future();
   }

   public Future webSocket(String requestURI) {
      Promise<WebSocket> promise = this.vertx.promise();
      this.webSocket((String)requestURI, (Handler)promise);
      return promise.future();
   }

   public Future webSocket(WebSocketConnectOptions options) {
      Promise<WebSocket> promise = this.vertx.promise();
      this.webSocket((WebSocketConnectOptions)options, (Handler)promise);
      return promise.future();
   }

   public Future webSocketAbs(String url, MultiMap headers, WebsocketVersion version, List subProtocols) {
      Promise<WebSocket> promise = this.vertx.promise();
      this.webSocketAbs(url, headers, version, subProtocols, promise);
      return promise.future();
   }

   public void webSocket(int port, String host, String requestURI, Handler handler) {
      this.webSocket((new WebSocketConnectOptions()).setURI(requestURI).setHost(host).setPort(port), handler);
   }

   public void webSocket(String host, String requestURI, Handler handler) {
      this.webSocket(this.options.getDefaultPort(), host, requestURI, handler);
   }

   public void webSocket(String requestURI, Handler handler) {
      this.webSocket(this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, handler);
   }

   public void webSocketAbs(String url, MultiMap headers, WebsocketVersion version, List subProtocols, Handler handler) {
      WebSocketConnectOptions options = (new WebSocketConnectOptions()).setAbsoluteURI(url).setHeaders(headers).setVersion(version).setSubProtocols(subProtocols);
      this.webSocket(options, handler);
   }

   public void close(Promise completion) {
      this.webSocketCM.close();
      completion.complete();
   }

   public void close(Handler handler) {
      this.netClient.close(handler);
   }

   public Future close() {
      return this.netClient.close();
   }

   public boolean isMetricsEnabled() {
      return this.getMetrics() != null;
   }

   public Metrics getMetrics() {
      return this.metrics;
   }

   public Future updateSSLOptions(SSLOptions options, boolean force) {
      return this.netClient.updateSSLOptions(options, force);
   }

   public HttpClientBase proxyFilter(Predicate filter) {
      this.proxyFilter = filter;
      return this;
   }

   public HttpClientOptions options() {
      return this.options;
   }

   public VertxInternal vertx() {
      return this.vertx;
   }
}
