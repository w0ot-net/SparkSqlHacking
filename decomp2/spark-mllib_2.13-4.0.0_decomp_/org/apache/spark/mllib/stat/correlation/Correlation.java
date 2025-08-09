package org.apache.spark.mllib.stat.correlation;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005\u0001B\u0005\u0005\u00063\u0001!\ta\u0007\u0005\u0006?\u00011\t\u0001\t\u0005\u0006]\u00011\ta\f\u0005\u0006y\u0001!\t!\u0010\u0002\f\u0007>\u0014(/\u001a7bi&|gN\u0003\u0002\b\u0011\u0005Y1m\u001c:sK2\fG/[8o\u0015\tI!\"\u0001\u0003ti\u0006$(BA\u0006\r\u0003\u0015iG\u000e\\5c\u0015\tia\"A\u0003ta\u0006\u00148N\u0003\u0002\u0010!\u00051\u0011\r]1dQ\u0016T\u0011!E\u0001\u0004_J<7C\u0001\u0001\u0014!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u001d!\t!R$\u0003\u0002\u001f+\t!QK\\5u\u0003I\u0019w.\u001c9vi\u0016\u001cuN\u001d:fY\u0006$\u0018n\u001c8\u0015\u0007\u0005\"C\u0006\u0005\u0002\u0015E%\u00111%\u0006\u0002\u0007\t>,(\r\\3\t\u000b\u0015\u0012\u0001\u0019\u0001\u0014\u0002\u0003a\u00042a\n\u0016\"\u001b\u0005A#BA\u0015\r\u0003\r\u0011H\rZ\u0005\u0003W!\u00121A\u0015#E\u0011\u0015i#\u00011\u0001'\u0003\u0005I\u0018\u0001G2p[B,H/Z\"peJ,G.\u0019;j_:l\u0015\r\u001e:jqR\u0011\u0001G\u000e\t\u0003cQj\u0011A\r\u0006\u0003g)\ta\u0001\\5oC2<\u0017BA\u001b3\u0005\u0019i\u0015\r\u001e:jq\")qg\u0001a\u0001q\u0005\t\u0001\fE\u0002(Ue\u0002\"!\r\u001e\n\u0005m\u0012$A\u0002,fGR|'/\u0001\u0011d_6\u0004X\u000f^3D_J\u0014X\r\\1uS>tw+\u001b;i\u001b\u0006$(/\u001b=J[BdGcA\u0011?\u007f!)Q\u0005\u0002a\u0001M!)Q\u0006\u0002a\u0001M\u0001"
)
public interface Correlation {
   double computeCorrelation(final RDD x, final RDD y);

   Matrix computeCorrelationMatrix(final RDD X);

   // $FF: synthetic method
   static double computeCorrelationWithMatrixImpl$(final Correlation $this, final RDD x, final RDD y) {
      return $this.computeCorrelationWithMatrixImpl(x, y);
   }

   default double computeCorrelationWithMatrixImpl(final RDD x, final RDD y) {
      RDD mat = x.zip(y, .MODULE$.Double()).map((x0$1) -> {
         if (x0$1 != null) {
            double xi = x0$1._1$mcD$sp();
            double yi = x0$1._2$mcD$sp();
            return new DenseVector(new double[]{xi, yi});
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(Vector.class));
      return this.computeCorrelationMatrix(mat).apply(0, 1);
   }

   static void $init$(final Correlation $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
