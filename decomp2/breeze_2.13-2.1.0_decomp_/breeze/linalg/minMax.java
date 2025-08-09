package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseValues;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r<Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQ!H\u0001\u0005\u0002yAQaH\u0001\u0005\u0004\u0001BQaP\u0001\u0005\u0004\u0001CQaS\u0001\u0005\u00041CQaV\u0001\u0005\u0004a\u000ba!\\5o\u001b\u0006D(BA\u0005\u000b\u0003\u0019a\u0017N\\1mO*\t1\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\tq\u0011!D\u0001\t\u0005\u0019i\u0017N\\'bqN\u0019\u0011!E\f\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\r\u0005s\u0017PU3g!\tA2$D\u0001\u001a\u0015\tQ\"\"A\u0004hK:,'/[2\n\u0005qI\"!B+Gk:\u001c\u0017A\u0002\u001fj]&$h\bF\u0001\u000e\u0003)\u0011X\rZ;dK~Ke\u000e^\u000b\u0003C!\"\"AI\u001c\u0011\t\r\"c%M\u0007\u0002\u0003%\u0011Qe\u0007\u0002\u0005\u00136\u0004H\u000e\u0005\u0002(Q1\u0001A!B\u0015\u0004\u0005\u0004Q#!\u0001+\u0012\u0005-r\u0003C\u0001\n-\u0013\ti3CA\u0004O_RD\u0017N\\4\u0011\u0005Iy\u0013B\u0001\u0019\u0014\u0005\r\te.\u001f\t\u0005%I\"D'\u0003\u00024'\t1A+\u001e9mKJ\u0002\"AE\u001b\n\u0005Y\u001a\"aA%oi\")\u0001h\u0001a\u0002s\u0005!\u0011\u000e^3s!\u0011QTH\n\u001b\u000e\u0003mR!\u0001\u0010\u0005\u0002\u000fM,\b\u000f]8si&\u0011ah\u000f\u0002\u0012\u0007\u0006tGK]1wKJ\u001cXMV1mk\u0016\u001c\u0018!\u0004:fIV\u001cWm\u0018#pk\ndW-\u0006\u0002B\tR\u0011!)\u0013\t\u0005G\u0011\u001aU\t\u0005\u0002(\t\u0012)\u0011\u0006\u0002b\u0001UA!!C\r$G!\t\u0011r)\u0003\u0002I'\t1Ai\\;cY\u0016DQ\u0001\u000f\u0003A\u0004)\u0003BAO\u001fD\r\u0006a!/\u001a3vG\u0016|f\t\\8biV\u0011Q\n\u0015\u000b\u0003\u001dV\u0003Ba\t\u0013P#B\u0011q\u0005\u0015\u0003\u0006S\u0015\u0011\rA\u000b\t\u0005%I\u0012&\u000b\u0005\u0002\u0013'&\u0011Ak\u0005\u0002\u0006\r2|\u0017\r\u001e\u0005\u0006q\u0015\u0001\u001dA\u0016\t\u0005uuz%+A\u0006sK\u0012,8-Z0M_:<WCA-])\tQ\u0016\r\u0005\u0003$Imk\u0006CA\u0014]\t\u0015IcA1\u0001+!\u0011\u0011\"G\u00180\u0011\u0005Iy\u0016B\u00011\u0014\u0005\u0011auN\\4\t\u000ba2\u00019\u00012\u0011\tij4L\u0018"
)
public final class minMax {
   public static UFunc.UImpl reduce_Long(final CanTraverseValues iter) {
      return minMax$.MODULE$.reduce_Long(iter);
   }

   public static UFunc.UImpl reduce_Float(final CanTraverseValues iter) {
      return minMax$.MODULE$.reduce_Float(iter);
   }

   public static UFunc.UImpl reduce_Double(final CanTraverseValues iter) {
      return minMax$.MODULE$.reduce_Double(iter);
   }

   public static UFunc.UImpl reduce_Int(final CanTraverseValues iter) {
      return minMax$.MODULE$.reduce_Int(iter);
   }

   public static Object withSink(final Object s) {
      return minMax$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return minMax$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return minMax$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return minMax$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return minMax$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return minMax$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return minMax$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return minMax$.MODULE$.apply(v, impl);
   }
}
