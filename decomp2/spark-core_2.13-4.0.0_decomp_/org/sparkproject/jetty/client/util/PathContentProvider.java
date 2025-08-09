package org.sparkproject.jetty.client.util;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.util.BufferUtil;

/** @deprecated */
@Deprecated
public class PathContentProvider extends AbstractTypedContentProvider {
   private static final Logger LOG = LoggerFactory.getLogger(PathContentProvider.class);
   private final Path filePath;
   private final long fileSize;
   private final int bufferSize;
   private ByteBufferPool bufferPool;
   private boolean useDirectByteBuffers;

   public PathContentProvider(Path filePath) throws IOException {
      this(filePath, 4096);
   }

   public PathContentProvider(Path filePath, int bufferSize) throws IOException {
      this("application/octet-stream", filePath, bufferSize);
   }

   public PathContentProvider(String contentType, Path filePath) throws IOException {
      this(contentType, filePath, 4096);
   }

   public PathContentProvider(String contentType, Path filePath, int bufferSize) throws IOException {
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

   public Iterator iterator() {
      return new PathIterator();
   }

   private class PathIterator implements Iterator, Closeable {
      private ByteBuffer buffer;
      private SeekableByteChannel channel;
      private long position;

      public boolean hasNext() {
         return this.position < PathContentProvider.this.getLength();
      }

      public ByteBuffer next() {
         try {
            if (this.channel == null) {
               this.buffer = PathContentProvider.this.bufferPool == null ? BufferUtil.allocate(PathContentProvider.this.bufferSize, PathContentProvider.this.isUseDirectByteBuffers()) : PathContentProvider.this.bufferPool.acquire(PathContentProvider.this.bufferSize, PathContentProvider.this.isUseDirectByteBuffers());
               this.channel = Files.newByteChannel(PathContentProvider.this.filePath, StandardOpenOption.READ);
               if (PathContentProvider.LOG.isDebugEnabled()) {
                  PathContentProvider.LOG.debug("Opened file {}", PathContentProvider.this.filePath);
               }
            }

            this.buffer.clear();
            int read = this.channel.read(this.buffer);
            if (read < 0) {
               throw new NoSuchElementException();
            } else {
               if (PathContentProvider.LOG.isDebugEnabled()) {
                  PathContentProvider.LOG.debug("Read {} bytes from {}", read, PathContentProvider.this.filePath);
               }

               this.position += (long)read;
               this.buffer.flip();
               return this.buffer;
            }
         } catch (NoSuchElementException x) {
            this.close();
            throw x;
         } catch (Throwable x) {
            this.close();
            throw (NoSuchElementException)(new NoSuchElementException()).initCause(x);
         }
      }

      public void close() {
         try {
            if (PathContentProvider.this.bufferPool != null && this.buffer != null) {
               PathContentProvider.this.bufferPool.release(this.buffer);
            }

            if (this.channel != null) {
               this.channel.close();
            }
         } catch (Throwable x) {
            PathContentProvider.LOG.trace("IGNORED", x);
         }

      }
   }
}
