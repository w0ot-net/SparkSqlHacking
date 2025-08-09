package org.apache.spark.status.api.v1;

import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i2A\u0001C\u0005\u0001-!AQ\u0004\u0001BC\u0002\u0013\u0005a\u0004\u0003\u0005/\u0001\t\u0005\t\u0015!\u0003 \u0011!y\u0003A!b\u0001\n\u0003q\u0002\u0002\u0003\u0019\u0001\u0005\u0003\u0005\u000b\u0011B\u0010\t\u0011E\u0002!Q1A\u0005\u0002yA\u0001B\r\u0001\u0003\u0002\u0003\u0006Ia\b\u0005\u0007g\u0001!\ta\u0004\u001b\u0003?MCWO\u001a4mK^\u0013\u0018\u000e^3NKR\u0014\u0018n\u0019#jgR\u0014\u0018NY;uS>t7O\u0003\u0002\u000b\u0017\u0005\u0011a/\r\u0006\u0003\u00195\t1!\u00199j\u0015\tqq\"\u0001\u0004ti\u0006$Xo\u001d\u0006\u0003!E\tQa\u001d9be.T!AE\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0012aA8sO\u000e\u00011C\u0001\u0001\u0018!\tA2$D\u0001\u001a\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0005\u0019\te.\u001f*fM\u0006QqO]5uK\nKH/Z:\u0016\u0003}\u00012\u0001\t\u0015,\u001d\t\tcE\u0004\u0002#K5\t1E\u0003\u0002%+\u00051AH]8pizJ\u0011AG\u0005\u0003Oe\tq\u0001]1dW\u0006<W-\u0003\u0002*U\tQ\u0011J\u001c3fq\u0016$7+Z9\u000b\u0005\u001dJ\u0002C\u0001\r-\u0013\ti\u0013D\u0001\u0004E_V\u0014G.Z\u0001\foJLG/\u001a\"zi\u0016\u001c\b%\u0001\u0007xe&$XMU3d_J$7/A\u0007xe&$XMU3d_J$7\u000fI\u0001\noJLG/\u001a+j[\u0016\f!b\u001e:ji\u0016$\u0016.\\3!\u0003\u0019a\u0014N\\5u}Q!Qg\u000e\u001d:!\t1\u0004!D\u0001\n\u0011\u0015ir\u00011\u0001 \u0011\u0015ys\u00011\u0001 \u0011\u0015\tt\u00011\u0001 \u0001"
)
public class ShuffleWriteMetricDistributions {
   private final IndexedSeq writeBytes;
   private final IndexedSeq writeRecords;
   private final IndexedSeq writeTime;

   public IndexedSeq writeBytes() {
      return this.writeBytes;
   }

   public IndexedSeq writeRecords() {
      return this.writeRecords;
   }

   public IndexedSeq writeTime() {
      return this.writeTime;
   }

   public ShuffleWriteMetricDistributions(final IndexedSeq writeBytes, final IndexedSeq writeRecords, final IndexedSeq writeTime) {
      this.writeBytes = writeBytes;
      this.writeRecords = writeRecords;
      this.writeTime = writeTime;
   }
}
