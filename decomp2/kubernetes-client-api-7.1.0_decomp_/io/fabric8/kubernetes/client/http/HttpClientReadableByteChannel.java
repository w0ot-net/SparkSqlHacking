package io.fabric8.kubernetes.client.http;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ReadableByteChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class HttpClientReadableByteChannel implements ReadableByteChannel, AsyncBody.Consumer {
   private final LinkedList buffers = new LinkedList();
   private Throwable failed;
   private boolean closed;
   private boolean done;
   private CompletableFuture asyncBodyFuture = new CompletableFuture();
   private ByteBuffer currentBuffer;
   private ReentrantLock lock = new ReentrantLock();
   private Condition condition;

   public HttpClientReadableByteChannel() {
      this.condition = this.lock.newCondition();
   }

   public void consume(List value, AsyncBody asyncBody) throws Exception {
      this.doLockedAndSignal(() -> this.buffers.addAll(value));
   }

   protected void onResponse(HttpResponse response) {
      AsyncBody asyncBody = (AsyncBody)response.body();
      this.asyncBodyFuture.complete(asyncBody);
      asyncBody.done().whenComplete(this::onBodyDone);
      asyncBody.consume();
      this.doLockedAndSignal(() -> null);
   }

   private void onBodyDone(Void v, Throwable t) {
      this.doLockedAndSignal(() -> {
         if (t != null) {
            this.failed = t;
         }

         this.done = true;
         return null;
      });
   }

   Object doLockedAndSignal(Supplier run) {
      this.lock.lock();

      Object var2;
      try {
         this.condition.signalAll();
         var2 = run.get();
      } finally {
         this.lock.unlock();
      }

      return var2;
   }

   public void close() {
      if ((Boolean)this.doLockedAndSignal(() -> {
         if (this.closed) {
            return false;
         } else {
            this.closed = true;
            return true;
         }
      })) {
         this.asyncBodyFuture.thenAccept(AsyncBody::cancel);
      }

   }

   public synchronized boolean isOpen() {
      return !this.closed;
   }

   public int read(ByteBuffer arg0) throws IOException {
      this.lock.lock();

      try {
         if (this.closed) {
            throw new ClosedChannelException();
         } else {
            int read;
            int remaining;
            for(read = 0; arg0.hasRemaining(); read += remaining) {
               for(; this.currentBuffer == null || !this.currentBuffer.hasRemaining(); this.currentBuffer = (ByteBuffer)this.buffers.poll()) {
                  if (this.buffers.isEmpty()) {
                     if (this.failed != null) {
                        throw new IOException("channel already closed with exception", this.failed);
                     }

                     if (read > 0) {
                        remaining = read;
                        return remaining;
                     }

                     if (this.done) {
                        remaining = -1;
                        return remaining;
                     }

                     this.lock.unlock();

                     try {
                        this.asyncBodyFuture.thenAccept(AsyncBody::consume);
                     } finally {
                        this.lock.lock();
                     }

                     try {
                        while(!this.done && this.buffers.isEmpty()) {
                           this.condition.await();
                        }
                     } catch (InterruptedException var13) {
                        this.close();
                        Thread.currentThread().interrupt();
                        throw new ClosedByInterruptException();
                     }
                  }
               }

               remaining = Math.min(arg0.remaining(), this.currentBuffer.remaining());

               for(int i = 0; i < remaining; ++i) {
                  arg0.put(this.currentBuffer.get());
               }
            }

            remaining = read;
            return remaining;
         }
      } finally {
         if (this.lock.isHeldByCurrentThread()) {
            this.lock.unlock();
         }

      }
   }
}
