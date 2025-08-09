package org.apache.spark;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)2QAB\u0004\u0001\u000f5A\u0001\u0002\u0006\u0001\u0003\u0006\u0004%\tA\u0006\u0005\t5\u0001\u0011\t\u0011)A\u0005/!A1\u0004\u0001BC\u0002\u0013\u0005A\u0004\u0003\u0005$\u0001\t\u0005\t\u0015!\u0003\u001e\u0011\u0015!\u0003\u0001\"\u0001&\u0005Mi\u0015\r](viB,Ho\u0015;bi&\u001cH/[2t\u0015\tA\u0011\"A\u0003ta\u0006\u00148N\u0003\u0002\u000b\u0017\u00051\u0011\r]1dQ\u0016T\u0011\u0001D\u0001\u0004_J<7C\u0001\u0001\u000f!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fM\u0006I1\u000f[;gM2,\u0017\nZ\u0002\u0001+\u00059\u0002CA\b\u0019\u0013\tI\u0002CA\u0002J]R\f!b\u001d5vM\u001adW-\u00133!\u0003I\u0011\u0017\u0010^3t\u0005f\u0004\u0016M\u001d;ji&|g.\u00133\u0016\u0003u\u00012a\u0004\u0010!\u0013\ty\u0002CA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0010C%\u0011!\u0005\u0005\u0002\u0005\u0019>tw-A\ncsR,7OQ=QCJ$\u0018\u000e^5p]&#\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004M!J\u0003CA\u0014\u0001\u001b\u00059\u0001\"\u0002\u000b\u0006\u0001\u00049\u0002\"B\u000e\u0006\u0001\u0004i\u0002"
)
public class MapOutputStatistics {
   private final int shuffleId;
   private final long[] bytesByPartitionId;

   public int shuffleId() {
      return this.shuffleId;
   }

   public long[] bytesByPartitionId() {
      return this.bytesByPartitionId;
   }

   public MapOutputStatistics(final int shuffleId, final long[] bytesByPartitionId) {
      this.shuffleId = shuffleId;
      this.bytesByPartitionId = bytesByPartitionId;
   }
}
