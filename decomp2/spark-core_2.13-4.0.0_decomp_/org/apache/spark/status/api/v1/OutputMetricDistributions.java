package org.apache.spark.status.api.v1;

import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2AAB\u0004\u0001)!A1\u0004\u0001BC\u0002\u0013\u0005A\u0004\u0003\u0005-\u0001\t\u0005\t\u0015!\u0003\u001e\u0011!i\u0003A!b\u0001\n\u0003a\u0002\u0002\u0003\u0018\u0001\u0005\u0003\u0005\u000b\u0011B\u000f\t\r=\u0002A\u0011A\u00071\u0005eyU\u000f\u001e9vi6+GO]5d\t&\u001cHO]5ckRLwN\\:\u000b\u0005!I\u0011A\u0001<2\u0015\tQ1\"A\u0002ba&T!\u0001D\u0007\u0002\rM$\u0018\r^;t\u0015\tqq\"A\u0003ta\u0006\u00148N\u0003\u0002\u0011#\u00051\u0011\r]1dQ\u0016T\u0011AE\u0001\u0004_J<7\u0001A\n\u0003\u0001U\u0001\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a!\u00118z%\u00164\u0017\u0001\u00042zi\u0016\u001cxK]5ui\u0016tW#A\u000f\u0011\u0007y1\u0013F\u0004\u0002 I9\u0011\u0001eI\u0007\u0002C)\u0011!eE\u0001\u0007yI|w\u000e\u001e \n\u0003aI!!J\f\u0002\u000fA\f7m[1hK&\u0011q\u0005\u000b\u0002\u000b\u0013:$W\r_3e'\u0016\f(BA\u0013\u0018!\t1\"&\u0003\u0002,/\t1Ai\\;cY\u0016\fQBY=uKN<&/\u001b;uK:\u0004\u0013A\u0004:fG>\u0014Hm],sSR$XM\\\u0001\u0010e\u0016\u001cwN\u001d3t/JLG\u000f^3oA\u00051A(\u001b8jiz\"2!M\u001a5!\t\u0011\u0004!D\u0001\b\u0011\u0015YR\u00011\u0001\u001e\u0011\u0015iS\u00011\u0001\u001e\u0001"
)
public class OutputMetricDistributions {
   private final IndexedSeq bytesWritten;
   private final IndexedSeq recordsWritten;

   public IndexedSeq bytesWritten() {
      return this.bytesWritten;
   }

   public IndexedSeq recordsWritten() {
      return this.recordsWritten;
   }

   public OutputMetricDistributions(final IndexedSeq bytesWritten, final IndexedSeq recordsWritten) {
      this.bytesWritten = bytesWritten;
      this.recordsWritten = recordsWritten;
   }
}
