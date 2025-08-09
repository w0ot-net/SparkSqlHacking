package org.glassfish.jersey.internal.guava;

import java.util.Comparator;
import java.util.Iterator;

public abstract class Ordering implements Comparator {
   static final int LEFT_IS_GREATER = 1;
   static final int RIGHT_IS_GREATER = -1;

   Ordering() {
   }

   public static Ordering natural() {
      return NaturalOrdering.INSTANCE;
   }

   public static Ordering from(Comparator comparator) {
      return (Ordering)(comparator instanceof Ordering ? (Ordering)comparator : new ComparatorOrdering(comparator));
   }

   public Ordering reverse() {
      return new ReverseOrdering(this);
   }

   Ordering nullsFirst() {
      return new NullsFirstOrdering(this);
   }

   Ordering nullsLast() {
      return new NullsLastOrdering(this);
   }

   public abstract int compare(Object var1, Object var2);

   Object min(Iterator iterator) {
      E minSoFar;
      for(minSoFar = (E)iterator.next(); iterator.hasNext(); minSoFar = (E)this.min(minSoFar, iterator.next())) {
      }

      return minSoFar;
   }

   Object min(Iterable iterable) {
      return this.min(iterable.iterator());
   }

   Object min(Object a, Object b) {
      return this.compare(a, b) <= 0 ? a : b;
   }

   Object min(Object a, Object b, Object c, Object... rest) {
      E minSoFar = (E)this.min(this.min(a, b), c);

      for(Object r : rest) {
         minSoFar = (E)this.min(minSoFar, r);
      }

      return minSoFar;
   }

   Object max(Iterator iterator) {
      E maxSoFar;
      for(maxSoFar = (E)iterator.next(); iterator.hasNext(); maxSoFar = (E)this.max(maxSoFar, iterator.next())) {
      }

      return maxSoFar;
   }

   Object max(Iterable iterable) {
      return this.max(iterable.iterator());
   }

   Object max(Object a, Object b) {
      return this.compare(a, b) >= 0 ? a : b;
   }

   Object max(Object a, Object b, Object c, Object... rest) {
      E maxSoFar = (E)this.max(this.max(a, b), c);

      for(Object r : rest) {
         maxSoFar = (E)this.max(maxSoFar, r);
      }

      return maxSoFar;
   }
}
