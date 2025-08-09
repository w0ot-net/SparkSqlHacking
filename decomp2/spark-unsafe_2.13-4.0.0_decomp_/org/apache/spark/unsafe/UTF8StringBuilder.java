package org.apache.spark.unsafe;

import org.apache.spark.unsafe.types.UTF8String;

public class UTF8StringBuilder {
   private static final int ARRAY_MAX = 2147483632;
   private byte[] buffer;
   private int cursor;

   public UTF8StringBuilder() {
      this(16);
   }

   public UTF8StringBuilder(int initialSize) {
      this.cursor = Platform.BYTE_ARRAY_OFFSET;
      if (initialSize < 0) {
         throw new IllegalArgumentException("Size must be non-negative");
      } else if (initialSize > 2147483632) {
         throw new IllegalArgumentException("Size " + initialSize + " exceeded maximum size of 2147483632");
      } else {
         this.buffer = new byte[initialSize];
      }
   }

   private void grow(int neededSize) {
      if (neededSize > 2147483632 - this.totalSize()) {
         throw new UnsupportedOperationException("Cannot grow internal buffer by size " + neededSize + " because the size after growing exceeds size limitation 2147483632");
      } else {
         int length = this.totalSize() + neededSize;
         if (this.buffer.length < length) {
            int newLength = length < 1073741816 ? length * 2 : 2147483632;
            byte[] tmp = new byte[newLength];
            Platform.copyMemory(this.buffer, (long)Platform.BYTE_ARRAY_OFFSET, tmp, (long)Platform.BYTE_ARRAY_OFFSET, (long)this.totalSize());
            this.buffer = tmp;
         }

      }
   }

   private int totalSize() {
      return this.cursor - Platform.BYTE_ARRAY_OFFSET;
   }

   public void append(UTF8String value) {
      this.grow(value.numBytes());
      value.writeToMemory(this.buffer, (long)this.cursor);
      this.cursor += value.numBytes();
   }

   public void append(String value) {
      this.append(UTF8String.fromString(value));
   }

   public void appendBytes(Object base, long offset, int length) {
      this.grow(length);
      Platform.copyMemory(base, offset, this.buffer, (long)this.cursor, (long)length);
      this.cursor += length;
   }

   public UTF8String build() {
      return UTF8String.fromBytes(this.buffer, 0, this.totalSize());
   }

   public void appendCodePoint(int codePoint) {
      if (codePoint <= 127) {
         this.grow(1);
         this.buffer[this.cursor - Platform.BYTE_ARRAY_OFFSET] = (byte)codePoint;
         ++this.cursor;
      } else if (codePoint <= 2047) {
         this.grow(2);
         this.buffer[this.cursor - Platform.BYTE_ARRAY_OFFSET] = (byte)(192 | codePoint >> 6);
         this.buffer[this.cursor + 1 - Platform.BYTE_ARRAY_OFFSET] = (byte)(128 | codePoint & 63);
         this.cursor += 2;
      } else if (codePoint <= 65535) {
         this.grow(3);
         this.buffer[this.cursor - Platform.BYTE_ARRAY_OFFSET] = (byte)(224 | codePoint >> 12);
         this.buffer[this.cursor + 1 - Platform.BYTE_ARRAY_OFFSET] = (byte)(128 | codePoint >> 6 & 63);
         this.buffer[this.cursor + 2 - Platform.BYTE_ARRAY_OFFSET] = (byte)(128 | codePoint & 63);
         this.cursor += 3;
      } else {
         if (codePoint > 1114111) {
            throw new IllegalArgumentException("Invalid Unicode codePoint: " + codePoint);
         }

         this.grow(4);
         this.buffer[this.cursor - Platform.BYTE_ARRAY_OFFSET] = (byte)(240 | codePoint >> 18);
         this.buffer[this.cursor + 1 - Platform.BYTE_ARRAY_OFFSET] = (byte)(128 | codePoint >> 12 & 63);
         this.buffer[this.cursor + 2 - Platform.BYTE_ARRAY_OFFSET] = (byte)(128 | codePoint >> 6 & 63);
         this.buffer[this.cursor + 3 - Platform.BYTE_ARRAY_OFFSET] = (byte)(128 | codePoint & 63);
         this.cursor += 4;
      }

   }
}
