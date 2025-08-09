package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.DoNotMock;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;

@DoNotMock("Use ImmutableRangeSet or TreeRangeSet")
@ElementTypesAreNonnullByDefault
@GwtIncompatible
public interface RangeSet {
   boolean contains(Comparable value);

   @CheckForNull
   Range rangeContaining(Comparable value);

   boolean intersects(Range otherRange);

   boolean encloses(Range otherRange);

   boolean enclosesAll(RangeSet other);

   default boolean enclosesAll(Iterable other) {
      for(Range range : other) {
         if (!this.encloses(range)) {
            return false;
         }
      }

      return true;
   }

   boolean isEmpty();

   Range span();

   Set asRanges();

   Set asDescendingSetOfRanges();

   RangeSet complement();

   RangeSet subRangeSet(Range view);

   void add(Range range);

   void remove(Range range);

   void clear();

   void addAll(RangeSet other);

   default void addAll(Iterable ranges) {
      for(Range range : ranges) {
         this.add(range);
      }

   }

   void removeAll(RangeSet other);

   default void removeAll(Iterable ranges) {
      for(Range range : ranges) {
         this.remove(range);
      }

   }

   boolean equals(@CheckForNull Object obj);

   int hashCode();

   String toString();
}
