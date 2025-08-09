package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
final class RegularImmutableMap extends ImmutableMap {
   static final ImmutableMap EMPTY;
   @VisibleForTesting
   static final double MAX_LOAD_FACTOR = 1.2;
   @VisibleForTesting
   static final double HASH_FLOODING_FPP = 0.001;
   static final int MAX_HASH_BUCKET_LENGTH = 8;
   @VisibleForTesting
   final transient Map.Entry[] entries;
   @CheckForNull
   private final transient @Nullable ImmutableMapEntry[] table;
   private final transient int mask;
   @J2ktIncompatible
   private static final long serialVersionUID = 0L;

   static ImmutableMap fromEntries(Map.Entry... entries) {
      return fromEntryArray(entries.length, entries, true);
   }

   static ImmutableMap fromEntryArray(int n, @Nullable Map.Entry[] entryArray, boolean throwIfDuplicateKeys) {
      Preconditions.checkPositionIndex(n, entryArray.length);
      if (n == 0) {
         ImmutableMap<K, V> empty = EMPTY;
         return empty;
      } else {
         try {
            return fromEntryArrayCheckingBucketOverflow(n, entryArray, throwIfDuplicateKeys);
         } catch (BucketOverflowException var4) {
            return JdkBackedImmutableMap.create(n, entryArray, throwIfDuplicateKeys);
         }
      }
   }

   private static ImmutableMap fromEntryArrayCheckingBucketOverflow(int n, Map.Entry[] entryArray, boolean throwIfDuplicateKeys) throws BucketOverflowException {
      Map.Entry<K, V>[] entries = (Map.Entry<K, V>[])(n == entryArray.length ? entryArray : ImmutableMapEntry.createEntryArray(n));
      int tableSize = Hashing.closedTableSize(n, 1.2);
      ImmutableMapEntry<K, V>[] table = ImmutableMapEntry.createEntryArray(tableSize);
      int mask = tableSize - 1;
      IdentityHashMap<Map.Entry<K, V>, Boolean> duplicates = null;
      int dupCount = 0;

      for(int entryIndex = n - 1; entryIndex >= 0; --entryIndex) {
         Map.Entry<K, V> entry = (Map.Entry)Objects.requireNonNull(entryArray[entryIndex]);
         K key = (K)entry.getKey();
         V value = (V)entry.getValue();
         CollectPreconditions.checkEntryNotNull(key, value);
         int tableIndex = Hashing.smear(key.hashCode()) & mask;
         ImmutableMapEntry<K, V> keyBucketHead = table[tableIndex];
         ImmutableMapEntry<K, V> effectiveEntry = checkNoConflictInKeyBucket(key, value, keyBucketHead, throwIfDuplicateKeys);
         if (effectiveEntry == null) {
            effectiveEntry = (ImmutableMapEntry<K, V>)(keyBucketHead == null ? makeImmutable(entry, key, value) : new ImmutableMapEntry.NonTerminalImmutableMapEntry(key, value, keyBucketHead));
            table[tableIndex] = effectiveEntry;
         } else {
            if (duplicates == null) {
               duplicates = new IdentityHashMap();
            }

            duplicates.put(effectiveEntry, true);
            ++dupCount;
            if (entries == entryArray) {
               entries = (Map.Entry[])(([Ljava.util.Map.Entry;)entries).clone();
            }
         }

         entries[entryIndex] = effectiveEntry;
      }

      if (duplicates != null) {
         entries = removeDuplicates(entries, n, n - dupCount, duplicates);
         int newTableSize = Hashing.closedTableSize(entries.length, 1.2);
         if (newTableSize != tableSize) {
            return fromEntryArrayCheckingBucketOverflow(entries.length, entries, true);
         }
      }

      return new RegularImmutableMap(entries, table, mask);
   }

   static Map.Entry[] removeDuplicates(Map.Entry[] entries, int n, int newN, IdentityHashMap duplicates) {
      Map.Entry<K, V>[] newEntries = ImmutableMapEntry.createEntryArray(newN);
      int in = 0;

      for(int out = 0; in < n; ++in) {
         Map.Entry<K, V> entry = entries[in];
         Boolean status = (Boolean)duplicates.get(entry);
         if (status != null) {
            if (!status) {
               continue;
            }

            duplicates.put(entry, false);
         }

         newEntries[out++] = entry;
      }

      return newEntries;
   }

   static ImmutableMapEntry makeImmutable(Map.Entry entry, Object key, Object value) {
      boolean reusable = entry instanceof ImmutableMapEntry && ((ImmutableMapEntry)entry).isReusable();
      return reusable ? (ImmutableMapEntry)entry : new ImmutableMapEntry(key, value);
   }

   static ImmutableMapEntry makeImmutable(Map.Entry entry) {
      return makeImmutable(entry, entry.getKey(), entry.getValue());
   }

   private RegularImmutableMap(Map.Entry[] entries, @CheckForNull @Nullable ImmutableMapEntry[] table, int mask) {
      this.entries = entries;
      this.table = table;
      this.mask = mask;
   }

   @CheckForNull
   @CanIgnoreReturnValue
   static ImmutableMapEntry checkNoConflictInKeyBucket(Object key, Object newValue, @CheckForNull ImmutableMapEntry keyBucketHead, boolean throwIfDuplicateKeys) throws BucketOverflowException {
      for(int bucketSize = 0; keyBucketHead != null; keyBucketHead = keyBucketHead.getNextInKeyBucket()) {
         if (keyBucketHead.getKey().equals(key)) {
            if (!throwIfDuplicateKeys) {
               return keyBucketHead;
            }

            checkNoConflict(false, "key", keyBucketHead, key + "=" + newValue);
         }

         ++bucketSize;
         if (bucketSize > 8) {
            throw new BucketOverflowException();
         }
      }

      return null;
   }

   @CheckForNull
   public Object get(@CheckForNull Object key) {
      return get(key, this.table, this.mask);
   }

   @CheckForNull
   static Object get(@CheckForNull Object key, @CheckForNull @Nullable ImmutableMapEntry[] keyTable, int mask) {
      if (key != null && keyTable != null) {
         int index = Hashing.smear(key.hashCode()) & mask;

         for(ImmutableMapEntry<?, V> entry = keyTable[index]; entry != null; entry = entry.getNextInKeyBucket()) {
            Object candidateKey = entry.getKey();
            if (key.equals(candidateKey)) {
               return entry.getValue();
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public void forEach(BiConsumer action) {
      Preconditions.checkNotNull(action);

      for(Map.Entry entry : this.entries) {
         action.accept(entry.getKey(), entry.getValue());
      }

   }

   public int size() {
      return this.entries.length;
   }

   boolean isPartialView() {
      return false;
   }

   ImmutableSet createEntrySet() {
      return new ImmutableMapEntrySet.RegularEntrySet(this, this.entries);
   }

   ImmutableSet createKeySet() {
      return new KeySet(this);
   }

   ImmutableCollection createValues() {
      return new Values(this);
   }

   @J2ktIncompatible
   @GwtIncompatible
   Object writeReplace() {
      return super.writeReplace();
   }

   static {
      EMPTY = new RegularImmutableMap(ImmutableMap.EMPTY_ENTRY_ARRAY, (ImmutableMapEntry[])null, 0);
   }

   static class BucketOverflowException extends Exception {
   }

   @GwtCompatible(
      emulated = true
   )
   private static final class KeySet extends IndexedImmutableSet {
      private final RegularImmutableMap map;

      KeySet(RegularImmutableMap map) {
         this.map = map;
      }

      Object get(int index) {
         return this.map.entries[index].getKey();
      }

      public boolean contains(@CheckForNull Object object) {
         return this.map.containsKey(object);
      }

      boolean isPartialView() {
         return true;
      }

      public int size() {
         return this.map.size();
      }

      @J2ktIncompatible
      @GwtIncompatible
      Object writeReplace() {
         return super.writeReplace();
      }

      @GwtIncompatible
      @J2ktIncompatible
      private static class SerializedForm implements Serializable {
         final ImmutableMap map;
         @J2ktIncompatible
         private static final long serialVersionUID = 0L;

         SerializedForm(ImmutableMap map) {
            this.map = map;
         }

         Object readResolve() {
            return this.map.keySet();
         }
      }
   }

   @GwtCompatible(
      emulated = true
   )
   private static final class Values extends ImmutableList {
      final RegularImmutableMap map;

      Values(RegularImmutableMap map) {
         this.map = map;
      }

      public Object get(int index) {
         return this.map.entries[index].getValue();
      }

      public int size() {
         return this.map.size();
      }

      boolean isPartialView() {
         return true;
      }

      @J2ktIncompatible
      @GwtIncompatible
      Object writeReplace() {
         return super.writeReplace();
      }

      @GwtIncompatible
      @J2ktIncompatible
      private static class SerializedForm implements Serializable {
         final ImmutableMap map;
         @J2ktIncompatible
         private static final long serialVersionUID = 0L;

         SerializedForm(ImmutableMap map) {
            this.map = map;
         }

         Object readResolve() {
            return this.map.values();
         }
      }
   }
}
