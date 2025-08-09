package org.apache.orc.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.apache.hadoop.io.Text;
import org.apache.orc.OrcConf;

public final class DynamicByteArray {
   static final int DEFAULT_CHUNKSIZE = 32768;
   static final int DEFAULT_NUM_CHUNKS = 128;
   private final int chunkSize;
   private byte[][] data;
   private int length;
   private int initializedChunks;

   public DynamicByteArray() {
      this(128, 32768);
   }

   public DynamicByteArray(int numChunks, int chunkSize) {
      this.initializedChunks = 0;
      if (chunkSize == 0) {
         throw new IllegalArgumentException("bad chunksize");
      } else {
         this.chunkSize = chunkSize;
         this.data = new byte[numChunks][];
      }
   }

   private void grow(int chunkIndex) {
      if (chunkIndex < 0) {
         throw new RuntimeException(String.format("chunkIndex overflow:%d. You can set %s=columnName, or %s=0 to turn off dictionary encoding.", chunkIndex, OrcConf.DIRECT_ENCODING_COLUMNS.getAttribute(), OrcConf.DICTIONARY_KEY_SIZE_THRESHOLD.getAttribute()));
      } else {
         if (chunkIndex >= this.initializedChunks) {
            if (chunkIndex >= this.data.length) {
               int newSize = Math.max(chunkIndex + 1, 2 * this.data.length);
               this.data = (byte[][])Arrays.copyOf(this.data, newSize);
            }

            for(int i = this.initializedChunks; i <= chunkIndex; ++i) {
               this.data[i] = new byte[this.chunkSize];
            }

            this.initializedChunks = chunkIndex + 1;
         }

      }
   }

   public byte get(int index) {
      if (index >= this.length) {
         throw new IndexOutOfBoundsException("Index " + index + " is outside of 0.." + (this.length - 1));
      } else {
         int i = index / this.chunkSize;
         int j = index % this.chunkSize;
         return this.data[i][j];
      }
   }

   public void set(int index, byte value) {
      int i = index / this.chunkSize;
      int j = index % this.chunkSize;
      this.grow(i);
      if (index >= this.length) {
         this.length = index + 1;
      }

      this.data[i][j] = value;
   }

   public int add(byte value) {
      int i = this.length / this.chunkSize;
      int j = this.length % this.chunkSize;
      this.grow(i);
      this.data[i][j] = value;
      int result = this.length++;
      return result;
   }

   public int add(byte[] value, int valueOffset, int valueLength) {
      int i = this.length / this.chunkSize;
      int j = this.length % this.chunkSize;
      this.grow((this.length + valueLength) / this.chunkSize);

      for(int remaining = valueLength; remaining > 0; j = 0) {
         int size = Math.min(remaining, this.chunkSize - j);
         System.arraycopy(value, valueOffset, this.data[i], j, size);
         remaining -= size;
         valueOffset += size;
         ++i;
      }

      int result = this.length;
      this.length += valueLength;
      return result;
   }

   public void readAll(InputStream in) throws IOException {
      int currentChunk = this.length / this.chunkSize;
      int currentOffset = this.length % this.chunkSize;
      this.grow(currentChunk);

      for(int currentLength = in.read(this.data[currentChunk], currentOffset, this.chunkSize - currentOffset); currentLength > 0; currentLength = in.read(this.data[currentChunk], currentOffset, this.chunkSize - currentOffset)) {
         this.length += currentLength;
         currentOffset = this.length % this.chunkSize;
         if (currentOffset == 0) {
            currentChunk = this.length / this.chunkSize;
            this.grow(currentChunk);
         }
      }

   }

   public int compare(byte[] other, int otherOffset, int otherLength, int ourOffset, int ourLength) {
      int currentChunk = ourOffset / this.chunkSize;
      int currentOffset = ourOffset % this.chunkSize;

      int maxLength;
      for(maxLength = Math.min(otherLength, ourLength); maxLength > 0 && other[otherOffset] == this.data[currentChunk][currentOffset]; --maxLength) {
         ++otherOffset;
         ++currentOffset;
         if (currentOffset == this.chunkSize) {
            ++currentChunk;
            currentOffset = 0;
         }
      }

      if (maxLength == 0) {
         return otherLength - ourLength;
      } else {
         int otherByte = 255 & other[otherOffset];
         int ourByte = 255 & this.data[currentChunk][currentOffset];
         return otherByte > ourByte ? 1 : -1;
      }
   }

   public int size() {
      return this.length;
   }

   public void clear() {
      this.length = 0;

      for(int i = 0; i < this.data.length; ++i) {
         this.data[i] = null;
      }

      this.initializedChunks = 0;
   }

   public void setText(Text result, int offset, int length) {
      result.clear();
      int currentChunk = offset / this.chunkSize;
      int currentOffset = offset % this.chunkSize;

      for(int currentLength = Math.min(length, this.chunkSize - currentOffset); length > 0; currentLength = Math.min(length, this.chunkSize - currentOffset)) {
         result.append(this.data[currentChunk], currentOffset, currentLength);
         length -= currentLength;
         ++currentChunk;
         currentOffset = 0;
      }

   }

   public void write(OutputStream out, int offset, int length) throws IOException {
      int currentChunk = offset / this.chunkSize;

      for(int currentOffset = offset % this.chunkSize; length > 0; currentOffset = 0) {
         int currentLength = Math.min(length, this.chunkSize - currentOffset);
         out.write(this.data[currentChunk], currentOffset, currentLength);
         length -= currentLength;
         ++currentChunk;
      }

   }

   public String toString() {
      StringBuilder sb = new StringBuilder(this.length * 3);
      sb.append('{');
      int l = this.length - 1;

      int i;
      for(i = 0; i < l; ++i) {
         sb.append(Integer.toHexString(this.get(i)));
         sb.append(',');
      }

      sb.append(this.get(i));
      sb.append('}');
      return sb.toString();
   }

   public void setByteBuffer(ByteBuffer result, int offset, int length) {
      result.clear();
      int currentChunk = offset / this.chunkSize;
      int currentOffset = offset % this.chunkSize;

      for(int currentLength = Math.min(length, this.chunkSize - currentOffset); length > 0; currentLength = Math.min(length, this.chunkSize - currentOffset)) {
         result.put(this.data[currentChunk], currentOffset, currentLength);
         length -= currentLength;
         ++currentChunk;
         currentOffset = 0;
      }

   }

   public byte[] get() {
      byte[] result = null;
      if (this.length > 0) {
         int currentChunk = 0;
         int currentOffset = 0;
         int currentLength = Math.min(this.length, this.chunkSize);
         int destOffset = 0;
         result = new byte[this.length];

         for(int totalLength = this.length; totalLength > 0; currentLength = Math.min(totalLength, this.chunkSize - currentOffset)) {
            System.arraycopy(this.data[currentChunk], currentOffset, result, destOffset, currentLength);
            destOffset += currentLength;
            totalLength -= currentLength;
            ++currentChunk;
            currentOffset = 0;
         }
      }

      return result;
   }

   public ByteBuffer get(int offset, int length) {
      int currentChunk = offset / this.chunkSize;
      int currentOffset = offset % this.chunkSize;
      int currentLength = Math.min(length, this.chunkSize - currentOffset);
      if (currentLength == length) {
         return ByteBuffer.wrap(this.data[currentChunk], currentOffset, length);
      } else {
         ByteBuffer bb = ByteBuffer.allocate(length);
         this.setByteBuffer(bb, offset, length);
         return bb.flip();
      }
   }

   public long getSizeInBytes() {
      return (long)this.initializedChunks * (long)this.chunkSize;
   }
}
