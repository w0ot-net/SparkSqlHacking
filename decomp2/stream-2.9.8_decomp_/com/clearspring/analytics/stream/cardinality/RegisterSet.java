package com.clearspring.analytics.stream.cardinality;

public class RegisterSet {
   public static final int LOG2_BITS_PER_WORD = 6;
   public static final int REGISTER_SIZE = 5;
   public final int count;
   public final int size;
   private final int[] M;

   public RegisterSet(int count) {
      this(count, (int[])null);
   }

   public RegisterSet(int count, int[] initialValues) {
      this.count = count;
      if (initialValues == null) {
         this.M = new int[getSizeForCount(count)];
      } else {
         this.M = initialValues;
      }

      this.size = this.M.length;
   }

   public static int getBits(int count) {
      return count / 6;
   }

   public static int getSizeForCount(int count) {
      int bits = getBits(count);
      if (bits == 0) {
         return 1;
      } else {
         return bits % 32 == 0 ? bits : bits + 1;
      }
   }

   public void set(int position, int value) {
      int bucketPos = position / 6;
      int shift = 5 * (position - bucketPos * 6);
      this.M[bucketPos] = this.M[bucketPos] & ~(31 << shift) | value << shift;
   }

   public int get(int position) {
      int bucketPos = position / 6;
      int shift = 5 * (position - bucketPos * 6);
      return (this.M[bucketPos] & 31 << shift) >>> shift;
   }

   public boolean updateIfGreater(int position, int value) {
      int bucket = position / 6;
      int shift = 5 * (position - bucket * 6);
      int mask = 31 << shift;
      long curVal = (long)(this.M[bucket] & mask);
      long newVal = (long)(value << shift);
      if (curVal < newVal) {
         this.M[bucket] = (int)((long)(this.M[bucket] & ~mask) | newVal);
         return true;
      } else {
         return false;
      }
   }

   public void merge(RegisterSet that) {
      for(int bucket = 0; bucket < this.M.length; ++bucket) {
         int word = 0;

         for(int j = 0; j < 6; ++j) {
            int mask = 31 << 5 * j;
            int thisVal = this.M[bucket] & mask;
            int thatVal = that.M[bucket] & mask;
            word |= thisVal < thatVal ? thatVal : thisVal;
         }

         this.M[bucket] = word;
      }

   }

   int[] readOnlyBits() {
      return this.M;
   }

   public int[] bits() {
      int[] copy = new int[this.size];
      System.arraycopy(this.M, 0, copy, 0, this.M.length);
      return copy;
   }
}
