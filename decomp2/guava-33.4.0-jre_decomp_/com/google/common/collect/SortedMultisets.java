package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.j2objc.annotations.Weak;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
final class SortedMultisets {
   private SortedMultisets() {
   }

   private static Object getElementOrThrow(@CheckForNull Multiset.Entry entry) {
      if (entry == null) {
         throw new NoSuchElementException();
      } else {
         return entry.getElement();
      }
   }

   @CheckForNull
   private static Object getElementOrNull(@CheckForNull Multiset.Entry entry) {
      return entry == null ? null : entry.getElement();
   }

   static class ElementSet extends Multisets.ElementSet implements SortedSet {
      @Weak
      private final SortedMultiset multiset;

      ElementSet(SortedMultiset multiset) {
         this.multiset = multiset;
      }

      final SortedMultiset multiset() {
         return this.multiset;
      }

      public Iterator iterator() {
         return Multisets.elementIterator(this.multiset().entrySet().iterator());
      }

      public Comparator comparator() {
         return this.multiset().comparator();
      }

      public SortedSet subSet(@ParametricNullness Object fromElement, @ParametricNullness Object toElement) {
         return this.multiset().subMultiset(fromElement, BoundType.CLOSED, toElement, BoundType.OPEN).elementSet();
      }

      public SortedSet headSet(@ParametricNullness Object toElement) {
         return this.multiset().headMultiset(toElement, BoundType.OPEN).elementSet();
      }

      public SortedSet tailSet(@ParametricNullness Object fromElement) {
         return this.multiset().tailMultiset(fromElement, BoundType.CLOSED).elementSet();
      }

      @ParametricNullness
      public Object first() {
         return SortedMultisets.getElementOrThrow(this.multiset().firstEntry());
      }

      @ParametricNullness
      public Object last() {
         return SortedMultisets.getElementOrThrow(this.multiset().lastEntry());
      }
   }

   @GwtIncompatible
   static class NavigableElementSet extends ElementSet implements NavigableSet {
      NavigableElementSet(SortedMultiset multiset) {
         super(multiset);
      }

      @CheckForNull
      public Object lower(@ParametricNullness Object e) {
         return SortedMultisets.getElementOrNull(this.multiset().headMultiset(e, BoundType.OPEN).lastEntry());
      }

      @CheckForNull
      public Object floor(@ParametricNullness Object e) {
         return SortedMultisets.getElementOrNull(this.multiset().headMultiset(e, BoundType.CLOSED).lastEntry());
      }

      @CheckForNull
      public Object ceiling(@ParametricNullness Object e) {
         return SortedMultisets.getElementOrNull(this.multiset().tailMultiset(e, BoundType.CLOSED).firstEntry());
      }

      @CheckForNull
      public Object higher(@ParametricNullness Object e) {
         return SortedMultisets.getElementOrNull(this.multiset().tailMultiset(e, BoundType.OPEN).firstEntry());
      }

      public NavigableSet descendingSet() {
         return new NavigableElementSet(this.multiset().descendingMultiset());
      }

      public Iterator descendingIterator() {
         return this.descendingSet().iterator();
      }

      @CheckForNull
      public Object pollFirst() {
         return SortedMultisets.getElementOrNull(this.multiset().pollFirstEntry());
      }

      @CheckForNull
      public Object pollLast() {
         return SortedMultisets.getElementOrNull(this.multiset().pollLastEntry());
      }

      public NavigableSet subSet(@ParametricNullness Object fromElement, boolean fromInclusive, @ParametricNullness Object toElement, boolean toInclusive) {
         return new NavigableElementSet(this.multiset().subMultiset(fromElement, BoundType.forBoolean(fromInclusive), toElement, BoundType.forBoolean(toInclusive)));
      }

      public NavigableSet headSet(@ParametricNullness Object toElement, boolean inclusive) {
         return new NavigableElementSet(this.multiset().headMultiset(toElement, BoundType.forBoolean(inclusive)));
      }

      public NavigableSet tailSet(@ParametricNullness Object fromElement, boolean inclusive) {
         return new NavigableElementSet(this.multiset().tailMultiset(fromElement, BoundType.forBoolean(inclusive)));
      }
   }
}
