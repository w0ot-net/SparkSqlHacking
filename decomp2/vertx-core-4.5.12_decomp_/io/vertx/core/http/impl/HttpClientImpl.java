package io.vertx.core.http.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.PoolOptions;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.impl.CloseFuture;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.net.HostAndPort;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.ProxyType;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.pool.ConnectionManager;
import io.vertx.core.net.impl.pool.Endpoint;
import io.vertx.core.net.impl.pool.EndpointProvider;
import io.vertx.core.net.impl.pool.Lease;
import io.vertx.core.spi.metrics.ClientMetrics;
import io.vertx.core.spi.metrics.MetricsProvider;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.util.Base64;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;

public class HttpClientImpl extends HttpClientBase implements HttpClientInternal, MetricsProvider, Closeable {
   private static final Pattern ABS_URI_START_PATTERN = Pattern.compile("^\\p{Alpha}[\\p{Alpha}\\p{Digit}+.\\-]*:");
   private static final Function DEFAULT_HANDLER = (resp) -> {
      try {
         int statusCode = resp.statusCode();
         String location = resp.getHeader(HttpHeaders.LOCATION);
         if (location != null && (statusCode == 301 || statusCode == 302 || statusCode == 303 || statusCode == 307 || statusCode == 308)) {
            HttpMethod m = resp.request().getMethod();
            if (statusCode == 303) {
               m = HttpMethod.GET;
            } else if (m != HttpMethod.GET && m != HttpMethod.HEAD) {
               return null;
            }

            URI uri = HttpUtils.resolveURIReference(resp.request().absoluteURI(), location);
            int port = uri.getPort();
            String protocol = uri.getScheme();
            char chend = protocol.charAt(protocol.length() - 1);
            boolean ssl;
            if (chend == 'p') {
               ssl = false;
               if (port == -1) {
                  port = 80;
               }
            } else {
               if (chend != 's') {
                  return null;
               }

               ssl = true;
               if (port == -1) {
                  port = 443;
               }
            }

            String requestURI = uri.getPath();
            if (requestURI == null || requestURI.isEmpty()) {
               requestURI = "/";
            }

            String query = uri.getQuery();
            if (query != null) {
               requestURI = requestURI + "?" + query;
            }

            RequestOptions options = new RequestOptions();
            options.setMethod(m);
            options.setHost(uri.getHost());
            options.setPort(port);
            options.setSsl(ssl);
            options.setURI(requestURI);
            options.setHeaders(resp.request().headers());
            options.removeHeader(HttpHeaders.CONTENT_LENGTH);
            return Future.succeededFuture(options);
         } else {
            return null;
         }
      } catch (Exception e) {
         return Future.failedFuture((Throwable)e);
      }
   };
   private static final Consumer EXPIRED_CHECKER = (endpoint) -> ((ClientHttpEndpointBase)endpoint).checkExpired();
   private final ConnectionManager httpCM;
   private final PoolOptions poolOptions;
   private volatile Handler connectionHandler;
   private volatile Function redirectHandler;
   private long timerID;

   public HttpClientImpl(VertxInternal vertx, HttpClientOptions options, PoolOptions poolOptions, CloseFuture closeFuture) {
      super(vertx, options, closeFuture);
      this.redirectHandler = DEFAULT_HANDLER;
      this.poolOptions = new PoolOptions(poolOptions);
      this.httpCM = this.httpConnectionManager();
      if (poolOptions.getCleanerPeriod() > 0 && ((long)options.getKeepAliveTimeout() > 0L || (long)options.getHttp2KeepAliveTimeout() > 0L)) {
         PoolChecker checker = new PoolChecker(this);
         ContextInternal timerContext = vertx.createEventLoopContext();
         this.timerID = timerContext.setTimer((long)options.getPoolCleanerPeriod(), checker);
      }

   }

   public NetClient netClient() {
      return this.netClient;
   }

   private void checkExpired(Handler checker) {
      this.httpCM.forEach(EXPIRED_CHECKER);
      synchronized(this) {
         if (!this.closeFuture.isClosed()) {
            this.timerID = this.vertx.setTimer((long)this.poolOptions.getCleanerPeriod(), checker);
         }

      }
   }

   private ConnectionManager httpConnectionManager() {
      return new ConnectionManager();
   }

   public Future connect(SocketAddress server) {
      return this.connect(server, (SocketAddress)null);
   }

   public Future connect(SocketAddress server, SocketAddress peer) {
      ContextInternal context = this.vertx.getOrCreateContext();
      Promise<HttpClientConnection> promise = context.promise();
      HttpChannelConnector connector = new HttpChannelConnector(this, this.netClient, (ProxyOptions)null, (ClientMetrics)null, this.options.getProtocolVersion(), this.options.isSsl(), this.options.isUseAlpn(), peer, server);
      connector.httpConnect(context, promise);
      return promise.future();
   }

   public void request(RequestOptions options, Handler handler) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      PromiseInternal<HttpClientRequest> promise = ctx.promise(handler);
      this.doRequest(options, promise);
   }

   public Future request(RequestOptions options) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      PromiseInternal<HttpClientRequest> promise = ctx.promise();
      this.doRequest(options, promise);
      return promise.future();
   }

   public void request(HttpMethod method, int port, String host, String requestURI, Handler handler) {
      this.request((new RequestOptions()).setMethod(method).setPort(port).setHost(host).setURI(requestURI), handler);
   }

   public Future request(HttpMethod method, int port, String host, String requestURI) {
      return this.request((new RequestOptions()).setMethod(method).setPort(port).setHost(host).setURI(requestURI));
   }

   public void request(HttpMethod method, String host, String requestURI, Handler handler) {
      this.request(method, this.options.getDefaultPort(), host, requestURI, handler);
   }

   public Future request(HttpMethod method, String host, String requestURI) {
      return this.request(method, this.options.getDefaultPort(), host, requestURI);
   }

   public void request(HttpMethod method, String requestURI, Handler handler) {
      this.request(method, this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI, handler);
   }

   public Future request(HttpMethod method, String requestURI) {
      return this.request(method, this.options.getDefaultPort(), this.options.getDefaultHost(), requestURI);
   }

   public void close(Promise completion) {
      synchronized(this) {
         if (this.timerID >= 0L) {
            this.vertx.cancelTimer(this.timerID);
            this.timerID = -1L;
         }
      }

      this.httpCM.close();
      super.close(completion);
   }

   public HttpClient connectionHandler(Handler handler) {
      this.connectionHandler = handler;
      return this;
   }

   public Handler connectionHandler() {
      return this.connectionHandler;
   }

   public HttpClient redirectHandler(Function handler) {
      if (handler == null) {
         handler = DEFAULT_HANDLER;
      }

      this.redirectHandler = handler;
      return this;
   }

   public Function redirectHandler() {
      return this.redirectHandler;
   }

   private void doRequest(RequestOptions request, PromiseInternal promise) {
      String host = this.getHost(request);
      int port = this.getPort(request);
      SocketAddress server = request.getServer();
      if (server == null) {
         server = SocketAddress.inetSocketAddress(port, host);
      }

      HttpMethod method = request.getMethod();
      String requestURI = request.getURI();
      Boolean ssl = request.isSsl();
      MultiMap headers = request.getHeaders();
      Boolean followRedirects = request.getFollowRedirects();
      Objects.requireNonNull(method, "no null method accepted");
      Objects.requireNonNull(host, "no null host accepted");
      Objects.requireNonNull(requestURI, "no null requestURI accepted");
      boolean useAlpn = this.options.isUseAlpn();
      boolean useSSL = ssl != null ? ssl : this.options.isSsl();
      if (!useAlpn && useSSL && this.options.getProtocolVersion() == HttpVersion.HTTP_2) {
         throw new IllegalArgumentException("Must enable ALPN when using H2");
      } else {
         this.checkClosed();
         ProxyOptions proxyOptions = this.resolveProxyOptions(request.getProxyOptions(), server);
         String peerHost;
         if (host.charAt(host.length() - 1) == '.') {
            peerHost = host.substring(0, host.length() - 1);
         } else {
            peerHost = host;
         }

         SocketAddress peerAddress = peerAddress(server, peerHost, port);
         EndpointKey key;
         if (proxyOptions != null && !useSSL && proxyOptions.getType() == ProxyType.HTTP) {
            if (!ABS_URI_START_PATTERN.matcher(requestURI).find()) {
               int defaultPort = 80;
               String addPort = port != -1 && port != defaultPort ? ":" + port : "";
               requestURI = (ssl == Boolean.TRUE ? "https://" : "http://") + host + addPort + requestURI;
            }

            if (proxyOptions.getUsername() != null && proxyOptions.getPassword() != null) {
               if (headers == null) {
                  headers = HttpHeaders.headers();
               }

               headers.add("Proxy-Authorization", "Basic " + Base64.getEncoder().encodeToString((proxyOptions.getUsername() + ":" + proxyOptions.getPassword()).getBytes()));
            }

            server = SocketAddress.inetSocketAddress(proxyOptions.getPort(), proxyOptions.getHost());
            key = new EndpointKey(useSSL, proxyOptions, server, peerAddress);
            proxyOptions = null;
         } else {
            key = new EndpointKey(useSSL, proxyOptions, server, peerAddress);
         }

         long connectTimeout = 0L;
         long idleTimeout = 0L;
         if (request.getTimeout() >= 0L) {
            connectTimeout = request.getTimeout();
            idleTimeout = request.getTimeout();
         }

         if (request.getConnectTimeout() >= 0L) {
            connectTimeout = request.getConnectTimeout();
         }

         if (request.getIdleTimeout() >= 0L) {
            idleTimeout = request.getIdleTimeout();
         }

         this.doRequest(method, server, host, port, useSSL, requestURI, headers, request.getTraceOperation(), connectTimeout, idleTimeout, followRedirects, proxyOptions, key, promise);
      }
   }

   private static SocketAddress peerAddress(SocketAddress remoteAddress, String peerHost, int peerPort) {
      return remoteAddress.isInetSocket() && peerHost.equals(remoteAddress.host()) && peerPort == remoteAddress.port() ? remoteAddress : SocketAddress.inetSocketAddress(peerPort, peerHost);
   }

   private void doRequest(HttpMethod method, SocketAddress server, String host, int port, Boolean useSSL, String requestURI, MultiMap headers, String traceOperation, long connectTimeout, long idleTimeout, Boolean followRedirects, final ProxyOptions proxyOptions, final EndpointKey key, PromiseInternal requestPromise) {
      ContextInternal ctx = requestPromise.context();
      EndpointProvider<Lease<HttpClientConnection>> provider = new EndpointProvider() {
         public Endpoint create(ContextInternal ctx, Runnable dispose) {
            int maxPoolSize = Math.max(HttpClientImpl.this.poolOptions.getHttp1MaxSize(), HttpClientImpl.this.poolOptions.getHttp2MaxSize());
            ClientMetrics metrics = HttpClientImpl.this.metrics != null ? HttpClientImpl.this.metrics.createEndpointMetrics(key.serverAddr, maxPoolSize) : null;
            HttpChannelConnector connector = new HttpChannelConnector(HttpClientImpl.this, HttpClientImpl.this.netClient, proxyOptions, metrics, HttpClientImpl.this.options.getProtocolVersion(), key.ssl, HttpClientImpl.this.options.isUseAlpn(), key.peerAddr, key.serverAddr);
            return new SharedClientHttpStreamEndpoint(HttpClientImpl.this, metrics, HttpClientImpl.this.poolOptions.getMaxWaitQueueSize(), HttpClientImpl.this.poolOptions.getHttp1MaxSize(), HttpClientImpl.this.poolOptions.getHttp2MaxSize(), connector, dispose);
         }
      };
      long now = System.currentTimeMillis();
      this.httpCM.getConnection(ctx, key, provider, connectTimeout, (ar1) -> {
         if (ar1.succeeded()) {
            Lease<HttpClientConnection> lease = (Lease)ar1.result();
            HttpClientConnection conn = (HttpClientConnection)lease.get();
            conn.createStream(ctx, (ar2) -> {
               if (ar2.succeeded()) {
                  HttpClientStream stream = (HttpClientStream)ar2.result();
                  stream.closeHandler((v) -> lease.recycle());
                  HttpClientRequest req = this.createRequest(stream);
                  req.setMethod(method);
                  req.authority(HostAndPort.create(host, port));
                  req.setURI(requestURI);
                  req.traceOperation(traceOperation);
                  if (headers != null) {
                     req.headers().setAll(headers);
                  }

                  if (followRedirects != null) {
                     req.setFollowRedirects(followRedirects);
                  }

                  if (idleTimeout > 0L) {
                     req.idleTimeout(idleTimeout);
                  }

                  requestPromise.tryComplete(req);
               } else {
                  requestPromise.tryFail(ar2.cause());
               }

            });
         } else {
            requestPromise.tryFail(ar1.cause());
         }

      });
   }

   private void checkClosed() {
      if (this.closeFuture.isClosed()) {
         throw new IllegalStateException("Client is closed");
      }
   }

   Future createRequest(HttpClientConnection conn, ContextInternal context) {
      PromiseInternal<HttpClientStream> promise = context.promise();
      conn.createStream(context, promise);
      return promise.map(this::createRequest);
   }

   private HttpClientRequest createRequest(HttpClientStream stream) {
      HttpClientRequest request = new HttpClientRequestImpl(stream, stream.getContext().promise());
      Function<HttpClientResponse, Future<RequestOptions>> rHandler = this.redirectHandler;
      if (rHandler != null) {
         request.setMaxRedirects(this.options.getMaxRedirects());
         request.redirectHandler((resp) -> {
            Future<RequestOptions> fut_ = (Future)rHandler.apply(resp);
            return fut_ != null ? fut_.compose(this::request) : null;
         });
      }

      return request;
   }

   private static class PoolChecker implements Handler {
      final WeakReference ref;

      private PoolChecker(HttpClientImpl client) {
         this.ref = new WeakReference(client);
      }

      public void handle(Long event) {
         HttpClientImpl client = (HttpClientImpl)this.ref.get();
         if (client != null) {
            client.checkExpired(this);
         }

      }
   }
}
