package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m:Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\u0004yAQAM\u0001\u0005\u0004M\naA\u001a7jaV$'BA\u0004\t\u0003\u0019a\u0017N\\1mO*\t\u0011\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\ta\u0011!D\u0001\u0007\u0005\u00191G.\u001b9vIN\u0019\u0011aD\u000b\u0011\u0005A\u0019R\"A\t\u000b\u0003I\tQa]2bY\u0006L!\u0001F\t\u0003\r\u0005s\u0017PU3g!\t1\u0012$D\u0001\u0018\u0015\tA\u0002\"A\u0004hK:,'/[2\n\u0005i9\"!B+Gk:\u001c\u0017A\u0002\u001fj]&$h\bF\u0001\f\u0003\u0019IW\u000e\u001d7E\u001bV\u0011q$K\u000b\u0002AA!\u0011E\t\u0013%\u001b\u0005\t\u0011BA\u0012\u001a\u0005\u0011IU\u000e\u001d7\u0011\u00071)s%\u0003\u0002'\r\tYA)\u001a8tK6\u000bGO]5y!\tA\u0013\u0006\u0004\u0001\u0005\u000b)\u001a!\u0019A\u0016\u0003\u0003Q\u000b\"\u0001L\u0018\u0011\u0005Ai\u0013B\u0001\u0018\u0012\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0005\u0019\n\u0005E\n\"aA!os\u00061\u0011.\u001c9m\tZ+\"\u0001\u000e\u001e\u0016\u0003U\u0002B!\t\u00127mA\u0019AbN\u001d\n\u0005a2!a\u0003#f]N,g+Z2u_J\u0004\"\u0001\u000b\u001e\u0005\u000b)\"!\u0019A\u0016"
)
public final class flipud {
   public static UFunc.UImpl implDV() {
      return flipud$.MODULE$.implDV();
   }

   public static UFunc.UImpl implDM() {
      return flipud$.MODULE$.implDM();
   }

   public static Object withSink(final Object s) {
      return flipud$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return flipud$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return flipud$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return flipud$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return flipud$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return flipud$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return flipud$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return flipud$.MODULE$.apply(v, impl);
   }
}
