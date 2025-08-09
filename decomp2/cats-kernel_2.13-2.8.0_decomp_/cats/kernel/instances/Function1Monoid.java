package cats.kernel.instances;

import cats.kernel.Monoid;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003.\u0001\u0011\u0005a\u0006C\u00033\u0001\u0019\r1\u0007C\u00046\u0001\t\u0007I\u0011\u0001\u001c\u0003\u001f\u0019+hn\u0019;j_:\fTj\u001c8pS\u0012T!AB\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0005\n\u0003\u0019YWM\u001d8fY*\t!\"\u0001\u0003dCR\u001c8\u0001A\u000b\u0004\u001bi!3\u0003\u0002\u0001\u000f)\u0019\u0002\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007\u0003B\u000b\u00171\rj\u0011!B\u0005\u0003/\u0015\u0011!CR;oGRLwN\\\u0019TK6LwM]8vaB\u0011\u0011D\u0007\u0007\u0001\t\u0015Y\u0002A1\u0001\u001d\u0005\u0005\t\u0015CA\u000f!!\tya$\u0003\u0002 !\t9aj\u001c;iS:<\u0007CA\b\"\u0013\t\u0011\u0003CA\u0002B]f\u0004\"!\u0007\u0013\u0005\u000b\u0015\u0002!\u0019\u0001\u000f\u0003\u0003\t\u00032a\n\u0015+\u001b\u00059\u0011BA\u0015\b\u0005\u0019iuN\\8jIB!qb\u000b\r$\u0013\ta\u0003CA\u0005Gk:\u001cG/[8oc\u00051A%\u001b8ji\u0012\"\u0012a\f\t\u0003\u001fAJ!!\r\t\u0003\tUs\u0017\u000e^\u0001\u0002\u0005V\tA\u0007E\u0002(Q\r\nQ!Z7qif,\u0012A\u000b"
)
public interface Function1Monoid extends Function1Semigroup, Monoid {
   void cats$kernel$instances$Function1Monoid$_setter_$empty_$eq(final Function1 x$1);

   Monoid B();

   Function1 empty();

   static void $init$(final Function1Monoid $this) {
      $this.cats$kernel$instances$Function1Monoid$_setter_$empty_$eq((x$2) -> $this.B().empty());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
