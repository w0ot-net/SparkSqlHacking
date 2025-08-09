package io.vertx.core.http.impl;

import io.netty.handler.codec.http2.Http2Error;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpClosedException;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.StreamResetException;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.NoStackTraceTimeoutException;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.net.HostAndPort;
import io.vertx.core.net.SocketAddress;
import java.util.Objects;

public abstract class HttpClientRequestBase implements HttpClientRequest {
   protected final ContextInternal context;
   protected final HttpClientStream stream;
   protected final SocketAddress server;
   protected final boolean ssl;
   private HttpMethod method;
   private String host;
   private int port;
   private String uri;
   private String path;
   private String query;
   private final PromiseInternal responsePromise;
   private Handler pushHandler;
   private long currentTimeoutTimerId = -1L;
   private long currentTimeoutMs;
   private long lastDataReceived;
   private Throwable reset;

   HttpClientRequestBase(HttpClientStream stream, PromiseInternal responsePromise, HttpMethod method, SocketAddress server, String host, int port, String uri) {
      this.stream = stream;
      this.responsePromise = responsePromise;
      this.context = responsePromise.context();
      this.uri = uri;
      this.method = method;
      this.server = server;
      this.host = host;
      this.port = port;
      this.ssl = stream.connection().isSsl();
      stream.pushHandler(this::handlePush);
      stream.headHandler((resp) -> {
         HttpClientResponseImpl response = new HttpClientResponseImpl(this, stream.version(), stream, resp.statusCode, resp.statusMessage, resp.headers);
         stream.chunkHandler(response::handleChunk);
         stream.endHandler(response::handleEnd);
         stream.priorityHandler(response::handlePriorityChange);
         stream.unknownFrameHandler(response::handleUnknownFrame);
         this.handleResponse(response);
      });
   }

   protected String authority() {
      return (this.port != 80 || this.ssl) && (this.port != 443 || !this.ssl) && this.port >= 0 ? this.host + ':' + this.port : this.host;
   }

   public int streamId() {
      return this.stream.id();
   }

   public String absoluteURI() {
      return (this.ssl ? "https://" : "http://") + this.authority() + this.uri;
   }

   public String query() {
      if (this.query == null) {
         this.query = HttpUtils.parseQuery(this.uri);
      }

      return this.query;
   }

   public String path() {
      if (this.path == null) {
         this.path = HttpUtils.parsePath(this.uri);
      }

      return this.path;
   }

   public synchronized String getURI() {
      return this.uri;
   }

   public synchronized HttpClientRequest setURI(String uri) {
      Objects.requireNonNull(uri);
      this.uri = uri;
      this.path = null;
      this.query = null;
      return this;
   }

   public String getHost() {
      return this.host;
   }

   public synchronized HttpClientRequest authority(HostAndPort authority) {
      Objects.requireNonNull(authority);
      this.host = authority.host();
      this.port = authority.port();
      return this;
   }

   public synchronized HttpClientRequest setHost(String host) {
      Objects.requireNonNull(this.uri);
      this.host = host;
      return this;
   }

   public int getPort() {
      return this.port;
   }

   public synchronized HttpClientRequest setPort(int port) {
      this.port = port;
      return this;
   }

   public synchronized HttpMethod getMethod() {
      return this.method;
   }

   public synchronized HttpClientRequest setMethod(HttpMethod method) {
      Objects.requireNonNull(this.uri);
      this.method = method;
      return this;
   }

   public synchronized HttpClientRequest setTimeout(long timeout) {
      this.cancelTimeout();
      this.currentTimeoutMs = timeout;
      this.currentTimeoutTimerId = this.context.setTimer(timeout, (id) -> this.handleTimeout(timeout));
      return this;
   }

   protected Throwable mapException(Throwable t) {
      if (t instanceof HttpClosedException && this.reset != null) {
         t = this.reset;
      }

      return t;
   }

   void handleException(Throwable t) {
      this.fail(t);
   }

   void fail(Throwable t) {
      this.cancelTimeout();
      this.responsePromise.tryFail(t);
      HttpClientResponseImpl response = (HttpClientResponseImpl)this.responsePromise.future().result();
      if (response != null) {
         response.handleException(t);
      }

   }

   void handlePush(HttpClientPush push) {
      HttpClientRequestPushPromise pushReq = new HttpClientRequestPushPromise(push.stream, push.method, push.uri, push.host, push.port, push.headers);
      if (this.pushHandler != null) {
         this.pushHandler.handle(pushReq);
      } else {
         pushReq.reset(Http2Error.CANCEL.code());
      }

   }

   void handleResponse(HttpClientResponse resp) {
      if (this.reset == null) {
         this.handleResponse(this.responsePromise, resp, this.cancelTimeout());
      }

   }

   abstract void handleResponse(Promise var1, HttpClientResponse var2, long var3);

   private synchronized long cancelTimeout() {
      long ret;
      if ((ret = this.currentTimeoutTimerId) != -1L) {
         this.context.owner().cancelTimer(this.currentTimeoutTimerId);
         this.currentTimeoutTimerId = -1L;
         ret = this.currentTimeoutMs;
         this.currentTimeoutMs = 0L;
      }

      return ret;
   }

   private void handleTimeout(long timeoutMs) {
      NoStackTraceTimeoutException cause;
      synchronized(this) {
         this.currentTimeoutTimerId = -1L;
         this.currentTimeoutMs = 0L;
         if (this.lastDataReceived > 0L) {
            long now = System.currentTimeMillis();
            long timeSinceLastData = now - this.lastDataReceived;
            if (timeSinceLastData < timeoutMs) {
               this.lastDataReceived = 0L;
               this.setTimeout(timeoutMs - timeSinceLastData);
               return;
            }
         }

         cause = timeoutEx(timeoutMs, this.method, this.server, this.uri);
      }

      this.reset(cause);
   }

   static NoStackTraceTimeoutException timeoutEx(long timeoutMs, HttpMethod method, SocketAddress server, String uri) {
      return new NoStackTraceTimeoutException("The timeout period of " + timeoutMs + "ms has been exceeded while executing " + method + " " + uri + " for server " + server);
   }

   synchronized void dataReceived() {
      if (this.currentTimeoutTimerId != -1L) {
         this.lastDataReceived = System.currentTimeMillis();
      }

   }

   public boolean reset(long code) {
      return this.reset(new StreamResetException(code));
   }

   public boolean reset(long code, Throwable cause) {
      return this.reset(new StreamResetException(code, cause));
   }

   private boolean reset(Throwable cause) {
      synchronized(this) {
         if (this.reset != null) {
            return false;
         }

         this.reset = cause;
      }

      this.stream.reset(cause);
      return true;
   }

   public HttpClientRequest response(Handler handler) {
      this.responsePromise.future().onComplete(handler);
      return this;
   }

   public Future response() {
      return this.responsePromise.future();
   }

   synchronized Handler pushHandler() {
      return this.pushHandler;
   }

   public synchronized HttpClientRequest pushHandler(Handler handler) {
      this.pushHandler = handler;
      return this;
   }
}
