package breeze.stats.regression;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I:Q\u0001B\u0003\t\n11QAD\u0003\t\n=AQAF\u0001\u0005\u0002]AQ\u0001G\u0001\u0005\u0002e\t!\u0004\\3bgR\u001c\u0016/^1sKNLU\u000e\u001d7f[\u0016tG/\u0019;j_:T!AB\u0004\u0002\u0015I,wM]3tg&|gN\u0003\u0002\t\u0013\u0005)1\u000f^1ug*\t!\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\ti\u0011!D\u0001\u0006\u0005iaW-Y:u'F,\u0018M]3t\u00136\u0004H.Z7f]R\fG/[8o'\t\t\u0001\u0003\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00031\ta\u0002Z8MK\u0006\u001cHoU9vCJ,7\u000f\u0006\u0003\u001b;!j\u0003CA\u0007\u001c\u0013\taRA\u0001\u000fMK\u0006\u001cHoU9vCJ,7OU3he\u0016\u001c8/[8o%\u0016\u001cX\u000f\u001c;\t\u000by\u0019\u0001\u0019A\u0010\u0002\t\u0011\fG/\u0019\t\u0004A\r*S\"A\u0011\u000b\u0005\tJ\u0011A\u00027j]\u0006dw-\u0003\u0002%C\tYA)\u001a8tK6\u000bGO]5y!\t\tb%\u0003\u0002(%\t1Ai\\;cY\u0016DQ!K\u0002A\u0002)\nqa\\;uaV$8\u000fE\u0002!W\u0015J!\u0001L\u0011\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\u0005\u0006]\r\u0001\raL\u0001\no>\u00148.\u0011:sCf\u00042!\u0005\u0019&\u0013\t\t$CA\u0003BeJ\f\u0017\u0010"
)
public final class leastSquaresImplementation {
   public static LeastSquaresRegressionResult doLeastSquares(final DenseMatrix data, final DenseVector outputs, final double[] workArray) {
      return leastSquaresImplementation$.MODULE$.doLeastSquares(data, outputs, workArray);
   }
}
