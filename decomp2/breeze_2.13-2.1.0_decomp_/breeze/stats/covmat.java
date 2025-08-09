package breeze.stats;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t;Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQ!H\u0001\u0005\u0002yAqaH\u0001C\u0002\u0013\r\u0001\u0005\u0003\u0004/\u0003\u0001\u0006I!\t\u0005\b_\u0005\u0011\r\u0011b\u00011\u0011\u0019\t\u0015\u0001)A\u0005c\u000511m\u001c<nCRT!!\u0003\u0006\u0002\u000bM$\u0018\r^:\u000b\u0003-\taA\u0019:fKj,7\u0001\u0001\t\u0003\u001d\u0005i\u0011\u0001\u0003\u0002\u0007G>4X.\u0019;\u0014\u0007\u0005\tr\u0003\u0005\u0002\u0013+5\t1CC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0013\t12C\u0001\u0004B]f\u0014VM\u001a\t\u00031mi\u0011!\u0007\u0006\u00035)\tqaZ3oKJL7-\u0003\u0002\u001d3\t)QKR;oG\u00061A(\u001b8jiz\"\u0012!D\u0001\u0011[\u0006$(/\u001b=D_Z\f'/[1oG\u0016,\u0012!\t\t\u0005E\r*S%D\u0001\u0002\u0013\t!3D\u0001\u0003J[Bd\u0007c\u0001\u0014*W5\tqE\u0003\u0002)\u0015\u00051A.\u001b8bY\u001eL!AK\u0014\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e\u001f\t\u0003%1J!!L\n\u0003\r\u0011{WO\u00197f\u0003Ei\u0017\r\u001e:jq\u000e{g/\u0019:jC:\u001cW\rI\u0001\u0013g\u0016\fX/\u001a8dK\u000e{g/\u0019:jC:\u001cW-F\u00012!\u0011\u00113EM\u0013\u0011\u0007MZdH\u0004\u00025s9\u0011Q\u0007O\u0007\u0002m)\u0011q\u0007D\u0001\u0007yI|w\u000e\u001e \n\u0003QI!AO\n\u0002\u000fA\f7m[1hK&\u0011A(\u0010\u0002\u0004'\u0016\f(B\u0001\u001e\u0014!\r1shK\u0005\u0003\u0001\u001e\u00121\u0002R3og\u00164Vm\u0019;pe\u0006\u00192/Z9vK:\u001cWmQ8wCJL\u0017M\\2fA\u0001"
)
public final class covmat {
   public static UFunc.UImpl sequenceCovariance() {
      return covmat$.MODULE$.sequenceCovariance();
   }

   public static UFunc.UImpl matrixCovariance() {
      return covmat$.MODULE$.matrixCovariance();
   }

   public static Object withSink(final Object s) {
      return covmat$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return covmat$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return covmat$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return covmat$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return covmat$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return covmat$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return covmat$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return covmat$.MODULE$.apply(v, impl);
   }
}
