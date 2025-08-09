package org.sparkproject.guava.collect;

import java.util.SortedMap;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface SortedMapDifference extends MapDifference {
   SortedMap entriesOnlyOnLeft();

   SortedMap entriesOnlyOnRight();

   SortedMap entriesInCommon();

   SortedMap entriesDiffering();
}
