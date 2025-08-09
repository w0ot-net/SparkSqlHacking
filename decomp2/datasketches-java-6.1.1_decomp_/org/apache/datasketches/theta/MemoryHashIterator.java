package org.apache.datasketches.theta;

import org.apache.datasketches.memory.Memory;

class MemoryHashIterator implements HashIterator {
   private Memory mem;
   private int arrLongs;
   private long thetaLong;
   private long offsetBytes;
   private int index;
   private long hash;

   MemoryHashIterator(Memory mem, int arrLongs, long thetaLong) {
      this.mem = mem;
      this.arrLongs = arrLongs;
      this.thetaLong = thetaLong;
      this.offsetBytes = (long)(PreambleUtil.extractPreLongs(mem) << 3);
      this.index = -1;
      this.hash = 0L;
   }

   public long get() {
      return this.hash;
   }

   public boolean next() {
      while(true) {
         if (++this.index < this.arrLongs) {
            this.hash = this.mem.getLong(this.offsetBytes + (long)(this.index << 3));
            if (this.hash == 0L || this.hash >= this.thetaLong) {
               continue;
            }

            return true;
         }

         return false;
      }
   }
}
