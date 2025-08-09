package io.vertx.core.http.impl;

import io.netty.channel.EventLoop;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http2.Http2Headers;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpFrame;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.impl.headers.Http2HeadersAdaptor;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.net.HostAndPort;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.observability.HttpRequest;
import io.vertx.core.spi.tracing.SpanKind;
import io.vertx.core.spi.tracing.VertxTracer;
import io.vertx.core.tracing.TracingPolicy;

class Http2ServerStream extends VertxHttp2Stream {
   protected final Http2Headers headers;
   protected final String scheme;
   protected final HttpMethod method;
   protected final String uri;
   protected final String host;
   protected final boolean hasAuthority;
   protected final HostAndPort authority;
   private final TracingPolicy tracingPolicy;
   private Object metric;
   private Object trace;
   private boolean halfClosedRemote;
   private boolean requestEnded;
   private boolean responseEnded;
   Http2ServerStreamHandler request;

   Http2ServerStream(Http2ServerConnection conn, ContextInternal context, HttpMethod method, String uri, TracingPolicy tracingPolicy, boolean halfClosedRemote) {
      super(conn, context);
      this.headers = null;
      this.method = method;
      this.uri = uri;
      this.scheme = null;
      this.host = null;
      this.hasAuthority = false;
      this.authority = null;
      this.tracingPolicy = tracingPolicy;
      this.halfClosedRemote = halfClosedRemote;
   }

   Http2ServerStream(Http2ServerConnection conn, ContextInternal context, Http2Headers headers, String scheme, boolean hasAuthority, HostAndPort authority, HttpMethod method, String uri, TracingPolicy tracingPolicy, boolean halfClosedRemote) {
      super(conn, context);
      this.scheme = scheme;
      this.headers = headers;
      this.hasAuthority = hasAuthority;
      this.authority = authority;
      this.host = authority != null ? authority.toString() : null;
      this.uri = uri;
      this.method = method;
      this.tracingPolicy = tracingPolicy;
      this.halfClosedRemote = halfClosedRemote;
   }

   void registerMetrics() {
      if (Metrics.METRICS_ENABLED) {
         HttpServerMetrics metrics = ((Http2ServerConnection)this.conn).metrics();
         if (metrics != null) {
            if (this.request.response().isPush()) {
               this.metric = metrics.responsePushed(((Http2ServerConnection)this.conn).metric(), this.method(), this.uri, this.request.response());
            } else {
               this.metric = metrics.requestBegin(((Http2ServerConnection)this.conn).metric(), (HttpRequest)this.request);
            }
         }
      }

   }

   void onHeaders(Http2Headers headers, StreamPriority streamPriority) {
      if (streamPriority != null) {
         this.priority(streamPriority);
      }

      this.registerMetrics();
      CharSequence value = (CharSequence)headers.get(HttpHeaderNames.EXPECT);
      if (((Http2ServerConnection)this.conn).options.isHandle100ContinueAutomatically() && (value != null && HttpHeaderValues.CONTINUE.equals(value) || headers.contains(HttpHeaderNames.EXPECT, HttpHeaderValues.CONTINUE))) {
         this.request.response().writeContinue();
      }

      VertxTracer tracer = this.context.tracer();
      if (tracer != null) {
         this.trace = tracer.receiveRequest(this.context, SpanKind.RPC, this.tracingPolicy, this.request, this.method().name(), new Http2HeadersAdaptor(headers), HttpUtils.SERVER_REQUEST_TAG_EXTRACTOR);
      }

      this.request.dispatch(((Http2ServerConnection)this.conn).requestHandler);
   }

   void onEnd(MultiMap trailers) {
      this.requestEnded = true;
      if (Metrics.METRICS_ENABLED) {
         HttpServerMetrics metrics = ((Http2ServerConnection)this.conn).metrics();
         if (metrics != null) {
            metrics.requestEnd(this.metric, (HttpRequest)this.request, this.bytesRead());
         }
      }

      super.onEnd(trailers);
   }

   void doWriteHeaders(Http2Headers headers, boolean end, boolean checkFlush, Handler handler) {
      if (Metrics.METRICS_ENABLED && !end) {
         HttpServerMetrics metrics = ((Http2ServerConnection)this.conn).metrics();
         if (metrics != null) {
            metrics.responseBegin(this.metric, this.request.response());
         }
      }

      super.doWriteHeaders(headers, end, checkFlush, handler);
   }

   protected void doWriteReset(long code) {
      if (!this.requestEnded || !this.responseEnded) {
         super.doWriteReset(code);
      }

   }

   void handleWritabilityChanged(boolean writable) {
      this.request.response().handlerWritabilityChanged(writable);
   }

   public HttpMethod method() {
      return this.method;
   }

   protected void endWritten() {
      this.responseEnded = true;
      if (Metrics.METRICS_ENABLED) {
         HttpServerMetrics metrics = ((Http2ServerConnection)this.conn).metrics();
         if (metrics != null) {
            metrics.responseEnd(this.metric, this.request.response(), this.bytesWritten());
         }
      }

   }

   void handleClose() {
      super.handleClose();
      this.request.handleClose();
   }

   void handleReset(long errorCode) {
      this.request.handleReset(errorCode);
   }

   void handleException(Throwable cause) {
      this.request.handleException(cause);
   }

   void handleCustomFrame(HttpFrame frame) {
      this.request.handleCustomFrame(frame);
   }

   void handlePriorityChange(StreamPriority newPriority) {
      this.request.handlePriorityChange(newPriority);
   }

   void handleData(Buffer buf) {
      this.request.handleData(buf);
   }

   void handleEnd(MultiMap trailers) {
      this.halfClosedRemote = true;
      this.request.handleEnd(trailers);
   }

   void onClose() {
      if (Metrics.METRICS_ENABLED) {
         HttpServerMetrics metrics = ((Http2ServerConnection)this.conn).metrics();
         if (metrics != null && (!this.requestEnded || !this.responseEnded)) {
            metrics.requestReset(this.metric);
         }
      }

      this.request.onClose();
      VertxTracer tracer = this.context.tracer();
      Object trace = this.trace;
      if (tracer != null && trace != null) {
         Throwable failure;
         synchronized((Http2ServerConnection)this.conn) {
            if (this.halfClosedRemote || this.requestEnded && this.responseEnded) {
               failure = null;
            } else {
               failure = HttpUtils.STREAM_CLOSED_EXCEPTION;
            }
         }

         tracer.sendResponse(this.context, failure == null ? this.request.response() : null, trace, failure, HttpUtils.SERVER_RESPONSE_TAG_EXTRACTOR);
      }

      super.onClose();
   }

   public Object metric() {
      return this.metric;
   }

   public void routed(String route) {
      if (Metrics.METRICS_ENABLED) {
         EventLoop eventLoop = this.vertx.getOrCreateContext().nettyEventLoop();
         synchronized(this) {
            if (this.shouldQueue(eventLoop)) {
               this.queueForWrite(eventLoop, () -> this.routedInternal(route));
               return;
            }
         }

         this.routedInternal(route);
      }

   }

   private void routedInternal(String route) {
      HttpServerMetrics metrics = ((Http2ServerConnection)this.conn).metrics();
      if (metrics != null && !this.responseEnded) {
         metrics.requestRouted(this.metric, route);
      }

   }
}
