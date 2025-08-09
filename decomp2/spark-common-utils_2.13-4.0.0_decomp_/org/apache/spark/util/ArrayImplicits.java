package org.apache.spark.util;

import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.ArraySeq.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019;a\u0001C\u0005\t\u0002-\tbAB\n\n\u0011\u0003YA\u0003C\u0003\u001c\u0003\u0011\u0005QD\u0002\u0003\u001f\u0003\u0005y\u0002\u0002C\u0011\u0004\u0005\u0003\u0005\u000b\u0011\u0002\u0012\t\u000bm\u0019A\u0011\u0001\u0019\t\u000bQ\u001aA\u0011A\u001b\t\u000fy\n\u0011\u0011!C\u0002\u007f\u0005q\u0011I\u001d:bs&k\u0007\u000f\\5dSR\u001c(B\u0001\u0006\f\u0003\u0011)H/\u001b7\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u0004\"AE\u0001\u000e\u0003%\u0011a\"\u0011:sCfLU\u000e\u001d7jG&$8o\u0005\u0002\u0002+A\u0011a#G\u0007\u0002/)\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b/\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002#\ti1\u000b]1sW\u0006\u0013(/Y=PaN,\"\u0001I\u0014\u0014\u0005\r)\u0012A\u0001=t!\r12%J\u0005\u0003I]\u0011Q!\u0011:sCf\u0004\"AJ\u0014\r\u0001\u0011)\u0001f\u0001b\u0001S\t\tA+\u0005\u0002+[A\u0011acK\u0005\u0003Y]\u0011qAT8uQ&tw\r\u0005\u0002\u0017]%\u0011qf\u0006\u0002\u0004\u0003:LHCA\u00194!\r\u00114!J\u0007\u0002\u0003!)\u0011%\u0002a\u0001E\u0005\u0019Bo\\%n[V$\u0018M\u00197f\u0003J\u0014\u0018-_*fcV\ta\u0007E\u00028y\u0015j\u0011\u0001\u000f\u0006\u0003si\n\u0011\"[7nkR\f'\r\\3\u000b\u0005m:\u0012AC2pY2,7\r^5p]&\u0011Q\b\u000f\u0002\t\u0003J\u0014\u0018-_*fc\u0006i1\u000b]1sW\u0006\u0013(/Y=PaN,\"\u0001Q\"\u0015\u0005\u0005#\u0005c\u0001\u001a\u0004\u0005B\u0011ae\u0011\u0003\u0006Q\u001d\u0011\r!\u000b\u0005\u0006C\u001d\u0001\r!\u0012\t\u0004-\r\u0012\u0005"
)
public final class ArrayImplicits {
   public static SparkArrayOps SparkArrayOps(final Object xs) {
      return ArrayImplicits$.MODULE$.SparkArrayOps(xs);
   }

   public static class SparkArrayOps {
      private final Object xs;

      public ArraySeq toImmutableArraySeq() {
         return .MODULE$.unsafeWrapArray(this.xs);
      }

      public SparkArrayOps(final Object xs) {
         this.xs = xs;
      }
   }
}
