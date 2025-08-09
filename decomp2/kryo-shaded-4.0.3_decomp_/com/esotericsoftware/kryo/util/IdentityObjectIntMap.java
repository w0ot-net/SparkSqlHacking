package com.esotericsoftware.kryo.util;

public class IdentityObjectIntMap {
   private static final int PRIME2 = -1105259343;
   private static final int PRIME3 = -1262997959;
   private static final int PRIME4 = -825114047;
   public int size;
   Object[] keyTable;
   int[] valueTable;
   int capacity;
   int stashSize;
   private float loadFactor;
   private int hashShift;
   private int mask;
   private int threshold;
   private int stashCapacity;
   private int pushIterations;
   private boolean isBigTable;

   public IdentityObjectIntMap() {
      this(32, 0.8F);
   }

   public IdentityObjectIntMap(int initialCapacity) {
      this(initialCapacity, 0.8F);
   }

   public IdentityObjectIntMap(int initialCapacity, float loadFactor) {
      if (initialCapacity < 0) {
         throw new IllegalArgumentException("initialCapacity must be >= 0: " + initialCapacity);
      } else if (this.capacity > 1073741824) {
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
            this.keyTable = new Object[this.capacity + this.stashCapacity];
            this.valueTable = new int[this.keyTable.length];
         }
      }
   }

   public void put(Object key, int value) {
      if (key == null) {
         throw new IllegalArgumentException("key cannot be null.");
      } else {
         K[] keyTable = (K[])this.keyTable;
         int mask = this.mask;
         boolean isBigTable = this.isBigTable;
         int hashCode = System.identityHashCode(key);
         int index1 = hashCode & mask;
         K key1 = (K)keyTable[index1];
         if (key == key1) {
            this.valueTable[index1] = value;
         } else {
            int index2 = this.hash2(hashCode);
            K key2 = (K)keyTable[index2];
            if (key == key2) {
               this.valueTable[index2] = value;
            } else {
               int index3 = this.hash3(hashCode);
               K key3 = (K)keyTable[index3];
               if (key == key3) {
                  this.valueTable[index3] = value;
               } else {
                  int index4 = -1;
                  K key4 = (K)null;
                  if (isBigTable) {
                     index4 = this.hash4(hashCode);
                     key4 = (K)keyTable[index4];
                     if (key == key4) {
                        this.valueTable[index4] = value;
                        return;
                     }
                  }

                  int i = this.capacity;

                  for(int n = i + this.stashSize; i < n; ++i) {
                     if (keyTable[i] == key) {
                        this.valueTable[i] = value;
                        return;
                     }
                  }

                  if (key1 == null) {
                     keyTable[index1] = key;
                     this.valueTable[index1] = value;
                     if (this.size++ >= this.threshold) {
                        this.resize(this.capacity << 1);
                     }

                  } else if (key2 == null) {
                     keyTable[index2] = key;
                     this.valueTable[index2] = value;
                     if (this.size++ >= this.threshold) {
                        this.resize(this.capacity << 1);
                     }

                  } else if (key3 == null) {
                     keyTable[index3] = key;
                     this.valueTable[index3] = value;
                     if (this.size++ >= this.threshold) {
                        this.resize(this.capacity << 1);
                     }

                  } else if (isBigTable && key4 == null) {
                     keyTable[index4] = key;
                     this.valueTable[index4] = value;
                     if (this.size++ >= this.threshold) {
                        this.resize(this.capacity << 1);
                     }

                  } else {
                     this.push(key, value, index1, key1, index2, key2, index3, key3, index4, key4);
                  }
               }
            }
         }
      }
   }

   private void putResize(Object key, int value) {
      int hashCode = System.identityHashCode(key);
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

   private void push(Object insertKey, int insertValue, int index1, Object key1, int index2, Object key2, int index3, Object key3, int index4, Object key4) {
      K[] keyTable = (K[])this.keyTable;
      int[] valueTable = this.valueTable;
      int mask = this.mask;
      boolean isBigTable = this.isBigTable;
      int i = 0;
      int pushIterations = this.pushIterations;
      int n = isBigTable ? 4 : 3;

      while(true) {
         K evictedKey;
         int evictedValue;
         switch (ObjectMap.random.nextInt(n)) {
            case 0:
               evictedKey = key1;
               evictedValue = valueTable[index1];
               keyTable[index1] = insertKey;
               valueTable[index1] = insertValue;
               break;
            case 1:
               evictedKey = key2;
               evictedValue = valueTable[index2];
               keyTable[index2] = insertKey;
               valueTable[index2] = insertValue;
               break;
            case 2:
               evictedKey = key3;
               evictedValue = valueTable[index3];
               keyTable[index3] = insertKey;
               valueTable[index3] = insertValue;
               break;
            default:
               evictedKey = key4;
               evictedValue = valueTable[index4];
               keyTable[index4] = insertKey;
               valueTable[index4] = insertValue;
         }

         int hashCode = System.identityHashCode(evictedKey);
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

   private void putStash(Object key, int value) {
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

   public int get(Object key, int defaultValue) {
      int hashCode = System.identityHashCode(key);
      int index = hashCode & this.mask;
      if (key != this.keyTable[index]) {
         index = this.hash2(hashCode);
         if (key != this.keyTable[index]) {
            index = this.hash3(hashCode);
            if (key != this.keyTable[index]) {
               if (!this.isBigTable) {
                  return this.getStash(key, defaultValue);
               }

               index = this.hash4(hashCode);
               if (key != this.keyTable[index]) {
                  return this.getStash(key, defaultValue);
               }
            }
         }
      }

      return this.valueTable[index];
   }

   private int getStash(Object key, int defaultValue) {
      K[] keyTable = (K[])this.keyTable;
      int i = this.capacity;

      for(int n = i + this.stashSize; i < n; ++i) {
         if (key == keyTable[i]) {
            return this.valueTable[i];
         }
      }

      return defaultValue;
   }

   public int getAndIncrement(Object key, int defaultValue, int increment) {
      int hashCode = System.identityHashCode(key);
      int index = hashCode & this.mask;
      if (key != this.keyTable[index]) {
         index = this.hash2(hashCode);
         if (key != this.keyTable[index]) {
            index = this.hash3(hashCode);
            if (key != this.keyTable[index]) {
               if (!this.isBigTable) {
                  return this.getAndIncrementStash(key, defaultValue, increment);
               }

               index = this.hash4(hashCode);
               if (key != this.keyTable[index]) {
                  return this.getAndIncrementStash(key, defaultValue, increment);
               }
            }
         }
      }

      int value = this.valueTable[index];
      this.valueTable[index] = value + increment;
      return value;
   }

   private int getAndIncrementStash(Object key, int defaultValue, int increment) {
      K[] keyTable = (K[])this.keyTable;
      int i = this.capacity;

      for(int n = i + this.stashSize; i < n; ++i) {
         if (key == keyTable[i]) {
            int value = this.valueTable[i];
            this.valueTable[i] = value + increment;
            return value;
         }
      }

      this.put(key, defaultValue + increment);
      return defaultValue;
   }

   public int remove(Object key, int defaultValue) {
      int hashCode = System.identityHashCode(key);
      int index = hashCode & this.mask;
      if (key == this.keyTable[index]) {
         this.keyTable[index] = null;
         int oldValue = this.valueTable[index];
         --this.size;
         return oldValue;
      } else {
         index = this.hash2(hashCode);
         if (key == this.keyTable[index]) {
            this.keyTable[index] = null;
            int oldValue = this.valueTable[index];
            --this.size;
            return oldValue;
         } else {
            index = this.hash3(hashCode);
            if (key == this.keyTable[index]) {
               this.keyTable[index] = null;
               int oldValue = this.valueTable[index];
               --this.size;
               return oldValue;
            } else {
               if (this.isBigTable) {
                  index = this.hash4(hashCode);
                  if (key == this.keyTable[index]) {
                     this.keyTable[index] = null;
                     int oldValue = this.valueTable[index];
                     --this.size;
                     return oldValue;
                  }
               }

               return this.removeStash(key, defaultValue);
            }
         }
      }
   }

   int removeStash(Object key, int defaultValue) {
      K[] keyTable = (K[])this.keyTable;
      int i = this.capacity;

      for(int n = i + this.stashSize; i < n; ++i) {
         if (key == keyTable[i]) {
            int oldValue = this.valueTable[i];
            this.removeStashIndex(i);
            --this.size;
            return oldValue;
         }
      }

      return defaultValue;
   }

   void removeStashIndex(int index) {
      --this.stashSize;
      int lastIndex = this.capacity + this.stashSize;
      if (index < lastIndex) {
         this.keyTable[index] = this.keyTable[lastIndex];
         this.valueTable[index] = this.valueTable[lastIndex];
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
         this.size = 0;
         this.resize(maximumCapacity);
      }
   }

   public void clear() {
      K[] keyTable = (K[])this.keyTable;

      for(int i = this.capacity + this.stashSize; i-- > 0; keyTable[i] = null) {
      }

      this.size = 0;
      this.stashSize = 0;
   }

   public boolean containsValue(int value) {
      K[] keyTable = (K[])this.keyTable;
      int[] valueTable = this.valueTable;
      int i = this.capacity + this.stashSize;

      while(i-- > 0) {
         if (keyTable[i] != null && valueTable[i] == value) {
            return true;
         }
      }

      return false;
   }

   public boolean containsKey(Object key) {
      int hashCode = System.identityHashCode(key);
      int index = hashCode & this.mask;
      if (key != this.keyTable[index]) {
         index = this.hash2(hashCode);
         if (key != this.keyTable[index]) {
            index = this.hash3(hashCode);
            if (key != this.keyTable[index]) {
               if (!this.isBigTable) {
                  return this.containsKeyStash(key);
               }

               index = this.hash4(hashCode);
               if (key != this.keyTable[index]) {
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
         if (key == keyTable[i]) {
            return true;
         }
      }

      return false;
   }

   public Object findKey(int value) {
      K[] keyTable = (K[])this.keyTable;
      int[] valueTable = this.valueTable;
      int i = this.capacity + this.stashSize;

      while(i-- > 0) {
         if (keyTable[i] != null && valueTable[i] == value) {
            return keyTable[i];
         }
      }

      return null;
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
      K[] oldKeyTable = (K[])this.keyTable;
      int[] oldValueTable = this.valueTable;
      this.keyTable = new Object[newSize + this.stashCapacity];
      this.valueTable = new int[newSize + this.stashCapacity];
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
         int[] valueTable = this.valueTable;
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
}
