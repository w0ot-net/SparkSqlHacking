package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.DoNotMock;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public class ImmutableRangeMap implements RangeMap, Serializable {
   private static final ImmutableRangeMap EMPTY = new ImmutableRangeMap(ImmutableList.of(), ImmutableList.of());
   private final transient ImmutableList ranges;
   private final transient ImmutableList values;
   private static final long serialVersionUID = 0L;

   public static Collector toImmutableRangeMap(Function keyFunction, Function valueFunction) {
      return CollectCollectors.toImmutableRangeMap(keyFunction, valueFunction);
   }

   public static ImmutableRangeMap of() {
      return EMPTY;
   }

   public static ImmutableRangeMap of(Range range, Object value) {
      return new ImmutableRangeMap(ImmutableList.of(range), ImmutableList.of(value));
   }

   public static ImmutableRangeMap copyOf(RangeMap rangeMap) {
      if (rangeMap instanceof ImmutableRangeMap) {
         return (ImmutableRangeMap)rangeMap;
      } else {
         Map<Range<K>, ? extends V> map = rangeMap.asMapOfRanges();
         ImmutableList.Builder<Range<K>> rangesBuilder = new ImmutableList.Builder(map.size());
         ImmutableList.Builder<V> valuesBuilder = new ImmutableList.Builder(map.size());

         for(Map.Entry entry : map.entrySet()) {
            rangesBuilder.add((Object)((Range)entry.getKey()));
            valuesBuilder.add(entry.getValue());
         }

         return new ImmutableRangeMap(rangesBuilder.build(), valuesBuilder.build());
      }
   }

   public static Builder builder() {
      return new Builder();
   }

   ImmutableRangeMap(ImmutableList ranges, ImmutableList values) {
      this.ranges = ranges;
      this.values = values;
   }

   @CheckForNull
   public Object get(Comparable key) {
      int index = SortedLists.binarySearch(this.ranges, (com.google.common.base.Function)(Range::lowerBound), (Comparable)Cut.belowValue(key), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
      if (index == -1) {
         return null;
      } else {
         Range<K> range = (Range)this.ranges.get(index);
         return range.contains(key) ? this.values.get(index) : null;
      }
   }

   @CheckForNull
   public Map.Entry getEntry(Comparable key) {
      int index = SortedLists.binarySearch(this.ranges, (com.google.common.base.Function)(Range::lowerBound), (Comparable)Cut.belowValue(key), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
      if (index == -1) {
         return null;
      } else {
         Range<K> range = (Range)this.ranges.get(index);
         return range.contains(key) ? Maps.immutableEntry(range, this.values.get(index)) : null;
      }
   }

   public Range span() {
      if (this.ranges.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         Range<K> firstRange = (Range)this.ranges.get(0);
         Range<K> lastRange = (Range)this.ranges.get(this.ranges.size() - 1);
         return Range.create(firstRange.lowerBound, lastRange.upperBound);
      }
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void put(Range range, Object value) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void putCoalescing(Range range, Object value) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void putAll(RangeMap rangeMap) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void clear() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void remove(Range range) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void merge(Range range, @CheckForNull Object value, BiFunction remappingFunction) {
      throw new UnsupportedOperationException();
   }

   public ImmutableMap asMapOfRanges() {
      if (this.ranges.isEmpty()) {
         return ImmutableMap.of();
      } else {
         RegularImmutableSortedSet<Range<K>> rangeSet = new RegularImmutableSortedSet(this.ranges, Range.rangeLexOrdering());
         return new ImmutableSortedMap(rangeSet, this.values);
      }
   }

   public ImmutableMap asDescendingMapOfRanges() {
      if (this.ranges.isEmpty()) {
         return ImmutableMap.of();
      } else {
         RegularImmutableSortedSet<Range<K>> rangeSet = new RegularImmutableSortedSet(this.ranges.reverse(), Range.rangeLexOrdering().reverse());
         return new ImmutableSortedMap(rangeSet, this.values.reverse());
      }
   }

   public ImmutableRangeMap subRangeMap(final Range range) {
      if (((Range)Preconditions.checkNotNull(range)).isEmpty()) {
         return of();
      } else if (!this.ranges.isEmpty() && !range.encloses(this.span())) {
         final int lowerIndex = SortedLists.binarySearch(this.ranges, (com.google.common.base.Function)(Range::upperBound), (Comparable)range.lowerBound, SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
         int upperIndex = SortedLists.binarySearch(this.ranges, (com.google.common.base.Function)(Range::lowerBound), (Comparable)range.upperBound, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
         if (lowerIndex >= upperIndex) {
            return of();
         } else {
            final int len = upperIndex - lowerIndex;
            ImmutableList<Range<K>> subRanges = new ImmutableList() {
               public int size() {
                  return len;
               }

               public Range get(int index) {
                  Preconditions.checkElementIndex(index, len);
                  return index != 0 && index != len - 1 ? (Range)ImmutableRangeMap.this.ranges.get(index + lowerIndex) : ((Range)ImmutableRangeMap.this.ranges.get(index + lowerIndex)).intersection(range);
               }

               boolean isPartialView() {
                  return true;
               }

               @J2ktIncompatible
               Object writeReplace() {
                  return super.writeReplace();
               }
            };
            return new ImmutableRangeMap(subRanges, this.values.subList(lowerIndex, upperIndex)) {
               public ImmutableRangeMap subRangeMap(Range subRange) {
                  return range.isConnected(subRange) ? ImmutableRangeMap.this.subRangeMap(subRange.intersection(range)) : ImmutableRangeMap.of();
               }

               @J2ktIncompatible
               Object writeReplace() {
                  return super.writeReplace();
               }
            };
         }
      } else {
         return this;
      }
   }

   public int hashCode() {
      return this.asMapOfRanges().hashCode();
   }

   public boolean equals(@CheckForNull Object o) {
      if (o instanceof RangeMap) {
         RangeMap<?, ?> rangeMap = (RangeMap)o;
         return this.asMapOfRanges().equals(rangeMap.asMapOfRanges());
      } else {
         return false;
      }
   }

   public String toString() {
      return this.asMapOfRanges().toString();
   }

   Object writeReplace() {
      return new SerializedForm(this.asMapOfRanges());
   }

   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   @DoNotMock
   public static final class Builder {
      private final List entries = Lists.newArrayList();

      @CanIgnoreReturnValue
      public Builder put(Range range, Object value) {
         Preconditions.checkNotNull(range);
         Preconditions.checkNotNull(value);
         Preconditions.checkArgument(!range.isEmpty(), "Range must not be empty, but was %s", (Object)range);
         this.entries.add(Maps.immutableEntry(range, value));
         return this;
      }

      @CanIgnoreReturnValue
      public Builder putAll(RangeMap rangeMap) {
         for(Map.Entry entry : rangeMap.asMapOfRanges().entrySet()) {
            this.put((Range)entry.getKey(), entry.getValue());
         }

         return this;
      }

      @CanIgnoreReturnValue
      Builder combine(Builder builder) {
         this.entries.addAll(builder.entries);
         return this;
      }

      public ImmutableRangeMap build() {
         Collections.sort(this.entries, Range.rangeLexOrdering().onKeys());
         ImmutableList.Builder<Range<K>> rangesBuilder = new ImmutableList.Builder(this.entries.size());
         ImmutableList.Builder<V> valuesBuilder = new ImmutableList.Builder(this.entries.size());

         for(int i = 0; i < this.entries.size(); ++i) {
            Range<K> range = (Range)((Map.Entry)this.entries.get(i)).getKey();
            if (i > 0) {
               Range<K> prevRange = (Range)((Map.Entry)this.entries.get(i - 1)).getKey();
               if (range.isConnected(prevRange) && !range.intersection(prevRange).isEmpty()) {
                  throw new IllegalArgumentException("Overlapping ranges: range " + prevRange + " overlaps with entry " + range);
               }
            }

            rangesBuilder.add((Object)range);
            valuesBuilder.add(((Map.Entry)this.entries.get(i)).getValue());
         }

         return new ImmutableRangeMap(rangesBuilder.build(), valuesBuilder.build());
      }
   }

   private static class SerializedForm implements Serializable {
      private final ImmutableMap mapOfRanges;
      private static final long serialVersionUID = 0L;

      SerializedForm(ImmutableMap mapOfRanges) {
         this.mapOfRanges = mapOfRanges;
      }

      Object readResolve() {
         return this.mapOfRanges.isEmpty() ? ImmutableRangeMap.of() : this.createRangeMap();
      }

      Object createRangeMap() {
         Builder<K, V> builder = new Builder();

         for(Map.Entry entry : this.mapOfRanges.entrySet()) {
            builder.put((Range)entry.getKey(), entry.getValue());
         }

         return builder.build();
      }
   }
}
