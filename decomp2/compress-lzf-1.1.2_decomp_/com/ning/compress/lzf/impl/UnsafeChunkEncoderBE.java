package com.ning.compress.lzf.impl;

import com.ning.compress.BufferRecycler;

public final class UnsafeChunkEncoderBE extends UnsafeChunkEncoder {
   public UnsafeChunkEncoderBE(int totalLength) {
      super(totalLength);
   }

   public UnsafeChunkEncoderBE(int totalLength, boolean bogus) {
      super(totalLength, bogus);
   }

   public UnsafeChunkEncoderBE(int totalLength, BufferRecycler bufferRecycler) {
      super(totalLength, bufferRecycler);
   }

   public UnsafeChunkEncoderBE(int totalLength, BufferRecycler bufferRecycler, boolean bogus) {
      super(totalLength, bufferRecycler, bogus);
   }

   protected int tryCompress(byte[] param1, int param2, int param3, byte[] param4, int param5) {
      // $FF: Couldn't be decompiled
   }

   private static final int _getInt(byte[] in, int inPos) {
      return unsafe.getInt(in, BYTE_ARRAY_OFFSET + (long)inPos);
   }

   private static final int _findMatchLength(byte[] in, int ptr1, int ptr2, int maxPtr1) {
      if (ptr1 + 8 >= maxPtr1) {
         return _findTailMatchLength(in, ptr1, ptr2, maxPtr1);
      } else {
         int i1 = unsafe.getInt(in, BYTE_ARRAY_OFFSET + (long)ptr1);
         int i2 = unsafe.getInt(in, BYTE_ARRAY_OFFSET + (long)ptr2);
         if (i1 != i2) {
            return 1 + _leadingBytes(i1, i2);
         } else {
            ptr1 += 4;
            ptr2 += 4;
            i1 = unsafe.getInt(in, BYTE_ARRAY_OFFSET + (long)ptr1);
            i2 = unsafe.getInt(in, BYTE_ARRAY_OFFSET + (long)ptr2);
            return i1 != i2 ? 5 + _leadingBytes(i1, i2) : _findLongMatchLength(in, ptr1 + 4, ptr2 + 4, maxPtr1);
         }
      }
   }

   private static final int _findLongMatchLength(byte[] in, int ptr1, int ptr2, int maxPtr1) {
      int base = ptr1 - 9;

      for(int longEnd = maxPtr1 - 8; ptr1 <= longEnd; ptr2 += 8) {
         long l1 = unsafe.getLong(in, BYTE_ARRAY_OFFSET + (long)ptr1);
         long l2 = unsafe.getLong(in, BYTE_ARRAY_OFFSET + (long)ptr2);
         if (l1 != l2) {
            return ptr1 - base + _leadingBytes(l1, l2);
         }

         ptr1 += 8;
      }

      while(ptr1 < maxPtr1 && in[ptr1] == in[ptr2]) {
         ++ptr1;
         ++ptr2;
      }

      return ptr1 - base;
   }

   private static final int _leadingBytes(int i1, int i2) {
      return Integer.numberOfLeadingZeros(i1 ^ i2) >> 3;
   }

   private static final int _leadingBytes(long l1, long l2) {
      return Long.numberOfLeadingZeros(l1 ^ l2) >> 3;
   }
}
