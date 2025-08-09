package org.apache.spark.graphx.impl;

public class EdgeWithLocalIds$mcB$sp extends EdgeWithLocalIds {
   public final byte attr$mcB$sp;

   public byte attr$mcB$sp() {
      return this.attr$mcB$sp;
   }

   public byte attr() {
      return this.attr$mcB$sp();
   }

   public byte copy$default$5() {
      return this.copy$default$5$mcB$sp();
   }

   public byte copy$default$5$mcB$sp() {
      return this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public EdgeWithLocalIds$mcB$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final byte attr$mcB$sp) {
      super(srcId, dstId, localSrcId, localDstId, (Object)null);
      this.attr$mcB$sp = attr$mcB$sp;
   }
}
