package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class FilteredKeySetMultimap extends FilteredKeyMultimap implements FilteredSetMultimap {
   FilteredKeySetMultimap(SetMultimap unfiltered, Predicate keyPredicate) {
      super(unfiltered, keyPredicate);
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

   public Set entries() {
      return (Set)super.entries();
   }

   Set createEntries() {
      return new EntrySet();
   }

   class EntrySet extends FilteredKeyMultimap.Entries implements Set {
      public int hashCode() {
         return Sets.hashCodeImpl(this);
      }

      public boolean equals(@CheckForNull Object o) {
         return Sets.equalsImpl(this, o);
      }
   }
}
