package org.apache.spark.rdd;

import org.apache.spark.Partition;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!2QAB\u0004\u0001\u0013=A\u0001B\u0007\u0001\u0003\u0006\u0004%\t\u0001\b\u0005\tA\u0001\u0011\t\u0011)A\u0005;!)\u0011\u0005\u0001C\u0001E!9a\u0005\u0001b\u0001\n\u0003b\u0002BB\u0014\u0001A\u0003%QD\u0001\u000bTQV4g\r\\3e%\u0012#\u0005+\u0019:uSRLwN\u001c\u0006\u0003\u0011%\t1A\u001d3e\u0015\tQ1\"A\u0003ta\u0006\u00148N\u0003\u0002\r\u001b\u00051\u0011\r]1dQ\u0016T\u0011AD\u0001\u0004_J<7c\u0001\u0001\u0011-A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\u0004\"a\u0006\r\u000e\u0003%I!!G\u0005\u0003\u0013A\u000b'\u000f^5uS>t\u0017aA5eq\u000e\u0001Q#A\u000f\u0011\u0005Eq\u0012BA\u0010\u0013\u0005\rIe\u000e^\u0001\u0005S\u0012D\b%\u0001\u0004=S:LGO\u0010\u000b\u0003G\u0015\u0002\"\u0001\n\u0001\u000e\u0003\u001dAQAG\u0002A\u0002u\tQ!\u001b8eKb\fa!\u001b8eKb\u0004\u0003"
)
public class ShuffledRDDPartition implements Partition {
   private final int idx;
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

   public int idx() {
      return this.idx;
   }

   public int index() {
      return this.index;
   }

   public ShuffledRDDPartition(final int idx) {
      this.idx = idx;
      Partition.$init$(this);
      this.index = idx;
   }
}
