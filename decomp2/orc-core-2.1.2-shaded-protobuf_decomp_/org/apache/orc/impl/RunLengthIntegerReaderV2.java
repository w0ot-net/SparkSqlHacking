package org.apache.orc.impl;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunLengthIntegerReaderV2 implements IntegerReader {
   public static final Logger LOG = LoggerFactory.getLogger(RunLengthIntegerReaderV2.class);
   private InStream input;
   private final boolean signed;
   private final long[] literals = new long[512];
   private int numLiterals = 0;
   private int used = 0;
   private final boolean skipCorrupt;
   private final SerializationUtils utils;
   private RunLengthIntegerWriterV2.EncodingType currentEncoding;
   private static final RunLengthIntegerWriterV2.EncodingType[] encodings = RunLengthIntegerWriterV2.EncodingType.values();

   public RunLengthIntegerReaderV2(InStream input, boolean signed, boolean skipCorrupt) throws IOException {
      this.input = input;
      this.signed = signed;
      this.skipCorrupt = skipCorrupt;
      this.utils = new SerializationUtils();
   }

   private void readValues(boolean ignoreEof) throws IOException {
      int firstByte = this.input.read();
      if (firstByte < 0) {
         if (!ignoreEof) {
            throw new EOFException("Read past end of RLE integer from " + String.valueOf(this.input));
         } else {
            this.used = this.numLiterals = 0;
         }
      } else {
         this.currentEncoding = encodings[firstByte >>> 6 & 3];
         switch (this.currentEncoding) {
            case SHORT_REPEAT -> this.readShortRepeatValues(firstByte);
            case DIRECT -> this.readDirectValues(firstByte);
            case PATCHED_BASE -> this.readPatchedBaseValues(firstByte);
            case DELTA -> this.readDeltaValues(firstByte);
            default -> throw new IOException("Unknown encoding " + String.valueOf(this.currentEncoding));
         }

      }
   }

   private void readDeltaValues(int firstByte) throws IOException {
      int fb = firstByte >>> 1 & 31;
      if (fb != 0) {
         SerializationUtils var10000 = this.utils;
         fb = SerializationUtils.decodeBitWidth(fb);
      }

      int len = (firstByte & 1) << 8;
      len |= this.input.read();
      long firstVal = 0L;
      if (this.signed) {
         firstVal = SerializationUtils.readVslong(this.input);
      } else {
         firstVal = SerializationUtils.readVulong(this.input);
      }

      this.literals[this.numLiterals++] = firstVal;
      if (fb == 0) {
         long fd = SerializationUtils.readVslong(this.input);
         if (fd == 0L) {
            assert this.numLiterals == 1;

            Arrays.fill(this.literals, this.numLiterals, this.numLiterals + len, this.literals[0]);
            this.numLiterals += len;
         } else {
            for(int i = 0; i < len; ++i) {
               this.literals[this.numLiterals++] = this.literals[this.numLiterals - 2] + fd;
            }
         }
      } else {
         long deltaBase = SerializationUtils.readVslong(this.input);
         this.literals[this.numLiterals++] = firstVal + deltaBase;
         long prevVal = this.literals[this.numLiterals - 1];
         --len;
         this.utils.readInts(this.literals, this.numLiterals, len, fb, this.input);

         while(len > 0) {
            if (deltaBase < 0L) {
               this.literals[this.numLiterals] = prevVal - this.literals[this.numLiterals];
            } else {
               this.literals[this.numLiterals] += prevVal;
            }

            prevVal = this.literals[this.numLiterals];
            --len;
            ++this.numLiterals;
         }
      }

   }

   private void readPatchedBaseValues(int firstByte) throws IOException {
      int fbo = firstByte >>> 1 & 31;
      SerializationUtils var10000 = this.utils;
      int fb = SerializationUtils.decodeBitWidth(fbo);
      int len = (firstByte & 1) << 8;
      len |= this.input.read();
      ++len;
      int thirdByte = this.input.read();
      int bw = thirdByte >>> 5 & 7;
      ++bw;
      int pwo = thirdByte & 31;
      var10000 = this.utils;
      int pw = SerializationUtils.decodeBitWidth(pwo);
      int fourthByte = this.input.read();
      int pgw = fourthByte >>> 5 & 7;
      ++pgw;
      int pl = fourthByte & 31;
      long base = this.utils.bytesToLongBE(this.input, bw);
      long mask = 1L << bw * 8 - 1;
      if ((base & mask) != 0L) {
         base &= ~mask;
         base = -base;
      }

      long[] unpacked = new long[len];
      this.utils.readInts(unpacked, 0, len, fb, this.input);
      long[] unpackedPatch = new long[pl];
      if (pw + pgw > 64 && !this.skipCorrupt) {
         throw new IOException("Corruption in ORC data encountered. To skip reading corrupted data, set hive.exec.orc.skip.corrupt.data to true");
      } else {
         int bitSize = this.utils.getClosestFixedBits(pw + pgw);
         this.utils.readInts(unpackedPatch, 0, pl, bitSize, this.input);
         int patchIdx = 0;
         long currGap = 0L;
         long currPatch = 0L;
         long patchMask = (1L << pw) - 1L;
         currGap = unpackedPatch[patchIdx] >>> pw;
         currPatch = unpackedPatch[patchIdx] & patchMask;

         long actualGap;
         for(actualGap = 0L; currGap == 255L && currPatch == 0L; currPatch = unpackedPatch[patchIdx] & patchMask) {
            actualGap += 255L;
            ++patchIdx;
            currGap = unpackedPatch[patchIdx] >>> pw;
         }

         actualGap += currGap;

         for(int i = 0; i < unpacked.length; ++i) {
            if ((long)i != actualGap) {
               this.literals[this.numLiterals++] = base + unpacked[i];
            } else {
               long patchedVal = unpacked[i] | currPatch << fb;
               this.literals[this.numLiterals++] = base + patchedVal;
               ++patchIdx;
               if (patchIdx < pl) {
                  currGap = unpackedPatch[patchIdx] >>> pw;
                  currPatch = unpackedPatch[patchIdx] & patchMask;

                  for(actualGap = 0L; currGap == 255L && currPatch == 0L; currPatch = unpackedPatch[patchIdx] & patchMask) {
                     actualGap += 255L;
                     ++patchIdx;
                     currGap = unpackedPatch[patchIdx] >>> pw;
                  }

                  actualGap += currGap;
                  actualGap += (long)i;
               }
            }
         }

      }
   }

   private void readDirectValues(int firstByte) throws IOException {
      int fbo = firstByte >>> 1 & 31;
      SerializationUtils var10000 = this.utils;
      int fb = SerializationUtils.decodeBitWidth(fbo);
      int len = (firstByte & 1) << 8;
      len |= this.input.read();
      ++len;
      this.utils.readInts(this.literals, this.numLiterals, len, fb, this.input);
      if (this.signed) {
         for(int i = 0; i < len; ++i) {
            this.literals[this.numLiterals] = this.utils.zigzagDecode(this.literals[this.numLiterals]);
            ++this.numLiterals;
         }
      } else {
         this.numLiterals += len;
      }

   }

   private void readShortRepeatValues(int firstByte) throws IOException {
      int size = firstByte >>> 3 & 7;
      ++size;
      int len = firstByte & 7;
      len += 3;
      long val = this.utils.bytesToLongBE(this.input, size);
      if (this.signed) {
         val = this.utils.zigzagDecode(val);
      }

      if (this.numLiterals != 0) {
         throw new AssertionError("readValues called with existing values present");
      } else {
         for(int i = 0; i < len; ++i) {
            this.literals[i] = val;
         }

         this.numLiterals = len;
      }
   }

   public boolean hasNext() throws IOException {
      return this.used != this.numLiterals || this.input.available() > 0;
   }

   public long next() throws IOException {
      if (this.used == this.numLiterals) {
         this.numLiterals = 0;
         this.used = 0;
         this.readValues(false);
      }

      long result = this.literals[this.used++];
      return result;
   }

   public void seek(PositionProvider index) throws IOException {
      this.input.seek(index);
      int consumed = (int)index.getNext();
      if (consumed != 0) {
         while(consumed > 0) {
            this.numLiterals = 0;
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
            this.numLiterals = 0;
            this.used = 0;
            this.readValues(false);
         }

         long consume = Math.min(numValues, (long)(this.numLiterals - this.used));
         this.used = (int)((long)this.used + consume);
         numValues -= consume;
      }

   }

   public void nextVector(ColumnVector previous, long[] data, int previousLen) throws IOException {
      if (!previous.isRepeating || previous.noNulls || !previous.isNull[0]) {
         previous.isRepeating = true;

         for(int i = 0; i < previousLen; ++i) {
            if (!previous.noNulls && previous.isNull[i]) {
               data[i] = 1L;
            } else {
               data[i] = this.next();
            }

            if (previous.isRepeating && i > 0 && (data[0] != data[i] || previous.isNull[0] != previous.isNull[i])) {
               previous.isRepeating = false;
            }
         }

      }
   }

   public void nextVector(ColumnVector vector, int[] data, int size) throws IOException {
      int batchSize = Math.min(data.length, size);
      if (vector.noNulls) {
         for(int r = 0; r < batchSize; ++r) {
            data[r] = (int)this.next();
         }
      } else if (!vector.isRepeating || !vector.isNull[0]) {
         for(int r = 0; r < batchSize; ++r) {
            data[r] = vector.isNull[r] ? 1 : (int)this.next();
         }
      }

   }
}
