package io.fabric8.kubernetes.client.vertx;

import io.fabric8.kubernetes.client.http.WebSocket;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.websocketx.CorruptedWebSocketFrameException;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClosedException;
import java.io.IOException;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class VertxWebSocket implements WebSocket {
   private static final Logger LOG = LoggerFactory.getLogger(VertxWebSocket.class);
   private final io.vertx.core.http.WebSocket ws;
   private final AtomicInteger pending = new AtomicInteger();
   private final WebSocket.Listener listener;

   VertxWebSocket(io.vertx.core.http.WebSocket ws, WebSocket.Listener listener) {
      this.ws = ws;
      this.listener = listener;
   }

   void init() {
      this.ws.binaryMessageHandler((msg) -> {
         this.ws.pause();
         this.listener.onMessage(this, msg.getByteBuf().nioBuffer());
      });
      this.ws.textMessageHandler((msg) -> {
         this.ws.pause();
         this.listener.onMessage(this, msg);
      });
      this.ws.endHandler((v) -> this.listener.onClose(this, this.ws.closeStatusCode(), this.ws.closeReason()));
      this.ws.exceptionHandler((err) -> {
         try {
            if (err instanceof CorruptedWebSocketFrameException) {
               err = (new ProtocolException()).initCause(err);
            } else if (err instanceof HttpClosedException) {
               err = new IOException(err);
            }

            this.listener.onError(this, err);
         } finally {
            if (!this.ws.isClosed()) {
               this.ws.close();
            }

         }

      });
      this.listener.onOpen(this);
   }

   public boolean send(ByteBuffer buffer) {
      Buffer vertxBuffer = Buffer.buffer(Unpooled.copiedBuffer(buffer));
      int len = vertxBuffer.length();
      this.pending.addAndGet(len);
      Future<Void> res = this.ws.writeBinaryMessage(vertxBuffer);
      if (res.isComplete()) {
         this.pending.addAndGet(-len);
         return res.succeeded();
      } else {
         res.onComplete((result) -> {
            if (result.cause() != null) {
               LOG.error("Queued write did not succeed", result.cause());
            }

            this.pending.addAndGet(-len);
         });
         return true;
      }
   }

   public synchronized boolean sendClose(int code, String reason) {
      if (this.ws.isClosed()) {
         return false;
      } else {
         Future<Void> res = this.ws.close((short)code, reason);
         res.onComplete((result) -> {
            this.ws.fetch(1L);
            if (result.cause() != null) {
               LOG.error("Queued close did not succeed", result.cause());
            }

         });
         return true;
      }
   }

   public long queueSize() {
      return (long)this.pending.get();
   }

   public void request() {
      this.ws.fetch(1L);
   }
}
