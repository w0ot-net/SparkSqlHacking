package org.apache.curator.shaded.com.google.common.collect;

import java.util.Comparator;
import java.util.Iterator;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
interface SortedIterable extends Iterable {
   Comparator comparator();

   Iterator iterator();
}
