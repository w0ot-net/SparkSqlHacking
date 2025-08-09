package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Collector.Characteristics;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class CollectCollectors {
   private static final Collector TO_IMMUTABLE_LIST = Collector.of(ImmutableList::builder, ImmutableList.Builder::add, ImmutableList.Builder::combine, ImmutableList.Builder::build);
   private static final Collector TO_IMMUTABLE_SET = Collector.of(ImmutableSet::builder, ImmutableSet.Builder::add, ImmutableSet.Builder::combine, ImmutableSet.Builder::build);
   @GwtIncompatible
   private static final Collector TO_IMMUTABLE_RANGE_SET = Collector.of(ImmutableRangeSet::builder, ImmutableRangeSet.Builder::add, ImmutableRangeSet.Builder::combine, ImmutableRangeSet.Builder::build);

   static Collector toImmutableList() {
      return TO_IMMUTABLE_LIST;
   }

   static Collector toImmutableSet() {
      return TO_IMMUTABLE_SET;
   }

   static Collector toImmutableSortedSet(Comparator comparator) {
      Preconditions.checkNotNull(comparator);
      return Collector.of(() -> new ImmutableSortedSet.Builder(comparator), ImmutableSortedSet.Builder::add, ImmutableSortedSet.Builder::combine, ImmutableSortedSet.Builder::build);
   }

   static Collector toImmutableEnumSet() {
      return CollectCollectors.EnumSetAccumulator.TO_IMMUTABLE_ENUM_SET;
   }

   private static Collector toImmutableEnumSetGeneric() {
      return Collector.of(() -> new EnumSetAccumulator(), EnumSetAccumulator::add, EnumSetAccumulator::combine, EnumSetAccumulator::toImmutableSet, Characteristics.UNORDERED);
   }

   @GwtIncompatible
   static Collector toImmutableRangeSet() {
      return TO_IMMUTABLE_RANGE_SET;
   }

   static Collector toImmutableMultiset(Function elementFunction, ToIntFunction countFunction) {
      Preconditions.checkNotNull(elementFunction);
      Preconditions.checkNotNull(countFunction);
      return Collector.of(LinkedHashMultiset::create, (multiset, t) -> multiset.add(Preconditions.checkNotNull(elementFunction.apply(t)), countFunction.applyAsInt(t)), (multiset1, multiset2) -> {
         multiset1.addAll(multiset2);
         return multiset1;
      }, (multiset) -> ImmutableMultiset.copyFromEntries(multiset.entrySet()));
   }

   static Collector toMultiset(Function elementFunction, ToIntFunction countFunction, Supplier multisetSupplier) {
      Preconditions.checkNotNull(elementFunction);
      Preconditions.checkNotNull(countFunction);
      Preconditions.checkNotNull(multisetSupplier);
      return Collector.of(multisetSupplier, (ms, t) -> ms.add(elementFunction.apply(t), countFunction.applyAsInt(t)), (ms1, ms2) -> {
         ms1.addAll(ms2);
         return ms1;
      });
   }

   static Collector toImmutableMap(Function keyFunction, Function valueFunction) {
      Preconditions.checkNotNull(keyFunction);
      Preconditions.checkNotNull(valueFunction);
      return Collector.of(ImmutableMap.Builder::new, (builder, input) -> builder.put(keyFunction.apply(input), valueFunction.apply(input)), ImmutableMap.Builder::combine, ImmutableMap.Builder::buildOrThrow);
   }

   static Collector toImmutableMap(Function keyFunction, Function valueFunction, BinaryOperator mergeFunction) {
      Preconditions.checkNotNull(keyFunction);
      Preconditions.checkNotNull(valueFunction);
      Preconditions.checkNotNull(mergeFunction);
      return Collectors.collectingAndThen(Collectors.toMap(keyFunction, valueFunction, mergeFunction, LinkedHashMap::new), ImmutableMap::copyOf);
   }

   static Collector toImmutableSortedMap(Comparator comparator, Function keyFunction, Function valueFunction) {
      Preconditions.checkNotNull(comparator);
      Preconditions.checkNotNull(keyFunction);
      Preconditions.checkNotNull(valueFunction);
      return Collector.of(() -> new ImmutableSortedMap.Builder(comparator), (builder, input) -> builder.put(keyFunction.apply(input), valueFunction.apply(input)), ImmutableSortedMap.Builder::combine, ImmutableSortedMap.Builder::buildOrThrow, Characteristics.UNORDERED);
   }

   static Collector toImmutableSortedMap(Comparator comparator, Function keyFunction, Function valueFunction, BinaryOperator mergeFunction) {
      Preconditions.checkNotNull(comparator);
      Preconditions.checkNotNull(keyFunction);
      Preconditions.checkNotNull(valueFunction);
      Preconditions.checkNotNull(mergeFunction);
      return Collectors.collectingAndThen(Collectors.toMap(keyFunction, valueFunction, mergeFunction, () -> new TreeMap(comparator)), ImmutableSortedMap::copyOfSorted);
   }

   static Collector toImmutableBiMap(Function keyFunction, Function valueFunction) {
      Preconditions.checkNotNull(keyFunction);
      Preconditions.checkNotNull(valueFunction);
      return Collector.of(ImmutableBiMap.Builder::new, (builder, input) -> builder.put(keyFunction.apply(input), valueFunction.apply(input)), ImmutableBiMap.Builder::combine, ImmutableBiMap.Builder::buildOrThrow);
   }

   static Collector toImmutableEnumMap(Function keyFunction, Function valueFunction) {
      Preconditions.checkNotNull(keyFunction);
      Preconditions.checkNotNull(valueFunction);
      return Collector.of(() -> new EnumMapAccumulator((v1, v2) -> {
            throw new IllegalArgumentException("Multiple values for key: " + v1 + ", " + v2);
         }), (accum, t) -> {
         K key = (K)((Enum)keyFunction.apply(t));
         V newValue = (V)valueFunction.apply(t);
         accum.put((Enum)Preconditions.checkNotNull(key, "Null key for input %s", (Object)t), Preconditions.checkNotNull(newValue, "Null value for input %s", t));
      }, EnumMapAccumulator::combine, EnumMapAccumulator::toImmutableMap, Characteristics.UNORDERED);
   }

   static Collector toImmutableEnumMap(Function keyFunction, Function valueFunction, BinaryOperator mergeFunction) {
      Preconditions.checkNotNull(keyFunction);
      Preconditions.checkNotNull(valueFunction);
      Preconditions.checkNotNull(mergeFunction);
      return Collector.of(() -> new EnumMapAccumulator(mergeFunction), (accum, t) -> {
         K key = (K)((Enum)keyFunction.apply(t));
         V newValue = (V)valueFunction.apply(t);
         accum.put((Enum)Preconditions.checkNotNull(key, "Null key for input %s", (Object)t), Preconditions.checkNotNull(newValue, "Null value for input %s", t));
      }, EnumMapAccumulator::combine, EnumMapAccumulator::toImmutableMap);
   }

   @GwtIncompatible
   static Collector toImmutableRangeMap(Function keyFunction, Function valueFunction) {
      Preconditions.checkNotNull(keyFunction);
      Preconditions.checkNotNull(valueFunction);
      return Collector.of(ImmutableRangeMap::builder, (builder, input) -> builder.put((Range)keyFunction.apply(input), valueFunction.apply(input)), ImmutableRangeMap.Builder::combine, ImmutableRangeMap.Builder::build);
   }

   static Collector toImmutableListMultimap(Function keyFunction, Function valueFunction) {
      Preconditions.checkNotNull(keyFunction, "keyFunction");
      Preconditions.checkNotNull(valueFunction, "valueFunction");
      return Collector.of(ImmutableListMultimap::builder, (builder, t) -> builder.put(keyFunction.apply(t), valueFunction.apply(t)), ImmutableListMultimap.Builder::combine, ImmutableListMultimap.Builder::build);
   }

   static Collector flatteningToImmutableListMultimap(Function keyFunction, Function valuesFunction) {
      Preconditions.checkNotNull(keyFunction);
      Preconditions.checkNotNull(valuesFunction);
      Function var10000 = (input) -> Preconditions.checkNotNull(keyFunction.apply(input));
      Function var10001 = (input) -> ((Stream)valuesFunction.apply(input)).peek(Preconditions::checkNotNull);
      MultimapBuilder.ListMultimapBuilder var10002 = MultimapBuilder.linkedHashKeys().arrayListValues();
      Objects.requireNonNull(var10002);
      return Collectors.collectingAndThen(flatteningToMultimap(var10000, var10001, var10002::build), ImmutableListMultimap::copyOf);
   }

   static Collector toImmutableSetMultimap(Function keyFunction, Function valueFunction) {
      Preconditions.checkNotNull(keyFunction, "keyFunction");
      Preconditions.checkNotNull(valueFunction, "valueFunction");
      return Collector.of(ImmutableSetMultimap::builder, (builder, t) -> builder.put(keyFunction.apply(t), valueFunction.apply(t)), ImmutableSetMultimap.Builder::combine, ImmutableSetMultimap.Builder::build);
   }

   static Collector flatteningToImmutableSetMultimap(Function keyFunction, Function valuesFunction) {
      Preconditions.checkNotNull(keyFunction);
      Preconditions.checkNotNull(valuesFunction);
      Function var10000 = (input) -> Preconditions.checkNotNull(keyFunction.apply(input));
      Function var10001 = (input) -> ((Stream)valuesFunction.apply(input)).peek(Preconditions::checkNotNull);
      MultimapBuilder.SetMultimapBuilder var10002 = MultimapBuilder.linkedHashKeys().linkedHashSetValues();
      Objects.requireNonNull(var10002);
      return Collectors.collectingAndThen(flatteningToMultimap(var10000, var10001, var10002::build), ImmutableSetMultimap::copyOf);
   }

   static Collector toMultimap(Function keyFunction, Function valueFunction, Supplier multimapSupplier) {
      Preconditions.checkNotNull(keyFunction);
      Preconditions.checkNotNull(valueFunction);
      Preconditions.checkNotNull(multimapSupplier);
      return Collector.of(multimapSupplier, (multimap, input) -> multimap.put(keyFunction.apply(input), valueFunction.apply(input)), (multimap1, multimap2) -> {
         multimap1.putAll(multimap2);
         return multimap1;
      });
   }

   static Collector flatteningToMultimap(Function keyFunction, Function valueFunction, Supplier multimapSupplier) {
      Preconditions.checkNotNull(keyFunction);
      Preconditions.checkNotNull(valueFunction);
      Preconditions.checkNotNull(multimapSupplier);
      return Collector.of(multimapSupplier, (multimap, input) -> {
         K key = (K)keyFunction.apply(input);
         Collection<V> valuesForKey = multimap.get(key);
         Stream var10000 = (Stream)valueFunction.apply(input);
         Objects.requireNonNull(valuesForKey);
         var10000.forEachOrdered(valuesForKey::add);
      }, (multimap1, multimap2) -> {
         multimap1.putAll(multimap2);
         return multimap1;
      });
   }

   private CollectCollectors() {
   }

   private static final class EnumSetAccumulator {
      static final Collector TO_IMMUTABLE_ENUM_SET = CollectCollectors.toImmutableEnumSetGeneric();
      @CheckForNull
      private EnumSet set;

      private EnumSetAccumulator() {
      }

      void add(Enum e) {
         if (this.set == null) {
            this.set = EnumSet.of(e);
         } else {
            this.set.add(e);
         }

      }

      EnumSetAccumulator combine(EnumSetAccumulator other) {
         if (this.set == null) {
            return other;
         } else if (other.set == null) {
            return this;
         } else {
            this.set.addAll(other.set);
            return this;
         }
      }

      ImmutableSet toImmutableSet() {
         if (this.set == null) {
            return ImmutableSet.of();
         } else {
            ImmutableSet<E> ret = ImmutableEnumSet.asImmutable(this.set);
            this.set = null;
            return ret;
         }
      }
   }

   private static class EnumMapAccumulator {
      private final BinaryOperator mergeFunction;
      @CheckForNull
      private EnumMap map = null;

      EnumMapAccumulator(BinaryOperator mergeFunction) {
         this.mergeFunction = mergeFunction;
      }

      void put(Enum key, Object value) {
         if (this.map == null) {
            this.map = new EnumMap(Collections.singletonMap(key, value));
         } else {
            this.map.merge(key, value, this.mergeFunction);
         }

      }

      EnumMapAccumulator combine(EnumMapAccumulator other) {
         if (this.map == null) {
            return other;
         } else if (other.map == null) {
            return this;
         } else {
            other.map.forEach(this::put);
            return this;
         }
      }

      ImmutableMap toImmutableMap() {
         return this.map == null ? ImmutableMap.of() : ImmutableEnumMap.asImmutable(this.map);
      }
   }
}
