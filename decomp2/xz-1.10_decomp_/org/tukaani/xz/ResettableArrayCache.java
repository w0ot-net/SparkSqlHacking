package org.tukaani.xz;

import java.util.ArrayList;
import java.util.List;

public class ResettableArrayCache extends ArrayCache {
   private final ArrayCache arrayCache;
   private final List byteArrays;
   private final List intArrays;

   public ResettableArrayCache(ArrayCache arrayCache) {
      this.arrayCache = arrayCache;
      if (arrayCache == ArrayCache.getDummyCache()) {
         this.byteArrays = null;
         this.intArrays = null;
      } else {
         this.byteArrays = new ArrayList();
         this.intArrays = new ArrayList();
      }

   }

   public byte[] getByteArray(int size, boolean fillWithZeros) {
      byte[] array = this.arrayCache.getByteArray(size, fillWithZeros);
      if (this.byteArrays != null) {
         synchronized(this.byteArrays) {
            this.byteArrays.add(array);
         }
      }

      return array;
   }

   public void putArray(byte[] array) {
      if (this.byteArrays != null) {
         synchronized(this.byteArrays) {
            int i = this.byteArrays.lastIndexOf(array);
            if (i != -1) {
               this.byteArrays.remove(i);
            }
         }

         this.arrayCache.putArray(array);
      }

   }

   public int[] getIntArray(int size, boolean fillWithZeros) {
      int[] array = this.arrayCache.getIntArray(size, fillWithZeros);
      if (this.intArrays != null) {
         synchronized(this.intArrays) {
            this.intArrays.add(array);
         }
      }

      return array;
   }

   public void putArray(int[] array) {
      if (this.intArrays != null) {
         synchronized(this.intArrays) {
            int i = this.intArrays.lastIndexOf(array);
            if (i != -1) {
               this.intArrays.remove(i);
            }
         }

         this.arrayCache.putArray(array);
      }

   }

   public void reset() {
      if (this.byteArrays != null) {
         synchronized(this.byteArrays) {
            for(int i = this.byteArrays.size() - 1; i >= 0; --i) {
               this.arrayCache.putArray((byte[])this.byteArrays.get(i));
            }

            this.byteArrays.clear();
         }

         synchronized(this.intArrays) {
            for(int i = this.intArrays.size() - 1; i >= 0; --i) {
               this.arrayCache.putArray((int[])this.intArrays.get(i));
            }

            this.intArrays.clear();
         }
      }

   }
}
