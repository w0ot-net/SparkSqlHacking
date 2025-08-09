package org.sparkproject.guava.collect;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public abstract class ForwardingNavigableSet extends ForwardingSortedSet implements NavigableSet {
   protected ForwardingNavigableSet() {
   }

   protected abstract NavigableSet delegate();

   @CheckForNull
   public Object lower(@ParametricNullness Object e) {
      return this.delegate().lower(e);
   }

   @CheckForNull
   protected Object standardLower(@ParametricNullness Object e) {
      return Iterators.getNext(this.headSet(e, false).descendingIterator(), (Object)null);
   }

   @CheckForNull
   public Object floor(@ParametricNullness Object e) {
      return this.delegate().floor(e);
   }

   @CheckForNull
   protected Object standardFloor(@ParametricNullness Object e) {
      return Iterators.getNext(this.headSet(e, true).descendingIterator(), (Object)null);
   }

   @CheckForNull
   public Object ceiling(@ParametricNullness Object e) {
      return this.delegate().ceiling(e);
   }

   @CheckForNull
   protected Object standardCeiling(@ParametricNullness Object e) {
      return Iterators.getNext(this.tailSet(e, true).iterator(), (Object)null);
   }

   @CheckForNull
   public Object higher(@ParametricNullness Object e) {
      return this.delegate().higher(e);
   }

   @CheckForNull
   protected Object standardHigher(@ParametricNullness Object e) {
      return Iterators.getNext(this.tailSet(e, false).iterator(), (Object)null);
   }

   @CheckForNull
   public Object pollFirst() {
      return this.delegate().pollFirst();
   }

   @CheckForNull
   protected Object standardPollFirst() {
      return Iterators.pollNext(this.iterator());
   }

   @CheckForNull
   public Object pollLast() {
      return this.delegate().pollLast();
   }

   @CheckForNull
   protected Object standardPollLast() {
      return Iterators.pollNext(this.descendingIterator());
   }

   @ParametricNullness
   protected Object standardFirst() {
      return this.iterator().next();
   }

   @ParametricNullness
   protected Object standardLast() {
      return this.descendingIterator().next();
   }

   public NavigableSet descendingSet() {
      return this.delegate().descendingSet();
   }

   public Iterator descendingIterator() {
      return this.delegate().descendingIterator();
   }

   public NavigableSet subSet(@ParametricNullness Object fromElement, boolean fromInclusive, @ParametricNullness Object toElement, boolean toInclusive) {
      return this.delegate().subSet(fromElement, fromInclusive, toElement, toInclusive);
   }

   protected NavigableSet standardSubSet(@ParametricNullness Object fromElement, boolean fromInclusive, @ParametricNullness Object toElement, boolean toInclusive) {
      return this.tailSet(fromElement, fromInclusive).headSet(toElement, toInclusive);
   }

   protected SortedSet standardSubSet(@ParametricNullness Object fromElement, @ParametricNullness Object toElement) {
      return this.subSet(fromElement, true, toElement, false);
   }

   public NavigableSet headSet(@ParametricNullness Object toElement, boolean inclusive) {
      return this.delegate().headSet(toElement, inclusive);
   }

   protected SortedSet standardHeadSet(@ParametricNullness Object toElement) {
      return this.headSet(toElement, false);
   }

   public NavigableSet tailSet(@ParametricNullness Object fromElement, boolean inclusive) {
      return this.delegate().tailSet(fromElement, inclusive);
   }

   protected SortedSet standardTailSet(@ParametricNullness Object fromElement) {
      return this.tailSet(fromElement, true);
   }

   protected class StandardDescendingSet extends Sets.DescendingSet {
      public StandardDescendingSet() {
         super(ForwardingNavigableSet.this);
      }
   }
}
