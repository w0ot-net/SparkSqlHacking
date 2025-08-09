package org.apache.zookeeper.server.quorum;

public class BufferStats {
   public static final int INIT_VALUE = -1;
   private int lastBufferSize = -1;
   private int minBufferSize = -1;
   private int maxBufferSize = -1;

   public synchronized int getLastBufferSize() {
      return this.lastBufferSize;
   }

   public synchronized void setLastBufferSize(int value) {
      this.lastBufferSize = value;
      if (this.minBufferSize == -1 || value < this.minBufferSize) {
         this.minBufferSize = value;
      }

      if (value > this.maxBufferSize) {
         this.maxBufferSize = value;
      }

   }

   public synchronized int getMinBufferSize() {
      return this.minBufferSize;
   }

   public synchronized int getMaxBufferSize() {
      return this.maxBufferSize;
   }

   public synchronized void reset() {
      this.lastBufferSize = -1;
      this.minBufferSize = -1;
      this.maxBufferSize = -1;
   }

   public synchronized String toString() {
      return String.format("%d/%d/%d", this.lastBufferSize, this.minBufferSize, this.maxBufferSize);
   }
}
