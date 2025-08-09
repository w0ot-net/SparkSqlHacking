package org.apache.spark.status.api.v1;

import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2AAB\u0004\u0001)!A1\u0004\u0001BC\u0002\u0013\u0005A\u0004\u0003\u0005-\u0001\t\u0005\t\u0015!\u0003\u001e\u0011!i\u0003A!b\u0001\n\u0003a\u0002\u0002\u0003\u0018\u0001\u0005\u0003\u0005\u000b\u0011B\u000f\t\r=\u0002A\u0011A\u00071\u0005aIe\u000e];u\u001b\u0016$(/[2ESN$(/\u001b2vi&|gn\u001d\u0006\u0003\u0011%\t!A^\u0019\u000b\u0005)Y\u0011aA1qS*\u0011A\"D\u0001\u0007gR\fG/^:\u000b\u00059y\u0011!B:qCJ\\'B\u0001\t\u0012\u0003\u0019\t\u0007/Y2iK*\t!#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001+A\u0011a#G\u0007\u0002/)\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b/\t1\u0011I\\=SK\u001a\f\u0011BY=uKN\u0014V-\u00193\u0016\u0003u\u00012A\b\u0014*\u001d\tyBE\u0004\u0002!G5\t\u0011E\u0003\u0002#'\u00051AH]8pizJ\u0011\u0001G\u0005\u0003K]\tq\u0001]1dW\u0006<W-\u0003\u0002(Q\tQ\u0011J\u001c3fq\u0016$7+Z9\u000b\u0005\u0015:\u0002C\u0001\f+\u0013\tYsC\u0001\u0004E_V\u0014G.Z\u0001\u000bEf$Xm\u001d*fC\u0012\u0004\u0013a\u0003:fG>\u0014Hm\u001d*fC\u0012\fAB]3d_J$7OU3bI\u0002\na\u0001P5oSRtDcA\u00194iA\u0011!\u0007A\u0007\u0002\u000f!)1$\u0002a\u0001;!)Q&\u0002a\u0001;\u0001"
)
public class InputMetricDistributions {
   private final IndexedSeq bytesRead;
   private final IndexedSeq recordsRead;

   public IndexedSeq bytesRead() {
      return this.bytesRead;
   }

   public IndexedSeq recordsRead() {
      return this.recordsRead;
   }

   public InputMetricDistributions(final IndexedSeq bytesRead, final IndexedSeq recordsRead) {
      this.bytesRead = bytesRead;
      this.recordsRead = recordsRead;
   }
}
