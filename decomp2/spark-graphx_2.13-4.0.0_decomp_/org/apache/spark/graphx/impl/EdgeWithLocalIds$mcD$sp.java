package org.apache.spark.graphx.impl;

public class EdgeWithLocalIds$mcD$sp extends EdgeWithLocalIds {
   public final double attr$mcD$sp;

   public double attr$mcD$sp() {
      return this.attr$mcD$sp;
   }

   public double attr() {
      return this.attr$mcD$sp();
   }

   public double copy$default$5() {
      return this.copy$default$5$mcD$sp();
   }

   public double copy$default$5$mcD$sp() {
      return this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public EdgeWithLocalIds$mcD$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final double attr$mcD$sp) {
      super(srcId, dstId, localSrcId, localDstId, (Object)null);
      this.attr$mcD$sp = attr$mcD$sp;
   }
}
