package breeze.linalg.operators;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0001:Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQAH\u0001\u0005\u0002}\tQa\u00149N_\u0012T!!\u0002\u0004\u0002\u0013=\u0004XM]1u_J\u001c(BA\u0004\t\u0003\u0019a\u0017N\\1mO*\t\u0011\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\ta\u0011!D\u0001\u0005\u0005\u0015y\u0005/T8e'\u0011\tq\"\u0006\r\u0011\u0005A\u0019R\"A\t\u000b\u0003I\tQa]2bY\u0006L!\u0001F\t\u0003\r\u0005s\u0017PU3g!\taa#\u0003\u0002\u0018\t\t1q\n\u001d+za\u0016\u0004\"!\u0007\u000f\u000e\u0003iQ!a\u0007\u0005\u0002\u000f\u001d,g.\u001a:jG&\u0011QD\u0007\u0002\u0011\u000b2,W.\u001a8uo&\u001cX-\u0016$v]\u000e\fa\u0001P5oSRtD#A\u0006"
)
public final class OpMod {
   public static Object withSink(final Object s) {
      return OpMod$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return OpMod$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return OpMod$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return OpMod$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return OpMod$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return OpMod$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return OpMod$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return OpMod$.MODULE$.apply(v, impl);
   }
}
