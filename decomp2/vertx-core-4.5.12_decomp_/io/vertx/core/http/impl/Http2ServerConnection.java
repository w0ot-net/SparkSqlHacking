package io.vertx.core.http.impl;

import io.netty.channel.EventLoop;
import io.netty.handler.codec.compression.CompressionOptions;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.Http2Error;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2Settings;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.StreamResetException;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.net.HostAndPort;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import java.util.ArrayDeque;
import java.util.function.Function;
import java.util.function.Supplier;

public class Http2ServerConnection extends Http2ConnectionBase implements HttpServerConnection {
   final HttpServerOptions options;
   private final String serverOrigin;
   private final HttpServerMetrics metrics;
   private final Function encodingDetector;
   private final Supplier streamContextSupplier;
   Handler requestHandler;
   private int concurrentStreams;
   private final ArrayDeque pendingPushes = new ArrayDeque(8);
   private VertxHttp2Stream upgraded;

   Http2ServerConnection(ContextInternal context, Supplier streamContextSupplier, String serverOrigin, VertxHttp2ConnectionHandler connHandler, Function encodingDetector, HttpServerOptions options, HttpServerMetrics metrics) {
      super(context, connHandler);
      this.options = options;
      this.serverOrigin = serverOrigin;
      this.encodingDetector = encodingDetector;
      this.streamContextSupplier = streamContextSupplier;
      this.metrics = metrics;
   }

   public HttpServerConnection handler(Handler handler) {
      this.requestHandler = handler;
      return this;
   }

   public HttpServerConnection invalidRequestHandler(Handler handler) {
      return this;
   }

   public HttpServerMetrics metrics() {
      return this.metrics;
   }

   private static boolean isMalformedRequest(Http2ServerStream request) {
      if (request.method == null) {
         return true;
      } else {
         if (request.method == HttpMethod.CONNECT) {
            if (request.scheme != null || request.uri != null || request.authority == null) {
               return true;
            }
         } else if (request.scheme == null || request.uri == null || request.uri.length() == 0) {
            return true;
         }

         if (request.hasAuthority) {
            if (request.authority == null) {
               return true;
            }

            CharSequence hostHeader = (CharSequence)request.headers.get(HttpHeaders.HOST);
            if (hostHeader != null) {
               HostAndPort host = HostAndPort.parseAuthority(hostHeader.toString(), -1);
               return host == null || !request.authority.host().equals(host.host()) || request.authority.port() != host.port();
            }
         }

         return false;
      }
   }

   String determineContentEncoding(Http2Headers headers) {
      String acceptEncoding = headers.get(HttpHeaderNames.ACCEPT_ENCODING) != null ? ((CharSequence)headers.get(HttpHeaderNames.ACCEPT_ENCODING)).toString() : null;
      return acceptEncoding != null && this.encodingDetector != null ? (String)this.encodingDetector.apply(acceptEncoding) : null;
   }

   private Http2ServerStream createStream(Http2Headers headers, boolean streamEnded) {
      CharSequence schemeHeader = (CharSequence)headers.getAndRemove(HttpHeaders.PSEUDO_SCHEME);
      HostAndPort authority = null;
      CharSequence authorityHeader = (CharSequence)headers.getAndRemove(HttpHeaders.PSEUDO_AUTHORITY);
      if (authorityHeader != null) {
         String authorityHeaderAsString = authorityHeader.toString();
         authority = HostAndPort.parseAuthority(authorityHeaderAsString, -1);
      }

      CharSequence hostHeader = null;
      if (authority == null) {
         hostHeader = (CharSequence)headers.getAndRemove(HttpHeaders.HOST);
         if (hostHeader != null) {
            authority = HostAndPort.parseAuthority(hostHeader.toString(), -1);
         }
      }

      CharSequence pathHeader = (CharSequence)headers.getAndRemove(HttpHeaders.PSEUDO_PATH);
      CharSequence methodHeader = (CharSequence)headers.getAndRemove(HttpHeaders.PSEUDO_METHOD);
      return new Http2ServerStream(this, (ContextInternal)this.streamContextSupplier.get(), headers, schemeHeader != null ? schemeHeader.toString() : null, authorityHeader != null || hostHeader != null, authority, methodHeader != null ? HttpMethod.valueOf(methodHeader.toString()) : null, pathHeader != null ? pathHeader.toString() : null, this.options.getTracingPolicy(), streamEnded);
   }

   private void initStream(int streamId, Http2ServerStream vertxStream) {
      String contentEncoding = this.options.isCompressionSupported() ? this.determineContentEncoding(vertxStream.headers) : null;
      Http2ServerRequest request = new Http2ServerRequest(vertxStream, this.serverOrigin, vertxStream.headers, contentEncoding);
      vertxStream.request = request;
      vertxStream.isConnect = request.method() == HttpMethod.CONNECT;
      Http2Stream stream = this.handler.connection().stream(streamId);
      vertxStream.init(stream);
   }

   VertxHttp2Stream stream(int id) {
      VertxHttp2Stream<?> stream = super.stream(id);
      return stream == null && id == 1 && this.handler.upgraded ? this.upgraded : stream;
   }

   protected synchronized void onHeadersRead(int streamId, Http2Headers headers, StreamPriority streamPriority, boolean endOfStream) {
      Http2ServerStream stream = (Http2ServerStream)this.stream(streamId);
      if (stream == null) {
         if (streamId == 1 && this.handler.upgraded) {
            stream = this.createStream(headers, true);
            this.upgraded = stream;
         } else {
            stream = this.createStream(headers, endOfStream);
         }

         if (isMalformedRequest(stream)) {
            this.handler.writeReset(streamId, Http2Error.PROTOCOL_ERROR.code());
            return;
         }

         this.initStream(streamId, stream);
         stream.onHeaders(headers, streamPriority);
      }

      if (endOfStream) {
         stream.onEnd();
      }

   }

   void sendPush(int streamId, HostAndPort authority, HttpMethod method, MultiMap headers, String path, StreamPriority streamPriority, Promise promise) {
      EventLoop eventLoop = this.context.nettyEventLoop();
      if (eventLoop.inEventLoop()) {
         this.doSendPush(streamId, authority, method, headers, path, streamPriority, promise);
      } else {
         eventLoop.execute(() -> this.doSendPush(streamId, authority, method, headers, path, streamPriority, promise));
      }

   }

   private synchronized void doSendPush(int streamId, HostAndPort authority, HttpMethod method, MultiMap headers, String path, StreamPriority streamPriority, Promise promise) {
      boolean ssl = this.isSsl();
      Http2Headers headers_ = new DefaultHttp2Headers();
      headers_.method(method.name());
      headers_.path(path);
      headers_.scheme(ssl ? "https" : "http");
      if (authority != null) {
         String s = (!ssl || authority.port() != 443) && (ssl || authority.port() != 80) && authority.port() > 0 ? authority.host() + ':' + authority.port() : authority.host();
         headers_.authority(s);
      }

      if (headers != null) {
         headers.forEach((header) -> {
            Http2Headers var10000 = (Http2Headers)headers_.add(header.getKey(), header.getValue());
         });
      }

      Future<Integer> fut = this.handler.writePushPromise(streamId, headers_);
      fut.addListener((FutureListener)(future) -> {
         if (future.isSuccess()) {
            synchronized(this) {
               int promisedStreamId = (Integer)future.getNow();
               String contentEncoding = this.determineContentEncoding(headers_);
               Http2Stream promisedStream = this.handler.connection().stream(promisedStreamId);
               Http2ServerStream vertxStream = new Http2ServerStream(this, this.context, method, path, this.options.getTracingPolicy(), true);
               Push push = new Push(vertxStream, contentEncoding, promise);
               vertxStream.request = push;
               push.stream.priority(streamPriority);
               push.stream.init(promisedStream);
               int maxConcurrentStreams = this.handler.maxConcurrentStreams();
               if (this.concurrentStreams < maxConcurrentStreams) {
                  ++this.concurrentStreams;
                  push.complete();
               } else {
                  this.pendingPushes.add(push);
               }
            }
         } else {
            promise.fail(future.cause());
         }

      });
   }

   protected void updateSettings(Http2Settings settingsUpdate, Handler completionHandler) {
      settingsUpdate.remove('\u0002');
      super.updateSettings(settingsUpdate, completionHandler);
   }

   private static class EncodingDetector extends HttpContentCompressor {
      private EncodingDetector(CompressionOptions[] compressionOptions) {
         super(compressionOptions);
      }

      protected String determineEncoding(String acceptEncoding) {
         return super.determineEncoding(acceptEncoding);
      }
   }

   private class Push implements Http2ServerStreamHandler {
      protected final ContextInternal context;
      protected final Http2ServerStream stream;
      protected final Http2ServerResponse response;
      private final Promise promise;

      public Push(Http2ServerStream stream, String contentEncoding, Promise promise) {
         this.context = stream.context;
         this.stream = stream;
         this.response = new Http2ServerResponse((Http2ServerConnection)stream.conn, stream, true, contentEncoding);
         this.promise = promise;
      }

      public Http2ServerResponse response() {
         return this.response;
      }

      public void dispatch(Handler handler) {
         throw new UnsupportedOperationException();
      }

      public void handleReset(long errorCode) {
         if (!this.promise.tryFail((Throwable)(new StreamResetException(errorCode)))) {
            this.response.handleReset(errorCode);
         }

      }

      public void handleException(Throwable cause) {
         if (this.response != null) {
            this.response.handleException(cause);
         }

      }

      public void handleClose() {
         if (Http2ServerConnection.this.pendingPushes.remove(this)) {
            this.promise.fail("Push reset by client");
         } else {
            Http2ServerConnection.this.concurrentStreams--;
            int maxConcurrentStreams = Http2ServerConnection.this.handler.maxConcurrentStreams();

            while(Http2ServerConnection.this.concurrentStreams < maxConcurrentStreams && Http2ServerConnection.this.pendingPushes.size() > 0) {
               Push push = (Push)Http2ServerConnection.this.pendingPushes.pop();
               Http2ServerConnection.this.concurrentStreams++;
               push.complete();
            }

            this.response.handleClose();
         }

      }

      void complete() {
         this.stream.registerMetrics();
         this.promise.complete(this.response);
      }
   }
}
