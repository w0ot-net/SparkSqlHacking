package org.sparkproject.guava.collect;

import java.util.Comparator;
import java.util.Iterator;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
interface SortedIterable extends Iterable {
   Comparator comparator();

   Iterator iterator();
}
