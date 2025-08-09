package org.apache.spark.graphx.impl;

import scala.reflect.ClassTag;
import scala.runtime.Statics;

public abstract class VertexPartitionBase$mcJ$sp extends VertexPartitionBase {
   public long apply(final long vid) {
      return this.apply$mcJ$sp(vid);
   }

   public long apply$mcJ$sp(final long vid) {
      return this.values$mcJ$sp()[this.index().getPos$mcJ$sp(vid)];
   }

   public VertexPartitionBase$mcJ$sp(final ClassTag evidence$3) {
      super(evidence$3);
      Statics.releaseFence();
   }
}
