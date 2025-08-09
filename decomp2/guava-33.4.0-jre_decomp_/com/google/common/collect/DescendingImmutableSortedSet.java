package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
final class DescendingImmutableSortedSet extends ImmutableSortedSet {
   private final ImmutableSortedSet forward;

   DescendingImmutableSortedSet(ImmutableSortedSet forward) {
      super(Ordering.from(forward.comparator()).reverse());
      this.forward = forward;
   }

   public boolean contains(@CheckForNull Object object) {
      return this.forward.contains(object);
   }

   public int size() {
      return this.forward.size();
   }

   public UnmodifiableIterator iterator() {
      return this.forward.descendingIterator();
   }

   ImmutableSortedSet headSetImpl(Object toElement, boolean inclusive) {
      return this.forward.tailSet(toElement, inclusive).descendingSet();
   }

   ImmutableSortedSet subSetImpl(Object fromElement, boolean fromInclusive, Object toElement, boolean toInclusive) {
      return this.forward.subSet(toElement, toInclusive, fromElement, fromInclusive).descendingSet();
   }

   ImmutableSortedSet tailSetImpl(Object fromElement, boolean inclusive) {
      return this.forward.headSet(fromElement, inclusive).descendingSet();
   }

   @GwtIncompatible("NavigableSet")
   public ImmutableSortedSet descendingSet() {
      return this.forward;
   }

   @GwtIncompatible("NavigableSet")
   public UnmodifiableIterator descendingIterator() {
      return this.forward.iterator();
   }

   @GwtIncompatible("NavigableSet")
   ImmutableSortedSet createDescendingSet() {
      throw new AssertionError("should never be called");
   }

   @CheckForNull
   public Object lower(Object element) {
      return this.forward.higher(element);
   }

   @CheckForNull
   public Object floor(Object element) {
      return this.forward.ceiling(element);
   }

   @CheckForNull
   public Object ceiling(Object element) {
      return this.forward.floor(element);
   }

   @CheckForNull
   public Object higher(Object element) {
      return this.forward.lower(element);
   }

   int indexOf(@CheckForNull Object target) {
      int index = this.forward.indexOf(target);
      return index == -1 ? index : this.size() - 1 - index;
   }

   boolean isPartialView() {
      return this.forward.isPartialView();
   }

   @J2ktIncompatible
   Object writeReplace() {
      return super.writeReplace();
   }
}
