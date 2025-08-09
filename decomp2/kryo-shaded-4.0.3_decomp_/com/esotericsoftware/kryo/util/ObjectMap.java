package com.esotericsoftware.kryo.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class ObjectMap {
   private static final int PRIME2 = -1105259343;
   private static final int PRIME3 = -1262997959;
   private static final int PRIME4 = -825114047;
   static Random random = new Random();
   public int size;
   Object[] keyTable;
   Object[] valueTable;
   int capacity;
   int stashSize;
   private float loadFactor;
   private int hashShift;
   private int mask;
   private int threshold;
   private int stashCapacity;
   private int pushIterations;
   private boolean isBigTable;

   public ObjectMap() {
      this(32, 0.8F);
   }

   public ObjectMap(int initialCapacity) {
      this(initialCapacity, 0.8F);
   }

   public ObjectMap(int initialCapacity, float loadFactor) {
      if (initialCapacity < 0) {
         throw new IllegalArgumentException("initialCapacity must be >= 0: " + initialCapacity);
      } else if (initialCapacity > 1073741824) {
         throw new IllegalArgumentException("initialCapacity is too large: " + initialCapacity);
      } else {
         this.capacity = nextPowerOfTwo(initialCapacity);
         if (loadFactor <= 0.0F) {
            throw new IllegalArgumentException("loadFactor must be > 0: " + loadFactor);
         } else {
            this.loadFactor = loadFactor;
            this.isBigTable = this.capacity >>> 16 != 0;
            this.threshold = (int)((float)this.capacity * loadFactor);
            this.mask = this.capacity - 1;
            this.hashShift = 31 - Integer.numberOfTrailingZeros(this.capacity);
            this.stashCapacity = Math.max(3, (int)Math.ceil(Math.log((double)this.capacity)) * 2);
            this.pushIterations = Math.max(Math.min(this.capacity, 8), (int)Math.sqrt((double)this.capacity) / 8);
            this.keyTable = new Object[this.capacity + this.stashCapacity];
            this.valueTable = new Object[this.keyTable.length];
         }
      }
   }

   public ObjectMap(ObjectMap map) {
      this(map.capacity, map.loadFactor);
      this.stashSize = map.stashSize;
      System.arraycopy(map.keyTable, 0, this.keyTable, 0, map.keyTable.length);
      System.arraycopy(map.valueTable, 0, this.valueTable, 0, map.valueTable.length);
      this.size = map.size;
   }

   public Object put(Object key, Object value) {
      if (key == null) {
         throw new IllegalArgumentException("key cannot be null.");
      } else {
         return this.put_internal(key, value);
      }
   }

   private Object put_internal(Object key, Object value) {
      K[] keyTable = (K[])this.keyTable;
      int mask = this.mask;
      boolean isBigTable = this.isBigTable;
      int hashCode = key.hashCode();
      int index1 = hashCode & mask;
      K key1 = (K)keyTable[index1];
      if (key.equals(key1)) {
         V oldValue = (V)this.valueTable[index1];
         this.valueTable[index1] = value;
         return oldValue;
      } else {
         int index2 = this.hash2(hashCode);
         K key2 = (K)keyTable[index2];
         if (key.equals(key2)) {
            V oldValue = (V)this.valueTable[index2];
            this.valueTable[index2] = value;
            return oldValue;
         } else {
            int index3 = this.hash3(hashCode);
            K key3 = (K)keyTable[index3];
            if (key.equals(key3)) {
               V oldValue = (V)this.valueTable[index3];
               this.valueTable[index3] = value;
               return oldValue;
            } else {
               int index4 = -1;
               K key4 = (K)null;
               if (isBigTable) {
                  index4 = this.hash4(hashCode);
                  key4 = (K)keyTable[index4];
                  if (key.equals(key4)) {
                     V oldValue = (V)this.valueTable[index4];
                     this.valueTable[index4] = value;
                     return oldValue;
                  }
               }

               int i = this.capacity;

               for(int n = i + this.stashSize; i < n; ++i) {
                  if (key.equals(keyTable[i])) {
                     V oldValue = (V)this.valueTable[i];
                     this.valueTable[i] = value;
                     return oldValue;
                  }
               }

               if (key1 == null) {
                  keyTable[index1] = key;
                  this.valueTable[index1] = value;
                  if (this.size++ >= this.threshold) {
                     this.resize(this.capacity << 1);
                  }

                  return null;
               } else if (key2 == null) {
                  keyTable[index2] = key;
                  this.valueTable[index2] = value;
                  if (this.size++ >= this.threshold) {
                     this.resize(this.capacity << 1);
                  }

                  return null;
               } else if (key3 == null) {
                  keyTable[index3] = key;
                  this.valueTable[index3] = value;
                  if (this.size++ >= this.threshold) {
                     this.resize(this.capacity << 1);
                  }

                  return null;
               } else if (isBigTable && key4 == null) {
                  keyTable[index4] = key;
                  this.valueTable[index4] = value;
                  if (this.size++ >= this.threshold) {
                     this.resize(this.capacity << 1);
                  }

                  return null;
               } else {
                  this.push(key, value, index1, key1, index2, key2, index3, key3, index4, key4);
                  return null;
               }
            }
         }
      }
   }

   public void putAll(ObjectMap map) {
      this.ensureCapacity(map.size);

      for(Entry entry : map.entries()) {
         this.put(entry.key, entry.value);
      }

   }

   private void putResize(Object key, Object value) {
      int hashCode = key.hashCode();
      int index1 = hashCode & this.mask;
      K key1 = (K)this.keyTable[index1];
      if (key1 == null) {
         this.keyTable[index1] = key;
         this.valueTable[index1] = value;
         if (this.size++ >= this.threshold) {
            this.resize(this.capacity << 1);
         }

      } else {
         int index2 = this.hash2(hashCode);
         K key2 = (K)this.keyTable[index2];
         if (key2 == null) {
            this.keyTable[index2] = key;
            this.valueTable[index2] = value;
            if (this.size++ >= this.threshold) {
               this.resize(this.capacity << 1);
            }

         } else {
            int index3 = this.hash3(hashCode);
            K key3 = (K)this.keyTable[index3];
            if (key3 == null) {
               this.keyTable[index3] = key;
               this.valueTable[index3] = value;
               if (this.size++ >= this.threshold) {
                  this.resize(this.capacity << 1);
               }

            } else {
               int index4 = -1;
               K key4 = (K)null;
               if (this.isBigTable) {
                  index4 = this.hash4(hashCode);
                  key4 = (K)this.keyTable[index4];
                  if (key4 == null) {
                     this.keyTable[index4] = key;
                     this.valueTable[index4] = value;
                     if (this.size++ >= this.threshold) {
                        this.resize(this.capacity << 1);
                     }

                     return;
                  }
               }

               this.push(key, value, index1, key1, index2, key2, index3, key3, index4, key4);
            }
         }
      }
   }

   private void push(Object insertKey, Object insertValue, int index1, Object key1, int index2, Object key2, int index3, Object key3, int index4, Object key4) {
      K[] keyTable = (K[])this.keyTable;
      V[] valueTable = (V[])this.valueTable;
      int mask = this.mask;
      boolean isBigTable = this.isBigTable;
      int i = 0;
      int pushIterations = this.pushIterations;
      int n = isBigTable ? 4 : 3;

      while(true) {
         K evictedKey;
         V evictedValue;
         switch (random.nextInt(n)) {
            case 0:
               evictedKey = key1;
               evictedValue = (V)valueTable[index1];
               keyTable[index1] = insertKey;
               valueTable[index1] = insertValue;
               break;
            case 1:
               evictedKey = key2;
               evictedValue = (V)valueTable[index2];
               keyTable[index2] = insertKey;
               valueTable[index2] = insertValue;
               break;
            case 2:
               evictedKey = key3;
               evictedValue = (V)valueTable[index3];
               keyTable[index3] = insertKey;
               valueTable[index3] = insertValue;
               break;
            default:
               evictedKey = key4;
               evictedValue = (V)valueTable[index4];
               keyTable[index4] = insertKey;
               valueTable[index4] = insertValue;
         }

         int hashCode = evictedKey.hashCode();
         index1 = hashCode & mask;
         key1 = (K)keyTable[index1];
         if (key1 == null) {
            keyTable[index1] = evictedKey;
            valueTable[index1] = evictedValue;
            if (this.size++ >= this.threshold) {
               this.resize(this.capacity << 1);
            }

            return;
         }

         index2 = this.hash2(hashCode);
         key2 = (K)keyTable[index2];
         if (key2 == null) {
            keyTable[index2] = evictedKey;
            valueTable[index2] = evictedValue;
            if (this.size++ >= this.threshold) {
               this.resize(this.capacity << 1);
            }

            return;
         }

         index3 = this.hash3(hashCode);
         key3 = (K)keyTable[index3];
         if (key3 == null) {
            keyTable[index3] = evictedKey;
            valueTable[index3] = evictedValue;
            if (this.size++ >= this.threshold) {
               this.resize(this.capacity << 1);
            }

            return;
         }

         if (isBigTable) {
            index4 = this.hash4(hashCode);
            key4 = (K)keyTable[index4];
            if (key4 == null) {
               keyTable[index4] = evictedKey;
               valueTable[index4] = evictedValue;
               if (this.size++ >= this.threshold) {
                  this.resize(this.capacity << 1);
               }

               return;
            }
         }

         ++i;
         if (i == pushIterations) {
            this.putStash(evictedKey, evictedValue);
            return;
         }

         insertKey = evictedKey;
         insertValue = evictedValue;
      }
   }

   private void putStash(Object key, Object value) {
      if (this.stashSize == this.stashCapacity) {
         this.resize(this.capacity << 1);
         this.put_internal(key, value);
      } else {
         int index = this.capacity + this.stashSize;
         this.keyTable[index] = key;
         this.valueTable[index] = value;
         ++this.stashSize;
         ++this.size;
      }
   }

   public Object get(Object key) {
      int hashCode = key.hashCode();
      int index = hashCode & this.mask;
      if (!key.equals(this.keyTable[index])) {
         index = this.hash2(hashCode);
         if (!key.equals(this.keyTable[index])) {
            index = this.hash3(hashCode);
            if (!key.equals(this.keyTable[index])) {
               if (!this.isBigTable) {
                  return this.getStash(key);
               }

               index = this.hash4(hashCode);
               if (!key.equals(this.keyTable[index])) {
                  return this.getStash(key);
               }
            }
         }
      }

      return this.valueTable[index];
   }

   private Object getStash(Object key) {
      K[] keyTable = (K[])this.keyTable;
      int i = this.capacity;

      for(int n = i + this.stashSize; i < n; ++i) {
         if (key.equals(keyTable[i])) {
            return this.valueTable[i];
         }
      }

      return null;
   }

   public Object get(Object key, Object defaultValue) {
      int hashCode = key.hashCode();
      int index = hashCode & this.mask;
      if (!key.equals(this.keyTable[index])) {
         index = this.hash2(hashCode);
         if (!key.equals(this.keyTable[index])) {
            index = this.hash3(hashCode);
            if (!key.equals(this.keyTable[index])) {
               if (!this.isBigTable) {
                  return this.getStash(key, defaultValue);
               }

               index = this.hash4(hashCode);
               if (!key.equals(this.keyTable[index])) {
                  return this.getStash(key, defaultValue);
               }
            }
         }
      }

      return this.valueTable[index];
   }

   private Object getStash(Object key, Object defaultValue) {
      K[] keyTable = (K[])this.keyTable;
      int i = this.capacity;

      for(int n = i + this.stashSize; i < n; ++i) {
         if (key.equals(keyTable[i])) {
            return this.valueTable[i];
         }
      }

      return defaultValue;
   }

   public Object remove(Object key) {
      int hashCode = key.hashCode();
      int index = hashCode & this.mask;
      if (key.equals(this.keyTable[index])) {
         this.keyTable[index] = null;
         V oldValue = (V)this.valueTable[index];
         this.valueTable[index] = null;
         --this.size;
         return oldValue;
      } else {
         index = this.hash2(hashCode);
         if (key.equals(this.keyTable[index])) {
            this.keyTable[index] = null;
            V oldValue = (V)this.valueTable[index];
            this.valueTable[index] = null;
            --this.size;
            return oldValue;
         } else {
            index = this.hash3(hashCode);
            if (key.equals(this.keyTable[index])) {
               this.keyTable[index] = null;
               V oldValue = (V)this.valueTable[index];
               this.valueTable[index] = null;
               --this.size;
               return oldValue;
            } else {
               if (this.isBigTable) {
                  index = this.hash4(hashCode);
                  if (key.equals(this.keyTable[index])) {
                     this.keyTable[index] = null;
                     V oldValue = (V)this.valueTable[index];
                     this.valueTable[index] = null;
                     --this.size;
                     return oldValue;
                  }
               }

               return this.removeStash(key);
            }
         }
      }
   }

   Object removeStash(Object key) {
      K[] keyTable = (K[])this.keyTable;
      int i = this.capacity;

      for(int n = i + this.stashSize; i < n; ++i) {
         if (key.equals(keyTable[i])) {
            V oldValue = (V)this.valueTable[i];
            this.removeStashIndex(i);
            --this.size;
            return oldValue;
         }
      }

      return null;
   }

   void removeStashIndex(int index) {
      --this.stashSize;
      int lastIndex = this.capacity + this.stashSize;
      if (index < lastIndex) {
         this.keyTable[index] = this.keyTable[lastIndex];
         this.valueTable[index] = this.valueTable[lastIndex];
         this.valueTable[lastIndex] = null;
      } else {
         this.valueTable[index] = null;
      }

   }

   public void shrink(int maximumCapacity) {
      if (maximumCapacity < 0) {
         throw new IllegalArgumentException("maximumCapacity must be >= 0: " + maximumCapacity);
      } else {
         if (this.size > maximumCapacity) {
            maximumCapacity = this.size;
         }

         if (this.capacity > maximumCapacity) {
            maximumCapacity = nextPowerOfTwo(maximumCapacity);
            this.resize(maximumCapacity);
         }
      }
   }

   public void clear(int maximumCapacity) {
      if (this.capacity <= maximumCapacity) {
         this.clear();
      } else {
         this.size = 0;
         this.resize(maximumCapacity);
      }
   }

   public void clear() {
      K[] keyTable = (K[])this.keyTable;
      V[] valueTable = (V[])this.valueTable;

      for(int i = this.capacity + this.stashSize; i-- > 0; valueTable[i] = null) {
         keyTable[i] = null;
      }

      this.size = 0;
      this.stashSize = 0;
   }

   public boolean containsValue(Object value, boolean identity) {
      V[] valueTable = (V[])this.valueTable;
      if (value == null) {
         K[] keyTable = (K[])this.keyTable;
         int i = this.capacity + this.stashSize;

         while(i-- > 0) {
            if (keyTable[i] != null && valueTable[i] == null) {
               return true;
            }
         }
      } else if (identity) {
         int i = this.capacity + this.stashSize;

         while(i-- > 0) {
            if (valueTable[i] == value) {
               return true;
            }
         }
      } else {
         int i = this.capacity + this.stashSize;

         while(i-- > 0) {
            if (value.equals(valueTable[i])) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean containsKey(Object key) {
      int hashCode = key.hashCode();
      int index = hashCode & this.mask;
      if (!key.equals(this.keyTable[index])) {
         index = this.hash2(hashCode);
         if (!key.equals(this.keyTable[index])) {
            index = this.hash3(hashCode);
            if (!key.equals(this.keyTable[index])) {
               if (!this.isBigTable) {
                  return this.containsKeyStash(key);
               }

               index = this.hash4(hashCode);
               if (!key.equals(this.keyTable[index])) {
                  return this.containsKeyStash(key);
               }
            }
         }
      }

      return true;
   }

   private boolean containsKeyStash(Object key) {
      K[] keyTable = (K[])this.keyTable;
      int i = this.capacity;

      for(int n = i + this.stashSize; i < n; ++i) {
         if (key.equals(keyTable[i])) {
            return true;
         }
      }

      return false;
   }

   public Object findKey(Object value, boolean identity) {
      V[] valueTable = (V[])this.valueTable;
      if (value == null) {
         K[] keyTable = (K[])this.keyTable;
         int i = this.capacity + this.stashSize;

         while(i-- > 0) {
            if (keyTable[i] != null && valueTable[i] == null) {
               return keyTable[i];
            }
         }
      } else if (identity) {
         int i = this.capacity + this.stashSize;

         while(i-- > 0) {
            if (valueTable[i] == value) {
               return this.keyTable[i];
            }
         }
      } else {
         int i = this.capacity + this.stashSize;

         while(i-- > 0) {
            if (value.equals(valueTable[i])) {
               return this.keyTable[i];
            }
         }
      }

      return null;
   }

   public void ensureCapacity(int additionalCapacity) {
      int sizeNeeded = this.size + additionalCapacity;
      if (sizeNeeded >= this.threshold) {
         this.resize(nextPowerOfTwo((int)((float)sizeNeeded / this.loadFactor)));
      }

   }

   private void resize(int newSize) {
      int oldEndIndex = this.capacity + this.stashSize;
      this.capacity = newSize;
      this.threshold = (int)((float)newSize * this.loadFactor);
      this.mask = newSize - 1;
      this.hashShift = 31 - Integer.numberOfTrailingZeros(newSize);
      this.stashCapacity = Math.max(3, (int)Math.ceil(Math.log((double)newSize)) * 2);
      this.pushIterations = Math.max(Math.min(newSize, 8), (int)Math.sqrt((double)newSize) / 8);
      this.isBigTable = this.capacity >>> 16 != 0;
      K[] oldKeyTable = (K[])this.keyTable;
      V[] oldValueTable = (V[])this.valueTable;
      this.keyTable = new Object[newSize + this.stashCapacity];
      this.valueTable = new Object[newSize + this.stashCapacity];
      int oldSize = this.size;
      this.size = 0;
      this.stashSize = 0;
      if (oldSize > 0) {
         for(int i = 0; i < oldEndIndex; ++i) {
            K key = (K)oldKeyTable[i];
            if (key != null) {
               this.putResize(key, oldValueTable[i]);
            }
         }
      }

   }

   private int hash2(int h) {
      h *= -1105259343;
      return (h ^ h >>> this.hashShift) & this.mask;
   }

   private int hash3(int h) {
      h *= -1262997959;
      return (h ^ h >>> this.hashShift) & this.mask;
   }

   private int hash4(int h) {
      h *= -825114047;
      return (h ^ h >>> this.hashShift) & this.mask;
   }

   public String toString() {
      if (this.size == 0) {
         return "{}";
      } else {
         StringBuilder buffer = new StringBuilder(32);
         buffer.append('{');
         K[] keyTable = (K[])this.keyTable;
         V[] valueTable = (V[])this.valueTable;
         int i = keyTable.length;

         while(i-- > 0) {
            K key = (K)keyTable[i];
            if (key != null) {
               buffer.append(key);
               buffer.append('=');
               buffer.append(valueTable[i]);
               break;
            }
         }

         while(i-- > 0) {
            K key = (K)keyTable[i];
            if (key != null) {
               buffer.append(", ");
               buffer.append(key);
               buffer.append('=');
               buffer.append(valueTable[i]);
            }
         }

         buffer.append('}');
         return buffer.toString();
      }
   }

   public Entries entries() {
      return new Entries(this);
   }

   public Values values() {
      return new Values(this);
   }

   public Keys keys() {
      return new Keys(this);
   }

   public static int nextPowerOfTwo(int value) {
      if (value == 0) {
         return 1;
      } else {
         --value;
         value |= value >> 1;
         value |= value >> 2;
         value |= value >> 4;
         value |= value >> 8;
         value |= value >> 16;
         return value + 1;
      }
   }

   public static class Entry {
      public Object key;
      public Object value;

      public String toString() {
         return this.key + "=" + this.value;
      }
   }

   private static class MapIterator {
      public boolean hasNext;
      final ObjectMap map;
      int nextIndex;
      int currentIndex;

      public MapIterator(ObjectMap map) {
         this.map = map;
         this.reset();
      }

      public void reset() {
         this.currentIndex = -1;
         this.nextIndex = -1;
         this.advance();
      }

      void advance() {
         this.hasNext = false;
         K[] keyTable = (K[])this.map.keyTable;
         int n = this.map.capacity + this.map.stashSize;

         while(++this.nextIndex < n) {
            if (keyTable[this.nextIndex] != null) {
               this.hasNext = true;
               break;
            }
         }

      }

      public void remove() {
         if (this.currentIndex < 0) {
            throw new IllegalStateException("next must be called before remove.");
         } else {
            if (this.currentIndex >= this.map.capacity) {
               this.map.removeStashIndex(this.currentIndex);
               this.nextIndex = this.currentIndex - 1;
               this.advance();
            } else {
               this.map.keyTable[this.currentIndex] = null;
               this.map.valueTable[this.currentIndex] = null;
            }

            this.currentIndex = -1;
            --this.map.size;
         }
      }
   }

   public static class Entries extends MapIterator implements Iterable, Iterator {
      Entry entry = new Entry();

      public Entries(ObjectMap map) {
         super(map);
      }

      public Entry next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else {
            K[] keyTable = (K[])this.map.keyTable;
            this.entry.key = keyTable[this.nextIndex];
            this.entry.value = this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.advance();
            return this.entry;
         }
      }

      public boolean hasNext() {
         return this.hasNext;
      }

      public Iterator iterator() {
         return this;
      }
   }

   public static class Values extends MapIterator implements Iterable, Iterator {
      public Values(ObjectMap map) {
         super(map);
      }

      public boolean hasNext() {
         return this.hasNext;
      }

      public Object next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else {
            V value = (V)this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.advance();
            return value;
         }
      }

      public Iterator iterator() {
         return this;
      }

      public ArrayList toArray() {
         ArrayList array = new ArrayList(this.map.size);

         while(this.hasNext) {
            array.add(this.next());
         }

         return array;
      }

      public void toArray(ArrayList array) {
         while(this.hasNext) {
            array.add(this.next());
         }

      }
   }

   public static class Keys extends MapIterator implements Iterable, Iterator {
      public Keys(ObjectMap map) {
         super(map);
      }

      public boolean hasNext() {
         return this.hasNext;
      }

      public Object next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else {
            K key = (K)this.map.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.advance();
            return key;
         }
      }

      public Iterator iterator() {
         return this;
      }

      public ArrayList toArray() {
         ArrayList array = new ArrayList(this.map.size);

         while(this.hasNext) {
            array.add(this.next());
         }

         return array;
      }
   }
}
