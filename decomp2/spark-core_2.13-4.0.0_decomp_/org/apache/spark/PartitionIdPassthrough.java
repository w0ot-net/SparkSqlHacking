package org.apache.spark;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00192Q!\u0002\u0004\u0001\r1A\u0001\"\u0005\u0001\u0003\u0006\u0004%\te\u0005\u0005\t5\u0001\u0011\t\u0011)A\u0005)!)1\u0004\u0001C\u00019!)q\u0004\u0001C!A\t1\u0002+\u0019:uSRLwN\\%e!\u0006\u001c8\u000f\u001e5s_V<\u0007N\u0003\u0002\b\u0011\u0005)1\u000f]1sW*\u0011\u0011BC\u0001\u0007CB\f7\r[3\u000b\u0003-\t1a\u001c:h'\t\u0001Q\u0002\u0005\u0002\u000f\u001f5\ta!\u0003\u0002\u0011\r\tY\u0001+\u0019:uSRLwN\\3s\u00035qW/\u001c)beRLG/[8og\u000e\u0001Q#\u0001\u000b\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\u0007%sG/\u0001\bok6\u0004\u0016M\u001d;ji&|gn\u001d\u0011\u0002\rqJg.\u001b;?)\tib\u0004\u0005\u0002\u000f\u0001!)\u0011c\u0001a\u0001)\u0005aq-\u001a;QCJ$\u0018\u000e^5p]R\u0011A#\t\u0005\u0006E\u0011\u0001\raI\u0001\u0004W\u0016L\bCA\u000b%\u0013\t)cCA\u0002B]f\u0004"
)
public class PartitionIdPassthrough extends Partitioner {
   private final int numPartitions;

   public int numPartitions() {
      return this.numPartitions;
   }

   public int getPartition(final Object key) {
      return BoxesRunTime.unboxToInt(key);
   }

   public PartitionIdPassthrough(final int numPartitions) {
      this.numPartitions = numPartitions;
   }
}
