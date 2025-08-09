package org.apache.curator.shaded.com.google.common.collect;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Converter;
import org.apache.curator.shaded.com.google.common.base.Equivalence;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Predicate;
import org.apache.curator.shaded.com.google.common.base.Predicates;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.j2objc.annotations.RetainedWith;
import org.apache.curator.shaded.com.google.j2objc.annotations.Weak;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class Maps {
   private Maps() {
   }

   static org.apache.curator.shaded.com.google.common.base.Function keyFunction() {
      return Maps.EntryFunction.KEY;
   }

   static org.apache.curator.shaded.com.google.common.base.Function valueFunction() {
      return Maps.EntryFunction.VALUE;
   }

   static Iterator keyIterator(Iterator entryIterator) {
      return new TransformedIterator(entryIterator) {
         @ParametricNullness
         Object transform(Map.Entry entry) {
            return entry.getKey();
         }
      };
   }

   static Iterator valueIterator(Iterator entryIterator) {
      return new TransformedIterator(entryIterator) {
         @ParametricNullness
         Object transform(Map.Entry entry) {
            return entry.getValue();
         }
      };
   }

   @GwtCompatible(
      serializable = true
   )
   @J2ktIncompatible
   public static ImmutableMap immutableEnumMap(Map map) {
      if (map instanceof ImmutableEnumMap) {
         ImmutableEnumMap<K, V> result = (ImmutableEnumMap)map;
         return result;
      } else {
         Iterator<? extends Map.Entry<K, ? extends V>> entryItr = map.entrySet().iterator();
         if (!entryItr.hasNext()) {
            return ImmutableMap.of();
         } else {
            Map.Entry<K, ? extends V> entry1 = (Map.Entry)entryItr.next();
            K key1 = (K)((Enum)entry1.getKey());
            V value1 = (V)entry1.getValue();
            CollectPreconditions.checkEntryNotNull(key1, value1);
            EnumMap<K, V> enumMap = new EnumMap(Collections.singletonMap(key1, value1));

            while(entryItr.hasNext()) {
               Map.Entry<K, ? extends V> entry = (Map.Entry)entryItr.next();
               K key = (K)((Enum)entry.getKey());
               V value = (V)entry.getValue();
               CollectPreconditions.checkEntryNotNull(key, value);
               enumMap.put(key, value);
            }

            return ImmutableEnumMap.asImmutable(enumMap);
         }
      }
   }

   @J2ktIncompatible
   public static Collector toImmutableEnumMap(Function keyFunction, Function valueFunction) {
      return CollectCollectors.toImmutableEnumMap(keyFunction, valueFunction);
   }

   @J2ktIncompatible
   public static Collector toImmutableEnumMap(Function keyFunction, Function valueFunction, BinaryOperator mergeFunction) {
      return CollectCollectors.toImmutableEnumMap(keyFunction, valueFunction, mergeFunction);
   }

   public static HashMap newHashMap() {
      return new HashMap();
   }

   public static HashMap newHashMap(Map map) {
      return new HashMap(map);
   }

   public static HashMap newHashMapWithExpectedSize(int expectedSize) {
      return new HashMap(capacity(expectedSize));
   }

   static int capacity(int expectedSize) {
      if (expectedSize < 3) {
         CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
         return expectedSize + 1;
      } else {
         return expectedSize < 1073741824 ? (int)Math.ceil((double)expectedSize / (double)0.75F) : Integer.MAX_VALUE;
      }
   }

   public static LinkedHashMap newLinkedHashMap() {
      return new LinkedHashMap();
   }

   public static LinkedHashMap newLinkedHashMap(Map map) {
      return new LinkedHashMap(map);
   }

   public static LinkedHashMap newLinkedHashMapWithExpectedSize(int expectedSize) {
      return new LinkedHashMap(capacity(expectedSize));
   }

   public static ConcurrentMap newConcurrentMap() {
      return new ConcurrentHashMap();
   }

   public static TreeMap newTreeMap() {
      return new TreeMap();
   }

   public static TreeMap newTreeMap(SortedMap map) {
      return new TreeMap(map);
   }

   public static TreeMap newTreeMap(@CheckForNull Comparator comparator) {
      return new TreeMap(comparator);
   }

   public static EnumMap newEnumMap(Class type) {
      return new EnumMap((Class)Preconditions.checkNotNull(type));
   }

   public static EnumMap newEnumMap(Map map) {
      return new EnumMap(map);
   }

   public static IdentityHashMap newIdentityHashMap() {
      return new IdentityHashMap();
   }

   public static MapDifference difference(Map left, Map right) {
      if (left instanceof SortedMap) {
         SortedMap<K, ? extends V> sortedLeft = (SortedMap)left;
         return difference(sortedLeft, right);
      } else {
         return difference(left, right, Equivalence.equals());
      }
   }

   public static MapDifference difference(Map left, Map right, Equivalence valueEquivalence) {
      Preconditions.checkNotNull(valueEquivalence);
      Map<K, V> onlyOnLeft = newLinkedHashMap();
      Map<K, V> onlyOnRight = new LinkedHashMap(right);
      Map<K, V> onBoth = newLinkedHashMap();
      Map<K, MapDifference.ValueDifference<V>> differences = newLinkedHashMap();
      doDifference(left, right, valueEquivalence, onlyOnLeft, onlyOnRight, onBoth, differences);
      return new MapDifferenceImpl(onlyOnLeft, onlyOnRight, onBoth, differences);
   }

   public static SortedMapDifference difference(SortedMap left, Map right) {
      Preconditions.checkNotNull(left);
      Preconditions.checkNotNull(right);
      Comparator<? super K> comparator = orNaturalOrder(left.comparator());
      SortedMap<K, V> onlyOnLeft = newTreeMap(comparator);
      SortedMap<K, V> onlyOnRight = newTreeMap(comparator);
      onlyOnRight.putAll(right);
      SortedMap<K, V> onBoth = newTreeMap(comparator);
      SortedMap<K, MapDifference.ValueDifference<V>> differences = newTreeMap(comparator);
      doDifference(left, right, Equivalence.equals(), onlyOnLeft, onlyOnRight, onBoth, differences);
      return new SortedMapDifferenceImpl(onlyOnLeft, onlyOnRight, onBoth, differences);
   }

   private static void doDifference(Map left, Map right, Equivalence valueEquivalence, Map onlyOnLeft, Map onlyOnRight, Map onBoth, Map differences) {
      for(Map.Entry entry : left.entrySet()) {
         K leftKey = (K)entry.getKey();
         V leftValue = (V)entry.getValue();
         if (right.containsKey(leftKey)) {
            V rightValue = (V)NullnessCasts.uncheckedCastNullableTToT(onlyOnRight.remove(leftKey));
            if (valueEquivalence.equivalent(leftValue, rightValue)) {
               onBoth.put(leftKey, leftValue);
            } else {
               differences.put(leftKey, Maps.ValueDifferenceImpl.create(leftValue, rightValue));
            }
         } else {
            onlyOnLeft.put(leftKey, leftValue);
         }
      }

   }

   private static Map unmodifiableMap(Map map) {
      return (Map)(map instanceof SortedMap ? Collections.unmodifiableSortedMap((SortedMap)map) : Collections.unmodifiableMap(map));
   }

   static Comparator orNaturalOrder(@CheckForNull Comparator comparator) {
      return (Comparator)(comparator != null ? comparator : Ordering.natural());
   }

   public static Map asMap(Set set, org.apache.curator.shaded.com.google.common.base.Function function) {
      return new AsMapView(set, function);
   }

   public static SortedMap asMap(SortedSet set, org.apache.curator.shaded.com.google.common.base.Function function) {
      return new SortedAsMapView(set, function);
   }

   @GwtIncompatible
   public static NavigableMap asMap(NavigableSet set, org.apache.curator.shaded.com.google.common.base.Function function) {
      return new NavigableAsMapView(set, function);
   }

   static Iterator asMapEntryIterator(Set set, final org.apache.curator.shaded.com.google.common.base.Function function) {
      return new TransformedIterator(set.iterator()) {
         Map.Entry transform(@ParametricNullness final Object key) {
            return Maps.immutableEntry(key, function.apply(key));
         }
      };
   }

   private static Set removeOnlySet(final Set set) {
      return new ForwardingSet() {
         protected Set delegate() {
            return set;
         }

         public boolean add(@ParametricNullness Object element) {
            throw new UnsupportedOperationException();
         }

         public boolean addAll(Collection es) {
            throw new UnsupportedOperationException();
         }
      };
   }

   private static SortedSet removeOnlySortedSet(final SortedSet set) {
      return new ForwardingSortedSet() {
         protected SortedSet delegate() {
            return set;
         }

         public boolean add(@ParametricNullness Object element) {
            throw new UnsupportedOperationException();
         }

         public boolean addAll(Collection es) {
            throw new UnsupportedOperationException();
         }

         public SortedSet headSet(@ParametricNullness Object toElement) {
            return Maps.removeOnlySortedSet(super.headSet(toElement));
         }

         public SortedSet subSet(@ParametricNullness Object fromElement, @ParametricNullness Object toElement) {
            return Maps.removeOnlySortedSet(super.subSet(fromElement, toElement));
         }

         public SortedSet tailSet(@ParametricNullness Object fromElement) {
            return Maps.removeOnlySortedSet(super.tailSet(fromElement));
         }
      };
   }

   @GwtIncompatible
   private static NavigableSet removeOnlyNavigableSet(final NavigableSet set) {
      return new ForwardingNavigableSet() {
         protected NavigableSet delegate() {
            return set;
         }

         public boolean add(@ParametricNullness Object element) {
            throw new UnsupportedOperationException();
         }

         public boolean addAll(Collection es) {
            throw new UnsupportedOperationException();
         }

         public SortedSet headSet(@ParametricNullness Object toElement) {
            return Maps.removeOnlySortedSet(super.headSet(toElement));
         }

         public NavigableSet headSet(@ParametricNullness Object toElement, boolean inclusive) {
            return Maps.removeOnlyNavigableSet(super.headSet(toElement, inclusive));
         }

         public SortedSet subSet(@ParametricNullness Object fromElement, @ParametricNullness Object toElement) {
            return Maps.removeOnlySortedSet(super.subSet(fromElement, toElement));
         }

         public NavigableSet subSet(@ParametricNullness Object fromElement, boolean fromInclusive, @ParametricNullness Object toElement, boolean toInclusive) {
            return Maps.removeOnlyNavigableSet(super.subSet(fromElement, fromInclusive, toElement, toInclusive));
         }

         public SortedSet tailSet(@ParametricNullness Object fromElement) {
            return Maps.removeOnlySortedSet(super.tailSet(fromElement));
         }

         public NavigableSet tailSet(@ParametricNullness Object fromElement, boolean inclusive) {
            return Maps.removeOnlyNavigableSet(super.tailSet(fromElement, inclusive));
         }

         public NavigableSet descendingSet() {
            return Maps.removeOnlyNavigableSet(super.descendingSet());
         }
      };
   }

   public static ImmutableMap toMap(Iterable keys, org.apache.curator.shaded.com.google.common.base.Function valueFunction) {
      return toMap(keys.iterator(), valueFunction);
   }

   public static ImmutableMap toMap(Iterator keys, org.apache.curator.shaded.com.google.common.base.Function valueFunction) {
      Preconditions.checkNotNull(valueFunction);
      ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();

      while(keys.hasNext()) {
         K key = (K)keys.next();
         builder.put(key, valueFunction.apply(key));
      }

      return builder.buildKeepingLast();
   }

   @CanIgnoreReturnValue
   public static ImmutableMap uniqueIndex(Iterable values, org.apache.curator.shaded.com.google.common.base.Function keyFunction) {
      return values instanceof Collection ? uniqueIndex(values.iterator(), keyFunction, ImmutableMap.builderWithExpectedSize(((Collection)values).size())) : uniqueIndex(values.iterator(), keyFunction);
   }

   @CanIgnoreReturnValue
   public static ImmutableMap uniqueIndex(Iterator values, org.apache.curator.shaded.com.google.common.base.Function keyFunction) {
      return uniqueIndex(values, keyFunction, ImmutableMap.builder());
   }

   private static ImmutableMap uniqueIndex(Iterator values, org.apache.curator.shaded.com.google.common.base.Function keyFunction, ImmutableMap.Builder builder) {
      Preconditions.checkNotNull(keyFunction);

      while(values.hasNext()) {
         V value = (V)values.next();
         builder.put(keyFunction.apply(value), value);
      }

      try {
         return builder.buildOrThrow();
      } catch (IllegalArgumentException duplicateKeys) {
         throw new IllegalArgumentException(duplicateKeys.getMessage() + ". To index multiple values under a key, use Multimaps.index.");
      }
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static ImmutableMap fromProperties(Properties properties) {
      ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
      Enumeration<?> e = properties.propertyNames();

      while(e.hasMoreElements()) {
         String key = (String)Objects.requireNonNull(e.nextElement());
         builder.put(key, (String)Objects.requireNonNull(properties.getProperty(key)));
      }

      return builder.buildOrThrow();
   }

   @GwtCompatible(
      serializable = true
   )
   public static Map.Entry immutableEntry(@ParametricNullness Object key, @ParametricNullness Object value) {
      return new ImmutableEntry(key, value);
   }

   static Set unmodifiableEntrySet(Set entrySet) {
      return new UnmodifiableEntrySet(Collections.unmodifiableSet(entrySet));
   }

   static Map.Entry unmodifiableEntry(final Map.Entry entry) {
      Preconditions.checkNotNull(entry);
      return new AbstractMapEntry() {
         @ParametricNullness
         public Object getKey() {
            return entry.getKey();
         }

         @ParametricNullness
         public Object getValue() {
            return entry.getValue();
         }
      };
   }

   static UnmodifiableIterator unmodifiableEntryIterator(final Iterator entryIterator) {
      return new UnmodifiableIterator() {
         public boolean hasNext() {
            return entryIterator.hasNext();
         }

         public Map.Entry next() {
            return Maps.unmodifiableEntry((Map.Entry)entryIterator.next());
         }
      };
   }

   public static Converter asConverter(final BiMap bimap) {
      return new BiMapConverter(bimap);
   }

   public static BiMap synchronizedBiMap(BiMap bimap) {
      return Synchronized.biMap(bimap, (Object)null);
   }

   public static BiMap unmodifiableBiMap(BiMap bimap) {
      return new UnmodifiableBiMap(bimap, (BiMap)null);
   }

   public static Map transformValues(Map fromMap, org.apache.curator.shaded.com.google.common.base.Function function) {
      return transformEntries(fromMap, asEntryTransformer(function));
   }

   public static SortedMap transformValues(SortedMap fromMap, org.apache.curator.shaded.com.google.common.base.Function function) {
      return transformEntries(fromMap, asEntryTransformer(function));
   }

   @GwtIncompatible
   public static NavigableMap transformValues(NavigableMap fromMap, org.apache.curator.shaded.com.google.common.base.Function function) {
      return transformEntries(fromMap, asEntryTransformer(function));
   }

   public static Map transformEntries(Map fromMap, EntryTransformer transformer) {
      return new TransformedEntriesMap(fromMap, transformer);
   }

   public static SortedMap transformEntries(SortedMap fromMap, EntryTransformer transformer) {
      return new TransformedEntriesSortedMap(fromMap, transformer);
   }

   @GwtIncompatible
   public static NavigableMap transformEntries(NavigableMap fromMap, EntryTransformer transformer) {
      return new TransformedEntriesNavigableMap(fromMap, transformer);
   }

   static EntryTransformer asEntryTransformer(final org.apache.curator.shaded.com.google.common.base.Function function) {
      Preconditions.checkNotNull(function);
      return new EntryTransformer() {
         @ParametricNullness
         public Object transformEntry(@ParametricNullness Object key, @ParametricNullness Object value) {
            return function.apply(value);
         }
      };
   }

   static org.apache.curator.shaded.com.google.common.base.Function asValueToValueFunction(final EntryTransformer transformer, @ParametricNullness final Object key) {
      Preconditions.checkNotNull(transformer);
      return new org.apache.curator.shaded.com.google.common.base.Function() {
         @ParametricNullness
         public Object apply(@ParametricNullness Object v1) {
            return transformer.transformEntry(key, v1);
         }
      };
   }

   static org.apache.curator.shaded.com.google.common.base.Function asEntryToValueFunction(final EntryTransformer transformer) {
      Preconditions.checkNotNull(transformer);
      return new org.apache.curator.shaded.com.google.common.base.Function() {
         @ParametricNullness
         public Object apply(Map.Entry entry) {
            return transformer.transformEntry(entry.getKey(), entry.getValue());
         }
      };
   }

   static Map.Entry transformEntry(final EntryTransformer transformer, final Map.Entry entry) {
      Preconditions.checkNotNull(transformer);
      Preconditions.checkNotNull(entry);
      return new AbstractMapEntry() {
         @ParametricNullness
         public Object getKey() {
            return entry.getKey();
         }

         @ParametricNullness
         public Object getValue() {
            return transformer.transformEntry(entry.getKey(), entry.getValue());
         }
      };
   }

   static org.apache.curator.shaded.com.google.common.base.Function asEntryToEntryFunction(final EntryTransformer transformer) {
      Preconditions.checkNotNull(transformer);
      return new org.apache.curator.shaded.com.google.common.base.Function() {
         public Map.Entry apply(final Map.Entry entry) {
            return Maps.transformEntry(transformer, entry);
         }
      };
   }

   static Predicate keyPredicateOnEntries(Predicate keyPredicate) {
      return Predicates.compose(keyPredicate, keyFunction());
   }

   static Predicate valuePredicateOnEntries(Predicate valuePredicate) {
      return Predicates.compose(valuePredicate, valueFunction());
   }

   public static Map filterKeys(Map unfiltered, final Predicate keyPredicate) {
      Preconditions.checkNotNull(keyPredicate);
      Predicate<Map.Entry<K, ?>> entryPredicate = keyPredicateOnEntries(keyPredicate);
      return (Map)(unfiltered instanceof AbstractFilteredMap ? filterFiltered((AbstractFilteredMap)unfiltered, entryPredicate) : new FilteredKeyMap((Map)Preconditions.checkNotNull(unfiltered), keyPredicate, entryPredicate));
   }

   public static SortedMap filterKeys(SortedMap unfiltered, final Predicate keyPredicate) {
      return filterEntries(unfiltered, keyPredicateOnEntries(keyPredicate));
   }

   @GwtIncompatible
   public static NavigableMap filterKeys(NavigableMap unfiltered, final Predicate keyPredicate) {
      return filterEntries(unfiltered, keyPredicateOnEntries(keyPredicate));
   }

   public static BiMap filterKeys(BiMap unfiltered, final Predicate keyPredicate) {
      Preconditions.checkNotNull(keyPredicate);
      return filterEntries(unfiltered, keyPredicateOnEntries(keyPredicate));
   }

   public static Map filterValues(Map unfiltered, final Predicate valuePredicate) {
      return filterEntries(unfiltered, valuePredicateOnEntries(valuePredicate));
   }

   public static SortedMap filterValues(SortedMap unfiltered, final Predicate valuePredicate) {
      return filterEntries(unfiltered, valuePredicateOnEntries(valuePredicate));
   }

   @GwtIncompatible
   public static NavigableMap filterValues(NavigableMap unfiltered, final Predicate valuePredicate) {
      return filterEntries(unfiltered, valuePredicateOnEntries(valuePredicate));
   }

   public static BiMap filterValues(BiMap unfiltered, final Predicate valuePredicate) {
      return filterEntries(unfiltered, valuePredicateOnEntries(valuePredicate));
   }

   public static Map filterEntries(Map unfiltered, Predicate entryPredicate) {
      Preconditions.checkNotNull(entryPredicate);
      return (Map)(unfiltered instanceof AbstractFilteredMap ? filterFiltered((AbstractFilteredMap)unfiltered, entryPredicate) : new FilteredEntryMap((Map)Preconditions.checkNotNull(unfiltered), entryPredicate));
   }

   public static SortedMap filterEntries(SortedMap unfiltered, Predicate entryPredicate) {
      Preconditions.checkNotNull(entryPredicate);
      return (SortedMap)(unfiltered instanceof FilteredEntrySortedMap ? filterFiltered((FilteredEntrySortedMap)unfiltered, entryPredicate) : new FilteredEntrySortedMap((SortedMap)Preconditions.checkNotNull(unfiltered), entryPredicate));
   }

   @GwtIncompatible
   public static NavigableMap filterEntries(NavigableMap unfiltered, Predicate entryPredicate) {
      Preconditions.checkNotNull(entryPredicate);
      return (NavigableMap)(unfiltered instanceof FilteredEntryNavigableMap ? filterFiltered((FilteredEntryNavigableMap)unfiltered, entryPredicate) : new FilteredEntryNavigableMap((NavigableMap)Preconditions.checkNotNull(unfiltered), entryPredicate));
   }

   public static BiMap filterEntries(BiMap unfiltered, Predicate entryPredicate) {
      Preconditions.checkNotNull(unfiltered);
      Preconditions.checkNotNull(entryPredicate);
      return (BiMap)(unfiltered instanceof FilteredEntryBiMap ? filterFiltered((FilteredEntryBiMap)unfiltered, entryPredicate) : new FilteredEntryBiMap(unfiltered, entryPredicate));
   }

   private static Map filterFiltered(AbstractFilteredMap map, Predicate entryPredicate) {
      return new FilteredEntryMap(map.unfiltered, Predicates.and(map.predicate, entryPredicate));
   }

   private static SortedMap filterFiltered(FilteredEntrySortedMap map, Predicate entryPredicate) {
      Predicate<Map.Entry<K, V>> predicate = Predicates.and(map.predicate, entryPredicate);
      return new FilteredEntrySortedMap(map.sortedMap(), predicate);
   }

   @GwtIncompatible
   private static NavigableMap filterFiltered(FilteredEntryNavigableMap map, Predicate entryPredicate) {
      Predicate<Map.Entry<K, V>> predicate = Predicates.and(map.entryPredicate, entryPredicate);
      return new FilteredEntryNavigableMap(map.unfiltered, predicate);
   }

   private static BiMap filterFiltered(FilteredEntryBiMap map, Predicate entryPredicate) {
      Predicate<Map.Entry<K, V>> predicate = Predicates.and(map.predicate, entryPredicate);
      return new FilteredEntryBiMap(map.unfiltered(), predicate);
   }

   @GwtIncompatible
   public static NavigableMap unmodifiableNavigableMap(NavigableMap map) {
      Preconditions.checkNotNull(map);
      return (NavigableMap)(map instanceof UnmodifiableNavigableMap ? map : new UnmodifiableNavigableMap(map));
   }

   @CheckForNull
   private static Map.Entry unmodifiableOrNull(@CheckForNull Map.Entry entry) {
      return entry == null ? null : unmodifiableEntry(entry);
   }

   @GwtIncompatible
   public static NavigableMap synchronizedNavigableMap(NavigableMap navigableMap) {
      return Synchronized.navigableMap(navigableMap);
   }

   @CheckForNull
   static Object safeGet(Map map, @CheckForNull Object key) {
      Preconditions.checkNotNull(map);

      try {
         return map.get(key);
      } catch (NullPointerException | ClassCastException var3) {
         return null;
      }
   }

   static boolean safeContainsKey(Map map, @CheckForNull Object key) {
      Preconditions.checkNotNull(map);

      try {
         return map.containsKey(key);
      } catch (NullPointerException | ClassCastException var3) {
         return false;
      }
   }

   @CheckForNull
   static Object safeRemove(Map map, @CheckForNull Object key) {
      Preconditions.checkNotNull(map);

      try {
         return map.remove(key);
      } catch (NullPointerException | ClassCastException var3) {
         return null;
      }
   }

   static boolean containsKeyImpl(Map map, @CheckForNull Object key) {
      return Iterators.contains(keyIterator(map.entrySet().iterator()), key);
   }

   static boolean containsValueImpl(Map map, @CheckForNull Object value) {
      return Iterators.contains(valueIterator(map.entrySet().iterator()), value);
   }

   static boolean containsEntryImpl(Collection c, @CheckForNull Object o) {
      return !(o instanceof Map.Entry) ? false : c.contains(unmodifiableEntry((Map.Entry)o));
   }

   static boolean removeEntryImpl(Collection c, @CheckForNull Object o) {
      return !(o instanceof Map.Entry) ? false : c.remove(unmodifiableEntry((Map.Entry)o));
   }

   static boolean equalsImpl(Map map, @CheckForNull Object object) {
      if (map == object) {
         return true;
      } else if (object instanceof Map) {
         Map<?, ?> o = (Map)object;
         return map.entrySet().equals(o.entrySet());
      } else {
         return false;
      }
   }

   static String toStringImpl(Map map) {
      StringBuilder sb = Collections2.newStringBuilderForCollection(map.size()).append('{');
      boolean first = true;

      for(Map.Entry entry : map.entrySet()) {
         if (!first) {
            sb.append(", ");
         }

         first = false;
         sb.append(entry.getKey()).append('=').append(entry.getValue());
      }

      return sb.append('}').toString();
   }

   static void putAllImpl(Map self, Map map) {
      for(Map.Entry entry : map.entrySet()) {
         self.put(entry.getKey(), entry.getValue());
      }

   }

   @CheckForNull
   static Object keyOrNull(@CheckForNull Map.Entry entry) {
      return entry == null ? null : entry.getKey();
   }

   @CheckForNull
   static Object valueOrNull(@CheckForNull Map.Entry entry) {
      return entry == null ? null : entry.getValue();
   }

   static ImmutableMap indexMap(Collection list) {
      ImmutableMap.Builder<E, Integer> builder = new ImmutableMap.Builder(list.size());
      int i = 0;

      for(Object e : list) {
         builder.put(e, i++);
      }

      return builder.buildOrThrow();
   }

   @GwtIncompatible
   public static NavigableMap subMap(NavigableMap map, Range range) {
      if (map.comparator() != null && map.comparator() != Ordering.natural() && range.hasLowerBound() && range.hasUpperBound()) {
         Preconditions.checkArgument(map.comparator().compare(range.lowerEndpoint(), range.upperEndpoint()) <= 0, "map is using a custom comparator which is inconsistent with the natural ordering.");
      }

      if (range.hasLowerBound() && range.hasUpperBound()) {
         return map.subMap(range.lowerEndpoint(), range.lowerBoundType() == BoundType.CLOSED, range.upperEndpoint(), range.upperBoundType() == BoundType.CLOSED);
      } else if (range.hasLowerBound()) {
         return map.tailMap(range.lowerEndpoint(), range.lowerBoundType() == BoundType.CLOSED);
      } else {
         return range.hasUpperBound() ? map.headMap(range.upperEndpoint(), range.upperBoundType() == BoundType.CLOSED) : (NavigableMap)Preconditions.checkNotNull(map);
      }
   }

   private static enum EntryFunction implements org.apache.curator.shaded.com.google.common.base.Function {
      KEY {
         @CheckForNull
         public Object apply(Map.Entry entry) {
            return entry.getKey();
         }
      },
      VALUE {
         @CheckForNull
         public Object apply(Map.Entry entry) {
            return entry.getValue();
         }
      };

      private EntryFunction() {
      }

      // $FF: synthetic method
      private static EntryFunction[] $values() {
         return new EntryFunction[]{KEY, VALUE};
      }
   }

   static class MapDifferenceImpl implements MapDifference {
      final Map onlyOnLeft;
      final Map onlyOnRight;
      final Map onBoth;
      final Map differences;

      MapDifferenceImpl(Map onlyOnLeft, Map onlyOnRight, Map onBoth, Map differences) {
         this.onlyOnLeft = Maps.unmodifiableMap(onlyOnLeft);
         this.onlyOnRight = Maps.unmodifiableMap(onlyOnRight);
         this.onBoth = Maps.unmodifiableMap(onBoth);
         this.differences = Maps.unmodifiableMap(differences);
      }

      public boolean areEqual() {
         return this.onlyOnLeft.isEmpty() && this.onlyOnRight.isEmpty() && this.differences.isEmpty();
      }

      public Map entriesOnlyOnLeft() {
         return this.onlyOnLeft;
      }

      public Map entriesOnlyOnRight() {
         return this.onlyOnRight;
      }

      public Map entriesInCommon() {
         return this.onBoth;
      }

      public Map entriesDiffering() {
         return this.differences;
      }

      public boolean equals(@CheckForNull Object object) {
         if (object == this) {
            return true;
         } else if (!(object instanceof MapDifference)) {
            return false;
         } else {
            MapDifference<?, ?> other = (MapDifference)object;
            return this.entriesOnlyOnLeft().equals(other.entriesOnlyOnLeft()) && this.entriesOnlyOnRight().equals(other.entriesOnlyOnRight()) && this.entriesInCommon().equals(other.entriesInCommon()) && this.entriesDiffering().equals(other.entriesDiffering());
         }
      }

      public int hashCode() {
         return org.apache.curator.shaded.com.google.common.base.Objects.hashCode(this.entriesOnlyOnLeft(), this.entriesOnlyOnRight(), this.entriesInCommon(), this.entriesDiffering());
      }

      public String toString() {
         if (this.areEqual()) {
            return "equal";
         } else {
            StringBuilder result = new StringBuilder("not equal");
            if (!this.onlyOnLeft.isEmpty()) {
               result.append(": only on left=").append(this.onlyOnLeft);
            }

            if (!this.onlyOnRight.isEmpty()) {
               result.append(": only on right=").append(this.onlyOnRight);
            }

            if (!this.differences.isEmpty()) {
               result.append(": value differences=").append(this.differences);
            }

            return result.toString();
         }
      }
   }

   static class ValueDifferenceImpl implements MapDifference.ValueDifference {
      @ParametricNullness
      private final Object left;
      @ParametricNullness
      private final Object right;

      static MapDifference.ValueDifference create(@ParametricNullness Object left, @ParametricNullness Object right) {
         return new ValueDifferenceImpl(left, right);
      }

      private ValueDifferenceImpl(@ParametricNullness Object left, @ParametricNullness Object right) {
         this.left = left;
         this.right = right;
      }

      @ParametricNullness
      public Object leftValue() {
         return this.left;
      }

      @ParametricNullness
      public Object rightValue() {
         return this.right;
      }

      public boolean equals(@CheckForNull Object object) {
         if (!(object instanceof MapDifference.ValueDifference)) {
            return false;
         } else {
            MapDifference.ValueDifference<?> that = (MapDifference.ValueDifference)object;
            return org.apache.curator.shaded.com.google.common.base.Objects.equal(this.left, that.leftValue()) && org.apache.curator.shaded.com.google.common.base.Objects.equal(this.right, that.rightValue());
         }
      }

      public int hashCode() {
         return org.apache.curator.shaded.com.google.common.base.Objects.hashCode(this.left, this.right);
      }

      public String toString() {
         return "(" + this.left + ", " + this.right + ")";
      }
   }

   static class SortedMapDifferenceImpl extends MapDifferenceImpl implements SortedMapDifference {
      SortedMapDifferenceImpl(SortedMap onlyOnLeft, SortedMap onlyOnRight, SortedMap onBoth, SortedMap differences) {
         super(onlyOnLeft, onlyOnRight, onBoth, differences);
      }

      public SortedMap entriesDiffering() {
         return (SortedMap)super.entriesDiffering();
      }

      public SortedMap entriesInCommon() {
         return (SortedMap)super.entriesInCommon();
      }

      public SortedMap entriesOnlyOnLeft() {
         return (SortedMap)super.entriesOnlyOnLeft();
      }

      public SortedMap entriesOnlyOnRight() {
         return (SortedMap)super.entriesOnlyOnRight();
      }
   }

   private static class AsMapView extends ViewCachingAbstractMap {
      private final Set set;
      final org.apache.curator.shaded.com.google.common.base.Function function;

      Set backingSet() {
         return this.set;
      }

      AsMapView(Set set, org.apache.curator.shaded.com.google.common.base.Function function) {
         this.set = (Set)Preconditions.checkNotNull(set);
         this.function = (org.apache.curator.shaded.com.google.common.base.Function)Preconditions.checkNotNull(function);
      }

      public Set createKeySet() {
         return Maps.removeOnlySet(this.backingSet());
      }

      Collection createValues() {
         return Collections2.transform(this.set, this.function);
      }

      public int size() {
         return this.backingSet().size();
      }

      public boolean containsKey(@CheckForNull Object key) {
         return this.backingSet().contains(key);
      }

      @CheckForNull
      public Object get(@CheckForNull Object key) {
         return this.getOrDefault(key, (Object)null);
      }

      @CheckForNull
      public Object getOrDefault(@CheckForNull Object key, @CheckForNull Object defaultValue) {
         return Collections2.safeContains(this.backingSet(), key) ? this.function.apply(key) : defaultValue;
      }

      @CheckForNull
      public Object remove(@CheckForNull Object key) {
         return this.backingSet().remove(key) ? this.function.apply(key) : null;
      }

      public void clear() {
         this.backingSet().clear();
      }

      protected Set createEntrySet() {
         class EntrySetImpl extends EntrySet {
            Map map() {
               return AsMapView.this;
            }

            public Iterator iterator() {
               return Maps.asMapEntryIterator(AsMapView.this.backingSet(), AsMapView.this.function);
            }
         }

         return new EntrySetImpl();
      }

      public void forEach(BiConsumer action) {
         Preconditions.checkNotNull(action);
         this.backingSet().forEach((k) -> action.accept(k, this.function.apply(k)));
      }
   }

   private static class SortedAsMapView extends AsMapView implements SortedMap {
      SortedAsMapView(SortedSet set, org.apache.curator.shaded.com.google.common.base.Function function) {
         super(set, function);
      }

      SortedSet backingSet() {
         return (SortedSet)super.backingSet();
      }

      @CheckForNull
      public Comparator comparator() {
         return this.backingSet().comparator();
      }

      public Set keySet() {
         return Maps.removeOnlySortedSet(this.backingSet());
      }

      public SortedMap subMap(@ParametricNullness Object fromKey, @ParametricNullness Object toKey) {
         return Maps.asMap(this.backingSet().subSet(fromKey, toKey), this.function);
      }

      public SortedMap headMap(@ParametricNullness Object toKey) {
         return Maps.asMap(this.backingSet().headSet(toKey), this.function);
      }

      public SortedMap tailMap(@ParametricNullness Object fromKey) {
         return Maps.asMap(this.backingSet().tailSet(fromKey), this.function);
      }

      @ParametricNullness
      public Object firstKey() {
         return this.backingSet().first();
      }

      @ParametricNullness
      public Object lastKey() {
         return this.backingSet().last();
      }
   }

   @GwtIncompatible
   private static final class NavigableAsMapView extends AbstractNavigableMap {
      private final NavigableSet set;
      private final org.apache.curator.shaded.com.google.common.base.Function function;

      NavigableAsMapView(NavigableSet ks, org.apache.curator.shaded.com.google.common.base.Function vFunction) {
         this.set = (NavigableSet)Preconditions.checkNotNull(ks);
         this.function = (org.apache.curator.shaded.com.google.common.base.Function)Preconditions.checkNotNull(vFunction);
      }

      public NavigableMap subMap(@ParametricNullness Object fromKey, boolean fromInclusive, @ParametricNullness Object toKey, boolean toInclusive) {
         return Maps.asMap(this.set.subSet(fromKey, fromInclusive, toKey, toInclusive), this.function);
      }

      public NavigableMap headMap(@ParametricNullness Object toKey, boolean inclusive) {
         return Maps.asMap(this.set.headSet(toKey, inclusive), this.function);
      }

      public NavigableMap tailMap(@ParametricNullness Object fromKey, boolean inclusive) {
         return Maps.asMap(this.set.tailSet(fromKey, inclusive), this.function);
      }

      @CheckForNull
      public Comparator comparator() {
         return this.set.comparator();
      }

      @CheckForNull
      public Object get(@CheckForNull Object key) {
         return this.getOrDefault(key, (Object)null);
      }

      @CheckForNull
      public Object getOrDefault(@CheckForNull Object key, @CheckForNull Object defaultValue) {
         return Collections2.safeContains(this.set, key) ? this.function.apply(key) : defaultValue;
      }

      public void clear() {
         this.set.clear();
      }

      Iterator entryIterator() {
         return Maps.asMapEntryIterator(this.set, this.function);
      }

      Spliterator entrySpliterator() {
         return CollectSpliterators.map(this.set.spliterator(), (e) -> Maps.immutableEntry(e, this.function.apply(e)));
      }

      public void forEach(BiConsumer action) {
         this.set.forEach((k) -> action.accept(k, this.function.apply(k)));
      }

      Iterator descendingEntryIterator() {
         return this.descendingMap().entrySet().iterator();
      }

      public NavigableSet navigableKeySet() {
         return Maps.removeOnlyNavigableSet(this.set);
      }

      public int size() {
         return this.set.size();
      }

      public NavigableMap descendingMap() {
         return Maps.asMap(this.set.descendingSet(), this.function);
      }
   }

   static class UnmodifiableEntries extends ForwardingCollection {
      private final Collection entries;

      UnmodifiableEntries(Collection entries) {
         this.entries = entries;
      }

      protected Collection delegate() {
         return this.entries;
      }

      public Iterator iterator() {
         return Maps.unmodifiableEntryIterator(this.entries.iterator());
      }

      public @Nullable Object[] toArray() {
         return this.standardToArray();
      }

      public Object[] toArray(Object[] array) {
         return this.standardToArray(array);
      }
   }

   static class UnmodifiableEntrySet extends UnmodifiableEntries implements Set {
      UnmodifiableEntrySet(Set entries) {
         super(entries);
      }

      public boolean equals(@CheckForNull Object object) {
         return Sets.equalsImpl(this, object);
      }

      public int hashCode() {
         return Sets.hashCodeImpl(this);
      }
   }

   private static final class BiMapConverter extends Converter implements Serializable {
      private final BiMap bimap;
      private static final long serialVersionUID = 0L;

      BiMapConverter(BiMap bimap) {
         this.bimap = (BiMap)Preconditions.checkNotNull(bimap);
      }

      protected Object doForward(Object a) {
         return convert(this.bimap, a);
      }

      protected Object doBackward(Object b) {
         return convert(this.bimap.inverse(), b);
      }

      private static Object convert(BiMap bimap, Object input) {
         Y output = (Y)bimap.get(input);
         Preconditions.checkArgument(output != null, "No non-null mapping present for input: %s", input);
         return output;
      }

      public boolean equals(@CheckForNull Object object) {
         if (object instanceof BiMapConverter) {
            BiMapConverter<?, ?> that = (BiMapConverter)object;
            return this.bimap.equals(that.bimap);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.bimap.hashCode();
      }

      public String toString() {
         return "Maps.asConverter(" + this.bimap + ")";
      }
   }

   private static class UnmodifiableBiMap extends ForwardingMap implements BiMap, Serializable {
      final Map unmodifiableMap;
      final BiMap delegate;
      @CheckForNull
      @RetainedWith
      BiMap inverse;
      @CheckForNull
      transient Set values;
      private static final long serialVersionUID = 0L;

      UnmodifiableBiMap(BiMap delegate, @CheckForNull BiMap inverse) {
         this.unmodifiableMap = Collections.unmodifiableMap(delegate);
         this.delegate = delegate;
         this.inverse = inverse;
      }

      protected Map delegate() {
         return this.unmodifiableMap;
      }

      @CheckForNull
      public Object forcePut(@ParametricNullness Object key, @ParametricNullness Object value) {
         throw new UnsupportedOperationException();
      }

      public void replaceAll(BiFunction function) {
         throw new UnsupportedOperationException();
      }

      @CheckForNull
      public Object putIfAbsent(Object key, Object value) {
         throw new UnsupportedOperationException();
      }

      public boolean remove(@Nullable Object key, @Nullable Object value) {
         throw new UnsupportedOperationException();
      }

      public boolean replace(Object key, Object oldValue, Object newValue) {
         throw new UnsupportedOperationException();
      }

      @CheckForNull
      public Object replace(Object key, Object value) {
         throw new UnsupportedOperationException();
      }

      public Object computeIfAbsent(Object key, Function mappingFunction) {
         throw new UnsupportedOperationException();
      }

      public Object computeIfPresent(Object key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      public Object compute(Object key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      public Object merge(Object key, Object value, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      public BiMap inverse() {
         BiMap<V, K> result = this.inverse;
         return result == null ? (this.inverse = new UnmodifiableBiMap(this.delegate.inverse(), this)) : result;
      }

      public Set values() {
         Set<V> result = this.values;
         return result == null ? (this.values = Collections.unmodifiableSet(this.delegate.values())) : result;
      }
   }

   static class TransformedEntriesMap extends IteratorBasedAbstractMap {
      final Map fromMap;
      final EntryTransformer transformer;

      TransformedEntriesMap(Map fromMap, EntryTransformer transformer) {
         this.fromMap = (Map)Preconditions.checkNotNull(fromMap);
         this.transformer = (EntryTransformer)Preconditions.checkNotNull(transformer);
      }

      public int size() {
         return this.fromMap.size();
      }

      public boolean containsKey(@CheckForNull Object key) {
         return this.fromMap.containsKey(key);
      }

      @CheckForNull
      public Object get(@CheckForNull Object key) {
         return this.getOrDefault(key, (Object)null);
      }

      @CheckForNull
      public Object getOrDefault(@CheckForNull Object key, @CheckForNull Object defaultValue) {
         V1 value = (V1)this.fromMap.get(key);
         return value == null && !this.fromMap.containsKey(key) ? defaultValue : this.transformer.transformEntry(key, NullnessCasts.uncheckedCastNullableTToT(value));
      }

      @CheckForNull
      public Object remove(@CheckForNull Object key) {
         return this.fromMap.containsKey(key) ? this.transformer.transformEntry(key, NullnessCasts.uncheckedCastNullableTToT(this.fromMap.remove(key))) : null;
      }

      public void clear() {
         this.fromMap.clear();
      }

      public Set keySet() {
         return this.fromMap.keySet();
      }

      Iterator entryIterator() {
         return Iterators.transform(this.fromMap.entrySet().iterator(), Maps.asEntryToEntryFunction(this.transformer));
      }

      Spliterator entrySpliterator() {
         return CollectSpliterators.map(this.fromMap.entrySet().spliterator(), Maps.asEntryToEntryFunction(this.transformer));
      }

      public void forEach(BiConsumer action) {
         Preconditions.checkNotNull(action);
         this.fromMap.forEach((k, v1) -> action.accept(k, this.transformer.transformEntry(k, v1)));
      }

      public Collection values() {
         return new Values(this);
      }
   }

   static class TransformedEntriesSortedMap extends TransformedEntriesMap implements SortedMap {
      protected SortedMap fromMap() {
         return (SortedMap)this.fromMap;
      }

      TransformedEntriesSortedMap(SortedMap fromMap, EntryTransformer transformer) {
         super(fromMap, transformer);
      }

      @CheckForNull
      public Comparator comparator() {
         return this.fromMap().comparator();
      }

      @ParametricNullness
      public Object firstKey() {
         return this.fromMap().firstKey();
      }

      public SortedMap headMap(@ParametricNullness Object toKey) {
         return Maps.transformEntries(this.fromMap().headMap(toKey), this.transformer);
      }

      @ParametricNullness
      public Object lastKey() {
         return this.fromMap().lastKey();
      }

      public SortedMap subMap(@ParametricNullness Object fromKey, @ParametricNullness Object toKey) {
         return Maps.transformEntries(this.fromMap().subMap(fromKey, toKey), this.transformer);
      }

      public SortedMap tailMap(@ParametricNullness Object fromKey) {
         return Maps.transformEntries(this.fromMap().tailMap(fromKey), this.transformer);
      }
   }

   @GwtIncompatible
   private static class TransformedEntriesNavigableMap extends TransformedEntriesSortedMap implements NavigableMap {
      TransformedEntriesNavigableMap(NavigableMap fromMap, EntryTransformer transformer) {
         super(fromMap, transformer);
      }

      @CheckForNull
      public Map.Entry ceilingEntry(@ParametricNullness Object key) {
         return this.transformEntry(this.fromMap().ceilingEntry(key));
      }

      @CheckForNull
      public Object ceilingKey(@ParametricNullness Object key) {
         return this.fromMap().ceilingKey(key);
      }

      public NavigableSet descendingKeySet() {
         return this.fromMap().descendingKeySet();
      }

      public NavigableMap descendingMap() {
         return Maps.transformEntries(this.fromMap().descendingMap(), this.transformer);
      }

      @CheckForNull
      public Map.Entry firstEntry() {
         return this.transformEntry(this.fromMap().firstEntry());
      }

      @CheckForNull
      public Map.Entry floorEntry(@ParametricNullness Object key) {
         return this.transformEntry(this.fromMap().floorEntry(key));
      }

      @CheckForNull
      public Object floorKey(@ParametricNullness Object key) {
         return this.fromMap().floorKey(key);
      }

      public NavigableMap headMap(@ParametricNullness Object toKey) {
         return this.headMap(toKey, false);
      }

      public NavigableMap headMap(@ParametricNullness Object toKey, boolean inclusive) {
         return Maps.transformEntries(this.fromMap().headMap(toKey, inclusive), this.transformer);
      }

      @CheckForNull
      public Map.Entry higherEntry(@ParametricNullness Object key) {
         return this.transformEntry(this.fromMap().higherEntry(key));
      }

      @CheckForNull
      public Object higherKey(@ParametricNullness Object key) {
         return this.fromMap().higherKey(key);
      }

      @CheckForNull
      public Map.Entry lastEntry() {
         return this.transformEntry(this.fromMap().lastEntry());
      }

      @CheckForNull
      public Map.Entry lowerEntry(@ParametricNullness Object key) {
         return this.transformEntry(this.fromMap().lowerEntry(key));
      }

      @CheckForNull
      public Object lowerKey(@ParametricNullness Object key) {
         return this.fromMap().lowerKey(key);
      }

      public NavigableSet navigableKeySet() {
         return this.fromMap().navigableKeySet();
      }

      @CheckForNull
      public Map.Entry pollFirstEntry() {
         return this.transformEntry(this.fromMap().pollFirstEntry());
      }

      @CheckForNull
      public Map.Entry pollLastEntry() {
         return this.transformEntry(this.fromMap().pollLastEntry());
      }

      public NavigableMap subMap(@ParametricNullness Object fromKey, boolean fromInclusive, @ParametricNullness Object toKey, boolean toInclusive) {
         return Maps.transformEntries(this.fromMap().subMap(fromKey, fromInclusive, toKey, toInclusive), this.transformer);
      }

      public NavigableMap subMap(@ParametricNullness Object fromKey, @ParametricNullness Object toKey) {
         return this.subMap(fromKey, true, toKey, false);
      }

      public NavigableMap tailMap(@ParametricNullness Object fromKey) {
         return this.tailMap(fromKey, true);
      }

      public NavigableMap tailMap(@ParametricNullness Object fromKey, boolean inclusive) {
         return Maps.transformEntries(this.fromMap().tailMap(fromKey, inclusive), this.transformer);
      }

      @CheckForNull
      private Map.Entry transformEntry(@CheckForNull Map.Entry entry) {
         return entry == null ? null : Maps.transformEntry(this.transformer, entry);
      }

      protected NavigableMap fromMap() {
         return (NavigableMap)super.fromMap();
      }
   }

   private abstract static class AbstractFilteredMap extends ViewCachingAbstractMap {
      final Map unfiltered;
      final Predicate predicate;

      AbstractFilteredMap(Map unfiltered, Predicate predicate) {
         this.unfiltered = unfiltered;
         this.predicate = predicate;
      }

      boolean apply(@CheckForNull Object key, @ParametricNullness Object value) {
         return this.predicate.apply(Maps.immutableEntry(key, value));
      }

      @CheckForNull
      public Object put(@ParametricNullness Object key, @ParametricNullness Object value) {
         Preconditions.checkArgument(this.apply(key, value));
         return this.unfiltered.put(key, value);
      }

      public void putAll(Map map) {
         for(Map.Entry entry : map.entrySet()) {
            Preconditions.checkArgument(this.apply(entry.getKey(), entry.getValue()));
         }

         this.unfiltered.putAll(map);
      }

      public boolean containsKey(@CheckForNull Object key) {
         return this.unfiltered.containsKey(key) && this.apply(key, this.unfiltered.get(key));
      }

      @CheckForNull
      public Object get(@CheckForNull Object key) {
         V value = (V)this.unfiltered.get(key);
         return value != null && this.apply(key, value) ? value : null;
      }

      public boolean isEmpty() {
         return this.entrySet().isEmpty();
      }

      @CheckForNull
      public Object remove(@CheckForNull Object key) {
         return this.containsKey(key) ? this.unfiltered.remove(key) : null;
      }

      Collection createValues() {
         return new FilteredMapValues(this, this.unfiltered, this.predicate);
      }
   }

   private static final class FilteredMapValues extends Values {
      final Map unfiltered;
      final Predicate predicate;

      FilteredMapValues(Map filteredMap, Map unfiltered, Predicate predicate) {
         super(filteredMap);
         this.unfiltered = unfiltered;
         this.predicate = predicate;
      }

      public boolean remove(@CheckForNull Object o) {
         Iterator<Map.Entry<K, V>> entryItr = this.unfiltered.entrySet().iterator();

         while(entryItr.hasNext()) {
            Map.Entry<K, V> entry = (Map.Entry)entryItr.next();
            if (this.predicate.apply(entry) && org.apache.curator.shaded.com.google.common.base.Objects.equal(entry.getValue(), o)) {
               entryItr.remove();
               return true;
            }
         }

         return false;
      }

      public boolean removeAll(Collection collection) {
         Iterator<Map.Entry<K, V>> entryItr = this.unfiltered.entrySet().iterator();
         boolean result = false;

         while(entryItr.hasNext()) {
            Map.Entry<K, V> entry = (Map.Entry)entryItr.next();
            if (this.predicate.apply(entry) && collection.contains(entry.getValue())) {
               entryItr.remove();
               result = true;
            }
         }

         return result;
      }

      public boolean retainAll(Collection collection) {
         Iterator<Map.Entry<K, V>> entryItr = this.unfiltered.entrySet().iterator();
         boolean result = false;

         while(entryItr.hasNext()) {
            Map.Entry<K, V> entry = (Map.Entry)entryItr.next();
            if (this.predicate.apply(entry) && !collection.contains(entry.getValue())) {
               entryItr.remove();
               result = true;
            }
         }

         return result;
      }

      public @Nullable Object[] toArray() {
         return Lists.newArrayList(this.iterator()).toArray();
      }

      public Object[] toArray(Object[] array) {
         return Lists.newArrayList(this.iterator()).toArray(array);
      }
   }

   private static class FilteredKeyMap extends AbstractFilteredMap {
      final Predicate keyPredicate;

      FilteredKeyMap(Map unfiltered, Predicate keyPredicate, Predicate entryPredicate) {
         super(unfiltered, entryPredicate);
         this.keyPredicate = keyPredicate;
      }

      protected Set createEntrySet() {
         return Sets.filter(this.unfiltered.entrySet(), this.predicate);
      }

      Set createKeySet() {
         return Sets.filter(this.unfiltered.keySet(), this.keyPredicate);
      }

      public boolean containsKey(@CheckForNull Object key) {
         return this.unfiltered.containsKey(key) && this.keyPredicate.apply(key);
      }
   }

   static class FilteredEntryMap extends AbstractFilteredMap {
      final Set filteredEntrySet;

      FilteredEntryMap(Map unfiltered, Predicate entryPredicate) {
         super(unfiltered, entryPredicate);
         this.filteredEntrySet = Sets.filter(unfiltered.entrySet(), this.predicate);
      }

      protected Set createEntrySet() {
         return new EntrySet();
      }

      Set createKeySet() {
         return new KeySet();
      }

      static boolean removeAllKeys(Map map, Predicate entryPredicate, Collection keyCollection) {
         Iterator<Map.Entry<K, V>> entryItr = map.entrySet().iterator();
         boolean result = false;

         while(entryItr.hasNext()) {
            Map.Entry<K, V> entry = (Map.Entry)entryItr.next();
            if (entryPredicate.apply(entry) && keyCollection.contains(entry.getKey())) {
               entryItr.remove();
               result = true;
            }
         }

         return result;
      }

      static boolean retainAllKeys(Map map, Predicate entryPredicate, Collection keyCollection) {
         Iterator<Map.Entry<K, V>> entryItr = map.entrySet().iterator();
         boolean result = false;

         while(entryItr.hasNext()) {
            Map.Entry<K, V> entry = (Map.Entry)entryItr.next();
            if (entryPredicate.apply(entry) && !keyCollection.contains(entry.getKey())) {
               entryItr.remove();
               result = true;
            }
         }

         return result;
      }

      private class EntrySet extends ForwardingSet {
         private EntrySet() {
         }

         protected Set delegate() {
            return FilteredEntryMap.this.filteredEntrySet;
         }

         public Iterator iterator() {
            return new TransformedIterator(FilteredEntryMap.this.filteredEntrySet.iterator()) {
               Map.Entry transform(final Map.Entry entry) {
                  return new ForwardingMapEntry() {
                     protected Map.Entry delegate() {
                        return entry;
                     }

                     @ParametricNullness
                     public Object setValue(@ParametricNullness Object newValue) {
                        Preconditions.checkArgument(FilteredEntryMap.this.apply(this.getKey(), newValue));
                        return super.setValue(newValue);
                     }
                  };
               }
            };
         }
      }

      class KeySet extends KeySet {
         KeySet() {
            super(FilteredEntryMap.this);
         }

         public boolean remove(@CheckForNull Object o) {
            if (FilteredEntryMap.this.containsKey(o)) {
               FilteredEntryMap.this.unfiltered.remove(o);
               return true;
            } else {
               return false;
            }
         }

         public boolean removeAll(Collection collection) {
            return Maps.FilteredEntryMap.removeAllKeys(FilteredEntryMap.this.unfiltered, FilteredEntryMap.this.predicate, collection);
         }

         public boolean retainAll(Collection collection) {
            return Maps.FilteredEntryMap.retainAllKeys(FilteredEntryMap.this.unfiltered, FilteredEntryMap.this.predicate, collection);
         }

         public @Nullable Object[] toArray() {
            return Lists.newArrayList(this.iterator()).toArray();
         }

         public Object[] toArray(Object[] array) {
            return Lists.newArrayList(this.iterator()).toArray(array);
         }
      }
   }

   private static class FilteredEntrySortedMap extends FilteredEntryMap implements SortedMap {
      FilteredEntrySortedMap(SortedMap unfiltered, Predicate entryPredicate) {
         super(unfiltered, entryPredicate);
      }

      SortedMap sortedMap() {
         return (SortedMap)this.unfiltered;
      }

      public SortedSet keySet() {
         return (SortedSet)super.keySet();
      }

      SortedSet createKeySet() {
         return new SortedKeySet();
      }

      @CheckForNull
      public Comparator comparator() {
         return this.sortedMap().comparator();
      }

      @ParametricNullness
      public Object firstKey() {
         return this.keySet().iterator().next();
      }

      @ParametricNullness
      public Object lastKey() {
         SortedMap<K, V> headMap = this.sortedMap();

         while(true) {
            K key = (K)headMap.lastKey();
            if (this.apply(key, NullnessCasts.uncheckedCastNullableTToT(this.unfiltered.get(key)))) {
               return key;
            }

            headMap = this.sortedMap().headMap(key);
         }
      }

      public SortedMap headMap(@ParametricNullness Object toKey) {
         return new FilteredEntrySortedMap(this.sortedMap().headMap(toKey), this.predicate);
      }

      public SortedMap subMap(@ParametricNullness Object fromKey, @ParametricNullness Object toKey) {
         return new FilteredEntrySortedMap(this.sortedMap().subMap(fromKey, toKey), this.predicate);
      }

      public SortedMap tailMap(@ParametricNullness Object fromKey) {
         return new FilteredEntrySortedMap(this.sortedMap().tailMap(fromKey), this.predicate);
      }

      class SortedKeySet extends FilteredEntryMap.KeySet implements SortedSet {
         @CheckForNull
         public Comparator comparator() {
            return FilteredEntrySortedMap.this.sortedMap().comparator();
         }

         public SortedSet subSet(@ParametricNullness Object fromElement, @ParametricNullness Object toElement) {
            return (SortedSet)FilteredEntrySortedMap.this.subMap(fromElement, toElement).keySet();
         }

         public SortedSet headSet(@ParametricNullness Object toElement) {
            return (SortedSet)FilteredEntrySortedMap.this.headMap(toElement).keySet();
         }

         public SortedSet tailSet(@ParametricNullness Object fromElement) {
            return (SortedSet)FilteredEntrySortedMap.this.tailMap(fromElement).keySet();
         }

         @ParametricNullness
         public Object first() {
            return FilteredEntrySortedMap.this.firstKey();
         }

         @ParametricNullness
         public Object last() {
            return FilteredEntrySortedMap.this.lastKey();
         }
      }
   }

   @GwtIncompatible
   private static class FilteredEntryNavigableMap extends AbstractNavigableMap {
      private final NavigableMap unfiltered;
      private final Predicate entryPredicate;
      private final Map filteredDelegate;

      FilteredEntryNavigableMap(NavigableMap unfiltered, Predicate entryPredicate) {
         this.unfiltered = (NavigableMap)Preconditions.checkNotNull(unfiltered);
         this.entryPredicate = entryPredicate;
         this.filteredDelegate = new FilteredEntryMap(unfiltered, entryPredicate);
      }

      @CheckForNull
      public Comparator comparator() {
         return this.unfiltered.comparator();
      }

      public NavigableSet navigableKeySet() {
         return new NavigableKeySet(this) {
            public boolean removeAll(Collection collection) {
               return Maps.FilteredEntryMap.removeAllKeys(FilteredEntryNavigableMap.this.unfiltered, FilteredEntryNavigableMap.this.entryPredicate, collection);
            }

            public boolean retainAll(Collection collection) {
               return Maps.FilteredEntryMap.retainAllKeys(FilteredEntryNavigableMap.this.unfiltered, FilteredEntryNavigableMap.this.entryPredicate, collection);
            }
         };
      }

      public Collection values() {
         return new FilteredMapValues(this, this.unfiltered, this.entryPredicate);
      }

      Iterator entryIterator() {
         return Iterators.filter(this.unfiltered.entrySet().iterator(), this.entryPredicate);
      }

      Iterator descendingEntryIterator() {
         return Iterators.filter(this.unfiltered.descendingMap().entrySet().iterator(), this.entryPredicate);
      }

      public int size() {
         return this.filteredDelegate.size();
      }

      public boolean isEmpty() {
         return !Iterables.any(this.unfiltered.entrySet(), this.entryPredicate);
      }

      @CheckForNull
      public Object get(@CheckForNull Object key) {
         return this.filteredDelegate.get(key);
      }

      public boolean containsKey(@CheckForNull Object key) {
         return this.filteredDelegate.containsKey(key);
      }

      @CheckForNull
      public Object put(@ParametricNullness Object key, @ParametricNullness Object value) {
         return this.filteredDelegate.put(key, value);
      }

      @CheckForNull
      public Object remove(@CheckForNull Object key) {
         return this.filteredDelegate.remove(key);
      }

      public void putAll(Map m) {
         this.filteredDelegate.putAll(m);
      }

      public void clear() {
         this.filteredDelegate.clear();
      }

      public Set entrySet() {
         return this.filteredDelegate.entrySet();
      }

      @CheckForNull
      public Map.Entry pollFirstEntry() {
         return (Map.Entry)Iterables.removeFirstMatching(this.unfiltered.entrySet(), this.entryPredicate);
      }

      @CheckForNull
      public Map.Entry pollLastEntry() {
         return (Map.Entry)Iterables.removeFirstMatching(this.unfiltered.descendingMap().entrySet(), this.entryPredicate);
      }

      public NavigableMap descendingMap() {
         return Maps.filterEntries(this.unfiltered.descendingMap(), this.entryPredicate);
      }

      public NavigableMap subMap(@ParametricNullness Object fromKey, boolean fromInclusive, @ParametricNullness Object toKey, boolean toInclusive) {
         return Maps.filterEntries(this.unfiltered.subMap(fromKey, fromInclusive, toKey, toInclusive), this.entryPredicate);
      }

      public NavigableMap headMap(@ParametricNullness Object toKey, boolean inclusive) {
         return Maps.filterEntries(this.unfiltered.headMap(toKey, inclusive), this.entryPredicate);
      }

      public NavigableMap tailMap(@ParametricNullness Object fromKey, boolean inclusive) {
         return Maps.filterEntries(this.unfiltered.tailMap(fromKey, inclusive), this.entryPredicate);
      }
   }

   static final class FilteredEntryBiMap extends FilteredEntryMap implements BiMap {
      @RetainedWith
      private final BiMap inverse;

      private static Predicate inversePredicate(final Predicate forwardPredicate) {
         return new Predicate() {
            public boolean apply(Map.Entry input) {
               return forwardPredicate.apply(Maps.immutableEntry(input.getValue(), input.getKey()));
            }
         };
      }

      FilteredEntryBiMap(BiMap delegate, Predicate predicate) {
         super(delegate, predicate);
         this.inverse = new FilteredEntryBiMap(delegate.inverse(), inversePredicate(predicate), this);
      }

      private FilteredEntryBiMap(BiMap delegate, Predicate predicate, BiMap inverse) {
         super(delegate, predicate);
         this.inverse = inverse;
      }

      BiMap unfiltered() {
         return (BiMap)this.unfiltered;
      }

      @CheckForNull
      public Object forcePut(@ParametricNullness Object key, @ParametricNullness Object value) {
         Preconditions.checkArgument(this.apply(key, value));
         return this.unfiltered().forcePut(key, value);
      }

      public void replaceAll(BiFunction function) {
         this.unfiltered().replaceAll((key, value) -> this.predicate.apply(Maps.immutableEntry(key, value)) ? function.apply(key, value) : value);
      }

      public BiMap inverse() {
         return this.inverse;
      }

      public Set values() {
         return this.inverse.keySet();
      }
   }

   @GwtIncompatible
   static class UnmodifiableNavigableMap extends ForwardingSortedMap implements NavigableMap, Serializable {
      private final NavigableMap delegate;
      @CheckForNull
      private transient UnmodifiableNavigableMap descendingMap;

      UnmodifiableNavigableMap(NavigableMap delegate) {
         this.delegate = delegate;
      }

      UnmodifiableNavigableMap(NavigableMap delegate, UnmodifiableNavigableMap descendingMap) {
         this.delegate = delegate;
         this.descendingMap = descendingMap;
      }

      protected SortedMap delegate() {
         return Collections.unmodifiableSortedMap(this.delegate);
      }

      @CheckForNull
      public Map.Entry lowerEntry(@ParametricNullness Object key) {
         return Maps.unmodifiableOrNull(this.delegate.lowerEntry(key));
      }

      @CheckForNull
      public Object lowerKey(@ParametricNullness Object key) {
         return this.delegate.lowerKey(key);
      }

      @CheckForNull
      public Map.Entry floorEntry(@ParametricNullness Object key) {
         return Maps.unmodifiableOrNull(this.delegate.floorEntry(key));
      }

      @CheckForNull
      public Object floorKey(@ParametricNullness Object key) {
         return this.delegate.floorKey(key);
      }

      @CheckForNull
      public Map.Entry ceilingEntry(@ParametricNullness Object key) {
         return Maps.unmodifiableOrNull(this.delegate.ceilingEntry(key));
      }

      @CheckForNull
      public Object ceilingKey(@ParametricNullness Object key) {
         return this.delegate.ceilingKey(key);
      }

      @CheckForNull
      public Map.Entry higherEntry(@ParametricNullness Object key) {
         return Maps.unmodifiableOrNull(this.delegate.higherEntry(key));
      }

      @CheckForNull
      public Object higherKey(@ParametricNullness Object key) {
         return this.delegate.higherKey(key);
      }

      @CheckForNull
      public Map.Entry firstEntry() {
         return Maps.unmodifiableOrNull(this.delegate.firstEntry());
      }

      @CheckForNull
      public Map.Entry lastEntry() {
         return Maps.unmodifiableOrNull(this.delegate.lastEntry());
      }

      @CheckForNull
      public final Map.Entry pollFirstEntry() {
         throw new UnsupportedOperationException();
      }

      @CheckForNull
      public final Map.Entry pollLastEntry() {
         throw new UnsupportedOperationException();
      }

      public void replaceAll(BiFunction function) {
         throw new UnsupportedOperationException();
      }

      @CheckForNull
      public Object putIfAbsent(Object key, Object value) {
         throw new UnsupportedOperationException();
      }

      public boolean remove(@Nullable Object key, @Nullable Object value) {
         throw new UnsupportedOperationException();
      }

      public boolean replace(Object key, Object oldValue, Object newValue) {
         throw new UnsupportedOperationException();
      }

      @CheckForNull
      public Object replace(Object key, Object value) {
         throw new UnsupportedOperationException();
      }

      public Object computeIfAbsent(Object key, Function mappingFunction) {
         throw new UnsupportedOperationException();
      }

      public Object computeIfPresent(Object key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      public Object compute(Object key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      public Object merge(Object key, Object value, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      public NavigableMap descendingMap() {
         UnmodifiableNavigableMap<K, V> result = this.descendingMap;
         return result == null ? (this.descendingMap = new UnmodifiableNavigableMap(this.delegate.descendingMap(), this)) : result;
      }

      public Set keySet() {
         return this.navigableKeySet();
      }

      public NavigableSet navigableKeySet() {
         return Sets.unmodifiableNavigableSet(this.delegate.navigableKeySet());
      }

      public NavigableSet descendingKeySet() {
         return Sets.unmodifiableNavigableSet(this.delegate.descendingKeySet());
      }

      public SortedMap subMap(@ParametricNullness Object fromKey, @ParametricNullness Object toKey) {
         return this.subMap(fromKey, true, toKey, false);
      }

      public NavigableMap subMap(@ParametricNullness Object fromKey, boolean fromInclusive, @ParametricNullness Object toKey, boolean toInclusive) {
         return Maps.unmodifiableNavigableMap(this.delegate.subMap(fromKey, fromInclusive, toKey, toInclusive));
      }

      public SortedMap headMap(@ParametricNullness Object toKey) {
         return this.headMap(toKey, false);
      }

      public NavigableMap headMap(@ParametricNullness Object toKey, boolean inclusive) {
         return Maps.unmodifiableNavigableMap(this.delegate.headMap(toKey, inclusive));
      }

      public SortedMap tailMap(@ParametricNullness Object fromKey) {
         return this.tailMap(fromKey, true);
      }

      public NavigableMap tailMap(@ParametricNullness Object fromKey, boolean inclusive) {
         return Maps.unmodifiableNavigableMap(this.delegate.tailMap(fromKey, inclusive));
      }
   }

   @GwtCompatible
   abstract static class ViewCachingAbstractMap extends AbstractMap {
      @CheckForNull
      private transient Set entrySet;
      @CheckForNull
      private transient Set keySet;
      @CheckForNull
      private transient Collection values;

      abstract Set createEntrySet();

      public Set entrySet() {
         Set<Map.Entry<K, V>> result = this.entrySet;
         return result == null ? (this.entrySet = this.createEntrySet()) : result;
      }

      public Set keySet() {
         Set<K> result = this.keySet;
         return result == null ? (this.keySet = this.createKeySet()) : result;
      }

      Set createKeySet() {
         return new KeySet(this);
      }

      public Collection values() {
         Collection<V> result = this.values;
         return result == null ? (this.values = this.createValues()) : result;
      }

      Collection createValues() {
         return new Values(this);
      }
   }

   abstract static class IteratorBasedAbstractMap extends AbstractMap {
      public abstract int size();

      abstract Iterator entryIterator();

      Spliterator entrySpliterator() {
         return Spliterators.spliterator(this.entryIterator(), (long)this.size(), 65);
      }

      public Set entrySet() {
         return new EntrySet() {
            Map map() {
               return IteratorBasedAbstractMap.this;
            }

            public Iterator iterator() {
               return IteratorBasedAbstractMap.this.entryIterator();
            }

            public Spliterator spliterator() {
               return IteratorBasedAbstractMap.this.entrySpliterator();
            }

            public void forEach(Consumer action) {
               IteratorBasedAbstractMap.this.forEachEntry(action);
            }
         };
      }

      void forEachEntry(Consumer action) {
         this.entryIterator().forEachRemaining(action);
      }

      public void clear() {
         Iterators.clear(this.entryIterator());
      }
   }

   static class KeySet extends Sets.ImprovedAbstractSet {
      @Weak
      final Map map;

      KeySet(Map map) {
         this.map = (Map)Preconditions.checkNotNull(map);
      }

      Map map() {
         return this.map;
      }

      public Iterator iterator() {
         return Maps.keyIterator(this.map().entrySet().iterator());
      }

      public void forEach(Consumer action) {
         Preconditions.checkNotNull(action);
         this.map.forEach((k, v) -> action.accept(k));
      }

      public int size() {
         return this.map().size();
      }

      public boolean isEmpty() {
         return this.map().isEmpty();
      }

      public boolean contains(@CheckForNull Object o) {
         return this.map().containsKey(o);
      }

      public boolean remove(@CheckForNull Object o) {
         if (this.contains(o)) {
            this.map().remove(o);
            return true;
         } else {
            return false;
         }
      }

      public void clear() {
         this.map().clear();
      }
   }

   static class SortedKeySet extends KeySet implements SortedSet {
      SortedKeySet(SortedMap map) {
         super(map);
      }

      SortedMap map() {
         return (SortedMap)super.map();
      }

      @CheckForNull
      public Comparator comparator() {
         return this.map().comparator();
      }

      public SortedSet subSet(@ParametricNullness Object fromElement, @ParametricNullness Object toElement) {
         return new SortedKeySet(this.map().subMap(fromElement, toElement));
      }

      public SortedSet headSet(@ParametricNullness Object toElement) {
         return new SortedKeySet(this.map().headMap(toElement));
      }

      public SortedSet tailSet(@ParametricNullness Object fromElement) {
         return new SortedKeySet(this.map().tailMap(fromElement));
      }

      @ParametricNullness
      public Object first() {
         return this.map().firstKey();
      }

      @ParametricNullness
      public Object last() {
         return this.map().lastKey();
      }
   }

   @GwtIncompatible
   static class NavigableKeySet extends SortedKeySet implements NavigableSet {
      NavigableKeySet(NavigableMap map) {
         super(map);
      }

      NavigableMap map() {
         return (NavigableMap)this.map;
      }

      @CheckForNull
      public Object lower(@ParametricNullness Object e) {
         return this.map().lowerKey(e);
      }

      @CheckForNull
      public Object floor(@ParametricNullness Object e) {
         return this.map().floorKey(e);
      }

      @CheckForNull
      public Object ceiling(@ParametricNullness Object e) {
         return this.map().ceilingKey(e);
      }

      @CheckForNull
      public Object higher(@ParametricNullness Object e) {
         return this.map().higherKey(e);
      }

      @CheckForNull
      public Object pollFirst() {
         return Maps.keyOrNull(this.map().pollFirstEntry());
      }

      @CheckForNull
      public Object pollLast() {
         return Maps.keyOrNull(this.map().pollLastEntry());
      }

      public NavigableSet descendingSet() {
         return this.map().descendingKeySet();
      }

      public Iterator descendingIterator() {
         return this.descendingSet().iterator();
      }

      public NavigableSet subSet(@ParametricNullness Object fromElement, boolean fromInclusive, @ParametricNullness Object toElement, boolean toInclusive) {
         return this.map().subMap(fromElement, fromInclusive, toElement, toInclusive).navigableKeySet();
      }

      public SortedSet subSet(@ParametricNullness Object fromElement, @ParametricNullness Object toElement) {
         return this.subSet(fromElement, true, toElement, false);
      }

      public NavigableSet headSet(@ParametricNullness Object toElement, boolean inclusive) {
         return this.map().headMap(toElement, inclusive).navigableKeySet();
      }

      public SortedSet headSet(@ParametricNullness Object toElement) {
         return this.headSet(toElement, false);
      }

      public NavigableSet tailSet(@ParametricNullness Object fromElement, boolean inclusive) {
         return this.map().tailMap(fromElement, inclusive).navigableKeySet();
      }

      public SortedSet tailSet(@ParametricNullness Object fromElement) {
         return this.tailSet(fromElement, true);
      }
   }

   static class Values extends AbstractCollection {
      @Weak
      final Map map;

      Values(Map map) {
         this.map = (Map)Preconditions.checkNotNull(map);
      }

      final Map map() {
         return this.map;
      }

      public Iterator iterator() {
         return Maps.valueIterator(this.map().entrySet().iterator());
      }

      public void forEach(Consumer action) {
         Preconditions.checkNotNull(action);
         this.map.forEach((k, v) -> action.accept(v));
      }

      public boolean remove(@CheckForNull Object o) {
         try {
            return super.remove(o);
         } catch (UnsupportedOperationException var5) {
            for(Map.Entry entry : this.map().entrySet()) {
               if (org.apache.curator.shaded.com.google.common.base.Objects.equal(o, entry.getValue())) {
                  this.map().remove(entry.getKey());
                  return true;
               }
            }

            return false;
         }
      }

      public boolean removeAll(Collection c) {
         try {
            return super.removeAll((Collection)Preconditions.checkNotNull(c));
         } catch (UnsupportedOperationException var6) {
            Set<K> toRemove = Sets.newHashSet();

            for(Map.Entry entry : this.map().entrySet()) {
               if (c.contains(entry.getValue())) {
                  toRemove.add(entry.getKey());
               }
            }

            return this.map().keySet().removeAll(toRemove);
         }
      }

      public boolean retainAll(Collection c) {
         try {
            return super.retainAll((Collection)Preconditions.checkNotNull(c));
         } catch (UnsupportedOperationException var6) {
            Set<K> toRetain = Sets.newHashSet();

            for(Map.Entry entry : this.map().entrySet()) {
               if (c.contains(entry.getValue())) {
                  toRetain.add(entry.getKey());
               }
            }

            return this.map().keySet().retainAll(toRetain);
         }
      }

      public int size() {
         return this.map().size();
      }

      public boolean isEmpty() {
         return this.map().isEmpty();
      }

      public boolean contains(@CheckForNull Object o) {
         return this.map().containsValue(o);
      }

      public void clear() {
         this.map().clear();
      }
   }

   abstract static class EntrySet extends Sets.ImprovedAbstractSet {
      abstract Map map();

      public int size() {
         return this.map().size();
      }

      public void clear() {
         this.map().clear();
      }

      public boolean contains(@CheckForNull Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> entry = (Map.Entry)o;
            Object key = entry.getKey();
            V value = (V)Maps.safeGet(this.map(), key);
            return org.apache.curator.shaded.com.google.common.base.Objects.equal(value, entry.getValue()) && (value != null || this.map().containsKey(key));
         }
      }

      public boolean isEmpty() {
         return this.map().isEmpty();
      }

      public boolean remove(@CheckForNull Object o) {
         if (this.contains(o) && o instanceof Map.Entry) {
            Map.Entry<?, ?> entry = (Map.Entry)o;
            return this.map().keySet().remove(entry.getKey());
         } else {
            return false;
         }
      }

      public boolean removeAll(Collection c) {
         try {
            return super.removeAll((Collection)Preconditions.checkNotNull(c));
         } catch (UnsupportedOperationException var3) {
            return Sets.removeAllImpl(this, (Iterator)c.iterator());
         }
      }

      public boolean retainAll(Collection c) {
         try {
            return super.retainAll((Collection)Preconditions.checkNotNull(c));
         } catch (UnsupportedOperationException var7) {
            Set<Object> keys = Sets.newHashSetWithExpectedSize(c.size());

            for(Object o : c) {
               if (this.contains(o) && o instanceof Map.Entry) {
                  Map.Entry<?, ?> entry = (Map.Entry)o;
                  keys.add(entry.getKey());
               }
            }

            return this.map().keySet().retainAll(keys);
         }
      }
   }

   @GwtIncompatible
   abstract static class DescendingMap extends ForwardingMap implements NavigableMap {
      @CheckForNull
      private transient Comparator comparator;
      @CheckForNull
      private transient Set entrySet;
      @CheckForNull
      private transient NavigableSet navigableKeySet;

      abstract NavigableMap forward();

      protected final Map delegate() {
         return this.forward();
      }

      public Comparator comparator() {
         Comparator<? super K> result = this.comparator;
         if (result == null) {
            Comparator<? super K> forwardCmp = this.forward().comparator();
            if (forwardCmp == null) {
               forwardCmp = Ordering.natural();
            }

            result = this.comparator = reverse(forwardCmp);
         }

         return result;
      }

      private static Ordering reverse(Comparator forward) {
         return Ordering.from(forward).reverse();
      }

      @ParametricNullness
      public Object firstKey() {
         return this.forward().lastKey();
      }

      @ParametricNullness
      public Object lastKey() {
         return this.forward().firstKey();
      }

      @CheckForNull
      public Map.Entry lowerEntry(@ParametricNullness Object key) {
         return this.forward().higherEntry(key);
      }

      @CheckForNull
      public Object lowerKey(@ParametricNullness Object key) {
         return this.forward().higherKey(key);
      }

      @CheckForNull
      public Map.Entry floorEntry(@ParametricNullness Object key) {
         return this.forward().ceilingEntry(key);
      }

      @CheckForNull
      public Object floorKey(@ParametricNullness Object key) {
         return this.forward().ceilingKey(key);
      }

      @CheckForNull
      public Map.Entry ceilingEntry(@ParametricNullness Object key) {
         return this.forward().floorEntry(key);
      }

      @CheckForNull
      public Object ceilingKey(@ParametricNullness Object key) {
         return this.forward().floorKey(key);
      }

      @CheckForNull
      public Map.Entry higherEntry(@ParametricNullness Object key) {
         return this.forward().lowerEntry(key);
      }

      @CheckForNull
      public Object higherKey(@ParametricNullness Object key) {
         return this.forward().lowerKey(key);
      }

      @CheckForNull
      public Map.Entry firstEntry() {
         return this.forward().lastEntry();
      }

      @CheckForNull
      public Map.Entry lastEntry() {
         return this.forward().firstEntry();
      }

      @CheckForNull
      public Map.Entry pollFirstEntry() {
         return this.forward().pollLastEntry();
      }

      @CheckForNull
      public Map.Entry pollLastEntry() {
         return this.forward().pollFirstEntry();
      }

      public NavigableMap descendingMap() {
         return this.forward();
      }

      public Set entrySet() {
         Set<Map.Entry<K, V>> result = this.entrySet;
         return result == null ? (this.entrySet = this.createEntrySet()) : result;
      }

      abstract Iterator entryIterator();

      Set createEntrySet() {
         class EntrySetImpl extends EntrySet {
            Map map() {
               return DescendingMap.this;
            }

            public Iterator iterator() {
               return DescendingMap.this.entryIterator();
            }
         }

         return new EntrySetImpl();
      }

      public Set keySet() {
         return this.navigableKeySet();
      }

      public NavigableSet navigableKeySet() {
         NavigableSet<K> result = this.navigableKeySet;
         return result == null ? (this.navigableKeySet = new NavigableKeySet(this)) : result;
      }

      public NavigableSet descendingKeySet() {
         return this.forward().navigableKeySet();
      }

      public NavigableMap subMap(@ParametricNullness Object fromKey, boolean fromInclusive, @ParametricNullness Object toKey, boolean toInclusive) {
         return this.forward().subMap(toKey, toInclusive, fromKey, fromInclusive).descendingMap();
      }

      public SortedMap subMap(@ParametricNullness Object fromKey, @ParametricNullness Object toKey) {
         return this.subMap(fromKey, true, toKey, false);
      }

      public NavigableMap headMap(@ParametricNullness Object toKey, boolean inclusive) {
         return this.forward().tailMap(toKey, inclusive).descendingMap();
      }

      public SortedMap headMap(@ParametricNullness Object toKey) {
         return this.headMap(toKey, false);
      }

      public NavigableMap tailMap(@ParametricNullness Object fromKey, boolean inclusive) {
         return this.forward().headMap(fromKey, inclusive).descendingMap();
      }

      public SortedMap tailMap(@ParametricNullness Object fromKey) {
         return this.tailMap(fromKey, true);
      }

      public Collection values() {
         return new Values(this);
      }

      public String toString() {
         return this.standardToString();
      }
   }

   @FunctionalInterface
   public interface EntryTransformer {
      @ParametricNullness
      Object transformEntry(@ParametricNullness Object key, @ParametricNullness Object value);
   }
}
