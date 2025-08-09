package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.client.KubernetesClientException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

public class ExecWatchInputStream extends InputStream {
   private static final int BUFFER_SIZE = 32768;
   private final LinkedList buffers;
   private boolean complete;
   private boolean closed;
   private Throwable failed;
   private ByteBuffer currentBuffer;
   private final Runnable request;
   private final int bufferSize;

   public ExecWatchInputStream(Runnable request) {
      this(request, 32768);
   }

   public ExecWatchInputStream(Runnable request, int bufferSize) {
      this.buffers = new LinkedList();
      this.request = request;
      this.bufferSize = bufferSize;
   }

   void onExit(Integer exitCode, Throwable t) {
      synchronized(this.buffers) {
         if (!this.complete) {
            this.complete = true;
            if (t != null) {
               this.failed = t;
            } else if (exitCode != null && exitCode != 0) {
               this.failed = new KubernetesClientException("process exited with a non-zero exit code: " + exitCode);
            }

            this.buffers.notifyAll();
         }
      }
   }

   void consume(List value) {
      synchronized(this.buffers) {
         if (this.closed) {
            this.request.run();
         } else {
            assert !this.complete || this.failed == null;

            this.buffers.addAll(value);
            this.buffers.notifyAll();
            if ((this.currentBuffer != null ? this.currentBuffer.remaining() : 0) + this.buffers.stream().mapToInt(Buffer::remaining).sum() < this.bufferSize) {
               this.request.run();
            }

         }
      }
   }

   private ByteBuffer current() throws IOException {
      synchronized(this.buffers) {
         while(this.currentBuffer == null || !this.currentBuffer.hasRemaining()) {
            if (this.closed) {
               throw new IOException("closed", this.failed);
            }

            if (this.buffers.isEmpty()) {
               if (this.complete) {
                  if (this.failed != null) {
                     throw new IOException("closed", this.failed);
                  }

                  return null;
               }

               this.requestMoreIfNeeded();
            }

            this.currentBuffer = (ByteBuffer)this.buffers.poll();
            if (this.currentBuffer == null && !this.complete) {
               try {
                  this.buffers.wait();
               } catch (InterruptedException var4) {
                  Thread.currentThread().interrupt();
                  throw new InterruptedIOException();
               }
            }
         }

         return this.currentBuffer;
      }
   }

   public int read(byte[] bytes, int off, int len) throws IOException {
      ByteBuffer buffer = this.current();
      if (buffer == null) {
         return -1;
      } else {
         int read = Math.min(buffer.remaining(), len);

         assert read > 0 && read <= buffer.remaining();

         buffer.get(bytes, off, read);
         return read;
      }
   }

   public int read() throws IOException {
      byte[] single = new byte[1];
      return this.read(single) == -1 ? -1 : single[0] & 255;
   }

   public void close() throws IOException {
      synchronized(this.buffers) {
         if (!this.closed) {
            this.closed = true;
            this.requestMoreIfNeeded();
            this.buffers.clear();
            this.buffers.notifyAll();
         }
      }
   }

   private void requestMoreIfNeeded() {
      if (this.currentBuffer != null) {
         this.currentBuffer = null;
         this.request.run();
      }

   }
}
