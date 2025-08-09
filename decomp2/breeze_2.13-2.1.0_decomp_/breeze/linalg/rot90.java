package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r;Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\u0004yAQ!N\u0001\u0005\u0004Y\nQA]8usAR!a\u0002\u0005\u0002\r1Lg.\u00197h\u0015\u0005I\u0011A\u00022sK\u0016TXm\u0001\u0001\u0011\u00051\tQ\"\u0001\u0004\u0003\u000bI|G/\u000f\u0019\u0014\u0007\u0005yQ\u0003\u0005\u0002\u0011'5\t\u0011CC\u0001\u0013\u0003\u0015\u00198-\u00197b\u0013\t!\u0012C\u0001\u0004B]f\u0014VM\u001a\t\u0003-ei\u0011a\u0006\u0006\u00031!\tqaZ3oKJL7-\u0003\u0002\u001b/\t)QKR;oG\u00061A(\u001b8jiz\"\u0012aC\u0001\u0007S6\u0004H\u000eR'\u0016\u0005}IS#\u0001\u0011\u0011\u000b\u0005\u0012CE\r\u0013\u000e\u0003\u0005I!aI\r\u0003\u000b%k\u0007\u000f\u001c\u001a\u0011\u00071)s%\u0003\u0002'\r\tYA)\u001a8tK6\u000bGO]5y!\tA\u0013\u0006\u0004\u0001\u0005\u000b)\u001a!\u0019A\u0016\u0003\u0003Q\u000b\"\u0001L\u0018\u0011\u0005Ai\u0013B\u0001\u0018\u0012\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0005\u0019\n\u0005E\n\"aA!osB\u0011\u0001cM\u0005\u0003iE\u00111!\u00138u\u00039IW\u000e\u001d72MJ|W.S7qYJ*2a\u000e\u001f?)\tA\u0004\t\u0005\u0003\"smj\u0014B\u0001\u001e\u001a\u0005\u0011IU\u000e\u001d7\u0011\u0005!bD!\u0002\u0016\u0005\u0005\u0004Y\u0003C\u0001\u0015?\t\u0015yDA1\u0001,\u0005\u0005\u0011\u0006\"B!\u0005\u0001\b\u0011\u0015!B5na2\u0014\u0004#B\u0011#wIj\u0004"
)
public final class rot90 {
   public static UFunc.UImpl impl1fromImpl2(final UFunc.UImpl2 impl2) {
      return rot90$.MODULE$.impl1fromImpl2(impl2);
   }

   public static UFunc.UImpl2 implDM() {
      return rot90$.MODULE$.implDM();
   }

   public static Object withSink(final Object s) {
      return rot90$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return rot90$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return rot90$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return rot90$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return rot90$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return rot90$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return rot90$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return rot90$.MODULE$.apply(v, impl);
   }
}
