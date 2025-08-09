package org.apache.curator.shaded.com.google.common.collect;

import java.util.SortedMap;
import java.util.SortedSet;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface RowSortedTable extends Table {
   SortedSet rowKeySet();

   SortedMap rowMap();
}
