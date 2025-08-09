package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.SortedMap;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
public final class ImmutableSortedMap extends ImmutableMap implements NavigableMap {
   private static final Comparator NATURAL_ORDER = Ordering.natural();
   private static final ImmutableSortedMap NATURAL_EMPTY_MAP = new ImmutableSortedMap(ImmutableSortedSet.emptySet(Ordering.natural()), ImmutableList.of());
   private final transient RegularImmutableSortedSet keySet;
   private final transient ImmutableList valueList;
   @CheckForNull
   private transient ImmutableSortedMap descendingMap;
   private static final long serialVersionUID = 0L;

   public static Collector toImmutableSortedMap(Comparator comparator, Function keyFunction, Function valueFunction) {
      return CollectCollectors.toImmutableSortedMap(comparator, keyFunction, valueFunction);
   }

   public static Collector toImmutableSortedMap(Comparator comparator, Function keyFunction, Function valueFunction, BinaryOperator mergeFunction) {
      return CollectCollectors.toImmutableSortedMap(comparator, keyFunction, valueFunction, mergeFunction);
   }

   static ImmutableSortedMap emptyMap(Comparator comparator) {
      return Ordering.natural().equals(comparator) ? of() : new ImmutableSortedMap(ImmutableSortedSet.emptySet(comparator), ImmutableList.of());
   }

   public static ImmutableSortedMap of() {
      return NATURAL_EMPTY_MAP;
   }

   public static ImmutableSortedMap of(Comparable k1, Object v1) {
      return of(Ordering.natural(), k1, v1);
   }

   private static ImmutableSortedMap of(Comparator comparator, Object k1, Object v1) {
      return new ImmutableSortedMap(new RegularImmutableSortedSet(ImmutableList.of(k1), (Comparator)Preconditions.checkNotNull(comparator)), ImmutableList.of(v1));
   }

   public static ImmutableSortedMap of(Comparable k1, Object v1, Comparable k2, Object v2) {
      return fromEntries(entryOf(k1, v1), entryOf(k2, v2));
   }

   public static ImmutableSortedMap of(Comparable k1, Object v1, Comparable k2, Object v2, Comparable k3, Object v3) {
      return fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3));
   }

   public static ImmutableSortedMap of(Comparable k1, Object v1, Comparable k2, Object v2, Comparable k3, Object v3, Comparable k4, Object v4) {
      return fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4));
   }

   public static ImmutableSortedMap of(Comparable k1, Object v1, Comparable k2, Object v2, Comparable k3, Object v3, Comparable k4, Object v4, Comparable k5, Object v5) {
      return fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5));
   }

   public static ImmutableSortedMap of(Comparable k1, Object v1, Comparable k2, Object v2, Comparable k3, Object v3, Comparable k4, Object v4, Comparable k5, Object v5, Comparable k6, Object v6) {
      return fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5), entryOf(k6, v6));
   }

   public static ImmutableSortedMap of(Comparable k1, Object v1, Comparable k2, Object v2, Comparable k3, Object v3, Comparable k4, Object v4, Comparable k5, Object v5, Comparable k6, Object v6, Comparable k7, Object v7) {
      return fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5), entryOf(k6, v6), entryOf(k7, v7));
   }

   public static ImmutableSortedMap of(Comparable k1, Object v1, Comparable k2, Object v2, Comparable k3, Object v3, Comparable k4, Object v4, Comparable k5, Object v5, Comparable k6, Object v6, Comparable k7, Object v7, Comparable k8, Object v8) {
      return fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5), entryOf(k6, v6), entryOf(k7, v7), entryOf(k8, v8));
   }

   public static ImmutableSortedMap of(Comparable k1, Object v1, Comparable k2, Object v2, Comparable k3, Object v3, Comparable k4, Object v4, Comparable k5, Object v5, Comparable k6, Object v6, Comparable k7, Object v7, Comparable k8, Object v8, Comparable k9, Object v9) {
      return fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5), entryOf(k6, v6), entryOf(k7, v7), entryOf(k8, v8), entryOf(k9, v9));
   }

   public static ImmutableSortedMap of(Comparable k1, Object v1, Comparable k2, Object v2, Comparable k3, Object v3, Comparable k4, Object v4, Comparable k5, Object v5, Comparable k6, Object v6, Comparable k7, Object v7, Comparable k8, Object v8, Comparable k9, Object v9, Comparable k10, Object v10) {
      return fromEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5), entryOf(k6, v6), entryOf(k7, v7), entryOf(k8, v8), entryOf(k9, v9), entryOf(k10, v10));
   }

   public static ImmutableSortedMap copyOf(Map map) {
      Ordering<K> naturalOrder = (Ordering)NATURAL_ORDER;
      return copyOfInternal(map, naturalOrder);
   }

   public static ImmutableSortedMap copyOf(Map map, Comparator comparator) {
      return copyOfInternal(map, (Comparator)Preconditions.checkNotNull(comparator));
   }

   public static ImmutableSortedMap copyOf(Iterable entries) {
      Ordering<K> naturalOrder = (Ordering)NATURAL_ORDER;
      return copyOf((Iterable)entries, naturalOrder);
   }

   public static ImmutableSortedMap copyOf(Iterable entries, Comparator comparator) {
      return fromEntries((Comparator)Preconditions.checkNotNull(comparator), false, entries);
   }

   public static ImmutableSortedMap copyOfSorted(SortedMap map) {
      Comparator<? super K> comparator = map.comparator();
      if (comparator == null) {
         comparator = NATURAL_ORDER;
      }

      if (map instanceof ImmutableSortedMap) {
         ImmutableSortedMap<K, V> kvMap = (ImmutableSortedMap)map;
         if (!kvMap.isPartialView()) {
            return kvMap;
         }
      }

      return fromEntries(comparator, true, map.entrySet());
   }

   private static ImmutableSortedMap copyOfInternal(Map map, Comparator comparator) {
      boolean sameComparator = false;
      if (map instanceof SortedMap) {
         SortedMap<?, ?> sortedMap = (SortedMap)map;
         Comparator<?> comparator2 = sortedMap.comparator();
         sameComparator = comparator2 == null ? comparator == NATURAL_ORDER : comparator.equals(comparator2);
      }

      if (sameComparator && map instanceof ImmutableSortedMap) {
         ImmutableSortedMap<K, V> kvMap = (ImmutableSortedMap)map;
         if (!kvMap.isPartialView()) {
            return kvMap;
         }
      }

      return fromEntries(comparator, sameComparator, map.entrySet());
   }

   private static ImmutableSortedMap fromEntries(Map.Entry... entries) {
      return fromEntries(Ordering.natural(), false, entries, entries.length);
   }

   private static ImmutableSortedMap fromEntries(Comparator comparator, boolean sameComparator, Iterable entries) {
      Map.Entry<K, V>[] entryArray = (Map.Entry[])Iterables.toArray(entries, (Object[])EMPTY_ENTRY_ARRAY);
      return fromEntries(comparator, sameComparator, entryArray, entryArray.length);
   }

   private static ImmutableSortedMap fromEntries(final Comparator comparator, boolean sameComparator, @Nullable Map.Entry[] entryArray, int size) {
      switch (size) {
         case 0:
            return emptyMap(comparator);
         case 1:
            Map.Entry<K, V> onlyEntry = (Map.Entry)Objects.requireNonNull(entryArray[0]);
            return of(comparator, onlyEntry.getKey(), onlyEntry.getValue());
         default:
            Object[] keys = new Object[size];
            Object[] values = new Object[size];
            if (sameComparator) {
               for(int i = 0; i < size; ++i) {
                  Map.Entry<K, V> entry = (Map.Entry)Objects.requireNonNull(entryArray[i]);
                  Object key = entry.getKey();
                  Object value = entry.getValue();
                  CollectPreconditions.checkEntryNotNull(key, value);
                  keys[i] = key;
                  values[i] = value;
               }
            } else {
               Arrays.sort(entryArray, 0, size, (e1, e2) -> {
                  Objects.requireNonNull(e1);
                  Objects.requireNonNull(e2);
                  return comparator.compare(e1.getKey(), e2.getKey());
               });
               Map.Entry<K, V> firstEntry = (Map.Entry)Objects.requireNonNull(entryArray[0]);
               K prevKey = (K)firstEntry.getKey();
               keys[0] = prevKey;
               values[0] = firstEntry.getValue();
               CollectPreconditions.checkEntryNotNull(keys[0], values[0]);

               for(int i = 1; i < size; ++i) {
                  Map.Entry<K, V> prevEntry = (Map.Entry)Objects.requireNonNull(entryArray[i - 1]);
                  Map.Entry<K, V> entry = (Map.Entry)Objects.requireNonNull(entryArray[i]);
                  K key = (K)entry.getKey();
                  V value = (V)entry.getValue();
                  CollectPreconditions.checkEntryNotNull(key, value);
                  keys[i] = key;
                  values[i] = value;
                  checkNoConflict(comparator.compare(prevKey, key) != 0, "key", prevEntry, entry);
                  prevKey = key;
               }
            }

            return new ImmutableSortedMap(new RegularImmutableSortedSet(new RegularImmutableList(keys), comparator), new RegularImmutableList(values));
      }
   }

   public static Builder naturalOrder() {
      return new Builder(Ordering.natural());
   }

   public static Builder orderedBy(Comparator comparator) {
      return new Builder(comparator);
   }

   public static Builder reverseOrder() {
      return new Builder(Ordering.natural().reverse());
   }

   ImmutableSortedMap(RegularImmutableSortedSet keySet, ImmutableList valueList) {
      this(keySet, valueList, (ImmutableSortedMap)null);
   }

   ImmutableSortedMap(RegularImmutableSortedSet keySet, ImmutableList valueList, @CheckForNull ImmutableSortedMap descendingMap) {
      this.keySet = keySet;
      this.valueList = valueList;
      this.descendingMap = descendingMap;
   }

   public int size() {
      return this.valueList.size();
   }

   public void forEach(BiConsumer action) {
      Preconditions.checkNotNull(action);
      ImmutableList<K> keyList = this.keySet.asList();

      for(int i = 0; i < this.size(); ++i) {
         action.accept(keyList.get(i), this.valueList.get(i));
      }

   }

   @CheckForNull
   public Object get(@CheckForNull Object key) {
      int index = this.keySet.indexOf(key);
      return index == -1 ? null : this.valueList.get(index);
   }

   boolean isPartialView() {
      return this.keySet.isPartialView() || this.valueList.isPartialView();
   }

   public ImmutableSet entrySet() {
      return super.entrySet();
   }

   ImmutableSet createEntrySet() {
      class EntrySet extends ImmutableMapEntrySet {
         public UnmodifiableIterator iterator() {
            return this.asList().iterator();
         }

         public Spliterator spliterator() {
            return this.asList().spliterator();
         }

         public void forEach(Consumer action) {
            this.asList().forEach(action);
         }

         ImmutableList createAsList() {
            return new ImmutableAsList() {
               public Map.Entry get(int index) {
                  return new AbstractMap.SimpleImmutableEntry(ImmutableSortedMap.this.keySet.asList().get(index), ImmutableSortedMap.this.valueList.get(index));
               }

               public Spliterator spliterator() {
                  return CollectSpliterators.indexed(this.size(), 1297, this::get);
               }

               ImmutableCollection delegateCollection() {
                  return EntrySet.this;
               }

               @J2ktIncompatible
               @GwtIncompatible
               Object writeReplace() {
                  return super.writeReplace();
               }
            };
         }

         ImmutableMap map() {
            return ImmutableSortedMap.this;
         }

         @J2ktIncompatible
         @GwtIncompatible
         Object writeReplace() {
            return super.writeReplace();
         }
      }

      return (ImmutableSet)(this.isEmpty() ? ImmutableSet.of() : new EntrySet());
   }

   public ImmutableSortedSet keySet() {
      return this.keySet;
   }

   ImmutableSet createKeySet() {
      throw new AssertionError("should never be called");
   }

   public ImmutableCollection values() {
      return this.valueList;
   }

   ImmutableCollection createValues() {
      throw new AssertionError("should never be called");
   }

   public Comparator comparator() {
      return this.keySet().comparator();
   }

   public Object firstKey() {
      return this.keySet().first();
   }

   public Object lastKey() {
      return this.keySet().last();
   }

   private ImmutableSortedMap getSubMap(int fromIndex, int toIndex) {
      if (fromIndex == 0 && toIndex == this.size()) {
         return this;
      } else {
         return fromIndex == toIndex ? emptyMap(this.comparator()) : new ImmutableSortedMap(this.keySet.getSubSet(fromIndex, toIndex), this.valueList.subList(fromIndex, toIndex));
      }
   }

   public ImmutableSortedMap headMap(Object toKey) {
      return this.headMap(toKey, false);
   }

   public ImmutableSortedMap headMap(Object toKey, boolean inclusive) {
      return this.getSubMap(0, this.keySet.headIndex(Preconditions.checkNotNull(toKey), inclusive));
   }

   public ImmutableSortedMap subMap(Object fromKey, Object toKey) {
      return this.subMap(fromKey, true, toKey, false);
   }

   public ImmutableSortedMap subMap(Object fromKey, boolean fromInclusive, Object toKey, boolean toInclusive) {
      Preconditions.checkNotNull(fromKey);
      Preconditions.checkNotNull(toKey);
      Preconditions.checkArgument(this.comparator().compare(fromKey, toKey) <= 0, "expected fromKey <= toKey but %s > %s", fromKey, toKey);
      return this.headMap(toKey, toInclusive).tailMap(fromKey, fromInclusive);
   }

   public ImmutableSortedMap tailMap(Object fromKey) {
      return this.tailMap(fromKey, true);
   }

   public ImmutableSortedMap tailMap(Object fromKey, boolean inclusive) {
      return this.getSubMap(this.keySet.tailIndex(Preconditions.checkNotNull(fromKey), inclusive), this.size());
   }

   @CheckForNull
   public Map.Entry lowerEntry(Object key) {
      return this.headMap(key, false).lastEntry();
   }

   @CheckForNull
   public Object lowerKey(Object key) {
      return Maps.keyOrNull(this.lowerEntry(key));
   }

   @CheckForNull
   public Map.Entry floorEntry(Object key) {
      return this.headMap(key, true).lastEntry();
   }

   @CheckForNull
   public Object floorKey(Object key) {
      return Maps.keyOrNull(this.floorEntry(key));
   }

   @CheckForNull
   public Map.Entry ceilingEntry(Object key) {
      return this.tailMap(key, true).firstEntry();
   }

   @CheckForNull
   public Object ceilingKey(Object key) {
      return Maps.keyOrNull(this.ceilingEntry(key));
   }

   @CheckForNull
   public Map.Entry higherEntry(Object key) {
      return this.tailMap(key, false).firstEntry();
   }

   @CheckForNull
   public Object higherKey(Object key) {
      return Maps.keyOrNull(this.higherEntry(key));
   }

   @CheckForNull
   public Map.Entry firstEntry() {
      return this.isEmpty() ? null : (Map.Entry)this.entrySet().asList().get(0);
   }

   @CheckForNull
   public Map.Entry lastEntry() {
      return this.isEmpty() ? null : (Map.Entry)this.entrySet().asList().get(this.size() - 1);
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Map.Entry pollFirstEntry() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @CheckForNull
   @CanIgnoreReturnValue
   @DoNotCall("Always throws UnsupportedOperationException")
   public final Map.Entry pollLastEntry() {
      throw new UnsupportedOperationException();
   }

   public ImmutableSortedMap descendingMap() {
      ImmutableSortedMap<K, V> result = this.descendingMap;
      if (result == null) {
         return this.isEmpty() ? emptyMap(Ordering.from(this.comparator()).reverse()) : new ImmutableSortedMap((RegularImmutableSortedSet)this.keySet.descendingSet(), this.valueList.reverse(), this);
      } else {
         return result;
      }
   }

   public ImmutableSortedSet navigableKeySet() {
      return this.keySet;
   }

   public ImmutableSortedSet descendingKeySet() {
      return this.keySet.descendingSet();
   }

   @J2ktIncompatible
   Object writeReplace() {
      return new SerializedForm(this);
   }

   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Use toImmutableSortedMap")
   public static Collector toImmutableMap(Function keyFunction, Function valueFunction) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Use toImmutableSortedMap")
   public static Collector toImmutableMap(Function keyFunction, Function valueFunction, BinaryOperator mergeFunction) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Use naturalOrder")
   public static Builder builder() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Use naturalOrder (which does not accept an expected size)")
   public static Builder builderWithExpectedSize(int expectedSize) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass a key of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7, Object k8, Object v8) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7, Object k8, Object v8, Object k9, Object v9) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Pass keys of type Comparable")
   public static ImmutableSortedMap of(Object k1, Object v1, Object k2, Object v2, Object k3, Object v3, Object k4, Object v4, Object k5, Object v5, Object k6, Object v6, Object k7, Object v7, Object k8, Object v8, Object k9, Object v9, Object k10, Object v10) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @SafeVarargs
   @DoNotCall("ImmutableSortedMap.ofEntries not currently available; use ImmutableSortedMap.copyOf")
   public static ImmutableSortedMap ofEntries(Map.Entry... entries) {
      throw new UnsupportedOperationException();
   }

   public static class Builder extends ImmutableMap.Builder {
      private final Comparator comparator;

      public Builder(Comparator comparator) {
         this.comparator = (Comparator)Preconditions.checkNotNull(comparator);
      }

      @CanIgnoreReturnValue
      public Builder put(Object key, Object value) {
         super.put(key, value);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder put(Map.Entry entry) {
         super.put(entry);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder putAll(Map map) {
         super.putAll(map);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder putAll(Iterable entries) {
         super.putAll(entries);
         return this;
      }

      /** @deprecated */
      @Deprecated
      @CanIgnoreReturnValue
      @DoNotCall("Always throws UnsupportedOperationException")
      public final Builder orderEntriesByValue(Comparator valueComparator) {
         throw new UnsupportedOperationException("Not available on ImmutableSortedMap.Builder");
      }

      Builder combine(ImmutableMap.Builder other) {
         super.combine(other);
         return this;
      }

      public ImmutableSortedMap build() {
         return this.buildOrThrow();
      }

      public ImmutableSortedMap buildOrThrow() {
         switch (this.size) {
            case 0:
               return ImmutableSortedMap.emptyMap(this.comparator);
            case 1:
               Map.Entry<K, V> onlyEntry = (Map.Entry)Objects.requireNonNull(this.entries[0]);
               return ImmutableSortedMap.of(this.comparator, onlyEntry.getKey(), onlyEntry.getValue());
            default:
               return ImmutableSortedMap.fromEntries(this.comparator, false, this.entries, this.size);
         }
      }

      /** @deprecated */
      @Deprecated
      @DoNotCall
      public final ImmutableSortedMap buildKeepingLast() {
         throw new UnsupportedOperationException("ImmutableSortedMap.Builder does not yet implement buildKeepingLast()");
      }
   }

   @J2ktIncompatible
   private static class SerializedForm extends ImmutableMap.SerializedForm {
      private final Comparator comparator;
      private static final long serialVersionUID = 0L;

      SerializedForm(ImmutableSortedMap sortedMap) {
         super(sortedMap);
         this.comparator = sortedMap.comparator();
      }

      Builder makeBuilder(int size) {
         return new Builder(this.comparator);
      }
   }
}
