package cats.kernel.instances;

import cats.kernel.Group;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003+\u0001\u0011\u00051\u0006C\u00030\u0001\u0019\r\u0001\u0007C\u00033\u0001\u0011\u00051G\u0001\bGk:\u001cG/[8oa\u001d\u0013x.\u001e9\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\tA\u0011\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0015\u0005!1-\u0019;t\u0007\u0001)\"!\u0004\u000e\u0014\t\u0001qAc\t\t\u0003\u001fIi\u0011\u0001\u0005\u0006\u0002#\u0005)1oY1mC&\u00111\u0003\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007U1\u0002$D\u0001\u0006\u0013\t9RAA\bGk:\u001cG/[8oa5{gn\\5e!\tI\"\u0004\u0004\u0001\u0005\u000bm\u0001!\u0019\u0001\u000f\u0003\u0003\u0005\u000b\"!\b\u0011\u0011\u0005=q\u0012BA\u0010\u0011\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aD\u0011\n\u0005\t\u0002\"aA!osB\u0019A%J\u0014\u000e\u0003\u001dI!AJ\u0004\u0003\u000b\u001d\u0013x.\u001e9\u0011\u0007=A\u0003$\u0003\u0002*!\tIa)\u001e8di&|g\u000eM\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u00031\u0002\"aD\u0017\n\u00059\u0002\"\u0001B+oSR\f\u0011!Q\u000b\u0002cA\u0019A%\n\r\u0002\u000f%tg/\u001a:tKR\u0011q\u0005\u000e\u0005\u0006k\r\u0001\raJ\u0001\u0002q\u0002"
)
public interface Function0Group extends Function0Monoid, Group {
   Group A();

   // $FF: synthetic method
   static Function0 inverse$(final Function0Group $this, final Function0 x) {
      return $this.inverse(x);
   }

   default Function0 inverse(final Function0 x) {
      return () -> this.A().inverse(x.apply());
   }

   static void $init$(final Function0Group $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
