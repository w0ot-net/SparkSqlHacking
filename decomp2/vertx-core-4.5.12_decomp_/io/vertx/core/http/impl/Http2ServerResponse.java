package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpStatusClass;
import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.Http2Headers;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.StreamResetException;
import io.vertx.core.http.impl.headers.Http2HeadersAdaptor;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.net.HostAndPort;
import io.vertx.core.spi.observability.HttpResponse;
import io.vertx.core.streams.ReadStream;
import java.util.Map;
import java.util.Set;

public class Http2ServerResponse implements HttpServerResponse, HttpResponse {
   private final Http2ServerStream stream;
   private final ChannelHandlerContext ctx;
   private final Http2ServerConnection conn;
   private final boolean push;
   private final String contentEncoding;
   private final Http2Headers headers = new DefaultHttp2Headers();
   private Http2HeadersAdaptor headersMap;
   private Http2Headers trailers;
   private Http2HeadersAdaptor trailedMap;
   private boolean chunked;
   private boolean headWritten;
   private boolean ended;
   private boolean closed;
   private CookieJar cookies;
   private HttpResponseStatus status;
   private String statusMessage;
   private Handler drainHandler;
   private Handler exceptionHandler;
   private Handler headersEndHandler;
   private Handler bodyEndHandler;
   private Handler closeHandler;
   private Handler endHandler;
   private Future netSocket;

   public Http2ServerResponse(Http2ServerConnection conn, Http2ServerStream stream, boolean push, String contentEncoding) {
      this.status = HttpResponseStatus.OK;
      this.stream = stream;
      this.ctx = conn.handlerContext;
      this.conn = conn;
      this.push = push;
      this.contentEncoding = contentEncoding;
   }

   boolean isPush() {
      return this.push;
   }

   void handleReset(long code) {
      this.handleException(new StreamResetException(code));
   }

   void handleException(Throwable cause) {
      Handler<Throwable> handler;
      synchronized(this.conn) {
         if (this.ended) {
            return;
         }

         handler = this.exceptionHandler;
      }

      if (handler != null) {
         handler.handle(cause);
      }

   }

   void handleClose() {
      Handler<Void> endHandler;
      Handler<Void> closeHandler;
      synchronized(this.conn) {
         this.closed = true;
         boolean failed = !this.ended;
         endHandler = failed ? this.endHandler : null;
         closeHandler = this.closeHandler;
      }

      if (endHandler != null) {
         this.stream.context.emit((Object)null, endHandler);
      }

      if (closeHandler != null) {
         this.stream.context.emit((Object)null, closeHandler);
      }

   }

   private void checkHeadWritten() {
      if (this.headWritten) {
         throw new IllegalStateException("Response head already sent");
      }
   }

   public HttpServerResponse exceptionHandler(Handler handler) {
      synchronized(this.conn) {
         if (handler != null) {
            this.checkValid();
         }

         this.exceptionHandler = handler;
         return this;
      }
   }

   public int statusCode() {
      return this.getStatusCode();
   }

   public int getStatusCode() {
      synchronized(this.conn) {
         return this.status.code();
      }
   }

   public HttpServerResponse setStatusCode(int statusCode) {
      if (statusCode < 0) {
         throw new IllegalArgumentException("code: " + statusCode + " (expected: 0+)");
      } else {
         synchronized(this.conn) {
            this.checkHeadWritten();
            this.status = HttpResponseStatus.valueOf(statusCode);
            return this;
         }
      }
   }

   public String getStatusMessage() {
      synchronized(this.conn) {
         return this.statusMessage == null ? this.status.reasonPhrase() : this.statusMessage;
      }
   }

   public HttpServerResponse setStatusMessage(String statusMessage) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         this.statusMessage = statusMessage;
         return this;
      }
   }

   public HttpServerResponse setChunked(boolean chunked) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         this.chunked = true;
         return this;
      }
   }

   public boolean isChunked() {
      synchronized(this.conn) {
         return this.chunked;
      }
   }

   public MultiMap headers() {
      synchronized(this.conn) {
         if (this.headersMap == null) {
            this.headersMap = new Http2HeadersAdaptor(this.headers);
         }

         return this.headersMap;
      }
   }

   public HttpServerResponse putHeader(String name, String value) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         this.headers().set(name, value);
         return this;
      }
   }

   public HttpServerResponse putHeader(CharSequence name, CharSequence value) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         this.headers().set(name, value);
         return this;
      }
   }

   public HttpServerResponse putHeader(String name, Iterable values) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         this.headers().set(name, values);
         return this;
      }
   }

   public HttpServerResponse putHeader(CharSequence name, Iterable values) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         this.headers().set(name, values);
         return this;
      }
   }

   public MultiMap trailers() {
      synchronized(this.conn) {
         if (this.trailedMap == null) {
            this.trailedMap = new Http2HeadersAdaptor(this.trailers = new DefaultHttp2Headers());
         }

         return this.trailedMap;
      }
   }

   public HttpServerResponse putTrailer(String name, String value) {
      synchronized(this.conn) {
         this.checkValid();
         this.trailers().set(name, value);
         return this;
      }
   }

   public HttpServerResponse putTrailer(CharSequence name, CharSequence value) {
      synchronized(this.conn) {
         this.checkValid();
         this.trailers().set(name, value);
         return this;
      }
   }

   public HttpServerResponse putTrailer(String name, Iterable values) {
      synchronized(this.conn) {
         this.checkValid();
         this.trailers().set(name, values);
         return this;
      }
   }

   public HttpServerResponse putTrailer(CharSequence name, Iterable value) {
      synchronized(this.conn) {
         this.checkValid();
         this.trailers().set(name, value);
         return this;
      }
   }

   public HttpServerResponse closeHandler(Handler handler) {
      synchronized(this.conn) {
         if (handler != null) {
            this.checkValid();
         }

         this.closeHandler = handler;
         return this;
      }
   }

   public HttpServerResponse endHandler(@Nullable Handler handler) {
      synchronized(this.conn) {
         if (handler != null) {
            this.checkValid();
         }

         this.endHandler = handler;
         return this;
      }
   }

   public HttpServerResponse writeContinue() {
      synchronized(this.conn) {
         this.checkHeadWritten();
         this.stream.writeHeaders((new DefaultHttp2Headers()).status(HttpResponseStatus.CONTINUE.codeAsText()), false, true, (Handler)null);
         return this;
      }
   }

   public Future writeEarlyHints(MultiMap headers) {
      PromiseInternal<Void> promise = this.stream.context.promise();
      this.writeEarlyHints(headers, promise);
      return promise.future();
   }

   public void writeEarlyHints(MultiMap headers, Handler handler) {
      DefaultHttp2Headers http2Headers = new DefaultHttp2Headers();

      for(Map.Entry header : headers) {
         http2Headers.add(header.getKey(), header.getValue());
      }

      http2Headers.status(HttpResponseStatus.EARLY_HINTS.codeAsText());
      synchronized(this.conn) {
         this.checkHeadWritten();
      }

      this.stream.writeHeaders(http2Headers, false, true, handler);
   }

   public Future write(Buffer chunk) {
      ByteBuf buf = chunk.getByteBuf();
      Promise<Void> promise = this.stream.context.promise();
      this.write(buf, false, promise);
      return promise.future();
   }

   public void write(Buffer chunk, Handler handler) {
      ByteBuf buf = chunk.getByteBuf();
      this.write(buf, false, handler);
   }

   public Future write(String chunk, String enc) {
      Promise<Void> promise = this.stream.context.promise();
      this.write(Buffer.buffer(chunk, enc).getByteBuf(), false, promise);
      return promise.future();
   }

   public void write(String chunk, String enc, Handler handler) {
      this.write(Buffer.buffer(chunk, enc).getByteBuf(), false, handler);
   }

   public Future write(String chunk) {
      Promise<Void> promise = this.stream.context.promise();
      this.write(Buffer.buffer(chunk).getByteBuf(), false, promise);
      return promise.future();
   }

   public void write(String chunk, Handler handler) {
      this.write(Buffer.buffer(chunk).getByteBuf(), false, handler);
   }

   private Http2ServerResponse write(ByteBuf chunk) {
      this.write(chunk, false, (Handler)null);
      return this;
   }

   public Future end(String chunk) {
      return this.end(Buffer.buffer(chunk));
   }

   public void end(String chunk, Handler handler) {
      this.end(Buffer.buffer(chunk), handler);
   }

   public Future end(String chunk, String enc) {
      return this.end(Buffer.buffer(chunk, enc));
   }

   public void end(String chunk, String enc, Handler handler) {
      this.end(Buffer.buffer(chunk, enc));
   }

   public Future end(Buffer chunk) {
      Promise<Void> promise = this.stream.context.promise();
      this.write(chunk.getByteBuf(), true, promise);
      return promise.future();
   }

   public void end(Buffer chunk, Handler handler) {
      this.end(chunk.getByteBuf(), handler);
   }

   public Future end() {
      Promise<Void> promise = this.stream.context.promise();
      this.write((ByteBuf)null, true, promise);
      return promise.future();
   }

   public void end(Handler handler) {
      this.end((ByteBuf)null, handler);
   }

   Future netSocket() {
      synchronized(this.conn) {
         if (this.netSocket == null) {
            this.status = HttpResponseStatus.OK;
            if (!this.checkSendHeaders(false)) {
               this.netSocket = this.stream.context.failedFuture("Response for CONNECT already sent");
            } else {
               HttpNetSocket ns = HttpNetSocket.netSocket(this.conn, this.stream.context, (ReadStream)this.stream.request, this);
               this.netSocket = Future.succeededFuture(ns);
            }
         }
      }

      return this.netSocket;
   }

   private void end(ByteBuf chunk, Handler handler) {
      this.write(chunk, true, handler);
   }

   void write(ByteBuf chunk, boolean end, Handler handler) {
      boolean invokeHandler = false;
      Handler<Void> bodyEndHandler;
      Handler<Void> endHandler;
      synchronized(this.conn) {
         if (this.ended) {
            throw new IllegalStateException("Response has already been written");
         }

         this.ended = end;
         boolean hasBody = false;
         if (chunk != null) {
            hasBody = true;
         } else {
            chunk = Unpooled.EMPTY_BUFFER;
         }

         if (end && !this.headWritten && this.needsContentLengthHeader()) {
            this.headers().set((CharSequence)HttpHeaderNames.CONTENT_LENGTH, (CharSequence)HttpUtils.positiveLongToString((long)chunk.readableBytes()));
         }

         boolean sent = this.checkSendHeaders(end && !hasBody && this.trailers == null, !hasBody);
         if (hasBody || !sent && end) {
            this.stream.writeData(chunk, end && this.trailers == null, handler);
         } else {
            invokeHandler = true;
         }

         if (end && this.trailers != null) {
            this.stream.writeHeaders(this.trailers, true, true, (Handler)null);
         }

         bodyEndHandler = this.bodyEndHandler;
         endHandler = this.endHandler;
      }

      if (end) {
         if (bodyEndHandler != null) {
            bodyEndHandler.handle((Object)null);
         }

         if (endHandler != null) {
            endHandler.handle((Object)null);
         }

         if (invokeHandler && handler != null) {
            handler.handle(Future.succeededFuture());
         }
      }

   }

   private boolean needsContentLengthHeader() {
      return this.stream.method != HttpMethod.HEAD && this.status != HttpResponseStatus.NOT_MODIFIED && !this.headers.contains(HttpHeaderNames.CONTENT_LENGTH);
   }

   private boolean checkSendHeaders(boolean end) {
      return this.checkSendHeaders(end, true);
   }

   private boolean checkSendHeaders(boolean end, boolean checkFlush) {
      if (!this.headWritten) {
         if (this.headersEndHandler != null) {
            this.headersEndHandler.handle((Object)null);
         }

         if (this.cookies != null) {
            this.setCookies();
         }

         this.prepareHeaders();
         this.headWritten = true;
         this.stream.writeHeaders(this.headers, end, checkFlush, (Handler)null);
         return true;
      } else {
         return false;
      }
   }

   private void prepareHeaders() {
      this.headers.status(this.status.codeAsText());
      if (this.contentEncoding != null && this.headers.get(HttpHeaderNames.CONTENT_ENCODING) == null) {
         this.headers.set(HttpHeaderNames.CONTENT_ENCODING, this.contentEncoding);
      }

      if (this.stream.method != HttpMethod.HEAD && this.status != HttpResponseStatus.NOT_MODIFIED) {
         if (this.status == HttpResponseStatus.RESET_CONTENT) {
            this.headers.remove(HttpHeaders.TRANSFER_ENCODING);
            this.headers.set(HttpHeaders.CONTENT_LENGTH, "0");
         } else if (this.status.codeClass() == HttpStatusClass.INFORMATIONAL || this.status == HttpResponseStatus.NO_CONTENT) {
            this.headers.remove(HttpHeaders.TRANSFER_ENCODING);
            this.headers.remove(HttpHeaders.CONTENT_LENGTH);
         }
      } else {
         this.headers.remove(HttpHeaders.TRANSFER_ENCODING);
      }

   }

   private void setCookies() {
      for(ServerCookie cookie : this.cookies) {
         if (cookie.isChanged()) {
            this.headers.add(HttpHeaders.SET_COOKIE, cookie.encode());
         }
      }

   }

   public HttpServerResponse writeCustomFrame(int type, int flags, Buffer payload) {
      synchronized(this.conn) {
         this.checkValid();
         this.checkSendHeaders(false);
         this.stream.writeFrame(type, flags, payload.getByteBuf());
         return this;
      }
   }

   private void checkValid() {
      if (this.ended) {
         throw new IllegalStateException("Response has already been written");
      }
   }

   void handlerWritabilityChanged(boolean writable) {
      Handler<Void> handler;
      synchronized(this.conn) {
         handler = this.drainHandler;
         if (this.ended || !writable || handler == null) {
            return;
         }
      }

      handler.handle((Object)null);
   }

   public boolean writeQueueFull() {
      synchronized(this.conn) {
         this.checkValid();
         return this.stream.isNotWritable();
      }
   }

   public HttpServerResponse setWriteQueueMaxSize(int maxSize) {
      synchronized(this.conn) {
         this.checkValid();
         return this;
      }
   }

   public HttpServerResponse drainHandler(Handler handler) {
      synchronized(this.conn) {
         if (handler != null) {
            this.checkValid();
         }

         this.drainHandler = handler;
         return this;
      }
   }

   public Future sendFile(String filename, long offset, long length) {
      Promise<Void> promise = this.stream.context.promise();
      this.sendFile(filename, offset, length, promise);
      return promise.future();
   }

   public HttpServerResponse sendFile(String filename, long offset, long length, Handler resultHandler) {
      if (offset < 0L) {
         resultHandler.handle(Future.failedFuture("offset : " + offset + " (expected: >= 0)"));
         return this;
      } else if (length < 0L) {
         resultHandler.handle(Future.failedFuture("length : " + length + " (expected: >= 0)"));
         return this;
      } else {
         synchronized(this.conn) {
            this.checkValid();
         }

         Handler<AsyncResult<Void>> h;
         if (resultHandler != null) {
            Context resultCtx = this.stream.vertx.getOrCreateContext();
            h = (ar) -> resultCtx.runOnContext((v) -> resultHandler.handle(ar));
         } else {
            h = (ar) -> {
            };
         }

         HttpUtils.resolveFile(this.stream.vertx, filename, offset, length, (ar) -> {
            if (ar.succeeded()) {
               AsyncFile file = (AsyncFile)ar.result();
               long fileLength = file.getReadLength();
               long contentLength = Math.min(length, fileLength);
               if (this.headers.get(HttpHeaderNames.CONTENT_LENGTH) == null) {
                  this.putHeader((CharSequence)HttpHeaderNames.CONTENT_LENGTH, (CharSequence)HttpUtils.positiveLongToString(contentLength));
               }

               if (this.headers.get(HttpHeaderNames.CONTENT_TYPE) == null) {
                  String contentType = MimeMapping.getMimeTypeForFilename(filename);
                  if (contentType != null) {
                     this.putHeader((CharSequence)HttpHeaderNames.CONTENT_TYPE, (CharSequence)contentType);
                  }
               }

               this.checkSendHeaders(false);
               file.pipeTo(this, (ar1) -> file.close((ar2) -> {
                     Throwable failure = ar1.failed() ? ar1.cause() : (ar2.failed() ? ar2.cause() : null);
                     if (failure == null) {
                        h.handle(ar1);
                     } else {
                        h.handle(Future.failedFuture(failure));
                     }

                  }));
            } else {
               h.handle(ar.mapEmpty());
            }

         });
         return this;
      }
   }

   public void close() {
      this.conn.close();
   }

   public boolean ended() {
      synchronized(this.conn) {
         return this.ended;
      }
   }

   public synchronized boolean closed() {
      synchronized(this.conn) {
         return this.closed;
      }
   }

   public boolean headWritten() {
      synchronized(this.conn) {
         return this.headWritten;
      }
   }

   public HttpServerResponse headersEndHandler(@Nullable Handler handler) {
      synchronized(this.conn) {
         this.headersEndHandler = handler;
         return this;
      }
   }

   public HttpServerResponse bodyEndHandler(@Nullable Handler handler) {
      synchronized(this.conn) {
         this.bodyEndHandler = handler;
         return this;
      }
   }

   public long bytesWritten() {
      return this.stream.bytesWritten();
   }

   public int streamId() {
      return this.stream.id();
   }

   public boolean reset(long code) {
      this.stream.writeReset(code);
      return true;
   }

   public Future push(HttpMethod method, HostAndPort authority, String path, MultiMap headers) {
      if (this.push) {
         throw new IllegalStateException("A push response cannot promise another push");
      } else {
         if (authority == null) {
            authority = this.stream.authority;
         }

         synchronized(this.conn) {
            this.checkValid();
         }

         Promise<HttpServerResponse> promise = this.stream.context.promise();
         this.conn.sendPush(this.stream.id(), authority, method, headers, path, this.stream.priority(), promise);
         return promise.future();
      }
   }

   public Future push(HttpMethod method, String authority, String path, MultiMap headers) {
      if (this.push) {
         throw new IllegalStateException("A push response cannot promise another push");
      } else {
         HostAndPort hostAndPort = null;
         if (authority != null) {
            hostAndPort = HostAndPort.parseAuthority(authority, -1);
         }

         if (hostAndPort == null) {
            hostAndPort = this.stream.authority;
         }

         synchronized(this.conn) {
            this.checkValid();
         }

         Promise<HttpServerResponse> promise = this.stream.context.promise();
         this.conn.sendPush(this.stream.id(), hostAndPort, method, headers, path, this.stream.priority(), promise);
         return promise.future();
      }
   }

   public HttpServerResponse setStreamPriority(StreamPriority priority) {
      this.stream.updatePriority(priority);
      return this;
   }

   CookieJar cookies() {
      synchronized(this.conn) {
         if (this.cookies == null) {
            CharSequence cookieHeader = this.stream.headers != null ? (CharSequence)this.stream.headers.get(HttpHeaders.COOKIE) : null;
            if (cookieHeader == null) {
               this.cookies = new CookieJar();
            } else {
               this.cookies = new CookieJar(cookieHeader);
            }
         }
      }

      return this.cookies;
   }

   public HttpServerResponse addCookie(Cookie cookie) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         this.cookies().add((ServerCookie)cookie);
         return this;
      }
   }

   public @Nullable Cookie removeCookie(String name, boolean invalidate) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         return this.cookies().removeOrInvalidate(name, invalidate);
      }
   }

   public @Nullable Cookie removeCookie(String name, String domain, String path, boolean invalidate) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         return this.cookies().removeOrInvalidate(name, domain, path, invalidate);
      }
   }

   public @Nullable Set removeCookies(String name, boolean invalidate) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         return this.cookies().removeOrInvalidateAll(name, invalidate);
      }
   }
}
