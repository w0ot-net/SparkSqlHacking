package org.apache.orc.impl;

import java.io.IOException;
import java.util.function.Consumer;

public class RunLengthIntegerWriterV2 implements IntegerWriter {
   static final int MAX_SCOPE = 512;
   static final int MIN_REPEAT = 3;
   static final long BASE_VALUE_LIMIT = 72057594037927936L;
   private static final int MAX_SHORT_REPEAT_LENGTH = 10;
   private long prevDelta;
   private int fixedRunLength;
   private int variableRunLength;
   private final long[] literals;
   private final PositionedOutputStream output;
   private final boolean signed;
   private EncodingType encoding;
   private int numLiterals;
   private final long[] zigzagLiterals;
   private final long[] baseRedLiterals;
   private final long[] adjDeltas;
   private long fixedDelta;
   private int zzBits90p;
   private int zzBits100p;
   private int brBits95p;
   private int brBits100p;
   private int bitsDeltaMax;
   private int patchWidth;
   private int patchGapWidth;
   private int patchLength;
   private long[] gapVsPatchList;
   private long min;
   private boolean isFixedDelta;
   private SerializationUtils utils;
   private boolean alignedBitpacking;

   RunLengthIntegerWriterV2(PositionedOutputStream output, boolean signed) {
      this(output, signed, true);
   }

   public RunLengthIntegerWriterV2(PositionedOutputStream output, boolean signed, boolean alignedBitpacking) {
      this.prevDelta = 0L;
      this.fixedRunLength = 0;
      this.variableRunLength = 0;
      this.literals = new long[512];
      this.baseRedLiterals = new long[512];
      this.adjDeltas = new long[512];
      this.output = output;
      this.signed = signed;
      this.zigzagLiterals = signed ? new long[512] : null;
      this.alignedBitpacking = alignedBitpacking;
      this.utils = new SerializationUtils();
      this.clear();
   }

   private void writeValues() throws IOException {
      if (this.numLiterals != 0) {
         if (this.encoding.equals(RunLengthIntegerWriterV2.EncodingType.SHORT_REPEAT)) {
            this.writeShortRepeatValues();
         } else if (this.encoding.equals(RunLengthIntegerWriterV2.EncodingType.DIRECT)) {
            this.writeDirectValues();
         } else if (this.encoding.equals(RunLengthIntegerWriterV2.EncodingType.PATCHED_BASE)) {
            this.writePatchedBaseValues();
         } else {
            this.writeDeltaValues();
         }

         this.clear();
      }

   }

   private void writeDeltaValues() throws IOException {
      int len = 0;
      int fb = this.bitsDeltaMax;
      int efb = 0;
      if (this.alignedBitpacking) {
         fb = this.utils.getClosestAlignedFixedBits(fb);
      }

      if (this.isFixedDelta) {
         if (this.fixedRunLength > 3) {
            len = this.fixedRunLength - 1;
            this.fixedRunLength = 0;
         } else {
            len = this.variableRunLength - 1;
            this.variableRunLength = 0;
         }
      } else {
         if (fb == 1) {
            fb = 2;
         }

         efb = this.utils.encodeBitWidth(fb);
         efb <<= 1;
         len = this.variableRunLength - 1;
         this.variableRunLength = 0;
      }

      int tailBits = (len & 256) >>> 8;
      int headerFirstByte = this.getOpcode() | efb | tailBits;
      int headerSecondByte = len & 255;
      this.output.write(headerFirstByte);
      this.output.write(headerSecondByte);
      if (this.signed) {
         this.utils.writeVslong(this.output, this.literals[0]);
      } else {
         this.utils.writeVulong(this.output, this.literals[0]);
      }

      if (this.isFixedDelta) {
         this.utils.writeVslong(this.output, this.fixedDelta);
      } else {
         this.utils.writeVslong(this.output, this.adjDeltas[0]);
         this.utils.writeInts(this.adjDeltas, 1, this.numLiterals - 2, fb, this.output);
      }

   }

   private void writePatchedBaseValues() throws IOException {
      int fb = this.brBits95p;
      int efb = this.utils.encodeBitWidth(fb) << 1;
      --this.variableRunLength;
      int tailBits = (this.variableRunLength & 256) >>> 8;
      int headerFirstByte = this.getOpcode() | efb | tailBits;
      int headerSecondByte = this.variableRunLength & 255;
      boolean isNegative = this.min < 0L;
      if (isNegative) {
         this.min = -this.min;
      }

      int baseWidth = this.utils.findClosestNumBits(this.min) + 1;
      int baseBytes = baseWidth % 8 == 0 ? baseWidth / 8 : baseWidth / 8 + 1;
      int bb = baseBytes - 1 << 5;
      if (isNegative) {
         this.min |= 1L << baseBytes * 8 - 1;
      }

      int headerThirdByte = bb | this.utils.encodeBitWidth(this.patchWidth);
      int headerFourthByte = this.patchGapWidth - 1 << 5 | this.patchLength;
      this.output.write(headerFirstByte);
      this.output.write(headerSecondByte);
      this.output.write(headerThirdByte);
      this.output.write(headerFourthByte);

      for(int i = baseBytes - 1; i >= 0; --i) {
         byte b = (byte)((int)(this.min >>> i * 8 & 255L));
         this.output.write(b);
      }

      int closestFixedBits = this.utils.getClosestFixedBits(fb);
      this.utils.writeInts(this.baseRedLiterals, 0, this.numLiterals, closestFixedBits, this.output);
      closestFixedBits = this.utils.getClosestFixedBits(this.patchGapWidth + this.patchWidth);
      this.utils.writeInts(this.gapVsPatchList, 0, this.gapVsPatchList.length, closestFixedBits, this.output);
      this.variableRunLength = 0;
   }

   private int getOpcode() {
      return this.encoding.ordinal() << 6;
   }

   private void writeDirectValues() throws IOException {
      int fb = this.zzBits100p;
      if (this.alignedBitpacking) {
         fb = this.utils.getClosestAlignedFixedBits(fb);
      }

      int efb = this.utils.encodeBitWidth(fb) << 1;
      --this.variableRunLength;
      int tailBits = (this.variableRunLength & 256) >>> 8;
      int headerFirstByte = this.getOpcode() | efb | tailBits;
      int headerSecondByte = this.variableRunLength & 255;
      this.output.write(headerFirstByte);
      this.output.write(headerSecondByte);
      long[] currentZigzagLiterals = this.signed ? this.zigzagLiterals : this.literals;
      this.utils.writeInts(currentZigzagLiterals, 0, this.numLiterals, fb, this.output);
      this.variableRunLength = 0;
   }

   private void writeShortRepeatValues() throws IOException {
      long repeatVal = 0L;
      if (this.signed) {
         repeatVal = this.utils.zigzagEncode(this.literals[0]);
      } else {
         repeatVal = this.literals[0];
      }

      int numBitsRepeatVal = this.utils.findClosestNumBits(repeatVal);
      int numBytesRepeatVal = numBitsRepeatVal % 8 == 0 ? numBitsRepeatVal >>> 3 : (numBitsRepeatVal >>> 3) + 1;
      int header = this.getOpcode();
      header |= numBytesRepeatVal - 1 << 3;
      this.fixedRunLength -= 3;
      header |= this.fixedRunLength;
      this.output.write(header);

      for(int i = numBytesRepeatVal - 1; i >= 0; --i) {
         int b = (int)(repeatVal >>> i * 8 & 255L);
         this.output.write(b);
      }

      this.fixedRunLength = 0;
   }

   private long[] prepareForDirectOrPatchedBase() {
      if (this.signed) {
         this.computeZigZagLiterals();
      }

      long[] currentZigzagLiterals = this.signed ? this.zigzagLiterals : this.literals;
      this.zzBits100p = this.utils.percentileBits(currentZigzagLiterals, 0, this.numLiterals, (double)1.0F);
      return currentZigzagLiterals;
   }

   private void determineEncoding() {
      if (this.numLiterals <= 3) {
         this.prepareForDirectOrPatchedBase();
         this.encoding = RunLengthIntegerWriterV2.EncodingType.DIRECT;
      } else {
         boolean isIncreasing = true;
         boolean isDecreasing = true;
         this.isFixedDelta = true;
         this.min = this.literals[0];
         long max = this.literals[0];
         long initialDelta = this.literals[1] - this.literals[0];
         long currDelta = 0L;
         long deltaMax = 0L;
         this.adjDeltas[0] = initialDelta;

         for(int i = 1; i < this.numLiterals; ++i) {
            long l1 = this.literals[i];
            long l0 = this.literals[i - 1];
            currDelta = l1 - l0;
            this.min = Math.min(this.min, l1);
            max = Math.max(max, l1);
            isIncreasing &= l0 <= l1;
            isDecreasing &= l0 >= l1;
            this.isFixedDelta &= currDelta == initialDelta;
            if (i > 1) {
               this.adjDeltas[i - 1] = Math.abs(currDelta);
               deltaMax = Math.max(deltaMax, this.adjDeltas[i - 1]);
            }
         }

         if (!this.utils.isSafeSubtract(max, this.min)) {
            this.prepareForDirectOrPatchedBase();
            this.encoding = RunLengthIntegerWriterV2.EncodingType.DIRECT;
         } else if (this.min == max) {
            assert this.isFixedDelta : this.min + "==" + max + ", isFixedDelta cannot be false";

            assert currDelta == 0L : this.min + "==" + max + ", currDelta should be zero";

            this.fixedDelta = 0L;
            this.encoding = RunLengthIntegerWriterV2.EncodingType.DELTA;
         } else if (this.isFixedDelta) {
            assert currDelta == initialDelta : "currDelta should be equal to initialDelta for fixed delta encoding";

            this.encoding = RunLengthIntegerWriterV2.EncodingType.DELTA;
            this.fixedDelta = currDelta;
         } else {
            if (initialDelta != 0L) {
               this.bitsDeltaMax = this.utils.findClosestNumBits(deltaMax);
               if (isIncreasing || isDecreasing) {
                  this.encoding = RunLengthIntegerWriterV2.EncodingType.DELTA;
                  return;
               }
            }

            long[] currentZigzagLiterals = this.prepareForDirectOrPatchedBase();
            this.zzBits90p = this.utils.percentileBits(currentZigzagLiterals, 0, this.numLiterals, 0.9);
            int diffBitsLH = this.zzBits100p - this.zzBits90p;
            if (diffBitsLH > 1) {
               for(int i = 0; i < this.numLiterals; ++i) {
                  this.baseRedLiterals[i] = this.literals[i] - this.min;
               }

               this.brBits95p = this.utils.percentileBits(this.baseRedLiterals, 0, this.numLiterals, 0.95);
               this.brBits100p = this.utils.percentileBits(this.baseRedLiterals, 0, this.numLiterals, (double)1.0F);
               if (this.brBits100p - this.brBits95p != 0 && Math.abs(this.min) < 72057594037927936L) {
                  this.encoding = RunLengthIntegerWriterV2.EncodingType.PATCHED_BASE;
                  this.preparePatchedBlob();
               } else {
                  this.encoding = RunLengthIntegerWriterV2.EncodingType.DIRECT;
               }
            } else {
               this.encoding = RunLengthIntegerWriterV2.EncodingType.DIRECT;
            }

         }
      }
   }

   private void computeZigZagLiterals() {
      assert this.signed : "only signed numbers need to compute zigzag values";

      for(int i = 0; i < this.numLiterals; ++i) {
         this.zigzagLiterals[i] = this.utils.zigzagEncode(this.literals[i]);
      }

   }

   private void preparePatchedBlob() {
      long mask = (1L << this.brBits95p) - 1L;
      this.patchLength = (int)Math.ceil((double)this.numLiterals * 0.05);
      int[] gapList = new int[this.patchLength];
      long[] patchList = new long[this.patchLength];
      this.patchWidth = this.brBits100p - this.brBits95p;
      this.patchWidth = this.utils.getClosestFixedBits(this.patchWidth);
      if (this.patchWidth == 64) {
         this.patchWidth = 56;
         this.brBits95p = 8;
         mask = (1L << this.brBits95p) - 1L;
      }

      int gapIdx = 0;
      int patchIdx = 0;
      int prev = 0;
      int gap = 0;
      int maxGap = 0;

      for(int i = 0; i < this.numLiterals; ++i) {
         if (this.baseRedLiterals[i] > mask) {
            gap = i - prev;
            if (gap > maxGap) {
               maxGap = gap;
            }

            prev = i;
            gapList[gapIdx++] = gap;
            long patch = this.baseRedLiterals[i] >>> this.brBits95p;
            patchList[patchIdx++] = patch;
            long[] var10000 = this.baseRedLiterals;
            var10000[i] &= mask;
         }
      }

      this.patchLength = gapIdx;
      if (maxGap == 0 && this.patchLength != 0) {
         this.patchGapWidth = 1;
      } else {
         this.patchGapWidth = this.utils.findClosestNumBits((long)maxGap);
      }

      if (this.patchGapWidth > 8) {
         this.patchGapWidth = 8;
         if (maxGap == 511) {
            this.patchLength += 2;
         } else {
            ++this.patchLength;
         }
      }

      gapIdx = 0;
      patchIdx = 0;
      this.gapVsPatchList = new long[this.patchLength];

      for(int i = 0; i < this.patchLength; ++i) {
         long g = (long)gapList[gapIdx++];

         long p;
         for(p = patchList[patchIdx++]; g > 255L; g -= 255L) {
            this.gapVsPatchList[i++] = 255L << this.patchWidth;
         }

         this.gapVsPatchList[i] = g << this.patchWidth | p;
      }

   }

   private void clear() {
      this.numLiterals = 0;
      this.encoding = null;
      this.prevDelta = 0L;
      this.fixedDelta = 0L;
      this.zzBits90p = 0;
      this.zzBits100p = 0;
      this.brBits95p = 0;
      this.brBits100p = 0;
      this.bitsDeltaMax = 0;
      this.patchGapWidth = 0;
      this.patchLength = 0;
      this.patchWidth = 0;
      this.gapVsPatchList = null;
      this.min = 0L;
      this.isFixedDelta = true;
   }

   public void flush() throws IOException {
      if (this.numLiterals != 0) {
         if (this.variableRunLength != 0) {
            this.determineEncoding();
            this.writeValues();
         } else if (this.fixedRunLength != 0) {
            if (this.fixedRunLength < 3) {
               this.variableRunLength = this.fixedRunLength;
               this.fixedRunLength = 0;
               this.determineEncoding();
            } else if (this.fixedRunLength <= 10) {
               this.encoding = RunLengthIntegerWriterV2.EncodingType.SHORT_REPEAT;
            } else {
               this.encoding = RunLengthIntegerWriterV2.EncodingType.DELTA;
               this.isFixedDelta = true;
            }

            this.writeValues();
         }
      }

      this.output.flush();
   }

   public void write(long val) throws IOException {
      if (this.numLiterals == 0) {
         this.initializeLiterals(val);
      } else if (this.numLiterals == 1) {
         this.prevDelta = val - this.literals[0];
         this.literals[this.numLiterals++] = val;
         if (val == this.literals[0]) {
            this.fixedRunLength = 2;
            this.variableRunLength = 0;
         } else {
            this.fixedRunLength = 0;
            this.variableRunLength = 2;
         }
      } else {
         long currentDelta = val - this.literals[this.numLiterals - 1];
         if (this.prevDelta == 0L && currentDelta == 0L) {
            this.literals[this.numLiterals++] = val;
            if (this.variableRunLength > 0) {
               this.fixedRunLength = 2;
            }

            ++this.fixedRunLength;
            if (this.fixedRunLength >= 3 && this.variableRunLength > 0) {
               this.numLiterals -= 3;
               this.variableRunLength -= 2;
               long[] tailVals = new long[3];
               System.arraycopy(this.literals, this.numLiterals, tailVals, 0, 3);
               this.determineEncoding();
               this.writeValues();

               for(long l : tailVals) {
                  this.literals[this.numLiterals++] = l;
               }
            }

            if (this.fixedRunLength == 512) {
               this.encoding = RunLengthIntegerWriterV2.EncodingType.DELTA;
               this.isFixedDelta = true;
               this.writeValues();
            }
         } else {
            if (this.fixedRunLength >= 3) {
               if (this.fixedRunLength <= 10) {
                  this.encoding = RunLengthIntegerWriterV2.EncodingType.SHORT_REPEAT;
               } else {
                  this.encoding = RunLengthIntegerWriterV2.EncodingType.DELTA;
                  this.isFixedDelta = true;
               }

               this.writeValues();
            }

            if (this.fixedRunLength > 0 && this.fixedRunLength < 3 && val != this.literals[this.numLiterals - 1]) {
               this.variableRunLength = this.fixedRunLength;
               this.fixedRunLength = 0;
            }

            if (this.numLiterals == 0) {
               this.initializeLiterals(val);
            } else {
               this.prevDelta = val - this.literals[this.numLiterals - 1];
               this.literals[this.numLiterals++] = val;
               ++this.variableRunLength;
               if (this.variableRunLength == 512) {
                  this.determineEncoding();
                  this.writeValues();
               }
            }
         }
      }

   }

   private void initializeLiterals(long val) {
      this.literals[this.numLiterals++] = val;
      this.fixedRunLength = 1;
      this.variableRunLength = 1;
   }

   public void getPosition(PositionRecorder recorder) throws IOException {
      this.output.getPosition(recorder);
      recorder.addPosition((long)this.numLiterals);
   }

   public long estimateMemory() {
      return this.output.getBufferSize();
   }

   public void changeIv(Consumer modifier) {
      this.output.changeIv(modifier);
   }

   public static enum EncodingType {
      SHORT_REPEAT,
      DIRECT,
      PATCHED_BASE,
      DELTA;

      // $FF: synthetic method
      private static EncodingType[] $values() {
         return new EncodingType[]{SHORT_REPEAT, DIRECT, PATCHED_BASE, DELTA};
      }
   }
}
