package org.xerial.snappy.pool;

import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentMap;

public final class CachingBufferPool implements BufferPool {
   private static final IntFunction ARRAY_FUNCTION = new IntFunction() {
      public byte[] create(int var1) {
         return new byte[var1];
      }
   };
   private static final IntFunction DBB_FUNCTION = new IntFunction() {
      public ByteBuffer create(int var1) {
         return ByteBuffer.allocateDirect(var1);
      }
   };
   private static final CachingBufferPool INSTANCE = new CachingBufferPool();
   private final ConcurrentMap bytes = new ConcurrentHashMap();
   private final ConcurrentMap buffers = new ConcurrentHashMap();

   private CachingBufferPool() {
   }

   public static BufferPool getInstance() {
      return INSTANCE;
   }

   public byte[] allocateArray(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("size is invalid: " + var1);
      } else {
         return (byte[])getOrCreate(var1, this.bytes, ARRAY_FUNCTION);
      }
   }

   public void releaseArray(byte[] var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("buffer is null");
      } else {
         returnValue(var1, var1.length, this.bytes);
      }
   }

   public ByteBuffer allocateDirect(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("size is invalid: " + var1);
      } else {
         return (ByteBuffer)getOrCreate(var1, this.buffers, DBB_FUNCTION);
      }
   }

   public void releaseDirect(ByteBuffer var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("buffer is null");
      } else {
         var1.clear();
         returnValue(var1, var1.capacity(), this.buffers);
      }
   }

   private static Object getOrCreate(int var0, ConcurrentMap var1, IntFunction var2) {
      assert var0 > 0;

      int var3 = adjustSize(var0);
      ConcurrentLinkedDeque var4 = optimisticGetEntry(var3, var1);

      SoftReference var5;
      while((var5 = (SoftReference)var4.pollFirst()) != null) {
         Object var6 = var5.get();
         if (var6 != null) {
            return var6;
         }
      }

      return var2.create(var3);
   }

   static int adjustSize(int var0) {
      assert var0 > 0;

      switch (Integer.numberOfLeadingZeros(var0)) {
         case 1:
         case 2:
            return var0 <= 1610612736 ? roundToPowers(var0, 27) : Integer.MAX_VALUE;
         case 3:
         case 4:
            return roundToPowers(var0, 24);
         case 5:
         case 6:
         case 7:
            return roundToPowers(var0, 22);
         case 8:
         case 9:
         case 10:
            return roundToPowers(var0, 19);
         case 11:
         case 12:
            return roundToPowers(var0, 17);
         case 13:
         case 14:
         case 15:
         case 16:
            return roundToPowers(var0, 14);
         case 17:
         case 18:
         case 19:
            return roundToPowers(var0, 11);
         default:
            return 4096;
      }
   }

   private static int roundToPowers(int var0, int var1) {
      int var2 = Integer.MAX_VALUE >> var1 << var1;
      int var3 = var0 & var2;
      return var3 == var0 ? var0 : var3 + (1 << var1);
   }

   private static ConcurrentLinkedDeque optimisticGetEntry(Integer var0, ConcurrentMap var1) {
      ConcurrentLinkedDeque var2 = (ConcurrentLinkedDeque)var1.get(var0);
      if (var2 == null) {
         var1.putIfAbsent(var0, new ConcurrentLinkedDeque());
         var2 = (ConcurrentLinkedDeque)var1.get(var0);
      }

      return var2;
   }

   private static void returnValue(Object var0, Integer var1, ConcurrentMap var2) {
      ConcurrentLinkedDeque var3 = (ConcurrentLinkedDeque)var2.get(var1);
      if (var3 != null) {
         var3.addFirst(new SoftReference(var0));
         boolean var5 = true;

         SoftReference var4;
         while(var5 && (var4 = (SoftReference)var3.peekLast()) != null) {
            if (var4.get() == null) {
               var3.removeLastOccurrence(var4);
            } else {
               var5 = false;
            }
         }
      }

   }

   public String toString() {
      return "CachingBufferPool [bytes=" + this.bytes + ", buffers=" + this.buffers + "]";
   }

   private interface IntFunction {
      Object create(int var1);
   }
}
