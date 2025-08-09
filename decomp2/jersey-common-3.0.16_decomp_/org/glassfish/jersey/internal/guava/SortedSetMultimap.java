package org.glassfish.jersey.internal.guava;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;

public interface SortedSetMultimap extends SetMultimap {
   SortedSet get(Object var1);

   SortedSet removeAll(Object var1);

   Map asMap();

   Comparator valueComparator();
}
