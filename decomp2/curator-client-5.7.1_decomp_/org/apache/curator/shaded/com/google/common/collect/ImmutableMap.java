package org.apache.curator.shaded.com.google.common.collect;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotCall;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;
import org.apache.curator.shaded.com.google.errorprone.annotations.concurrent.LazyInit;
import org.apache.curator.shaded.com.google.j2objc.annotations.RetainedWith;
import org.checkerframework.checker.nullness.qual.Nullable;

@DoNotMock("Use ImmutableMap.of or another implementation")
@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
public abstract class ImmutableMap implements Map, Serializable {
   static final Map.Entry[] EMPTY_ENTRY_ARRAY = new Map.Entry[0];
   @LazyInit
   @CheckForNull
   @RetainedWith
   private transient ImmutableSet entrySet;
   @LazyInit
   @CheckForNull
   @RetainedWith
   private transient ImmutableSet keySet;
   @LazyInit
   @CheckForNull
   @RetainedWith
   private transient ImmutableCollection values;
   @LazyInit
   @CheckForNull
   private transient ImmutableSetMultimap multimapView;

   public static Collector toImmutableMap(Function keyFunction, Function valueFunction) {
      return CollectCollectors.toImmutableMap(keyFunction, valueFunction);
   }

   public static Collector toImmutableMap(Function keyFunction, Function valueFunction, BinaryOperator mergeFunction) {
      return CollectCollectors.toImmutableMap(keyFunction, valueFunction, mergeFunction);
   }

   public static ImmutableMap of() {
      return RegularImmutableMap.EMPTY;
   }

   public static ImmutableMap of(Object k1, Object v1) {
      return ImmutableBiMap.of(k1, v1);
   }

   public static ImmutableMap of(Object k1, Object v1, Object k2, Object v2) {
      return RegularImmutableMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2));
   }

   public static ImmutableMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3) {
      return RegularImmutableMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3));
   }

   public static ImmutableMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4) {
      return RegularImmutableMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4));
   }

   public static ImmutableMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5) {
      return RegularImmutableMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5));
   }

   public static ImmutableMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6) {
      return RegularImmutableMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5), entryOf(k6, v6));
   }

   public static ImmutableMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7) {
      return RegularImmutableMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5), entryOf(k6, v6), entryOf(k7, v7));
   }

   public static ImmutableMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7, Object k8, Object v8) {
      return RegularImmutableMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5), entryOf(k6, v6), entryOf(k7, v7), entryOf(k8, v8));
   }

   public static ImmutableMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7, Object k8, Object v8, Object k9, Object v9) {
      return RegularImmutableMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5), entryOf(k6, v6), entryOf(k7, v7), entryOf(k8, v8), entryOf(k9, v9));
   }

   public static ImmutableMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7, Object k8, Object v8, Object k9, Object v9, Object k10, Object v10) {
      return RegularImmutableMap.fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5), entryOf(k6, v6), entryOf(k7, v7), entryOf(k8, v8), entryOf(k9, v9), entryOf(k10, v10));
   }

   @SafeVarargs
   public static ImmutableMap ofEntries(Map.Entry... entries) {
      return RegularImmutableMap.fromEntries(entries);
   }

   static Map.Entry entryOf(Object key, Object value) {
      return new ImmutableMapEntry(key, value);
   }

   public static Builder builder() {
      return new Builder();
   }

   public static Builder builderWithExpectedSize(int expectedSize) {
      CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
      return new Builder(expectedSize);
   }

   static void checkNoConflict(boolean safe, String conflictDescription, Object entry1, Object entry2) {
      if (!safe) {
         throw conflictException(conflictDescription, entry1, entry2);
      }
   }

   static IllegalArgumentException conflictException(String conflictDescription, Object entry1, Object entry2) {
      return new IllegalArgumentException("Multiple entries with same " + conflictDescription + ": " + entry1 + " and " + entry2);
   }

   public static ImmutableMap copyOf(Map map) {
      if (map instanceof ImmutableMap && !(map instanceof SortedMap)) {
         ImmutableMap<K, V> kvMap = (ImmutableMap)map;
         if (!kvMap.isPartialView()) {
            return kvMap;
         }
      } else if (map instanceof EnumMap) {
         ImmutableMap<K, V> kvMap = copyOfEnumMap((EnumMap)map);
         return kvMap;
      }

      return copyOf((Iterable)map.entrySet());
   }

   public static ImmutableMap copyOf(Iterable entries) {
      Map.Entry<K, V>[] entryArray = (Map.Entry[])Iterables.toArray(entries, (Object[])EMPTY_ENTRY_ARRAY);
      switch (entryArray.length) {
         case 0:
            return of();
         case 1:
            Map.Entry<K, V> onlyEntry = (Map.Entry)Objects.requireNonNull(entryArray[0]);
            return of(onlyEntry.getKey(), onlyEntry.getValue());
         default:
            return RegularImmutableMap.fromEntries(entryArray);
      }
   }

   private static ImmutableMap copyOfEnumMap(EnumMap original) {
      EnumMap<K, V> copy = new EnumMap(original);

      for(Map.Entry entry : copy.entrySet()) {
         CollectPreconditions.checkEntryNotNull(entry.getKey(), entry.getValue());
      }

      return ImmutableEnumMap.asImmutable(copy);
   }

   ImmutableMap() {
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Object put(Object k, Object v) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Object putIfAbsent(Object key, Object value) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final boolean replace(Object key, Object oldValue, Object newValue) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Object replace(Object key, Object value) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Object computeIfAbsent(Object key, Function mappingFunction) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Object computeIfPresent(Object key, BiFunction remappingFunction) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Object compute(Object key, BiFunction remappingFunction) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Object merge(Object key, Object value, BiFunction remappingFunction) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void putAll(Map map) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void replaceAll(BiFunction function) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Object remove(@CheckForNull Object o) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void clear() {
      throw new UnsupportedOperationException();
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public boolean containsKey(@CheckForNull Object key) {
      return this.get(key) != null;
   }

   public boolean containsValue(@CheckForNull Object value) {
      return this.values().contains(value);
   }

   @CheckForNull
   public abstract Object get(@CheckForNull Object key);

   @CheckForNull
   public final Object getOrDefault(@CheckForNull Object key, @CheckForNull Object defaultValue) {
      V result = (V)this.get(key);
      return result != null ? result : defaultValue;
   }

   public ImmutableSet entrySet() {
      ImmutableSet<Map.Entry<K, V>> result = this.entrySet;
      return result == null ? (this.entrySet = this.createEntrySet()) : result;
   }

   abstract ImmutableSet createEntrySet();

   public ImmutableSet keySet() {
      ImmutableSet<K> result = this.keySet;
      return result == null ? (this.keySet = this.createKeySet()) : result;
   }

   abstract ImmutableSet createKeySet();

   UnmodifiableIterator keyIterator() {
      final UnmodifiableIterator<Map.Entry<K, V>> entryIterator = this.entrySet().iterator();
      return new UnmodifiableIterator() {
         public boolean hasNext() {
            return entryIterator.hasNext();
         }

         public Object next() {
            return ((Map.Entry)entryIterator.next()).getKey();
         }
      };
   }

   Spliterator keySpliterator() {
      return CollectSpliterators.map(this.entrySet().spliterator(), Map.Entry::getKey);
   }

   public ImmutableCollection values() {
      ImmutableCollection<V> result = this.values;
      return result == null ? (this.values = this.createValues()) : result;
   }

   abstract ImmutableCollection createValues();

   public ImmutableSetMultimap asMultimap() {
      if (this.isEmpty()) {
         return ImmutableSetMultimap.of();
      } else {
         ImmutableSetMultimap<K, V> result = this.multimapView;
         return result == null ? (this.multimapView = new ImmutableSetMultimap(new MapViewOfValuesAsSingletonSets(), this.size(), (Comparator)null)) : result;
      }
   }

   public boolean equals(@CheckForNull Object object) {
      return Maps.equalsImpl(this, object);
   }

   abstract boolean isPartialView();

   public int hashCode() {
      return Sets.hashCodeImpl(this.entrySet());
   }

   boolean isHashCodeFast() {
      return false;
   }

   public String toString() {
      return Maps.toStringImpl(this);
   }

   @J2ktIncompatible
   Object writeReplace() {
      return new SerializedForm(this);
   }

   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   @DoNotMock
   public static class Builder {
      @CheckForNull
      Comparator valueComparator;
      @Nullable Map.Entry[] entries;
      int size;
      boolean entriesUsed;

      public Builder() {
         this(4);
      }

      Builder(int initialCapacity) {
         this.entries = new Map.Entry[initialCapacity];
         this.size = 0;
         this.entriesUsed = false;
      }

      private void ensureCapacity(int minCapacity) {
         if (minCapacity > this.entries.length) {
            this.entries = (Map.Entry[])Arrays.copyOf(this.entries, ImmutableCollection.Builder.expandedCapacity(this.entries.length, minCapacity));
            this.entriesUsed = false;
         }

      }

      @CanIgnoreReturnValue
      public Builder put(Object key, Object value) {
         this.ensureCapacity(this.size + 1);
         Map.Entry<K, V> entry = ImmutableMap.entryOf(key, value);
         this.entries[this.size++] = entry;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder put(Map.Entry entry) {
         return this.put(entry.getKey(), entry.getValue());
      }

      @CanIgnoreReturnValue
      public Builder putAll(Map map) {
         return this.putAll((Iterable)map.entrySet());
      }

      @CanIgnoreReturnValue
      public Builder putAll(Iterable entries) {
         if (entries instanceof Collection) {
            this.ensureCapacity(this.size + ((Collection)entries).size());
         }

         for(Map.Entry entry : entries) {
            this.put(entry);
         }

         return this;
      }

      @CanIgnoreReturnValue
      public Builder orderEntriesByValue(Comparator valueComparator) {
         Preconditions.checkState(this.valueComparator == null, "valueComparator was already set");
         this.valueComparator = (Comparator)Preconditions.checkNotNull(valueComparator, "valueComparator");
         return this;
      }

      @CanIgnoreReturnValue
      Builder combine(Builder other) {
         Preconditions.checkNotNull(other);
         this.ensureCapacity(this.size + other.size);
         System.arraycopy(other.entries, 0, this.entries, this.size, other.size);
         this.size += other.size;
         return this;
      }

      private ImmutableMap build(boolean throwIfDuplicateKeys) {
         switch (this.size) {
            case 0:
               return ImmutableMap.of();
            case 1:
               Map.Entry<K, V> onlyEntry = (Map.Entry)Objects.requireNonNull(this.entries[0]);
               return ImmutableMap.of(onlyEntry.getKey(), onlyEntry.getValue());
            default:
               int localSize = this.size;
               Map.Entry<K, V>[] localEntries;
               if (this.valueComparator == null) {
                  localEntries = this.entries;
               } else {
                  if (this.entriesUsed) {
                     this.entries = (Map.Entry[])Arrays.copyOf(this.entries, this.size);
                  }

                  Map.Entry<K, V>[] nonNullEntries = this.entries;
                  if (!throwIfDuplicateKeys) {
                     nonNullEntries = lastEntryForEachKey(nonNullEntries, this.size);
                     localSize = nonNullEntries.length;
                  }

                  Arrays.sort(nonNullEntries, 0, localSize, Ordering.from(this.valueComparator).onResultOf(Maps.valueFunction()));
                  localEntries = nonNullEntries;
               }

               this.entriesUsed = true;
               return RegularImmutableMap.fromEntryArray(localSize, localEntries, throwIfDuplicateKeys);
         }
      }

      public ImmutableMap build() {
         return this.buildOrThrow();
      }

      public ImmutableMap buildOrThrow() {
         return this.build(true);
      }

      public ImmutableMap buildKeepingLast() {
         return this.build(false);
      }

      @VisibleForTesting
      ImmutableMap buildJdkBacked() {
         Preconditions.checkState(this.valueComparator == null, "buildJdkBacked is only for testing; can't use valueComparator");
         switch (this.size) {
            case 0:
               return ImmutableMap.of();
            case 1:
               Map.Entry<K, V> onlyEntry = (Map.Entry)Objects.requireNonNull(this.entries[0]);
               return ImmutableMap.of(onlyEntry.getKey(), onlyEntry.getValue());
            default:
               this.entriesUsed = true;
               return JdkBackedImmutableMap.create(this.size, this.entries, true);
         }
      }

      private static Map.Entry[] lastEntryForEachKey(Map.Entry[] entries, int size) {
         Set<K> seen = new HashSet();
         BitSet dups = new BitSet();

         for(int i = size - 1; i >= 0; --i) {
            if (!seen.add(entries[i].getKey())) {
               dups.set(i);
            }
         }

         if (dups.isEmpty()) {
            return entries;
         } else {
            Map.Entry<K, V>[] newEntries = new Map.Entry[size - dups.cardinality()];
            int inI = 0;

            for(int outI = 0; inI < size; ++inI) {
               if (!dups.get(inI)) {
                  newEntries[outI++] = entries[inI];
               }
            }

            return newEntries;
         }
      }
   }

   abstract static class IteratorBasedImmutableMap extends ImmutableMap {
      abstract UnmodifiableIterator entryIterator();

      Spliterator entrySpliterator() {
         return Spliterators.spliterator(this.entryIterator(), (long)this.size(), 1297);
      }

      ImmutableSet createKeySet() {
         return new ImmutableMapKeySet(this);
      }

      ImmutableSet createEntrySet() {
         class EntrySetImpl extends ImmutableMapEntrySet {
            ImmutableMap map() {
               return IteratorBasedImmutableMap.this;
            }

            public UnmodifiableIterator iterator() {
               return IteratorBasedImmutableMap.this.entryIterator();
            }
         }

         return new EntrySetImpl();
      }

      ImmutableCollection createValues() {
         return new ImmutableMapValues(this);
      }
   }

   private final class MapViewOfValuesAsSingletonSets extends IteratorBasedImmutableMap {
      private MapViewOfValuesAsSingletonSets() {
      }

      public int size() {
         return ImmutableMap.this.size();
      }

      ImmutableSet createKeySet() {
         return ImmutableMap.this.keySet();
      }

      public boolean containsKey(@CheckForNull Object key) {
         return ImmutableMap.this.containsKey(key);
      }

      @CheckForNull
      public ImmutableSet get(@CheckForNull Object key) {
         V outerValue = (V)ImmutableMap.this.get(key);
         return outerValue == null ? null : ImmutableSet.of(outerValue);
      }

      boolean isPartialView() {
         return ImmutableMap.this.isPartialView();
      }

      public int hashCode() {
         return ImmutableMap.this.hashCode();
      }

      boolean isHashCodeFast() {
         return ImmutableMap.this.isHashCodeFast();
      }

      UnmodifiableIterator entryIterator() {
         final Iterator<Map.Entry<K, V>> backingIterator = ImmutableMap.this.entrySet().iterator();
         return new UnmodifiableIterator() {
            public boolean hasNext() {
               return backingIterator.hasNext();
            }

            public Map.Entry next() {
               final Map.Entry<K, V> backingEntry = (Map.Entry)backingIterator.next();
               return new AbstractMapEntry() {
                  public Object getKey() {
                     return backingEntry.getKey();
                  }

                  public ImmutableSet getValue() {
                     return ImmutableSet.of(backingEntry.getValue());
                  }
               };
            }
         };
      }
   }

   @J2ktIncompatible
   static class SerializedForm implements Serializable {
      private static final boolean USE_LEGACY_SERIALIZATION = true;
      private final Object keys;
      private final Object values;
      private static final long serialVersionUID = 0L;

      SerializedForm(ImmutableMap map) {
         Object[] keys = new Object[map.size()];
         Object[] values = new Object[map.size()];
         int i = 0;

         for(Map.Entry entry : map.entrySet()) {
            keys[i] = entry.getKey();
            values[i] = entry.getValue();
            ++i;
         }

         this.keys = keys;
         this.values = values;
      }

      final Object readResolve() {
         if (!(this.keys instanceof ImmutableSet)) {
            return this.legacyReadResolve();
         } else {
            ImmutableSet<K> keySet = (ImmutableSet)this.keys;
            ImmutableCollection<V> values = (ImmutableCollection)this.values;
            Builder<K, V> builder = this.makeBuilder(keySet.size());
            UnmodifiableIterator<K> keyIter = keySet.iterator();
            UnmodifiableIterator<V> valueIter = values.iterator();

            while(keyIter.hasNext()) {
               builder.put(keyIter.next(), valueIter.next());
            }

            return builder.buildOrThrow();
         }
      }

      final Object legacyReadResolve() {
         K[] keys = (K[])((Object[])this.keys);
         V[] values = (V[])((Object[])this.values);
         Builder<K, V> builder = this.makeBuilder(keys.length);

         for(int i = 0; i < keys.length; ++i) {
            builder.put(keys[i], values[i]);
         }

         return builder.buildOrThrow();
      }

      Builder makeBuilder(int size) {
         return new Builder(size);
      }
   }
}
