package org.apache.arrow.memory;

import org.apache.arrow.util.Preconditions;
import org.apache.arrow.util.VisibleForTesting;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.initialization.qual.UnderInitialization;
import org.checkerframework.checker.nullness.qual.Nullable;

public class LowCostIdentityHashMap {
   private @Nullable Object[] elementData;
   private int size;
   private int threshold;
   private static final int DEFAULT_MIN_SIZE = 1;
   private static final int LOAD_FACTOR = 7500;

   public LowCostIdentityHashMap() {
      this(1);
   }

   public LowCostIdentityHashMap(int maxSize) {
      if (maxSize >= 0) {
         this.size = 0;
         this.threshold = this.getThreshold(maxSize);
         this.elementData = this.newElementArrayUnderInitialized(this.computeElementArraySize());
      } else {
         throw new IllegalArgumentException();
      }
   }

   private @UnderInitialization int getThreshold(int maxSize) {
      return maxSize > 2 ? maxSize : 2;
   }

   private @UnderInitialization int computeElementArraySize() {
      int arraySize = (int)((long)this.threshold * 10000L / 7500L);
      return arraySize < 0 ? -arraySize : arraySize;
   }

   private Object @Initialized [] newElementArrayInitialized(int s) {
      return new Object[s];
   }

   private Object @UnderInitialization [] newElementArrayUnderInitialized(int s) {
      return new Object[s];
   }

   public void clear() {
      this.size = 0;

      for(int i = 0; i < this.elementData.length; ++i) {
         this.elementData[i] = null;
      }

   }

   public boolean containsKey(Object key) {
      Preconditions.checkNotNull(key);
      int index = this.findIndex(key, this.elementData);
      return this.elementData[index] == null ? false : ((ValueWithKeyIncluded)this.elementData[index]).getKey() == key;
   }

   public boolean containsValue(ValueWithKeyIncluded value) {
      Preconditions.checkNotNull(value);

      for(int i = 0; i < this.elementData.length; ++i) {
         if (this.elementData[i] == value) {
            return true;
         }
      }

      return false;
   }

   public @Nullable ValueWithKeyIncluded get(Object key) {
      Preconditions.checkNotNull(key);
      int index = this.findIndex(key, this.elementData);
      return this.elementData[index] == null ? null : (((ValueWithKeyIncluded)this.elementData[index]).getKey() == key ? (ValueWithKeyIncluded)this.elementData[index] : null);
   }

   @VisibleForTesting
   int findIndex(@Nullable Object key, @Nullable Object[] array) {
      int length = array.length;
      int index = getModuloHash(key, length);

      for(int last = (index + length - 1) % length; index != last && array[index] != null && ((ValueWithKeyIncluded)array[index]).getKey() != key; index = (index + 1) % length) {
      }

      return index;
   }

   @VisibleForTesting
   static int getModuloHash(@Nullable Object key, int length) {
      return (System.identityHashCode(key) & Integer.MAX_VALUE) % length;
   }

   public ValueWithKeyIncluded put(ValueWithKeyIncluded value) {
      Preconditions.checkNotNull(value);
      K key = (K)value.getKey();
      Preconditions.checkNotNull(key);
      int index = this.findIndex(key, this.elementData);
      if (this.elementData[index] == null || ((ValueWithKeyIncluded)this.elementData[index]).getKey() != key) {
         if (++this.size > this.threshold) {
            this.rehash();
            index = this.findIndex(key, this.elementData);
         }

         this.elementData[index] = null;
      }

      Object result = this.elementData[index];
      this.elementData[index] = value;
      return (ValueWithKeyIncluded)result;
   }

   @VisibleForTesting
   void rehash() {
      int newlength = this.elementData.length * 15 / 10;
      if (newlength == 0) {
         newlength = 1;
      }

      Object[] newData = this.newElementArrayInitialized(newlength);

      for(int i = 0; i < this.elementData.length; ++i) {
         Object key = this.elementData[i] == null ? null : ((ValueWithKeyIncluded)this.elementData[i]).getKey();
         if (key != null) {
            int index = this.findIndex(key, newData);
            newData[index] = this.elementData[i];
         }
      }

      this.elementData = newData;
      this.computeMaxSize();
   }

   private void computeMaxSize() {
      this.threshold = (int)((long)this.elementData.length * 7500L / 10000L);
   }

   public @Nullable ValueWithKeyIncluded remove(Object key) {
      Preconditions.checkNotNull(key);
      int next;
      int index = next = this.findIndex(key, this.elementData);
      if (this.elementData[index] != null && ((ValueWithKeyIncluded)this.elementData[index]).getKey() == key) {
         Object result = this.elementData[index];
         this.elementData[index] = null;
         --this.size;
         int length = this.elementData.length;

         while(true) {
            next = (next + 1) % length;
            Object object = this.elementData[next];
            if (object == null) {
               return (ValueWithKeyIncluded)result;
            }

            int hash = getModuloHash(((ValueWithKeyIncluded)object).getKey(), length);
            boolean hashedOk = hash > index;
            if (next < index) {
               hashedOk = hashedOk || hash <= next;
            } else {
               hashedOk = hashedOk && hash <= next;
            }

            if (!hashedOk) {
               this.elementData[index] = object;
               index = next;
               this.elementData[next] = null;
            }
         }
      } else {
         return null;
      }
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   public int size() {
      return this.size;
   }

   public @Nullable ValueWithKeyIncluded getNextValue() {
      for(int i = 0; i < this.elementData.length; ++i) {
         if (this.elementData[i] != null) {
            return (ValueWithKeyIncluded)this.elementData[i];
         }
      }

      return null;
   }
}
