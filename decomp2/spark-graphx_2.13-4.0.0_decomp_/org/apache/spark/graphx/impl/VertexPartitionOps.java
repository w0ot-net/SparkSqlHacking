package org.apache.spark.graphx.impl;

import org.apache.spark.util.collection.BitSet;
import org.apache.spark.util.collection.OpenHashSet;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014Qa\u0002\u0005\u0001\u0015IA\u0001B\u000b\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\tY\u0001\u0011\u0019\u0011)A\u0006[!)1\u0007\u0001C\u0001i!)\u0011\b\u0001C\u0001u!)A\t\u0001C\u0001\u000b\")A\u000b\u0001C\u0001+\n\u0011b+\u001a:uKb\u0004\u0016M\u001d;ji&|gn\u00149t\u0015\tI!\"\u0001\u0003j[Bd'BA\u0006\r\u0003\u00199'/\u00199iq*\u0011QBD\u0001\u0006gB\f'o\u001b\u0006\u0003\u001fA\ta!\u00199bG\",'\"A\t\u0002\u0007=\u0014x-\u0006\u0002\u00145M\u0011\u0001\u0001\u0006\t\u0005+YAr%D\u0001\t\u0013\t9\u0002B\u0001\fWKJ$X\r\u001f)beRLG/[8o\u0005\u0006\u001cXm\u00149t!\tI\"\u0004\u0004\u0001\u0005\u000bm\u0001!\u0019A\u000f\u0003\u0005Y#5\u0001A\t\u0003=\u0011\u0002\"a\b\u0012\u000e\u0003\u0001R\u0011!I\u0001\u0006g\u000e\fG.Y\u0005\u0003G\u0001\u0012qAT8uQ&tw\r\u0005\u0002 K%\u0011a\u0005\t\u0002\u0004\u0003:L\bCA\u000b)\u0013\tI\u0003BA\bWKJ$X\r\u001f)beRLG/[8o\u0003\u0011\u0019X\r\u001c4\u0011\u0007UA\u0003$\u0001\u0006fm&$WM\\2fIU\u00022AL\u0019\u0019\u001b\u0005y#B\u0001\u0019!\u0003\u001d\u0011XM\u001a7fGRL!AM\u0018\u0003\u0011\rc\u0017m]:UC\u001e\fa\u0001P5oSRtDCA\u001b9)\t1t\u0007E\u0002\u0016\u0001aAQ\u0001L\u0002A\u00045BQAK\u0002A\u0002-\n\u0011b^5uQ&sG-\u001a=\u0015\u0005-Z\u0004\"\u0002\u001f\u0005\u0001\u0004i\u0014!B5oI\u0016D\bC\u0001 B\u001d\t)r(\u0003\u0002A\u0011\u00059\u0001/Y2lC\u001e,\u0017B\u0001\"D\u0005I1VM\u001d;fq&#Gk\\%oI\u0016DX*\u00199\u000b\u0005\u0001C\u0011AC<ji\"4\u0016\r\\;fgV\u0011aI\u0013\u000b\u0003\u000f>#\"\u0001\u0013'\u0011\u0007UA\u0013\n\u0005\u0002\u001a\u0015\u0012)1*\u0002b\u0001;\t\u0019a\u000b\u0012\u001a\t\u000f5+\u0011\u0011!a\u0002\u001d\u0006QQM^5eK:\u001cW\r\n\u001c\u0011\u00079\n\u0014\nC\u0003Q\u000b\u0001\u0007\u0011+\u0001\u0004wC2,Xm\u001d\t\u0004?IK\u0015BA*!\u0005\u0015\t%O]1z\u0003!9\u0018\u000e\u001e5NCN\\GCA\u0016W\u0011\u00159f\u00011\u0001Y\u0003\u0011i\u0017m]6\u0011\u0005esV\"\u0001.\u000b\u0005mc\u0016AC2pY2,7\r^5p]*\u0011Q\fD\u0001\u0005kRLG.\u0003\u0002`5\n1!)\u001b;TKR\u0004"
)
public class VertexPartitionOps extends VertexPartitionBaseOps {
   private final VertexPartition self;
   private final ClassTag evidence$5;

   public VertexPartition withIndex(final OpenHashSet index) {
      return new VertexPartition(index, this.self.values(), this.self.mask(), this.evidence$5);
   }

   public VertexPartition withValues(final Object values, final ClassTag evidence$6) {
      return new VertexPartition(this.self.index(), values, this.self.mask(), evidence$6);
   }

   public VertexPartition withMask(final BitSet mask) {
      return new VertexPartition(this.self.index(), this.self.values(), mask, this.evidence$5);
   }

   public VertexPartitionOps(final VertexPartition self, final ClassTag evidence$5) {
      super(self, evidence$5, VertexPartition.VertexPartitionOpsConstructor$.MODULE$);
      this.self = self;
      this.evidence$5 = evidence$5;
   }
}
