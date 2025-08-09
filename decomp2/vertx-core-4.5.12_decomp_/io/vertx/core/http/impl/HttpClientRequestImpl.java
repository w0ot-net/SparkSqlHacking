package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.impl.headers.HeadersMultiMap;
import io.vertx.core.impl.Arguments;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.net.SocketAddress;
import java.util.Objects;
import java.util.function.Function;

public class HttpClientRequestImpl extends HttpClientRequestBase implements HttpClientRequest {
   static final Logger log = LoggerFactory.getLogger(HttpClientRequestImpl.class);
   private final Promise endPromise;
   private final Future endFuture;
   private boolean chunked;
   private Handler continueHandler;
   private Handler earlyHintsHandler;
   private Handler drainHandler;
   private Handler exceptionHandler;
   private Function redirectHandler;
   private boolean ended;
   private boolean followRedirects;
   private int maxRedirects;
   private int numberOfRedirections;
   private HeadersMultiMap headers;
   private StreamPriority priority;
   private boolean headWritten;
   private boolean isConnect;
   private String traceOperation;

   HttpClientRequestImpl(HttpClientStream stream, PromiseInternal responsePromise) {
      this(stream, responsePromise, HttpMethod.GET, (SocketAddress)null, (String)null, 0, "/", (String)null);
   }

   HttpClientRequestImpl(HttpClientStream stream, PromiseInternal responsePromise, HttpMethod method, SocketAddress server, String host, int port, String requestURI, String traceOperation) {
      super(stream, responsePromise, method, server, host, port, requestURI);
      this.chunked = false;
      this.endPromise = this.context.promise();
      this.endFuture = this.endPromise.future();
      this.priority = HttpUtils.DEFAULT_STREAM_PRIORITY;
      this.numberOfRedirections = 0;
      this.traceOperation = traceOperation;
      stream.continueHandler(this::handleContinue);
      stream.earlyHintsHandler(this::handleEarlyHints);
      stream.drainHandler(this::handleDrained);
      stream.exceptionHandler(this::handleException);
   }

   void handleException(Throwable t) {
      t = this.mapException(t);
      super.handleException(t);
      if (this.endPromise.tryFail(t)) {
         Handler<Throwable> handler = this.exceptionHandler();
         if (handler != null) {
            this.context.emit(t, handler);
         } else if (log.isDebugEnabled()) {
            log.error(t.getMessage(), t);
         } else {
            log.error(t.getMessage());
         }
      }

   }

   public synchronized HttpClientRequest setFollowRedirects(boolean followRedirects) {
      this.checkEnded();
      this.followRedirects = followRedirects;
      return this;
   }

   public synchronized boolean isFollowRedirects() {
      return this.followRedirects;
   }

   public synchronized HttpClientRequest setMaxRedirects(int maxRedirects) {
      Arguments.require(maxRedirects >= 0, "Max redirects must be >= 0");
      this.checkEnded();
      this.maxRedirects = maxRedirects;
      return this;
   }

   public synchronized int getMaxRedirects() {
      return this.maxRedirects;
   }

   public int numberOfRedirections() {
      return this.numberOfRedirections;
   }

   public synchronized HttpClientRequestImpl setChunked(boolean chunked) {
      this.checkEnded();
      if (this.headWritten) {
         throw new IllegalStateException("Cannot set chunked after data has been written on request");
      } else {
         if (this.version() != HttpVersion.HTTP_1_0) {
            this.chunked = chunked;
         }

         return this;
      }
   }

   public synchronized boolean isChunked() {
      return this.chunked;
   }

   public synchronized MultiMap headers() {
      if (this.headers == null) {
         this.headers = HeadersMultiMap.httpHeaders();
      }

      return this.headers;
   }

   public synchronized HttpClientRequest putHeader(String name, String value) {
      this.checkEnded();
      this.headers().set(name, value);
      return this;
   }

   public synchronized HttpClientRequest putHeader(String name, Iterable values) {
      this.checkEnded();
      this.headers().set(name, values);
      return this;
   }

   public synchronized HttpClientRequest setWriteQueueMaxSize(int maxSize) {
      this.checkEnded();
      this.stream.doSetWriteQueueMaxSize(maxSize);
      return this;
   }

   public boolean writeQueueFull() {
      synchronized(this) {
         this.checkEnded();
      }

      return this.stream.isNotWritable();
   }

   public HttpVersion version() {
      return this.stream.version();
   }

   private synchronized Handler exceptionHandler() {
      return this.exceptionHandler;
   }

   public synchronized HttpClientRequest exceptionHandler(Handler handler) {
      if (handler != null) {
         this.checkEnded();
         this.exceptionHandler = handler;
      } else {
         this.exceptionHandler = null;
      }

      return this;
   }

   public synchronized HttpClientRequest drainHandler(Handler handler) {
      if (handler != null) {
         this.checkEnded();
      }

      this.drainHandler = handler;
      return this;
   }

   public synchronized HttpClientRequest continueHandler(Handler handler) {
      if (handler != null) {
         this.checkEnded();
      }

      this.continueHandler = handler;
      return this;
   }

   public synchronized HttpClientRequest earlyHintsHandler(@Nullable Handler handler) {
      if (handler != null) {
         this.checkEnded();
      }

      this.earlyHintsHandler = handler;
      return this;
   }

   public synchronized HttpClientRequest redirectHandler(@Nullable Function handler) {
      if (handler != null) {
         this.checkEnded();
      }

      this.redirectHandler = handler;
      return this;
   }

   public Future sendHead() {
      Promise<Void> promise = this.context.promise();
      this.sendHead(promise);
      return promise.future();
   }

   public HttpClientRequest sendHead(Handler headersHandler) {
      this.checkEnded();
      this.doWrite((ByteBuf)null, false, false, headersHandler);
      return this;
   }

   public Future connect() {
      this.doWrite((ByteBuf)null, false, true, (ar) -> {
      });
      return this.response();
   }

   public void connect(Handler handler) {
      Future<HttpClientResponse> fut = this.connect();
      if (handler != null) {
         fut.onComplete(handler);
      }

   }

   public synchronized HttpClientRequest putHeader(CharSequence name, CharSequence value) {
      this.checkEnded();
      this.headers().set(name, value);
      return this;
   }

   public synchronized HttpClientRequest putHeader(CharSequence name, Iterable values) {
      this.checkEnded();
      this.headers().set(name, values);
      return this;
   }

   public synchronized HttpClientRequest traceOperation(String op) {
      this.checkEnded();
      this.traceOperation = op;
      return this;
   }

   public String traceOperation() {
      return this.traceOperation;
   }

   private void tryComplete() {
      this.endPromise.tryComplete();
   }

   public synchronized HttpConnection connection() {
      return this.stream.connection();
   }

   public HttpClientRequest writeCustomFrame(int type, int flags, Buffer payload) {
      synchronized(this) {
         this.checkEnded();
      }

      this.stream.writeFrame(type, flags, payload.getByteBuf());
      return this;
   }

   private void handleDrained(Void v) {
      Handler<Void> handler;
      synchronized(this) {
         handler = this.drainHandler;
         if (handler == null || this.endFuture.isComplete()) {
            return;
         }
      }

      this.context.dispatch(handler);
   }

   private void handleNextRequest(HttpClientRequest next, Handler handler, long timeoutMs) {
      next.response(handler);
      next.exceptionHandler(this.exceptionHandler());
      this.exceptionHandler((Handler)null);
      next.pushHandler(this.pushHandler());
      next.setFollowRedirects(true);
      next.setMaxRedirects(this.maxRedirects);
      ((HttpClientRequestImpl)next).numberOfRedirections = this.numberOfRedirections + 1;
      this.endFuture.onComplete((ar) -> {
         if (ar.succeeded()) {
            if (timeoutMs > 0L) {
               next.setTimeout(timeoutMs);
            }

            next.end();
         } else {
            next.reset(0L);
         }

      });
   }

   private void handleContinue(Void v) {
      Handler<Void> handler;
      synchronized(this) {
         handler = this.continueHandler;
      }

      if (handler != null) {
         handler.handle((Object)null);
      }

   }

   private void handleEarlyHints(MultiMap headers) {
      Handler<MultiMap> handler;
      synchronized(this) {
         handler = this.earlyHintsHandler;
      }

      if (handler != null) {
         handler.handle(headers);
      }

   }

   void handleResponse(Promise promise, HttpClientResponse resp, long timeoutMs) {
      int statusCode = resp.statusCode();
      if (this.followRedirects && this.numberOfRedirections < this.maxRedirects && statusCode >= 300 && statusCode < 400) {
         Function<HttpClientResponse, Future<HttpClientRequest>> handler = this.redirectHandler;
         if (handler != null) {
            Future<HttpClientRequest> next = (Future)handler.apply(resp);
            if (next != null) {
               resp.end().compose((v) -> next, (err) -> next).onComplete((ar1) -> {
                  if (ar1.succeeded()) {
                     this.handleNextRequest((HttpClientRequest)ar1.result(), promise, timeoutMs);
                  } else {
                     this.fail(ar1.cause());
                  }

               });
               return;
            }
         }
      }

      promise.complete(resp);
   }

   public Future end(String chunk) {
      Promise<Void> promise = this.context.promise();
      this.end((String)chunk, (Handler)promise);
      return promise.future();
   }

   public void end(String chunk, Handler handler) {
      this.end(Buffer.buffer(chunk), handler);
   }

   public Future end(String chunk, String enc) {
      Promise<Void> promise = this.context.promise();
      this.end(chunk, enc, promise);
      return promise.future();
   }

   public void end(String chunk, String enc, Handler handler) {
      Objects.requireNonNull(enc, "no null encoding accepted");
      this.end(Buffer.buffer(chunk, enc), handler);
   }

   public Future end(Buffer chunk) {
      Promise<Void> promise = this.context.promise();
      this.write(chunk.getByteBuf(), true, promise);
      return promise.future();
   }

   public void end(Buffer chunk, Handler handler) {
      this.write(chunk.getByteBuf(), true, handler);
   }

   public Future end() {
      Promise<Void> promise = this.context.promise();
      this.end((Handler)promise);
      return promise.future();
   }

   public void end(Handler handler) {
      this.write((ByteBuf)null, true, handler);
   }

   public Future write(Buffer chunk) {
      Promise<Void> promise = this.context.promise();
      this.write((Buffer)chunk, (Handler)promise);
      return promise.future();
   }

   public void write(Buffer chunk, Handler handler) {
      ByteBuf buf = chunk.getByteBuf();
      this.write(buf, false, handler);
   }

   public Future write(String chunk) {
      Promise<Void> promise = this.context.promise();
      this.write((String)chunk, (Handler)promise);
      return promise.future();
   }

   public void write(String chunk, Handler handler) {
      this.write(Buffer.buffer(chunk).getByteBuf(), false, handler);
   }

   public Future write(String chunk, String enc) {
      Promise<Void> promise = this.context.promise();
      this.write(chunk, enc, promise);
      return promise.future();
   }

   public void write(String chunk, String enc, Handler handler) {
      Objects.requireNonNull(enc, "no null encoding accepted");
      this.write(Buffer.buffer(chunk, enc).getByteBuf(), false, handler);
   }

   private boolean requiresContentLength() {
      return !this.chunked && (this.headers == null || !this.headers.contains(HttpHeaders.CONTENT_LENGTH)) && !this.isConnect;
   }

   private void write(ByteBuf buff, boolean end, Handler completionHandler) {
      if (end) {
         if (buff != null && this.requiresContentLength()) {
            this.headers().set((CharSequence)HttpHeaders.CONTENT_LENGTH, (CharSequence)HttpUtils.positiveLongToString((long)buff.readableBytes()));
         }
      } else if (this.requiresContentLength()) {
         throw new IllegalStateException("You must set the Content-Length header to be the total size of the message body BEFORE sending any data if you are not using HTTP chunked encoding.");
      }

      this.doWrite(buff, end, false, completionHandler);
   }

   private void doWrite(ByteBuf buff, boolean end, boolean connect, Handler completionHandler) {
      boolean writeHead;
      boolean writeEnd;
      synchronized(this) {
         if (this.ended) {
            completionHandler.handle(Future.failedFuture((Throwable)(new IllegalStateException("Request already complete"))));
            return;
         }

         this.checkResponseHandler();
         if (!this.headWritten) {
            this.headWritten = true;
            this.isConnect = connect;
            writeHead = true;
         } else {
            writeHead = false;
         }

         writeEnd = !this.isConnect && end;
         this.ended = end;
      }

      if (writeHead) {
         HttpMethod method = this.getMethod();
         String uri = this.getURI();
         if (uri.isEmpty()) {
            uri = "/";
         }

         HttpRequestHead head = new HttpRequestHead(method, uri, this.headers, this.authority(), this.absoluteURI(), this.traceOperation);
         this.stream.writeHead(head, this.chunked, buff, writeEnd, this.priority, connect, completionHandler);
      } else {
         if (buff == null && !end) {
            throw new IllegalArgumentException();
         }

         this.stream.writeBuffer(buff, writeEnd, completionHandler);
      }

      if (end) {
         this.tryComplete();
      }

   }

   private void checkEnded() {
      if (this.ended) {
         throw new IllegalStateException("Request already complete");
      }
   }

   private void checkResponseHandler() {
   }

   public synchronized HttpClientRequest setStreamPriority(StreamPriority priority) {
      if (this.headWritten) {
         this.stream.updatePriority(priority);
      } else {
         this.priority = priority;
      }

      return this;
   }

   public synchronized StreamPriority getStreamPriority() {
      return this.stream.priority();
   }
}
