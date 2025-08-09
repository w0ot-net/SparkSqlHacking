package org.apache.spark.rdd;

import org.apache.spark.Partition;
import org.apache.spark.storage.BlockId;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I2Qa\u0002\u0005\u0001\u0015AA\u0001b\u0007\u0001\u0003\u0006\u0004%\t!\b\u0005\tI\u0001\u0011\t\u0011)A\u0005=!AQ\u0005\u0001B\u0001B\u0003%a\u0005C\u0003*\u0001\u0011\u0005!\u0006C\u00040\u0001\t\u0007I\u0011\u0001\u0019\t\rE\u0002\u0001\u0015!\u0003'\u0005E\u0011En\\2l%\u0012#\u0005+\u0019:uSRLwN\u001c\u0006\u0003\u0013)\t1A\u001d3e\u0015\tYA\"A\u0003ta\u0006\u00148N\u0003\u0002\u000e\u001d\u00051\u0011\r]1dQ\u0016T\u0011aD\u0001\u0004_J<7c\u0001\u0001\u0012/A\u0011!#F\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t1\u0011I\\=SK\u001a\u0004\"\u0001G\r\u000e\u0003)I!A\u0007\u0006\u0003\u0013A\u000b'\u000f^5uS>t\u0017a\u00022m_\u000e\\\u0017\nZ\u0002\u0001+\u0005q\u0002CA\u0010#\u001b\u0005\u0001#BA\u0011\u000b\u0003\u001d\u0019Ho\u001c:bO\u0016L!a\t\u0011\u0003\u000f\tcwnY6JI\u0006A!\r\\8dW&#\u0007%A\u0002jIb\u0004\"AE\u0014\n\u0005!\u001a\"aA%oi\u00061A(\u001b8jiz\"2aK\u0017/!\ta\u0003!D\u0001\t\u0011\u0015YB\u00011\u0001\u001f\u0011\u0015)C\u00011\u0001'\u0003\u0015Ig\u000eZ3y+\u00051\u0013AB5oI\u0016D\b\u0005"
)
public class BlockRDDPartition implements Partition {
   private final BlockId blockId;
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

   public BlockId blockId() {
      return this.blockId;
   }

   public int index() {
      return this.index;
   }

   public BlockRDDPartition(final BlockId blockId, final int idx) {
      this.blockId = blockId;
      Partition.$init$(this);
      this.index = idx;
   }
}
