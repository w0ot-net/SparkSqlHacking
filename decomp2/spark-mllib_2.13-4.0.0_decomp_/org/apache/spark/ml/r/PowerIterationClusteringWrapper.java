package org.apache.spark.ml.r;

import org.apache.spark.ml.clustering.PowerIterationClustering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y:a\u0001B\u0003\t\u0002\u0015yaAB\t\u0006\u0011\u0003)!\u0003C\u0003\u001a\u0003\u0011\u00051\u0004C\u0003\u001d\u0003\u0011\u0005Q$A\u0010Q_^,'/\u0013;fe\u0006$\u0018n\u001c8DYV\u001cH/\u001a:j]\u001e<&/\u00199qKJT!AB\u0004\u0002\u0003IT!\u0001C\u0005\u0002\u00055d'B\u0001\u0006\f\u0003\u0015\u0019\b/\u0019:l\u0015\taQ\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001d\u0005\u0019qN]4\u0011\u0005A\tQ\"A\u0003\u0003?A{w/\u001a:Ji\u0016\u0014\u0018\r^5p]\u000ecWo\u001d;fe&twm\u0016:baB,'o\u0005\u0002\u0002'A\u0011AcF\u0007\u0002+)\ta#A\u0003tG\u0006d\u0017-\u0003\u0002\u0019+\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002\u001f\u0005Yr-\u001a;Q_^,'/\u0013;fe\u0006$\u0018n\u001c8DYV\u001cH/\u001a:j]\u001e$rA\b\u0013*maRD\b\u0005\u0002 E5\t\u0001E\u0003\u0002\"\u000f\u0005Q1\r\\;ti\u0016\u0014\u0018N\\4\n\u0005\r\u0002#\u0001\u0007)po\u0016\u0014\u0018\n^3sCRLwN\\\"mkN$XM]5oO\")Qe\u0001a\u0001M\u0005\t1\u000e\u0005\u0002\u0015O%\u0011\u0001&\u0006\u0002\u0004\u0013:$\b\"\u0002\u0016\u0004\u0001\u0004Y\u0013\u0001C5oSRlu\u000eZ3\u0011\u00051\u001adBA\u00172!\tqS#D\u00010\u0015\t\u0001$$\u0001\u0004=e>|GOP\u0005\u0003eU\ta\u0001\u0015:fI\u00164\u0017B\u0001\u001b6\u0005\u0019\u0019FO]5oO*\u0011!'\u0006\u0005\u0006o\r\u0001\rAJ\u0001\b[\u0006D\u0018\n^3s\u0011\u0015I4\u00011\u0001,\u0003\u0019\u0019(oY\"pY\")1h\u0001a\u0001W\u00051Am\u001d;D_2DQ!P\u0002A\u0002-\n\u0011b^3jO\"$8i\u001c7"
)
public final class PowerIterationClusteringWrapper {
   public static PowerIterationClustering getPowerIterationClustering(final int k, final String initMode, final int maxIter, final String srcCol, final String dstCol, final String weightCol) {
      return PowerIterationClusteringWrapper$.MODULE$.getPowerIterationClustering(k, initMode, maxIter, srcCol, dstCol, weightCol);
   }
}
