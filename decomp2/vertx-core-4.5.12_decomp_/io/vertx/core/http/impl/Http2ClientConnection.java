package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.Http2Error;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.handler.timeout.IdleStateEvent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.GoAway;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpFrame;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.StreamResetException;
import io.vertx.core.http.impl.headers.HeadersMultiMap;
import io.vertx.core.http.impl.headers.Http2HeadersAdaptor;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.spi.metrics.ClientMetrics;
import io.vertx.core.spi.metrics.HttpClientMetrics;
import io.vertx.core.spi.tracing.SpanKind;
import io.vertx.core.spi.tracing.VertxTracer;
import io.vertx.core.streams.WriteStream;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;

class Http2ClientConnection extends Http2ConnectionBase implements HttpClientConnection {
   private final HttpClientBase client;
   private final ClientMetrics metrics;
   private Handler evictionHandler;
   private Handler concurrencyChangeHandler;
   private long expirationTimestamp;
   private boolean evicted;

   Http2ClientConnection(HttpClientBase client, ContextInternal context, VertxHttp2ConnectionHandler connHandler, ClientMetrics metrics) {
      super(context, connHandler);
      this.evictionHandler = DEFAULT_EVICTION_HANDLER;
      this.concurrencyChangeHandler = DEFAULT_CONCURRENCY_CHANGE_HANDLER;
      this.metrics = metrics;
      this.client = client;
   }

   public Http2ClientConnection evictionHandler(Handler handler) {
      this.evictionHandler = handler;
      return this;
   }

   public Http2ClientConnection concurrencyChangeHandler(Handler handler) {
      this.concurrencyChangeHandler = handler;
      return this;
   }

   public long concurrency() {
      long concurrency = this.remoteSettings().getMaxConcurrentStreams();
      long http2MaxConcurrency = this.client.options().getHttp2MultiplexingLimit() <= 0 ? Long.MAX_VALUE : (long)this.client.options().getHttp2MultiplexingLimit();
      if (http2MaxConcurrency > 0L) {
         concurrency = Math.min(concurrency, http2MaxConcurrency);
      }

      return concurrency;
   }

   public long activeStreams() {
      return (long)this.handler.connection().numActiveStreams();
   }

   boolean onGoAwaySent(GoAway goAway) {
      boolean goneAway = super.onGoAwaySent(goAway);
      if (goneAway) {
         this.tryEvict();
      }

      return goneAway;
   }

   boolean onGoAwayReceived(GoAway goAway) {
      boolean goneAway = super.onGoAwayReceived(goAway);
      if (goneAway) {
         this.tryEvict();
      }

      return goneAway;
   }

   private void tryEvict() {
      if (!this.evicted) {
         this.evicted = true;
         this.evictionHandler.handle((Object)null);
      }

   }

   protected void concurrencyChanged(long concurrency) {
      int limit = this.client.options().getHttp2MultiplexingLimit();
      if (limit > 0) {
         concurrency = Math.min(concurrency, (long)limit);
      }

      this.concurrencyChangeHandler.handle(concurrency);
   }

   public HttpClientMetrics metrics() {
      return this.client.metrics();
   }

   void upgradeStream(Object metric, Object trace, ContextInternal context, Handler completionHandler) {
      Future<HttpClientStream> fut;
      synchronized(this) {
         try {
            Stream stream = this.createStream(context);
            stream.init(this.handler.connection().stream(1));
            stream.metric = metric;
            stream.trace = trace;
            stream.requestEnded = true;
            fut = Future.succeededFuture((HttpClientStream)stream);
         } catch (Exception e) {
            fut = Future.failedFuture((Throwable)e);
         }
      }

      completionHandler.handle(fut);
   }

   public void createStream(ContextInternal context, Handler handler) {
      Future<HttpClientStream> fut;
      synchronized(this) {
         try {
            StreamImpl stream = this.createStream(context);
            fut = Future.succeededFuture(stream);
         } catch (Exception e) {
            fut = Future.failedFuture((Throwable)e);
         }
      }

      context.emit(fut, handler);
   }

   public Future createRequest(ContextInternal context) {
      return ((HttpClientImpl)this.client).createRequest(this, context);
   }

   private StreamImpl createStream(ContextInternal context) {
      return new StreamImpl(this, context, false);
   }

   private void recycle() {
      int timeout = this.client.options().getHttp2KeepAliveTimeout();
      this.expirationTimestamp = timeout > 0 ? System.currentTimeMillis() + (long)timeout * 1000L : 0L;
   }

   public boolean isValid() {
      return this.expirationTimestamp == 0L || System.currentTimeMillis() <= this.expirationTimestamp;
   }

   public long lastResponseReceivedTimestamp() {
      return 0L;
   }

   protected synchronized void onHeadersRead(int streamId, Http2Headers headers, StreamPriority streamPriority, boolean endOfStream) {
      Stream stream = (Stream)this.stream(streamId);
      if (!stream.stream.isTrailersReceived()) {
         stream.onHeaders(headers, streamPriority);
         if (endOfStream) {
            stream.onEnd();
         }
      } else {
         stream.onEnd(new Http2HeadersAdaptor(headers));
      }

   }

   private void metricsEnd(Stream stream) {
      if (this.metrics != null) {
         this.metrics.responseEnd(stream.metric, stream.bytesRead());
      }

   }

   public synchronized void onPushPromiseRead(ChannelHandlerContext ctx, int streamId, int promisedStreamId, Http2Headers headers, int padding) throws Http2Exception {
      StreamImpl stream = (StreamImpl)this.stream(streamId);
      if (stream != null) {
         Handler<HttpClientPush> pushHandler = stream.pushHandler;
         if (pushHandler != null) {
            Http2Stream promisedStream = this.handler.connection().stream(promisedStreamId);
            StreamImpl pushStream = new StreamImpl(this, this.context, true);
            pushStream.init(promisedStream);
            HttpClientPush push = new HttpClientPush(headers, pushStream);
            if (this.metrics != null) {
               Object metric = this.metrics.requestBegin(headers.path().toString(), push);
               pushStream.metric = metric;
               this.metrics.requestEnd(metric, 0L);
            }

            stream.context.dispatch(push, pushHandler);
            return;
         }
      }

      this.handler.writeReset(promisedStreamId, Http2Error.CANCEL.code());
   }

   protected void handleIdle(IdleStateEvent event) {
      if (this.handler.connection().local().numActiveStreams() > 0) {
         super.handleIdle(event);
      }

   }

   public static VertxHttp2ConnectionHandler createHttp2ConnectionHandler(HttpClientBase client, ClientMetrics metrics, ContextInternal context, boolean upgrade, Object socketMetric) {
      HttpClientOptions options = client.options();
      HttpClientMetrics met = client.metrics();
      VertxHttp2ConnectionHandler<Http2ClientConnection> handler = (new VertxHttp2ConnectionHandlerBuilder()).server(false).useDecompression(client.options().isDecompressionSupported()).useUniformStreamByteDistributor(client.useH2UniformStreamByteDistributor).gracefulShutdownTimeoutMillis(0L).initialSettings(client.options().getInitialSettings()).connectionFactory((connHandler) -> {
         Http2ClientConnection conn = new Http2ClientConnection(client, context, connHandler, metrics);
         if (metrics != null) {
            conn.metric(socketMetric);
         }

         return conn;
      }).logEnabled(options.getLogActivity()).build();
      handler.addHandler((conn) -> {
         if (options.getHttp2ConnectionWindowSize() > 0) {
            conn.setWindowSize(options.getHttp2ConnectionWindowSize());
         }

         if (metrics != null && !upgrade) {
            met.endpointConnected(metrics);
         }

      });
      handler.removeHandler((conn) -> {
         if (metrics != null) {
            met.endpointDisconnected(metrics);
         }

         conn.tryEvict();
      });
      return handler;
   }

   abstract static class Stream extends VertxHttp2Stream {
      private final boolean push;
      private HttpResponseHead response;
      protected Object metric;
      protected Object trace;
      private boolean requestEnded;
      private boolean responseEnded;
      protected Handler headHandler;
      protected Handler chunkHandler;
      protected Handler endHandler;
      protected Handler priorityHandler;
      protected Handler drainHandler;
      protected Handler continueHandler;
      protected Handler earlyHintsHandler;
      protected Handler unknownFrameHandler;
      protected Handler exceptionHandler;
      protected Handler pushHandler;
      protected Handler closeHandler;
      protected long writeWindow;
      protected final long windowSize;

      Stream(Http2ClientConnection conn, ContextInternal context, boolean push) {
         super(conn, context);
         this.push = push;
         this.windowSize = (long)conn.getWindowSize();
      }

      void onContinue() {
         this.context.emit((Object)null, (v) -> this.handleContinue());
      }

      void onEarlyHints(MultiMap headers) {
         this.context.emit((Object)null, (v) -> this.handleEarlyHints(headers));
      }

      abstract void handleContinue();

      abstract void handleEarlyHints(MultiMap var1);

      public Object metric() {
         return this.metric;
      }

      public Object trace() {
         return this.trace;
      }

      void doWriteData(ByteBuf chunk, boolean end, Handler handler) {
         super.doWriteData(chunk, end, handler);
      }

      void doWriteHeaders(Http2Headers headers, boolean end, boolean checkFlush, Handler handler) {
         this.isConnect = "CONNECT".contentEquals(headers.method());
         super.doWriteHeaders(headers, end, checkFlush, handler);
      }

      protected void doWriteReset(long code) {
         if (!this.requestEnded || !this.responseEnded) {
            super.doWriteReset(code);
         }

      }

      protected void endWritten() {
         this.requestEnded = true;
         if (((Http2ClientConnection)this.conn).metrics != null) {
            ((Http2ClientConnection)this.conn).metrics.requestEnd(this.metric, this.bytesWritten());
         }

      }

      void onEnd(MultiMap trailers) {
         ((Http2ClientConnection)this.conn).metricsEnd(this);
         this.responseEnded = true;
         super.onEnd(trailers);
      }

      void onReset(long code) {
         if (((Http2ClientConnection)this.conn).metrics != null) {
            ((Http2ClientConnection)this.conn).metrics.requestReset(this.metric);
         }

         super.onReset(code);
      }

      void onHeaders(Http2Headers headers, StreamPriority streamPriority) {
         if (streamPriority != null) {
            this.priority(streamPriority);
         }

         if (this.response == null) {
            int status;
            String statusMessage;
            try {
               status = Integer.parseInt(headers.status().toString());
               statusMessage = HttpResponseStatus.valueOf(status).reasonPhrase();
            } catch (Exception e) {
               this.handleException(e);
               this.writeReset(1L);
               return;
            }

            if (status == 100) {
               this.onContinue();
               return;
            }

            if (status == 103) {
               MultiMap headersMultiMap = HeadersMultiMap.httpHeaders();
               this.removeStatusHeaders(headers);

               for(Map.Entry header : headers) {
                  headersMultiMap.add((CharSequence)header.getKey(), (CharSequence)header.getValue());
               }

               this.onEarlyHints(headersMultiMap);
               return;
            }

            this.response = new HttpResponseHead(HttpVersion.HTTP_2, status, statusMessage, new Http2HeadersAdaptor(headers));
            this.removeStatusHeaders(headers);
            if (((Http2ClientConnection)this.conn).metrics != null) {
               ((Http2ClientConnection)this.conn).metrics.responseBegin(this.metric, this.response);
            }

            if (this.headHandler != null) {
               this.context.emit(this.response, this.headHandler);
            }
         }

      }

      private void removeStatusHeaders(Http2Headers headers) {
         headers.remove(HttpHeaders.PSEUDO_STATUS);
      }

      void onClose() {
         if (((Http2ClientConnection)this.conn).metrics != null && (!this.requestEnded || !this.responseEnded)) {
            ((Http2ClientConnection)this.conn).metrics.requestReset(this.metric);
         }

         VertxTracer tracer = this.context.tracer();
         if (tracer != null && this.trace != null) {
            VertxException err;
            if (this.responseEnded && this.requestEnded) {
               err = null;
            } else {
               err = HttpUtils.STREAM_CLOSED_EXCEPTION;
            }

            tracer.receiveResponse(this.context, this.response, this.trace, err, HttpUtils.CLIENT_RESPONSE_TAG_EXTRACTOR);
         }

         if (!this.responseEnded) {
            this.onException(HttpUtils.STREAM_CLOSED_EXCEPTION);
         }

         super.onClose();
         if (!this.push) {
            ((Http2ClientConnection)this.conn).recycle();
         }

         if (this.closeHandler != null) {
            this.closeHandler.handle((Object)null);
         }

      }
   }

   static class StreamImpl extends Stream implements HttpClientStream {
      StreamImpl(Http2ClientConnection conn, ContextInternal context, boolean push) {
         super(conn, context, push);
      }

      public void closeHandler(Handler handler) {
         this.closeHandler = handler;
      }

      public void continueHandler(Handler handler) {
         this.continueHandler = handler;
      }

      public void earlyHintsHandler(Handler handler) {
         this.earlyHintsHandler = handler;
      }

      public void unknownFrameHandler(Handler handler) {
         this.unknownFrameHandler = handler;
      }

      public void pushHandler(Handler handler) {
         this.pushHandler = handler;
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
         return this;
      }

      public boolean writeQueueFull() {
         return !this.isNotWritable();
      }

      public synchronized boolean isNotWritable() {
         return this.writeWindow > this.windowSize;
      }

      public void headHandler(Handler handler) {
         this.headHandler = handler;
      }

      public void chunkHandler(Handler handler) {
         this.chunkHandler = handler;
      }

      public void priorityHandler(Handler handler) {
         this.priorityHandler = handler;
      }

      public void endHandler(Handler handler) {
         this.endHandler = handler;
      }

      public StreamPriority priority() {
         return super.priority();
      }

      public void updatePriority(StreamPriority streamPriority) {
         super.updatePriority(streamPriority);
      }

      public HttpVersion version() {
         return HttpVersion.HTTP_2;
      }

      void handleEnd(MultiMap trailers) {
         if (this.endHandler != null) {
            this.endHandler.handle(trailers);
         }

      }

      void handleData(Buffer buf) {
         if (this.chunkHandler != null) {
            this.chunkHandler.handle(buf);
         }

      }

      void handleReset(long errorCode) {
         this.handleException(new StreamResetException(errorCode));
      }

      void handleWritabilityChanged(boolean writable) {
      }

      void handleCustomFrame(HttpFrame frame) {
         if (this.unknownFrameHandler != null) {
            this.unknownFrameHandler.handle(frame);
         }

      }

      void handlePriorityChange(StreamPriority streamPriority) {
         if (this.priorityHandler != null) {
            this.priorityHandler.handle(streamPriority);
         }

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

      void handleException(Throwable exception) {
         if (this.exceptionHandler != null) {
            this.exceptionHandler.handle(exception);
         }

      }

      public void writeHead(HttpRequestHead request, boolean chunked, ByteBuf buf, boolean end, StreamPriority priority, boolean connect, Handler handler) {
         if (!this.checkReset(handler)) {
            this.priority(priority);
            ContextInternal ctx = ((Http2ClientConnection)this.conn).getContext();
            EventLoop eventLoop = ctx.nettyEventLoop();
            synchronized(this) {
               if (this.shouldQueue(eventLoop)) {
                  this.queueForWrite(eventLoop, () -> this.writeHeaders(request, buf, end, priority, connect, handler));
                  return;
               }
            }

            this.writeHeaders(request, buf, end, priority, connect, handler);
         }
      }

      private void writeHeaders(HttpRequestHead request, ByteBuf buf, boolean end, StreamPriority priority, boolean connect, Handler handler) {
         Http2Headers headers = new DefaultHttp2Headers();
         headers.method(request.method.name());
         boolean e;
         if (request.method == HttpMethod.CONNECT) {
            if (request.authority == null) {
               throw new IllegalArgumentException("Missing :authority / host header");
            }

            headers.authority(request.authority);
            e = false;
         } else {
            headers.path(request.uri);
            headers.scheme(((Http2ClientConnection)this.conn).isSsl() ? "https" : "http");
            if (request.authority != null) {
               headers.authority(request.authority);
            }

            e = end;
         }

         if (request.headers != null && request.headers.size() > 0) {
            for(Map.Entry header : request.headers) {
               headers.add(HttpUtils.toLowerCase((CharSequence)header.getKey()), header.getValue());
            }
         }

         if (((Http2ClientConnection)this.conn).client.options().isDecompressionSupported() && headers.get(HttpHeaderNames.ACCEPT_ENCODING) == null) {
            headers.set(HttpHeaderNames.ACCEPT_ENCODING, Http1xClientConnection.determineCompressionAcceptEncoding());
         }

         try {
            this.createStream(request, headers);
         } catch (Http2Exception var11) {
            if (handler != null) {
               handler.handle(this.context.failedFuture((Throwable)var11));
            }

            this.handleException(var11);
            return;
         }

         if (buf != null) {
            this.doWriteHeaders(headers, false, false, (Handler)null);
            this.doWriteData(buf, e, handler);
         } else {
            this.doWriteHeaders(headers, e, true, handler);
         }

      }

      private void createStream(HttpRequestHead head, Http2Headers headers) throws Http2Exception {
         int id = ((Http2ClientConnection)this.conn).handler.encoder().connection().local().lastStreamCreated();
         if (id == 0) {
            id = 1;
         } else {
            id += 2;
         }

         head.id = id;
         head.remoteAddress = ((Http2ClientConnection)this.conn).remoteAddress();
         Http2Stream stream = ((Http2ClientConnection)this.conn).handler.encoder().connection().local().createStream(id, false);
         this.init(stream);
         if (((Http2ClientConnection)this.conn).metrics != null) {
            this.metric = ((Http2ClientConnection)this.conn).metrics.requestBegin(headers.path().toString(), head);
         }

         VertxTracer tracer = this.context.tracer();
         if (tracer != null) {
            BiConsumer<String, String> headers_ = (key, val) -> (new Http2HeadersAdaptor(headers)).add(key, val);
            String operation = head.traceOperation;
            if (operation == null) {
               operation = headers.method().toString();
            }

            this.trace = tracer.sendRequest(this.context, SpanKind.RPC, ((Http2ClientConnection)this.conn).client.options().getTracingPolicy(), head, operation, headers_, HttpUtils.CLIENT_HTTP_REQUEST_TAG_EXTRACTOR);
         }

      }

      public void writeBuffer(ByteBuf buf, boolean end, Handler listener) {
         if (!this.checkReset(listener)) {
            if (buf != null) {
               int size = buf.readableBytes();
               synchronized(this) {
                  this.writeWindow += (long)size;
               }

               if (listener != null) {
                  listener = (ar) -> {
                     Handler<Void> drainHandler;
                     synchronized(this) {
                        boolean full = this.writeWindow > this.windowSize;
                        this.writeWindow -= (long)size;
                        if (full && this.writeWindow <= this.windowSize) {
                           drainHandler = this.drainHandler;
                        } else {
                           drainHandler = null;
                        }
                     }

                     if (drainHandler != null) {
                        drainHandler.handle((Object)null);
                     }

                     listener.handle(ar);
                  };
               }
            }

            this.writeData(buf, end, listener);
         }
      }

      public ContextInternal getContext() {
         return this.context;
      }

      public void doSetWriteQueueMaxSize(int size) {
      }

      public void reset(Throwable cause) {
         long code;
         if (cause instanceof StreamResetException) {
            code = ((StreamResetException)cause).getCode();
         } else if (cause instanceof TimeoutException) {
            code = 8L;
         } else {
            code = 0L;
         }

         ((Http2ClientConnection)this.conn).context.emit(code, this::writeReset);
      }

      public HttpClientConnection connection() {
         return (HttpClientConnection)this.conn;
      }
   }
}
