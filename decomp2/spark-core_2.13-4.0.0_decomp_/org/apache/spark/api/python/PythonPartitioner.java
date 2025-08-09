package org.apache.spark.api.python;

import org.apache.spark.Partitioner;
import org.apache.spark.util.Utils$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005}2Q!\u0003\u0006\u0001\u001dQA\u0001\"\u0007\u0001\u0003\u0006\u0004%\te\u0007\u0005\tE\u0001\u0011\t\u0011)A\u00059!A1\u0005\u0001BC\u0002\u0013\u0005A\u0005\u0003\u0005)\u0001\t\u0005\t\u0015!\u0003&\u0011\u0015I\u0003\u0001\"\u0001+\u0011\u0015y\u0003\u0001\"\u00111\u0011\u00151\u0004\u0001\"\u00118\u0011\u0015i\u0004\u0001\"\u0011?\u0005E\u0001\u0016\u0010\u001e5p]B\u000b'\u000f^5uS>tWM\u001d\u0006\u0003\u00171\ta\u0001]=uQ>t'BA\u0007\u000f\u0003\r\t\u0007/\u001b\u0006\u0003\u001fA\tQa\u001d9be.T!!\u0005\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0012aA8sON\u0011\u0001!\u0006\t\u0003-]i\u0011AD\u0005\u000319\u00111\u0002U1si&$\u0018n\u001c8fe\u0006ia.^7QCJ$\u0018\u000e^5p]N\u001c\u0001!F\u0001\u001d!\ti\u0002%D\u0001\u001f\u0015\u0005y\u0012!B:dC2\f\u0017BA\u0011\u001f\u0005\rIe\u000e^\u0001\u000f]Vl\u0007+\u0019:uSRLwN\\:!\u0003U\u0001\u0018\u0010U1si&$\u0018n\u001c8Gk:\u001cG/[8o\u0013\u0012,\u0012!\n\t\u0003;\u0019J!a\n\u0010\u0003\t1{gnZ\u0001\u0017af\u0004\u0016M\u001d;ji&|gNR;oGRLwN\\%eA\u00051A(\u001b8jiz\"2aK\u0017/!\ta\u0003!D\u0001\u000b\u0011\u0015IR\u00011\u0001\u001d\u0011\u0015\u0019S\u00011\u0001&\u000319W\r\u001e)beRLG/[8o)\ta\u0012\u0007C\u00033\r\u0001\u00071'A\u0002lKf\u0004\"!\b\u001b\n\u0005Ur\"aA!os\u00061Q-];bYN$\"\u0001O\u001e\u0011\u0005uI\u0014B\u0001\u001e\u001f\u0005\u001d\u0011un\u001c7fC:DQ\u0001P\u0004A\u0002M\nQa\u001c;iKJ\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u00029\u0001"
)
public class PythonPartitioner extends Partitioner {
   private final int numPartitions;
   private final long pyPartitionFunctionId;

   public int numPartitions() {
      return this.numPartitions;
   }

   public long pyPartitionFunctionId() {
      return this.pyPartitionFunctionId;
   }

   public int getPartition(final Object key) {
      if (key == null) {
         return 0;
      } else if (key instanceof Long) {
         long var4 = BoxesRunTime.unboxToLong(key);
         return Utils$.MODULE$.nonNegativeMod((int)var4, this.numPartitions());
      } else {
         return Utils$.MODULE$.nonNegativeMod(key.hashCode(), this.numPartitions());
      }
   }

   public boolean equals(final Object other) {
      if (!(other instanceof PythonPartitioner var4)) {
         return false;
      } else {
         return var4.numPartitions() == this.numPartitions() && var4.pyPartitionFunctionId() == this.pyPartitionFunctionId();
      }
   }

   public int hashCode() {
      return 31 * this.numPartitions() + Long.hashCode(this.pyPartitionFunctionId());
   }

   public PythonPartitioner(final int numPartitions, final long pyPartitionFunctionId) {
      this.numPartitions = numPartitions;
      this.pyPartitionFunctionId = pyPartitionFunctionId;
   }
}
