package org.apache.spark.graphx.impl;

import scala.reflect.ClassTag;
import scala.runtime.Statics;

public abstract class VertexPartitionBase$mcI$sp extends VertexPartitionBase {
   public int apply(final long vid) {
      return this.apply$mcI$sp(vid);
   }

   public int apply$mcI$sp(final long vid) {
      return this.values$mcI$sp()[this.index().getPos$mcJ$sp(vid)];
   }

   public VertexPartitionBase$mcI$sp(final ClassTag evidence$3) {
      super(evidence$3);
      Statics.releaseFence();
   }
}
