package org.sparkproject.jetty.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.thread.AutoLock;

public class InputStreamResponseListener extends Response.Listener.Adapter {
   private static final Logger LOG = LoggerFactory.getLogger(InputStreamResponseListener.class);
   private static final Chunk EOF;
   private final AutoLock.WithCondition lock = new AutoLock.WithCondition();
   private final CountDownLatch responseLatch = new CountDownLatch(1);
   private final CountDownLatch resultLatch = new CountDownLatch(1);
   private final AtomicReference stream = new AtomicReference();
   private final Queue chunks = new ArrayDeque();
   private Response response;
   private Result result;
   private Throwable failure;
   private boolean closed;

   public void onHeaders(Response response) {
      try (AutoLock ignored = this.lock.lock()) {
         this.response = response;
         this.responseLatch.countDown();
      }

   }

   public void onContent(Response response, ByteBuffer content, Callback callback) {
      if (content.remaining() == 0) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Skipped empty content {}", content);
         }

         callback.succeeded();
      } else {
         boolean closed;
         try (AutoLock.WithCondition l = this.lock.lock()) {
            closed = this.closed;
            if (!closed) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Queueing content {}", content);
               }

               this.chunks.add(new Chunk(content, callback));
               l.signalAll();
            }
         }

         if (closed) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("InputStream closed, ignored content {}", content);
            }

            callback.failed(new AsynchronousCloseException());
         }

      }
   }

   public void onSuccess(Response response) {
      try (AutoLock.WithCondition l = this.lock.lock()) {
         if (!this.closed) {
            this.chunks.add(EOF);
         }

         l.signalAll();
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("End of content");
      }

   }

   public void onFailure(Response response, Throwable failure) {
      List<Callback> callbacks;
      try (AutoLock.WithCondition l = this.lock.lock()) {
         if (this.failure != null) {
            return;
         }

         this.failure = failure;
         callbacks = this.drain();
         l.signalAll();
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Content failure", failure);
      }

      callbacks.forEach((callback) -> callback.failed(failure));
   }

   public void onComplete(Result result) {
      Throwable failure = result.getFailure();
      List<Callback> callbacks = Collections.emptyList();

      try (AutoLock.WithCondition l = this.lock.lock()) {
         this.result = result;
         if (result.isFailed() && this.failure == null) {
            this.failure = failure;
            callbacks = this.drain();
         }

         this.responseLatch.countDown();
         this.resultLatch.countDown();
         l.signalAll();
      }

      if (LOG.isDebugEnabled()) {
         if (failure == null) {
            LOG.debug("Result success");
         } else {
            LOG.debug("Result failure", failure);
         }
      }

      callbacks.forEach((callback) -> callback.failed(failure));
   }

   public Response get(long timeout, TimeUnit unit) throws InterruptedException, TimeoutException, ExecutionException {
      boolean expired = !this.responseLatch.await(timeout, unit);
      if (expired) {
         throw new TimeoutException();
      } else {
         try (AutoLock ignored = this.lock.lock()) {
            if (this.response == null) {
               throw new ExecutionException(this.failure);
            } else {
               return this.response;
            }
         }
      }
   }

   public Result await(long timeout, TimeUnit unit) throws InterruptedException, TimeoutException {
      boolean expired = !this.resultLatch.await(timeout, unit);
      if (expired) {
         throw new TimeoutException();
      } else {
         try (AutoLock ignored = this.lock.lock()) {
            return this.result;
         }
      }
   }

   public InputStream getInputStream() {
      InputStream result = new Input();
      return this.stream.compareAndSet((Object)null, result) ? result : IO.getClosedStream();
   }

   private List drain() {
      List<Callback> callbacks = new ArrayList();

      try (AutoLock ignored = this.lock.lock()) {
         while(true) {
            Chunk chunk = (Chunk)this.chunks.peek();
            if (chunk == null || chunk == EOF) {
               return callbacks;
            }

            callbacks.add(chunk.callback);
            this.chunks.poll();
         }
      }
   }

   static {
      EOF = new Chunk(BufferUtil.EMPTY_BUFFER, Callback.NOOP);
   }

   private class Input extends InputStream {
      public int read() throws IOException {
         byte[] tmp = new byte[1];
         int read = this.read(tmp);
         return read < 0 ? read : tmp[0] & 255;
      }

      public int read(byte[] b, int offset, int length) throws IOException {
         try {
            Callback callback = null;

            int result;
            try (AutoLock.WithCondition l = InputStreamResponseListener.this.lock.lock()) {
               while(true) {
                  Chunk chunk = (Chunk)InputStreamResponseListener.this.chunks.peek();
                  if (chunk == InputStreamResponseListener.EOF) {
                     return -1;
                  }

                  if (chunk != null) {
                     ByteBuffer buffer = chunk.buffer;
                     result = Math.min(buffer.remaining(), length);
                     buffer.get(b, offset, result);
                     if (!buffer.hasRemaining()) {
                        callback = chunk.callback;
                        InputStreamResponseListener.this.chunks.poll();
                     }
                     break;
                  }

                  if (InputStreamResponseListener.this.failure != null) {
                     throw this.toIOException(InputStreamResponseListener.this.failure);
                  }

                  if (InputStreamResponseListener.this.closed) {
                     throw new AsynchronousCloseException();
                  }

                  l.await();
               }
            }

            if (callback != null) {
               callback.succeeded();
            }

            return result;
         } catch (InterruptedException var11) {
            throw new InterruptedIOException();
         }
      }

      private IOException toIOException(Throwable failure) {
         return failure instanceof IOException ? (IOException)failure : new IOException(failure);
      }

      public void close() throws IOException {
         List<Callback> callbacks;
         try (AutoLock.WithCondition l = InputStreamResponseListener.this.lock.lock()) {
            if (InputStreamResponseListener.this.closed) {
               return;
            }

            InputStreamResponseListener.this.closed = true;
            callbacks = InputStreamResponseListener.this.drain();
            l.signalAll();
         }

         if (InputStreamResponseListener.LOG.isDebugEnabled()) {
            InputStreamResponseListener.LOG.debug("InputStream close");
         }

         if (!callbacks.isEmpty()) {
            Throwable failure = new AsynchronousCloseException();
            callbacks.forEach((callback) -> callback.failed(failure));
         }

         super.close();
      }
   }

   private static class Chunk {
      private final ByteBuffer buffer;
      private final Callback callback;

      private Chunk(ByteBuffer buffer, Callback callback) {
         this.buffer = (ByteBuffer)Objects.requireNonNull(buffer);
         this.callback = (Callback)Objects.requireNonNull(callback);
      }
   }
}
