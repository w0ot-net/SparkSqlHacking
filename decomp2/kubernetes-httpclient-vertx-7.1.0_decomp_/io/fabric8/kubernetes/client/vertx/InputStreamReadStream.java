package io.fabric8.kubernetes.client.vertx;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.buffer.impl.VertxByteBufAllocator;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.impl.InboundBuffer;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

class InputStreamReadStream implements ReadStream {
   private static final int CHUNK_SIZE = 2048;
   private static final int MAX_DEPTH = 8;
   private final Buffer endSentinel;
   private final VertxHttpRequest vertxHttpRequest;
   private final InputStream is;
   private final HttpClientRequest request;
   private InboundBuffer inboundBuffer;
   private Handler exceptionHandler;
   private Handler endHandler;
   private byte[] bytes;
   final ThreadLocal counter = new ThreadLocal() {
      protected AtomicInteger initialValue() {
         return new AtomicInteger();
      }
   };

   InputStreamReadStream(VertxHttpRequest vertxHttpRequest, InputStream is, HttpClientRequest request) {
      this.vertxHttpRequest = vertxHttpRequest;
      this.is = is;
      this.request = request;
      this.endSentinel = Buffer.buffer();
   }

   public ReadStream exceptionHandler(Handler handler) {
      this.exceptionHandler = handler;
      return this;
   }

   public ReadStream handler(Handler handler) {
      boolean start = this.inboundBuffer == null && handler != null;
      if (start) {
         this.inboundBuffer = new InboundBuffer(this.vertxHttpRequest.vertx.getOrCreateContext());
         this.inboundBuffer.drainHandler((v) -> this.readChunk());
      }

      if (handler != null) {
         this.inboundBuffer.handler((buff) -> {
            if (buff == this.endSentinel) {
               if (this.endHandler != null) {
                  this.endHandler.handle((Object)null);
               }
            } else {
               handler.handle(buff);
            }

         });
      } else {
         this.inboundBuffer.handler((Handler)null);
      }

      if (start) {
         this.readChunk();
      }

      return this;
   }

   private void readChunk() {
      AtomicInteger atomicInteger = (AtomicInteger)this.counter.get();

      label37: {
         try {
            int depth = atomicInteger.getAndIncrement();
            if (depth >= 8) {
               break label37;
            }

            this.readChunk2();
         } finally {
            atomicInteger.decrementAndGet();
         }

         return;
      }

      this.vertxHttpRequest.vertx.runOnContext((v) -> this.readChunk());
   }

   private void readChunk2() {
      Future<Buffer> fut = this.vertxHttpRequest.vertx.executeBlocking((p) -> {
         if (this.bytes == null) {
            this.bytes = new byte[2048];
         }

         int amount;
         try {
            amount = this.is.read(this.bytes);
         } catch (IOException e) {
            p.fail(e);
            return;
         }

         if (amount == -1) {
            p.complete();
         } else {
            p.complete(Buffer.buffer(VertxByteBufAllocator.DEFAULT.heapBuffer(amount, Integer.MAX_VALUE).writeBytes(this.bytes, 0, amount)));
         }

      });
      fut.onComplete((ar) -> {
         if (ar.succeeded()) {
            Buffer chunk = (Buffer)ar.result();
            if (chunk != null) {
               boolean writable = this.inboundBuffer.write(chunk);
               if (writable) {
                  this.readChunk();
               }
            } else {
               this.inboundBuffer.write(this.endSentinel);
            }
         } else {
            if (this.exceptionHandler != null) {
               this.exceptionHandler.handle(ar.cause());
            }

            this.request.reset(0L, ar.cause());
         }

      });
   }

   public ReadStream endHandler(Handler handler) {
      this.endHandler = handler;
      return this;
   }

   public ReadStream pause() {
      this.inboundBuffer.pause();
      return this;
   }

   public ReadStream resume() {
      this.inboundBuffer.resume();
      return this;
   }

   public ReadStream fetch(long amount) {
      this.inboundBuffer.fetch(amount);
      return this;
   }
}
