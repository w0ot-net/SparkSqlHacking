package org.apache.commons.compress.compressors.lz77support;

import java.io.IOException;
import java.util.Objects;
import org.apache.commons.lang3.ArrayFill;

public class LZ77Compressor {
   private static final Block THE_EOD = new EOD();
   static final int NUMBER_OF_BYTES_IN_HASH = 3;
   private static final int NO_MATCH = -1;
   private static final int HASH_SIZE = 32768;
   private static final int HASH_MASK = 32767;
   private static final int H_SHIFT = 5;
   private final Parameters params;
   private final Callback callback;
   private final byte[] window;
   private final int[] head;
   private final int[] prev;
   private final int wMask;
   private boolean initialized;
   private int currentPosition;
   private int lookahead;
   private int insertHash;
   private int blockStart;
   private int matchStart = -1;
   private int missedInserts;

   public LZ77Compressor(Parameters params, Callback callback) {
      Objects.requireNonNull(params, "params");
      Objects.requireNonNull(callback, "callback");
      this.params = params;
      this.callback = callback;
      int wSize = params.getWindowSize();
      this.window = new byte[wSize * 2];
      this.wMask = wSize - 1;
      this.head = ArrayFill.fill(new int['耀'], -1);
      this.prev = new int[wSize];
   }

   private void catchUpMissedInserts() {
      while(this.missedInserts > 0) {
         this.insertString(this.currentPosition - this.missedInserts--);
      }

   }

   private void compress() throws IOException {
      int minMatch = this.params.getMinBackReferenceLength();
      boolean lazy = this.params.getLazyMatching();
      int lazyThreshold = this.params.getLazyMatchingThreshold();

      while(this.lookahead >= minMatch) {
         this.catchUpMissedInserts();
         int matchLength = 0;
         int hashHead = this.insertString(this.currentPosition);
         if (hashHead != -1 && hashHead - this.currentPosition <= this.params.getMaxOffset()) {
            matchLength = this.longestMatch(hashHead);
            if (lazy && matchLength <= lazyThreshold && this.lookahead > minMatch) {
               matchLength = this.longestMatchForNextPosition(matchLength);
            }
         }

         if (matchLength >= minMatch) {
            if (this.blockStart != this.currentPosition) {
               this.flushLiteralBlock();
               this.blockStart = -1;
            }

            this.flushBackReference(matchLength);
            this.insertStringsInMatch(matchLength);
            this.lookahead -= matchLength;
            this.currentPosition += matchLength;
            this.blockStart = this.currentPosition;
         } else {
            --this.lookahead;
            ++this.currentPosition;
            if (this.currentPosition - this.blockStart >= this.params.getMaxLiteralLength()) {
               this.flushLiteralBlock();
               this.blockStart = this.currentPosition;
            }
         }
      }

   }

   public void compress(byte[] data) throws IOException {
      this.compress(data, 0, data.length);
   }

   public void compress(byte[] data, int off, int len) throws IOException {
      for(int wSize = this.params.getWindowSize(); len > wSize; len -= wSize) {
         this.doCompress(data, off, wSize);
         off += wSize;
      }

      if (len > 0) {
         this.doCompress(data, off, len);
      }

   }

   private void doCompress(byte[] data, int off, int len) throws IOException {
      int spaceLeft = this.window.length - this.currentPosition - this.lookahead;
      if (len > spaceLeft) {
         this.slide();
      }

      System.arraycopy(data, off, this.window, this.currentPosition + this.lookahead, len);
      this.lookahead += len;
      if (!this.initialized && this.lookahead >= this.params.getMinBackReferenceLength()) {
         this.initialize();
      }

      if (this.initialized) {
         this.compress();
      }

   }

   public void finish() throws IOException {
      if (this.blockStart != this.currentPosition || this.lookahead > 0) {
         this.currentPosition += this.lookahead;
         this.flushLiteralBlock();
      }

      this.callback.accept(THE_EOD);
   }

   private void flushBackReference(int matchLength) throws IOException {
      this.callback.accept(new BackReference(this.currentPosition - this.matchStart, matchLength));
   }

   private void flushLiteralBlock() throws IOException {
      this.callback.accept(new LiteralBlock(this.window, this.blockStart, this.currentPosition - this.blockStart));
   }

   private void initialize() {
      for(int i = 0; i < 2; ++i) {
         this.insertHash = this.nextHash(this.insertHash, this.window[i]);
      }

      this.initialized = true;
   }

   private int insertString(int pos) {
      this.insertHash = this.nextHash(this.insertHash, this.window[pos - 1 + 3]);
      int hashHead = this.head[this.insertHash];
      this.prev[pos & this.wMask] = hashHead;
      this.head[this.insertHash] = pos;
      return hashHead;
   }

   private void insertStringsInMatch(int matchLength) {
      int stop = Math.min(matchLength - 1, this.lookahead - 3);

      for(int i = 1; i <= stop; ++i) {
         this.insertString(this.currentPosition + i);
      }

      this.missedInserts = matchLength - stop - 1;
   }

   private int longestMatch(int matchHead) {
      int minLength = this.params.getMinBackReferenceLength();
      int longestMatchLength = minLength - 1;
      int maxPossibleLength = Math.min(this.params.getMaxBackReferenceLength(), this.lookahead);
      int minIndex = Math.max(0, this.currentPosition - this.params.getMaxOffset());
      int niceBackReferenceLength = Math.min(maxPossibleLength, this.params.getNiceBackReferenceLength());
      int maxCandidates = this.params.getMaxCandidates();

      for(int candidates = 0; candidates < maxCandidates && matchHead >= minIndex; ++candidates) {
         int currentLength = 0;

         for(int i = 0; i < maxPossibleLength && this.window[matchHead + i] == this.window[this.currentPosition + i]; ++i) {
            ++currentLength;
         }

         if (currentLength > longestMatchLength) {
            longestMatchLength = currentLength;
            this.matchStart = matchHead;
            if (currentLength >= niceBackReferenceLength) {
               break;
            }
         }

         matchHead = this.prev[matchHead & this.wMask];
      }

      return longestMatchLength;
   }

   private int longestMatchForNextPosition(int prevMatchLength) {
      int prevMatchStart = this.matchStart;
      int prevInsertHash = this.insertHash;
      --this.lookahead;
      ++this.currentPosition;
      int hashHead = this.insertString(this.currentPosition);
      int prevHashHead = this.prev[this.currentPosition & this.wMask];
      int matchLength = this.longestMatch(hashHead);
      if (matchLength <= prevMatchLength) {
         matchLength = prevMatchLength;
         this.matchStart = prevMatchStart;
         this.head[this.insertHash] = prevHashHead;
         this.insertHash = prevInsertHash;
         --this.currentPosition;
         ++this.lookahead;
      }

      return matchLength;
   }

   private int nextHash(int oldHash, byte nextByte) {
      int nextVal = nextByte & 255;
      return (oldHash << 5 ^ nextVal) & 32767;
   }

   public void prefill(byte[] data) {
      if (this.currentPosition == 0 && this.lookahead == 0) {
         int len = Math.min(this.params.getWindowSize(), data.length);
         System.arraycopy(data, data.length - len, this.window, 0, len);
         if (len >= 3) {
            this.initialize();
            int stop = len - 3 + 1;

            for(int i = 0; i < stop; ++i) {
               this.insertString(i);
            }

            this.missedInserts = 2;
         } else {
            this.missedInserts = len;
         }

         this.blockStart = this.currentPosition = len;
      } else {
         throw new IllegalStateException("The compressor has already started to accept data, can't prefill anymore");
      }
   }

   private void slide() throws IOException {
      int wSize = this.params.getWindowSize();
      if (this.blockStart != this.currentPosition && this.blockStart < wSize) {
         this.flushLiteralBlock();
         this.blockStart = this.currentPosition;
      }

      System.arraycopy(this.window, wSize, this.window, 0, wSize);
      this.currentPosition -= wSize;
      this.matchStart -= wSize;
      this.blockStart -= wSize;

      for(int i = 0; i < 32768; ++i) {
         int h = this.head[i];
         this.head[i] = h >= wSize ? h - wSize : -1;
      }

      for(int i = 0; i < wSize; ++i) {
         int p = this.prev[i];
         this.prev[i] = p >= wSize ? p - wSize : -1;
      }

   }

   public static final class BackReference extends Block {
      private final int offset;
      private final int length;

      public BackReference(int offset, int length) {
         this.offset = offset;
         this.length = length;
      }

      public int getLength() {
         return this.length;
      }

      public int getOffset() {
         return this.offset;
      }

      public Block.BlockType getType() {
         return LZ77Compressor.Block.BlockType.BACK_REFERENCE;
      }

      public String toString() {
         return "BackReference with offset " + this.offset + " and length " + this.length;
      }
   }

   public abstract static class Block {
      public abstract BlockType getType();

      public static enum BlockType {
         LITERAL,
         BACK_REFERENCE,
         EOD;

         // $FF: synthetic method
         private static BlockType[] $values() {
            return new BlockType[]{LITERAL, BACK_REFERENCE, EOD};
         }
      }
   }

   public static final class EOD extends Block {
      public Block.BlockType getType() {
         return LZ77Compressor.Block.BlockType.EOD;
      }
   }

   public static final class LiteralBlock extends Block {
      private final byte[] data;
      private final int offset;
      private final int length;

      public LiteralBlock(byte[] data, int offset, int length) {
         this.data = data;
         this.offset = offset;
         this.length = length;
      }

      public byte[] getData() {
         return this.data;
      }

      public int getLength() {
         return this.length;
      }

      public int getOffset() {
         return this.offset;
      }

      public Block.BlockType getType() {
         return LZ77Compressor.Block.BlockType.LITERAL;
      }

      public String toString() {
         return "LiteralBlock starting at " + this.offset + " with length " + this.length;
      }
   }

   public interface Callback {
      void accept(Block var1) throws IOException;
   }
}
