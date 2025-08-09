package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;
import java.util.function.BiFunction;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;

@DoNotMock("Use ImmutableRangeMap or TreeRangeMap")
@ElementTypesAreNonnullByDefault
@GwtIncompatible
public interface RangeMap {
   @CheckForNull
   Object get(Comparable key);

   @CheckForNull
   Map.Entry getEntry(Comparable key);

   Range span();

   void put(Range range, Object value);

   void putCoalescing(Range range, Object value);

   void putAll(RangeMap rangeMap);

   void clear();

   void remove(Range range);

   void merge(Range range, @CheckForNull Object value, BiFunction remappingFunction);

   Map asMapOfRanges();

   Map asDescendingMapOfRanges();

   RangeMap subRangeMap(Range range);

   boolean equals(@CheckForNull Object o);

   int hashCode();

   String toString();
}
