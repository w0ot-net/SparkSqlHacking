package org.apache.spark.util.collection;

public class OpenHashSet$Hasher$mcF$sp extends OpenHashSet.Hasher {
   public int hash(final float o) {
      return this.hash$mcF$sp(o);
   }

   public int hash$mcF$sp(final float o) {
      return Float.hashCode(o);
   }
}
