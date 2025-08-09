package org.apache.commons.lang3.compare;

import java.util.function.Predicate;
import org.apache.commons.lang3.ObjectUtils;

public class ComparableUtils {
   public static Predicate between(Comparable b, Comparable c) {
      return (a) -> is(a).between(b, c);
   }

   public static Predicate betweenExclusive(Comparable b, Comparable c) {
      return (a) -> is(a).betweenExclusive(b, c);
   }

   public static Predicate ge(Comparable b) {
      return (a) -> is(a).greaterThanOrEqualTo(b);
   }

   public static Predicate gt(Comparable b) {
      return (a) -> is(a).greaterThan(b);
   }

   public static ComparableCheckBuilder is(Comparable a) {
      return new ComparableCheckBuilder(a);
   }

   public static Predicate le(Comparable b) {
      return (a) -> is(a).lessThanOrEqualTo(b);
   }

   public static Predicate lt(Comparable b) {
      return (a) -> is(a).lessThan(b);
   }

   public static Comparable max(Comparable comparable1, Comparable comparable2) {
      return ObjectUtils.compare(comparable1, comparable2, false) > 0 ? comparable1 : comparable2;
   }

   public static Comparable min(Comparable comparable1, Comparable comparable2) {
      return ObjectUtils.compare(comparable1, comparable2, true) < 0 ? comparable1 : comparable2;
   }

   private ComparableUtils() {
   }

   public static class ComparableCheckBuilder {
      private final Comparable a;

      private ComparableCheckBuilder(Comparable a) {
         this.a = a;
      }

      public boolean between(Comparable b, Comparable c) {
         return this.betweenOrdered(b, c) || this.betweenOrdered(c, b);
      }

      public boolean betweenExclusive(Comparable b, Comparable c) {
         return this.betweenOrderedExclusive(b, c) || this.betweenOrderedExclusive(c, b);
      }

      private boolean betweenOrdered(Comparable b, Comparable c) {
         return this.greaterThanOrEqualTo(b) && this.lessThanOrEqualTo(c);
      }

      private boolean betweenOrderedExclusive(Comparable b, Comparable c) {
         return this.greaterThan(b) && this.lessThan(c);
      }

      public boolean equalTo(Comparable b) {
         return this.a.compareTo(b) == 0;
      }

      public boolean greaterThan(Comparable b) {
         return this.a.compareTo(b) > 0;
      }

      public boolean greaterThanOrEqualTo(Comparable b) {
         return this.a.compareTo(b) >= 0;
      }

      public boolean lessThan(Comparable b) {
         return this.a.compareTo(b) < 0;
      }

      public boolean lessThanOrEqualTo(Comparable b) {
         return this.a.compareTo(b) <= 0;
      }
   }
}
