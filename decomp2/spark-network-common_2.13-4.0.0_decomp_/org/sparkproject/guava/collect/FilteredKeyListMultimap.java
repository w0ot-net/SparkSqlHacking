package org.sparkproject.guava.collect;

import java.util.List;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Predicate;

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
