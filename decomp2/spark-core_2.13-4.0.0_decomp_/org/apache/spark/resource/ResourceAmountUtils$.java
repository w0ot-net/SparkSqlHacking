package org.apache.spark.resource;

public final class ResourceAmountUtils$ {
   public static final ResourceAmountUtils$ MODULE$ = new ResourceAmountUtils$();
   private static final long ONE_ENTIRE_RESOURCE = 10000000000000000L;

   public final long ONE_ENTIRE_RESOURCE() {
      return ONE_ENTIRE_RESOURCE;
   }

   public boolean isOneEntireResource(final long amount) {
      return amount == this.ONE_ENTIRE_RESOURCE();
   }

   public long toInternalResource(final double amount) {
      return (long)(amount * (double)this.ONE_ENTIRE_RESOURCE());
   }

   public double toFractionalResource(final long amount) {
      return (double)amount / (double)this.ONE_ENTIRE_RESOURCE();
   }

   private ResourceAmountUtils$() {
   }
}
