package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.SortedMap;
import java.util.SortedSet;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface RowSortedTable extends Table {
   SortedSet rowKeySet();

   SortedMap rowMap();
}
