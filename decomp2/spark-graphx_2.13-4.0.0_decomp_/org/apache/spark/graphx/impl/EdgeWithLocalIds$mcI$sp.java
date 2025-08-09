package org.apache.spark.graphx.impl;

public class EdgeWithLocalIds$mcI$sp extends EdgeWithLocalIds {
   public final int attr$mcI$sp;

   public int attr$mcI$sp() {
      return this.attr$mcI$sp;
   }

   public int attr() {
      return this.attr$mcI$sp();
   }

   public int copy$default$5() {
      return this.copy$default$5$mcI$sp();
   }

   public int copy$default$5$mcI$sp() {
      return this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public EdgeWithLocalIds$mcI$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final int attr$mcI$sp) {
      super(srcId, dstId, localSrcId, localDstId, (Object)null);
      this.attr$mcI$sp = attr$mcI$sp;
   }
}
