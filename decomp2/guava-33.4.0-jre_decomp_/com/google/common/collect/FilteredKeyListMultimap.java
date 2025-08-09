package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import java.util.List;
import javax.annotation.CheckForNull;

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
