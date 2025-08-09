package org.apache.orc.impl;

import java.util.Arrays;

public final class DynamicIntArray {
   static final int DEFAULT_CHUNKSIZE = 8192;
   static final int INIT_CHUNKS = 128;
   private final int chunkSize;
   private int[][] data;
   private int length;
   private int initializedChunks;

   public DynamicIntArray() {
      this(8192);
   }

   public DynamicIntArray(int chunkSize) {
      this.initializedChunks = 0;
      this.chunkSize = chunkSize;
      this.data = new int[128][];
   }

   private void grow(int chunkIndex) {
      if (chunkIndex >= this.initializedChunks) {
         if (chunkIndex >= this.data.length) {
            int newSize = Math.max(chunkIndex + 1, 2 * this.data.length);
            this.data = (int[][])Arrays.copyOf(this.data, newSize);
         }

         for(int i = this.initializedChunks; i <= chunkIndex; ++i) {
            this.data[i] = new int[this.chunkSize];
         }

         this.initializedChunks = chunkIndex + 1;
      }

   }

   public int get(int index) {
      if (index >= 0 && index < this.length) {
         int i = index / this.chunkSize;
         int j = index % this.chunkSize;
         return this.data[i][j];
      } else {
         throw new IndexOutOfBoundsException("Index " + index + " is outside of 0.." + (this.length - 1));
      }
   }

   public void set(int index, int value) {
      int i = index / this.chunkSize;
      int j = index % this.chunkSize;
      this.grow(i);
      if (index >= this.length) {
         this.length = index + 1;
      }

      this.data[i][j] = value;
   }

   public void increment(int index, int value) {
      int i = index / this.chunkSize;
      int j = index % this.chunkSize;
      this.grow(i);
      if (index >= this.length) {
         this.length = index + 1;
      }

      int[] var10000 = this.data[i];
      var10000[j] += value;
   }

   public void add(int value) {
      int i = this.length / this.chunkSize;
      int j = this.length % this.chunkSize;
      this.grow(i);
      this.data[i][j] = value;
      ++this.length;
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

   public String toString() {
      int l = this.length - 1;
      if (l == -1) {
         return "{}";
      } else {
         StringBuilder sb = new StringBuilder(this.length * 4);
         sb.append('{');

         for(int i = 0; i <= l; ++i) {
            sb.append(this.get(i));
            if (i != l) {
               sb.append(",");
            }
         }

         return sb.append('}').toString();
      }
   }

   public int getSizeInBytes() {
      return 4 * this.initializedChunks * this.chunkSize;
   }
}
