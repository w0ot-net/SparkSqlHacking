package org.apache.spark.util.collection;

public class OpenHashSet$Hasher$mcD$sp extends OpenHashSet.Hasher {
   public int hash(final double o) {
      return this.hash$mcD$sp(o);
   }

   public int hash$mcD$sp(final double o) {
      return Double.hashCode(o);
   }
}
