package io.vertx.core.http.impl;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ClientWebSocket;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebSocketConnectOptions;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.net.SocketAddress;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

public class ClientWebSocketImpl implements ClientWebSocketInternal {
   private HttpClientBase client;
   private final AtomicReference connect = new AtomicReference();
   private volatile WebSocket ws;
   private Handler exceptionHandler;
   private Handler dataHandler;
   private Handler endHandler;
   private Handler closeHandler;
   private Handler drainHandler;
   private Handler frameHandler;
   private Handler textMessageHandler;
   private Handler binaryMessageHandler;
   private Handler pongHandler;

   ClientWebSocketImpl(HttpClientBase client) {
      this.client = client;
   }

   public Future connect(WebSocketConnectOptions options) {
      return this.connect(this.client.vertx().getOrCreateContext(), options);
   }

   public Future connect(Context context, WebSocketConnectOptions options) {
      return this.connect((ContextInternal)context, options);
   }

   private Future connect(ContextInternal ctx, WebSocketConnectOptions options) {
      Promise<WebSocket> promise = ctx.promise();
      if (!this.connect.compareAndSet((Object)null, promise)) {
         return ctx.failedFuture("Already connecting");
      } else {
         this.client.webSocket((WebSocketConnectOptions)options, (Handler)promise);
         return promise.future().andThen((ar) -> {
            if (ar.succeeded()) {
               WebSocket w = (WebSocket)ar.result();
               this.ws = w;
               w.handler(this.dataHandler);
               w.binaryMessageHandler(this.binaryMessageHandler);
               w.textMessageHandler(this.textMessageHandler);
               w.endHandler(this.endHandler);
               w.closeHandler(this.closeHandler);
               w.exceptionHandler(this.exceptionHandler);
               w.drainHandler(this.drainHandler);
               w.frameHandler(this.frameHandler);
               w.pongHandler(this.pongHandler);
               w.resume();
            }

         });
      }
   }

   public void connect(WebSocketConnectOptions options, Handler handler) {
      Future<WebSocket> fut = this.connect(options);
      if (handler != null) {
         fut.onComplete(handler);
      }

   }

   public ClientWebSocket exceptionHandler(Handler handler) {
      this.exceptionHandler = handler;
      WebSocket w = this.ws;
      if (w != null) {
         w.exceptionHandler(handler);
      }

      return this;
   }

   public ClientWebSocket handler(Handler handler) {
      this.dataHandler = handler;
      WebSocket w = this.ws;
      if (w != null) {
         w.handler(handler);
      }

      return this;
   }

   public WebSocket pause() {
      this.delegate().pause();
      return this;
   }

   public WebSocket resume() {
      this.delegate().resume();
      return this;
   }

   public WebSocket fetch(long amount) {
      this.delegate().fetch(amount);
      return this;
   }

   public ClientWebSocket endHandler(Handler handler) {
      this.endHandler = handler;
      WebSocket w = this.ws;
      if (w != null) {
         w.endHandler(handler);
      }

      return this;
   }

   public WebSocket setWriteQueueMaxSize(int maxSize) {
      this.delegate().setWriteQueueMaxSize(maxSize);
      return this;
   }

   public ClientWebSocket drainHandler(Handler handler) {
      this.drainHandler = handler;
      WebSocket w = this.ws;
      if (w != null) {
         w.drainHandler(handler);
      }

      return this;
   }

   public ClientWebSocket closeHandler(Handler handler) {
      this.closeHandler = handler;
      WebSocket w = this.ws;
      if (w != null) {
         w.closeHandler(handler);
      }

      return this;
   }

   public ClientWebSocket frameHandler(Handler handler) {
      this.frameHandler = handler;
      WebSocket w = this.ws;
      if (w != null) {
         w.frameHandler(handler);
      }

      return this;
   }

   public String binaryHandlerID() {
      return this.delegate().binaryHandlerID();
   }

   public String textHandlerID() {
      return this.delegate().textHandlerID();
   }

   public String subProtocol() {
      return this.delegate().subProtocol();
   }

   public Short closeStatusCode() {
      return this.delegate().closeStatusCode();
   }

   public String closeReason() {
      return this.delegate().closeReason();
   }

   public MultiMap headers() {
      return this.delegate().headers();
   }

   public Future writeFrame(WebSocketFrame frame) {
      return this.delegate().writeFrame(frame);
   }

   public ClientWebSocket writeFrame(WebSocketFrame frame, Handler handler) {
      this.delegate().writeFrame(frame, handler);
      return this;
   }

   public Future writeFinalTextFrame(String text) {
      return this.delegate().writeFinalTextFrame(text);
   }

   public ClientWebSocket writeFinalTextFrame(String text, Handler handler) {
      this.delegate().writeFinalTextFrame(text, handler);
      return this;
   }

   public Future writeFinalBinaryFrame(Buffer data) {
      return this.delegate().writeFinalBinaryFrame(data);
   }

   public ClientWebSocket writeFinalBinaryFrame(Buffer data, Handler handler) {
      this.delegate().writeFinalBinaryFrame(data, handler);
      return this;
   }

   public Future writeBinaryMessage(Buffer data) {
      return this.delegate().writeBinaryMessage(data);
   }

   public ClientWebSocket writeBinaryMessage(Buffer data, Handler handler) {
      this.delegate().writeBinaryMessage(data, handler);
      return this;
   }

   public Future writeTextMessage(String text) {
      return this.delegate().writeTextMessage(text);
   }

   public ClientWebSocket writeTextMessage(String text, Handler handler) {
      this.delegate().writeTextMessage(text, handler);
      return this;
   }

   public Future writePing(Buffer data) {
      return this.delegate().writePing(data);
   }

   public ClientWebSocket writePing(Buffer data, Handler handler) {
      this.delegate().writePing(data, handler);
      return this;
   }

   public Future writePong(Buffer data) {
      return this.delegate().writePong(data);
   }

   public ClientWebSocket writePong(Buffer data, Handler handler) {
      this.delegate().writePong(data, handler);
      return this;
   }

   public ClientWebSocket textMessageHandler(@Nullable Handler handler) {
      this.textMessageHandler = handler;
      WebSocket w = this.ws;
      if (w != null) {
         w.textMessageHandler(handler);
      }

      return this;
   }

   public ClientWebSocket binaryMessageHandler(@Nullable Handler handler) {
      this.binaryMessageHandler = handler;
      WebSocket w = this.ws;
      if (w != null) {
         w.binaryMessageHandler(handler);
      }

      return this;
   }

   public ClientWebSocket pongHandler(@Nullable Handler handler) {
      this.pongHandler = handler;
      WebSocket w = this.ws;
      if (w != null) {
         w.pongHandler(handler);
      }

      return this;
   }

   public Future end() {
      return this.delegate().end();
   }

   public void end(Handler handler) {
      this.delegate().end(handler);
   }

   public Future close() {
      return this.delegate().close();
   }

   public void close(Handler handler) {
      this.delegate().close(handler);
   }

   public Future close(short statusCode) {
      return this.delegate().close(statusCode);
   }

   public void close(short statusCode, Handler handler) {
      this.delegate().close(statusCode, handler);
   }

   public Future close(short statusCode, @Nullable String reason) {
      return this.delegate().close(statusCode, reason);
   }

   public void close(short statusCode, @Nullable String reason, Handler handler) {
      this.delegate().close(statusCode, reason, handler);
   }

   public SocketAddress remoteAddress() {
      return this.delegate().remoteAddress();
   }

   public SocketAddress localAddress() {
      return this.delegate().localAddress();
   }

   public boolean isSsl() {
      return this.delegate().isSsl();
   }

   public boolean isClosed() {
      return this.delegate().isClosed();
   }

   public SSLSession sslSession() {
      return this.delegate().sslSession();
   }

   public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
      return this.delegate().peerCertificateChain();
   }

   public List peerCertificates() throws SSLPeerUnverifiedException {
      return this.delegate().peerCertificates();
   }

   public Future write(Buffer data) {
      return this.delegate().write(data);
   }

   public void write(Buffer data, Handler handler) {
      this.delegate().write(data, handler);
   }

   public boolean writeQueueFull() {
      return this.delegate().writeQueueFull();
   }

   private WebSocket delegate() {
      WebSocket w = this.ws;
      if (w == null) {
         throw new IllegalStateException("Not connected");
      } else {
         return w;
      }
   }
}
