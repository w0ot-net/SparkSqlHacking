package cats.kernel.instances;

import cats.kernel.Monoid;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003+\u0001\u0011\u00051\u0006C\u00030\u0001\u0019\r\u0001\u0007C\u00043\u0001\t\u0007I\u0011A\u001a\u0003\u001f\u0019+hn\u0019;j_:\u0004Tj\u001c8pS\u0012T!AB\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0005\n\u0003\u0019YWM\u001d8fY*\t!\"\u0001\u0003dCR\u001c8\u0001A\u000b\u0003\u001bi\u0019B\u0001\u0001\b\u0015GA\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\u00042!\u0006\f\u0019\u001b\u0005)\u0011BA\f\u0006\u0005I1UO\\2uS>t\u0007gU3nS\u001e\u0014x.\u001e9\u0011\u0005eQB\u0002\u0001\u0003\u00067\u0001\u0011\r\u0001\b\u0002\u0002\u0003F\u0011Q\u0004\t\t\u0003\u001fyI!a\b\t\u0003\u000f9{G\u000f[5oOB\u0011q\"I\u0005\u0003EA\u00111!\u00118z!\r!SeJ\u0007\u0002\u000f%\u0011ae\u0002\u0002\u0007\u001b>tw.\u001b3\u0011\u0007=A\u0003$\u0003\u0002*!\tIa)\u001e8di&|g\u000eM\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u00031\u0002\"aD\u0017\n\u00059\u0002\"\u0001B+oSR\f\u0011!Q\u000b\u0002cA\u0019A%\n\r\u0002\u000b\u0015l\u0007\u000f^=\u0016\u0003\u001d\u0002"
)
public interface Function0Monoid extends Function0Semigroup, Monoid {
   void cats$kernel$instances$Function0Monoid$_setter_$empty_$eq(final Function0 x$1);

   Monoid A();

   Function0 empty();

   static void $init$(final Function0Monoid $this) {
      $this.cats$kernel$instances$Function0Monoid$_setter_$empty_$eq(() -> $this.A().empty());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
