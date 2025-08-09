package org.apache.spark.ui.storage;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.status.StreamBlockData;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.math.Numeric.LongIsIntegral.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005%3A\u0001C\u0005\u0005)!A1\u0004\u0001B\u0001B\u0003%A\u0004C\u0003/\u0001\u0011\u0005q\u0006C\u00034\u0001\u0011\u0005A\u0007C\u0003>\u0001\u0011\u0005A\u0007C\u0003?\u0001\u0011\u0005q\bC\u0003D\u0001\u0011\u0005q\bC\u0003E\u0001\u0011\u0005QIA\u000bFq\u0016\u001cW\u000f^8s'R\u0014X-Y7Tk6l\u0017M]=\u000b\u0005)Y\u0011aB:u_J\fw-\u001a\u0006\u0003\u00195\t!!^5\u000b\u00059y\u0011!B:qCJ\\'B\u0001\t\u0012\u0003\u0019\t\u0007/Y2iK*\t!#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001+A\u0011a#G\u0007\u0002/)\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b/\t1\u0011I\\=SK\u001a\faA\u00197pG.\u001c\bcA\u000f&Q9\u0011ad\t\b\u0003?\tj\u0011\u0001\t\u0006\u0003CM\ta\u0001\u0010:p_Rt\u0014\"\u0001\r\n\u0005\u0011:\u0012a\u00029bG.\fw-Z\u0005\u0003M\u001d\u00121aU3r\u0015\t!s\u0003\u0005\u0002*Y5\t!F\u0003\u0002,\u001b\u000511\u000f^1ukNL!!\f\u0016\u0003\u001fM#(/Z1n\u00052|7m\u001b#bi\u0006\fa\u0001P5oSRtDC\u0001\u00193!\t\t\u0004!D\u0001\n\u0011\u0015Y\"\u00011\u0001\u001d\u0003))\u00070Z2vi>\u0014\u0018\nZ\u000b\u0002kA\u0011aG\u000f\b\u0003oa\u0002\"aH\f\n\u0005e:\u0012A\u0002)sK\u0012,g-\u0003\u0002<y\t11\u000b\u001e:j]\u001eT!!O\f\u0002\u00111|7-\u0019;j_:\fA\u0002^8uC2lU-\\*ju\u0016,\u0012\u0001\u0011\t\u0003-\u0005K!AQ\f\u0003\t1{gnZ\u0001\u000ei>$\u0018\r\u001c#jg.\u001c\u0016N_3\u0002\u001f9,Xn\u0015;sK\u0006l'\t\\8dWN,\u0012A\u0012\t\u0003-\u001dK!\u0001S\f\u0003\u0007%sG\u000f"
)
public class ExecutorStreamSummary {
   private final Seq blocks;

   public String executorId() {
      return ((StreamBlockData)this.blocks.head()).executorId();
   }

   public String location() {
      return ((StreamBlockData)this.blocks.head()).hostPort();
   }

   public long totalMemSize() {
      return BoxesRunTime.unboxToLong(((IterableOnceOps)this.blocks.map((x$7) -> BoxesRunTime.boxToLong($anonfun$totalMemSize$1(x$7)))).sum(.MODULE$));
   }

   public long totalDiskSize() {
      return BoxesRunTime.unboxToLong(((IterableOnceOps)this.blocks.map((x$8) -> BoxesRunTime.boxToLong($anonfun$totalDiskSize$1(x$8)))).sum(.MODULE$));
   }

   public int numStreamBlocks() {
      return this.blocks.size();
   }

   // $FF: synthetic method
   public static final long $anonfun$totalMemSize$1(final StreamBlockData x$7) {
      return x$7.memSize();
   }

   // $FF: synthetic method
   public static final long $anonfun$totalDiskSize$1(final StreamBlockData x$8) {
      return x$8.diskSize();
   }

   public ExecutorStreamSummary(final Seq blocks) {
      this.blocks = blocks;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
