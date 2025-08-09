package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E:Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\taA\u001a7ja2\u0014(B\u0001\u0004\b\u0003\u0019a\u0017N\\1mO*\t\u0001\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\tY\u0011!D\u0001\u0006\u0005\u00191G.\u001b9meN\u0019\u0011A\u0004\u000b\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g!\t)\u0002$D\u0001\u0017\u0015\t9r!A\u0004hK:,'/[2\n\u0005e1\"!B+Gk:\u001c\u0017A\u0002\u001fj]&$h\bF\u0001\u000b\u0003\u0019IW\u000e\u001d7E\u001bV\u0011a\u0004K\u000b\u0002?A!\u0001%I\u0012$\u001b\u0005\t\u0011B\u0001\u0012\u0019\u0005\u0011IU\u000e\u001d7\u0011\u0007-!c%\u0003\u0002&\u000b\tYA)\u001a8tK6\u000bGO]5y!\t9\u0003\u0006\u0004\u0001\u0005\u000b%\u001a!\u0019\u0001\u0016\u0003\u0003Q\u000b\"a\u000b\u0018\u0011\u0005=a\u0013BA\u0017\u0011\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aD\u0018\n\u0005A\u0002\"aA!os\u0002"
)
public final class fliplr {
   public static UFunc.UImpl implDM() {
      return fliplr$.MODULE$.implDM();
   }

   public static Object withSink(final Object s) {
      return fliplr$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return fliplr$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return fliplr$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return fliplr$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return fliplr$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return fliplr$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return fliplr$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return fliplr$.MODULE$.apply(v, impl);
   }
}
