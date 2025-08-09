package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E:Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\ta!\u001e8jcV,'B\u0001\u0004\b\u0003\u0019a\u0017N\\1mO*\t\u0001\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\tY\u0011!D\u0001\u0006\u0005\u0019)h.[9vKN\u0019\u0011A\u0004\u000b\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g!\t)\u0002$D\u0001\u0017\u0015\t9r!A\u0004hK:,'/[2\n\u0005e1\"!B+Gk:\u001c\u0017A\u0002\u001fj]&$h\bF\u0001\u000b\u0003\u0011IW\u000e\u001d7\u0016\u0005yAS#A\u0010\u0011\t\u0001\n3eI\u0007\u0002\u0003%\u0011!\u0005\u0007\u0002\u0005\u00136\u0004H\u000eE\u0002\fI\u0019J!!J\u0003\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\t\u0003O!b\u0001\u0001B\u0003*\u0007\t\u0007!FA\u0001T#\tYc\u0006\u0005\u0002\u0010Y%\u0011Q\u0006\u0005\u0002\b\u001d>$\b.\u001b8h!\tyq&\u0003\u00021!\t\u0019\u0011I\\="
)
public final class unique {
   public static UFunc.UImpl impl() {
      return unique$.MODULE$.impl();
   }

   public static Object withSink(final Object s) {
      return unique$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return unique$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return unique$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return unique$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return unique$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return unique$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return unique$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return unique$.MODULE$.apply(v, impl);
   }
}
