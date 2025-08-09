package com.esotericsoftware.kryo.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntMap {
   private static final int PRIME2 = -1105259343;
   private static final int PRIME3 = -1262997959;
   private static final int PRIME4 = -825114047;
   private static final int EMPTY = 0;
   public int size;
   int[] keyTable;
   Object[] valueTable;
   int capacity;
   int stashSize;
   Object zeroValue;
   boolean hasZeroValue;
   private float loadFactor;
   private int hashShift;
   private int mask;
   private int threshold;
   private int stashCapacity;
   private int pushIterations;
   private boolean isBigTable;

   public IntMap() {
      this(32, 0.8F);
   }

   public IntMap(int initialCapacity) {
      this(initialCapacity, 0.8F);
   }

   public IntMap(int initialCapacity, float loadFactor) {
      if (initialCapacity < 0) {
         throw new IllegalArgumentException("initialCapacity must be >= 0: " + initialCapacity);
      } else if (initialCapacity > 1073741824) {
         throw new IllegalArgumentException("initialCapacity is too large: " + initialCapacity);
      } else {
         this.capacity = ObjectMap.nextPowerOfTwo(initialCapacity);
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
            this.keyTable = new int[this.capacity + this.stashCapacity];
            this.valueTable = new Object[this.keyTable.length];
         }
      }
   }

   public IntMap(IntMap map) {
      this(map.capacity, map.loadFactor);
      this.stashSize = map.stashSize;
      System.arraycopy(map.keyTable, 0, this.keyTable, 0, map.keyTable.length);
      System.arraycopy(map.valueTable, 0, this.valueTable, 0, map.valueTable.length);
      this.size = map.size;
      this.zeroValue = map.zeroValue;
      this.hasZeroValue = map.hasZeroValue;
   }

   public Object put(int key, Object value) {
      if (key == 0) {
         V oldValue = (V)this.zeroValue;
         this.zeroValue = value;
         if (!this.hasZeroValue) {
            this.hasZeroValue = true;
            ++this.size;
         }

         return oldValue;
      } else {
         int[] keyTable = this.keyTable;
         int mask = this.mask;
         boolean isBigTable = this.isBigTable;
         int index1 = key & mask;
         int key1 = keyTable[index1];
         if (key1 == key) {
            V oldValue = (V)this.valueTable[index1];
            this.valueTable[index1] = value;
            return oldValue;
         } else {
            int index2 = this.hash2(key);
            int key2 = keyTable[index2];
            if (key2 == key) {
               V oldValue = (V)this.valueTable[index2];
               this.valueTable[index2] = value;
               return oldValue;
            } else {
               int index3 = this.hash3(key);
               int key3 = keyTable[index3];
               if (key3 == key) {
                  V oldValue = (V)this.valueTable[index3];
                  this.valueTable[index3] = value;
                  return oldValue;
               } else {
                  int index4 = -1;
                  int key4 = -1;
                  if (isBigTable) {
                     index4 = this.hash4(key);
                     key4 = keyTable[index4];
                     if (key4 == key) {
                        V oldValue = (V)this.valueTable[index4];
                        this.valueTable[index4] = value;
                        return oldValue;
                     }
                  }

                  int i = this.capacity;

                  for(int n = i + this.stashSize; i < n; ++i) {
                     if (keyTable[i] == key) {
                        V oldValue = (V)this.valueTable[i];
                        this.valueTable[i] = value;
                        return oldValue;
                     }
                  }

                  if (key1 == 0) {
                     keyTable[index1] = key;
                     this.valueTable[index1] = value;
                     if (this.size++ >= this.threshold) {
                        this.resize(this.capacity << 1);
                     }

                     return null;
                  } else if (key2 == 0) {
                     keyTable[index2] = key;
                     this.valueTable[index2] = value;
                     if (this.size++ >= this.threshold) {
                        this.resize(this.capacity << 1);
                     }

                     return null;
                  } else if (key3 == 0) {
                     keyTable[index3] = key;
                     this.valueTable[index3] = value;
                     if (this.size++ >= this.threshold) {
                        this.resize(this.capacity << 1);
                     }

                     return null;
                  } else if (isBigTable && key4 == 0) {
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
   }

   public void putAll(IntMap map) {
      for(Entry entry : map.entries()) {
         this.put(entry.key, entry.value);
      }

   }

   private void putResize(int key, Object value) {
      if (key == 0) {
         this.zeroValue = value;
         this.hasZeroValue = true;
      } else {
         int index1 = key & this.mask;
         int key1 = this.keyTable[index1];
         if (key1 == 0) {
            this.keyTable[index1] = key;
            this.valueTable[index1] = value;
            if (this.size++ >= this.threshold) {
               this.resize(this.capacity << 1);
            }

         } else {
            int index2 = this.hash2(key);
            int key2 = this.keyTable[index2];
            if (key2 == 0) {
               this.keyTable[index2] = key;
               this.valueTable[index2] = value;
               if (this.size++ >= this.threshold) {
                  this.resize(this.capacity << 1);
               }

            } else {
               int index3 = this.hash3(key);
               int key3 = this.keyTable[index3];
               if (key3 == 0) {
                  this.keyTable[index3] = key;
                  this.valueTable[index3] = value;
                  if (this.size++ >= this.threshold) {
                     this.resize(this.capacity << 1);
                  }

               } else {
                  int index4 = -1;
                  int key4 = -1;
                  if (this.isBigTable) {
                     index4 = this.hash4(key);
                     key4 = this.keyTable[index4];
                     if (key4 == 0) {
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
   }

   private void push(int insertKey, Object insertValue, int index1, int key1, int index2, int key2, int index3, int key3, int index4, int key4) {
      int[] keyTable = this.keyTable;
      V[] valueTable = (V[])this.valueTable;
      int mask = this.mask;
      boolean isBigTable = this.isBigTable;
      int i = 0;
      int pushIterations = this.pushIterations;
      int n = isBigTable ? 4 : 3;

      while(true) {
         int evictedKey;
         V evictedValue;
         switch (ObjectMap.random.nextInt(n)) {
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

         index1 = evictedKey & mask;
         key1 = keyTable[index1];
         if (key1 == 0) {
            keyTable[index1] = evictedKey;
            valueTable[index1] = evictedValue;
            if (this.size++ >= this.threshold) {
               this.resize(this.capacity << 1);
            }

            return;
         }

         index2 = this.hash2(evictedKey);
         key2 = keyTable[index2];
         if (key2 == 0) {
            keyTable[index2] = evictedKey;
            valueTable[index2] = evictedValue;
            if (this.size++ >= this.threshold) {
               this.resize(this.capacity << 1);
            }

            return;
         }

         index3 = this.hash3(evictedKey);
         key3 = keyTable[index3];
         if (key3 == 0) {
            keyTable[index3] = evictedKey;
            valueTable[index3] = evictedValue;
            if (this.size++ >= this.threshold) {
               this.resize(this.capacity << 1);
            }

            return;
         }

         if (isBigTable) {
            index4 = this.hash4(evictedKey);
            key4 = keyTable[index4];
            if (key4 == 0) {
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

   private void putStash(int key, Object value) {
      if (this.stashSize == this.stashCapacity) {
         this.resize(this.capacity << 1);
         this.put(key, value);
      } else {
         int index = this.capacity + this.stashSize;
         this.keyTable[index] = key;
         this.valueTable[index] = value;
         ++this.stashSize;
         ++this.size;
      }
   }

   public Object get(int key) {
      if (key == 0) {
         return !this.hasZeroValue ? null : this.zeroValue;
      } else {
         int index = key & this.mask;
         if (this.keyTable[index] != key) {
            index = this.hash2(key);
            if (this.keyTable[index] != key) {
               index = this.hash3(key);
               if (this.keyTable[index] != key) {
                  if (!this.isBigTable) {
                     return this.getStash(key, (Object)null);
                  }

                  index = this.hash4(key);
                  if (this.keyTable[index] != key) {
                     return this.getStash(key, (Object)null);
                  }
               }
            }
         }

         return this.valueTable[index];
      }
   }

   public Object get(int key, Object defaultValue) {
      if (key == 0) {
         return !this.hasZeroValue ? defaultValue : this.zeroValue;
      } else {
         int index = key & this.mask;
         if (this.keyTable[index] != key) {
            index = this.hash2(key);
            if (this.keyTable[index] != key) {
               index = this.hash3(key);
               if (this.keyTable[index] != key) {
                  if (!this.isBigTable) {
                     return this.getStash(key, defaultValue);
                  }

                  index = this.hash4(key);
                  if (this.keyTable[index] != key) {
                     return this.getStash(key, defaultValue);
                  }
               }
            }
         }

         return this.valueTable[index];
      }
   }

   private Object getStash(int key, Object defaultValue) {
      int[] keyTable = this.keyTable;
      int i = this.capacity;

      for(int n = i + this.stashSize; i < n; ++i) {
         if (keyTable[i] == key) {
            return this.valueTable[i];
         }
      }

      return defaultValue;
   }

   public Object remove(int key) {
      if (key == 0) {
         if (!this.hasZeroValue) {
            return null;
         } else {
            V oldValue = (V)this.zeroValue;
            this.zeroValue = null;
            this.hasZeroValue = false;
            --this.size;
            return oldValue;
         }
      } else {
         int index = key & this.mask;
         if (this.keyTable[index] == key) {
            this.keyTable[index] = 0;
            V oldValue = (V)this.valueTable[index];
            this.valueTable[index] = null;
            --this.size;
            return oldValue;
         } else {
            index = this.hash2(key);
            if (this.keyTable[index] == key) {
               this.keyTable[index] = 0;
               V oldValue = (V)this.valueTable[index];
               this.valueTable[index] = null;
               --this.size;
               return oldValue;
            } else {
               index = this.hash3(key);
               if (this.keyTable[index] == key) {
                  this.keyTable[index] = 0;
                  V oldValue = (V)this.valueTable[index];
                  this.valueTable[index] = null;
                  --this.size;
                  return oldValue;
               } else {
                  if (this.isBigTable) {
                     index = this.hash4(key);
                     if (this.keyTable[index] == key) {
                        this.keyTable[index] = 0;
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
   }

   Object removeStash(int key) {
      int[] keyTable = this.keyTable;
      int i = this.capacity;

      for(int n = i + this.stashSize; i < n; ++i) {
         if (keyTable[i] == key) {
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
            maximumCapacity = ObjectMap.nextPowerOfTwo(maximumCapacity);
            this.resize(maximumCapacity);
         }
      }
   }

   public void clear(int maximumCapacity) {
      if (this.capacity <= maximumCapacity) {
         this.clear();
      } else {
         this.zeroValue = null;
         this.hasZeroValue = false;
         this.size = 0;
         this.resize(maximumCapacity);
      }
   }

   public void clear() {
      int[] keyTable = this.keyTable;
      V[] valueTable = (V[])this.valueTable;

      for(int i = this.capacity + this.stashSize; i-- > 0; valueTable[i] = null) {
         keyTable[i] = 0;
      }

      this.size = 0;
      this.stashSize = 0;
      this.zeroValue = null;
      this.hasZeroValue = false;
   }

   public boolean containsValue(Object value, boolean identity) {
      V[] valueTable = (V[])this.valueTable;
      if (value == null) {
         if (this.hasZeroValue && this.zeroValue == null) {
            return true;
         }

         int[] keyTable = this.keyTable;
         int i = this.capacity + this.stashSize;

         while(i-- > 0) {
            if (keyTable[i] != 0 && valueTable[i] == null) {
               return true;
            }
         }
      } else if (identity) {
         if (value == this.zeroValue) {
            return true;
         }

         int i = this.capacity + this.stashSize;

         while(i-- > 0) {
            if (valueTable[i] == value) {
               return true;
            }
         }
      } else {
         if (this.hasZeroValue && value.equals(this.zeroValue)) {
            return true;
         }

         int i = this.capacity + this.stashSize;

         while(i-- > 0) {
            if (value.equals(valueTable[i])) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean containsKey(int key) {
      if (key == 0) {
         return this.hasZeroValue;
      } else {
         int index = key & this.mask;
         if (this.keyTable[index] != key) {
            index = this.hash2(key);
            if (this.keyTable[index] != key) {
               index = this.hash3(key);
               if (this.keyTable[index] != key) {
                  if (!this.isBigTable) {
                     return this.containsKeyStash(key);
                  }

                  index = this.hash4(key);
                  if (this.keyTable[index] != key) {
                     return this.containsKeyStash(key);
                  }
               }
            }
         }

         return true;
      }
   }

   private boolean containsKeyStash(int key) {
      int[] keyTable = this.keyTable;
      int i = this.capacity;

      for(int n = i + this.stashSize; i < n; ++i) {
         if (keyTable[i] == key) {
            return true;
         }
      }

      return false;
   }

   public int findKey(Object value, boolean identity, int notFound) {
      V[] valueTable = (V[])this.valueTable;
      if (value == null) {
         if (this.hasZeroValue && this.zeroValue == null) {
            return 0;
         }

         int[] keyTable = this.keyTable;
         int i = this.capacity + this.stashSize;

         while(i-- > 0) {
            if (keyTable[i] != 0 && valueTable[i] == null) {
               return keyTable[i];
            }
         }
      } else if (identity) {
         if (value == this.zeroValue) {
            return 0;
         }

         int i = this.capacity + this.stashSize;

         while(i-- > 0) {
            if (valueTable[i] == value) {
               return this.keyTable[i];
            }
         }
      } else {
         if (this.hasZeroValue && value.equals(this.zeroValue)) {
            return 0;
         }

         int i = this.capacity + this.stashSize;

         while(i-- > 0) {
            if (value.equals(valueTable[i])) {
               return this.keyTable[i];
            }
         }
      }

      return notFound;
   }

   public void ensureCapacity(int additionalCapacity) {
      int sizeNeeded = this.size + additionalCapacity;
      if (sizeNeeded >= this.threshold) {
         this.resize(ObjectMap.nextPowerOfTwo((int)((float)sizeNeeded / this.loadFactor)));
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
      int[] oldKeyTable = this.keyTable;
      V[] oldValueTable = (V[])this.valueTable;
      this.keyTable = new int[newSize + this.stashCapacity];
      this.valueTable = new Object[newSize + this.stashCapacity];
      int oldSize = this.size;
      this.size = this.hasZeroValue ? 1 : 0;
      this.stashSize = 0;
      if (oldSize > 0) {
         for(int i = 0; i < oldEndIndex; ++i) {
            int key = oldKeyTable[i];
            if (key != 0) {
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
         return "[]";
      } else {
         StringBuilder buffer = new StringBuilder(32);
         buffer.append('[');
         int[] keyTable = this.keyTable;
         V[] valueTable = (V[])this.valueTable;
         int i = keyTable.length;
         if (this.hasZeroValue) {
            buffer.append("0=");
            buffer.append(this.zeroValue);
         } else {
            while(i-- > 0) {
               int key = keyTable[i];
               if (key != 0) {
                  buffer.append(key);
                  buffer.append('=');
                  buffer.append(valueTable[i]);
                  break;
               }
            }
         }

         while(i-- > 0) {
            int key = keyTable[i];
            if (key != 0) {
               buffer.append(", ");
               buffer.append(key);
               buffer.append('=');
               buffer.append(valueTable[i]);
            }
         }

         buffer.append(']');
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

   public static class Entry {
      public int key;
      public Object value;

      public String toString() {
         return this.key + "=" + this.value;
      }
   }

   private static class MapIterator {
      static final int INDEX_ILLEGAL = -2;
      static final int INDEX_ZERO = -1;
      public boolean hasNext;
      final IntMap map;
      int nextIndex;
      int currentIndex;

      public MapIterator(IntMap map) {
         this.map = map;
         this.reset();
      }

      public void reset() {
         this.currentIndex = -2;
         this.nextIndex = -1;
         if (this.map.hasZeroValue) {
            this.hasNext = true;
         } else {
            this.findNextIndex();
         }

      }

      void findNextIndex() {
         this.hasNext = false;
         int[] keyTable = this.map.keyTable;
         int n = this.map.capacity + this.map.stashSize;

         while(++this.nextIndex < n) {
            if (keyTable[this.nextIndex] != 0) {
               this.hasNext = true;
               break;
            }
         }

      }

      public void remove() {
         if (this.currentIndex == -1 && this.map.hasZeroValue) {
            this.map.zeroValue = null;
            this.map.hasZeroValue = false;
         } else {
            if (this.currentIndex < 0) {
               throw new IllegalStateException("next must be called before remove.");
            }

            if (this.currentIndex >= this.map.capacity) {
               this.map.removeStashIndex(this.currentIndex);
               this.nextIndex = this.currentIndex - 1;
               this.findNextIndex();
            } else {
               this.map.keyTable[this.currentIndex] = 0;
               this.map.valueTable[this.currentIndex] = null;
            }
         }

         this.currentIndex = -2;
         --this.map.size;
      }
   }

   public static class Entries extends MapIterator implements Iterable, Iterator {
      private Entry entry = new Entry();

      public Entries(IntMap map) {
         super(map);
      }

      public Entry next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else {
            int[] keyTable = this.map.keyTable;
            if (this.nextIndex == -1) {
               this.entry.key = 0;
               this.entry.value = this.map.zeroValue;
            } else {
               this.entry.key = keyTable[this.nextIndex];
               this.entry.value = this.map.valueTable[this.nextIndex];
            }

            this.currentIndex = this.nextIndex;
            this.findNextIndex();
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
      public Values(IntMap map) {
         super(map);
      }

      public boolean hasNext() {
         return this.hasNext;
      }

      public Object next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else {
            V value;
            if (this.nextIndex == -1) {
               value = (V)this.map.zeroValue;
            } else {
               value = (V)this.map.valueTable[this.nextIndex];
            }

            this.currentIndex = this.nextIndex;
            this.findNextIndex();
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
   }

   public static class Keys extends MapIterator {
      public Keys(IntMap map) {
         super(map);
      }

      public int next() {
         if (!this.hasNext) {
            throw new NoSuchElementException();
         } else {
            int key = this.nextIndex == -1 ? 0 : this.map.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return key;
         }
      }

      public IntArray toArray() {
         IntArray array = new IntArray(true, this.map.size);

         while(this.hasNext) {
            array.add(this.next());
         }

         return array;
      }
   }
}
