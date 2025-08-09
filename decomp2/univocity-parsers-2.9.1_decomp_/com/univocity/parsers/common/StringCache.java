package com.univocity.parsers.common;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class StringCache {
   private static final int DEFAULT_SIZE_LIMIT = 16384;
   private static final int DEFAULT_MAX_STRING_LENGTH = 0;
   private final Map stringCache = new ConcurrentHashMap();
   private int sizeLimit = 16384;
   private int maxStringLength = 0;

   protected abstract Object process(String var1);

   public boolean containsKey(String input) {
      return this.stringCache.containsKey(input);
   }

   public int getSizeLimit() {
      return this.sizeLimit;
   }

   public void setSizeLimit(int sizeLimit) {
      if (sizeLimit <= 0) {
         sizeLimit = 16384;
      }

      this.sizeLimit = sizeLimit;
   }

   public void put(String input, Object value) {
      if (input != null && input.length() <= this.maxStringLength) {
         if (this.stringCache.size() >= this.sizeLimit) {
            this.stringCache.clear();
         }

         this.stringCache.put(input, new SoftReference(value));
      }
   }

   public Object get(String input) {
      if (input != null && (this.maxStringLength <= 0 || input.length() <= this.maxStringLength)) {
         SoftReference<T> ref = (SoftReference)this.stringCache.get(input);
         T out;
         if (ref != null && ref.get() != null) {
            out = (T)ref.get();
         } else {
            out = (T)this.process(input);
            ref = new SoftReference(out);
            this.stringCache.put(input, ref);
         }

         return out;
      } else {
         return null;
      }
   }

   public void clear() {
      this.stringCache.clear();
   }

   public int getMaxStringLength() {
      return this.maxStringLength;
   }

   public void setMaxStringLength(int maxStringLength) {
      this.maxStringLength = maxStringLength;
   }
}
