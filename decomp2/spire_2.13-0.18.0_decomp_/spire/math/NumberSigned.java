package spire.math;

import algebra.ring.Signed;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005aA\u0003\u0005\u0006S\u0001!\tA\u000b\u0005\u0006]\u0001!\ta\f\u0005\u0006c\u0001!\tE\r\u0005\u0006q\u0001!\t%\u000f\u0002\r\u001dVl'-\u001a:TS\u001etW\r\u001a\u0006\u0003\u000f!\tA!\\1uQ*\t\u0011\"A\u0003ta&\u0014Xm\u0005\u0003\u0001\u0017E1\u0003C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g\rE\u0002\u0013?\tr!a\u0005\u000f\u000f\u0005QQbBA\u000b\u001a\u001b\u00051\"BA\f\u0019\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0005\n\u0005mA\u0011aB1mO\u0016\u0014'/Y\u0005\u0003;y\tq\u0001]1dW\u0006<WM\u0003\u0002\u001c\u0011%\u0011\u0001%\t\u0002\u0007'&<g.\u001a3\u000b\u0005uq\u0002CA\u0012%\u001b\u00051\u0011BA\u0013\u0007\u0005\u0019qU/\u001c2feB\u00111eJ\u0005\u0003Q\u0019\u00111BT;nE\u0016\u0014xJ\u001d3fe\u00061A%\u001b8ji\u0012\"\u0012a\u000b\t\u0003\u00191J!!L\u0007\u0003\tUs\u0017\u000e^\u0001\u0006_J$WM]\u000b\u0002aA\u00111\u0005A\u0001\u0007g&<g.^7\u0015\u0005M2\u0004C\u0001\u00075\u0013\t)TBA\u0002J]RDQaN\u0002A\u0002\t\n\u0011!Y\u0001\u0004C\n\u001cHC\u0001\u0012;\u0011\u00159D\u00011\u0001#\u0001"
)
public interface NumberSigned extends Signed, NumberOrder {
   // $FF: synthetic method
   static NumberSigned order$(final NumberSigned $this) {
      return $this.order();
   }

   default NumberSigned order() {
      return this;
   }

   // $FF: synthetic method
   static int signum$(final NumberSigned $this, final Number a) {
      return $this.signum(a);
   }

   default int signum(final Number a) {
      return a.signum();
   }

   // $FF: synthetic method
   static Number abs$(final NumberSigned $this, final Number a) {
      return $this.abs(a);
   }

   default Number abs(final Number a) {
      return a.abs();
   }

   static void $init$(final NumberSigned $this) {
   }
}
