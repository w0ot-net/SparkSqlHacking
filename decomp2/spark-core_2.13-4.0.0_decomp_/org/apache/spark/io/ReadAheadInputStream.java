package org.apache.spark.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.concurrent.GuardedBy;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.ERROR.;
import org.apache.spark.util.ThreadUtils;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Throwables;

public class ReadAheadInputStream extends InputStream {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(ReadAheadInputStream.class);
   private ReentrantLock stateChangeLock = new ReentrantLock();
   @GuardedBy("stateChangeLock")
   private ByteBuffer activeBuffer;
   @GuardedBy("stateChangeLock")
   private ByteBuffer readAheadBuffer;
   @GuardedBy("stateChangeLock")
   private boolean endOfStream;
   @GuardedBy("stateChangeLock")
   private boolean readInProgress;
   @GuardedBy("stateChangeLock")
   private boolean readAborted;
   @GuardedBy("stateChangeLock")
   private Throwable readException;
   @GuardedBy("stateChangeLock")
   private boolean isClosed;
   @GuardedBy("stateChangeLock")
   private boolean isUnderlyingInputStreamBeingClosed;
   @GuardedBy("stateChangeLock")
   private boolean isReading;
   private AtomicBoolean isWaiting = new AtomicBoolean(false);
   private final InputStream underlyingInputStream;
   private final ExecutorService executorService = ThreadUtils.newDaemonSingleThreadExecutor("read-ahead");
   private final Condition asyncReadComplete;

   public ReadAheadInputStream(InputStream inputStream, int bufferSizeInBytes) {
      this.asyncReadComplete = this.stateChangeLock.newCondition();
      Preconditions.checkArgument(bufferSizeInBytes > 0, "bufferSizeInBytes should be greater than 0, but the value is " + bufferSizeInBytes);
      this.activeBuffer = ByteBuffer.allocate(bufferSizeInBytes);
      this.readAheadBuffer = ByteBuffer.allocate(bufferSizeInBytes);
      this.underlyingInputStream = inputStream;
      this.activeBuffer.flip();
      this.readAheadBuffer.flip();
   }

   private boolean isEndOfStream() {
      return !this.activeBuffer.hasRemaining() && !this.readAheadBuffer.hasRemaining() && this.endOfStream;
   }

   private void checkReadException() throws IOException {
      if (this.readAborted) {
         Throwables.throwIfInstanceOf(this.readException, IOException.class);
         Throwables.throwIfUnchecked(this.readException);
         throw new IOException(this.readException);
      }
   }

   private void readAsync() throws IOException {
      this.stateChangeLock.lock();
      byte[] arr = this.readAheadBuffer.array();

      label44: {
         try {
            if (!this.endOfStream && !this.readInProgress) {
               this.checkReadException();
               this.readAheadBuffer.position(0);
               this.readAheadBuffer.flip();
               this.readInProgress = true;
               break label44;
            }
         } finally {
            this.stateChangeLock.unlock();
         }

         return;
      }

      this.executorService.execute(() -> {
         this.stateChangeLock.lock();

         try {
            if (this.isClosed) {
               this.readInProgress = false;
               return;
            }

            this.isReading = true;
         } finally {
            this.stateChangeLock.unlock();
         }

         int read = 0;
         int off = 0;
         int len = arr.length;
         Throwable exception = null;

         try {
            do {
               read = this.underlyingInputStream.read(arr, off, len);
               if (read <= 0) {
                  break;
               }

               off += read;
               len -= read;
            } while(len > 0 && !this.isWaiting.get());
         } catch (Throwable ex) {
            exception = ex;
            if (ex instanceof Error error) {
               throw error;
            }
         } finally {
            this.stateChangeLock.lock();
            this.readAheadBuffer.limit(off);
            if (read >= 0 && !(exception instanceof EOFException)) {
               if (exception != null) {
                  this.readAborted = true;
                  this.readException = exception;
               }
            } else {
               this.endOfStream = true;
            }

            this.readInProgress = false;
            this.signalAsyncReadComplete();
            this.stateChangeLock.unlock();
            this.closeUnderlyingInputStreamIfNecessary();
         }

      });
   }

   private void closeUnderlyingInputStreamIfNecessary() {
      boolean needToCloseUnderlyingInputStream = false;
      this.stateChangeLock.lock();

      try {
         this.isReading = false;
         if (this.isClosed && !this.isUnderlyingInputStreamBeingClosed) {
            needToCloseUnderlyingInputStream = true;
         }
      } finally {
         this.stateChangeLock.unlock();
      }

      if (needToCloseUnderlyingInputStream) {
         try {
            this.underlyingInputStream.close();
         } catch (IOException e) {
            logger.warn("{}", e, new MDC[]{MDC.of(.MODULE$, e.getMessage())});
         }
      }

   }

   private void signalAsyncReadComplete() {
      this.stateChangeLock.lock();

      try {
         this.asyncReadComplete.signalAll();
      } finally {
         this.stateChangeLock.unlock();
      }

   }

   private void waitForAsyncReadComplete() throws IOException {
      this.stateChangeLock.lock();
      this.isWaiting.set(true);

      try {
         while(this.readInProgress) {
            this.asyncReadComplete.await();
         }
      } catch (InterruptedException e) {
         InterruptedIOException iio = new InterruptedIOException(e.getMessage());
         iio.initCause(e);
         throw iio;
      } finally {
         this.isWaiting.set(false);
         this.stateChangeLock.unlock();
      }

      this.checkReadException();
   }

   public int read() throws IOException {
      if (this.activeBuffer.hasRemaining()) {
         return this.activeBuffer.get() & 255;
      } else {
         byte[] oneByteArray = new byte[1];
         return this.read(oneByteArray, 0, 1) == -1 ? -1 : oneByteArray[0] & 255;
      }
   }

   public int read(byte[] b, int offset, int len) throws IOException {
      if (offset >= 0 && len >= 0 && len <= b.length - offset) {
         if (len == 0) {
            return 0;
         } else {
            if (!this.activeBuffer.hasRemaining()) {
               this.stateChangeLock.lock();

               try {
                  this.waitForAsyncReadComplete();
                  if (!this.readAheadBuffer.hasRemaining()) {
                     this.readAsync();
                     this.waitForAsyncReadComplete();
                     if (this.isEndOfStream()) {
                        byte var4 = -1;
                        return var4;
                     }
                  }

                  this.swapBuffers();
                  this.readAsync();
               } finally {
                  this.stateChangeLock.unlock();
               }
            }

            len = Math.min(len, this.activeBuffer.remaining());
            this.activeBuffer.get(b, offset, len);
            return len;
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   private void swapBuffers() {
      ByteBuffer temp = this.activeBuffer;
      this.activeBuffer = this.readAheadBuffer;
      this.readAheadBuffer = temp;
   }

   public int available() throws IOException {
      this.stateChangeLock.lock();

      int var1;
      try {
         var1 = (int)Math.min(2147483647L, (long)this.activeBuffer.remaining() + (long)this.readAheadBuffer.remaining());
      } finally {
         this.stateChangeLock.unlock();
      }

      return var1;
   }

   public long skip(long n) throws IOException {
      if (n <= 0L) {
         return 0L;
      } else if (n <= (long)this.activeBuffer.remaining()) {
         this.activeBuffer.position((int)n + this.activeBuffer.position());
         return n;
      } else {
         this.stateChangeLock.lock();

         long skipped;
         try {
            skipped = this.skipInternal(n);
         } finally {
            this.stateChangeLock.unlock();
         }

         return skipped;
      }
   }

   private long skipInternal(long n) throws IOException {
      assert this.stateChangeLock.isLocked();

      this.waitForAsyncReadComplete();
      if (this.isEndOfStream()) {
         return 0L;
      } else if ((long)this.available() >= n) {
         int toSkip = (int)n;
         toSkip -= this.activeBuffer.remaining();

         assert toSkip > 0;

         this.activeBuffer.position(0);
         this.activeBuffer.flip();
         this.readAheadBuffer.position(toSkip + this.readAheadBuffer.position());
         this.swapBuffers();
         this.readAsync();
         return n;
      } else {
         int skippedBytes = this.available();
         long toSkip = n - (long)skippedBytes;
         this.activeBuffer.position(0);
         this.activeBuffer.flip();
         this.readAheadBuffer.position(0);
         this.readAheadBuffer.flip();
         long skippedFromInputStream = this.underlyingInputStream.skip(toSkip);
         this.readAsync();
         return (long)skippedBytes + skippedFromInputStream;
      }
   }

   public void close() throws IOException {
      boolean isSafeToCloseUnderlyingInputStream = false;
      this.stateChangeLock.lock();

      try {
         if (this.isClosed) {
            return;
         }

         this.isClosed = true;
         if (!this.isReading) {
            isSafeToCloseUnderlyingInputStream = true;
            this.isUnderlyingInputStreamBeingClosed = true;
         }
      } finally {
         this.stateChangeLock.unlock();
      }

      try {
         this.executorService.shutdownNow();
         this.executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
         InterruptedIOException iio = new InterruptedIOException(e.getMessage());
         iio.initCause(e);
         throw iio;
      } finally {
         if (isSafeToCloseUnderlyingInputStream) {
            this.underlyingInputStream.close();
         }

      }

   }
}
