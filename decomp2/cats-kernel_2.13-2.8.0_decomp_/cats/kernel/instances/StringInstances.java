package cats.kernel.instances;

import cats.kernel.Monoid;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r3q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0004\u0019\u0001\t\u0007I1A\r\t\u000fI\u0002!\u0019!C\u0002g\ty1\u000b\u001e:j]\u001eLen\u001d;b]\u000e,7O\u0003\u0002\u0007\u000f\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0011%\taa[3s]\u0016d'\"\u0001\u0006\u0002\t\r\fGo]\u0002\u0001'\t\u0001Q\u0002\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003U\u0001\"A\u0004\f\n\u0005]y!\u0001B+oSR\f1dY1ug.+'O\\3m'R$wJ\u001d3fe\u001a{'o\u0015;sS:<W#\u0001\u000e\u0013\tmiBf\f\u0004\u00059\u0001\u0001!D\u0001\u0007=e\u00164\u0017N\\3nK:$h\bE\u0002\u001f?\u0005j\u0011aB\u0005\u0003A\u001d\u0011Qa\u0014:eKJ\u0004\"AI\u0015\u000f\u0005\r:\u0003C\u0001\u0013\u0010\u001b\u0005)#B\u0001\u0014\f\u0003\u0019a$o\\8u}%\u0011\u0001fD\u0001\u0007!J,G-\u001a4\n\u0005)Z#AB*ue&twM\u0003\u0002)\u001fA\u0019a$L\u0011\n\u00059:!\u0001\u0002%bg\"\u00042A\b\u0019\"\u0013\t\ttA\u0001\u0007M_^,'OQ8v]\u0012,G-\u0001\u000fdCR\u001c8*\u001a:oK2\u001cF\u000fZ'p]>LGMR8s'R\u0014\u0018N\\4\u0016\u0003Q\u00022AH\u001b\"\u0013\t1tA\u0001\u0004N_:|\u0017\u000e\u001a\u0015\u0003\u0001a\u0002\"!\u000f!\u000f\u0005ijdB\u0001\u0010<\u0013\tat!\u0001\u0004d_6\u0004\u0018\r^\u0005\u0003}}\nAc]2bY\u00064VM]:j_:\u001c\u0006/Z2jM&\u001c'B\u0001\u001f\b\u0013\t\t%I\u0001\u001atkB\u0004(/Z:t+:,8/\u001a3J[B|'\u000f^,be:Lgn\u001a$peN\u001b\u0017\r\\1WKJ\u001c\u0018n\u001c8Ta\u0016\u001c\u0017NZ5d\u0015\tqt\b"
)
public interface StringInstances {
   void cats$kernel$instances$StringInstances$_setter_$catsKernelStdOrderForString_$eq(final Order x$1);

   void cats$kernel$instances$StringInstances$_setter_$catsKernelStdMonoidForString_$eq(final Monoid x$1);

   Order catsKernelStdOrderForString();

   Monoid catsKernelStdMonoidForString();

   static void $init$(final StringInstances $this) {
      $this.cats$kernel$instances$StringInstances$_setter_$catsKernelStdOrderForString_$eq(new StringOrder());
      $this.cats$kernel$instances$StringInstances$_setter_$catsKernelStdMonoidForString_$eq(new StringMonoid());
   }
}
