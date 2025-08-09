package org.apache.spark.mllib.stat.correlation;

import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0001<a\u0001C\u0005\t\u0002-)bAB\f\n\u0011\u0003Y\u0001\u0004C\u0003 \u0003\u0011\u0005\u0011\u0005C\u0003#\u0003\u0011\u00051\u0005C\u0004?\u0003E\u0005I\u0011A \t\u000b)\u000bA\u0011A&\t\u000fe\u000b\u0011\u0013!C\u0001\u007f!)!,\u0001C\u00017\u0006a1i\u001c:sK2\fG/[8og*\u0011!bC\u0001\fG>\u0014(/\u001a7bi&|gN\u0003\u0002\r\u001b\u0005!1\u000f^1u\u0015\tqq\"A\u0003nY2L'M\u0003\u0002\u0011#\u0005)1\u000f]1sW*\u0011!cE\u0001\u0007CB\f7\r[3\u000b\u0003Q\t1a\u001c:h!\t1\u0012!D\u0001\n\u00051\u0019uN\u001d:fY\u0006$\u0018n\u001c8t'\t\t\u0011\u0004\u0005\u0002\u001b;5\t1DC\u0001\u001d\u0003\u0015\u00198-\u00197b\u0013\tq2D\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tQ#\u0001\u0003d_J\u0014H\u0003\u0002\u0013(_E\u0002\"AG\u0013\n\u0005\u0019Z\"A\u0002#pk\ndW\rC\u0003)\u0007\u0001\u0007\u0011&A\u0001y!\rQS\u0006J\u0007\u0002W)\u0011AfD\u0001\u0004e\u0012$\u0017B\u0001\u0018,\u0005\r\u0011F\t\u0012\u0005\u0006a\r\u0001\r!K\u0001\u0002s\"9!g\u0001I\u0001\u0002\u0004\u0019\u0014AB7fi\"|G\r\u0005\u00025w9\u0011Q'\u000f\t\u0003mmi\u0011a\u000e\u0006\u0003q\u0001\na\u0001\u0010:p_Rt\u0014B\u0001\u001e\u001c\u0003\u0019\u0001&/\u001a3fM&\u0011A(\u0010\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005iZ\u0012AD2peJ$C-\u001a4bk2$HeM\u000b\u0002\u0001*\u00121'Q\u0016\u0002\u0005B\u00111\tS\u0007\u0002\t*\u0011QIR\u0001\nk:\u001c\u0007.Z2lK\u0012T!aR\u000e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002J\t\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u0015\r|'O]'biJL\u0007\u0010F\u0002M%b\u0003\"!\u0014)\u000e\u00039S!aT\u0007\u0002\r1Lg.\u00197h\u0013\t\tfJ\u0001\u0004NCR\u0014\u0018\u000e\u001f\u0005\u0006'\u0016\u0001\r\u0001V\u0001\u00021B\u0019!&L+\u0011\u000553\u0016BA,O\u0005\u00191Vm\u0019;pe\"9!'\u0002I\u0001\u0002\u0004\u0019\u0014\u0001F2peJl\u0015\r\u001e:jq\u0012\"WMZ1vYR$#'\u0001\fhKR\u001cuN\u001d:fY\u0006$\u0018n\u001c8Ge>lg*Y7f)\tav\f\u0005\u0002\u0017;&\u0011a,\u0003\u0002\f\u0007>\u0014(/\u001a7bi&|g\u000eC\u00033\u000f\u0001\u00071\u0007"
)
public final class Correlations {
   public static Correlation getCorrelationFromName(final String method) {
      return Correlations$.MODULE$.getCorrelationFromName(method);
   }

   public static String corrMatrix$default$2() {
      return Correlations$.MODULE$.corrMatrix$default$2();
   }

   public static Matrix corrMatrix(final RDD X, final String method) {
      return Correlations$.MODULE$.corrMatrix(X, method);
   }

   public static String corr$default$3() {
      return Correlations$.MODULE$.corr$default$3();
   }

   public static double corr(final RDD x, final RDD y, final String method) {
      return Correlations$.MODULE$.corr(x, y, method);
   }
}
