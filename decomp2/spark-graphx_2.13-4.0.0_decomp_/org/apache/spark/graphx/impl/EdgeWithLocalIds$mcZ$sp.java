package org.apache.spark.graphx.impl;

public class EdgeWithLocalIds$mcZ$sp extends EdgeWithLocalIds {
   public final boolean attr$mcZ$sp;

   public boolean attr$mcZ$sp() {
      return this.attr$mcZ$sp;
   }

   public boolean attr() {
      return this.attr$mcZ$sp();
   }

   public boolean copy$default$5() {
      return this.copy$default$5$mcZ$sp();
   }

   public boolean copy$default$5$mcZ$sp() {
      return this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public EdgeWithLocalIds$mcZ$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final boolean attr$mcZ$sp) {
      super(srcId, dstId, localSrcId, localDstId, (Object)null);
      this.attr$mcZ$sp = attr$mcZ$sp;
   }
}
