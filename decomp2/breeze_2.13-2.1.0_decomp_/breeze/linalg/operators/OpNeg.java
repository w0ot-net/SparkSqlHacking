package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.math.Ring;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019;Q\u0001B\u0003\t\u000211QAD\u0003\t\u0002=AQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0004\t\nQa\u00149OK\u001eT!AB\u0004\u0002\u0013=\u0004XM]1u_J\u001c(B\u0001\u0005\n\u0003\u0019a\u0017N\\1mO*\t!\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\ti\u0011!D\u0001\u0006\u0005\u0015y\u0005OT3h'\u0011\t\u0001CF\r\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\r\u0005s\u0017PU3g!\tiq#\u0003\u0002\u0019\u000b\t1q\n\u001d+za\u0016\u0004\"AG\u000f\u000e\u0003mQ!\u0001H\u0005\u0002\u000f\u001d,g.\u001a:jG&\u0011ad\u0007\u0002\u0011\u000b2,W.\u001a8uo&\u001cX-\u0016$v]\u000e\fa\u0001P5oSRtD#\u0001\u0007\u0002\u0019ILgn\u001a(fO\u0006$\u0018n\u001c8\u0016\u0005\r*DC\u0001\u0013?!\u0015)sFM\u001a4\u001d\t1SF\u0004\u0002(Y9\u0011\u0001fK\u0007\u0002S)\u0011!fC\u0001\u0007yI|w\u000e\u001e \n\u0003)I!\u0001H\u0005\n\u00059Z\u0012!B+Gk:\u001c\u0017B\u0001\u00192\u0005\u0015)\u0016*\u001c9m\u0015\tq3$D\u0001\u0002!\t!T\u0007\u0004\u0001\u0005\u000bY\u001a!\u0019A\u001c\u0003\u0003M\u000b\"\u0001O\u001e\u0011\u0005EI\u0014B\u0001\u001e\u0013\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0005\u001f\n\u0005u\u0012\"aA!os\"9qhAA\u0001\u0002\b\u0001\u0015aC3wS\u0012,gnY3%cE\u00022!\u0011#4\u001b\u0005\u0011%BA\"\n\u0003\u0011i\u0017\r\u001e5\n\u0005\u0015\u0013%\u0001\u0002*j]\u001e\u0004"
)
public final class OpNeg {
   public static UFunc.UImpl ringNegation(final Ring evidence$11) {
      return OpNeg$.MODULE$.ringNegation(evidence$11);
   }

   public static Object withSink(final Object s) {
      return OpNeg$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return OpNeg$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return OpNeg$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return OpNeg$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return OpNeg$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return OpNeg$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return OpNeg$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return OpNeg$.MODULE$.apply(v, impl);
   }
}
