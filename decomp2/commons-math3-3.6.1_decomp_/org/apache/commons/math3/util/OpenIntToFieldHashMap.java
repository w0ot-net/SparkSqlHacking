package org.apache.commons.math3.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;

public class OpenIntToFieldHashMap implements Serializable {
   protected static final byte FREE = 0;
   protected static final byte FULL = 1;
   protected static final byte REMOVED = 2;
   private static final long serialVersionUID = -9179080286849120720L;
   private static final float LOAD_FACTOR = 0.5F;
   private static final int DEFAULT_EXPECTED_SIZE = 16;
   private static final int RESIZE_MULTIPLIER = 2;
   private static final int PERTURB_SHIFT = 5;
   private final Field field;
   private int[] keys;
   private FieldElement[] values;
   private byte[] states;
   private final FieldElement missingEntries;
   private int size;
   private int mask;
   private transient int count;

   public OpenIntToFieldHashMap(Field field) {
      this(field, 16, (FieldElement)field.getZero());
   }

   public OpenIntToFieldHashMap(Field field, FieldElement missingEntries) {
      this(field, 16, missingEntries);
   }

   public OpenIntToFieldHashMap(Field field, int expectedSize) {
      this(field, expectedSize, (FieldElement)field.getZero());
   }

   public OpenIntToFieldHashMap(Field field, int expectedSize, FieldElement missingEntries) {
      this.field = field;
      int capacity = computeCapacity(expectedSize);
      this.keys = new int[capacity];
      this.values = this.buildArray(capacity);
      this.states = new byte[capacity];
      this.missingEntries = missingEntries;
      this.mask = capacity - 1;
   }

   public OpenIntToFieldHashMap(OpenIntToFieldHashMap source) {
      this.field = source.field;
      int length = source.keys.length;
      this.keys = new int[length];
      System.arraycopy(source.keys, 0, this.keys, 0, length);
      this.values = this.buildArray(length);
      System.arraycopy(source.values, 0, this.values, 0, length);
      this.states = new byte[length];
      System.arraycopy(source.states, 0, this.states, 0, length);
      this.missingEntries = source.missingEntries;
      this.size = source.size;
      this.mask = source.mask;
      this.count = source.count;
   }

   private static int computeCapacity(int expectedSize) {
      if (expectedSize == 0) {
         return 1;
      } else {
         int capacity = (int)FastMath.ceil((double)((float)expectedSize / 0.5F));
         int powerOfTwo = Integer.highestOneBit(capacity);
         return powerOfTwo == capacity ? capacity : nextPowerOfTwo(capacity);
      }
   }

   private static int nextPowerOfTwo(int i) {
      return Integer.highestOneBit(i) << 1;
   }

   public FieldElement get(int key) {
      int hash = hashOf(key);
      int index = hash & this.mask;
      if (this.containsKey(key, index)) {
         return this.values[index];
      } else if (this.states[index] == 0) {
         return this.missingEntries;
      } else {
         int j = index;

         for(int perturb = perturb(hash); this.states[index] != 0; perturb >>= 5) {
            j = probe(perturb, j);
            index = j & this.mask;
            if (this.containsKey(key, index)) {
               return this.values[index];
            }
         }

         return this.missingEntries;
      }
   }

   public boolean containsKey(int key) {
      int hash = hashOf(key);
      int index = hash & this.mask;
      if (this.containsKey(key, index)) {
         return true;
      } else if (this.states[index] == 0) {
         return false;
      } else {
         int j = index;

         for(int perturb = perturb(hash); this.states[index] != 0; perturb >>= 5) {
            j = probe(perturb, j);
            index = j & this.mask;
            if (this.containsKey(key, index)) {
               return true;
            }
         }

         return false;
      }
   }

   public Iterator iterator() {
      return new Iterator();
   }

   private static int perturb(int hash) {
      return hash & Integer.MAX_VALUE;
   }

   private int findInsertionIndex(int key) {
      return findInsertionIndex(this.keys, this.states, key, this.mask);
   }

   private static int findInsertionIndex(int[] keys, byte[] states, int key, int mask) {
      int hash = hashOf(key);
      int index = hash & mask;
      if (states[index] == 0) {
         return index;
      } else if (states[index] == 1 && keys[index] == key) {
         return changeIndexSign(index);
      } else {
         int perturb = perturb(hash);
         int j = index;
         if (states[index] == 1) {
            do {
               j = probe(perturb, j);
               index = j & mask;
               perturb >>= 5;
            } while(states[index] == 1 && keys[index] != key);
         }

         if (states[index] == 0) {
            return index;
         } else if (states[index] == 1) {
            return changeIndexSign(index);
         } else {
            int firstRemoved = index;

            while(true) {
               j = probe(perturb, j);
               index = j & mask;
               if (states[index] == 0) {
                  return firstRemoved;
               }

               if (states[index] == 1 && keys[index] == key) {
                  return changeIndexSign(index);
               }

               perturb >>= 5;
            }
         }
      }
   }

   private static int probe(int perturb, int j) {
      return (j << 2) + j + perturb + 1;
   }

   private static int changeIndexSign(int index) {
      return -index - 1;
   }

   public int size() {
      return this.size;
   }

   public FieldElement remove(int key) {
      int hash = hashOf(key);
      int index = hash & this.mask;
      if (this.containsKey(key, index)) {
         return this.doRemove(index);
      } else if (this.states[index] == 0) {
         return this.missingEntries;
      } else {
         int j = index;

         for(int perturb = perturb(hash); this.states[index] != 0; perturb >>= 5) {
            j = probe(perturb, j);
            index = j & this.mask;
            if (this.containsKey(key, index)) {
               return this.doRemove(index);
            }
         }

         return this.missingEntries;
      }
   }

   private boolean containsKey(int key, int index) {
      return (key != 0 || this.states[index] == 1) && this.keys[index] == key;
   }

   private FieldElement doRemove(int index) {
      this.keys[index] = 0;
      this.states[index] = 2;
      T previous = (T)this.values[index];
      this.values[index] = this.missingEntries;
      --this.size;
      ++this.count;
      return previous;
   }

   public FieldElement put(int key, FieldElement value) {
      int index = this.findInsertionIndex(key);
      T previous = (T)this.missingEntries;
      boolean newMapping = true;
      if (index < 0) {
         index = changeIndexSign(index);
         previous = (T)this.values[index];
         newMapping = false;
      }

      this.keys[index] = key;
      this.states[index] = 1;
      this.values[index] = value;
      if (newMapping) {
         ++this.size;
         if (this.shouldGrowTable()) {
            this.growTable();
         }

         ++this.count;
      }

      return previous;
   }

   private void growTable() {
      int oldLength = this.states.length;
      int[] oldKeys = this.keys;
      T[] oldValues = (T[])this.values;
      byte[] oldStates = this.states;
      int newLength = 2 * oldLength;
      int[] newKeys = new int[newLength];
      T[] newValues = (T[])this.buildArray(newLength);
      byte[] newStates = new byte[newLength];
      int newMask = newLength - 1;

      for(int i = 0; i < oldLength; ++i) {
         if (oldStates[i] == 1) {
            int key = oldKeys[i];
            int index = findInsertionIndex(newKeys, newStates, key, newMask);
            newKeys[index] = key;
            newValues[index] = oldValues[i];
            newStates[index] = 1;
         }
      }

      this.mask = newMask;
      this.keys = newKeys;
      this.values = newValues;
      this.states = newStates;
   }

   private boolean shouldGrowTable() {
      return (float)this.size > (float)(this.mask + 1) * 0.5F;
   }

   private static int hashOf(int key) {
      int h = key ^ key >>> 20 ^ key >>> 12;
      return h ^ h >>> 7 ^ h >>> 4;
   }

   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      this.count = 0;
   }

   private FieldElement[] buildArray(int length) {
      return (FieldElement[])Array.newInstance(this.field.getRuntimeClass(), length);
   }

   public class Iterator {
      private final int referenceCount;
      private int current;
      private int next;

      private Iterator() {
         this.referenceCount = OpenIntToFieldHashMap.this.count;
         this.next = -1;

         try {
            this.advance();
         } catch (NoSuchElementException var3) {
         }

      }

      public boolean hasNext() {
         return this.next >= 0;
      }

      public int key() throws ConcurrentModificationException, NoSuchElementException {
         if (this.referenceCount != OpenIntToFieldHashMap.this.count) {
            throw new ConcurrentModificationException();
         } else if (this.current < 0) {
            throw new NoSuchElementException();
         } else {
            return OpenIntToFieldHashMap.this.keys[this.current];
         }
      }

      public FieldElement value() throws ConcurrentModificationException, NoSuchElementException {
         if (this.referenceCount != OpenIntToFieldHashMap.this.count) {
            throw new ConcurrentModificationException();
         } else if (this.current < 0) {
            throw new NoSuchElementException();
         } else {
            return OpenIntToFieldHashMap.this.values[this.current];
         }
      }

      public void advance() throws ConcurrentModificationException, NoSuchElementException {
         if (this.referenceCount != OpenIntToFieldHashMap.this.count) {
            throw new ConcurrentModificationException();
         } else {
            this.current = this.next;

            try {
               while(OpenIntToFieldHashMap.this.states[++this.next] != 1) {
               }
            } catch (ArrayIndexOutOfBoundsException var2) {
               this.next = -2;
               if (this.current < 0) {
                  throw new NoSuchElementException();
               }
            }

         }
      }
   }
}
