package breeze.linalg;

import breeze.linalg.support.CanForeachValues;
import breeze.linalg.support.CanMapValues;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003,\u0001\u0011\u0005A\u0006C\u00031\u0001\u0011\u0005\u0011\u0007C\u0003H\u0001\u0011\u0005\u0001JA\bCe>\fGmY1ti\u0016$G*[6f\u0015\t1q!\u0001\u0004mS:\fGn\u001a\u0006\u0002\u0011\u00051!M]3fu\u0016\u001c\u0001!\u0006\u0003\f1\tB3\u0003\u0002\u0001\r%\u0011\u0002\"!\u0004\t\u000e\u00039Q\u0011aD\u0001\u0006g\u000e\fG.Y\u0005\u0003#9\u0011a!\u00118z%\u00164\u0007\u0003B\n\u0015-\u0005j\u0011!B\u0005\u0003+\u0015\u00111B\u0011:pC\u0012\u001c\u0017m\u001d;fIB\u0011q\u0003\u0007\u0007\u0001\t\u0015I\u0002A1\u0001\u001b\u0005\u0005!\u0016CA\u000e\u001f!\tiA$\u0003\u0002\u001e\u001d\t9aj\u001c;iS:<\u0007CA\u0007 \u0013\t\u0001cBA\u0002B]f\u0004\"a\u0006\u0012\u0005\u000b\r\u0002!\u0019\u0001\u000e\u0003\u0003\t\u00032aE\u0013(\u0013\t1SA\u0001\u0006Ok6,'/[2PaN\u0004\"a\u0006\u0015\u0005\u000b%\u0002!\u0019\u0001\u0016\u0003\tM+GNZ\t\u00037I\ta\u0001J5oSR$C#A\u0017\u0011\u00055q\u0013BA\u0018\u000f\u0005\u0011)f.\u001b;\u0002\u00075\f\u0007/F\u00023\u0001V\"\"a\r\"\u0015\u0005Q:\u0004CA\f6\t\u00151$A1\u0001\u001b\u0005\r\u0011Vm\u001d\u0005\u0006q\t\u0001\u001d!O\u0001\u0004G64\bC\u0002\u001e>O\u0005zD'D\u0001<\u0015\taT!A\u0004tkB\u0004xN\u001d;\n\u0005yZ$\u0001D\"b]6\u000b\u0007OV1mk\u0016\u001c\bCA\fA\t\u0015\t%A1\u0001\u001b\u0005\u0005)\u0006\"B\"\u0003\u0001\u0004!\u0015!\u00014\u0011\t5)\u0015eP\u0005\u0003\r:\u0011\u0011BR;oGRLwN\\\u0019\u0002\u000f\u0019|'/Z1dQV\u0011\u0011J\u0015\u000b\u0003\u0015>#\"!L&\t\u000ba\u001a\u00019\u0001'\u0011\tiju%I\u0005\u0003\u001dn\u0012\u0001cQ1o\r>\u0014X-Y2i-\u0006dW/Z:\t\u000b\r\u001b\u0001\u0019\u0001)\u0011\t5)\u0015%\u0015\t\u0003/I#Q!Q\u0002C\u0002i\u0001"
)
public interface BroadcastedLike extends Broadcasted {
   // $FF: synthetic method
   static Object map$(final BroadcastedLike $this, final Function1 f, final CanMapValues cmv) {
      return $this.map(f, cmv);
   }

   default Object map(final Function1 f, final CanMapValues cmv) {
      return cmv.map(this.repr(), f);
   }

   // $FF: synthetic method
   static void foreach$(final BroadcastedLike $this, final Function1 f, final CanForeachValues cmv) {
      $this.foreach(f, cmv);
   }

   default void foreach(final Function1 f, final CanForeachValues cmv) {
      cmv.foreach(this.repr(), f);
   }

   static void $init$(final BroadcastedLike $this) {
   }
}
