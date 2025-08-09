package org.sparkproject.jetty.util.compression;

import java.util.zip.Deflater;
import org.sparkproject.jetty.util.component.Container;
import org.sparkproject.jetty.util.thread.ThreadPool;

public class DeflaterPool extends CompressionPool {
   private final int compressionLevel;
   private final boolean nowrap;

   public DeflaterPool(int capacity, int compressionLevel, boolean nowrap) {
      super(capacity);
      this.compressionLevel = compressionLevel;
      this.nowrap = nowrap;
   }

   protected Deflater newPooled() {
      return new Deflater(this.compressionLevel, this.nowrap);
   }

   protected void end(Deflater deflater) {
      deflater.end();
   }

   protected void reset(Deflater deflater) {
      deflater.reset();
   }

   public static DeflaterPool ensurePool(Container container) {
      DeflaterPool pool = (DeflaterPool)container.getBean(DeflaterPool.class);
      if (pool != null) {
         return pool;
      } else {
         int capacity = 1024;
         ThreadPool.SizedThreadPool threadPool = (ThreadPool.SizedThreadPool)container.getBean(ThreadPool.SizedThreadPool.class);
         if (threadPool != null) {
            capacity = threadPool.getMaxThreads();
         }

         pool = new DeflaterPool(capacity, -1, true);
         container.addBean(pool, true);
         return pool;
      }
   }
}
