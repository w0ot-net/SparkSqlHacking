package io.vertx.core.http.impl;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.websocketx.WebSocketDecoderConfig;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketHandshakeException;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.util.ReferenceCountUtil;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.impl.NetSocketImpl;
import io.vertx.core.net.impl.SslChannelProvider;
import io.vertx.core.net.impl.VertxHandler;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.tracing.VertxTracer;
import io.vertx.core.tracing.TracingPolicy;
import java.util.function.Supplier;

public class Http1xServerConnection extends Http1xConnectionBase implements HttpServerConnection {
   private static final Logger log = LoggerFactory.getLogger(Http1xServerConnection.class);
   private final String serverOrigin;
   private final Supplier streamContextSupplier;
   private final SslChannelProvider sslChannelProvider;
   private final TracingPolicy tracingPolicy;
   private boolean requestFailed;
   private Http1xServerRequest requestInProgress;
   private Http1xServerRequest responseInProgress;
   private boolean keepAlive;
   private boolean channelPaused;
   private boolean writable;
   private Handler requestHandler;
   private Handler invalidRequestHandler;
   final HttpServerMetrics metrics;
   final boolean handle100ContinueAutomatically;
   final HttpServerOptions options;

   public Http1xServerConnection(Supplier streamContextSupplier, SslChannelProvider sslChannelProvider, HttpServerOptions options, ChannelHandlerContext chctx, ContextInternal context, String serverOrigin, HttpServerMetrics metrics) {
      super(context, chctx);
      this.serverOrigin = serverOrigin;
      this.streamContextSupplier = streamContextSupplier;
      this.options = options;
      this.sslChannelProvider = sslChannelProvider;
      this.metrics = metrics;
      this.handle100ContinueAutomatically = options.isHandle100ContinueAutomatically();
      this.tracingPolicy = options.getTracingPolicy();
      this.writable = true;
      this.keepAlive = true;
   }

   TracingPolicy tracingPolicy() {
      return this.tracingPolicy;
   }

   public HttpServerConnection handler(Handler handler) {
      this.requestHandler = handler;
      return this;
   }

   public HttpServerConnection invalidRequestHandler(Handler handler) {
      this.invalidRequestHandler = handler;
      return this;
   }

   public HttpServerMetrics metrics() {
      return this.metrics;
   }

   public void handleMessage(Object msg) {
      assert msg != null;

      if (this.requestInProgress != null || this.keepAlive || this.webSocket != null) {
         if (msg == LastHttpContent.EMPTY_LAST_CONTENT) {
            this.onEnd();
         } else if (msg instanceof DefaultHttpRequest) {
            DefaultHttpRequest request = (DefaultHttpRequest)msg;
            ContextInternal requestCtx = (ContextInternal)this.streamContextSupplier.get();
            Http1xServerRequest req = new Http1xServerRequest(this, request, requestCtx);
            this.requestInProgress = req;
            if (this.responseInProgress != null) {
               this.enqueueRequest(req);
               return;
            }

            this.responseInProgress = this.requestInProgress;
            this.keepAlive = HttpUtils.isKeepAlive(request);
            req.handleBegin(this.writable, this.keepAlive);
            Handler<HttpServerRequest> handler = request.decoderResult().isSuccess() ? this.requestHandler : this.invalidRequestHandler;
            req.context.emit(req, handler);
         } else {
            this.handleOther(msg);
         }

      }
   }

   private void enqueueRequest(Http1xServerRequest req) {
      this.responseInProgress.enqueue(req);
      req.pause();
   }

   private void handleOther(Object msg) {
      if (!(msg instanceof DefaultHttpContent) && !(msg instanceof HttpContent)) {
         if (msg instanceof WebSocketFrame) {
            this.handleWsFrame((WebSocketFrame)msg);
         }
      } else {
         this.onContent(msg);
      }

   }

   private void onContent(Object msg) {
      HttpContent content = (HttpContent)msg;
      if (!content.decoderResult().isSuccess()) {
         this.handleError(content);
      } else {
         Buffer buffer = Buffer.buffer(VertxHandler.safeBuffer(content.content()));
         Http1xServerRequest request = this.requestInProgress;
         request.context.execute(buffer, request::handleContent);
         if (content instanceof LastHttpContent) {
            this.onEnd();
         }

      }
   }

   private void onEnd() {
      Http1xServerRequest request = this.requestInProgress;
      this.requestInProgress = null;
      boolean close = !this.keepAlive && this.responseInProgress == null;
      request.context.execute(request, Http1xServerRequest::handleEnd);
      if (close) {
         this.flushAndClose();
      }

   }

   private void flushAndClose() {
      ChannelPromise channelFuture = this.channelFuture();
      this.writeToChannel(Unpooled.EMPTY_BUFFER, channelFuture);
      channelFuture.addListener((fut) -> this.close());
   }

   void responseComplete() {
      EventLoop eventLoop = this.context.nettyEventLoop();
      if (eventLoop.inEventLoop()) {
         if (Metrics.METRICS_ENABLED) {
            this.reportResponseComplete();
         }

         Http1xServerRequest request = this.responseInProgress;
         this.responseInProgress = null;
         DecoderResult result = request.decoderResult();
         if (result.isSuccess()) {
            if (this.keepAlive) {
               Http1xServerRequest next = request.next();
               if (next != null) {
                  this.handleNext(next);
               }
            } else if (this.requestInProgress != request && this.webSocket == null) {
               this.flushAndClose();
            }
         } else {
            ChannelPromise channelFuture = this.channelFuture();
            this.writeToChannel(Unpooled.EMPTY_BUFFER, channelFuture);
            channelFuture.addListener((fut) -> this.fail(result.cause()));
         }
      } else {
         eventLoop.execute(this::responseComplete);
      }

   }

   private void handleNext(Http1xServerRequest next) {
      this.responseInProgress = next;
      this.keepAlive = HttpUtils.isKeepAlive(next.nettyRequest());
      next.handleBegin(this.writable, this.keepAlive);
      next.context.emit(next, (next_) -> {
         next_.resume();
         Handler<HttpServerRequest> handler = next_.nettyRequest().decoderResult().isSuccess() ? this.requestHandler : this.invalidRequestHandler;
         handler.handle(next_);
      });
   }

   public void doPause() {
      if (!this.channelPaused) {
         this.channelPaused = true;
         super.doPause();
      }

   }

   public void doResume() {
      if (this.channelPaused) {
         this.channelPaused = false;
         super.doResume();
      }

   }

   private void reportResponseComplete() {
      Http1xServerRequest request = this.responseInProgress;
      if (this.metrics != null) {
         this.flushBytesWritten();
         if (this.requestFailed) {
            this.metrics.requestReset(request.metric());
            this.requestFailed = false;
         } else {
            this.metrics.responseEnd(request.metric(), request.response(), request.response().bytesWritten());
         }
      }

      VertxTracer tracer = this.context.tracer();
      Object trace = request.trace();
      if (tracer != null && trace != null) {
         tracer.sendResponse(request.context, request.response(), trace, (Throwable)null, HttpUtils.SERVER_RESPONSE_TAG_EXTRACTOR);
      }

   }

   String getServerOrigin() {
      return this.serverOrigin;
   }

   Vertx vertx() {
      return this.vertx;
   }

   void createWebSocket(Http1xServerRequest request, PromiseInternal promise) {
      this.context.execute((Runnable)(() -> {
         if (request != this.responseInProgress) {
            promise.fail("Invalid request");
         } else if (this.webSocket != null) {
            promise.complete(this.webSocket);
         } else if (!(request.nettyRequest() instanceof FullHttpRequest)) {
            promise.fail(new IllegalStateException());
         } else {
            WebSocketServerHandshaker handshaker;
            try {
               handshaker = this.createHandshaker(request);
            } catch (WebSocketHandshakeException e) {
               promise.fail(e);
               return;
            }

            this.webSocket = new ServerWebSocketImpl(promise.context(), this, handshaker.version() != WebSocketVersion.V00, (long)this.options.getWebSocketClosingTimeout(), request, handshaker, this.options.getMaxWebSocketFrameSize(), this.options.getMaxWebSocketMessageSize(), this.options.isRegisterWebSocketWriteHandlers());
            if (Metrics.METRICS_ENABLED && this.metrics != null) {
               ((ServerWebSocketImpl)this.webSocket).setMetric(this.metrics.connected(this.metric(), request.metric(), (ServerWebSocket)this.webSocket));
            }

            promise.complete(this.webSocket);
         }

      }));
   }

   private WebSocketServerHandshaker createHandshaker(Http1xServerRequest request) throws WebSocketHandshakeException {
      String connectionHeader = request.getHeader(HttpHeaders.CONNECTION);
      if (connectionHeader != null && connectionHeader.toLowerCase().contains("upgrade")) {
         if (request.method() != HttpMethod.GET) {
            request.response().setStatusCode(HttpResponseStatus.METHOD_NOT_ALLOWED.code()).end();
            throw new WebSocketHandshakeException("Invalid HTTP method");
         } else {
            String wsURL;
            try {
               wsURL = HttpUtils.getWebSocketLocation(request, this.isSsl());
            } catch (Exception e) {
               request.response().setStatusCode(HttpResponseStatus.BAD_REQUEST.code()).end("Invalid request URI");
               throw new WebSocketHandshakeException("Invalid WebSocket location", e);
            }

            String subProtocols = null;
            if (this.options.getWebSocketSubProtocols() != null) {
               subProtocols = String.join(",", this.options.getWebSocketSubProtocols());
            }

            WebSocketDecoderConfig config = WebSocketDecoderConfig.newBuilder().allowExtensions(this.options.getPerMessageWebSocketCompressionSupported() || this.options.getPerFrameWebSocketCompressionSupported()).maxFramePayloadLength(this.options.getMaxWebSocketFrameSize()).allowMaskMismatch(this.options.isAcceptUnmaskedFrames()).closeOnProtocolViolation(false).build();
            WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(wsURL, subProtocols, config);
            WebSocketServerHandshaker shake = factory.newHandshaker(request.nettyRequest());
            if (shake != null) {
               return shake;
            } else {
               request.response().putHeader((CharSequence)HttpHeaderNames.SEC_WEBSOCKET_VERSION, (CharSequence)WebSocketVersion.V13.toHttpHeaderValue()).setStatusCode(HttpResponseStatus.UPGRADE_REQUIRED.code()).end();
               throw new WebSocketHandshakeException("Invalid WebSocket version");
            }
         }
      } else {
         request.response().setStatusCode(HttpResponseStatus.BAD_REQUEST.code()).end("\"Connection\" header must be \"Upgrade\".");
         throw new WebSocketHandshakeException("Invalid connection header");
      }
   }

   public void netSocket(Handler handler) {
      Future<NetSocket> fut = this.netSocket();
      if (handler != null) {
         fut.onComplete(handler);
      }

   }

   public Future netSocket() {
      Promise<NetSocket> promise = this.context.promise();
      this.netSocket(promise);
      return promise.future();
   }

   void netSocket(Promise promise) {
      this.context.execute((Runnable)(() -> {
         this.flush();
         ChannelPipeline pipeline = this.chctx.pipeline();
         ChannelHandler compressor = pipeline.get(HttpChunkContentCompressor.class);
         if (compressor != null) {
            pipeline.remove(compressor);
         }

         pipeline.remove("httpDecoder");
         if (pipeline.get("chunkedWriter") != null) {
            pipeline.remove("chunkedWriter");
         }

         pipeline.replace("handler", "handler", VertxHandler.create((ctx) -> {
            NetSocketImpl socket = new NetSocketImpl(this.context, ctx, this.sslChannelProvider, this.metrics, false) {
               protected void handleClosed() {
                  if (Http1xServerConnection.this.metrics != null) {
                     Http1xServerRequest request = Http1xServerConnection.this.responseInProgress;
                     Http1xServerConnection.this.metrics.responseEnd(request.metric(), request.response(), request.response().bytesWritten());
                  }

                  super.handleClosed();
               }

               public synchronized void handleMessage(Object msg) {
                  if (msg instanceof HttpContent) {
                     ReferenceCountUtil.release(msg);
                  } else {
                     super.handleMessage(msg);
                  }
               }
            };
            socket.metric(this.metric());
            return socket;
         }));
         pipeline.remove("httpEncoder");
         VertxHandler<NetSocketImpl> handler = (VertxHandler)pipeline.get("handler");
         promise.complete(handler.getConnection());
      }));
   }

   public void handleInterestedOpsChanged() {
      this.writable = !this.isNotWritable();
      ContextInternal context;
      Handler<Boolean> handler;
      if (this.responseInProgress != null) {
         context = this.responseInProgress.context;
         Http1xServerResponse var10000 = this.responseInProgress.response();
         handler = var10000::handleWritabilityChanged;
      } else {
         if (this.webSocket == null) {
            return;
         }

         context = ((ServerWebSocketImpl)this.webSocket).context;
         ServerWebSocketImpl var3 = (ServerWebSocketImpl)this.webSocket;
         handler = var3::handleWritabilityChanged;
      }

      context.execute(this.writable, handler);
   }

   void write100Continue() {
      this.chctx.writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE));
   }

   void write103EarlyHints(io.netty.handler.codec.http.HttpHeaders headers, PromiseInternal promise) {
      this.chctx.writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.EARLY_HINTS, Unpooled.buffer(0), headers, EmptyHttpHeaders.INSTANCE)).addListener(promise);
   }

   protected void handleClosed() {
      Http1xServerRequest responseInProgress = this.responseInProgress;
      Http1xServerRequest requestInProgress = this.requestInProgress;
      ServerWebSocketImpl webSocket = (ServerWebSocketImpl)this.webSocket;
      if (requestInProgress != null) {
         requestInProgress.context.execute((Handler)((v) -> requestInProgress.handleException(HttpUtils.CONNECTION_CLOSED_EXCEPTION)));
      }

      if (responseInProgress != null && responseInProgress != requestInProgress) {
         responseInProgress.context.execute((Handler)((v) -> responseInProgress.handleException(HttpUtils.CONNECTION_CLOSED_EXCEPTION)));
      }

      if (webSocket != null) {
         webSocket.context.execute((Handler)((v) -> webSocket.handleConnectionClosed()));
      }

      super.handleClosed();
   }

   public void handleException(Throwable t) {
      super.handleException(t);
      if (Metrics.METRICS_ENABLED && this.metrics != null) {
         this.requestFailed = true;
      }

      if (this.requestInProgress != null) {
         this.requestInProgress.handleException(t);
      }

      if (this.responseInProgress != null && this.responseInProgress != this.requestInProgress) {
         this.responseInProgress.handleException(t);
      }

   }

   protected boolean supportsFileRegion() {
      return super.supportsFileRegion() && this.chctx.pipeline().get(HttpChunkContentCompressor.class) == null;
   }

   private void handleError(HttpObject obj) {
      DecoderResult result = obj.decoderResult();
      ReferenceCountUtil.release(obj);
      this.fail(result.cause());
   }
}
