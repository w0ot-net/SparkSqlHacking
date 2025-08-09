package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
final class EmptyContiguousSet extends ContiguousSet {
   EmptyContiguousSet(DiscreteDomain domain) {
      super(domain);
   }

   public Comparable first() {
      throw new NoSuchElementException();
   }

   public Comparable last() {
      throw new NoSuchElementException();
   }

   public int size() {
      return 0;
   }

   public ContiguousSet intersection(ContiguousSet other) {
      return this;
   }

   public Range range() {
      throw new NoSuchElementException();
   }

   public Range range(BoundType lowerBoundType, BoundType upperBoundType) {
      throw new NoSuchElementException();
   }

   ContiguousSet headSetImpl(Comparable toElement, boolean inclusive) {
      return this;
   }

   ContiguousSet subSetImpl(Comparable fromElement, boolean fromInclusive, Comparable toElement, boolean toInclusive) {
      return this;
   }

   ContiguousSet tailSetImpl(Comparable fromElement, boolean fromInclusive) {
      return this;
   }

   public boolean contains(@CheckForNull Object object) {
      return false;
   }

   @GwtIncompatible
   int indexOf(@CheckForNull Object target) {
      return -1;
   }

   public UnmodifiableIterator iterator() {
      return Iterators.emptyIterator();
   }

   @GwtIncompatible
   public UnmodifiableIterator descendingIterator() {
      return Iterators.emptyIterator();
   }

   boolean isPartialView() {
      return false;
   }

   public boolean isEmpty() {
      return true;
   }

   public ImmutableList asList() {
      return ImmutableList.of();
   }

   public String toString() {
      return "[]";
   }

   public boolean equals(@CheckForNull Object object) {
      if (object instanceof Set) {
         Set<?> that = (Set)object;
         return that.isEmpty();
      } else {
         return false;
      }
   }

   @GwtIncompatible
   boolean isHashCodeFast() {
      return true;
   }

   public int hashCode() {
      return 0;
   }

   @GwtIncompatible
   @J2ktIncompatible
   Object writeReplace() {
      return new SerializedForm(this.domain);
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   @GwtIncompatible
   ImmutableSortedSet createDescendingSet() {
      return ImmutableSortedSet.emptySet(Ordering.natural().reverse());
   }

   @GwtIncompatible
   @J2ktIncompatible
   private static final class SerializedForm implements Serializable {
      private final DiscreteDomain domain;
      private static final long serialVersionUID = 0L;

      private SerializedForm(DiscreteDomain domain) {
         this.domain = domain;
      }

      private Object readResolve() {
         return new EmptyContiguousSet(this.domain);
      }
   }
}
