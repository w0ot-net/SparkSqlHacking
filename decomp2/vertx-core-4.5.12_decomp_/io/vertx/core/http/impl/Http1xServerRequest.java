package io.vertx.core.http.impl;

import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpFrame;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerFileUpload;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.impl.headers.HeadersAdaptor;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.net.HostAndPort;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.HostAndPortImpl;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.observability.HttpRequest;
import io.vertx.core.spi.tracing.SpanKind;
import io.vertx.core.spi.tracing.TagExtractor;
import io.vertx.core.spi.tracing.VertxTracer;
import io.vertx.core.streams.impl.InboundBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.security.cert.X509Certificate;

public class Http1xServerRequest extends HttpServerRequestInternal implements HttpRequest {
   private static final HostAndPort NULL_HOST_AND_PORT = HostAndPort.create("", -1);
   private final Http1xServerConnection conn;
   final ContextInternal context;
   private io.netty.handler.codec.http.HttpRequest request;
   private HttpVersion version;
   private HttpMethod method;
   private HostAndPort authority;
   private String uri;
   private String path;
   private String query;
   Http1xServerRequest next;
   Object metric;
   Object trace;
   private Http1xServerResponse response;
   private Charset paramsCharset;
   private MultiMap params;
   private boolean semicolonIsNormalCharInParams;
   private MultiMap headers;
   private String absoluteURI;
   private HttpEventHandler eventHandler;
   private Handler uploadHandler;
   private MultiMap attributes;
   private boolean expectMultipart;
   private HttpPostRequestDecoder decoder;
   private boolean ended;
   private long bytesRead;
   private InboundBuffer pending;

   Http1xServerRequest(Http1xServerConnection conn, io.netty.handler.codec.http.HttpRequest request, ContextInternal context) {
      this.paramsCharset = StandardCharsets.UTF_8;
      this.conn = conn;
      this.context = context;
      this.request = request;
   }

   private HttpEventHandler eventHandler(boolean create) {
      if (this.eventHandler == null && create) {
         this.eventHandler = new HttpEventHandler(this.context);
      }

      return this.eventHandler;
   }

   io.netty.handler.codec.http.HttpRequest nettyRequest() {
      synchronized(this.conn) {
         return this.request;
      }
   }

   void setRequest(io.netty.handler.codec.http.HttpRequest request) {
      synchronized(this.conn) {
         this.request = request;
      }
   }

   void handleContent(Buffer buffer) {
      InboundBuffer<Object> queue;
      synchronized(this.conn) {
         queue = this.pending;
      }

      if (queue != null) {
         if (!queue.write((Object)buffer)) {
            this.conn.doPause();
         }
      } else {
         this.onData(buffer);
      }

   }

   void handleBegin(boolean writable, boolean keepAlive) {
      if (Metrics.METRICS_ENABLED) {
         this.reportRequestBegin();
      }

      this.response = new Http1xServerResponse((VertxInternal)this.conn.vertx(), this.context, this.conn, this.request, this.metric, writable, keepAlive);
      if (this.conn.handle100ContinueAutomatically) {
         this.check100();
      }

   }

   void enqueue(Http1xServerRequest request) {
      Http1xServerRequest current;
      for(current = this; current.next != null; current = current.next) {
      }

      current.next = request;
   }

   Http1xServerRequest next() {
      return this.next;
   }

   private void check100() {
      if (HttpUtil.is100ContinueExpected(this.request)) {
         this.conn.write100Continue();
      }

   }

   public Object metric() {
      return this.metric;
   }

   Object trace() {
      return this.trace;
   }

   public Context context() {
      return this.context;
   }

   public int id() {
      return 0;
   }

   public HttpVersion version() {
      if (this.version == null) {
         io.netty.handler.codec.http.HttpVersion nettyVersion = this.request.protocolVersion();
         if (nettyVersion == io.netty.handler.codec.http.HttpVersion.HTTP_1_0) {
            this.version = HttpVersion.HTTP_1_0;
         } else if (nettyVersion == io.netty.handler.codec.http.HttpVersion.HTTP_1_1) {
            this.version = HttpVersion.HTTP_1_1;
         }
      }

      return this.version;
   }

   public HttpMethod method() {
      if (this.method == null) {
         this.method = HttpMethod.fromNetty(this.request.method());
      }

      return this.method;
   }

   public String uri() {
      if (this.uri == null) {
         this.uri = this.request.uri();
      }

      return this.uri;
   }

   public String path() {
      if (this.path == null) {
         this.path = HttpUtils.parsePath(this.uri());
      }

      return this.path;
   }

   public String query() {
      if (this.query == null) {
         this.query = HttpUtils.parseQuery(this.uri());
      }

      return this.query;
   }

   public boolean isValidAuthority() {
      HostAndPort authority = this.authority;
      if (authority == NULL_HOST_AND_PORT) {
         return false;
      } else if (authority != null) {
         return true;
      } else {
         String host = this.getHeader(HttpHeaderNames.HOST);
         if (host != null && HostAndPortImpl.isValidAuthority(host)) {
            return true;
         } else {
            this.authority = NULL_HOST_AND_PORT;
            return false;
         }
      }
   }

   public HostAndPort authority() {
      HostAndPort authority = this.authority;
      if (authority == NULL_HOST_AND_PORT) {
         return null;
      } else {
         if (authority == null) {
            String host = this.getHeader(HttpHeaderNames.HOST);
            if (host == null) {
               this.authority = NULL_HOST_AND_PORT;
               return null;
            }

            authority = HostAndPort.parseAuthority(host, -1);
            this.authority = authority;
         }

         return authority;
      }
   }

   public @Nullable String host() {
      return this.getHeader(HttpHeaderNames.HOST);
   }

   public long bytesRead() {
      synchronized(this.conn) {
         return this.bytesRead;
      }
   }

   public Http1xServerResponse response() {
      return this.response;
   }

   public MultiMap headers() {
      MultiMap headers = this.headers;
      if (headers == null) {
         HttpHeaders reqHeaders = this.request.headers();
         if (reqHeaders instanceof MultiMap) {
            headers = (MultiMap)reqHeaders;
         } else {
            headers = new HeadersAdaptor(reqHeaders);
         }

         this.headers = headers;
      }

      return headers;
   }

   public HttpServerRequest setParamsCharset(String charset) {
      Objects.requireNonNull(charset, "Charset must not be null");
      Charset current = this.paramsCharset;
      this.paramsCharset = Charset.forName(charset);
      if (!this.paramsCharset.equals(current)) {
         this.params = null;
      }

      return this;
   }

   public String getParamsCharset() {
      return this.paramsCharset.name();
   }

   public MultiMap params(boolean semicolonIsNormalChar) {
      if (this.params == null || semicolonIsNormalChar != this.semicolonIsNormalCharInParams) {
         this.params = HttpUtils.params(this.uri(), this.paramsCharset, semicolonIsNormalChar);
         this.semicolonIsNormalCharInParams = semicolonIsNormalChar;
      }

      return this.params;
   }

   public HttpServerRequest handler(Handler handler) {
      synchronized(this.conn) {
         if (handler != null) {
            this.checkEnded();
         }

         HttpEventHandler eventHandler = this.eventHandler(handler != null);
         if (eventHandler != null) {
            eventHandler.chunkHandler(handler);
         }

         return this;
      }
   }

   public HttpServerRequest exceptionHandler(Handler handler) {
      synchronized(this.conn) {
         HttpEventHandler eventHandler = this.eventHandler(handler != null);
         if (eventHandler != null) {
            eventHandler.exceptionHandler(handler);
         }

         return this;
      }
   }

   public HttpServerRequest pause() {
      synchronized(this.conn) {
         if (this.pending != null) {
            this.pending.pause();
         } else {
            this.pending = InboundBuffer.createPaused(this.context, 8L, this.pendingDrainHandler(), this.pendingHandler());
         }

         return this;
      }
   }

   private Handler pendingHandler() {
      return (buffer) -> {
         if (buffer == InboundBuffer.END_SENTINEL) {
            this.onEnd();
         } else {
            this.onData((Buffer)buffer);
         }

      };
   }

   private Handler pendingDrainHandler() {
      return (v) -> this.conn.doResume();
   }

   public HttpServerRequest fetch(long amount) {
      synchronized(this.conn) {
         if (this.pending != null) {
            this.pending.fetch(amount);
         } else {
            this.pending = InboundBuffer.createAndFetch(this.context, 8L, amount, this.pendingDrainHandler(), this.pendingHandler());
         }

         return this;
      }
   }

   public HttpServerRequest resume() {
      return this.fetch(Long.MAX_VALUE);
   }

   public HttpServerRequest endHandler(Handler handler) {
      synchronized(this.conn) {
         if (handler != null) {
            this.checkEnded();
         }

         HttpEventHandler eventHandler = this.eventHandler(handler != null);
         if (eventHandler != null) {
            eventHandler.endHandler(handler);
         }

         return this;
      }
   }

   public String scheme() {
      return this.isSSL() ? "https" : "http";
   }

   public String absoluteURI() {
      if (this.absoluteURI == null) {
         this.absoluteURI = HttpUtils.absoluteURI(this.conn.getServerOrigin(), this);
      }

      return this.absoluteURI;
   }

   public SocketAddress remoteAddress() {
      return super.remoteAddress();
   }

   public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
      return this.conn.peerCertificateChain();
   }

   public Future toNetSocket() {
      return this.response.netSocket(this.method(), this.headers());
   }

   public HttpServerRequest uploadHandler(Handler handler) {
      synchronized(this.conn) {
         if (handler != null) {
            this.checkEnded();
         }

         this.uploadHandler = handler;
         return this;
      }
   }

   public MultiMap formAttributes() {
      return this.attributes();
   }

   public String getFormAttribute(String attributeName) {
      return this.formAttributes().get(attributeName);
   }

   public Future toWebSocket() {
      return this.webSocket().map((Function)((ws) -> {
         ws.accept();
         return ws;
      }));
   }

   Future webSocket() {
      PromiseInternal<ServerWebSocket> promise = this.context.promise();
      this.webSocket(promise);
      return promise.future();
   }

   private void webSocket(PromiseInternal promise) {
      Buffer body = Buffer.buffer();
      boolean[] failed = new boolean[1];
      this.handler((buff) -> {
         if (!failed[0]) {
            body.appendBuffer(buff);
            if (body.length() > 8192) {
               failed[0] = true;
               this.response.setStatusCode(413).end();
               this.response.close();
            }
         }

      });
      this.exceptionHandler(promise::tryFail);
      this.endHandler((v) -> {
         if (!failed[0]) {
            this.request = new DefaultFullHttpRequest(this.request.protocolVersion(), this.request.method(), this.request.uri(), body.getByteBuf(), this.request.headers(), EmptyHttpHeaders.INSTANCE);
            this.conn.createWebSocket(this, promise);
         }

      });
      this.resume();
   }

   public HttpServerRequest setExpectMultipart(boolean expect) {
      synchronized(this.conn) {
         this.checkEnded();
         this.expectMultipart = expect;
         if (expect) {
            if (this.decoder == null) {
               String contentType = this.request.headers().get(HttpHeaderNames.CONTENT_TYPE);
               if (contentType == null) {
                  throw new IllegalStateException("Request must have a content-type header to decode a multipart request");
               }

               if (!HttpUtils.isValidMultipartContentType(contentType)) {
                  throw new IllegalStateException("Request must have a valid content-type header to decode a multipart request");
               }

               if (!HttpUtils.isValidMultipartMethod(this.request.method())) {
                  throw new IllegalStateException("Request method must be one of POST, PUT, PATCH or DELETE to decode a multipart request");
               }

               NettyFileUploadDataFactory factory = new NettyFileUploadDataFactory(this.context, this, () -> this.uploadHandler);
               HttpServerOptions options = this.conn.options;
               factory.setMaxLimit((long)options.getMaxFormAttributeSize());
               int maxFields = options.getMaxFormFields();
               int maxBufferedBytes = options.getMaxFormBufferedBytes();
               this.decoder = new HttpPostRequestDecoder(factory, this.request, HttpConstants.DEFAULT_CHARSET, maxFields, maxBufferedBytes);
            }
         } else {
            this.decoder = null;
         }

         return this;
      }
   }

   public synchronized boolean isExpectMultipart() {
      return this.expectMultipart;
   }

   public boolean isEnded() {
      synchronized(this.conn) {
         return this.ended && (this.pending == null || !this.pending.isPaused() && this.pending.isEmpty());
      }
   }

   public HttpServerRequest customFrameHandler(Handler handler) {
      return this;
   }

   public HttpConnection connection() {
      return this.conn;
   }

   public synchronized Future body() {
      this.checkEnded();
      return this.eventHandler(true).body();
   }

   public synchronized Future end() {
      this.checkEnded();
      return this.eventHandler(true).end();
   }

   private void onData(Buffer data) {
      HttpEventHandler handler;
      synchronized(this.conn) {
         this.bytesRead += (long)data.length();
         if (this.decoder != null) {
            try {
               this.decoder.offer(new DefaultHttpContent(data.getByteBuf()));
            } catch (HttpPostRequestDecoder.TooLongFormFieldException | HttpPostRequestDecoder.TooManyFormFieldsException | HttpPostRequestDecoder.ErrorDataDecoderException e) {
               this.decoder.destroy();
               this.decoder = null;
               this.handleException(e);
            }
         }

         handler = this.eventHandler;
      }

      if (handler != null) {
         this.eventHandler.handleChunk(data);
      }

   }

   void handleEnd() {
      HttpEventHandler handler = null;
      InboundBuffer<Object> queue;
      synchronized(this.conn) {
         this.ended = true;
         queue = this.pending;
         if (queue == null) {
            handler = this.endRequest();
         }
      }

      if (queue != null) {
         queue.write(InboundBuffer.END_SENTINEL);
      } else if (handler != null) {
         handler.handleEnd();
      }

   }

   private HttpEventHandler endRequest() {
      assert Thread.holdsLock(this.conn);

      if (Metrics.METRICS_ENABLED) {
         this.reportRequestComplete();
      }

      if (this.decoder != null) {
         this.endDecode();
      }

      return this.eventHandler;
   }

   private void onEnd() {
      HttpEventHandler handler;
      synchronized(this.conn) {
         handler = this.endRequest();
      }

      if (handler != null) {
         handler.handleEnd();
      }

   }

   private void reportRequestComplete() {
      HttpServerMetrics metrics = this.conn.metrics;
      if (metrics != null) {
         metrics.requestEnd(this.metric, this, this.bytesRead);
         this.conn.flushBytesRead();
      }

   }

   private void reportRequestBegin() {
      HttpServerMetrics metrics = this.conn.metrics;
      if (metrics != null) {
         this.metric = metrics.requestBegin(this.conn.metric(), this);
      }

      VertxTracer tracer = this.context.tracer();
      if (tracer != null) {
         this.trace = tracer.receiveRequest(this.context, SpanKind.RPC, this.conn.tracingPolicy(), this, this.request.method().name(), this.request.headers(), HttpUtils.SERVER_REQUEST_TAG_EXTRACTOR);
      }

   }

   private void endDecode() {
      try {
         this.decoder.offer(LastHttpContent.EMPTY_LAST_CONTENT);

         while(this.decoder.hasNext()) {
            InterfaceHttpData data = this.decoder.next();
            if (data instanceof Attribute) {
               Attribute attr = (Attribute)data;

               try {
                  this.attributes().add(attr.getName(), attr.getValue());
               } catch (Exception e) {
                  this.handleException(e);
               } finally {
                  attr.release();
               }
            }
         }
      } catch (HttpPostRequestDecoder.TooLongFormFieldException | HttpPostRequestDecoder.TooManyFormFieldsException | HttpPostRequestDecoder.ErrorDataDecoderException e) {
         this.handleException(e);
      } catch (HttpPostRequestDecoder.EndOfDataDecoderException var19) {
      } finally {
         this.decoder.destroy();
         this.decoder = null;
      }

   }

   void handleException(Throwable t) {
      HttpEventHandler handler = null;
      Http1xServerResponse resp = null;
      InterfaceHttpData upload = null;
      synchronized(this.conn) {
         if (!this.isEnded()) {
            handler = this.eventHandler;
            if (this.decoder != null) {
               upload = this.decoder.currentPartialHttpData();
            }
         }

         if (!this.response.ended()) {
            if (Metrics.METRICS_ENABLED) {
               this.reportRequestReset(t);
            }

            resp = this.response;
         }
      }

      if (resp != null) {
         resp.handleException(t);
      }

      if (upload instanceof NettyFileUpload) {
         ((NettyFileUpload)upload).handleException(t);
      }

      if (handler != null) {
         handler.handleException(t);
      }

   }

   private void reportRequestReset(Throwable err) {
      if (this.conn.metrics != null) {
         this.conn.metrics.requestReset(this.metric);
      }

      VertxTracer tracer = this.context.tracer();
      if (tracer != null) {
         tracer.sendResponse(this.context, (Object)null, this.trace, err, TagExtractor.empty());
      }

   }

   private void checkEnded() {
      if (this.isEnded()) {
         throw new IllegalStateException("Request has already been read");
      }
   }

   private MultiMap attributes() {
      if (this.attributes == null) {
         this.attributes = MultiMap.caseInsensitiveMultiMap();
      }

      return this.attributes;
   }

   public HttpServerRequest streamPriorityHandler(Handler handler) {
      return this;
   }

   public DecoderResult decoderResult() {
      return this.request.decoderResult();
   }

   public Set cookies() {
      return this.response.cookies();
   }

   public Set cookies(String name) {
      return this.response.cookies().getAll(name);
   }

   public Cookie getCookie(String name) {
      return this.response.cookies().get(name);
   }

   public Cookie getCookie(String name, String domain, String path) {
      return this.response.cookies().get(name, domain, path);
   }

   public HttpServerRequest routed(String route) {
      if (Metrics.METRICS_ENABLED && !this.response.ended() && this.conn.metrics != null) {
         this.conn.metrics.requestRouted(this.metric, route);
      }

      return this;
   }
}
