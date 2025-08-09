package org.apache.curator.shaded.com.google.common.collect;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
abstract class DescendingMultiset extends ForwardingMultiset implements SortedMultiset {
   @CheckForNull
   private transient Comparator comparator;
   @CheckForNull
   private transient NavigableSet elementSet;
   @CheckForNull
   private transient Set entrySet;

   abstract SortedMultiset forwardMultiset();

   public Comparator comparator() {
      Comparator<? super E> result = this.comparator;
      return result == null ? (this.comparator = Ordering.from(this.forwardMultiset().comparator()).reverse()) : result;
   }

   public NavigableSet elementSet() {
      NavigableSet<E> result = this.elementSet;
      return result == null ? (this.elementSet = new SortedMultisets.NavigableElementSet(this)) : result;
   }

   @CheckForNull
   public Multiset.Entry pollFirstEntry() {
      return this.forwardMultiset().pollLastEntry();
   }

   @CheckForNull
   public Multiset.Entry pollLastEntry() {
      return this.forwardMultiset().pollFirstEntry();
   }

   public SortedMultiset headMultiset(@ParametricNullness Object toElement, BoundType boundType) {
      return this.forwardMultiset().tailMultiset(toElement, boundType).descendingMultiset();
   }

   public SortedMultiset subMultiset(@ParametricNullness Object fromElement, BoundType fromBoundType, @ParametricNullness Object toElement, BoundType toBoundType) {
      return this.forwardMultiset().subMultiset(toElement, toBoundType, fromElement, fromBoundType).descendingMultiset();
   }

   public SortedMultiset tailMultiset(@ParametricNullness Object fromElement, BoundType boundType) {
      return this.forwardMultiset().headMultiset(fromElement, boundType).descendingMultiset();
   }

   protected Multiset delegate() {
      return this.forwardMultiset();
   }

   public SortedMultiset descendingMultiset() {
      return this.forwardMultiset();
   }

   @CheckForNull
   public Multiset.Entry firstEntry() {
      return this.forwardMultiset().lastEntry();
   }

   @CheckForNull
   public Multiset.Entry lastEntry() {
      return this.forwardMultiset().firstEntry();
   }

   abstract Iterator entryIterator();

   public Set entrySet() {
      Set<Multiset.Entry<E>> result = this.entrySet;
      return result == null ? (this.entrySet = this.createEntrySet()) : result;
   }

   Set createEntrySet() {
      class EntrySetImpl extends Multisets.EntrySet {
         Multiset multiset() {
            return DescendingMultiset.this;
         }

         public Iterator iterator() {
            return DescendingMultiset.this.entryIterator();
         }

         public int size() {
            return DescendingMultiset.this.forwardMultiset().entrySet().size();
         }
      }

      return new EntrySetImpl();
   }

   public Iterator iterator() {
      return Multisets.iteratorImpl(this);
   }

   public @Nullable Object[] toArray() {
      return this.standardToArray();
   }

   public Object[] toArray(Object[] array) {
      return this.standardToArray(array);
   }

   public String toString() {
      return this.entrySet().toString();
   }
}
