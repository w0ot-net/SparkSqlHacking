package breeze.stats.regression;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%;Q!\u0003\u0006\t\u0002E1Qa\u0005\u0006\t\u0002QAQ!I\u0001\u0005\u0002\tBqaI\u0001C\u0002\u0013\rA\u0005\u0003\u0004<\u0003\u0001\u0006I!\n\u0005\by\u0005\u0011\r\u0011b\u0001>\u0011\u0019\u0011\u0015\u0001)A\u0005}!91)\u0001b\u0001\n\u0007!\u0005B\u0002%\u0002A\u0003%Q)\u0001\u0007mK\u0006\u001cHoU9vCJ,7O\u0003\u0002\f\u0019\u0005Q!/Z4sKN\u001c\u0018n\u001c8\u000b\u00055q\u0011!B:uCR\u001c(\"A\b\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"AE\u0001\u000e\u0003)\u0011A\u0002\\3bgR\u001c\u0016/^1sKN\u001c2!A\u000b\u001c!\t1\u0012$D\u0001\u0018\u0015\u0005A\u0012!B:dC2\f\u0017B\u0001\u000e\u0018\u0005\u0019\te.\u001f*fMB\u0011AdH\u0007\u0002;)\u0011aDD\u0001\bO\u0016tWM]5d\u0013\t\u0001SDA\u0003V\rVt7-\u0001\u0004=S:LGO\u0010\u000b\u0002#\u0005IR.\u0019;sSb4Vm\u0019;pe^KG\u000f[,pe.\f%O]1z+\u0005)\u0003C\u0002\u0014(SI*\u0004(D\u0001\u0002\u0013\tAsDA\u0003J[Bd7\u0007E\u0002+[=j\u0011a\u000b\u0006\u0003Y9\ta\u0001\\5oC2<\u0017B\u0001\u0018,\u0005-!UM\\:f\u001b\u0006$(/\u001b=\u0011\u0005Y\u0001\u0014BA\u0019\u0018\u0005\u0019!u.\u001e2mKB\u0019!fM\u0018\n\u0005QZ#a\u0003#f]N,g+Z2u_J\u00042A\u0006\u001c0\u0013\t9tCA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0013s%\u0011!H\u0003\u0002\u001d\u0019\u0016\f7\u000f^*rk\u0006\u0014Xm\u001d*fOJ,7o]5p]J+7/\u001e7u\u0003ii\u0017\r\u001e:jqZ+7\r^8s/&$\bnV8sW\u0006\u0013(/Y=!\u0003ei\u0017\r\u001e:jqZ+7\r^8s'B,7-\u001b4jK\u0012<vN]6\u0016\u0003y\u0002bAJ\u0014*e}B\u0004C\u0001\fA\u0013\t\tuCA\u0002J]R\f!$\\1ue&Dh+Z2u_J\u001c\u0006/Z2jM&,GmV8sW\u0002\nA\"\\1ue&Dh+Z2u_J,\u0012!\u0012\t\u0006M\u0019K#\u0007O\u0005\u0003\u000f~\u0011Q!S7qYJ\nQ\"\\1ue&Dh+Z2u_J\u0004\u0003"
)
public final class leastSquares {
   public static UFunc.UImpl2 matrixVector() {
      return leastSquares$.MODULE$.matrixVector();
   }

   public static UFunc.UImpl3 matrixVectorSpecifiedWork() {
      return leastSquares$.MODULE$.matrixVectorSpecifiedWork();
   }

   public static UFunc.UImpl3 matrixVectorWithWorkArray() {
      return leastSquares$.MODULE$.matrixVectorWithWorkArray();
   }

   public static Object withSink(final Object s) {
      return leastSquares$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return leastSquares$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return leastSquares$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return leastSquares$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return leastSquares$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return leastSquares$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return leastSquares$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return leastSquares$.MODULE$.apply(v, impl);
   }
}
