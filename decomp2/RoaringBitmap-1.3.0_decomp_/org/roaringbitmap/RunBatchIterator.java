package org.roaringbitmap;

public final class RunBatchIterator implements ContainerBatchIterator {
   private RunContainer runs;
   private int run = 0;
   private int cursor = 0;

   public RunBatchIterator(RunContainer runs) {
      this.wrap(runs);
   }

   public int next(int key, int[] buffer, int offset) {
      int consumed = 0;

      do {
         int runStart = this.runs.getValue(this.run);
         int runLength = this.runs.getLength(this.run);
         int chunkStart = runStart + this.cursor;
         int usableBufferLength = buffer.length - offset - consumed;
         int chunkEnd = chunkStart + Math.min(runLength - this.cursor, usableBufferLength - 1);
         int chunk = chunkEnd - chunkStart + 1;

         for(int i = 0; i < chunk; ++i) {
            buffer[offset + consumed + i] = key + chunkStart + i;
         }

         consumed += chunk;
         if (runStart + runLength == chunkEnd) {
            ++this.run;
            this.cursor = 0;
         } else {
            this.cursor += chunk;
         }
      } while(offset + consumed < buffer.length && this.run != this.runs.numberOfRuns());

      return consumed;
   }

   public boolean hasNext() {
      return this.run < this.runs.numberOfRuns();
   }

   public ContainerBatchIterator clone() {
      try {
         return (ContainerBatchIterator)super.clone();
      } catch (CloneNotSupportedException e) {
         throw new IllegalStateException(e);
      }
   }

   public void releaseContainer() {
      this.runs = null;
   }

   public void advanceIfNeeded(char target) {
      while(true) {
         int runStart = this.runs.getValue(this.run);
         int runLength = this.runs.getLength(this.run);
         if (runStart > target) {
            this.cursor = 0;
         } else {
            int offset = target - runStart;
            if (offset <= runLength) {
               this.cursor = offset;
            } else {
               ++this.run;
               this.cursor = 0;
               if (this.run != this.runs.numberOfRuns()) {
                  continue;
               }
            }
         }

         return;
      }
   }

   void wrap(RunContainer runs) {
      this.runs = runs;
      this.run = 0;
      this.cursor = 0;
   }
}
