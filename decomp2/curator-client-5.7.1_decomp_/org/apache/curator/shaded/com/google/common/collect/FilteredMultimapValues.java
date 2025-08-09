package org.apache.curator.shaded.com.google.common.collect;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Objects;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Predicate;
import org.apache.curator.shaded.com.google.common.base.Predicates;
import org.apache.curator.shaded.com.google.j2objc.annotations.Weak;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class FilteredMultimapValues extends AbstractCollection {
   @Weak
   private final FilteredMultimap multimap;

   FilteredMultimapValues(FilteredMultimap multimap) {
      this.multimap = (FilteredMultimap)Preconditions.checkNotNull(multimap);
   }

   public Iterator iterator() {
      return Maps.valueIterator(this.multimap.entries().iterator());
   }

   public boolean contains(@CheckForNull Object o) {
      return this.multimap.containsValue(o);
   }

   public int size() {
      return this.multimap.size();
   }

   public boolean remove(@CheckForNull Object o) {
      Predicate<? super Map.Entry<K, V>> entryPredicate = this.multimap.entryPredicate();
      Iterator<Map.Entry<K, V>> unfilteredItr = this.multimap.unfiltered().entries().iterator();

      while(unfilteredItr.hasNext()) {
         Map.Entry<K, V> entry = (Map.Entry)unfilteredItr.next();
         if (entryPredicate.apply(entry) && Objects.equal(entry.getValue(), o)) {
            unfilteredItr.remove();
            return true;
         }
      }

      return false;
   }

   public boolean removeAll(Collection c) {
      return Iterables.removeIf(this.multimap.unfiltered().entries(), Predicates.and(this.multimap.entryPredicate(), Maps.valuePredicateOnEntries(Predicates.in(c))));
   }

   public boolean retainAll(Collection c) {
      return Iterables.removeIf(this.multimap.unfiltered().entries(), Predicates.and(this.multimap.entryPredicate(), Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(c)))));
   }

   public void clear() {
      this.multimap.clear();
   }
}
