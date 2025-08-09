package io.airlift.compress.zstd;

import sun.misc.Unsafe;

class SequenceStore {
   public final byte[] literalsBuffer;
   public int literalsLength;
   public final int[] offsets;
   public final int[] literalLengths;
   public final int[] matchLengths;
   public int sequenceCount;
   public final byte[] literalLengthCodes;
   public final byte[] matchLengthCodes;
   public final byte[] offsetCodes;
   public LongField longLengthField;
   public int longLengthPosition;
   private static final byte[] LITERAL_LENGTH_CODE = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 16, 17, 17, 18, 18, 19, 19, 20, 20, 20, 20, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 23, 23, 23, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24};
   private static final byte[] MATCH_LENGTH_CODE = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 32, 33, 33, 34, 34, 35, 35, 36, 36, 36, 36, 37, 37, 37, 37, 38, 38, 38, 38, 38, 38, 38, 38, 39, 39, 39, 39, 39, 39, 39, 39, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42};

   public SequenceStore(int blockSize, int maxSequences) {
      this.offsets = new int[maxSequences];
      this.literalLengths = new int[maxSequences];
      this.matchLengths = new int[maxSequences];
      this.literalLengthCodes = new byte[maxSequences];
      this.matchLengthCodes = new byte[maxSequences];
      this.offsetCodes = new byte[maxSequences];
      this.literalsBuffer = new byte[blockSize];
      this.reset();
   }

   public void appendLiterals(Object inputBase, long inputAddress, int inputSize) {
      UnsafeUtil.UNSAFE.copyMemory(inputBase, inputAddress, this.literalsBuffer, (long)(Unsafe.ARRAY_BYTE_BASE_OFFSET + this.literalsLength), (long)inputSize);
      this.literalsLength += inputSize;
   }

   public void storeSequence(Object literalBase, long literalAddress, int literalLength, int offsetCode, int matchLengthBase) {
      long input = literalAddress;
      long output = (long)(Unsafe.ARRAY_BYTE_BASE_OFFSET + this.literalsLength);
      int copied = 0;

      do {
         UnsafeUtil.UNSAFE.putLong(this.literalsBuffer, output, UnsafeUtil.UNSAFE.getLong(literalBase, input));
         input += 8L;
         output += 8L;
         copied += 8;
      } while(copied < literalLength);

      this.literalsLength += literalLength;
      if (literalLength > 65535) {
         this.longLengthField = SequenceStore.LongField.LITERAL;
         this.longLengthPosition = this.sequenceCount;
      }

      this.literalLengths[this.sequenceCount] = literalLength;
      this.offsets[this.sequenceCount] = offsetCode + 1;
      if (matchLengthBase > 65535) {
         this.longLengthField = SequenceStore.LongField.MATCH;
         this.longLengthPosition = this.sequenceCount;
      }

      this.matchLengths[this.sequenceCount] = matchLengthBase;
      ++this.sequenceCount;
   }

   public void reset() {
      this.literalsLength = 0;
      this.sequenceCount = 0;
      this.longLengthField = null;
   }

   public void generateCodes() {
      for(int i = 0; i < this.sequenceCount; ++i) {
         this.literalLengthCodes[i] = (byte)literalLengthToCode(this.literalLengths[i]);
         this.offsetCodes[i] = (byte)Util.highestBit(this.offsets[i]);
         this.matchLengthCodes[i] = (byte)matchLengthToCode(this.matchLengths[i]);
      }

      if (this.longLengthField == SequenceStore.LongField.LITERAL) {
         this.literalLengthCodes[this.longLengthPosition] = 35;
      }

      if (this.longLengthField == SequenceStore.LongField.MATCH) {
         this.matchLengthCodes[this.longLengthPosition] = 52;
      }

   }

   private static int literalLengthToCode(int literalLength) {
      return literalLength >= 64 ? Util.highestBit(literalLength) + 19 : LITERAL_LENGTH_CODE[literalLength];
   }

   private static int matchLengthToCode(int matchLengthBase) {
      return matchLengthBase >= 128 ? Util.highestBit(matchLengthBase) + 36 : MATCH_LENGTH_CODE[matchLengthBase];
   }

   public static enum LongField {
      LITERAL,
      MATCH;
   }
}
