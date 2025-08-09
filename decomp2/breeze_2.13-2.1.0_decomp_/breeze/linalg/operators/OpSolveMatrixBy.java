package breeze.linalg.operators;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0001:Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQAH\u0001\u0005\u0002}\tqb\u00149T_24X-T1ue&D()\u001f\u0006\u0003\u000b\u0019\t\u0011b\u001c9fe\u0006$xN]:\u000b\u0005\u001dA\u0011A\u00027j]\u0006dwMC\u0001\n\u0003\u0019\u0011'/Z3{K\u000e\u0001\u0001C\u0001\u0007\u0002\u001b\u0005!!aD(q'>dg/Z'biJL\u0007PQ=\u0014\t\u0005yQ\u0003\u0007\t\u0003!Mi\u0011!\u0005\u0006\u0002%\u0005)1oY1mC&\u0011A#\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u000511\u0012BA\f\u0005\u0005\u0019y\u0005\u000fV=qKB\u0011\u0011\u0004H\u0007\u00025)\u00111\u0004C\u0001\bO\u0016tWM]5d\u0013\ti\"DA\u0003V\rVt7-\u0001\u0004=S:LGO\u0010\u000b\u0002\u0017\u0001"
)
public final class OpSolveMatrixBy {
   public static Object withSink(final Object s) {
      return OpSolveMatrixBy$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return OpSolveMatrixBy$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return OpSolveMatrixBy$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return OpSolveMatrixBy$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return OpSolveMatrixBy$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return OpSolveMatrixBy$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return OpSolveMatrixBy$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return OpSolveMatrixBy$.MODULE$.apply(v, impl);
   }
}
