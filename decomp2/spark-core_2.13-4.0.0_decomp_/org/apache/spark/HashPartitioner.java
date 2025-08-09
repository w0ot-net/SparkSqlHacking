package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.util.Utils$;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E2Aa\u0002\u0005\u0001\u001f!AA\u0003\u0001B\u0001B\u0003%Q\u0003C\u0003\u001c\u0001\u0011\u0005A\u0004C\u0003 \u0001\u0011\u0005\u0001\u0005C\u0003\"\u0001\u0011\u0005!\u0005C\u0003)\u0001\u0011\u0005\u0013\u0006C\u00030\u0001\u0011\u0005\u0003GA\bICND\u0007+\u0019:uSRLwN\\3s\u0015\tI!\"A\u0003ta\u0006\u00148N\u0003\u0002\f\u0019\u00051\u0011\r]1dQ\u0016T\u0011!D\u0001\u0004_J<7\u0001A\n\u0003\u0001A\u0001\"!\u0005\n\u000e\u0003!I!a\u0005\u0005\u0003\u0017A\u000b'\u000f^5uS>tWM]\u0001\u000ba\u0006\u0014H/\u001b;j_:\u001c\bC\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"aA%oi\u00061A(\u001b8jiz\"\"!\b\u0010\u0011\u0005E\u0001\u0001\"\u0002\u000b\u0003\u0001\u0004)\u0012!\u00048v[B\u000b'\u000f^5uS>t7/F\u0001\u0016\u000319W\r\u001e)beRLG/[8o)\t)2\u0005C\u0003%\t\u0001\u0007Q%A\u0002lKf\u0004\"A\u0006\u0014\n\u0005\u001d:\"aA!os\u00061Q-];bYN$\"AK\u0017\u0011\u0005YY\u0013B\u0001\u0017\u0018\u0005\u001d\u0011un\u001c7fC:DQAL\u0003A\u0002\u0015\nQa\u001c;iKJ\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002+\u0001"
)
public class HashPartitioner extends Partitioner {
   private final int partitions;

   public int numPartitions() {
      return this.partitions;
   }

   public int getPartition(final Object key) {
      return key == null ? 0 : Utils$.MODULE$.nonNegativeMod(key.hashCode(), this.numPartitions());
   }

   public boolean equals(final Object other) {
      if (other instanceof HashPartitioner var4) {
         return var4.numPartitions() == this.numPartitions();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.numPartitions();
   }

   public HashPartitioner(final int partitions) {
      this.partitions = partitions;
      .MODULE$.require(partitions >= 0, () -> "Number of partitions (" + this.partitions + ") cannot be negative.");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
