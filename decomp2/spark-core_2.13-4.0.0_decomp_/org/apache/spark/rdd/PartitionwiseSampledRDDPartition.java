package org.apache.spark.rdd;

import org.apache.spark.Partition;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2Q\u0001C\u0005\u0001\u0017EA\u0001\"\u000b\u0001\u0003\u0006\u0004%\tA\u000b\u0005\tW\u0001\u0011\t\u0011)A\u00051!AA\u0006\u0001BC\u0002\u0013\u0005Q\u0006\u0003\u00052\u0001\t\u0005\t\u0015!\u0003/\u0011\u0015\u0011\u0004\u0001\"\u00014\u0011\u001dA\u0004A1A\u0005BeBa!\u0010\u0001!\u0002\u0013Q$\u0001\t)beRLG/[8oo&\u001cXmU1na2,GM\u0015#E!\u0006\u0014H/\u001b;j_:T!AC\u0006\u0002\u0007I$GM\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h'\u0011\u0001!\u0003\u0007\u000f\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g!\tI\"$D\u0001\f\u0013\tY2BA\u0005QCJ$\u0018\u000e^5p]B\u0011QD\n\b\u0003=\u0011r!aH\u0012\u000e\u0003\u0001R!!\t\u0012\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!F\u0005\u0003KQ\tq\u0001]1dW\u0006<W-\u0003\u0002(Q\ta1+\u001a:jC2L'0\u00192mK*\u0011Q\u0005F\u0001\u0005aJ,g/F\u0001\u0019\u0003\u0015\u0001(/\u001a<!\u0003\u0011\u0019X-\u001a3\u0016\u00039\u0002\"aE\u0018\n\u0005A\"\"\u0001\u0002'p]\u001e\fQa]3fI\u0002\na\u0001P5oSRtDc\u0001\u001b7oA\u0011Q\u0007A\u0007\u0002\u0013!)\u0011&\u0002a\u00011!)A&\u0002a\u0001]\u0005)\u0011N\u001c3fqV\t!\b\u0005\u0002\u0014w%\u0011A\b\u0006\u0002\u0004\u0013:$\u0018AB5oI\u0016D\b\u0005"
)
public class PartitionwiseSampledRDDPartition implements Partition {
   private final Partition prev;
   private final long seed;
   private final int index;

   // $FF: synthetic method
   public boolean org$apache$spark$Partition$$super$equals(final Object x$1) {
      return super.equals(x$1);
   }

   public int hashCode() {
      return Partition.hashCode$(this);
   }

   public boolean equals(final Object other) {
      return Partition.equals$(this, other);
   }

   public Partition prev() {
      return this.prev;
   }

   public long seed() {
      return this.seed;
   }

   public int index() {
      return this.index;
   }

   public PartitionwiseSampledRDDPartition(final Partition prev, final long seed) {
      this.prev = prev;
      this.seed = seed;
      Partition.$init$(this);
      this.index = prev.index();
   }
}
