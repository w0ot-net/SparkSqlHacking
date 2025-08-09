package org.apache.spark.ml.r;

import org.apache.spark.ml.fpm.PrefixSpan;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u:a\u0001B\u0003\t\u0002\u0015yaAB\t\u0006\u0011\u0003)!\u0003C\u0003\u001a\u0003\u0011\u00051\u0004C\u0003\u001d\u0003\u0011\u0005Q$A\tQe\u00164\u0017\u000e_*qC:<&/\u00199qKJT!AB\u0004\u0002\u0003IT!\u0001C\u0005\u0002\u00055d'B\u0001\u0006\f\u0003\u0015\u0019\b/\u0019:l\u0015\taQ\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001d\u0005\u0019qN]4\u0011\u0005A\tQ\"A\u0003\u0003#A\u0013XMZ5y'B\fgn\u0016:baB,'o\u0005\u0002\u0002'A\u0011AcF\u0007\u0002+)\ta#A\u0003tG\u0006d\u0017-\u0003\u0002\u0019+\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002\u001f\u0005iq-\u001a;Qe\u00164\u0017\u000e_*qC:$RA\b\u0013*]A\u0002\"a\b\u0012\u000e\u0003\u0001R!!I\u0004\u0002\u0007\u0019\u0004X.\u0003\u0002$A\tQ\u0001K]3gSb\u001c\u0006/\u00198\t\u000b\u0015\u001a\u0001\u0019\u0001\u0014\u0002\u00155LgnU;qa>\u0014H\u000f\u0005\u0002\u0015O%\u0011\u0001&\u0006\u0002\u0007\t>,(\r\\3\t\u000b)\u001a\u0001\u0019A\u0016\u0002!5\f\u0007\u0010U1ui\u0016\u0014h\u000eT3oORD\u0007C\u0001\u000b-\u0013\tiSCA\u0002J]RDQaL\u0002A\u0002\u0019\n!#\\1y\u0019>\u001c\u0017\r\u001c)s_*$%iU5{K\")\u0011g\u0001a\u0001e\u0005Y1/Z9vK:\u001cWmQ8m!\t\u0019$H\u0004\u00025qA\u0011Q'F\u0007\u0002m)\u0011qGG\u0001\u0007yI|w\u000e\u001e \n\u0005e*\u0012A\u0002)sK\u0012,g-\u0003\u0002<y\t11\u000b\u001e:j]\u001eT!!O\u000b"
)
public final class PrefixSpanWrapper {
   public static PrefixSpan getPrefixSpan(final double minSupport, final int maxPatternLength, final double maxLocalProjDBSize, final String sequenceCol) {
      return PrefixSpanWrapper$.MODULE$.getPrefixSpan(minSupport, maxPatternLength, maxLocalProjDBSize, sequenceCol);
   }
}
