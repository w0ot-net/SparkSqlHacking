package org.apache.commons.io.output;

import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.commons.io.input.QueueInputStream;

public class QueueOutputStream extends OutputStream {
   private final BlockingQueue blockingQueue;

   public QueueOutputStream() {
      this(new LinkedBlockingQueue());
   }

   public QueueOutputStream(BlockingQueue blockingQueue) {
      this.blockingQueue = (BlockingQueue)Objects.requireNonNull(blockingQueue, "blockingQueue");
   }

   public QueueInputStream newQueueInputStream() {
      return QueueInputStream.builder().setBlockingQueue(this.blockingQueue).get();
   }

   public void write(int b) throws InterruptedIOException {
      try {
         this.blockingQueue.put(255 & b);
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         InterruptedIOException interruptedIoException = new InterruptedIOException();
         interruptedIoException.initCause(e);
         throw interruptedIoException;
      }
   }
}
