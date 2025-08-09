package org.apache.curator.shaded.com.google.common.collect;

import java.util.Comparator;
import java.util.SortedSet;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingSortedSetMultimap extends ForwardingSetMultimap implements SortedSetMultimap {
   protected ForwardingSortedSetMultimap() {
   }

   protected abstract SortedSetMultimap delegate();

   public SortedSet get(@ParametricNullness Object key) {
      return this.delegate().get(key);
   }

   public SortedSet removeAll(@CheckForNull Object key) {
      return this.delegate().removeAll(key);
   }

   public SortedSet replaceValues(@ParametricNullness Object key, Iterable values) {
      return this.delegate().replaceValues(key, values);
   }

   @CheckForNull
   public Comparator valueComparator() {
      return this.delegate().valueComparator();
   }
}
