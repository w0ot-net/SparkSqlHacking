package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.NetClientImpl;
import io.vertx.core.net.impl.NetSocketImpl;
import io.vertx.core.net.impl.VertxHandler;
import io.vertx.core.spi.metrics.ClientMetrics;
import io.vertx.core.spi.metrics.HttpClientMetrics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpChannelConnector {
   private final HttpClientBase client;
   private final NetClientImpl netClient;
   private final HttpClientOptions options;
   private final ProxyOptions proxyOptions;
   private final ClientMetrics metrics;
   private final boolean ssl;
   private final boolean useAlpn;
   private final HttpVersion version;
   private final SocketAddress peerAddress;
   private final SocketAddress server;

   public HttpChannelConnector(HttpClientBase client, NetClientImpl netClient, ProxyOptions proxyOptions, ClientMetrics metrics, HttpVersion version, boolean ssl, boolean useAlpn, SocketAddress peerAddress, SocketAddress server) {
      this.client = client;
      this.netClient = netClient;
      this.metrics = metrics;
      this.options = client.options();
      this.proxyOptions = proxyOptions;
      this.ssl = ssl;
      this.useAlpn = useAlpn;
      this.version = version;
      this.peerAddress = peerAddress;
      this.server = server;
   }

   public SocketAddress server() {
      return this.server;
   }

   private void connect(ContextInternal context, Promise promise) {
      this.netClient.connectInternal(this.proxyOptions, this.server, this.peerAddress, this.options.isForceSni() ? this.peerAddress.host() : null, this.ssl, this.useAlpn, false, promise, context, 0);
   }

   public Future wrap(ContextInternal context, NetSocket so_) {
      NetSocketImpl so = (NetSocketImpl)so_;
      Object metric = so.metric();
      PromiseInternal<HttpClientConnection> promise = context.promise();
      ChannelPipeline pipeline = so.channelHandlerContext().pipeline();
      List<ChannelHandler> removedHandlers = new ArrayList();

      for(Map.Entry stringChannelHandlerEntry : pipeline) {
         ChannelHandler handler = (ChannelHandler)stringChannelHandlerEntry.getValue();
         if (!(handler instanceof SslHandler)) {
            removedHandlers.add(handler);
         }
      }

      removedHandlers.forEach(pipeline::remove);
      Channel ch = so.channelHandlerContext().channel();
      if (this.ssl) {
         String protocol = so.applicationLayerProtocol();
         if (this.useAlpn) {
            if ("h2".equals(protocol)) {
               this.applyHttp2ConnectionOptions(ch.pipeline());
               this.http2Connected(context, metric, ch, promise);
            } else {
               this.applyHttp1xConnectionOptions(ch.pipeline());
               HttpVersion fallbackProtocol = "http/1.0".equals(protocol) ? HttpVersion.HTTP_1_0 : HttpVersion.HTTP_1_1;
               this.http1xConnected(fallbackProtocol, this.server, true, context, metric, ch, promise);
            }
         } else {
            this.applyHttp1xConnectionOptions(ch.pipeline());
            this.http1xConnected(this.version, this.server, true, context, metric, ch, promise);
         }
      } else if (this.version == HttpVersion.HTTP_2) {
         if (this.options.isHttp2ClearTextUpgrade()) {
            this.applyHttp1xConnectionOptions(pipeline);
            this.http1xConnected(this.version, this.server, false, context, metric, ch, promise);
         } else {
            this.applyHttp2ConnectionOptions(pipeline);
            this.http2Connected(context, metric, ch, promise);
         }
      } else {
         this.applyHttp1xConnectionOptions(pipeline);
         this.http1xConnected(this.version, this.server, false, context, metric, ch, promise);
      }

      return promise.future();
   }

   public void httpConnect(ContextInternal context, Handler handler) {
      Promise<NetSocket> promise = context.promise();
      Future<HttpClientConnection> fut = promise.future().flatMap((so) -> this.wrap(context, so));
      fut.onComplete(handler);
      this.connect(context, promise);
   }

   private void applyHttp2ConnectionOptions(ChannelPipeline pipeline) {
      int idleTimeout = this.options.getIdleTimeout();
      int readIdleTimeout = this.options.getReadIdleTimeout();
      int writeIdleTimeout = this.options.getWriteIdleTimeout();
      if (idleTimeout > 0 || readIdleTimeout > 0 || writeIdleTimeout > 0) {
         pipeline.addLast("idle", new IdleStateHandler((long)readIdleTimeout, (long)writeIdleTimeout, (long)idleTimeout, this.options.getIdleTimeoutUnit()));
      }

   }

   private void applyHttp1xConnectionOptions(ChannelPipeline pipeline) {
      int idleTimeout = this.options.getIdleTimeout();
      int readIdleTimeout = this.options.getReadIdleTimeout();
      int writeIdleTimeout = this.options.getWriteIdleTimeout();
      if (idleTimeout > 0 || readIdleTimeout > 0 || writeIdleTimeout > 0) {
         pipeline.addLast("idle", new IdleStateHandler((long)readIdleTimeout, (long)writeIdleTimeout, (long)idleTimeout, this.options.getIdleTimeoutUnit()));
      }

      if (this.options.getLogActivity()) {
         pipeline.addLast("logging", new LoggingHandler(this.options.getActivityLogDataFormat()));
      }

      pipeline.addLast("codec", new HttpClientCodec(this.options.getMaxInitialLineLength(), this.options.getMaxHeaderSize(), this.options.getMaxChunkSize(), false, !HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION, this.options.getDecoderInitialBufferSize()));
      if (this.options.isDecompressionSupported()) {
         pipeline.addLast("inflater", new HttpContentDecompressor(false));
      }

   }

   private void http1xConnected(HttpVersion version, SocketAddress server, boolean ssl, ContextInternal context, Object socketMetric, Channel ch, Promise future) {
      boolean upgrade = version == HttpVersion.HTTP_2 && this.options.isHttp2ClearTextUpgrade();
      VertxHandler<Http1xClientConnection> clientHandler = VertxHandler.create((chctx) -> {
         HttpClientMetrics met = this.client.metrics();
         Http1xClientConnection conn = new Http1xClientConnection(upgrade ? HttpVersion.HTTP_1_1 : version, this.client, chctx, ssl, server, context, this.metrics);
         if (met != null) {
            conn.metric(socketMetric);
            met.endpointConnected(this.metrics);
         }

         return conn;
      });
      clientHandler.addHandler((conn) -> {
         if (upgrade) {
            boolean preflightRequest = this.options.isHttp2ClearTextUpgradeWithPreflightRequest();
            if (preflightRequest) {
               Http2UpgradeClientConnection conn2 = new Http2UpgradeClientConnection(this.client, conn);
               conn2.concurrencyChangeHandler((concurrency) -> {
               });
               conn2.createStream(conn.getContext(), (ar) -> {
                  if (ar.succeeded()) {
                     HttpClientStream stream = (HttpClientStream)ar.result();
                     stream.headHandler((resp) -> {
                        Http2UpgradeClientConnection connection = (Http2UpgradeClientConnection)stream.connection();
                        HttpClientConnection unwrap = connection.unwrap();
                        future.tryComplete(unwrap);
                     });
                     stream.exceptionHandler(future::tryFail);
                     HttpRequestHead request = new HttpRequestHead(HttpMethod.OPTIONS, "/", HttpHeaders.headers(), server.toString(), "http://" + server + "/", (String)null);
                     stream.writeHead(request, false, (ByteBuf)null, true, (StreamPriority)null, false, (Handler)null);
                  } else {
                     future.fail(ar.cause());
                  }

               });
            } else {
               future.complete(new Http2UpgradeClientConnection(this.client, conn));
            }
         } else {
            future.complete(conn);
         }

      });
      ch.pipeline().addLast("handler", clientHandler);
   }

   private void http2Connected(ContextInternal context, Object metric, Channel ch, PromiseInternal promise) {
      VertxHttp2ConnectionHandler<Http2ClientConnection> clientHandler;
      try {
         clientHandler = Http2ClientConnection.createHttp2ConnectionHandler(this.client, this.metrics, context, false, metric);
         ch.pipeline().addLast("handler", clientHandler);
         ch.flush();
      } catch (Exception e) {
         this.connectFailed(ch, e, promise);
         return;
      }

      clientHandler.connectFuture().addListener(promise);
   }

   private void connectFailed(Channel ch, Throwable t, Promise future) {
      if (ch != null) {
         try {
            ch.close();
         } catch (Exception var5) {
         }
      }

      future.tryFail(t);
   }
}
