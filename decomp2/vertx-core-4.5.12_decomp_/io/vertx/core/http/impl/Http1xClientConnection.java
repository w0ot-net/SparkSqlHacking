package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.compression.Brotli;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.DefaultLastHttpContent;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.websocketx.WebSocket07FrameDecoder;
import io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder;
import io.netty.handler.codec.http.websocketx.WebSocket13FrameDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker00;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker07;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker08;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker13;
import io.netty.handler.codec.http.websocketx.WebSocketDecoderConfig;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrameDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketHandshakeException;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandler;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandshaker;
import io.netty.handler.codec.http.websocketx.extensions.compression.DeflateFrameClientExtensionHandshaker;
import io.netty.handler.codec.http.websocketx.extensions.compression.PerMessageDeflateClientExtensionHandshaker;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.FutureListener;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpFrame;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebsocketVersion;
import io.vertx.core.http.impl.headers.HeadersAdaptor;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.NetSocketImpl;
import io.vertx.core.net.impl.NetSocketInternal;
import io.vertx.core.net.impl.SslChannelProvider;
import io.vertx.core.net.impl.VertxHandler;
import io.vertx.core.spi.metrics.ClientMetrics;
import io.vertx.core.spi.metrics.HttpClientMetrics;
import io.vertx.core.spi.tracing.SpanKind;
import io.vertx.core.spi.tracing.TagExtractor;
import io.vertx.core.spi.tracing.VertxTracer;
import io.vertx.core.streams.WriteStream;
import io.vertx.core.streams.impl.InboundBuffer;
import java.net.URI;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

public class Http1xClientConnection extends Http1xConnectionBase implements HttpClientConnection {
   private static final Logger log = LoggerFactory.getLogger(Http1xClientConnection.class);
   private static final Handler INVALID_MSG_HANDLER = (msg) -> {
      ReferenceCountUtil.release(msg);
      throw new IllegalStateException("Invalid object " + msg);
   };
   private final HttpClientBase client;
   private final HttpClientOptions options;
   private final boolean ssl;
   private final SocketAddress server;
   public final ClientMetrics metrics;
   private final HttpVersion version;
   private final long lowWaterMark;
   private final long highWaterMark;
   private Deque requests = new ArrayDeque();
   private Deque responses = new ArrayDeque();
   private boolean closed;
   private boolean evicted;
   private Handler evictionHandler;
   private Handler invalidMessageHandler;
   private boolean close;
   private boolean shutdown;
   private long shutdownTimerID;
   private boolean isConnect;
   private int keepAliveTimeout;
   private long expirationTimestamp;
   private int seq;
   private long readWindow;
   private long writeWindow;
   private boolean writeOverflow;
   private long lastResponseReceivedTimestamp;

   Http1xClientConnection(HttpVersion version, HttpClientBase client, ChannelHandlerContext channel, boolean ssl, SocketAddress server, ContextInternal context, ClientMetrics metrics) {
      super(context, channel);
      this.evictionHandler = DEFAULT_EVICTION_HANDLER;
      this.invalidMessageHandler = INVALID_MSG_HANDLER;
      this.shutdownTimerID = -1L;
      this.seq = 1;
      this.client = client;
      this.options = client.options();
      this.ssl = ssl;
      this.server = server;
      this.metrics = metrics;
      this.version = version;
      this.readWindow = 0L;
      this.writeWindow = 0L;
      this.highWaterMark = (long)channel.channel().config().getWriteBufferHighWaterMark();
      this.lowWaterMark = (long)channel.channel().config().getWriteBufferLowWaterMark();
      this.keepAliveTimeout = this.options.getKeepAliveTimeout();
      this.expirationTimestamp = expirationTimestampOf((long)this.keepAliveTimeout);
   }

   public HttpClientConnection evictionHandler(Handler handler) {
      this.evictionHandler = handler;
      return this;
   }

   public HttpClientConnection concurrencyChangeHandler(Handler handler) {
      return this;
   }

   public long concurrency() {
      return this.options.isPipelining() ? (long)this.options.getPipeliningLimit() : 1L;
   }

   public synchronized long activeStreams() {
      return this.requests.isEmpty() && this.responses.isEmpty() ? 0L : 1L;
   }

   public NetSocketInternal toNetSocket() {
      this.evictionHandler.handle((Object)null);
      this.chctx.pipeline().replace("handler", "handler", VertxHandler.create((ctx) -> {
         NetSocketImpl socket = new NetSocketImpl(this.context, ctx, (SslChannelProvider)null, this.metrics(), false);
         socket.metric(this.metric());
         return socket;
      }));
      VertxHandler<NetSocketImpl> handler = (VertxHandler)this.chctx.pipeline().get(VertxHandler.class);
      return (NetSocketInternal)handler.getConnection();
   }

   private HttpRequest createRequest(HttpMethod method, String uri, MultiMap headerMap, String authority, boolean chunked, ByteBuf buf, boolean end) {
      HttpRequest request = new DefaultHttpRequest(HttpUtils.toNettyHttpVersion(this.version), method.toNetty(), uri, false);
      HttpHeaders headers = request.headers();
      if (headerMap != null) {
         for(Map.Entry header : headerMap) {
            headers.add((String)header.getKey(), header.getValue());
         }
      }

      if (!headers.contains(io.vertx.core.http.HttpHeaders.HOST)) {
         request.headers().set(io.vertx.core.http.HttpHeaders.HOST, authority);
      } else {
         headers.remove(io.vertx.core.http.HttpHeaders.TRANSFER_ENCODING);
      }

      if (chunked) {
         HttpUtil.setTransferEncodingChunked(request, true);
      }

      if (this.options.isDecompressionSupported() && request.headers().get(io.vertx.core.http.HttpHeaders.ACCEPT_ENCODING) == null) {
         CharSequence acceptEncoding = determineCompressionAcceptEncoding();
         request.headers().set(io.vertx.core.http.HttpHeaders.ACCEPT_ENCODING, acceptEncoding);
      }

      if (!this.options.isKeepAlive() && this.options.getProtocolVersion() == HttpVersion.HTTP_1_1) {
         request.headers().set(io.vertx.core.http.HttpHeaders.CONNECTION, io.vertx.core.http.HttpHeaders.CLOSE);
      } else if (this.options.isKeepAlive() && this.options.getProtocolVersion() == HttpVersion.HTTP_1_0) {
         request.headers().set(io.vertx.core.http.HttpHeaders.CONNECTION, io.vertx.core.http.HttpHeaders.KEEP_ALIVE);
      }

      if (end) {
         if (buf != null) {
            request = new AssembledFullHttpRequest(request, buf);
         } else {
            request = new AssembledFullHttpRequest(request);
         }
      } else if (buf != null) {
         request = new AssembledHttpRequest(request, buf);
      }

      return request;
   }

   static CharSequence determineCompressionAcceptEncoding() {
      return isBrotliAvailable() ? io.vertx.core.http.HttpHeaders.DEFLATE_GZIP_BR : io.vertx.core.http.HttpHeaders.DEFLATE_GZIP;
   }

   private static boolean isBrotliAvailable() {
      return Brotli.isAvailable();
   }

   private void beginRequest(Stream stream, HttpRequestHead request, boolean chunked, ByteBuf buf, boolean end, boolean connect, Handler handler) {
      request.id = stream.id;
      request.remoteAddress = this.remoteAddress();
      stream.bytesWritten = stream.bytesWritten + (buf != null ? (long)buf.readableBytes() : 0L);
      HttpRequest nettyRequest = this.createRequest(request.method, request.uri, request.headers, request.authority, chunked, buf, end);
      synchronized(this) {
         this.responses.add(stream);
         this.isConnect = connect;
         if (this.metrics != null) {
            stream.metric = this.metrics.requestBegin(request.uri, request);
         }

         VertxTracer tracer = this.context.tracer();
         if (tracer != null) {
            BiConsumer<String, String> headers = (key, val) -> (new HeadersAdaptor(nettyRequest.headers())).add(key, val);
            String operation = request.traceOperation;
            if (operation == null) {
               operation = request.method.name();
            }

            stream.trace = tracer.sendRequest(stream.context, SpanKind.RPC, this.options.getTracingPolicy(), request, operation, headers, HttpUtils.CLIENT_HTTP_REQUEST_TAG_EXTRACTOR);
         }
      }

      this.writeToChannel(nettyRequest, handler == null ? null : this.context.promise(handler));
      if (end) {
         this.endRequest(stream);
      }

   }

   private void writeBuffer(Stream s, ByteBuf buff, boolean end, FutureListener listener) {
      s.bytesWritten = s.bytesWritten + (buff != null ? (long)buff.readableBytes() : 0L);
      if (this.isConnect) {
         Object msg = buff != null ? buff : Unpooled.EMPTY_BUFFER;
         if (end) {
            this.writeToChannel(msg, this.channelFuture().addListener(listener).addListener((v) -> this.close()));
         } else {
            this.writeToChannel(msg);
         }
      } else {
         Object msg;
         if (end) {
            if (buff != null && buff.isReadable()) {
               msg = new DefaultLastHttpContent(buff, false);
            } else {
               msg = LastHttpContent.EMPTY_LAST_CONTENT;
            }
         } else {
            msg = new DefaultHttpContent(buff);
         }

         this.writeToChannel(msg, listener);
         if (end) {
            this.endRequest(s);
         }
      }

   }

   private void endRequest(Stream s) {
      Stream next;
      boolean responseEnded;
      synchronized(this) {
         s.requestEnded = true;
         this.requests.pop();
         next = (Stream)this.requests.peek();
         responseEnded = s.responseEnded;
         if (this.metrics != null) {
            this.metrics.requestEnd(s.metric, s.bytesWritten);
         }
      }

      this.flushBytesWritten();
      if (next != null) {
         next.promise.complete((HttpClientStream)next);
      }

      if (responseEnded) {
         s.context.execute((Object)null, s::handleClosed);
         this.checkLifecycle();
      }

   }

   private boolean reset(Stream stream) {
      boolean inflight;
      synchronized(this) {
         inflight = this.responses.contains(stream) || stream.responseEnded;
         if (!inflight) {
            this.requests.remove(stream);
         }

         this.close = inflight;
      }

      this.checkLifecycle();
      return !inflight;
   }

   private void receiveBytes(int len) {
      boolean le = this.readWindow <= this.highWaterMark;
      this.readWindow += (long)len;
      boolean gt = this.readWindow > this.highWaterMark;
      if (le && gt) {
         this.doPause();
      }

   }

   private void ackBytes(int len) {
      EventLoop eventLoop = this.context.nettyEventLoop();
      if (eventLoop.inEventLoop()) {
         boolean gt = this.readWindow > this.lowWaterMark;
         this.readWindow -= (long)len;
         boolean le = this.readWindow <= this.lowWaterMark;
         if (gt && le) {
            this.doResume();
         }
      } else {
         eventLoop.execute(() -> this.ackBytes(len));
      }

   }

   private void checkLifecycle() {
      if (!this.close && (!this.shutdown || !this.requests.isEmpty() || !this.responses.isEmpty())) {
         if (!this.isConnect) {
            this.expirationTimestamp = expirationTimestampOf((long)this.keepAliveTimeout);
         }
      } else {
         this.close();
      }

   }

   public Future close() {
      if (!this.evicted) {
         this.evicted = true;
         if (this.evictionHandler != null) {
            this.evictionHandler.handle((Object)null);
         }
      }

      return super.close();
   }

   private Throwable validateMessage(Object msg) {
      if (msg instanceof HttpObject) {
         HttpObject obj = (HttpObject)msg;
         DecoderResult result = obj.decoderResult();
         if (result.isFailure()) {
            return result.cause();
         }

         if (obj instanceof HttpResponse) {
            io.netty.handler.codec.http.HttpVersion version = ((HttpResponse)obj).protocolVersion();
            if (version != io.netty.handler.codec.http.HttpVersion.HTTP_1_0 && version != io.netty.handler.codec.http.HttpVersion.HTTP_1_1) {
               return new IllegalStateException("Unsupported HTTP version: " + version);
            }
         }
      }

      return null;
   }

   public void handleMessage(Object msg) {
      Throwable error = this.validateMessage(msg);
      if (error != null) {
         ReferenceCountUtil.release(msg);
         this.fail(error);
      } else if (msg instanceof HttpObject) {
         this.handleHttpMessage((HttpObject)msg);
      } else if (msg instanceof ByteBuf && this.isConnect) {
         this.handleChunk((ByteBuf)msg);
      } else if (msg instanceof WebSocketFrame) {
         this.handleWsFrame((WebSocketFrame)msg);
      } else {
         this.invalidMessageHandler.handle(msg);
      }

   }

   private void handleHttpMessage(HttpObject obj) {
      Stream stream;
      synchronized(this) {
         stream = (Stream)this.responses.peekFirst();
      }

      if (stream == null) {
         this.fail(new VertxException("Received HTTP message with no request in progress"));
      } else if (obj instanceof HttpResponse) {
         HttpResponse response = (HttpResponse)obj;
         HttpVersion version;
         if (response.protocolVersion() == io.netty.handler.codec.http.HttpVersion.HTTP_1_0) {
            version = HttpVersion.HTTP_1_0;
         } else {
            version = HttpVersion.HTTP_1_1;
         }

         this.handleResponseBegin(stream, new HttpResponseHead(version, response.status().code(), response.status().reasonPhrase(), new HeadersAdaptor(response.headers())));
      } else if (obj instanceof HttpContent) {
         HttpContent chunk = (HttpContent)obj;
         if (chunk.content().isReadable()) {
            this.handleResponseChunk(stream, chunk.content());
         }

         if (!this.isConnect && chunk instanceof LastHttpContent) {
            this.handleResponseEnd(stream, (LastHttpContent)chunk);
         }
      }

   }

   private void handleChunk(ByteBuf chunk) {
      Stream stream;
      synchronized(this) {
         stream = (Stream)this.responses.peekFirst();
         if (stream == null) {
            return;
         }
      }

      if (chunk.isReadable()) {
         this.handleResponseChunk(stream, chunk);
      }

   }

   private void handleResponseBegin(Stream stream, HttpResponseHead response) {
      if (response.statusCode == HttpResponseStatus.CONTINUE.code()) {
         stream.context.execute((Object)null, (v) -> stream.handleContinue());
      } else if (response.statusCode == HttpResponseStatus.EARLY_HINTS.code()) {
         stream.context.execute((Object)null, (v) -> stream.handleEarlyHints(response.headers));
      } else {
         HttpRequestHead request;
         synchronized(this) {
            request = stream.request;
            stream.response = response;
            if (this.metrics != null) {
               this.metrics.responseBegin(stream.metric, response);
            }
         }

         stream.handleHead(response);
         if (this.isConnect) {
            if ((request.method != HttpMethod.CONNECT || response.statusCode != 200) && (request.method != HttpMethod.GET || request.headers == null || !request.headers.contains(io.vertx.core.http.HttpHeaders.CONNECTION, io.vertx.core.http.HttpHeaders.UPGRADE, true) || response.statusCode != 101)) {
               this.isConnect = false;
            } else {
               this.removeChannelHandlers();
            }
         }
      }

   }

   private void removeChannelHandlers() {
      ChannelPipeline pipeline = this.chctx.pipeline();
      ChannelHandler inflater = pipeline.get(HttpContentDecompressor.class);
      if (inflater != null) {
         pipeline.remove(inflater);
      }

      Handler<Object> prev = this.invalidMessageHandler;
      this.invalidMessageHandler = (msg) -> ReferenceCountUtil.release(msg);

      try {
         pipeline.remove("codec");
      } finally {
         this.invalidMessageHandler = prev;
      }

   }

   private void handleResponseChunk(Stream stream, ByteBuf chunk) {
      Buffer buff = Buffer.buffer(VertxHandler.safeBuffer(chunk));
      int len = buff.length();
      this.receiveBytes(len);
      stream.bytesRead = stream.bytesRead + (long)len;
      stream.context.execute(buff, stream::handleChunk);
   }

   private void handleResponseEnd(Stream stream, LastHttpContent trailer) {
      boolean check;
      HttpResponseHead response;
      synchronized(this) {
         response = stream.response;
         if (response == null) {
            return;
         }

         this.responses.pop();
         HttpRequestHead request = stream.request;
         if (request.method != HttpMethod.CONNECT && response.statusCode != 101) {
            String responseConnectionHeader = response.headers.get((CharSequence)HttpHeaderNames.CONNECTION);
            String requestConnectionHeader = request.headers != null ? request.headers.get((CharSequence)HttpHeaderNames.CONNECTION) : null;
            boolean close = !this.options.isKeepAlive();
            if (!HttpHeaderValues.CLOSE.contentEqualsIgnoreCase(responseConnectionHeader) && !HttpHeaderValues.CLOSE.contentEqualsIgnoreCase(requestConnectionHeader)) {
               if (response.version == HttpVersion.HTTP_1_0 && !HttpHeaderValues.KEEP_ALIVE.contentEqualsIgnoreCase(responseConnectionHeader)) {
                  close = true;
               }
            } else {
               close = true;
            }

            this.close = close;
            String keepAliveHeader = response.headers.get((CharSequence)HttpHeaderNames.KEEP_ALIVE);
            if (keepAliveHeader != null) {
               int timeout = HttpUtils.parseKeepAliveHeaderTimeout(keepAliveHeader);
               if (timeout != -1) {
                  this.keepAliveTimeout = timeout;
               }
            }
         }

         stream.responseEnded = true;
         check = this.requests.peek() != stream;
      }

      VertxTracer tracer = this.context.tracer();
      if (tracer != null) {
         tracer.receiveResponse(stream.context, response, stream.trace, (Throwable)null, HttpUtils.CLIENT_RESPONSE_TAG_EXTRACTOR);
      }

      if (this.metrics != null) {
         this.metrics.responseEnd(stream.metric, stream.bytesRead);
      }

      this.flushBytesRead();
      if (check) {
         this.checkLifecycle();
      }

      this.lastResponseReceivedTimestamp = System.currentTimeMillis();
      stream.context.execute(trailer, stream::handleEnd);
      if (stream.requestEnded) {
         stream.context.execute((Object)null, stream::handleClosed);
      }

   }

   public HttpClientMetrics metrics() {
      return this.client.metrics();
   }

   synchronized void toWebSocket(ContextInternal context, String requestURI, MultiMap headers, boolean allowOriginHeader, WebsocketVersion vers, List subProtocols, long handshakeTimeout, boolean registerWriteHandlers, int maxWebSocketFrameSize, Promise promise) {
      try {
         URI wsuri = new URI(requestURI);
         if (!wsuri.isAbsolute()) {
            wsuri = new URI((this.ssl ? "https:" : "http:") + "//" + this.server.host() + ":" + this.server.port() + requestURI);
         }

         WebSocketVersion version = WebSocketVersion.valueOf(((Enum)(vers == null ? WebSocketVersion.V13 : vers)).toString());
         HttpHeaders nettyHeaders;
         if (headers != null) {
            nettyHeaders = new DefaultHttpHeaders();

            for(Map.Entry entry : headers) {
               nettyHeaders.add((String)entry.getKey(), entry.getValue());
            }
         } else {
            nettyHeaders = null;
         }

         long timer;
         if (handshakeTimeout > 0L) {
            timer = this.vertx.setTimer(handshakeTimeout, (id) -> this.close());
         } else {
            timer = -1L;
         }

         ChannelPipeline p = this.chctx.channel().pipeline();
         ArrayList<WebSocketClientExtensionHandshaker> extensionHandshakers = this.initializeWebSocketExtensionHandshakers(this.client.options());
         if (!extensionHandshakers.isEmpty()) {
            p.addBefore("handler", "webSocketsExtensionsHandler", new WebSocketClientExtensionHandler((WebSocketClientExtensionHandshaker[])extensionHandshakers.toArray(new WebSocketClientExtensionHandshaker[0])));
         }

         String subp = null;
         if (subProtocols != null) {
            subp = String.join(",", subProtocols);
         }

         WebSocketClientHandshaker handshaker = newHandshaker(wsuri, version, subp, !extensionHandshakers.isEmpty(), allowOriginHeader, nettyHeaders, maxWebSocketFrameSize, !this.options.isSendUnmaskedFrames());
         Handler<AsyncResult<HeadersAdaptor>> webSocketHandshakeComplete = (ar) -> {
            if (timer > 0L) {
               this.vertx.cancelTimer(timer);
            }

            if (ar.failed()) {
               this.close();
               promise.fail(ar.cause());
            } else {
               WebSocketImpl ws = this.finish(context, version, registerWriteHandlers, handshaker, (MultiMap)ar.result());
               this.webSocket = ws;
               ws.pause();
               this.getContext().emit(ws, (w) -> {
                  promise.handle((AsyncResult)Future.succeededFuture(w));
                  ((WebSocketImpl)this.webSocket).headers((MultiMap)null);
               });
            }

         };
         WebSocketHandshakeInboundHandler handshakeInboundHandler = new WebSocketHandshakeInboundHandler(handshaker, webSocketHandshakeComplete);
         p.addBefore("handler", "handshakeCompleter", handshakeInboundHandler);
      } catch (Exception e) {
         this.handleException(e);
      }

   }

   private WebSocketImpl finish(ContextInternal context, WebSocketVersion version, boolean registerWriteHandlers, WebSocketClientHandshaker handshaker, MultiMap headers) {
      WebSocketImpl ws = new WebSocketImpl(context, this, version != WebSocketVersion.V00, (long)this.options.getWebSocketClosingTimeout(), this.options.getMaxWebSocketFrameSize(), this.options.getMaxWebSocketMessageSize(), registerWriteHandlers);
      ws.subProtocol(handshaker.actualSubprotocol());
      ws.registerHandler(this.vertx.eventBus());
      log.debug("WebSocket handshake complete");
      HttpClientMetrics metrics = this.client.metrics();
      if (metrics != null) {
         ws.setMetric(metrics.connected(ws));
      }

      ws.headers(headers);
      return ws;
   }

   static WebSocketClientHandshaker newHandshaker(URI webSocketURL, WebSocketVersion version, String subprotocol, boolean allowExtensions, final boolean allowOriginHeader, HttpHeaders customHeaders, int maxFramePayloadLength, boolean performMasking) {
      final WebSocketDecoderConfig config = WebSocketDecoderConfig.newBuilder().expectMaskedFrames(false).allowExtensions(allowExtensions).maxFramePayloadLength(maxFramePayloadLength).allowMaskMismatch(false).closeOnProtocolViolation(false).build();
      if (version == WebSocketVersion.V13) {
         return new WebSocketClientHandshaker13(webSocketURL, WebSocketVersion.V13, subprotocol, allowExtensions, customHeaders, maxFramePayloadLength, performMasking, false, -1L) {
            protected WebSocketFrameDecoder newWebsocketDecoder() {
               return new WebSocket13FrameDecoder(config);
            }

            protected FullHttpRequest newHandshakeRequest() {
               FullHttpRequest request = super.newHandshakeRequest();
               if (!allowOriginHeader) {
                  request.headers().remove(io.vertx.core.http.HttpHeaders.ORIGIN);
               }

               return request;
            }
         };
      } else if (version == WebSocketVersion.V08) {
         return new WebSocketClientHandshaker08(webSocketURL, WebSocketVersion.V08, subprotocol, allowExtensions, customHeaders, maxFramePayloadLength, performMasking, false, -1L) {
            protected WebSocketFrameDecoder newWebsocketDecoder() {
               return new WebSocket08FrameDecoder(config);
            }

            protected FullHttpRequest newHandshakeRequest() {
               FullHttpRequest request = super.newHandshakeRequest();
               if (!allowOriginHeader) {
                  request.headers().remove(HttpHeaderNames.SEC_WEBSOCKET_ORIGIN);
               }

               return request;
            }
         };
      } else if (version == WebSocketVersion.V07) {
         return new WebSocketClientHandshaker07(webSocketURL, WebSocketVersion.V07, subprotocol, allowExtensions, customHeaders, maxFramePayloadLength, performMasking, false, -1L) {
            protected WebSocketFrameDecoder newWebsocketDecoder() {
               return new WebSocket07FrameDecoder(config);
            }

            protected FullHttpRequest newHandshakeRequest() {
               FullHttpRequest request = super.newHandshakeRequest();
               if (!allowOriginHeader) {
                  request.headers().remove(HttpHeaderNames.SEC_WEBSOCKET_ORIGIN);
               }

               return request;
            }
         };
      } else if (version == WebSocketVersion.V00) {
         return new WebSocketClientHandshaker00(webSocketURL, WebSocketVersion.V00, subprotocol, customHeaders, maxFramePayloadLength, -1L) {
            protected FullHttpRequest newHandshakeRequest() {
               FullHttpRequest request = super.newHandshakeRequest();
               if (!allowOriginHeader) {
                  request.headers().remove(io.vertx.core.http.HttpHeaders.ORIGIN);
               }

               return request;
            }
         };
      } else {
         throw new WebSocketHandshakeException("Protocol version " + version + " not supported.");
      }
   }

   ArrayList initializeWebSocketExtensionHandshakers(HttpClientOptions options) {
      ArrayList<WebSocketClientExtensionHandshaker> extensionHandshakers = new ArrayList();
      if (options.getTryUsePerFrameWebSocketCompression()) {
         extensionHandshakers.add(new DeflateFrameClientExtensionHandshaker(options.getWebSocketCompressionLevel(), false));
      }

      if (options.getTryUsePerMessageWebSocketCompression()) {
         extensionHandshakers.add(new PerMessageDeflateClientExtensionHandshaker(options.getWebSocketCompressionLevel(), ZlibCodecFactory.isSupportingWindowSizeAndMemLevel(), 15, options.getWebSocketCompressionAllowClientNoContext(), options.getWebSocketCompressionRequestServerNoContext()));
      }

      return extensionHandshakers;
   }

   public void handleInterestedOpsChanged() {
      boolean writable = !this.isNotWritable();
      ContextInternal context;
      Handler<Boolean> handler;
      synchronized(this) {
         Stream current = (Stream)this.requests.peek();
         if (current != null) {
            context = current.context;
            handler = current::handleWritabilityChanged;
         } else {
            if (this.webSocket == null) {
               return;
            }

            context = ((WebSocketImpl)this.webSocket).context;
            WebSocketImpl var10000 = (WebSocketImpl)this.webSocket;
            handler = var10000::handleWritabilityChanged;
         }
      }

      context.execute(writable, handler);
   }

   protected void handleClosed() {
      super.handleClosed();
      long timerID = this.shutdownTimerID;
      if (timerID != -1L) {
         this.shutdownTimerID = -1L;
         this.vertx.cancelTimer(timerID);
      }

      this.closed = true;
      if (this.metrics != null) {
         HttpClientMetrics met = this.client.metrics();
         met.endpointDisconnected(this.metrics);
      }

      if (!this.evicted) {
         this.evicted = true;
         if (this.evictionHandler != null) {
            this.evictionHandler.handle((Object)null);
         }
      }

      VertxTracer tracer = this.context.tracer();
      List<Stream> allocatedStreams;
      List<Stream> sentStreams;
      WebSocketImpl ws;
      synchronized(this) {
         ws = (WebSocketImpl)this.webSocket;
         sentStreams = new ArrayList(this.responses);
         allocatedStreams = new ArrayList(this.requests);
         allocatedStreams.removeAll(this.responses);
      }

      if (ws != null) {
         ws.handleConnectionClosed();
      }

      for(Stream stream : allocatedStreams) {
         stream.context.execute(HttpUtils.CONNECTION_CLOSED_EXCEPTION, stream::handleClosed);
      }

      for(Stream stream : sentStreams) {
         if (this.metrics != null) {
            this.metrics.requestReset(stream.metric);
         }

         Object trace = stream.trace;
         if (tracer != null && trace != null) {
            tracer.receiveResponse(stream.context, (Object)null, trace, HttpUtils.CONNECTION_CLOSED_EXCEPTION, TagExtractor.empty());
         }

         stream.context.execute(HttpUtils.CONNECTION_CLOSED_EXCEPTION, stream::handleClosed);
      }

   }

   protected void handleIdle(IdleStateEvent event) {
      synchronized(this) {
         if (this.webSocket == null && this.responses.isEmpty() && this.requests.isEmpty()) {
            return;
         }
      }

      super.handleIdle(event);
   }

   public void handleException(Throwable e) {
      super.handleException(e);
      LinkedHashSet<Stream> allStreams = new LinkedHashSet();
      synchronized(this) {
         allStreams.addAll(this.requests);
         allStreams.addAll(this.responses);
      }

      for(Stream stream : allStreams) {
         stream.handleException(e);
      }

   }

   public Future createRequest(ContextInternal context) {
      return ((HttpClientImpl)this.client).createRequest(this, context);
   }

   public void createStream(ContextInternal context, Handler handler) {
      EventLoop eventLoop = context.nettyEventLoop();
      if (eventLoop.inEventLoop()) {
         StreamImpl stream;
         synchronized(this) {
            if (this.closed) {
               stream = null;
            } else {
               stream = new StreamImpl(context, this, this.seq++);
               this.requests.add(stream);
               if (this.requests.size() == 1) {
                  stream.promise.complete(stream);
               }
            }
         }

         if (stream != null) {
            stream.promise.future().onComplete(handler);
         } else {
            handler.handle(Future.failedFuture((Throwable)HttpUtils.CONNECTION_CLOSED_EXCEPTION));
         }
      } else {
         eventLoop.execute(() -> this.createStream(context, handler));
      }

   }

   public long lastResponseReceivedTimestamp() {
      return this.lastResponseReceivedTimestamp;
   }

   public boolean isValid() {
      return this.expirationTimestamp == 0L || System.currentTimeMillis() <= this.expirationTimestamp;
   }

   private synchronized void shutdownNow() {
      this.shutdownTimerID = -1L;
      this.close();
   }

   public Future shutdown(long timeout, TimeUnit unit) {
      PromiseInternal<Void> promise = this.vertx.promise();
      this.shutdown(unit.toMillis(timeout), promise);
      return promise.future();
   }

   private void shutdown(long timeoutMs, PromiseInternal promise) {
      synchronized(this) {
         if (this.shutdown) {
            promise.fail("Already shutdown");
            return;
         }

         this.shutdown = true;
         this.closeFuture().onComplete(promise);
      }

      synchronized(this) {
         if (!this.closed) {
            if (timeoutMs > 0L) {
               this.shutdownTimerID = this.context.setTimer(timeoutMs, (id) -> this.shutdownNow());
            } else {
               this.close = true;
            }
         }
      }

      this.checkLifecycle();
   }

   private static long expirationTimestampOf(long timeout) {
      return timeout == 0L ? 0L : System.currentTimeMillis() + timeout * 1000L;
   }

   private abstract static class Stream {
      protected final Promise promise;
      protected final ContextInternal context;
      protected final int id;
      private Object trace;
      private Object metric;
      private HttpRequestHead request;
      private HttpResponseHead response;
      private boolean requestEnded;
      private boolean responseEnded;
      private long bytesRead;
      private long bytesWritten;

      Stream(ContextInternal context, int id) {
         this.context = context;
         this.id = id;
         this.promise = context.promise();
      }

      Object metric() {
         return this.metric;
      }

      Object trace() {
         return this.trace;
      }

      abstract void handleContinue();

      abstract void handleEarlyHints(MultiMap var1);

      abstract void handleHead(HttpResponseHead var1);

      abstract void handleChunk(Buffer var1);

      abstract void handleEnd(LastHttpContent var1);

      abstract void handleWritabilityChanged(boolean var1);

      abstract void handleException(Throwable var1);

      abstract void handleClosed(Throwable var1);
   }

   private static class StreamImpl extends Stream implements HttpClientStream {
      private final Http1xClientConnection conn;
      private final InboundBuffer queue;
      private Throwable reset;
      private boolean closed;
      private Handler headHandler;
      private Handler chunkHandler;
      private Handler endHandler;
      private Handler drainHandler;
      private Handler continueHandler;
      private int writeInProgress = 0;
      private Handler earlyHintsHandler;
      private Handler exceptionHandler;
      private Handler closeHandler;

      StreamImpl(ContextInternal context, Http1xClientConnection conn, int id) {
         super(context, id);
         this.conn = conn;
         InboundBuffer var10001 = (new InboundBuffer(context, 5L)).handler((item) -> {
            if (this.reset == null) {
               if (item instanceof MultiMap) {
                  Handler<MultiMap> handler = this.endHandler;
                  if (handler != null) {
                     handler.handle((MultiMap)item);
                  }
               } else {
                  Buffer buffer = (Buffer)item;
                  int len = buffer.length();
                  conn.ackBytes(len);
                  Handler<Buffer> handler = this.chunkHandler;
                  if (handler != null) {
                     handler.handle(buffer);
                  }
               }
            }

         });
         context.getClass();
         this.queue = var10001.exceptionHandler(context::reportException);
      }

      public void continueHandler(Handler handler) {
         this.continueHandler = handler;
      }

      public void earlyHintsHandler(Handler handler) {
         this.earlyHintsHandler = handler;
      }

      public StreamImpl drainHandler(Handler handler) {
         this.drainHandler = handler;
         return this;
      }

      public StreamImpl exceptionHandler(Handler handler) {
         this.exceptionHandler = handler;
         return this;
      }

      public WriteStream setWriteQueueMaxSize(int maxSize) {
         return null;
      }

      public boolean writeQueueFull() {
         return false;
      }

      public void headHandler(Handler handler) {
         this.headHandler = handler;
      }

      public void closeHandler(Handler handler) {
         this.closeHandler = handler;
      }

      public void priorityHandler(Handler handler) {
      }

      public void pushHandler(Handler handler) {
      }

      public void unknownFrameHandler(Handler handler) {
      }

      public int id() {
         return this.id;
      }

      public Object metric() {
         return super.metric();
      }

      public Object trace() {
         return super.trace();
      }

      public HttpVersion version() {
         return this.conn.version;
      }

      public HttpClientConnection connection() {
         return this.conn;
      }

      public ContextInternal getContext() {
         return this.context;
      }

      public void writeHead(HttpRequestHead request, boolean chunked, ByteBuf buf, boolean end, StreamPriority priority, boolean connect, Handler handler) {
         this.writeHead(request, chunked, buf, end, connect, handler == null ? null : this.context.promise(handler));
      }

      private boolean checkReset(Handler handler) {
         Throwable reset;
         synchronized(this) {
            reset = this.reset;
            if (reset == null) {
               return false;
            }
         }

         handler.handle(this.context.failedFuture(reset));
         return true;
      }

      private void writeHead(HttpRequestHead request, boolean chunked, ByteBuf buf, boolean end, boolean connect, Handler handler) {
         if (!this.checkReset(handler)) {
            EventLoop eventLoop = this.conn.context.nettyEventLoop();
            synchronized(this) {
               if (this.shouldQueue(eventLoop)) {
                  this.queueForWrite(eventLoop, () -> {
                     super.request = request;
                     this.conn.beginRequest(this, request, chunked, buf, end, connect, handler);
                  });
                  return;
               }
            }

            if (this.reset != null) {
               handler.handle(this.context.failedFuture(this.reset));
            } else {
               super.request = request;
               this.conn.beginRequest(this, request, chunked, buf, end, connect, handler);
            }
         }
      }

      public void writeBuffer(ByteBuf buff, boolean end, Handler handler) {
         if (!this.checkReset(handler)) {
            if (buff != null || end) {
               FutureListener<Void> listener = handler == null ? null : this.context.promise(handler);
               this.writeBuffer(buff, end, listener);
            }

         }
      }

      private void writeBuffer(ByteBuf buff, boolean end, FutureListener listener) {
         FutureListener<Void> l;
         if (buff != null) {
            int size = buff.readableBytes();
            l = (future) -> {
               Handler<Void> drain;
               synchronized(this.conn) {
                  Http1xClientConnection var6 = this.conn;
                  var6.writeWindow = var6.writeWindow - (long)size;
                  if (this.conn.writeOverflow && this.conn.writeWindow < this.conn.lowWaterMark) {
                     drain = this.drainHandler;
                     this.conn.writeOverflow = false;
                  } else {
                     drain = null;
                  }
               }

               if (drain != null) {
                  this.context.emit(drain);
               }

               if (listener != null) {
                  listener.operationComplete(future);
               }

            };
            synchronized(this.conn) {
               Http1xClientConnection var7 = this.conn;
               var7.writeWindow = var7.writeWindow + (long)size;
               if (this.conn.writeWindow > this.conn.highWaterMark) {
                  this.conn.writeOverflow = true;
               }
            }
         } else {
            l = listener;
         }

         EventLoop eventLoop = this.conn.context.nettyEventLoop();
         synchronized(this) {
            if (this.shouldQueue(eventLoop)) {
               this.queueForWrite(eventLoop, () -> this.conn.writeBuffer(this, buff, end, l));
               return;
            }
         }

         this.conn.writeBuffer(this, buff, end, l);
      }

      private boolean shouldQueue(EventLoop eventLoop) {
         return !eventLoop.inEventLoop() || this.writeInProgress > 0;
      }

      private void queueForWrite(EventLoop eventLoop, Runnable action) {
         ++this.writeInProgress;
         eventLoop.execute(() -> {
            synchronized(this) {
               --this.writeInProgress;
            }

            action.run();
         });
      }

      public void writeFrame(int type, int flags, ByteBuf payload) {
         throw new IllegalStateException("Cannot write an HTTP/2 frame over an HTTP/1.x connection");
      }

      public void doSetWriteQueueMaxSize(int size) {
         this.conn.doSetWriteQueueMaxSize(size);
      }

      public boolean isNotWritable() {
         synchronized(this.conn) {
            return this.conn.writeWindow > this.conn.highWaterMark;
         }
      }

      public void doPause() {
         this.queue.pause();
      }

      public void doFetch(long amount) {
         this.queue.fetch(amount);
      }

      public void reset(Throwable cause) {
         synchronized(this.conn) {
            if (this.reset != null) {
               return;
            }

            this.reset = (Throwable)Objects.requireNonNull(cause);
         }

         EventLoop eventLoop = this.conn.context.nettyEventLoop();
         if (eventLoop.inEventLoop()) {
            this._reset(cause);
         } else {
            eventLoop.execute(() -> this._reset(cause));
         }

      }

      private void _reset(Throwable cause) {
         boolean removed = this.conn.reset(this);
         if (removed) {
            this.context.execute(cause, this::handleClosed);
         } else {
            this.context.execute(cause, this::handleException);
         }

      }

      public StreamPriority priority() {
         return null;
      }

      public void updatePriority(StreamPriority streamPriority) {
      }

      void handleWritabilityChanged(boolean writable) {
      }

      void handleContinue() {
         if (this.continueHandler != null) {
            this.continueHandler.handle((Object)null);
         }

      }

      void handleEarlyHints(MultiMap headers) {
         if (this.earlyHintsHandler != null) {
            this.earlyHintsHandler.handle(headers);
         }

      }

      void handleHead(HttpResponseHead response) {
         Handler<HttpResponseHead> handler = this.headHandler;
         if (handler != null) {
            this.context.emit(response, handler);
         }

      }

      public void chunkHandler(Handler handler) {
         this.chunkHandler = handler;
      }

      public void endHandler(Handler handler) {
         this.endHandler = handler;
      }

      void handleChunk(Buffer buff) {
         this.queue.write((Object)buff);
      }

      void handleEnd(LastHttpContent trailer) {
         this.queue.write((Object)(new HeadersAdaptor(trailer.trailingHeaders())));
      }

      void handleException(Throwable cause) {
         if (this.exceptionHandler != null) {
            this.exceptionHandler.handle(cause);
         }

      }

      void handleClosed(Throwable err) {
         if (err != null) {
            this.handleException(err);
            this.promise.tryFail(err);
         }

         if (!this.closed) {
            this.closed = true;
            if (this.closeHandler != null) {
               this.closeHandler.handle((Object)null);
            }
         }

      }
   }
}
