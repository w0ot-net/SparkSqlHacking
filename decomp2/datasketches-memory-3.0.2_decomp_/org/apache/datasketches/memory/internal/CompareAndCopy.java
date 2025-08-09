package org.apache.datasketches.memory.internal;

final class CompareAndCopy {
   private CompareAndCopy() {
   }

   static int compare(ResourceImpl state1, long offsetBytes1, long lengthBytes1, ResourceImpl state2, long offsetBytes2, long lengthBytes2) {
      state1.checkValid();
      ResourceImpl.checkBounds(offsetBytes1, lengthBytes1, state1.getCapacity());
      state2.checkValid();
      ResourceImpl.checkBounds(offsetBytes2, lengthBytes2, state2.getCapacity());
      long cumOff1 = state1.getCumulativeOffset(offsetBytes1);
      long cumOff2 = state2.getCumulativeOffset(offsetBytes2);
      Object arr1 = state1.getUnsafeObject();
      Object arr2 = state2.getUnsafeObject();
      if (arr1 != arr2 || cumOff1 != cumOff2) {
         long lenBytes = Math.min(lengthBytes1, lengthBytes2);

         for(long i = 0L; i < lenBytes; ++i) {
            int byte1 = UnsafeUtil.unsafe.getByte(arr1, cumOff1 + i);
            int byte2 = UnsafeUtil.unsafe.getByte(arr2, cumOff2 + i);
            if (byte1 < byte2) {
               return -1;
            }

            if (byte1 > byte2) {
               return 1;
            }
         }
      }

      return Long.compare(lengthBytes1, lengthBytes2);
   }

   static boolean equals(ResourceImpl state1, ResourceImpl state2) {
      long cap1 = state1.getCapacity();
      long cap2 = state2.getCapacity();
      return cap1 == cap2 && equals(state1, 0L, state2, 0L, cap1);
   }

   static boolean equals(ResourceImpl state1, long offsetBytes1, ResourceImpl state2, long offsetBytes2, long lengthBytes) {
      state1.checkValid();
      ResourceImpl.checkBounds(offsetBytes1, lengthBytes, state1.getCapacity());
      state2.checkValid();
      ResourceImpl.checkBounds(offsetBytes2, lengthBytes, state2.getCapacity());
      long cumOff1 = state1.getCumulativeOffset(offsetBytes1);
      long cumOff2 = state2.getCumulativeOffset(offsetBytes2);
      Object arr1 = state1.getUnsafeObject();
      Object arr2 = state2.getUnsafeObject();
      if (arr1 == arr2 && cumOff1 == cumOff2) {
         return true;
      } else {
         while(lengthBytes >= 8L) {
            int chunk = (int)Math.min(lengthBytes, 1048576L);

            int i;
            for(i = 0; i <= chunk - 8; i += 8) {
               long v1 = UnsafeUtil.unsafe.getLong(arr1, cumOff1 + (long)i);
               long v2 = UnsafeUtil.unsafe.getLong(arr2, cumOff2 + (long)i);
               if (v1 != v2) {
                  return false;
               }
            }

            lengthBytes -= (long)i;
            cumOff1 += (long)i;
            cumOff2 += (long)i;
         }

         return lengthBytes == 0L || equalsByBytes(arr1, cumOff1, arr2, cumOff2, (int)lengthBytes);
      }
   }

   private static boolean equalsByBytes(Object arr1, long cumOff1, Object arr2, long cumOff2, int lenBytes) {
      for(int i = 0; i < lenBytes; ++i) {
         int v1 = UnsafeUtil.unsafe.getByte(arr1, cumOff1 + (long)i);
         int v2 = UnsafeUtil.unsafe.getByte(arr2, cumOff2 + (long)i);
         if (v1 != v2) {
            return false;
         }
      }

      return true;
   }

   static void copy(ResourceImpl srcState, long srcOffsetBytes, ResourceImpl dstState, long dstOffsetBytes, long lengthBytes) {
      srcState.checkValid();
      ResourceImpl.checkBounds(srcOffsetBytes, lengthBytes, srcState.getCapacity());
      dstState.checkValid();
      ResourceImpl.checkBounds(dstOffsetBytes, lengthBytes, dstState.getCapacity());
      long srcAdd = srcState.getCumulativeOffset(srcOffsetBytes);
      long dstAdd = dstState.getCumulativeOffset(dstOffsetBytes);
      copyMemory(srcState.getUnsafeObject(), srcAdd, dstState.getUnsafeObject(), dstAdd, lengthBytes);
   }

   static final void copyMemoryCheckingDifferentObject(Object srcUnsafeObj, long srcAdd, Object dstUnsafeObj, long dstAdd, long lengthBytes) {
      if (srcUnsafeObj != dstUnsafeObj) {
         copyNonOverlappingMemoryWithChunking(srcUnsafeObj, srcAdd, dstUnsafeObj, dstAdd, lengthBytes);
      } else {
         throw new IllegalArgumentException("Not expecting to copy to/from array which is the underlying object of the memory at the same time");
      }
   }

   private static void copyMemory(Object srcUnsafeObj, long srcAdd, Object dstUnsafeObj, long dstAdd, long lengthBytes) {
      if (srcUnsafeObj != dstUnsafeObj) {
         copyNonOverlappingMemoryWithChunking(srcUnsafeObj, srcAdd, dstUnsafeObj, dstAdd, lengthBytes);
      } else {
         copyMemoryOverlapAddressCheck(srcUnsafeObj, srcAdd, dstUnsafeObj, dstAdd, lengthBytes);
      }

   }

   private static void copyMemoryOverlapAddressCheck(Object srcUnsafeObj, long srcAdd, Object dstUnsafeObj, long dstAdd, long lengthBytes) {
      if (srcAdd + lengthBytes > dstAdd && dstAdd + lengthBytes > srcAdd) {
         if (srcAdd == dstAdd) {
            throw new IllegalArgumentException("Attempt to copy a block of memory exactly in-place, should be a bug");
         } else {
            UnsafeUtil.unsafe.copyMemory(srcUnsafeObj, srcAdd, dstUnsafeObj, dstAdd, lengthBytes);
         }
      } else {
         copyNonOverlappingMemoryWithChunking(srcUnsafeObj, srcAdd, dstUnsafeObj, dstAdd, lengthBytes);
      }
   }

   private static void copyNonOverlappingMemoryWithChunking(Object srcUnsafeObj, long srcAdd, Object dstUnsafeObj, long dstAdd, long lengthBytes) {
      while(lengthBytes > 0L) {
         long chunk = Math.min(lengthBytes, 1048576L);
         UnsafeUtil.unsafe.copyMemory(srcUnsafeObj, srcAdd, dstUnsafeObj, dstAdd, chunk);
         lengthBytes -= chunk;
         srcAdd += chunk;
         dstAdd += chunk;
      }

   }

   static void getNonNativeChars(Object unsafeObj, long cumOffsetBytes, long copyBytes, char[] dstArray, int dstOffsetChars, int lengthChars) {
      ResourceImpl.checkBounds((long)dstOffsetChars, (long)lengthChars, (long)dstArray.length);

      while(copyBytes > 1048576L) {
         long chunkBytes = Math.min(copyBytes, 1048576L);
         int chunkChars = (int)(chunkBytes >> 1);
         getCharArrayChunk(unsafeObj, cumOffsetBytes, dstArray, dstOffsetChars, chunkChars);
         cumOffsetBytes += chunkBytes;
         dstOffsetChars += chunkChars;
         copyBytes -= chunkBytes;
         lengthChars -= chunkChars;
      }

      getCharArrayChunk(unsafeObj, cumOffsetBytes, dstArray, dstOffsetChars, lengthChars);
   }

   private static void getCharArrayChunk(Object unsafeObj, long cumOffsetBytes, char[] dstArray, int dstOffsetChars, int lengthChars) {
      for(int i = 0; i < lengthChars; ++i) {
         dstArray[dstOffsetChars + i] = Character.reverseBytes(UnsafeUtil.unsafe.getChar(unsafeObj, cumOffsetBytes + ((long)i << 1)));
      }

   }

   static void getNonNativeDoubles(Object unsafeObj, long cumOffsetBytes, long copyBytes, double[] dstArray, int dstOffsetDoubles, int lengthDoubles) {
      ResourceImpl.checkBounds((long)dstOffsetDoubles, (long)lengthDoubles, (long)dstArray.length);

      while(copyBytes > 1048576L) {
         long chunkBytes = Math.min(copyBytes, 1048576L);
         int chunkDoubles = (int)(chunkBytes >> 3);
         getDoubleArrayChunk(unsafeObj, cumOffsetBytes, dstArray, dstOffsetDoubles, chunkDoubles);
         cumOffsetBytes += chunkBytes;
         dstOffsetDoubles += chunkDoubles;
         copyBytes -= chunkBytes;
         lengthDoubles -= chunkDoubles;
      }

      getDoubleArrayChunk(unsafeObj, cumOffsetBytes, dstArray, dstOffsetDoubles, lengthDoubles);
   }

   private static void getDoubleArrayChunk(Object unsafeObj, long cumOffsetBytes, double[] dstArray, int dstOffsetDoubles, int lengthDoubles) {
      for(int i = 0; i < lengthDoubles; ++i) {
         dstArray[dstOffsetDoubles + i] = Double.longBitsToDouble(Long.reverseBytes(UnsafeUtil.unsafe.getLong(unsafeObj, cumOffsetBytes + ((long)i << 3))));
      }

   }

   static void getNonNativeFloats(Object unsafeObj, long cumOffsetBytes, long copyBytes, float[] dstArray, int dstOffsetFloats, int lengthFloats) {
      ResourceImpl.checkBounds((long)dstOffsetFloats, (long)lengthFloats, (long)dstArray.length);

      while(copyBytes > 1048576L) {
         long chunkBytes = Math.min(copyBytes, 1048576L);
         int chunkFloats = (int)(chunkBytes >> 2);
         getFloatArrayChunk(unsafeObj, cumOffsetBytes, dstArray, dstOffsetFloats, chunkFloats);
         cumOffsetBytes += chunkBytes;
         dstOffsetFloats += chunkFloats;
         copyBytes -= chunkBytes;
         lengthFloats -= chunkFloats;
      }

      getFloatArrayChunk(unsafeObj, cumOffsetBytes, dstArray, dstOffsetFloats, lengthFloats);
   }

   private static void getFloatArrayChunk(Object unsafeObj, long cumOffsetBytes, float[] dstArray, int dstOffsetFloats, int lengthFloats) {
      for(int i = 0; i < lengthFloats; ++i) {
         dstArray[dstOffsetFloats + i] = Float.intBitsToFloat(Integer.reverseBytes(UnsafeUtil.unsafe.getInt(unsafeObj, cumOffsetBytes + ((long)i << 2))));
      }

   }

   static void getNonNativeInts(Object unsafeObj, long cumOffsetBytes, long copyBytes, int[] dstArray, int dstOffsetInts, int lengthInts) {
      ResourceImpl.checkBounds((long)dstOffsetInts, (long)lengthInts, (long)dstArray.length);

      while(copyBytes > 1048576L) {
         long chunkBytes = Math.min(copyBytes, 1048576L);
         int chunkInts = (int)(chunkBytes >> 2);
         getIntArrayChunk(unsafeObj, cumOffsetBytes, dstArray, dstOffsetInts, chunkInts);
         cumOffsetBytes += chunkBytes;
         dstOffsetInts += chunkInts;
         copyBytes -= chunkBytes;
         lengthInts -= chunkInts;
      }

      getIntArrayChunk(unsafeObj, cumOffsetBytes, dstArray, dstOffsetInts, lengthInts);
   }

   private static void getIntArrayChunk(Object unsafeObj, long cumOffsetBytes, int[] dstArray, int dstOffsetInts, int lengthInts) {
      for(int i = 0; i < lengthInts; ++i) {
         dstArray[dstOffsetInts + i] = Integer.reverseBytes(UnsafeUtil.unsafe.getInt(unsafeObj, cumOffsetBytes + ((long)i << 2)));
      }

   }

   static void getNonNativeLongs(Object unsafeObj, long cumOffsetBytes, long copyBytes, long[] dstArray, int dstOffsetLongs, int lengthLongs) {
      ResourceImpl.checkBounds((long)dstOffsetLongs, (long)lengthLongs, (long)dstArray.length);

      while(copyBytes > 1048576L) {
         long chunkBytes = Math.min(copyBytes, 1048576L);
         int chunkLongs = (int)(chunkBytes >> 3);
         getLongArrayChunk(unsafeObj, cumOffsetBytes, dstArray, dstOffsetLongs, chunkLongs);
         cumOffsetBytes += chunkBytes;
         dstOffsetLongs += chunkLongs;
         copyBytes -= chunkBytes;
         lengthLongs -= chunkLongs;
      }

      getLongArrayChunk(unsafeObj, cumOffsetBytes, dstArray, dstOffsetLongs, lengthLongs);
   }

   private static void getLongArrayChunk(Object unsafeObj, long cumOffsetBytes, long[] dstArray, int dstOffsetLongs, int lengthLongs) {
      for(int i = 0; i < lengthLongs; ++i) {
         dstArray[dstOffsetLongs + i] = Long.reverseBytes(UnsafeUtil.unsafe.getLong(unsafeObj, cumOffsetBytes + ((long)i << 3)));
      }

   }

   static void getNonNativeShorts(Object unsafeObj, long cumOffsetBytes, long copyBytes, short[] dstArray, int dstOffsetShorts, int lengthShorts) {
      ResourceImpl.checkBounds((long)dstOffsetShorts, (long)lengthShorts, (long)dstArray.length);

      while(copyBytes > 1048576L) {
         long chunkBytes = Math.min(copyBytes, 1048576L);
         int chunkShorts = (int)(chunkBytes >> 1);
         getShortArrayChunk(unsafeObj, cumOffsetBytes, dstArray, dstOffsetShorts, chunkShorts);
         cumOffsetBytes += chunkBytes;
         dstOffsetShorts += chunkShorts;
         copyBytes -= chunkBytes;
         lengthShorts -= chunkShorts;
      }

      getShortArrayChunk(unsafeObj, cumOffsetBytes, dstArray, dstOffsetShorts, lengthShorts);
   }

   private static void getShortArrayChunk(Object unsafeObj, long cumOffsetBytes, short[] dstArray, int dstOffsetShorts, int lengthShorts) {
      for(int i = 0; i < lengthShorts; ++i) {
         dstArray[dstOffsetShorts + i] = Short.reverseBytes(UnsafeUtil.unsafe.getShort(unsafeObj, cumOffsetBytes + ((long)i << 1)));
      }

   }

   static void putNonNativeChars(char[] srcArray, int srcOffsetChars, int lengthChars, long copyBytes, Object unsafeObj, long cumOffsetBytes) {
      ResourceImpl.checkBounds((long)srcOffsetChars, (long)lengthChars, (long)srcArray.length);

      while(copyBytes > 1048576L) {
         long chunkBytes = Math.min(copyBytes, 1048576L);
         int chunkChars = (int)(chunkBytes >> 1);
         putCharArrayChunk(srcArray, srcOffsetChars, chunkChars, unsafeObj, cumOffsetBytes);
         cumOffsetBytes += chunkBytes;
         srcOffsetChars += chunkChars;
         copyBytes -= chunkBytes;
         lengthChars -= chunkChars;
      }

      putCharArrayChunk(srcArray, srcOffsetChars, lengthChars, unsafeObj, cumOffsetBytes);
   }

   private static void putCharArrayChunk(char[] srcArray, int srcOffsetChars, int lengthChars, Object unsafeObj, long cumOffsetBytes) {
      for(int i = 0; i < lengthChars; ++i) {
         UnsafeUtil.unsafe.putChar(unsafeObj, cumOffsetBytes + ((long)i << 1), Character.reverseBytes(srcArray[srcOffsetChars + i]));
      }

   }

   static void putNonNativeDoubles(double[] srcArray, int srcOffsetDoubles, int lengthDoubles, long copyBytes, Object unsafeObj, long cumOffsetBytes) {
      ResourceImpl.checkBounds((long)srcOffsetDoubles, (long)lengthDoubles, (long)srcArray.length);

      while(copyBytes > 1048576L) {
         long chunkBytes = Math.min(copyBytes, 1048576L);
         int chunkDoubles = (int)(chunkBytes >> 3);
         putDoubleArrayChunk(srcArray, srcOffsetDoubles, chunkDoubles, unsafeObj, cumOffsetBytes);
         cumOffsetBytes += chunkBytes;
         srcOffsetDoubles += chunkDoubles;
         copyBytes -= chunkBytes;
         lengthDoubles -= chunkDoubles;
      }

      putDoubleArrayChunk(srcArray, srcOffsetDoubles, lengthDoubles, unsafeObj, cumOffsetBytes);
   }

   private static void putDoubleArrayChunk(double[] srcArray, int srcOffsetDoubles, int lengthDoubles, Object unsafeObj, long cumOffsetBytes) {
      for(int i = 0; i < lengthDoubles; ++i) {
         UnsafeUtil.unsafe.putLong(unsafeObj, cumOffsetBytes + ((long)i << 3), Long.reverseBytes(Double.doubleToRawLongBits(srcArray[srcOffsetDoubles + i])));
      }

   }

   static void putNonNativeFloats(float[] srcArray, int srcOffsetFloats, int lengthFloats, long copyBytes, Object unsafeObj, long cumOffsetBytes) {
      ResourceImpl.checkBounds((long)srcOffsetFloats, (long)lengthFloats, (long)srcArray.length);

      while(copyBytes > 1048576L) {
         long chunkBytes = Math.min(copyBytes, 1048576L);
         int chunkFloats = (int)(chunkBytes >> 2);
         putFloatArrayChunk(srcArray, srcOffsetFloats, chunkFloats, unsafeObj, cumOffsetBytes);
         cumOffsetBytes += chunkBytes;
         srcOffsetFloats += chunkFloats;
         copyBytes -= chunkBytes;
         lengthFloats -= chunkFloats;
      }

      putFloatArrayChunk(srcArray, srcOffsetFloats, lengthFloats, unsafeObj, cumOffsetBytes);
   }

   private static void putFloatArrayChunk(float[] srcArray, int srcOffsetFloats, int lengthFloats, Object unsafeObj, long cumOffsetBytes) {
      for(int i = 0; i < lengthFloats; ++i) {
         UnsafeUtil.unsafe.putInt(unsafeObj, cumOffsetBytes + ((long)i << 2), Integer.reverseBytes(Float.floatToRawIntBits(srcArray[srcOffsetFloats + i])));
      }

   }

   static void putNonNativeInts(int[] srcArray, int srcOffsetInts, int lengthInts, long copyBytes, Object unsafeObj, long cumOffsetBytes) {
      ResourceImpl.checkBounds((long)srcOffsetInts, (long)lengthInts, (long)srcArray.length);

      while(copyBytes > 1048576L) {
         long chunkBytes = Math.min(copyBytes, 1048576L);
         int chunkInts = (int)(chunkBytes >> 2);
         putIntArrayChunk(srcArray, srcOffsetInts, chunkInts, unsafeObj, cumOffsetBytes);
         cumOffsetBytes += chunkBytes;
         srcOffsetInts += chunkInts;
         copyBytes -= chunkBytes;
         lengthInts -= chunkInts;
      }

      putIntArrayChunk(srcArray, srcOffsetInts, lengthInts, unsafeObj, cumOffsetBytes);
   }

   private static void putIntArrayChunk(int[] srcArray, int srcOffsetInts, int lengthInts, Object unsafeObj, long cumOffsetBytes) {
      for(int i = 0; i < lengthInts; ++i) {
         UnsafeUtil.unsafe.putInt(unsafeObj, cumOffsetBytes + ((long)i << 2), Integer.reverseBytes(srcArray[srcOffsetInts + i]));
      }

   }

   static void putNonNativeLongs(long[] srcArray, int srcOffsetLongs, int lengthLongs, long copyBytes, Object unsafeObj, long cumOffsetBytes) {
      ResourceImpl.checkBounds((long)srcOffsetLongs, (long)lengthLongs, (long)srcArray.length);

      while(copyBytes > 1048576L) {
         long chunkBytes = Math.min(copyBytes, 1048576L);
         int chunkLongs = (int)(chunkBytes >> 3);
         putLongArrayChunk(srcArray, srcOffsetLongs, chunkLongs, unsafeObj, cumOffsetBytes);
         cumOffsetBytes += chunkBytes;
         srcOffsetLongs += chunkLongs;
         copyBytes -= chunkBytes;
         lengthLongs -= chunkLongs;
      }

      putLongArrayChunk(srcArray, srcOffsetLongs, lengthLongs, unsafeObj, cumOffsetBytes);
   }

   private static void putLongArrayChunk(long[] srcArray, int srcOffsetLongs, int lengthLongs, Object unsafeObj, long cumOffsetBytes) {
      for(int i = 0; i < lengthLongs; ++i) {
         UnsafeUtil.unsafe.putLong(unsafeObj, cumOffsetBytes + ((long)i << 3), Long.reverseBytes(srcArray[srcOffsetLongs + i]));
      }

   }

   static void putNonNativeShorts(short[] srcArray, int srcOffsetShorts, int lengthShorts, long copyBytes, Object unsafeObj, long cumOffsetBytes) {
      ResourceImpl.checkBounds((long)srcOffsetShorts, (long)lengthShorts, (long)srcArray.length);

      while(copyBytes > 1048576L) {
         long chunkBytes = Math.min(copyBytes, 1048576L);
         int chunkShorts = (int)(chunkBytes >> 1);
         putShortArrayChunk(srcArray, srcOffsetShorts, chunkShorts, unsafeObj, cumOffsetBytes);
         cumOffsetBytes += chunkBytes;
         srcOffsetShorts += chunkShorts;
         copyBytes -= chunkBytes;
         lengthShorts -= chunkShorts;
      }

      putShortArrayChunk(srcArray, srcOffsetShorts, lengthShorts, unsafeObj, cumOffsetBytes);
   }

   private static void putShortArrayChunk(short[] srcArray, int srcOffsetShorts, int lengthShorts, Object unsafeObj, long cumOffsetBytes) {
      for(int i = 0; i < lengthShorts; ++i) {
         UnsafeUtil.unsafe.putShort(unsafeObj, cumOffsetBytes + ((long)i << 1), Short.reverseBytes(srcArray[srcOffsetShorts + i]));
      }

   }
}
