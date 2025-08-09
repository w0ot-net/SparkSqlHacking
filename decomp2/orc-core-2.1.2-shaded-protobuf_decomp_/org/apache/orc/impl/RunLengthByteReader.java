package org.apache.orc.impl;

import java.io.EOFException;
import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;

public class RunLengthByteReader {
   private InStream input;
   private final byte[] literals = new byte[128];
   private int numLiterals = 0;
   private int used = 0;
   private boolean repeat = false;

   public RunLengthByteReader(InStream input) {
      this.input = input;
   }

   public void setInStream(InStream input) {
      this.input = input;
   }

   private void readValues(boolean ignoreEof, int numSkipRows) throws IOException {
      int control = this.input.read();
      this.used = 0;
      if (control == -1) {
         if (!ignoreEof) {
            throw new EOFException("Read past end of buffer RLE byte from " + String.valueOf(this.input));
         }

         this.used = this.numLiterals = 0;
      } else if (control < 128) {
         this.repeat = true;
         this.numLiterals = control + 3;
         if (numSkipRows >= this.numLiterals) {
            IOUtils.skipFully(this.input, 1L);
         } else {
            int val = this.input.read();
            if (val == -1) {
               throw new EOFException("Reading RLE byte got EOF");
            }

            this.literals[0] = (byte)val;
         }
      } else {
         this.repeat = false;
         this.numLiterals = 256 - control;
         numSkipRows = Math.min(numSkipRows, this.numLiterals);
         if (numSkipRows > 0) {
            IOUtils.skipFully(this.input, (long)numSkipRows);
         }

         int result;
         for(int bytes = numSkipRows; bytes < this.numLiterals; bytes += result) {
            result = this.input.read(this.literals, bytes, this.numLiterals - bytes);
            if (result == -1) {
               throw new EOFException("Reading RLE byte literal got EOF in " + String.valueOf(this));
            }
         }
      }

   }

   public boolean hasNext() throws IOException {
      return this.used != this.numLiterals || this.input.available() > 0;
   }

   public byte next() throws IOException {
      if (this.used == this.numLiterals) {
         this.readValues(false, 0);
      }

      byte result;
      if (this.repeat) {
         result = this.literals[0];
      } else {
         result = this.literals[this.used];
      }

      ++this.used;
      return result;
   }

   public void nextVector(ColumnVector previous, long[] data, long size) throws IOException {
      previous.isRepeating = true;

      for(int i = 0; (long)i < size; ++i) {
         if (!previous.isNull[i]) {
            data[i] = (long)this.next();
         } else {
            data[i] = 1L;
         }

         if (previous.isRepeating && i > 0 && (data[0] != data[i] || previous.isNull[0] != previous.isNull[i])) {
            previous.isRepeating = false;
         }
      }

   }

   public void nextVector(boolean[] isNull, int[] data, long size) throws IOException {
      if (isNull == null) {
         for(int i = 0; (long)i < size; ++i) {
            data[i] = this.next();
         }
      } else {
         for(int i = 0; (long)i < size; ++i) {
            if (!isNull[i]) {
               data[i] = this.next();
            }
         }
      }

   }

   public void seek(PositionProvider index) throws IOException {
      this.input.seek(index);
      int consumed = (int)index.getNext();
      if (consumed != 0) {
         while(consumed > 0) {
            this.readValues(false, 0);
            this.used = consumed;
            consumed -= this.numLiterals;
         }
      } else {
         this.used = 0;
         this.numLiterals = 0;
      }

   }

   public void skip(long items) throws IOException {
      while(items > 0L) {
         if (this.used == this.numLiterals) {
            this.readValues(false, (int)items);
         }

         long consume = Math.min(items, (long)(this.numLiterals - this.used));
         this.used = (int)((long)this.used + consume);
         items -= consume;
      }

   }

   public String toString() {
      String var10000 = this.repeat ? "repeat" : "literal";
      return "byte rle " + var10000 + " used: " + this.used + "/" + this.numLiterals + " from " + String.valueOf(this.input);
   }
}
