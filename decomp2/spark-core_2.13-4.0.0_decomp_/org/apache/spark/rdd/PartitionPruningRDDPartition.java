package org.apache.spark.rdd;

import org.apache.spark.Partition;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512Qa\u0002\u0005\u0001\u0015AA\u0001b\u0007\u0001\u0003\u0002\u0003\u0006I!\b\u0005\tA\u0001\u0011)\u0019!C\u0001C!A!\u0005\u0001B\u0001B\u0003%q\u0003C\u0003$\u0001\u0011\u0005A\u0005C\u0004*\u0001\t\u0007I\u0011\t\u0016\t\r-\u0002\u0001\u0015!\u0003\u001e\u0005q\u0001\u0016M\u001d;ji&|g\u000e\u0015:v]&twM\u0015#E!\u0006\u0014H/\u001b;j_:T!!\u0003\u0006\u0002\u0007I$GM\u0003\u0002\f\u0019\u0005)1\u000f]1sW*\u0011QBD\u0001\u0007CB\f7\r[3\u000b\u0003=\t1a\u001c:h'\r\u0001\u0011c\u0006\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005aIR\"\u0001\u0006\n\u0005iQ!!\u0003)beRLG/[8o\u0003\rIG\r_\u0002\u0001!\t\u0011b$\u0003\u0002 '\t\u0019\u0011J\u001c;\u0002\u0017A\f'/\u001a8u'Bd\u0017\u000e^\u000b\u0002/\u0005a\u0001/\u0019:f]R\u001c\u0006\u000f\\5uA\u00051A(\u001b8jiz\"2!J\u0014)!\t1\u0003!D\u0001\t\u0011\u0015YB\u00011\u0001\u001e\u0011\u0015\u0001C\u00011\u0001\u0018\u0003\u0015Ig\u000eZ3y+\u0005i\u0012AB5oI\u0016D\b\u0005"
)
public class PartitionPruningRDDPartition implements Partition {
   private final Partition parentSplit;
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

   public Partition parentSplit() {
      return this.parentSplit;
   }

   public int index() {
      return this.index;
   }

   public PartitionPruningRDDPartition(final int idx, final Partition parentSplit) {
      this.parentSplit = parentSplit;
      Partition.$init$(this);
      this.index = idx;
   }
}
