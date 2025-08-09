package org.apache.spark.mllib.linalg;

import scala.Function1;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y:a\u0001B\u0003\t\u0002\u001dyaAB\t\u0006\u0011\u00039!\u0003C\u0003\u001a\u0003\u0011\u00051\u0004C\u0003\u001d\u0003\u0011\u0005Q$A\fFS\u001e,gNV1mk\u0016$UmY8na>\u001c\u0018\u000e^5p]*\u0011aaB\u0001\u0007Y&t\u0017\r\\4\u000b\u0005!I\u0011!B7mY&\u0014'B\u0001\u0006\f\u0003\u0015\u0019\b/\u0019:l\u0015\taQ\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001d\u0005\u0019qN]4\u0011\u0005A\tQ\"A\u0003\u0003/\u0015Kw-\u001a8WC2,X\rR3d_6\u0004xn]5uS>t7CA\u0001\u0014!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0010\u00035\u0019\u00180\\7fiJL7-R5hgR1aDL\u001a9uq\u0002B\u0001F\u0010\"W%\u0011\u0001%\u0006\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0007\t2\u0003&D\u0001$\u0015\t1AEC\u0001&\u0003\u0019\u0011'/Z3{K&\u0011qe\t\u0002\f\t\u0016t7/\u001a,fGR|'\u000f\u0005\u0002\u0015S%\u0011!&\u0006\u0002\u0007\t>,(\r\\3\u0011\u0007\tb\u0003&\u0003\u0002.G\tYA)\u001a8tK6\u000bGO]5y\u0011\u0015y3\u00011\u00011\u0003\riW\u000f\u001c\t\u0005)E\n\u0013%\u0003\u00023+\tIa)\u001e8di&|g.\r\u0005\u0006i\r\u0001\r!N\u0001\u0002]B\u0011ACN\u0005\u0003oU\u00111!\u00138u\u0011\u0015I4\u00011\u00016\u0003\u0005Y\u0007\"B\u001e\u0004\u0001\u0004A\u0013a\u0001;pY\")Qh\u0001a\u0001k\u0005iQ.\u0019=Ji\u0016\u0014\u0018\r^5p]N\u0004"
)
public final class EigenValueDecomposition {
   public static Tuple2 symmetricEigs(final Function1 mul, final int n, final int k, final double tol, final int maxIterations) {
      return EigenValueDecomposition$.MODULE$.symmetricEigs(mul, n, k, tol, maxIterations);
   }
}
