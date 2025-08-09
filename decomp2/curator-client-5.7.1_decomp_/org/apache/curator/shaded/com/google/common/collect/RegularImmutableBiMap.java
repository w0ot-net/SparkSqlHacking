package org.apache.curator.shaded.com.google.common.collect;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.concurrent.LazyInit;
import org.apache.curator.shaded.com.google.j2objc.annotations.RetainedWith;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
class RegularImmutableBiMap extends ImmutableBiMap {
   static final RegularImmutableBiMap EMPTY;
   static final double MAX_LOAD_FACTOR = 1.2;
   @CheckForNull
   private final transient @Nullable ImmutableMapEntry[] keyTable;
   @CheckForNull
   private final transient @Nullable ImmutableMapEntry[] valueTable;
   @VisibleForTesting
   final transient Map.Entry[] entries;
   private final transient int mask;
   private final transient int hashCode;
   @LazyInit
   @CheckForNull
   @RetainedWith
   private transient ImmutableBiMap inverse;

   static ImmutableBiMap fromEntries(Map.Entry... entries) {
      return fromEntryArray(entries.length, entries);
   }

   static ImmutableBiMap fromEntryArray(int n, Map.Entry[] entryArray) {
      Preconditions.checkPositionIndex(n, entryArray.length);
      int tableSize = Hashing.closedTableSize(n, 1.2);
      int mask = tableSize - 1;
      ImmutableMapEntry<K, V>[] keyTable = ImmutableMapEntry.createEntryArray(tableSize);
      ImmutableMapEntry<K, V>[] valueTable = ImmutableMapEntry.createEntryArray(tableSize);
      Map.Entry<K, V>[] entries = (Map.Entry<K, V>[])(n == entryArray.length ? entryArray : ImmutableMapEntry.createEntryArray(n));
      int hashCode = 0;

      for(int i = 0; i < n; ++i) {
         Map.Entry<K, V> entry = (Map.Entry)Objects.requireNonNull(entryArray[i]);
         K key = (K)entry.getKey();
         V value = (V)entry.getValue();
         CollectPreconditions.checkEntryNotNull(key, value);
         int keyHash = key.hashCode();
         int valueHash = value.hashCode();
         int keyBucket = Hashing.smear(keyHash) & mask;
         int valueBucket = Hashing.smear(valueHash) & mask;
         ImmutableMapEntry<K, V> nextInKeyBucket = keyTable[keyBucket];
         ImmutableMapEntry<K, V> nextInValueBucket = valueTable[valueBucket];

         try {
            RegularImmutableMap.checkNoConflictInKeyBucket(key, value, nextInKeyBucket, true);
            checkNoConflictInValueBucket(value, entry, nextInValueBucket);
         } catch (RegularImmutableMap.BucketOverflowException var19) {
            return JdkBackedImmutableBiMap.create(n, entryArray);
         }

         ImmutableMapEntry<K, V> newEntry = (ImmutableMapEntry<K, V>)(nextInValueBucket == null && nextInKeyBucket == null ? RegularImmutableMap.makeImmutable(entry, key, value) : new ImmutableMapEntry.NonTerminalImmutableBiMapEntry(key, value, nextInKeyBucket, nextInValueBucket));
         keyTable[keyBucket] = newEntry;
         valueTable[valueBucket] = newEntry;
         entries[i] = newEntry;
         hashCode += keyHash ^ valueHash;
      }

      return new RegularImmutableBiMap(keyTable, valueTable, entries, mask, hashCode);
   }

   private RegularImmutableBiMap(@CheckForNull @Nullable ImmutableMapEntry[] keyTable, @CheckForNull @Nullable ImmutableMapEntry[] valueTable, Map.Entry[] entries, int mask, int hashCode) {
      this.keyTable = keyTable;
      this.valueTable = valueTable;
      this.entries = entries;
      this.mask = mask;
      this.hashCode = hashCode;
   }

   private static void checkNoConflictInValueBucket(Object value, Map.Entry entry, @CheckForNull ImmutableMapEntry valueBucketHead) throws RegularImmutableMap.BucketOverflowException {
      for(int bucketSize = 0; valueBucketHead != null; valueBucketHead = valueBucketHead.getNextInValueBucket()) {
         checkNoConflict(!value.equals(valueBucketHead.getValue()), "value", entry, valueBucketHead);
         ++bucketSize;
         if (bucketSize > 8) {
            throw new RegularImmutableMap.BucketOverflowException();
         }
      }

   }

   @CheckForNull
   public Object get(@CheckForNull Object key) {
      return RegularImmutableMap.get(key, this.keyTable, this.mask);
   }

   ImmutableSet createEntrySet() {
      return (ImmutableSet)(this.isEmpty() ? ImmutableSet.of() : new ImmutableMapEntrySet.RegularEntrySet(this, this.entries));
   }

   ImmutableSet createKeySet() {
      return new ImmutableMapKeySet(this);
   }

   public void forEach(BiConsumer action) {
      Preconditions.checkNotNull(action);

      for(Map.Entry entry : this.entries) {
         action.accept(entry.getKey(), entry.getValue());
      }

   }

   boolean isHashCodeFast() {
      return true;
   }

   public int hashCode() {
      return this.hashCode;
   }

   boolean isPartialView() {
      return false;
   }

   public int size() {
      return this.entries.length;
   }

   public ImmutableBiMap inverse() {
      if (this.isEmpty()) {
         return ImmutableBiMap.of();
      } else {
         ImmutableBiMap<V, K> result = this.inverse;
         return result == null ? (this.inverse = new Inverse()) : result;
      }
   }

   static {
      EMPTY = new RegularImmutableBiMap((ImmutableMapEntry[])null, (ImmutableMapEntry[])null, ImmutableMap.EMPTY_ENTRY_ARRAY, 0, 0);
   }

   private final class Inverse extends ImmutableBiMap {
      private Inverse() {
      }

      public int size() {
         return this.inverse().size();
      }

      public ImmutableBiMap inverse() {
         return RegularImmutableBiMap.this;
      }

      public void forEach(BiConsumer action) {
         Preconditions.checkNotNull(action);
         RegularImmutableBiMap.this.forEach((k, v) -> action.accept(v, k));
      }

      @CheckForNull
      public Object get(@CheckForNull Object value) {
         if (value != null && RegularImmutableBiMap.this.valueTable != null) {
            int bucket = Hashing.smear(value.hashCode()) & RegularImmutableBiMap.this.mask;

            for(ImmutableMapEntry<K, V> entry = RegularImmutableBiMap.this.valueTable[bucket]; entry != null; entry = entry.getNextInValueBucket()) {
               if (value.equals(entry.getValue())) {
                  return entry.getKey();
               }
            }

            return null;
         } else {
            return null;
         }
      }

      ImmutableSet createKeySet() {
         return new ImmutableMapKeySet(this);
      }

      ImmutableSet createEntrySet() {
         return new InverseEntrySet();
      }

      boolean isPartialView() {
         return false;
      }

      @J2ktIncompatible
      Object writeReplace() {
         return new InverseSerializedForm(RegularImmutableBiMap.this);
      }

      @J2ktIncompatible
      private void readObject(ObjectInputStream stream) throws InvalidObjectException {
         throw new InvalidObjectException("Use InverseSerializedForm");
      }

      final class InverseEntrySet extends ImmutableMapEntrySet {
         ImmutableMap map() {
            return Inverse.this;
         }

         boolean isHashCodeFast() {
            return true;
         }

         public int hashCode() {
            return RegularImmutableBiMap.this.hashCode;
         }

         public UnmodifiableIterator iterator() {
            return this.asList().iterator();
         }

         public void forEach(Consumer action) {
            this.asList().forEach(action);
         }

         ImmutableList createAsList() {
            return new ImmutableAsList() {
               public Map.Entry get(int index) {
                  Map.Entry<K, V> entry = RegularImmutableBiMap.this.entries[index];
                  return Maps.immutableEntry(entry.getValue(), entry.getKey());
               }

               ImmutableCollection delegateCollection() {
                  return InverseEntrySet.this;
               }
            };
         }
      }
   }

   @J2ktIncompatible
   private static class InverseSerializedForm implements Serializable {
      private final ImmutableBiMap forward;
      private static final long serialVersionUID = 1L;

      InverseSerializedForm(ImmutableBiMap forward) {
         this.forward = forward;
      }

      Object readResolve() {
         return this.forward.inverse();
      }
   }
}
