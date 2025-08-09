package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y9Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQaF\u0001\u0005\u0002aAQ!G\u0001\u0005Ri\t\u0011c\u00195fEf\u001c\b.\u001a<ESN$\u0018M\\2f\u0015\t1q!\u0001\u0004mS:\fGn\u001a\u0006\u0002\u0011\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\f\u00035\tQAA\tdQ\u0016\u0014\u0017p\u001d5fm\u0012K7\u000f^1oG\u0016\u001c2!\u0001\b\u0015!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fMB\u00111\"F\u0005\u0003-\u0015\u0011\u0011CT8s[\n\u000b7/\u001a3ESN$\u0018M\\2f\u0003\u0019a\u0014N\\5u}Q\t!\"\u0001\u0007o_Jl7i\u001c8ti\u0006tG/F\u0001\u001c!\tyA$\u0003\u0002\u001e!\t1Ai\\;cY\u0016\u0004"
)
public final class chebyshevDistance {
   public static UFunc.UImpl2 distanceFromNormAndSub(final UFunc.UImpl2 subImpl, final UFunc.UImpl2 normImpl) {
      return chebyshevDistance$.MODULE$.distanceFromNormAndSub(subImpl, normImpl);
   }

   public static Object withSink(final Object s) {
      return chebyshevDistance$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return chebyshevDistance$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return chebyshevDistance$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return chebyshevDistance$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return chebyshevDistance$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return chebyshevDistance$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return chebyshevDistance$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return chebyshevDistance$.MODULE$.apply(v, impl);
   }
}
