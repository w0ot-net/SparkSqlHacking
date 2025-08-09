package org.apache.spark.mllib.stat.correlation;

import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.rdd.RDD;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015;a!\u0002\u0004\t\u0002!\u0011bA\u0002\u000b\u0007\u0011\u0003AQ\u0003C\u0003&\u0003\u0011\u0005q\u0005C\u0003)\u0003\u0011\u0005\u0013\u0006C\u00038\u0003\u0011\u0005\u0003(A\nTa\u0016\f'/\\1o\u0007>\u0014(/\u001a7bi&|gN\u0003\u0002\b\u0011\u0005Y1m\u001c:sK2\fG/[8o\u0015\tI!\"\u0001\u0003ti\u0006$(BA\u0006\r\u0003\u0015iG\u000e\\5c\u0015\tia\"A\u0003ta\u0006\u00148N\u0003\u0002\u0010!\u00051\u0011\r]1dQ\u0016T\u0011!E\u0001\u0004_J<\u0007CA\n\u0002\u001b\u00051!aE*qK\u0006\u0014X.\u00198D_J\u0014X\r\\1uS>t7\u0003B\u0001\u00179}\u0001\"a\u0006\u000e\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a!\u00118z%\u00164\u0007CA\n\u001e\u0013\tqbAA\u0006D_J\u0014X\r\\1uS>t\u0007C\u0001\u0011$\u001b\u0005\t#B\u0001\u0012\r\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u0013\"\u0005\u001daunZ4j]\u001e\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002%\u0005\u00112m\\7qkR,7i\u001c:sK2\fG/[8o)\rQS&\u000e\t\u0003/-J!\u0001\f\r\u0003\r\u0011{WO\u00197f\u0011\u0015q3\u00011\u00010\u0003\u0005A\bc\u0001\u00194U5\t\u0011G\u0003\u00023\u0019\u0005\u0019!\u000f\u001a3\n\u0005Q\n$a\u0001*E\t\")ag\u0001a\u0001_\u0005\t\u00110\u0001\rd_6\u0004X\u000f^3D_J\u0014X\r\\1uS>tW*\u0019;sSb$\"!O \u0011\u0005ijT\"A\u001e\u000b\u0005qR\u0011A\u00027j]\u0006dw-\u0003\u0002?w\t1Q*\u0019;sSbDQ\u0001\u0011\u0003A\u0002\u0005\u000b\u0011\u0001\u0017\t\u0004aM\u0012\u0005C\u0001\u001eD\u0013\t!5H\u0001\u0004WK\u000e$xN\u001d"
)
public final class SpearmanCorrelation {
   public static Matrix computeCorrelationMatrix(final RDD X) {
      return SpearmanCorrelation$.MODULE$.computeCorrelationMatrix(X);
   }

   public static double computeCorrelation(final RDD x, final RDD y) {
      return SpearmanCorrelation$.MODULE$.computeCorrelation(x, y);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return SpearmanCorrelation$.MODULE$.LogStringContext(sc);
   }

   public static double computeCorrelationWithMatrixImpl(final RDD x, final RDD y) {
      return SpearmanCorrelation$.MODULE$.computeCorrelationWithMatrixImpl(x, y);
   }
}
