package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpClosedException;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.impl.headers.HeadersMultiMap;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.HostAndPort;
import io.vertx.core.net.NetSocket;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.observability.HttpResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Set;

public class Http1xServerResponse implements HttpServerResponse, HttpResponse {
   private static final Buffer EMPTY_BUFFER;
   private static final Logger log;
   private static final String RESPONSE_WRITTEN = "Response has already been written";
   private final VertxInternal vertx;
   private final HttpRequest request;
   private final Http1xServerConnection conn;
   private final ContextInternal context;
   private HttpResponseStatus status;
   private final HttpVersion version;
   private final boolean keepAlive;
   private final boolean head;
   private final Object requestMetric;
   private boolean headWritten;
   private boolean written;
   private Handler drainHandler;
   private Handler exceptionHandler;
   private Handler closeHandler;
   private Handler endHandler;
   private Handler headersEndHandler;
   private Handler bodyEndHandler;
   private boolean writable;
   private boolean closed;
   private final HeadersMultiMap headers;
   private CookieJar cookies;
   private MultiMap trailers;
   private HttpHeaders trailingHeaders;
   private String statusMessage;
   private long bytesWritten;
   private Future netSocket;

   Http1xServerResponse(VertxInternal vertx, ContextInternal context, Http1xServerConnection conn, HttpRequest request, Object requestMetric, boolean writable, boolean keepAlive) {
      this.trailingHeaders = EmptyHttpHeaders.INSTANCE;
      this.vertx = vertx;
      this.conn = conn;
      this.context = context;
      this.version = request.protocolVersion();
      this.headers = HeadersMultiMap.httpHeaders();
      this.request = request;
      this.status = HttpResponseStatus.OK;
      this.requestMetric = requestMetric;
      this.writable = writable;
      this.keepAlive = keepAlive;
      this.head = request.method() == HttpMethod.HEAD;
   }

   public MultiMap headers() {
      return this.headers;
   }

   public MultiMap trailers() {
      if (this.trailers == null) {
         HeadersMultiMap v = HeadersMultiMap.httpHeaders();
         this.trailers = v;
         this.trailingHeaders = v;
      }

      return this.trailers;
   }

   public int statusCode() {
      return this.status.code();
   }

   public int getStatusCode() {
      return this.status.code();
   }

   public HttpServerResponse setStatusCode(int statusCode) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         this.status = this.statusMessage != null ? new HttpResponseStatus(statusCode, this.statusMessage) : HttpResponseStatus.valueOf(statusCode);
         return this;
      }
   }

   public String getStatusMessage() {
      return this.status.reasonPhrase();
   }

   public HttpServerResponse setStatusMessage(String statusMessage) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         this.statusMessage = statusMessage;
         this.status = new HttpResponseStatus(this.status.code(), statusMessage);
         return this;
      }
   }

   public Http1xServerResponse setChunked(boolean chunked) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         if (this.version != HttpVersion.HTTP_1_0) {
            this.headers.set((CharSequence)io.vertx.core.http.HttpHeaders.TRANSFER_ENCODING, (CharSequence)(chunked ? "chunked" : null));
         }

         return this;
      }
   }

   public boolean isChunked() {
      synchronized(this.conn) {
         return this.headers.contains(io.vertx.core.http.HttpHeaders.TRANSFER_ENCODING, io.vertx.core.http.HttpHeaders.CHUNKED, true);
      }
   }

   public Http1xServerResponse putHeader(String key, String value) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         this.headers.set(key, value);
         return this;
      }
   }

   public Http1xServerResponse putHeader(String key, Iterable values) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         this.headers.set(key, values);
         return this;
      }
   }

   public Http1xServerResponse putTrailer(String key, String value) {
      synchronized(this.conn) {
         this.checkValid();
         this.trailers().set(key, value);
         return this;
      }
   }

   public Http1xServerResponse putTrailer(String key, Iterable values) {
      synchronized(this.conn) {
         this.checkValid();
         this.trailers().set(key, values);
         return this;
      }
   }

   public HttpServerResponse putHeader(CharSequence name, CharSequence value) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         this.headers.set(name, value);
         return this;
      }
   }

   public HttpServerResponse putHeader(CharSequence name, Iterable values) {
      synchronized(this.conn) {
         this.checkHeadWritten();
         this.headers.set(name, values);
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

   public HttpServerResponse putTrailer(CharSequence name, Iterable value) {
      synchronized(this.conn) {
         this.checkValid();
         this.trailers().set(name, value);
         return this;
      }
   }

   public HttpServerResponse setWriteQueueMaxSize(int size) {
      synchronized(this.conn) {
         this.checkValid();
         this.conn.doSetWriteQueueMaxSize(size);
         return this;
      }
   }

   public boolean writeQueueFull() {
      synchronized(this.conn) {
         this.checkValid();
         return !this.writable;
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

   public HttpServerResponse exceptionHandler(Handler handler) {
      synchronized(this.conn) {
         if (handler != null) {
            this.checkValid();
         }

         this.exceptionHandler = handler;
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

   public Future write(Buffer chunk) {
      PromiseInternal<Void> promise = this.context.promise();
      this.write(chunk.getByteBuf(), promise);
      return promise.future();
   }

   public void write(Buffer chunk, Handler handler) {
      this.write(chunk.getByteBuf(), handler == null ? null : this.context.promise(handler));
   }

   public Future write(String chunk, String enc) {
      PromiseInternal<Void> promise = this.context.promise();
      this.write(Buffer.buffer(chunk, enc).getByteBuf(), promise);
      return promise.future();
   }

   public void write(String chunk, String enc, Handler handler) {
      this.write(Buffer.buffer(chunk, enc).getByteBuf(), handler == null ? null : this.context.promise(handler));
   }

   public Future write(String chunk) {
      PromiseInternal<Void> promise = this.context.promise();
      this.write(Buffer.buffer(chunk).getByteBuf(), promise);
      return promise.future();
   }

   public void write(String chunk, Handler handler) {
      this.write(Buffer.buffer(chunk).getByteBuf(), handler == null ? null : this.context.promise(handler));
   }

   public HttpServerResponse writeContinue() {
      this.conn.write100Continue();
      return this;
   }

   public Future writeEarlyHints(MultiMap headers) {
      PromiseInternal<Void> promise = this.context.promise();
      this.writeEarlyHints(headers, promise);
      return promise.future();
   }

   public void writeEarlyHints(MultiMap headers, Handler handler) {
      HeadersMultiMap headersMultiMap;
      if (headers instanceof HeadersMultiMap) {
         headersMultiMap = (HeadersMultiMap)headers;
      } else {
         headersMultiMap = HeadersMultiMap.httpHeaders();
         headersMultiMap.addAll(headers);
      }

      synchronized(this.conn) {
         this.checkHeadWritten();
      }

      this.conn.write103EarlyHints(headersMultiMap, this.context.promise(handler));
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
      this.end(Buffer.buffer(chunk, enc), handler);
   }

   public Future end(Buffer chunk) {
      PromiseInternal<Void> promise = this.context.promise();
      this.end(chunk, promise);
      return promise.future();
   }

   public void end(Buffer chunk, Handler handler) {
      this.end(chunk, handler == null ? null : this.context.promise(handler));
   }

   private void end(Buffer chunk, PromiseInternal listener) {
      synchronized(this.conn) {
         if (this.written) {
            throw new IllegalStateException("Response has already been written");
         } else {
            this.written = true;
            ByteBuf data = chunk.getByteBuf();
            this.bytesWritten += (long)data.readableBytes();
            HttpObject msg;
            if (!this.headWritten) {
               this.prepareHeaders(this.bytesWritten);
               msg = new AssembledFullHttpResponse(this.head, this.version, this.status, this.headers, data, this.trailingHeaders);
            } else {
               msg = new AssembledLastHttpContent(data, this.trailingHeaders);
            }

            this.conn.writeToChannel(msg, listener);
            this.conn.responseComplete();
            if (this.bodyEndHandler != null) {
               this.bodyEndHandler.handle((Object)null);
            }

            if (!this.closed && this.endHandler != null) {
               this.endHandler.handle((Object)null);
            }

            if (!this.keepAlive) {
               this.closed = true;
            }

         }
      }
   }

   void completeHandshake() {
      if (this.conn.metrics != null) {
         this.conn.metrics.responseBegin(this.requestMetric, this);
      }

      this.setStatusCode(101);
      synchronized(this.conn) {
         this.headWritten = true;
         this.written = true;
      }

      this.conn.responseComplete();
   }

   public void close() {
      synchronized(this.conn) {
         if (!this.closed) {
            if (this.headWritten) {
               this.closeConnAfterWrite();
            } else {
               this.conn.close();
            }

            this.closed = true;
         }

      }
   }

   public Future end() {
      return this.end(EMPTY_BUFFER);
   }

   public void end(Handler handler) {
      this.end(EMPTY_BUFFER, handler);
   }

   public Future sendFile(String filename, long offset, long length) {
      Promise<Void> promise = this.context.promise();
      this.sendFile(filename, offset, length, promise);
      return promise.future();
   }

   public HttpServerResponse sendFile(String filename, long start, long end, Handler resultHandler) {
      this.doSendFile(filename, start, end, resultHandler);
      return this;
   }

   public boolean ended() {
      synchronized(this.conn) {
         return this.written;
      }
   }

   public boolean closed() {
      synchronized(this.conn) {
         return this.closed;
      }
   }

   public boolean headWritten() {
      synchronized(this.conn) {
         return this.headWritten;
      }
   }

   public long bytesWritten() {
      synchronized(this.conn) {
         return this.bytesWritten;
      }
   }

   public HttpServerResponse headersEndHandler(Handler handler) {
      synchronized(this.conn) {
         this.headersEndHandler = handler;
         return this;
      }
   }

   public HttpServerResponse bodyEndHandler(Handler handler) {
      synchronized(this.conn) {
         this.bodyEndHandler = handler;
         return this;
      }
   }

   private void doSendFile(String filename, long offset, long length, Handler resultHandler) {
      ContextInternal ctx = this.vertx.getOrCreateContext();
      if (offset < 0L) {
         ctx.runOnContext((v) -> resultHandler.handle(Future.failedFuture("offset : " + offset + " (expected: >= 0)")));
      } else if (length < 0L) {
         ctx.runOnContext((v) -> resultHandler.handle(Future.failedFuture("length : " + length + " (expected: >= 0)")));
      } else {
         synchronized(this.conn) {
            this.checkValid();
            if (this.headWritten) {
               throw new IllegalStateException("Head already written");
            } else {
               File file = this.vertx.resolveFile(filename);
               if (!file.exists()) {
                  if (resultHandler != null) {
                     ctx.runOnContext((v) -> resultHandler.handle(Future.failedFuture((Throwable)(new FileNotFoundException()))));
                  } else {
                     log.error("File not found: " + filename);
                  }

               } else {
                  long contentLength = Math.min(length, file.length() - offset);
                  if (contentLength < 0L) {
                     if (resultHandler != null) {
                        Exception exception = new IllegalArgumentException("offset : " + offset + " is larger than the requested file length : " + file.length());
                        ctx.runOnContext((v) -> resultHandler.handle(Future.failedFuture((Throwable)exception)));
                     } else {
                        log.error("Invalid offset: " + offset);
                     }

                  } else {
                     this.bytesWritten = contentLength;
                     if (!this.headers.contains(io.vertx.core.http.HttpHeaders.CONTENT_TYPE)) {
                        String contentType = MimeMapping.getMimeTypeForFilename(filename);
                        if (contentType != null) {
                           this.headers.set((CharSequence)io.vertx.core.http.HttpHeaders.CONTENT_TYPE, (CharSequence)contentType);
                        }
                     }

                     this.prepareHeaders(this.bytesWritten);
                     RandomAccessFile raf = null;

                     ChannelFuture channelFuture;
                     try {
                        raf = new RandomAccessFile(file, "r");
                        this.conn.writeToChannel(new AssembledHttpResponse(this.head, this.version, this.status, this.headers));
                        channelFuture = this.conn.sendFile(raf, Math.min(offset, file.length()), contentLength);
                     } catch (IOException e) {
                        try {
                           if (raf != null) {
                              raf.close();
                           }
                        } catch (IOException var17) {
                        }

                        if (resultHandler != null) {
                           ctx.runOnContext((v) -> resultHandler.handle(Future.failedFuture((Throwable)e)));
                        } else {
                           log.error("Failed to send file", e);
                        }

                        return;
                     }

                     this.written = true;
                     channelFuture.addListener((future) -> {
                        if (future.isSuccess()) {
                           ChannelPromise pr = this.conn.channelHandlerContext().newPromise();
                           this.conn.writeToChannel(LastHttpContent.EMPTY_LAST_CONTENT, pr);
                        }

                        if (resultHandler != null) {
                           AsyncResult<Void> res;
                           if (future.isSuccess()) {
                              res = Future.succeededFuture();
                           } else {
                              res = Future.failedFuture(future.cause());
                           }

                           ctx.emit((Object)null, (v) -> resultHandler.handle(res));
                        }

                        Handler<Void> handler;
                        synchronized(this.conn) {
                           handler = this.bodyEndHandler;
                        }

                        if (handler != null) {
                           this.context.emit(handler);
                        }

                        this.conn.responseComplete();
                        Handler<Void> end;
                        synchronized(this.conn) {
                           end = !this.closed ? this.endHandler : null;
                        }

                        if (null != end) {
                           this.context.emit(end);
                        }

                     });
                  }
               }
            }
         }
      }
   }

   private void closeConnAfterWrite() {
      ChannelPromise channelFuture = this.conn.channelFuture();
      this.conn.writeToChannel(Unpooled.EMPTY_BUFFER, channelFuture);
      channelFuture.addListener((fut) -> this.conn.close());
   }

   void handleWritabilityChanged(boolean writable) {
      Handler<Void> handler;
      synchronized(this.conn) {
         boolean skip = this.writable && !writable;
         this.writable = writable;
         handler = this.drainHandler;
         if (handler == null || skip) {
            return;
         }
      }

      this.context.dispatch((Object)null, handler);
   }

   void handleException(Throwable t) {
      if (t instanceof HttpClosedException) {
         this.handleClosed();
      } else {
         Handler<Throwable> handler;
         synchronized(this.conn) {
            handler = this.exceptionHandler;
            if (handler == null) {
               return;
            }
         }

         this.context.dispatch(t, handler);
      }

   }

   private void handleClosed() {
      Handler<Void> closedHandler;
      Handler<Void> endHandler;
      Handler<Throwable> exceptionHandler;
      synchronized(this.conn) {
         if (this.closed) {
            return;
         }

         this.closed = true;
         exceptionHandler = this.written ? null : this.exceptionHandler;
         endHandler = this.written ? null : this.endHandler;
         closedHandler = this.closeHandler;
      }

      if (exceptionHandler != null) {
         this.context.dispatch(HttpUtils.CONNECTION_CLOSED_EXCEPTION, exceptionHandler);
      }

      if (endHandler != null) {
         this.context.dispatch((Object)null, endHandler);
      }

      if (closedHandler != null) {
         this.context.dispatch((Object)null, closedHandler);
      }

   }

   private void checkValid() {
      if (this.written) {
         throw new IllegalStateException("Response has already been written");
      }
   }

   private void checkHeadWritten() {
      if (this.headWritten) {
         throw new IllegalStateException("Response head already sent");
      }
   }

   private void prepareHeaders(long contentLength) {
      if (this.version == HttpVersion.HTTP_1_0 && this.keepAlive) {
         this.headers.set(io.vertx.core.http.HttpHeaders.CONNECTION, io.vertx.core.http.HttpHeaders.KEEP_ALIVE);
      } else if (this.version == HttpVersion.HTTP_1_1 && !this.keepAlive) {
         this.headers.set(io.vertx.core.http.HttpHeaders.CONNECTION, io.vertx.core.http.HttpHeaders.CLOSE);
      }

      if (!this.head && this.status != HttpResponseStatus.NOT_MODIFIED) {
         if (contentLength >= 0L && !this.headers.contains(io.vertx.core.http.HttpHeaders.CONTENT_LENGTH) && !this.headers.contains(io.vertx.core.http.HttpHeaders.TRANSFER_ENCODING)) {
            this.headers.set((CharSequence)io.vertx.core.http.HttpHeaders.CONTENT_LENGTH, (CharSequence)HttpUtils.positiveLongToString(contentLength));
         }
      } else {
         this.headers.remove(io.vertx.core.http.HttpHeaders.TRANSFER_ENCODING);
      }

      if (this.headersEndHandler != null) {
         this.headersEndHandler.handle((Object)null);
      }

      if (this.cookies != null) {
         this.setCookies();
      }

      if (Metrics.METRICS_ENABLED) {
         this.reportResponseBegin();
      }

      this.headWritten = true;
   }

   private void setCookies() {
      for(ServerCookie cookie : this.cookies) {
         if (cookie.isChanged()) {
            this.headers.add((CharSequence)io.vertx.core.http.HttpHeaders.SET_COOKIE, (CharSequence)cookie.encode());
         }
      }

   }

   private void reportResponseBegin() {
      if (this.conn.metrics != null) {
         this.conn.metrics.responseBegin(this.requestMetric, this);
      }

   }

   private Http1xServerResponse write(ByteBuf chunk, PromiseInternal promise) {
      synchronized(this.conn) {
         if (this.written) {
            throw new IllegalStateException("Response has already been written");
         } else if (!this.headWritten && !this.headers.contains(io.vertx.core.http.HttpHeaders.TRANSFER_ENCODING) && !this.headers.contains(io.vertx.core.http.HttpHeaders.CONTENT_LENGTH) && this.version != HttpVersion.HTTP_1_0) {
            throw new IllegalStateException("You must set the Content-Length header to be the total size of the message body BEFORE sending any data if you are not using HTTP chunked encoding.");
         } else {
            this.bytesWritten += (long)chunk.readableBytes();
            HttpObject msg;
            if (!this.headWritten) {
               this.prepareHeaders(-1L);
               msg = new AssembledHttpResponse(this.head, this.version, this.status, this.headers, chunk);
            } else {
               msg = new DefaultHttpContent(chunk);
            }

            this.conn.writeToChannel(msg, promise);
            return this;
         }
      }
   }

   Future netSocket(io.vertx.core.http.HttpMethod requestMethod, MultiMap requestHeaders) {
      synchronized(this.conn) {
         if (this.netSocket == null) {
            if (this.headWritten) {
               return this.context.failedFuture("Response already sent");
            }

            if (!HttpUtils.isConnectOrUpgrade(requestMethod, requestHeaders)) {
               return this.context.failedFuture("HTTP method must be CONNECT or an HTTP upgrade to upgrade the connection to a TCP socket");
            }

            this.status = requestMethod == io.vertx.core.http.HttpMethod.CONNECT ? HttpResponseStatus.OK : HttpResponseStatus.SWITCHING_PROTOCOLS;
            this.prepareHeaders(-1L);
            PromiseInternal<Void> upgradePromise = this.context.promise();
            this.conn.writeToChannel(new AssembledHttpResponse(this.head, this.version, this.status, this.headers), upgradePromise);
            this.written = true;
            Promise<NetSocket> promise = this.context.promise();
            this.netSocket = promise.future();
            this.conn.netSocket(promise);
         }
      }

      return this.netSocket;
   }

   public int streamId() {
      return -1;
   }

   public boolean reset(long code) {
      synchronized(this.conn) {
         if (this.written) {
            return false;
         }
      }

      this.close();
      return true;
   }

   public Future push(io.vertx.core.http.HttpMethod method, HostAndPort authority, String path, MultiMap headers) {
      return this.context.failedFuture("HTTP/1 does not support response push");
   }

   public Future push(io.vertx.core.http.HttpMethod method, String host, String path, MultiMap headers) {
      return this.context.failedFuture("HTTP/1 does not support response push");
   }

   public HttpServerResponse writeCustomFrame(int type, int flags, Buffer payload) {
      return this;
   }

   CookieJar cookies() {
      synchronized(this.conn) {
         if (this.cookies == null) {
            String cookieHeader = this.request.headers().get(io.vertx.core.http.HttpHeaders.COOKIE);
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

   static {
      EMPTY_BUFFER = Buffer.buffer(Unpooled.EMPTY_BUFFER);
      log = LoggerFactory.getLogger(Http1xServerResponse.class);
   }
}
