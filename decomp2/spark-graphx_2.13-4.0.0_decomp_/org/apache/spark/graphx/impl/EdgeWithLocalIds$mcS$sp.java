package org.apache.spark.graphx.impl;

public class EdgeWithLocalIds$mcS$sp extends EdgeWithLocalIds {
   public final short attr$mcS$sp;

   public short attr$mcS$sp() {
      return this.attr$mcS$sp;
   }

   public short attr() {
      return this.attr$mcS$sp();
   }

   public short copy$default$5() {
      return this.copy$default$5$mcS$sp();
   }

   public short copy$default$5$mcS$sp() {
      return this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public EdgeWithLocalIds$mcS$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final short attr$mcS$sp) {
      super(srcId, dstId, localSrcId, localDstId, (Object)null);
      this.attr$mcS$sp = attr$mcS$sp;
   }
}
