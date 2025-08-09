package breeze.signal;

import breeze.generic.UFunc;
import breeze.storage.Zero;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\tQ\"\u001b$pkJLWM]*iS\u001a$(B\u0001\u0004\b\u0003\u0019\u0019\u0018n\u001a8bY*\t\u0001\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\tY\u0011!D\u0001\u0006\u00055Igi\\;sS\u0016\u00148\u000b[5giN\u0019\u0011A\u0004\u000b\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g!\t)\u0002$D\u0001\u0017\u0015\t9r!A\u0004hK:,'/[2\n\u0005e1\"!B+Gk:\u001c\u0017A\u0002\u001fj]&$h\bF\u0001\u000b\u0003EIW\u000e\u001d7J\r>,(/[3s'\"Lg\r^\u000b\u0003=-\"2a\b\u001b=!\u0011\u0001\u0013eI\u0012\u000e\u0003\u0005I!A\t\r\u0003\t%k\u0007\u000f\u001c\t\u0004I\u001dJS\"A\u0013\u000b\u0005\u0019:\u0011A\u00027j]\u0006dw-\u0003\u0002)K\tYA)\u001a8tKZ+7\r^8s!\tQ3\u0006\u0004\u0001\u0005\u000b1\u001a!\u0019A\u0017\u0003\u0003Q\u000b\"AL\u0019\u0011\u0005=y\u0013B\u0001\u0019\u0011\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"a\u0004\u001a\n\u0005M\u0002\"aA!os\"9QgAA\u0001\u0002\b1\u0014AC3wS\u0012,gnY3%cA\u0019qGO\u0015\u000e\u0003aR!!O\u0004\u0002\u000fM$xN]1hK&\u00111\b\u000f\u0002\u00055\u0016\u0014x\u000eC\u0004>\u0007\u0005\u0005\t9\u0001 \u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0002@\u0005&j\u0011\u0001\u0011\u0006\u0003\u0003B\tqA]3gY\u0016\u001cG/\u0003\u0002D\u0001\nA1\t\\1tgR\u000bw\r"
)
public final class iFourierShift {
   public static UFunc.UImpl implIFourierShift(final Zero evidence$1, final ClassTag evidence$2) {
      return iFourierShift$.MODULE$.implIFourierShift(evidence$1, evidence$2);
   }

   public static Object withSink(final Object s) {
      return iFourierShift$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return iFourierShift$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return iFourierShift$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return iFourierShift$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return iFourierShift$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return iFourierShift$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return iFourierShift$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return iFourierShift$.MODULE$.apply(v, impl);
   }
}
