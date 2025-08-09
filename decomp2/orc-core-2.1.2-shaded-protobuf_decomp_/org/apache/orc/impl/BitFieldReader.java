package org.apache.orc.impl;

import java.io.EOFException;
import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.io.filter.FilterContext;

public final class BitFieldReader {
   private final RunLengthByteReader input;
   private int current;
   private byte currentIdx = 8;

   public BitFieldReader(InStream input) {
      this.input = new RunLengthByteReader(input);
   }

   private void readByte() throws IOException {
      if (this.input.hasNext()) {
         this.current = 255 & this.input.next();
         this.currentIdx = 0;
      } else {
         throw new EOFException("Read past end of bit field from " + String.valueOf(this));
      }
   }

   public int next() throws IOException {
      if (this.currentIdx > 7) {
         this.readByte();
      }

      ++this.currentIdx;
      return this.current >>> 8 - this.currentIdx & 1;
   }

   public void nextVector(LongColumnVector previous, FilterContext filterContext, long previousLen) throws IOException {
      previous.isRepeating = false;
      int previousIdx = 0;
      if (previous.noNulls) {
         for(int i = 0; i != filterContext.getSelectedSize(); ++i) {
            int idx = filterContext.getSelected()[i];
            if (idx - previousIdx > 0) {
               this.skip((long)(idx - previousIdx));
            }

            previous.vector[idx] = (long)this.next();
            previousIdx = idx + 1;
         }

         this.skip(previousLen - (long)previousIdx);
      } else {
         for(int i = 0; i != filterContext.getSelectedSize(); ++i) {
            int idx = filterContext.getSelected()[i];
            if (idx - previousIdx > 0) {
               this.skip((long)TreeReaderFactory.TreeReader.countNonNullRowsInRange(previous.isNull, previousIdx, idx));
            }

            if (!previous.isNull[idx]) {
               previous.vector[idx] = (long)this.next();
            } else {
               previous.vector[idx] = 1L;
            }

            previousIdx = idx + 1;
         }

         this.skip((long)TreeReaderFactory.TreeReader.countNonNullRowsInRange(previous.isNull, previousIdx, (int)previousLen));
      }

   }

   public void nextVector(LongColumnVector previous, long previousLen) throws IOException {
      previous.isRepeating = true;

      for(int i = 0; (long)i < previousLen; ++i) {
         if (!previous.noNulls && previous.isNull[i]) {
            previous.vector[i] = 1L;
         } else {
            previous.vector[i] = (long)this.next();
         }

         if (previous.isRepeating && i > 0 && (previous.vector[0] != previous.vector[i] || previous.isNull[0] != previous.isNull[i])) {
            previous.isRepeating = false;
         }
      }

   }

   public void seek(PositionProvider index) throws IOException {
      this.input.seek(index);
      int consumed = (int)index.getNext();
      if (consumed > 8) {
         throw new IllegalArgumentException("Seek past end of byte at " + consumed + " in " + String.valueOf(this.input));
      } else {
         if (consumed != 0) {
            this.readByte();
            this.currentIdx = (byte)consumed;
         } else {
            this.currentIdx = 8;
         }

      }
   }

   public void skip(long totalBits) throws IOException {
      int availableBits = 8 - this.currentIdx;
      if (totalBits <= (long)availableBits) {
         this.currentIdx = (byte)((int)((long)this.currentIdx + totalBits));
      } else {
         long bitsToSkip = totalBits - (long)availableBits;
         this.input.skip(bitsToSkip / 8L);
         if (this.input.hasNext()) {
            this.current = this.input.next();
            this.currentIdx = (byte)((int)(bitsToSkip % 8L));
         }
      }

   }

   public String toString() {
      int var10000 = this.current;
      return "bit reader current: " + var10000 + " current bit index: " + this.currentIdx + " from " + String.valueOf(this.input);
   }
}
