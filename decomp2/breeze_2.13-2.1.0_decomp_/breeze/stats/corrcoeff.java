package breeze.stats;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u:Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\t\u0011bY8se\u000e|WM\u001a4\u000b\u0005\u00199\u0011!B:uCR\u001c(\"\u0001\u0005\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"aC\u0001\u000e\u0003\u0015\u0011\u0011bY8se\u000e|WM\u001a4\u0014\u0007\u0005qA\u0003\u0005\u0002\u0010%5\t\u0001CC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0002C\u0001\u0004B]f\u0014VM\u001a\t\u0003+ai\u0011A\u0006\u0006\u0003/\u001d\tqaZ3oKJL7-\u0003\u0002\u001a-\t)QKR;oG\u00061A(\u001b8jiz\"\u0012AC\u0001\u0012[\u0006$(/\u001b=D_J\u0014X\r\\1uS>tWC\u0001\u0010&)\tyr\u0007\u0005\u0003!C\rrS\"A\u0001\n\u0005\tB\"\u0001B%na2\u0004\"\u0001J\u0013\r\u0001\u0011)ae\u0001b\u0001O\t\tA+\u0005\u0002)WA\u0011q\"K\u0005\u0003UA\u0011qAT8uQ&tw\r\u0005\u0002\u0010Y%\u0011Q\u0006\u0005\u0002\u0004\u0003:L\bcA\u00183i5\t\u0001G\u0003\u00022\u000f\u00051A.\u001b8bY\u001eL!a\r\u0019\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e\u001f\t\u0003\u001fUJ!A\u000e\t\u0003\r\u0011{WO\u00197f\u0011\u0015A4\u0001q\u0001:\u0003Q\u0019wN^1sS\u0006t7-Z\"bY\u000e,H.\u0019;peB!!(I\u0012/\u001d\tY1(\u0003\u0002=\u000b\u000511m\u001c<nCR\u0004"
)
public final class corrcoeff {
   public static UFunc.UImpl matrixCorrelation(final UFunc.UImpl covarianceCalculator) {
      return corrcoeff$.MODULE$.matrixCorrelation(covarianceCalculator);
   }

   public static Object withSink(final Object s) {
      return corrcoeff$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return corrcoeff$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return corrcoeff$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return corrcoeff$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return corrcoeff$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return corrcoeff$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return corrcoeff$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return corrcoeff$.MODULE$.apply(v, impl);
   }
}
