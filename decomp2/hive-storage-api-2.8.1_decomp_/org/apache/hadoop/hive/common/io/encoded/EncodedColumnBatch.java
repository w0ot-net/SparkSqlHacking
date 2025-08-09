package org.apache.hadoop.hive.common.io.encoded;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncodedColumnBatch {
   protected Object batchKey;
   protected ColumnStreamData[][] columnData;
   protected boolean[] hasData;
   private static final Logger LOG = LoggerFactory.getLogger(EncodedColumnBatch.class);

   public void reset() {
      if (this.hasData != null) {
         Arrays.fill(this.hasData, false);
      }

      if (this.columnData != null) {
         for(int i = 0; i < this.columnData.length; ++i) {
            if (this.columnData[i] != null) {
               for(int j = 0; j < this.columnData[i].length; ++j) {
                  this.columnData[i][j] = null;
               }
            }
         }

      }
   }

   public void initColumn(int colIx, int streamCount) {
      this.hasData[colIx] = true;
      if (this.columnData[colIx] == null || this.columnData[colIx].length != streamCount) {
         this.columnData[colIx] = new ColumnStreamData[streamCount];
      }

   }

   public void setStreamData(int colIx, int streamIx, ColumnStreamData csd) {
      assert this.hasData[colIx];

      this.columnData[colIx][streamIx] = csd;
   }

   public Object getBatchKey() {
      return this.batchKey;
   }

   public ColumnStreamData[] getColumnData(int colIx) {
      if (!this.hasData[colIx]) {
         throw new AssertionError("No data for column " + colIx);
      } else {
         return this.columnData[colIx];
      }
   }

   public int getTotalColCount() {
      return this.columnData.length;
   }

   protected void resetColumnArrays(int columnCount) {
      if (this.hasData != null && columnCount == this.hasData.length) {
         Arrays.fill(this.hasData, false);
      } else {
         this.hasData = new boolean[columnCount];
      }

      ColumnStreamData[][] columnData = new ColumnStreamData[columnCount][];
      if (this.columnData != null) {
         for(int i = 0; i < Math.min(columnData.length, this.columnData.length); ++i) {
            columnData[i] = this.columnData[i];
         }
      }

      this.columnData = columnData;
   }

   public boolean hasData(int colIx) {
      return this.hasData[colIx];
   }

   public static class ColumnStreamData {
      private List cacheBuffers;
      private int indexBaseOffset = 0;
      private AtomicInteger refCount = new AtomicInteger(0);

      public void reset() {
         this.cacheBuffers.clear();
         this.refCount.set(0);
         this.indexBaseOffset = 0;
      }

      public void incRef() {
         this.refCount.incrementAndGet();
      }

      public int decRef() {
         int i = this.refCount.decrementAndGet();

         assert i >= 0;

         return i;
      }

      public List getCacheBuffers() {
         return this.cacheBuffers;
      }

      public void setCacheBuffers(List cacheBuffers) {
         this.cacheBuffers = cacheBuffers;
      }

      public int getIndexBaseOffset() {
         return this.indexBaseOffset;
      }

      public void setIndexBaseOffset(int indexBaseOffset) {
         this.indexBaseOffset = indexBaseOffset;
      }

      public String toString() {
         StringBuilder sb = new StringBuilder();
         if (this.cacheBuffers != null) {
            Iterator<MemoryBuffer> iter = this.cacheBuffers.iterator();

            while(iter.hasNext()) {
               MemoryBuffer mb = (MemoryBuffer)iter.next();
               sb.append(mb.getClass().getSimpleName());
               sb.append(" with ");
               sb.append(mb.getByteBufferRaw().remaining());
               sb.append(" bytes");
               if (iter.hasNext()) {
                  sb.append(", ");
               }
            }
         }

         return "ColumnStreamData [cacheBuffers=[" + sb.toString() + "], indexBaseOffset=" + this.indexBaseOffset + "]";
      }
   }
}
