package org.sparkproject.jetty.client.util;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.thread.AutoLock;

public class AsyncRequestContent implements Request.Content, Request.Content.Subscription, Closeable {
   private static final Logger LOG = LoggerFactory.getLogger(AsyncRequestContent.class);
   private final AutoLock lock;
   private final Condition flush;
   private final Deque chunks;
   private final String contentType;
   private long length;
   private Request.Content.Consumer consumer;
   private boolean emitInitialContent;
   private int demand;
   private boolean stalled;
   private boolean committed;
   private boolean closed;
   private boolean terminated;
   private Throwable failure;

   public AsyncRequestContent(ByteBuffer... buffers) {
      this("application/octet-stream", buffers);
   }

   public AsyncRequestContent(String contentType, ByteBuffer... buffers) {
      this.lock = new AutoLock();
      this.flush = this.lock.newCondition();
      this.chunks = new ArrayDeque();
      this.length = -1L;
      this.contentType = contentType;
      Stream.of(buffers).forEach(this::offer);
   }

   public String getContentType() {
      return this.contentType;
   }

   public long getLength() {
      return this.length;
   }

   public Request.Content.Subscription subscribe(Request.Content.Consumer consumer, boolean emitInitialContent) {
      try (AutoLock ignored = this.lock.lock()) {
         if (this.consumer != null) {
            throw new IllegalStateException("Multiple subscriptions not supported on " + String.valueOf(this));
         }

         this.consumer = consumer;
         this.emitInitialContent = emitInitialContent;
         this.stalled = true;
         if (this.closed) {
            this.length = this.chunks.stream().mapToLong((chunk) -> (long)chunk.buffer.remaining()).sum();
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Content subscription for {}: {}", this, consumer);
      }

      return this;
   }

   public void demand() {
      boolean produce;
      try (AutoLock ignored = this.lock.lock()) {
         ++this.demand;
         produce = this.stalled;
         if (this.stalled) {
            this.stalled = false;
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Content demand, producing {} for {}", produce, this);
      }

      if (produce) {
         this.produce();
      }

   }

   public void fail(Throwable failure) {
      List<Callback> toFail = List.of();

      try (AutoLock l = this.lock.lock()) {
         if (this.failure == null) {
            this.failure = failure;
            toFail = (List)this.chunks.stream().map((chunk) -> chunk.callback).collect(Collectors.toList());
            this.chunks.clear();
            this.flush.signal();
         }
      }

      toFail.forEach((c) -> c.failed(failure));
   }

   public boolean offer(ByteBuffer buffer) {
      return this.offer(buffer, Callback.NOOP);
   }

   public boolean offer(ByteBuffer buffer, Callback callback) {
      return this.offer(new Chunk(buffer, callback));
   }

   private boolean offer(Chunk chunk) {
      boolean produce = false;

      Throwable failure;
      try (AutoLock ignored = this.lock.lock()) {
         failure = this.failure;
         if (failure == null) {
            if (this.closed) {
               failure = new IOException("closed");
            } else {
               this.chunks.offer(chunk);
               if (this.demand > 0 && this.stalled) {
                  this.stalled = false;
                  produce = true;
               }
            }
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Content offer {}, producing {} for {}", new Object[]{failure == null ? "succeeded" : "failed", produce, this, failure});
      }

      if (failure != null) {
         chunk.callback.failed(failure);
         return false;
      } else {
         if (produce) {
            this.produce();
         }

         return true;
      }
   }

   private void produce() {
      while(true) {
         Throwable failure;
         try (AutoLock ignored = this.lock.lock()) {
            failure = this.failure;
         }

         if (failure != null) {
            this.notifyFailure(this.consumer, failure);
            return;
         }

         try {
            Chunk chunk = AsyncRequestContent.Chunk.EMPTY;
            boolean lastContent = false;

            Request.Content.Consumer consumer;
            try (AutoLock ignored = this.lock.lock()) {
               if (this.terminated) {
                  throw new EOFException("Demand after last content");
               }

               consumer = this.consumer;
               if (this.committed || this.emitInitialContent) {
                  chunk = (Chunk)this.chunks.poll();
                  lastContent = this.closed && this.chunks.isEmpty();
                  if (lastContent) {
                     this.terminated = true;
                  }
               }

               if (chunk == null && (lastContent || !this.committed)) {
                  chunk = AsyncRequestContent.Chunk.EMPTY;
               }

               if (chunk == null) {
                  this.stalled = true;
               } else {
                  --this.demand;
                  this.committed = true;
               }
            }

            if (chunk == null) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("No content, processing stalled for {}", this);
               }

               return;
            }

            this.notifyContent(consumer, chunk.buffer, lastContent, Callback.from(this::notifyFlush, chunk.callback));

            boolean noDemand;
            try (AutoLock ignored = this.lock.lock()) {
               noDemand = this.demand == 0;
               if (noDemand) {
                  this.stalled = true;
               }
            }

            if (noDemand) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("No demand, processing stalled for {}", this);
               }

               return;
            }
         } catch (Throwable x) {
            this.fail(x);
         }
      }
   }

   private void notifyContent(Request.Content.Consumer consumer, ByteBuffer buffer, boolean last, Callback callback) {
      try {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Notifying content last={} {} for {}", new Object[]{last, BufferUtil.toDetailString(buffer), this});
         }

         consumer.onContent(buffer, last, callback);
      } catch (Throwable var6) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Failure while notifying content", var6);
         }

         callback.failed(var6);
         this.fail(var6);
      }

   }

   private void notifyFailure(Request.Content.Consumer consumer, Throwable failure) {
      try {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Notifying failure for {}", this, failure);
         }

         consumer.onFailure(failure);
      } catch (Throwable x) {
         LOG.trace("Failure while notifying content failure {}", failure, x);
      }

   }

   private void notifyFlush() {
      try (AutoLock l = this.lock.lock()) {
         this.flush.signal();
      }

   }

   public void flush() throws IOException {
      try (AutoLock l = this.lock.lock()) {
         try {
            while(this.failure == null) {
               if (this.chunks.isEmpty()) {
                  return;
               }

               this.flush.await();
            }

            throw new IOException(this.failure);
         } catch (InterruptedException var5) {
            throw new InterruptedIOException();
         }
      }
   }

   public void close() {
      boolean produce = false;

      try (AutoLock l = this.lock.lock()) {
         if (this.closed) {
            return;
         }

         this.closed = true;
         if (this.demand > 0 && this.stalled) {
            this.stalled = false;
            produce = true;
         }

         this.flush.signal();
      }

      if (produce) {
         this.produce();
      }

   }

   public boolean isClosed() {
      try (AutoLock ignored = this.lock.lock()) {
         return this.closed;
      }
   }

   public String toString() {
      int demand;
      boolean stalled;
      int chunks;
      try (AutoLock ignored = this.lock.lock()) {
         demand = this.demand;
         stalled = this.stalled;
         chunks = this.chunks.size();
      }

      return String.format("%s@%x[demand=%d,stalled=%b,chunks=%d]", this.getClass().getSimpleName(), this.hashCode(), demand, stalled, chunks);
   }

   private static class Chunk {
      private static final Chunk EMPTY;
      private final ByteBuffer buffer;
      private final Callback callback;

      private Chunk(ByteBuffer buffer, Callback callback) {
         this.buffer = (ByteBuffer)Objects.requireNonNull(buffer);
         this.callback = (Callback)Objects.requireNonNull(callback);
      }

      static {
         EMPTY = new Chunk(BufferUtil.EMPTY_BUFFER, Callback.NOOP);
      }
   }
}
