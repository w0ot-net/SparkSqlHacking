package org.glassfish.jersey.model.internal;

import java.util.Comparator;

public class RankedComparator implements Comparator {
   private final Order order;

   public RankedComparator() {
      this(RankedComparator.Order.ASCENDING);
   }

   public RankedComparator(Order order) {
      this.order = order;
   }

   public int compare(RankedProvider o1, RankedProvider o2) {
      return this.getPriority(o1) > this.getPriority(o2) ? this.order.ordering : -this.order.ordering;
   }

   protected int getPriority(RankedProvider rankedProvider) {
      return rankedProvider.getRank();
   }

   public static enum Order {
      ASCENDING(1),
      DESCENDING(-1);

      private final int ordering;

      private Order(int ordering) {
         this.ordering = ordering;
      }
   }
}
