package org.apache.spark.graphx.impl;

public class EdgeWithLocalIds$mcJ$sp extends EdgeWithLocalIds {
   public final long attr$mcJ$sp;

   public long attr$mcJ$sp() {
      return this.attr$mcJ$sp;
   }

   public long attr() {
      return this.attr$mcJ$sp();
   }

   public long copy$default$5() {
      return this.copy$default$5$mcJ$sp();
   }

   public long copy$default$5$mcJ$sp() {
      return this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public EdgeWithLocalIds$mcJ$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final long attr$mcJ$sp) {
      super(srcId, dstId, localSrcId, localDstId, (Object)null);
      this.attr$mcJ$sp = attr$mcJ$sp;
   }
}
