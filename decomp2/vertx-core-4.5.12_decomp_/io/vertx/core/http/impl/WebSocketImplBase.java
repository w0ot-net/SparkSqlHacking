package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.WebSocketBase;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.core.http.WebSocketFrameType;
import io.vertx.core.http.impl.ws.WebSocketFrameImpl;
import io.vertx.core.http.impl.ws.WebSocketFrameInternal;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.VertxHandler;
import io.vertx.core.streams.impl.InboundBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

public abstract class WebSocketImplBase implements WebSocketInternal {
   private final boolean supportsContinuation;
   private final String textHandlerID;
   private final String binaryHandlerID;
   private final int maxWebSocketFrameSize;
   private final int maxWebSocketMessageSize;
   private final InboundBuffer pending;
   private ChannelHandlerContext chctx;
   protected final ContextInternal context;
   private MessageConsumer binaryHandlerRegistration;
   private MessageConsumer textHandlerRegistration;
   private String subProtocol;
   private Object metric;
   private Handler handler;
   private Handler frameHandler;
   private FrameAggregator frameAggregator;
   private Handler pongHandler;
   private Handler drainHandler;
   private Handler closeHandler;
   private Handler endHandler;
   protected final Http1xConnectionBase conn;
   private boolean writable;
   private boolean closed;
   private Short closeStatusCode;
   private String closeReason;
   private long closeTimeoutID = -1L;
   private MultiMap headers;

   WebSocketImplBase(ContextInternal context, Http1xConnectionBase conn, boolean supportsContinuation, int maxWebSocketFrameSize, int maxWebSocketMessageSize, boolean registerWebSocketWriteHandlers) {
      this.supportsContinuation = supportsContinuation;
      if (registerWebSocketWriteHandlers) {
         this.textHandlerID = "__vertx.ws." + UUID.randomUUID();
         this.binaryHandlerID = "__vertx.ws." + UUID.randomUUID();
      } else {
         this.textHandlerID = this.binaryHandlerID = null;
      }

      this.conn = conn;
      this.context = context;
      this.maxWebSocketFrameSize = maxWebSocketFrameSize;
      this.maxWebSocketMessageSize = maxWebSocketMessageSize;
      this.pending = new InboundBuffer(context);
      this.writable = !conn.isNotWritable();
      this.chctx = conn.channelHandlerContext();
      this.pending.handler(this::receiveFrame);
      this.pending.drainHandler((v) -> conn.doResume());
   }

   void registerHandler(EventBus eventBus) {
      if (this.binaryHandlerID != null) {
         Handler<Message<Buffer>> binaryHandler = (msg) -> this.writeBinaryFrameInternal((Buffer)msg.body());
         Handler<Message<String>> textHandler = (msg) -> this.writeTextFrameInternal((String)msg.body());
         this.binaryHandlerRegistration = eventBus.localConsumer(this.binaryHandlerID).handler(binaryHandler);
         this.textHandlerRegistration = eventBus.localConsumer(this.textHandlerID).handler(textHandler);
      }

   }

   public ChannelHandlerContext channelHandlerContext() {
      return this.chctx;
   }

   public HttpConnection connection() {
      return this.conn;
   }

   public String binaryHandlerID() {
      return this.binaryHandlerID;
   }

   public String textHandlerID() {
      return this.textHandlerID;
   }

   public boolean writeQueueFull() {
      synchronized(this.conn) {
         this.checkClosed();
         return this.conn.isNotWritable();
      }
   }

   public Future close() {
      return this.close((short)1000, (String)((String)null));
   }

   public void close(Handler handler) {
      Future<Void> future = this.close();
      if (handler != null) {
         future.onComplete(handler);
      }

   }

   public Future close(short statusCode) {
      return this.close(statusCode, (String)null);
   }

   public void close(short statusCode, Handler handler) {
      Future<Void> future = this.close(statusCode, (String)null);
      if (handler != null) {
         future.onComplete(handler);
      }

   }

   public void close(short statusCode, @Nullable String reason, Handler handler) {
      Future<Void> fut = this.close(statusCode, reason);
      if (handler != null) {
         fut.onComplete(handler);
      }

   }

   public Future close(short statusCode, String reason) {
      boolean sendCloseFrame;
      synchronized(this.conn) {
         if (sendCloseFrame = this.closeStatusCode == null) {
            this.closeStatusCode = statusCode;
            this.closeReason = reason;
         }
      }

      if (sendCloseFrame) {
         ByteBuf byteBuf = HttpUtils.generateWSCloseFrameByteBuf(statusCode, reason);
         CloseWebSocketFrame frame = new CloseWebSocketFrame(true, 0, byteBuf);
         PromiseInternal<Void> promise = this.context.promise();
         this.conn.writeToChannel(frame, promise);
         return promise;
      } else {
         return this.context.succeededFuture();
      }
   }

   public boolean isSsl() {
      return this.conn.isSsl();
   }

   public SSLSession sslSession() {
      return this.conn.sslSession();
   }

   public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
      return this.conn.peerCertificateChain();
   }

   public List peerCertificates() throws SSLPeerUnverifiedException {
      return this.conn.peerCertificates();
   }

   public SocketAddress localAddress() {
      return this.conn.localAddress();
   }

   public SocketAddress remoteAddress() {
      return this.conn.remoteAddress();
   }

   public Future writeFinalTextFrame(String text) {
      Promise<Void> promise = this.context.promise();
      this.writeFinalTextFrame(text, promise);
      return promise.future();
   }

   public WebSocketBase writeFinalTextFrame(String text, Handler handler) {
      return this.writeFrame(WebSocketFrame.textFrame(text, true), handler);
   }

   public Future writeFinalBinaryFrame(Buffer data) {
      Promise<Void> promise = this.context.promise();
      this.writeFinalBinaryFrame(data, promise);
      return promise.future();
   }

   public WebSocketBase writeFinalBinaryFrame(Buffer data, Handler handler) {
      return this.writeFrame(WebSocketFrame.binaryFrame(data, true), handler);
   }

   public String subProtocol() {
      synchronized(this.conn) {
         return this.subProtocol;
      }
   }

   void subProtocol(String subProtocol) {
      synchronized(this.conn) {
         this.subProtocol = subProtocol;
      }
   }

   public Short closeStatusCode() {
      synchronized(this.conn) {
         return this.closeStatusCode;
      }
   }

   public String closeReason() {
      synchronized(this.conn) {
         return this.closeReason;
      }
   }

   public MultiMap headers() {
      synchronized(this.conn) {
         return this.headers;
      }
   }

   void headers(MultiMap responseHeaders) {
      synchronized(this.conn) {
         this.headers = responseHeaders;
      }
   }

   public Future writeBinaryMessage(Buffer data) {
      return this.writePartialMessage(WebSocketFrameType.BINARY, data, 0);
   }

   public final WebSocketBase writeBinaryMessage(Buffer data, Handler handler) {
      Future<Void> fut = this.writeBinaryMessage(data);
      if (handler != null) {
         fut.onComplete(handler);
      }

      return this;
   }

   public Future writeTextMessage(String text) {
      byte[] utf8Bytes = text.getBytes(StandardCharsets.UTF_8);
      boolean isFinal = utf8Bytes.length <= this.maxWebSocketFrameSize;
      return isFinal ? this.writeFrame(new WebSocketFrameImpl(WebSocketFrameType.TEXT, utf8Bytes, true)) : this.writePartialMessage(WebSocketFrameType.TEXT, Buffer.buffer(utf8Bytes), 0);
   }

   public final WebSocketBase writeTextMessage(String text, Handler handler) {
      Future<Void> fut = this.writeTextMessage(text);
      if (handler != null) {
         fut.onComplete(handler);
      }

      return this;
   }

   public Future write(Buffer data) {
      return this.writeFrame(WebSocketFrame.binaryFrame(data, true));
   }

   public final void write(Buffer data, Handler handler) {
      Future<Void> fut = this.write(data);
      if (handler != null) {
         fut.onComplete(handler);
      }

   }

   public Future writePing(Buffer data) {
      return data.length() <= this.maxWebSocketFrameSize && data.length() <= 125 ? this.writeFrame(WebSocketFrame.pingFrame(data)) : this.context.failedFuture("Ping cannot exceed maxWebSocketFrameSize or 125 bytes");
   }

   public final WebSocketBase writePing(Buffer data, Handler handler) {
      Future<Void> fut = this.writePing(data);
      if (handler != null) {
         fut.onComplete(handler);
      }

      return this;
   }

   public Future writePong(Buffer data) {
      return data.length() <= this.maxWebSocketFrameSize && data.length() <= 125 ? this.writeFrame(WebSocketFrame.pongFrame(data)) : this.context.failedFuture("Pong cannot exceed maxWebSocketFrameSize or 125 bytes");
   }

   public final WebSocketBase writePong(Buffer data, Handler handler) {
      Future<Void> fut = this.writePong(data);
      if (handler != null) {
         fut.onComplete(handler);
      }

      return this;
   }

   private Future writePartialMessage(WebSocketFrameType frameType, Buffer data, int offset) {
      int end = offset + this.maxWebSocketFrameSize;
      boolean isFinal;
      if (end >= data.length()) {
         end = data.length();
         isFinal = true;
      } else {
         isFinal = false;
      }

      Buffer slice = data.slice(offset, end);
      WebSocketFrame frame;
      if (offset != 0 && this.supportsContinuation) {
         frame = WebSocketFrame.continuationFrame(slice, isFinal);
      } else {
         frame = new WebSocketFrameImpl(frameType, slice.getByteBuf(), isFinal);
      }

      int newOffset = offset + this.maxWebSocketFrameSize;
      if (isFinal) {
         return this.writeFrame(frame);
      } else {
         this.writeFrame(frame);
         return this.writePartialMessage(frameType, data, newOffset);
      }
   }

   public Future writeFrame(WebSocketFrame frame) {
      synchronized(this.conn) {
         return this.unsafeWriteFrame((WebSocketFrameImpl)frame);
      }
   }

   protected final Future unsafeWriteFrame(WebSocketFrameImpl frame) {
      assert Thread.holdsLock(this.conn);

      if (this.unsafeIsClosed()) {
         return this.context.failedFuture("WebSocket is closed");
      } else {
         PromiseInternal<Void> promise = this.context.promise();
         this.conn.writeToChannel(encodeFrame(frame), promise);
         return promise.future();
      }
   }

   public final WebSocketBase writeFrame(WebSocketFrame frame, Handler handler) {
      Future<Void> fut = this.writeFrame(frame);
      if (handler != null) {
         fut.onComplete(handler);
      }

      return this;
   }

   private void writeBinaryFrameInternal(Buffer data) {
      this.writeFrame(new WebSocketFrameImpl(WebSocketFrameType.BINARY, data.getByteBuf()));
   }

   private void writeTextFrameInternal(String str) {
      this.writeFrame(new WebSocketFrameImpl(str));
   }

   private static io.netty.handler.codec.http.websocketx.WebSocketFrame encodeFrame(WebSocketFrameImpl frame) {
      ByteBuf buf = VertxHandler.safeBuffer(frame.getBinaryData());
      switch (frame.type()) {
         case BINARY:
            return new BinaryWebSocketFrame(frame.isFinal(), 0, buf);
         case TEXT:
            return new TextWebSocketFrame(frame.isFinal(), 0, buf);
         case CLOSE:
            return new CloseWebSocketFrame(true, 0, buf);
         case CONTINUATION:
            return new ContinuationWebSocketFrame(frame.isFinal(), 0, buf);
         case PONG:
            return new PongWebSocketFrame(buf);
         case PING:
            return new PingWebSocketFrame(buf);
         default:
            throw new IllegalStateException("Unsupported WebSocket msg " + frame);
      }
   }

   void checkClosed() {
      if (this.isClosed()) {
         throw new IllegalStateException("WebSocket is closed");
      }
   }

   public boolean isClosed() {
      synchronized(this.conn) {
         return this.unsafeIsClosed();
      }
   }

   private boolean unsafeIsClosed() {
      assert Thread.holdsLock(this.conn);

      return this.closed || this.closeStatusCode != null;
   }

   void handleFrame(WebSocketFrameInternal frame) {
      switch (frame.type()) {
         case CLOSE:
            this.handleCloseFrame(frame);
         case CONTINUATION:
         default:
            break;
         case PONG:
            Handler<Buffer> pongHandler = this.pongHandler();
            if (pongHandler != null) {
               this.context.dispatch(frame.binaryData(), pongHandler);
            }
            break;
         case PING:
            this.conn.writeToChannel(new PongWebSocketFrame(frame.getBinaryData().copy()));
      }

      if (!this.pending.write((Object)frame)) {
         this.conn.doPause();
      }

   }

   private void handleCloseFrame(WebSocketFrameInternal closeFrame) {
      boolean echo;
      synchronized(this.conn) {
         echo = this.closeStatusCode == null;
         this.closed = true;
         this.closeStatusCode = closeFrame.closeStatusCode();
         this.closeReason = closeFrame.closeReason();
      }

      this.handleClose(true);
      if (echo) {
         ChannelPromise fut = this.conn.channelFuture();
         this.conn.writeToChannel(new CloseWebSocketFrame(this.closeStatusCode, this.closeReason), fut);
         fut.addListener((v) -> this.handleCloseConnection());
      } else {
         this.handleCloseConnection();
      }

   }

   protected void handleClose(boolean graceful) {
      MessageConsumer<?> binaryConsumer;
      MessageConsumer<?> textConsumer;
      Handler<Void> closeHandler;
      Handler<Throwable> exceptionHandler;
      synchronized(this.conn) {
         closeHandler = this.closeHandler;
         Http1xConnectionBase var10000 = this.conn;
         exceptionHandler = var10000::handleException;
         binaryConsumer = this.binaryHandlerRegistration;
         textConsumer = this.textHandlerRegistration;
         this.binaryHandlerRegistration = null;
         this.textHandlerRegistration = null;
         this.closeHandler = null;
      }

      if (binaryConsumer != null) {
         binaryConsumer.unregister();
      }

      if (textConsumer != null) {
         textConsumer.unregister();
      }

      if (exceptionHandler != null && !graceful) {
         this.context.dispatch(HttpUtils.CONNECTION_CLOSED_EXCEPTION, exceptionHandler);
      }

      if (closeHandler != null) {
         this.context.dispatch((Object)null, closeHandler);
      }

   }

   private void receiveFrame(WebSocketFrameInternal frame) {
      Handler<WebSocketFrameInternal> frameAggregator;
      Handler<WebSocketFrameInternal> frameHandler;
      synchronized(this.conn) {
         frameHandler = this.frameHandler;
         frameAggregator = this.frameAggregator;
      }

      if (frameAggregator != null) {
         this.context.dispatch(frame, frameAggregator);
      }

      if (frameHandler != null) {
         this.context.dispatch(frame, frameHandler);
      }

      switch (frame.type()) {
         case BINARY:
         case TEXT:
         case CONTINUATION:
            Handler<Buffer> handler = this.handler();
            if (handler != null) {
               this.context.dispatch(frame.binaryData(), handler);
            }
            break;
         case CLOSE:
            Handler<Void> endHandler = this.endHandler();
            if (endHandler != null) {
               this.context.dispatch(endHandler);
            }
            break;
         case PONG:
         case PING:
            this.fetch(1L);
      }

   }

   protected abstract void handleCloseConnection();

   void closeConnection() {
      this.conn.channelHandlerContext().close();
   }

   void initiateConnectionCloseTimeout(long timeoutMillis) {
      synchronized(this.conn) {
         this.closeTimeoutID = this.context.setTimer(timeoutMillis, (id) -> {
            synchronized(this.conn) {
               this.closeTimeoutID = -1L;
            }

            this.closeConnection();
         });
      }
   }

   public WebSocketBase frameHandler(Handler handler) {
      synchronized(this.conn) {
         this.checkClosed();
         this.frameHandler = handler;
         return this;
      }
   }

   public WebSocketBase textMessageHandler(Handler handler) {
      synchronized(this.conn) {
         this.checkClosed();
         if (handler != null) {
            if (this.frameAggregator == null) {
               this.frameAggregator = new FrameAggregator();
            }

            this.frameAggregator.textMessageHandler = handler;
         } else if (this.frameAggregator != null) {
            if (this.frameAggregator.binaryMessageHandler == null) {
               this.frameAggregator = null;
            } else {
               this.frameAggregator.textMessageHandler = null;
               this.frameAggregator.textMessageBuffer = null;
            }
         }

         return this;
      }
   }

   public WebSocketBase binaryMessageHandler(Handler handler) {
      synchronized(this.conn) {
         this.checkClosed();
         if (handler != null) {
            if (this.frameAggregator == null) {
               this.frameAggregator = new FrameAggregator();
            }

            this.frameAggregator.binaryMessageHandler = handler;
         } else if (this.frameAggregator != null) {
            if (this.frameAggregator.textMessageHandler == null) {
               this.frameAggregator = null;
            } else {
               this.frameAggregator.binaryMessageHandler = null;
               this.frameAggregator.binaryMessageBuffer = null;
            }
         }

         return this;
      }
   }

   public WebSocketBase pongHandler(Handler handler) {
      synchronized(this.conn) {
         this.checkClosed();
         this.pongHandler = handler;
         return this;
      }
   }

   private Handler pongHandler() {
      synchronized(this.conn) {
         return this.pongHandler;
      }
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
      this.conn.handleException(t);
   }

   void handleConnectionClosed() {
      synchronized(this.conn) {
         if (this.closeTimeoutID != -1L) {
            this.context.owner().cancelTimer(this.closeTimeoutID);
         }

         if (this.closed) {
            return;
         }

         this.closed = true;
      }

      this.handleClose(false);
   }

   synchronized void setMetric(Object metric) {
      this.metric = metric;
   }

   synchronized Object getMetric() {
      return this.metric;
   }

   public WebSocketBase handler(Handler handler) {
      synchronized(this.conn) {
         if (handler != null) {
            this.checkClosed();
         }

         this.handler = handler;
         return this;
      }
   }

   private Handler handler() {
      synchronized(this.conn) {
         return this.handler;
      }
   }

   public WebSocketBase endHandler(Handler handler) {
      synchronized(this.conn) {
         if (handler != null) {
            this.checkClosed();
         }

         this.endHandler = handler;
         return this;
      }
   }

   private Handler endHandler() {
      synchronized(this.conn) {
         return this.endHandler;
      }
   }

   public WebSocketBase exceptionHandler(Handler handler) {
      this.conn.exceptionHandler(handler);
      return this;
   }

   public WebSocketBase closeHandler(Handler handler) {
      synchronized(this.conn) {
         this.checkClosed();
         this.closeHandler = handler;
         return this;
      }
   }

   public WebSocketBase drainHandler(Handler handler) {
      synchronized(this.conn) {
         this.checkClosed();
         this.drainHandler = handler;
         return this;
      }
   }

   public WebSocketBase pause() {
      this.pending.pause();
      return this;
   }

   public WebSocketBase resume() {
      this.pending.resume();
      return this;
   }

   public WebSocketBase fetch(long amount) {
      this.pending.fetch(amount);
      return this;
   }

   public WebSocketBase setWriteQueueMaxSize(int maxSize) {
      synchronized(this.conn) {
         this.checkClosed();
         this.conn.doSetWriteQueueMaxSize(maxSize);
         return this;
      }
   }

   public Future end() {
      return this.close();
   }

   public void end(Handler handler) {
      this.close(handler);
   }

   private class FrameAggregator implements Handler {
      private Handler textMessageHandler;
      private Handler binaryMessageHandler;
      private Buffer textMessageBuffer;
      private Buffer binaryMessageBuffer;

      private FrameAggregator() {
      }

      public void handle(WebSocketFrameInternal frame) {
         switch (frame.type()) {
            case BINARY:
               this.handleBinaryFrame(frame);
               break;
            case TEXT:
               this.handleTextFrame(frame);
            case CLOSE:
            default:
               break;
            case CONTINUATION:
               if (this.textMessageBuffer != null && this.textMessageBuffer.length() > 0) {
                  this.handleTextFrame(frame);
               } else if (this.binaryMessageBuffer != null && this.binaryMessageBuffer.length() > 0) {
                  this.handleBinaryFrame(frame);
               }
         }

      }

      private void handleTextFrame(WebSocketFrameInternal frame) {
         Buffer frameBuffer = Buffer.buffer(frame.getBinaryData());
         if (this.textMessageBuffer == null) {
            this.textMessageBuffer = frameBuffer;
         } else {
            this.textMessageBuffer.appendBuffer(frameBuffer);
         }

         if (this.textMessageBuffer.length() > WebSocketImplBase.this.maxWebSocketMessageSize) {
            int len = this.textMessageBuffer.length() - frameBuffer.length();
            this.textMessageBuffer = null;
            String msg = "Cannot process text frame of size " + frameBuffer.length() + ", it would cause message buffer (size " + len + ") to overflow max message size of " + WebSocketImplBase.this.maxWebSocketMessageSize;
            WebSocketImplBase.this.handleException(new IllegalStateException(msg));
         } else {
            if (frame.isFinal()) {
               String fullMessage = this.textMessageBuffer.toString();
               this.textMessageBuffer = null;
               if (this.textMessageHandler != null) {
                  this.textMessageHandler.handle(fullMessage);
               }
            }

         }
      }

      private void handleBinaryFrame(WebSocketFrameInternal frame) {
         Buffer frameBuffer = Buffer.buffer(frame.getBinaryData());
         if (this.binaryMessageBuffer == null) {
            this.binaryMessageBuffer = frameBuffer;
         } else {
            this.binaryMessageBuffer.appendBuffer(frameBuffer);
         }

         if (this.binaryMessageBuffer.length() > WebSocketImplBase.this.maxWebSocketMessageSize) {
            int len = this.binaryMessageBuffer.length() - frameBuffer.length();
            this.binaryMessageBuffer = null;
            String msg = "Cannot process binary frame of size " + frameBuffer.length() + ", it would cause message buffer (size " + len + ") to overflow max message size of " + WebSocketImplBase.this.maxWebSocketMessageSize;
            WebSocketImplBase.this.handleException(new IllegalStateException(msg));
         } else {
            if (frame.isFinal()) {
               Buffer fullMessage = this.binaryMessageBuffer.copy();
               this.binaryMessageBuffer = null;
               if (this.binaryMessageHandler != null) {
                  this.binaryMessageHandler.handle(fullMessage);
               }
            }

         }
      }
   }
}
