package org.sparkproject.jetty.client.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.sparkproject.jetty.client.AsyncContentProvider;
import org.sparkproject.jetty.client.Synchronizable;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;

/** @deprecated */
@Deprecated
public class DeferredContentProvider implements AsyncContentProvider, Callback, Closeable {
   private static final Chunk CLOSE;
   private final Object lock = this;
   private final Deque chunks = new ArrayDeque();
   private final AtomicReference listener = new AtomicReference();
   private final DeferredContentProviderIterator iterator = new DeferredContentProviderIterator();
   private final AtomicBoolean closed = new AtomicBoolean();
   private long length = -1L;
   private int size;
   private Throwable failure;

   public DeferredContentProvider(ByteBuffer... buffers) {
      for(ByteBuffer buffer : buffers) {
         this.offer(buffer);
      }

   }

   public void setListener(AsyncContentProvider.Listener listener) {
      if (!this.listener.compareAndSet((Object)null, listener)) {
         throw new IllegalStateException(String.format("The same %s instance cannot be used in multiple requests", AsyncContentProvider.class.getName()));
      } else {
         if (this.isClosed()) {
            synchronized(this.lock) {
               long total = 0L;

               for(Chunk chunk : this.chunks) {
                  total += (long)chunk.buffer.remaining();
               }

               this.length = total;
            }
         }

      }
   }

   public long getLength() {
      return this.length;
   }

   public boolean offer(ByteBuffer buffer) {
      return this.offer(buffer, Callback.NOOP);
   }

   public boolean offer(ByteBuffer buffer, Callback callback) {
      return this.offer(new Chunk(buffer, callback));
   }

   private boolean offer(Chunk chunk) {
      boolean result = false;
      Throwable failure;
      synchronized(this.lock) {
         failure = this.failure;
         if (failure == null) {
            result = this.chunks.offer(chunk);
            if (result && chunk != CLOSE) {
               ++this.size;
            }
         }
      }

      if (failure != null) {
         chunk.callback.failed(failure);
      } else if (result) {
         this.notifyListener();
      }

      return result;
   }

   private void clear() {
      synchronized(this.lock) {
         this.chunks.clear();
      }
   }

   public void flush() throws IOException {
      synchronized(this.lock) {
         try {
            while(this.failure == null) {
               if (this.size == 0) {
                  return;
               }

               this.lock.wait();
            }

            throw new IOException(this.failure);
         } catch (InterruptedException var4) {
            throw new InterruptedIOException();
         }
      }
   }

   public void close() {
      if (this.closed.compareAndSet(false, true)) {
         this.offer(CLOSE);
      }

   }

   public boolean isClosed() {
      return this.closed.get();
   }

   public void failed(Throwable failure) {
      this.iterator.failed(failure);
   }

   private void notifyListener() {
      AsyncContentProvider.Listener listener = (AsyncContentProvider.Listener)this.listener.get();
      if (listener != null) {
         listener.onContent();
      }

   }

   public Iterator iterator() {
      return this.iterator;
   }

   static {
      CLOSE = new Chunk(BufferUtil.EMPTY_BUFFER, Callback.NOOP);
   }

   private class DeferredContentProviderIterator implements Iterator, Callback, Synchronizable {
      private Chunk current;

      public boolean hasNext() {
         synchronized(DeferredContentProvider.this.lock) {
            return DeferredContentProvider.this.chunks.peek() != DeferredContentProvider.CLOSE;
         }
      }

      public ByteBuffer next() {
         synchronized(DeferredContentProvider.this.lock) {
            Chunk chunk = this.current = (Chunk)DeferredContentProvider.this.chunks.poll();
            if (chunk == DeferredContentProvider.CLOSE) {
               DeferredContentProvider.this.chunks.offerFirst(DeferredContentProvider.CLOSE);
               throw new NoSuchElementException();
            } else {
               return chunk == null ? null : chunk.buffer;
            }
         }
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }

      public void succeeded() {
         Chunk chunk;
         synchronized(DeferredContentProvider.this.lock) {
            chunk = this.current;
            this.current = null;
            if (chunk != null) {
               --DeferredContentProvider.this.size;
               DeferredContentProvider.this.lock.notify();
            }
         }

         if (chunk != null) {
            chunk.callback.succeeded();
         }

      }

      public void failed(Throwable x) {
         List<Chunk> chunks = new ArrayList();
         synchronized(DeferredContentProvider.this.lock) {
            DeferredContentProvider.this.failure = x;
            Chunk chunk = this.current;
            this.current = null;
            if (chunk != null) {
               chunks.add(chunk);
            }

            chunks.addAll(DeferredContentProvider.this.chunks);
            DeferredContentProvider.this.clear();
            DeferredContentProvider.this.lock.notify();
         }

         for(Chunk chunk : chunks) {
            chunk.callback.failed(x);
         }

      }

      public Object getLock() {
         return DeferredContentProvider.this.lock;
      }
   }

   public static class Chunk {
      public final ByteBuffer buffer;
      public final Callback callback;

      public Chunk(ByteBuffer buffer, Callback callback) {
         this.buffer = (ByteBuffer)Objects.requireNonNull(buffer);
         this.callback = (Callback)Objects.requireNonNull(callback);
      }

      public String toString() {
         return String.format("%s@%x", this.getClass().getSimpleName(), this.hashCode());
      }
   }
}
