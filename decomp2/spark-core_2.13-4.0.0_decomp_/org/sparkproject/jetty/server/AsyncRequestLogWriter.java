package org.sparkproject.jetty.server;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.BlockingArrayQueue;

public class AsyncRequestLogWriter extends RequestLogWriter {
   private static final Logger LOG = LoggerFactory.getLogger(AsyncRequestLogWriter.class);
   private final BlockingQueue _queue;
   private transient WriterThread _thread;
   private boolean _warnedFull;

   public AsyncRequestLogWriter() {
      this((String)null, (BlockingQueue)null);
   }

   public AsyncRequestLogWriter(String filename) {
      this(filename, (BlockingQueue)null);
   }

   public AsyncRequestLogWriter(String filename, BlockingQueue queue) {
      super(filename);
      if (queue == null) {
         queue = new BlockingArrayQueue(1024);
      }

      this._queue = queue;
   }

   protected void doStart() throws Exception {
      super.doStart();
      this._thread = new WriterThread();
      this._thread.start();
   }

   protected void doStop() throws Exception {
      this._thread.interrupt();
      this._thread.join();
      super.doStop();
      this._thread = null;
   }

   public void write(String log) throws IOException {
      if (!this._queue.offer(log)) {
         if (this._warnedFull) {
            LOG.warn("Log Queue overflow");
         }

         this._warnedFull = true;
      }

   }

   private class WriterThread extends Thread {
      WriterThread() {
         int var10001 = AsyncRequestLogWriter.this.hashCode();
         this.setName("AsyncRequestLogWriter@" + Integer.toString(var10001, 16));
      }

      public void run() {
         while(AsyncRequestLogWriter.this.isRunning()) {
            try {
               String log = (String)AsyncRequestLogWriter.this._queue.poll(10L, TimeUnit.SECONDS);
               if (log != null) {
                  AsyncRequestLogWriter.super.write(log);
               }

               while(!AsyncRequestLogWriter.this._queue.isEmpty()) {
                  log = (String)AsyncRequestLogWriter.this._queue.poll();
                  if (log != null) {
                     AsyncRequestLogWriter.super.write(log);
                  }
               }
            } catch (InterruptedException e) {
               AsyncRequestLogWriter.LOG.trace("IGNORED", e);
            } catch (Throwable t) {
               AsyncRequestLogWriter.LOG.warn("Failed to write log", t);
            }
         }

      }
   }
}
