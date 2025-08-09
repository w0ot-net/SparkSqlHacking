package org.apache.curator.shaded.com.google.common.collect;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface SortedSetMultimap extends SetMultimap {
   SortedSet get(@ParametricNullness Object key);

   @CanIgnoreReturnValue
   SortedSet removeAll(@CheckForNull Object key);

   @CanIgnoreReturnValue
   SortedSet replaceValues(@ParametricNullness Object key, Iterable values);

   Map asMap();

   @CheckForNull
   Comparator valueComparator();
}
