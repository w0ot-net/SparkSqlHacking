package org.apache.avro.io;

import java.io.IOException;
import java.util.Arrays;
import org.apache.avro.AvroTypeException;

public abstract class ParsingEncoder extends Encoder {
   private long[] counts = new long[10];
   protected int pos = -1;

   public void setItemCount(long itemCount) throws IOException {
      if (this.counts[this.pos] != 0L) {
         long var10002 = this.counts[this.pos];
         throw new AvroTypeException("Incorrect number of items written. " + var10002 + " more required.");
      } else {
         this.counts[this.pos] = itemCount;
      }
   }

   public void startItem() throws IOException {
      int var10002 = this.counts[this.pos]--;
   }

   protected final void push() {
      if (++this.pos == this.counts.length) {
         this.counts = Arrays.copyOf(this.counts, this.pos + 10);
      }

      this.counts[this.pos] = 0L;
   }

   protected final void pop() {
      if (this.counts[this.pos] != 0L) {
         long var10002 = this.counts[this.pos];
         throw new AvroTypeException("Incorrect number of items written. " + var10002 + " more required.");
      } else {
         --this.pos;
      }
   }

   protected final int depth() {
      return this.pos;
   }
}
