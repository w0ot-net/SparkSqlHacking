package org.apache.curator.shaded.com.google.common.collect;

import java.util.Comparator;
import java.util.Objects;
import java.util.Spliterator;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
final class ImmutableSortedAsList extends RegularImmutableAsList implements SortedIterable {
   ImmutableSortedAsList(ImmutableSortedSet backingSet, ImmutableList backingList) {
      super(backingSet, (ImmutableList)backingList);
   }

   ImmutableSortedSet delegateCollection() {
      return (ImmutableSortedSet)super.delegateCollection();
   }

   public Comparator comparator() {
      return this.delegateCollection().comparator();
   }

   @GwtIncompatible
   public int indexOf(@CheckForNull Object target) {
      int index = this.delegateCollection().indexOf(target);
      return index >= 0 && this.get(index).equals(target) ? index : -1;
   }

   @GwtIncompatible
   public int lastIndexOf(@CheckForNull Object target) {
      return this.indexOf(target);
   }

   public boolean contains(@CheckForNull Object target) {
      return this.indexOf(target) >= 0;
   }

   @GwtIncompatible
   ImmutableList subListUnchecked(int fromIndex, int toIndex) {
      ImmutableList<E> parentSubList = super.subListUnchecked(fromIndex, toIndex);
      return (new RegularImmutableSortedSet(parentSubList, this.comparator())).asList();
   }

   public Spliterator spliterator() {
      int var10000 = this.size();
      ImmutableList var10002 = this.delegateList();
      Objects.requireNonNull(var10002);
      return CollectSpliterators.indexed(var10000, 1301, var10002::get, this.comparator());
   }
}
