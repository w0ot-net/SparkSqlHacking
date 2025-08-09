package org.apache.spark.graphx.impl;

import org.apache.spark.util.collection.BitSet;
import org.apache.spark.util.collection.OpenHashSet;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014Qa\u0002\u0005\u0001\u0015IA\u0001B\u000b\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\tY\u0001\u0011\u0019\u0011)A\u0006[!)1\u0007\u0001C\u0001i!)\u0011\b\u0001C\u0001u!)A\t\u0001C\u0001\u000b\")A\u000b\u0001C\u0001+\nY2\u000b[5qa\u0006\u0014G.\u001a,feR,\u0007\u0010U1si&$\u0018n\u001c8PaNT!!\u0003\u0006\u0002\t%l\u0007\u000f\u001c\u0006\u0003\u00171\taa\u001a:ba\"D(BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0016\u0005MQ2C\u0001\u0001\u0015!\u0011)b\u0003G\u0014\u000e\u0003!I!a\u0006\u0005\u0003-Y+'\u000f^3y!\u0006\u0014H/\u001b;j_:\u0014\u0015m]3PaN\u0004\"!\u0007\u000e\r\u0001\u0011)1\u0004\u0001b\u0001;\t\u0011a\u000bR\u0002\u0001#\tqB\u0005\u0005\u0002 E5\t\u0001EC\u0001\"\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0003EA\u0004O_RD\u0017N\\4\u0011\u0005})\u0013B\u0001\u0014!\u0005\r\te.\u001f\t\u0003+!J!!\u000b\u0005\u00031MC\u0017\u000e\u001d9bE2,g+\u001a:uKb\u0004\u0016M\u001d;ji&|g.\u0001\u0003tK24\u0007cA\u000b)1\u0005QQM^5eK:\u001cW\r\n\u001d\u0011\u00079\n\u0004$D\u00010\u0015\t\u0001\u0004%A\u0004sK\u001adWm\u0019;\n\u0005Iz#\u0001C\"mCN\u001cH+Y4\u0002\rqJg.\u001b;?)\t)\u0004\b\u0006\u00027oA\u0019Q\u0003\u0001\r\t\u000b1\u001a\u00019A\u0017\t\u000b)\u001a\u0001\u0019A\u0016\u0002\u0013]LG\u000f[%oI\u0016DHCA\u0016<\u0011\u0015aD\u00011\u0001>\u0003\u0015Ig\u000eZ3y!\tq\u0014I\u0004\u0002\u0016\u007f%\u0011\u0001\tC\u0001\ba\u0006\u001c7.Y4f\u0013\t\u00115I\u0001\nWKJ$X\r_%e)>Le\u000eZ3y\u001b\u0006\u0004(B\u0001!\t\u0003)9\u0018\u000e\u001e5WC2,Xm]\u000b\u0003\r*#\"aR(\u0015\u0005!c\u0005cA\u000b)\u0013B\u0011\u0011D\u0013\u0003\u0006\u0017\u0016\u0011\r!\b\u0002\u0004-\u0012\u0013\u0004bB'\u0006\u0003\u0003\u0005\u001dAT\u0001\u000bKZLG-\u001a8dK\u0012J\u0004c\u0001\u00182\u0013\")\u0001+\u0002a\u0001#\u00061a/\u00197vKN\u00042a\b*J\u0013\t\u0019\u0006EA\u0003BeJ\f\u00170\u0001\u0005xSRDW*Y:l)\tYc\u000bC\u0003X\r\u0001\u0007\u0001,\u0001\u0003nCN\\\u0007CA-_\u001b\u0005Q&BA.]\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0003;2\tA!\u001e;jY&\u0011qL\u0017\u0002\u0007\u0005&$8+\u001a;"
)
public class ShippableVertexPartitionOps extends VertexPartitionBaseOps {
   private final ShippableVertexPartition self;
   private final ClassTag evidence$8;

   public ShippableVertexPartition withIndex(final OpenHashSet index) {
      return new ShippableVertexPartition(index, this.self.values(), this.self.mask(), this.self.routingTable(), this.evidence$8);
   }

   public ShippableVertexPartition withValues(final Object values, final ClassTag evidence$9) {
      return new ShippableVertexPartition(this.self.index(), values, this.self.mask(), this.self.routingTable(), evidence$9);
   }

   public ShippableVertexPartition withMask(final BitSet mask) {
      return new ShippableVertexPartition(this.self.index(), this.self.values(), mask, this.self.routingTable(), this.evidence$8);
   }

   public ShippableVertexPartitionOps(final ShippableVertexPartition self, final ClassTag evidence$8) {
      super(self, evidence$8, ShippableVertexPartition.ShippableVertexPartitionOpsConstructor$.MODULE$);
      this.self = self;
      this.evidence$8 = evidence$8;
   }
}
