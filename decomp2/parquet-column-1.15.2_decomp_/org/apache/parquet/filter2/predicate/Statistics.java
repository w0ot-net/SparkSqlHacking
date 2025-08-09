package org.apache.parquet.filter2.predicate;

import java.util.Comparator;
import java.util.Objects;

public class Statistics {
   private final Object min;
   private final Object max;
   private final Comparator comparator;

   /** @deprecated */
   @Deprecated
   public Statistics(Object min, Object max) {
      this.min = Objects.requireNonNull(min, "min cannot be null");
      this.max = Objects.requireNonNull(max, "max cannot be null");
      this.comparator = null;
   }

   public Statistics(Object min, Object max, Comparator comparator) {
      this.min = Objects.requireNonNull(min, "min cannot be null");
      this.max = Objects.requireNonNull(max, "max cannot be null");
      this.comparator = (Comparator)Objects.requireNonNull(comparator, "comparator cannot be null");
   }

   public Object getMin() {
      return this.min;
   }

   public Object getMax() {
      return this.max;
   }

   public Comparator getComparator() {
      return this.comparator;
   }
}
