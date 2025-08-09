package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

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
