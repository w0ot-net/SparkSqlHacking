package org.apache.spark.mllib.clustering;

import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y;a!\u0002\u0004\t\u0002!\u0001bA\u0002\n\u0007\u0011\u0003A1\u0003C\u0003!\u0003\u0011\u0005!\u0005C\u0003$\u0003\u0011\u0005A\u0005C\u0003=\u0003\u0011%Q(A\u0006M_\u000e\fGnS'fC:\u001c(BA\u0004\t\u0003)\u0019G.^:uKJLgn\u001a\u0006\u0003\u0013)\tQ!\u001c7mS\nT!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'o\u001a\t\u0003#\u0005i\u0011A\u0002\u0002\f\u0019>\u001c\u0017\r\\&NK\u0006t7oE\u0002\u0002)i\u0001\"!\u0006\r\u000e\u0003YQ\u0011aF\u0001\u0006g\u000e\fG.Y\u0005\u00033Y\u0011a!\u00118z%\u00164\u0007CA\u000e\u001f\u001b\u0005a\"BA\u000f\u000b\u0003!Ig\u000e^3s]\u0006d\u0017BA\u0010\u001d\u0005\u001daunZ4j]\u001e\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002!\u0005q1.T3b]N\u0004F.^:QYV\u001cHCB\u0013,aIB$\bE\u0002\u0016M!J!a\n\f\u0003\u000b\u0005\u0013(/Y=\u0011\u0005EI\u0013B\u0001\u0016\u0007\u000591Vm\u0019;pe^KG\u000f\u001b(pe6DQ\u0001L\u0002A\u00025\nAa]3fIB\u0011QCL\u0005\u0003_Y\u00111!\u00138u\u0011\u0015\t4\u00011\u0001&\u0003\u0019\u0001x.\u001b8ug\")1g\u0001a\u0001i\u00059q/Z5hQR\u001c\bcA\u000b'kA\u0011QCN\u0005\u0003oY\u0011a\u0001R8vE2,\u0007\"B\u001d\u0004\u0001\u0004i\u0013!A6\t\u000bm\u001a\u0001\u0019A\u0017\u0002\u001b5\f\u00070\u0013;fe\u0006$\u0018n\u001c8t\u00031\u0001\u0018nY6XK&<\u0007\u000e^3e+\tq\u0014\t\u0006\u0003@\u0015J+\u0006C\u0001!B\u0019\u0001!QA\u0011\u0003C\u0002\r\u0013\u0011\u0001V\t\u0003\t\u001e\u0003\"!F#\n\u0005\u00193\"a\u0002(pi\"Lgn\u001a\t\u0003+!K!!\u0013\f\u0003\u0007\u0005s\u0017\u0010C\u0003L\t\u0001\u0007A*\u0001\u0003sC:$\u0007CA'Q\u001b\u0005q%BA(\u0017\u0003\u0011)H/\u001b7\n\u0005Es%A\u0002*b]\u0012|W\u000eC\u0003T\t\u0001\u0007A+\u0001\u0003eCR\f\u0007cA\u000b'\u007f!)1\u0007\u0002a\u0001i\u0001"
)
public final class LocalKMeans {
   public static VectorWithNorm[] kMeansPlusPlus(final int seed, final VectorWithNorm[] points, final double[] weights, final int k, final int maxIterations) {
      return LocalKMeans$.MODULE$.kMeansPlusPlus(seed, points, weights, k, maxIterations);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return LocalKMeans$.MODULE$.LogStringContext(sc);
   }
}
