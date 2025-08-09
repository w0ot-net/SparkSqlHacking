package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000b\u001d\u0002AQ\u0001\u0015\u0003\u0019!\u000b7O\u00117pG.\u001c\u0016N_3\u000b\u0005\u00199\u0011AB:iCJ,GM\u0003\u0002\t\u0013\u0005)\u0001/\u0019:b[*\u0011!bC\u0001\u0003[2T!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\u0002\u0001'\r\u00011#\u0007\t\u0003)]i\u0011!\u0006\u0006\u0002-\u0005)1oY1mC&\u0011\u0001$\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005iYR\"A\u0004\n\u0005q9!A\u0002)be\u0006l7/\u0001\u0004%S:LG\u000f\n\u000b\u0002?A\u0011A\u0003I\u0005\u0003CU\u0011A!\u00168ji\u0006I!\r\\8dWNK'0Z\u000b\u0002IA\u0011!$J\u0005\u0003M\u001d\u0011\u0001\"\u00138u!\u0006\u0014\u0018-\\\u0001\rO\u0016$(\t\\8dWNK'0Z\u000b\u0002SA\u0011ACK\u0005\u0003WU\u00111!\u00138u\u0001"
)
public interface HasBlockSize extends Params {
   void org$apache$spark$ml$param$shared$HasBlockSize$_setter_$blockSize_$eq(final IntParam x$1);

   IntParam blockSize();

   // $FF: synthetic method
   static int getBlockSize$(final HasBlockSize $this) {
      return $this.getBlockSize();
   }

   default int getBlockSize() {
      return BoxesRunTime.unboxToInt(this.$(this.blockSize()));
   }

   static void $init$(final HasBlockSize $this) {
      $this.org$apache$spark$ml$param$shared$HasBlockSize$_setter_$blockSize_$eq(new IntParam($this, "blockSize", "block size for stacking input data in matrices. Data is stacked within partitions. If block size is more than remaining data in a partition then it is adjusted to the size of this data.", ParamValidators$.MODULE$.gt((double)0.0F)));
   }
}
