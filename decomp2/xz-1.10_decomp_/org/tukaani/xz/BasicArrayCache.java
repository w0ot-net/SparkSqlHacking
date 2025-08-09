package org.tukaani.xz;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class BasicArrayCache extends ArrayCache {
   private static final int CACHEABLE_SIZE_MIN = 32768;
   private static final int STACKS_MAX = 32;
   private static final int ELEMENTS_PER_STACK = 512;
   private final CacheMap byteArrayCache = new CacheMap();
   private final CacheMap intArrayCache = new CacheMap();

   public static BasicArrayCache getInstance() {
      return BasicArrayCache.LazyHolder.INSTANCE;
   }

   private static Object getArray(CacheMap cache, int size) {
      if (size < 32768) {
         return null;
      } else {
         CyclicStack<Reference<T>> stack;
         synchronized(cache) {
            stack = (CyclicStack)cache.get(size);
         }

         if (stack == null) {
            return null;
         } else {
            T array;
            do {
               Reference<T> r = (Reference)stack.pop();
               if (r == null) {
                  return null;
               }

               array = (T)r.get();
            } while(array == null);

            return array;
         }
      }
   }

   private static void putArray(CacheMap cache, Object array, int size) {
      if (size >= 32768) {
         CyclicStack<Reference<T>> stack;
         synchronized(cache) {
            stack = (CyclicStack)cache.get(size);
            if (stack == null) {
               stack = new CyclicStack();
               cache.put(size, stack);
            }
         }

         stack.push(new SoftReference(array));
      }
   }

   public byte[] getByteArray(int size, boolean fillWithZeros) {
      byte[] array = (byte[])getArray(this.byteArrayCache, size);
      if (array == null) {
         array = new byte[size];
      } else if (fillWithZeros) {
         Arrays.fill(array, (byte)0);
      }

      return array;
   }

   public void putArray(byte[] array) {
      putArray(this.byteArrayCache, array, array.length);
   }

   public int[] getIntArray(int size, boolean fillWithZeros) {
      int[] array = (int[])getArray(this.intArrayCache, size);
      if (array == null) {
         array = new int[size];
      } else if (fillWithZeros) {
         Arrays.fill(array, 0);
      }

      return array;
   }

   public void putArray(int[] array) {
      putArray(this.intArrayCache, array, array.length);
   }

   private static class CyclicStack {
      private final Object[] elements;
      private int pos;

      private CyclicStack() {
         this.elements = new Object[512];
         this.pos = 0;
      }

      public synchronized Object pop() {
         T e = (T)this.elements[this.pos];
         this.elements[this.pos] = null;
         this.pos = this.pos - 1 & 511;
         return e;
      }

      public synchronized void push(Object e) {
         this.pos = this.pos + 1 & 511;
         this.elements[this.pos] = e;
      }
   }

   private static class CacheMap extends LinkedHashMap {
      private static final long serialVersionUID = 1L;

      public CacheMap() {
         super(64, 0.75F, true);
      }

      protected boolean removeEldestEntry(Map.Entry eldest) {
         return this.size() > 32;
      }
   }

   private static final class LazyHolder {
      static final BasicArrayCache INSTANCE = new BasicArrayCache();
   }
}
