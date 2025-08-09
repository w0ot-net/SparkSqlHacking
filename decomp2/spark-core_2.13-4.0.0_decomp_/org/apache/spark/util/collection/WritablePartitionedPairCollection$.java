package org.apache.spark.util.collection;

import java.util.Comparator;

public final class WritablePartitionedPairCollection$ {
   public static final WritablePartitionedPairCollection$ MODULE$ = new WritablePartitionedPairCollection$();

   public Comparator partitionComparator() {
      return (a, b) -> a._1$mcI$sp() - b._1$mcI$sp();
   }

   public Comparator partitionKeyComparator(final Comparator keyComparator) {
      return (a, b) -> {
         int partitionDiff = a._1$mcI$sp() - b._1$mcI$sp();
         return partitionDiff != 0 ? partitionDiff : keyComparator.compare(a._2(), b._2());
      };
   }

   private WritablePartitionedPairCollection$() {
   }
}
