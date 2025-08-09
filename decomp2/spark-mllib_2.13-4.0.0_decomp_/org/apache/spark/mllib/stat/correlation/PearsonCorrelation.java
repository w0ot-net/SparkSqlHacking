package org.apache.spark.mllib.stat.correlation;

import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.rdd.RDD;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005<a\u0001C\u0005\t\u0002-)bAB\f\n\u0011\u0003Y\u0001\u0004C\u0003)\u0003\u0011\u0005!\u0006C\u0003,\u0003\u0011\u0005C\u0006C\u0003;\u0003\u0011\u00053\bC\u0003I\u0003\u0011\u0005\u0011\nC\u0003M\u0003\u0011%Q\nC\u0004V\u0003E\u0005I\u0011\u0002,\u0002%A+\u0017M]:p]\u000e{'O]3mCRLwN\u001c\u0006\u0003\u0015-\t1bY8se\u0016d\u0017\r^5p]*\u0011A\"D\u0001\u0005gR\fGO\u0003\u0002\u000f\u001f\u0005)Q\u000e\u001c7jE*\u0011\u0001#E\u0001\u0006gB\f'o\u001b\u0006\u0003%M\ta!\u00199bG\",'\"\u0001\u000b\u0002\u0007=\u0014x\r\u0005\u0002\u0017\u00035\t\u0011B\u0001\nQK\u0006\u00148o\u001c8D_J\u0014X\r\\1uS>t7\u0003B\u0001\u001a?\t\u0002\"AG\u000f\u000e\u0003mQ\u0011\u0001H\u0001\u0006g\u000e\fG.Y\u0005\u0003=m\u0011a!\u00118z%\u00164\u0007C\u0001\f!\u0013\t\t\u0013BA\u0006D_J\u0014X\r\\1uS>t\u0007CA\u0012'\u001b\u0005!#BA\u0013\u0010\u0003!Ig\u000e^3s]\u0006d\u0017BA\u0014%\u0005\u001daunZ4j]\u001e\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002+\u0005\u00112m\\7qkR,7i\u001c:sK2\fG/[8o)\ri\u0003\u0007\u000f\t\u000359J!aL\u000e\u0003\r\u0011{WO\u00197f\u0011\u0015\t4\u00011\u00013\u0003\u0005A\bcA\u001a7[5\tAG\u0003\u00026\u001f\u0005\u0019!\u000f\u001a3\n\u0005]\"$a\u0001*E\t\")\u0011h\u0001a\u0001e\u0005\t\u00110\u0001\rd_6\u0004X\u000f^3D_J\u0014X\r\\1uS>tW*\u0019;sSb$\"\u0001\u0010\"\u0011\u0005u\u0002U\"\u0001 \u000b\u0005}j\u0011A\u00027j]\u0006dw-\u0003\u0002B}\t1Q*\u0019;sSbDQa\u0011\u0003A\u0002\u0011\u000b\u0011\u0001\u0017\t\u0004gY*\u0005CA\u001fG\u0013\t9eH\u0001\u0004WK\u000e$xN]\u0001'G>l\u0007/\u001e;f\u0007>\u0014(/\u001a7bi&|g.T1ue&DhI]8n\u0007>4\u0018M]5b]\u000e,GC\u0001\u001fK\u0011\u0015YU\u00011\u0001=\u0003A\u0019wN^1sS\u0006t7-Z'biJL\u00070A\u0006dY>\u001cX\rV8[KJ|Gc\u0001(R'B\u0011!dT\u0005\u0003!n\u0011qAQ8pY\u0016\fg\u000eC\u0003S\r\u0001\u0007Q&A\u0003wC2,X\rC\u0004U\rA\u0005\t\u0019A\u0017\u0002\u0013QD'/Z:i_2$\u0017!F2m_N,Gk\u001c.fe>$C-\u001a4bk2$HEM\u000b\u0002/*\u0012Q\u0006W\u0016\u00023B\u0011!lX\u0007\u00027*\u0011A,X\u0001\nk:\u001c\u0007.Z2lK\u0012T!AX\u000e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002a7\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3"
)
public final class PearsonCorrelation {
   public static Matrix computeCorrelationMatrixFromCovariance(final Matrix covarianceMatrix) {
      return PearsonCorrelation$.MODULE$.computeCorrelationMatrixFromCovariance(covarianceMatrix);
   }

   public static Matrix computeCorrelationMatrix(final RDD X) {
      return PearsonCorrelation$.MODULE$.computeCorrelationMatrix(X);
   }

   public static double computeCorrelation(final RDD x, final RDD y) {
      return PearsonCorrelation$.MODULE$.computeCorrelation(x, y);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return PearsonCorrelation$.MODULE$.LogStringContext(sc);
   }

   public static double computeCorrelationWithMatrixImpl(final RDD x, final RDD y) {
      return PearsonCorrelation$.MODULE$.computeCorrelationWithMatrixImpl(x, y);
   }
}
