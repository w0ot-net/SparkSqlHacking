package org.sparkproject.jetty.client.util;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.IO;

public class PathRequestContent extends AbstractRequestContent {
   private static final Logger LOG = LoggerFactory.getLogger(PathRequestContent.class);
   private final Path filePath;
   private final long fileSize;
   private final int bufferSize;
   private ByteBufferPool bufferPool;
   private boolean useDirectByteBuffers;

   public PathRequestContent(Path filePath) throws IOException {
      this(filePath, 4096);
   }

   public PathRequestContent(Path filePath, int bufferSize) throws IOException {
      this("application/octet-stream", filePath, bufferSize);
   }

   public PathRequestContent(String contentType, Path filePath) throws IOException {
      this(contentType, filePath, 4096);
   }

   public PathRequestContent(String contentType, Path filePath, int bufferSize) throws IOException {
      super(contentType);
      this.useDirectByteBuffers = true;
      if (!Files.isRegularFile(filePath, new LinkOption[0])) {
         throw new NoSuchFileException(filePath.toString());
      } else if (!Files.isReadable(filePath)) {
         throw new AccessDeniedException(filePath.toString());
      } else {
         this.filePath = filePath;
         this.fileSize = Files.size(filePath);
         this.bufferSize = bufferSize;
      }
   }

   public long getLength() {
      return this.fileSize;
   }

   public boolean isReproducible() {
      return true;
   }

   public ByteBufferPool getByteBufferPool() {
      return this.bufferPool;
   }

   public void setByteBufferPool(ByteBufferPool byteBufferPool) {
      this.bufferPool = byteBufferPool;
   }

   public boolean isUseDirectByteBuffers() {
      return this.useDirectByteBuffers;
   }

   public void setUseDirectByteBuffers(boolean useDirectByteBuffers) {
      this.useDirectByteBuffers = useDirectByteBuffers;
   }

   protected Request.Content.Subscription newSubscription(Request.Content.Consumer consumer, boolean emitInitialContent) {
      return new SubscriptionImpl(consumer, emitInitialContent);
   }

   private class SubscriptionImpl extends AbstractRequestContent.AbstractSubscription {
      private ReadableByteChannel channel;
      private long readTotal;

      private SubscriptionImpl(Request.Content.Consumer consumer, boolean emitInitialContent) {
         super(consumer, emitInitialContent);
      }

      protected boolean produceContent(AbstractRequestContent.Producer producer) throws IOException {
         if (this.channel == null) {
            this.channel = Files.newByteChannel(PathRequestContent.this.filePath, StandardOpenOption.READ);
            if (PathRequestContent.LOG.isDebugEnabled()) {
               PathRequestContent.LOG.debug("Opened file {}", PathRequestContent.this.filePath);
            }
         }

         ByteBuffer buffer = PathRequestContent.this.bufferPool == null ? BufferUtil.allocate(PathRequestContent.this.bufferSize, PathRequestContent.this.isUseDirectByteBuffers()) : PathRequestContent.this.bufferPool.acquire(PathRequestContent.this.bufferSize, PathRequestContent.this.isUseDirectByteBuffers());
         BufferUtil.clearToFill(buffer);
         int read = this.channel.read(buffer);
         BufferUtil.flipToFlush(buffer, 0);
         if (PathRequestContent.LOG.isDebugEnabled()) {
            PathRequestContent.LOG.debug("Read {} bytes from {}", read, PathRequestContent.this.filePath);
         }

         if (!this.channel.isOpen() && read < 0) {
            throw new EOFException("EOF reached for " + String.valueOf(PathRequestContent.this.filePath));
         } else {
            if (read > 0) {
               this.readTotal += (long)read;
            }

            boolean last = this.readTotal == PathRequestContent.this.fileSize;
            if (last) {
               IO.close((Closeable)this.channel);
            }

            return producer.produce(buffer, last, Callback.from((Runnable)(() -> this.release(buffer))));
         }
      }

      private void release(ByteBuffer buffer) {
         if (PathRequestContent.this.bufferPool != null) {
            PathRequestContent.this.bufferPool.release(buffer);
         }

      }

      public void fail(Throwable failure) {
         super.fail(failure);
         IO.close((Closeable)this.channel);
      }
   }
}
