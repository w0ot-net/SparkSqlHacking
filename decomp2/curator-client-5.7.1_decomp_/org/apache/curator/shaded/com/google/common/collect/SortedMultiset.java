package org.apache.curator.shaded.com.google.common.collect;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public interface SortedMultiset extends SortedMultisetBridge, SortedIterable {
   Comparator comparator();

   @CheckForNull
   Multiset.Entry firstEntry();

   @CheckForNull
   Multiset.Entry lastEntry();

   @CheckForNull
   Multiset.Entry pollFirstEntry();

   @CheckForNull
   Multiset.Entry pollLastEntry();

   NavigableSet elementSet();

   Set entrySet();

   Iterator iterator();

   SortedMultiset descendingMultiset();

   SortedMultiset headMultiset(@ParametricNullness Object upperBound, BoundType boundType);

   SortedMultiset subMultiset(@ParametricNullness Object lowerBound, BoundType lowerBoundType, @ParametricNullness Object upperBound, BoundType upperBoundType);

   SortedMultiset tailMultiset(@ParametricNullness Object lowerBound, BoundType boundType);
}
