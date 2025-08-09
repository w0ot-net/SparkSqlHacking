package org.apache.spark.storage;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00152Qa\u0001\u0003\u0001\r1A\u0001\u0002\b\u0001\u0003\u0002\u0003\u0006I!\b\u0005\u0006C\u0001!\tA\t\u00020\u00052|7m[*bm\u0016$wJ\u001c#fG>lW.[:tS>tW\r\u001a\"m_\u000e\\W*\u00198bO\u0016\u0014X\t_2faRLwN\u001c\u0006\u0003\u000b\u0019\tqa\u001d;pe\u0006<WM\u0003\u0002\b\u0011\u0005)1\u000f]1sW*\u0011\u0011BC\u0001\u0007CB\f7\r[3\u000b\u0003-\t1a\u001c:h'\t\u0001Q\u0002\u0005\u0002\u000f39\u0011qB\u0006\b\u0003!Qi\u0011!\u0005\u0006\u0003%M\ta\u0001\u0010:p_Rt4\u0001A\u0005\u0002+\u0005)1oY1mC&\u0011q\u0003G\u0001\ba\u0006\u001c7.Y4f\u0015\u0005)\u0012B\u0001\u000e\u001c\u0005%)\u0005pY3qi&|gN\u0003\u0002\u00181\u00059!\r\\8dW&#\u0007C\u0001\u0010 \u001b\u0005!\u0011B\u0001\u0011\u0005\u0005\u001d\u0011En\\2l\u0013\u0012\fa\u0001P5oSRtDCA\u0012%!\tq\u0002\u0001C\u0003\u001d\u0005\u0001\u0007Q\u0004"
)
public class BlockSavedOnDecommissionedBlockManagerException extends Exception {
   public BlockSavedOnDecommissionedBlockManagerException(final BlockId blockId) {
      super("Block " + blockId + " cannot be saved on decommissioned executor");
   }
}
