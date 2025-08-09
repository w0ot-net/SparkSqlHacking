package cats.kernel.instances;

import cats.kernel.Group;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003.\u0001\u0011\u0005a\u0006C\u00033\u0001\u0019\r1\u0007C\u00036\u0001\u0011\u0005aG\u0001\bGk:\u001cG/[8oc\u001d\u0013x.\u001e9\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\tA\u0011\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0015\u0005!1-\u0019;t\u0007\u0001)2!\u0004\u000e%'\u0011\u0001a\u0002\u0006\u0014\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g!\u0011)b\u0003G\u0012\u000e\u0003\u0015I!aF\u0003\u0003\u001f\u0019+hn\u0019;j_:\fTj\u001c8pS\u0012\u0004\"!\u0007\u000e\r\u0001\u0011)1\u0004\u0001b\u00019\t\t\u0011)\u0005\u0002\u001eAA\u0011qBH\u0005\u0003?A\u0011qAT8uQ&tw\r\u0005\u0002\u0010C%\u0011!\u0005\u0005\u0002\u0004\u0003:L\bCA\r%\t\u0015)\u0003A1\u0001\u001d\u0005\u0005\u0011\u0005cA\u0014)U5\tq!\u0003\u0002*\u000f\t)qI]8vaB!qb\u000b\r$\u0013\ta\u0003CA\u0005Gk:\u001cG/[8oc\u00051A%\u001b8ji\u0012\"\u0012a\f\t\u0003\u001fAJ!!\r\t\u0003\tUs\u0017\u000e^\u0001\u0002\u0005V\tA\u0007E\u0002(Q\r\nq!\u001b8wKJ\u001cX\r\u0006\u0002+o!)\u0001h\u0001a\u0001U\u0005\t\u0001\u0010"
)
public interface Function1Group extends Function1Monoid, Group {
   Group B();

   // $FF: synthetic method
   static Function1 inverse$(final Function1Group $this, final Function1 x) {
      return $this.inverse(x);
   }

   default Function1 inverse(final Function1 x) {
      return (a) -> this.B().inverse(x.apply(a));
   }

   static void $init$(final Function1Group $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
