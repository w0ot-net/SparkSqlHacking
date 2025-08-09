package org.glassfish.jersey.internal.util.collection;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.glassfish.jersey.internal.LocalizationMessages;

public final class ByteBufferInputStream extends NonBlockingInputStream {
   private static final ByteBuffer EOF = ByteBuffer.wrap(new byte[0]);
   private boolean eof = false;
   private ByteBuffer current = null;
   private final BlockingQueue buffers = new LinkedTransferQueue();
   private final AtomicReference queueStatus = new AtomicReference((Object)null);
   private final AtomicBoolean closed = new AtomicBoolean(false);

   private boolean fetchChunk(boolean block) throws InterruptedException {
      if (this.eof) {
         return false;
      } else {
         do {
            if (this.closed.get()) {
               this.current = EOF;
               break;
            }

            this.current = block ? (ByteBuffer)this.buffers.take() : (ByteBuffer)this.buffers.poll();
         } while(this.current != null && this.current != EOF && !this.current.hasRemaining());

         this.eof = this.current == EOF;
         return !this.eof;
      }
   }

   private void checkNotClosed() throws IOException {
      if (this.closed.get()) {
         throw new IOException(LocalizationMessages.INPUT_STREAM_CLOSED());
      }
   }

   private void checkThrowable() throws IOException {
      Object o = this.queueStatus.get();
      if (o != null && o != EOF && this.queueStatus.compareAndSet(o, EOF)) {
         try {
            throw new IOException((Throwable)o);
         } finally {
            this.close();
         }
      }
   }

   public int available() throws IOException {
      if (!this.eof && !this.closed.get()) {
         int available = 0;
         if (this.current != null && this.current.hasRemaining()) {
            available = this.current.remaining();
         }

         for(ByteBuffer buffer : this.buffers) {
            if (buffer == EOF) {
               break;
            }

            available += buffer.remaining();
         }

         this.checkThrowable();
         return this.closed.get() ? 0 : available;
      } else {
         this.checkThrowable();
         return 0;
      }
   }

   public int read() throws IOException {
      return this.tryRead(true);
   }

   public int read(byte[] b, int off, int len) throws IOException {
      return this.tryRead(b, off, len, true);
   }

   public int tryRead() throws IOException {
      return this.tryRead(false);
   }

   public int tryRead(byte[] b) throws IOException {
      return this.tryRead(b, 0, b.length);
   }

   public int tryRead(byte[] b, int off, int len) throws IOException {
      return this.tryRead(b, off, len, false);
   }

   public void close() throws IOException {
      if (this.closed.compareAndSet(false, true)) {
         this.closeQueue();
         this.buffers.clear();
      }

      this.checkThrowable();
   }

   public boolean put(ByteBuffer src) throws InterruptedException {
      if (this.queueStatus.get() == null) {
         this.buffers.put(src);
         return true;
      } else {
         return false;
      }
   }

   public void closeQueue() {
      if (this.queueStatus.compareAndSet((Object)null, EOF)) {
         try {
            this.buffers.put(EOF);
         } catch (InterruptedException var2) {
            Thread.currentThread().interrupt();
         }
      }

   }

   public void closeQueue(Throwable throwable) {
      if (this.queueStatus.compareAndSet((Object)null, throwable)) {
         try {
            this.buffers.put(EOF);
         } catch (InterruptedException var3) {
            Thread.currentThread().interrupt();
         }
      }

   }

   private int tryRead(byte[] b, int off, int len, boolean block) throws IOException {
      this.checkThrowable();
      this.checkNotClosed();
      if (b == null) {
         throw new NullPointerException();
      } else if (off >= 0 && len >= 0 && len <= b.length - off) {
         if (len == 0) {
            return 0;
         } else if (this.eof) {
            return -1;
         } else {
            int i = 0;

            while(i < len) {
               if (this.current != null && this.current.hasRemaining()) {
                  int available = this.current.remaining();
                  if (available >= len - i) {
                     this.current.get(b, off + i, len - i);
                     return len;
                  }

                  this.current.get(b, off + i, available);
                  i += available;
               } else {
                  try {
                     if (!this.fetchChunk(block) || this.current == null) {
                        break;
                     }
                  } catch (InterruptedException e) {
                     Thread.currentThread().interrupt();
                     if (block) {
                        throw new IOException(e);
                     }
                  }
               }
            }

            return i == 0 && this.eof ? -1 : i;
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   private int tryRead(boolean block) throws IOException {
      this.checkThrowable();
      this.checkNotClosed();
      if (this.eof) {
         return -1;
      } else if (this.current != null && this.current.hasRemaining()) {
         return this.current.get() & 255;
      } else {
         try {
            if (this.fetchChunk(block) && this.current != null) {
               return this.current.get() & 255;
            }

            if (block) {
               return -1;
            }
         } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            if (block) {
               throw new IOException(e);
            }
         }

         return this.eof ? -1 : Integer.MIN_VALUE;
      }
   }
}
