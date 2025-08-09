package org.apache.curator.shaded.com.google.common.collect;

import java.util.List;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Predicate;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class FilteredKeyListMultimap extends FilteredKeyMultimap implements ListMultimap {
   FilteredKeyListMultimap(ListMultimap unfiltered, Predicate keyPredicate) {
      super(unfiltered, keyPredicate);
   }

   public ListMultimap unfiltered() {
      return (ListMultimap)super.unfiltered();
   }

   public List get(@ParametricNullness Object key) {
      return (List)super.get(key);
   }

   public List removeAll(@CheckForNull Object key) {
      return (List)super.removeAll(key);
   }

   public List replaceValues(@ParametricNullness Object key, Iterable values) {
      return (List)super.replaceValues(key, values);
   }
}
