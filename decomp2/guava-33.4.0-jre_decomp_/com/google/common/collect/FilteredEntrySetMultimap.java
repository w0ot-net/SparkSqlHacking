package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class FilteredEntrySetMultimap extends FilteredEntryMultimap implements FilteredSetMultimap {
   FilteredEntrySetMultimap(SetMultimap unfiltered, Predicate predicate) {
      super(unfiltered, predicate);
   }

   public SetMultimap unfiltered() {
      return (SetMultimap)this.unfiltered;
   }

   public Set get(@ParametricNullness Object key) {
      return (Set)super.get(key);
   }

   public Set removeAll(@CheckForNull Object key) {
      return (Set)super.removeAll(key);
   }

   public Set replaceValues(@ParametricNullness Object key, Iterable values) {
      return (Set)super.replaceValues(key, values);
   }

   Set createEntries() {
      return Sets.filter(this.unfiltered().entries(), this.entryPredicate());
   }

   public Set entries() {
      return (Set)super.entries();
   }
}
