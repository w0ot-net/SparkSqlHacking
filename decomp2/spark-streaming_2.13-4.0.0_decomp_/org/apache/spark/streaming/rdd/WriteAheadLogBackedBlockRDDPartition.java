package org.apache.spark.streaming.rdd;

import org.apache.spark.Partition;
import org.apache.spark.storage.BlockId;
import org.apache.spark.streaming.util.WriteAheadLogRecordHandle;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3QAC\u0006\u0001\u001bUA\u0001\u0002\t\u0001\u0003\u0006\u0004%\tA\t\u0005\tM\u0001\u0011\t\u0011)A\u0005G!Aq\u0005\u0001BC\u0002\u0013\u0005\u0001\u0006\u0003\u00050\u0001\t\u0005\t\u0015!\u0003*\u0011!\u0001\u0004A!b\u0001\n\u0003\t\u0004\u0002C\u001b\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001a\t\u0011Y\u0002!Q1A\u0005\u0002]B\u0001B\u0010\u0001\u0003\u0002\u0003\u0006I\u0001\u000f\u0005\u0006\u007f\u0001!\t\u0001\u0011\u0002%/JLG/Z!iK\u0006$Gj\\4CC\u000e\\W\r\u001a\"m_\u000e\\'\u000b\u0012#QCJ$\u0018\u000e^5p]*\u0011A\"D\u0001\u0004e\u0012$'B\u0001\b\u0010\u0003%\u0019HO]3b[&twM\u0003\u0002\u0011#\u0005)1\u000f]1sW*\u0011!cE\u0001\u0007CB\f7\r[3\u000b\u0003Q\t1a\u001c:h'\r\u0001a\u0003\b\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0005uqR\"A\b\n\u0005}y!!\u0003)beRLG/[8o\u0003\u0015Ig\u000eZ3y\u0007\u0001)\u0012a\t\t\u0003/\u0011J!!\n\r\u0003\u0007%sG/\u0001\u0004j]\u0012,\u0007\u0010I\u0001\bE2|7m[%e+\u0005I\u0003C\u0001\u0016.\u001b\u0005Y#B\u0001\u0017\u0010\u0003\u001d\u0019Ho\u001c:bO\u0016L!AL\u0016\u0003\u000f\tcwnY6JI\u0006A!\r\\8dW&#\u0007%\u0001\bjg\ncwnY6JIZ\u000bG.\u001b3\u0016\u0003I\u0002\"aF\u001a\n\u0005QB\"a\u0002\"p_2,\u0017M\\\u0001\u0010SN\u0014En\\2l\u0013\u00124\u0016\r\\5eA\u0005yq/\u00197SK\u000e|'\u000f\u001a%b]\u0012dW-F\u00019!\tID(D\u0001;\u0015\tYT\"\u0001\u0003vi&d\u0017BA\u001f;\u0005e9&/\u001b;f\u0003\",\u0017\r\u001a'pOJ+7m\u001c:e\u0011\u0006tG\r\\3\u0002!]\fGNU3d_J$\u0007*\u00198eY\u0016\u0004\u0013A\u0002\u001fj]&$h\bF\u0003B\u0007\u0012+e\t\u0005\u0002C\u00015\t1\u0002C\u0003!\u0013\u0001\u00071\u0005C\u0003(\u0013\u0001\u0007\u0011\u0006C\u00031\u0013\u0001\u0007!\u0007C\u00037\u0013\u0001\u0007\u0001\b"
)
public class WriteAheadLogBackedBlockRDDPartition implements Partition {
   private final int index;
   private final BlockId blockId;
   private final boolean isBlockIdValid;
   private final WriteAheadLogRecordHandle walRecordHandle;

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

   public int index() {
      return this.index;
   }

   public BlockId blockId() {
      return this.blockId;
   }

   public boolean isBlockIdValid() {
      return this.isBlockIdValid;
   }

   public WriteAheadLogRecordHandle walRecordHandle() {
      return this.walRecordHandle;
   }

   public WriteAheadLogBackedBlockRDDPartition(final int index, final BlockId blockId, final boolean isBlockIdValid, final WriteAheadLogRecordHandle walRecordHandle) {
      this.index = index;
      this.blockId = blockId;
      this.isBlockIdValid = isBlockIdValid;
      this.walRecordHandle = walRecordHandle;
      Partition.$init$(this);
   }
}
