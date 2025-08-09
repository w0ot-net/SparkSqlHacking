package org.apache.curator.shaded.com.google.common.collect;

import java.util.SortedMap;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface SortedMapDifference extends MapDifference {
   SortedMap entriesOnlyOnLeft();

   SortedMap entriesOnlyOnRight();

   SortedMap entriesInCommon();

   SortedMap entriesDiffering();
}
