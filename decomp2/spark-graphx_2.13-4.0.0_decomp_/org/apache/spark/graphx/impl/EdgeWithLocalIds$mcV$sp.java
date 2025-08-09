package org.apache.spark.graphx.impl;

import scala.runtime.BoxedUnit;

public class EdgeWithLocalIds$mcV$sp extends EdgeWithLocalIds {
   public final BoxedUnit attr$mcV$sp;

   public void attr$mcV$sp() {
   }

   public void attr() {
      this.attr$mcV$sp();
   }

   public void copy$default$5() {
      this.copy$default$5$mcV$sp();
   }

   public void copy$default$5$mcV$sp() {
      this.attr();
   }

   public boolean specInstance$() {
      return true;
   }

   public EdgeWithLocalIds$mcV$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final BoxedUnit attr$mcV$sp) {
      super(srcId, dstId, localSrcId, localDstId, BoxedUnit.UNIT);
      this.attr$mcV$sp = attr$mcV$sp;
   }
}
