package org.apache.orc.impl;

import java.io.EOFException;
import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;

public class RunLengthIntegerReader implements IntegerReader {
   private InStream input;
   private final boolean signed;
   private final long[] literals = new long[128];
   private int numLiterals = 0;
   private int delta = 0;
   private int used = 0;
   private boolean repeat = false;

   public RunLengthIntegerReader(InStream input, boolean signed) throws IOException {
      this.input = input;
      this.signed = signed;
   }

   private void readValues(boolean ignoreEof) throws IOException {
      int control = this.input.read();
      if (control == -1) {
         if (!ignoreEof) {
            throw new EOFException("Read past end of RLE integer from " + String.valueOf(this.input));
         } else {
            this.used = this.numLiterals = 0;
         }
      } else {
         if (control < 128) {
            this.numLiterals = control + 3;
            this.used = 0;
            this.repeat = true;
            this.delta = this.input.read();
            if (this.delta == -1) {
               throw new EOFException("End of stream in RLE Integer from " + String.valueOf(this.input));
            }

            this.delta = (byte)(0 + this.delta);
            if (this.signed) {
               this.literals[0] = SerializationUtils.readVslong(this.input);
            } else {
               this.literals[0] = SerializationUtils.readVulong(this.input);
            }
         } else {
            this.repeat = false;
            this.numLiterals = 256 - control;
            this.used = 0;

            for(int i = 0; i < this.numLiterals; ++i) {
               if (this.signed) {
                  this.literals[i] = SerializationUtils.readVslong(this.input);
               } else {
                  this.literals[i] = SerializationUtils.readVulong(this.input);
               }
            }
         }

      }
   }

   public boolean hasNext() throws IOException {
      return this.used != this.numLiterals || this.input.available() > 0;
   }

   public long next() throws IOException {
      if (this.used == this.numLiterals) {
         this.readValues(false);
      }

      long result;
      if (this.repeat) {
         result = this.literals[0] + (long)(this.used++ * this.delta);
      } else {
         result = this.literals[this.used++];
      }

      return result;
   }

   public void nextVector(ColumnVector previous, long[] data, int previousLen) throws IOException {
      previous.isRepeating = true;

      for(int i = 0; i < previousLen; ++i) {
         if (!previous.isNull[i]) {
            data[i] = this.next();
         } else {
            data[i] = 1L;
         }

         if (previous.isRepeating && i > 0 && (data[0] != data[i] || previous.isNull[0] != previous.isNull[i])) {
            previous.isRepeating = false;
         }
      }

   }

   public void nextVector(ColumnVector vector, int[] data, int size) throws IOException {
      if (vector.noNulls) {
         for(int r = 0; r < data.length && r < size; ++r) {
            data[r] = (int)this.next();
         }
      } else if (!vector.isRepeating || !vector.isNull[0]) {
         for(int r = 0; r < data.length && r < size; ++r) {
            if (!vector.isNull[r]) {
               data[r] = (int)this.next();
            } else {
               data[r] = 1;
            }
         }
      }

   }

   public void seek(PositionProvider index) throws IOException {
      this.input.seek(index);
      int consumed = (int)index.getNext();
      if (consumed != 0) {
         while(consumed > 0) {
            this.readValues(false);
            this.used = consumed;
            consumed -= this.numLiterals;
         }
      } else {
         this.used = 0;
         this.numLiterals = 0;
      }

   }

   public void skip(long numValues) throws IOException {
      while(numValues > 0L) {
         if (this.used == this.numLiterals) {
            this.readValues(false);
         }

         long consume = Math.min(numValues, (long)(this.numLiterals - this.used));
         this.used = (int)((long)this.used + consume);
         numValues -= consume;
      }

   }
}
