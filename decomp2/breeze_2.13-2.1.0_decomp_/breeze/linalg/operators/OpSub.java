package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.math.Ring;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u:Q\u0001B\u0003\t\u000211QAD\u0003\t\u0002=AQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0004\t\nQa\u00149Tk\nT!AB\u0004\u0002\u0013=\u0004XM]1u_J\u001c(B\u0001\u0005\n\u0003\u0019a\u0017N\\1mO*\t!\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\ti\u0011!D\u0001\u0006\u0005\u0015y\u0005oU;c'\u0011\t\u0001CF\r\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\r\u0005s\u0017PU3g!\tiq#\u0003\u0002\u0019\u000b\t1q\n\u001d+za\u0016\u0004\"AG\u000f\u000e\u0003mQ!\u0001H\u0005\u0002\u000f\u001d,g.\u001a:jG&\u0011ad\u0007\u0002\u0011\u000b2,W.\u001a8uo&\u001cX-\u0016$v]\u000e\fa\u0001P5oSRtD#\u0001\u0007\u0002\u001b=\u00048+\u001e2Ge>l'+\u001b8h+\t\u0019C\u0006\u0006\u0002%kA)QE\n\u0016+U5\t\u0011!\u0003\u0002(Q\t)\u0011*\u001c9me%\u0011\u0011f\u0007\u0002\u0006+\u001a+hn\u0019\t\u0003W1b\u0001\u0001B\u0003.\u0007\t\u0007aFA\u0001T#\ty#\u0007\u0005\u0002\u0012a%\u0011\u0011G\u0005\u0002\b\u001d>$\b.\u001b8h!\t\t2'\u0003\u00025%\t\u0019\u0011I\\=\t\u000fY\u001a\u0011\u0011!a\u0002o\u0005QQM^5eK:\u001cW\r\n\u001a\u0011\u0007aZ$&D\u0001:\u0015\tQ\u0014\"\u0001\u0003nCRD\u0017B\u0001\u001f:\u0005\u0011\u0011\u0016N\\4"
)
public final class OpSub {
   public static UFunc.UImpl2 opSubFromRing(final Ring evidence$2) {
      return OpSub$.MODULE$.opSubFromRing(evidence$2);
   }

   public static Object withSink(final Object s) {
      return OpSub$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return OpSub$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return OpSub$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return OpSub$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return OpSub$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return OpSub$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return OpSub$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return OpSub$.MODULE$.apply(v, impl);
   }
}
