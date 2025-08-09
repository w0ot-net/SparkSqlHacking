package org.apache.logging.log4j.internal.map;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.TriConsumer;

public class UnmodifiableArrayBackedMap extends AbstractMap implements Serializable, ReadOnlyStringMap {
   private static final long serialVersionUID = 6849423432534211514L;
   public static final UnmodifiableArrayBackedMap EMPTY_MAP = new UnmodifiableArrayBackedMap(0);
   private static final int NUM_FIXED_ARRAY_ENTRIES = 1;
   private Object[] backingArray;
   private int numEntries;

   private static int getArrayIndexForKey(int entryIndex) {
      return 2 * entryIndex + 1;
   }

   private static int getArrayIndexForValue(int entryIndex) {
      return 2 * entryIndex + 1 + 1;
   }

   public static UnmodifiableArrayBackedMap getMap(Object[] backingArray) {
      return backingArray != null && backingArray.length != 1 ? new UnmodifiableArrayBackedMap(backingArray) : EMPTY_MAP;
   }

   private UnmodifiableArrayBackedMap(int capacity) {
      this.backingArray = new Object[capacity * 2 + 1];
      this.backingArray[0] = 0;
   }

   private UnmodifiableArrayBackedMap(Object[] backingArray) {
      this.numEntries = backingArray == null ? 0 : (Integer)backingArray[0];
      this.backingArray = backingArray;
   }

   UnmodifiableArrayBackedMap(UnmodifiableArrayBackedMap other) {
      this.backingArray = other.backingArray;
      this.numEntries = other.numEntries;
   }

   private void add(String key, String value) {
      this.backingArray[getArrayIndexForKey(this.numEntries)] = key;
      this.backingArray[getArrayIndexForValue(this.numEntries)] = value;
      ++this.numEntries;
   }

   public void clear() {
      throw new UnsupportedOperationException("Instance cannot be cleared, reuse EMPTY_MAP instead.");
   }

   public boolean containsKey(Object key) {
      return this.containsKey((String)key);
   }

   public boolean containsKey(String key) {
      int hashCode = key.hashCode();

      for(int i = 0; i < this.numEntries; ++i) {
         if (this.backingArray[getArrayIndexForKey(i)].hashCode() == hashCode && this.backingArray[getArrayIndexForKey(i)].equals(key)) {
            return true;
         }
      }

      return false;
   }

   public Object[] getBackingArray() {
      return this.backingArray;
   }

   public boolean containsValue(Object value) {
      for(int i = 0; i < this.numEntries; ++i) {
         Object valueInMap = this.backingArray[getArrayIndexForValue(i)];
         if (value == null) {
            if (valueInMap == null) {
               return true;
            }
         } else if (value.equals(valueInMap)) {
            return true;
         }
      }

      return false;
   }

   public UnmodifiableArrayBackedMap copyAndPut(String key, String value) {
      UnmodifiableArrayBackedMap newMap = new UnmodifiableArrayBackedMap(this.numEntries + 1);
      if (this.numEntries > 0) {
         System.arraycopy(this.backingArray, 1, newMap.backingArray, 1, this.numEntries * 2);
         newMap.numEntries = this.numEntries;
      }

      newMap.addOrOverwriteKey(key, value);
      newMap.updateNumEntriesInArray();
      return newMap;
   }

   public UnmodifiableArrayBackedMap copyAndPutAll(Map entriesToAdd) {
      UnmodifiableArrayBackedMap newMap = new UnmodifiableArrayBackedMap(this.numEntries + entriesToAdd.size());
      if (this.numEntries > 0) {
         System.arraycopy(this.backingArray, 0, newMap.backingArray, 0, this.numEntries * 2 + 1);
         newMap.numEntries = this.numEntries;
      }

      for(Map.Entry entry : entriesToAdd.entrySet()) {
         String key = (String)entry.getKey();
         String value = (String)entry.getValue();
         if (!this.isEmpty()) {
            newMap.addOrOverwriteKey(key, value);
         } else {
            newMap.add(key, value);
         }
      }

      newMap.updateNumEntriesInArray();
      return newMap;
   }

   public UnmodifiableArrayBackedMap copyAndRemove(String key) {
      int indexToRemove = -1;

      for(int oldIndex = this.numEntries - 1; oldIndex >= 0; --oldIndex) {
         if (this.backingArray[getArrayIndexForKey(oldIndex)].hashCode() == key.hashCode() && this.backingArray[getArrayIndexForKey(oldIndex)].equals(key)) {
            indexToRemove = oldIndex;
            break;
         }
      }

      if (indexToRemove == -1) {
         return this;
      } else if (this.numEntries == 1) {
         return EMPTY_MAP;
      } else {
         UnmodifiableArrayBackedMap newMap = new UnmodifiableArrayBackedMap(this.numEntries);
         if (indexToRemove > 0) {
            System.arraycopy(this.backingArray, 1, newMap.backingArray, 1, indexToRemove * 2);
         }

         if (indexToRemove + 1 < this.numEntries) {
            int nextIndexToCopy = indexToRemove + 1;
            int numRemainingEntries = this.numEntries - nextIndexToCopy;
            System.arraycopy(this.backingArray, getArrayIndexForKey(nextIndexToCopy), newMap.backingArray, getArrayIndexForKey(indexToRemove), numRemainingEntries * 2);
         }

         newMap.numEntries = this.numEntries - 1;
         newMap.updateNumEntriesInArray();
         return newMap;
      }
   }

   public UnmodifiableArrayBackedMap copyAndRemoveAll(Iterable keysToRemoveIterable) {
      if (this.isEmpty()) {
         return EMPTY_MAP;
      } else {
         Set<String> keysToRemove;
         if (keysToRemoveIterable instanceof Set) {
            keysToRemove = (Set)keysToRemoveIterable;
         } else {
            keysToRemove = new HashSet();

            for(String key : keysToRemoveIterable) {
               keysToRemove.add(key);
            }
         }

         UnmodifiableArrayBackedMap oldMap = this;
         int oldMapEntryCount = this.numEntries;
         UnmodifiableArrayBackedMap newMap = new UnmodifiableArrayBackedMap(oldMapEntryCount);
         if (keysToRemove.isEmpty()) {
            System.arraycopy(this.backingArray, 0, newMap.backingArray, 0, oldMapEntryCount * 2);
            newMap.numEntries = oldMapEntryCount;
            return this;
         } else {
            int newMapEntryIndex = 0;

            for(int oldMapEntryIndex = 0; oldMapEntryIndex < oldMapEntryCount; ++oldMapEntryIndex) {
               int oldMapKeyIndex = getArrayIndexForKey(oldMapEntryIndex);
               Object key = oldMap.backingArray[oldMapKeyIndex];
               boolean removed = keysToRemove.contains(key);
               if (!removed) {
                  int oldMapValueIndex = getArrayIndexForValue(oldMapEntryIndex);
                  Object value = oldMap.backingArray[oldMapValueIndex];
                  int newMapKeyIndex = getArrayIndexForKey(newMapEntryIndex);
                  int newMapValueIndex = getArrayIndexForValue(newMapEntryIndex);
                  newMap.backingArray[newMapKeyIndex] = key;
                  newMap.backingArray[newMapValueIndex] = value;
                  ++newMapEntryIndex;
               }
            }

            newMap.numEntries = newMapEntryIndex;
            newMap.updateNumEntriesInArray();
            return newMap;
         }
      }
   }

   private void updateNumEntriesInArray() {
      this.backingArray[0] = this.numEntries;
   }

   public void forEach(BiConsumer action) {
      for(int i = 0; i < this.numEntries; ++i) {
         String key = (String)this.backingArray[getArrayIndexForKey(i)];
         String value = (String)this.backingArray[getArrayIndexForValue(i)];
         action.accept(key, value);
      }

   }

   public void forEach(final org.apache.logging.log4j.util.BiConsumer action) {
      for(int i = 0; i < this.numEntries; ++i) {
         String key = (String)this.backingArray[getArrayIndexForKey(i)];
         V value = (V)this.backingArray[getArrayIndexForValue(i)];
         action.accept(key, value);
      }

   }

   public void forEach(final TriConsumer action, final Object state) {
      for(int i = 0; i < this.numEntries; ++i) {
         String key = (String)this.backingArray[getArrayIndexForKey(i)];
         V value = (V)this.backingArray[getArrayIndexForValue(i)];
         action.accept(key, value, state);
      }

   }

   public Set entrySet() {
      return new UnmodifiableEntrySet();
   }

   public String get(Object key) {
      return (String)this.getValue((String)key);
   }

   public Object getValue(String key) {
      if (this.numEntries == 0) {
         return null;
      } else {
         int hashCode = key.hashCode();

         for(int i = 0; i < this.numEntries; ++i) {
            if (this.backingArray[getArrayIndexForKey(i)].hashCode() == hashCode && this.backingArray[getArrayIndexForKey(i)].equals(key)) {
               return this.backingArray[getArrayIndexForValue(i)];
            }
         }

         return null;
      }
   }

   private void addOrOverwriteKey(String key, String value) {
      int keyHashCode = key.hashCode();

      for(int i = 0; i < this.numEntries; ++i) {
         if (this.backingArray[getArrayIndexForKey(i)].hashCode() == keyHashCode && this.backingArray[getArrayIndexForKey(i)].equals(key)) {
            this.backingArray[getArrayIndexForValue(i)] = value;
            return;
         }
      }

      this.add(key, value);
   }

   public String put(String key, String value) {
      throw new UnsupportedOperationException("put() is not supported, use copyAndPut instead");
   }

   public void putAll(Map m) {
      throw new UnsupportedOperationException("putAll() is not supported, use copyAndPutAll instead");
   }

   public String remove(Object key) {
      throw new UnsupportedOperationException("remove() is not supported, use copyAndRemove instead");
   }

   public int size() {
      return this.numEntries;
   }

   public Map toMap() {
      return this;
   }

   private class UnmodifiableEntry implements Map.Entry {
      private int index;

      public UnmodifiableEntry(int index) {
         this.index = index;
      }

      public String getKey() {
         return (String)UnmodifiableArrayBackedMap.this.backingArray[UnmodifiableArrayBackedMap.getArrayIndexForKey(this.index)];
      }

      public String getValue() {
         return (String)UnmodifiableArrayBackedMap.this.backingArray[UnmodifiableArrayBackedMap.getArrayIndexForValue(this.index)];
      }

      public int hashCode() {
         String key = (String)UnmodifiableArrayBackedMap.this.backingArray[UnmodifiableArrayBackedMap.getArrayIndexForKey(this.index)];
         String value = (String)UnmodifiableArrayBackedMap.this.backingArray[UnmodifiableArrayBackedMap.getArrayIndexForValue(this.index)];
         return Objects.hashCode(key) ^ Objects.hashCode(value);
      }

      public String setValue(String value) {
         throw new UnsupportedOperationException("Cannot update Entry instances in UnmodifiableArrayBackedMap");
      }
   }

   private class UnmodifiableEntryIterator implements Iterator {
      private int index;

      private UnmodifiableEntryIterator() {
      }

      public boolean hasNext() {
         return this.index < UnmodifiableArrayBackedMap.this.numEntries;
      }

      public Map.Entry next() {
         return UnmodifiableArrayBackedMap.this.new UnmodifiableEntry(this.index++);
      }
   }

   private class UnmodifiableEntrySet extends AbstractSet {
      private UnmodifiableEntrySet() {
      }

      public boolean add(Map.Entry e) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(Collection c) {
         throw new UnsupportedOperationException();
      }

      public Iterator iterator() {
         return UnmodifiableArrayBackedMap.this.new UnmodifiableEntryIterator();
      }

      public int size() {
         return UnmodifiableArrayBackedMap.this.numEntries;
      }
   }
}
