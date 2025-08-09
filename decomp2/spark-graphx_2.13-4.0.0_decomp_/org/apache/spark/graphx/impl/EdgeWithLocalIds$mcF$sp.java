package org.apache.spark.graphx.impl;

public class EdgeWithLocalIds$mcF$sp extends EdgeWithLocalIds {
   public final float attr$mcF$sp;

   public float attr$mcF$sp() {
      return this.attr$mcF$sp;
   }

   public float attr() {
      return this.attr$mcF$sp();
   }

   public float copy$default$5() {
      return this.copy$default$5$mcF$sp();
   }

   public float copy$default$5$mcF$sp() {
      return this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public EdgeWithLocalIds$mcF$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final float attr$mcF$sp) {
      super(srcId, dstId, localSrcId, localDstId, (Object)null);
      this.attr$mcF$sp = attr$mcF$sp;
   }
}
