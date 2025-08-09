package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m9Qa\u0001\u0003\t\u0002%1Qa\u0003\u0003\t\u00021AQ!G\u0001\u0005\u0002i\t\u0011B_5q-\u0006dW/Z:\u000b\u0005\u00151\u0011A\u00027j]\u0006dwMC\u0001\b\u0003\u0019\u0011'/Z3{K\u000e\u0001\u0001C\u0001\u0006\u0002\u001b\u0005!!!\u0003>jaZ\u000bG.^3t'\r\tQb\u0005\t\u0003\u001dEi\u0011a\u0004\u0006\u0002!\u0005)1oY1mC&\u0011!c\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Q9R\"A\u000b\u000b\u0005Y1\u0011aB4f]\u0016\u0014\u0018nY\u0005\u00031U\u0011Q!\u0016$v]\u000e\fa\u0001P5oSRtD#A\u0005"
)
public final class zipValues {
   public static Object withSink(final Object s) {
      return zipValues$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return zipValues$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return zipValues$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return zipValues$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return zipValues$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return zipValues$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return zipValues$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return zipValues$.MODULE$.apply(v, impl);
   }
}
