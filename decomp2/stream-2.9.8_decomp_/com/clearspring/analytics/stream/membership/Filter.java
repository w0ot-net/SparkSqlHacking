package com.clearspring.analytics.stream.membership;

import com.clearspring.analytics.hash.MurmurHash;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

public abstract class Filter {
   int hashCount;

   public int getHashCount() {
      return this.hashCount;
   }

   public int[] getHashBuckets(String key) {
      return getHashBuckets(key, this.hashCount, this.buckets());
   }

   public int[] getHashBuckets(byte[] key) {
      return getHashBuckets(key, this.hashCount, this.buckets());
   }

   abstract int buckets();

   public abstract void add(String var1);

   public abstract boolean isPresent(String var1);

   abstract int emptyBuckets();

   ICompactSerializer getSerializer() {
      Method method = null;

      try {
         method = this.getClass().getMethod("serializer");
         return (ICompactSerializer)method.invoke((Object)null);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   public static int[] getHashBuckets(String key, int hashCount, int max) {
      byte[] b;
      try {
         b = key.getBytes("UTF-16");
      } catch (UnsupportedEncodingException e) {
         throw new RuntimeException(e);
      }

      return getHashBuckets(b, hashCount, max);
   }

   static int[] getHashBuckets(byte[] b, int hashCount, int max) {
      int[] result = new int[hashCount];
      int hash1 = MurmurHash.hash(b, b.length, 0);
      int hash2 = MurmurHash.hash(b, b.length, hash1);

      for(int i = 0; i < hashCount; ++i) {
         result[i] = Math.abs((hash1 + i * hash2) % max);
      }

      return result;
   }
}
