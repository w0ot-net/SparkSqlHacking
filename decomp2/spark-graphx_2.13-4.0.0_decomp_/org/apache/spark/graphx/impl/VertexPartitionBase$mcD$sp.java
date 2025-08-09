package org.apache.spark.graphx.impl;

import scala.reflect.ClassTag;
import scala.runtime.Statics;

public abstract class VertexPartitionBase$mcD$sp extends VertexPartitionBase {
   public double apply(final long vid) {
      return this.apply$mcD$sp(vid);
   }

   public double apply$mcD$sp(final long vid) {
      return this.values$mcD$sp()[this.index().getPos$mcJ$sp(vid)];
   }

   public VertexPartitionBase$mcD$sp(final ClassTag evidence$3) {
      super(evidence$3);
      Statics.releaseFence();
   }
}
