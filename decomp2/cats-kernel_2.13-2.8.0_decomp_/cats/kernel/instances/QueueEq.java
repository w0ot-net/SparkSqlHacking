package cats.kernel.instances;

import cats.kernel.Eq;
import scala.collection.immutable.Queue;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005q2A\u0001B\u0003\u0001\u0019!A1\u0006\u0001B\u0001B\u0003-A\u0006C\u0003.\u0001\u0011\u0005a\u0006C\u00034\u0001\u0011\u0005AGA\u0004Rk\u0016,X-R9\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\tA\u0011\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0015\u0005!1-\u0019;t\u0007\u0001)\"!\u0004\u0012\u0014\u0007\u0001qA\u0003\u0005\u0002\u0010%5\t\u0001CC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0002C\u0001\u0004B]f\u0014VM\u001a\t\u0004+YAR\"A\u0004\n\u0005]9!AA#r!\rIb\u0004I\u0007\u00025)\u00111\u0004H\u0001\nS6lW\u000f^1cY\u0016T!!\b\t\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002 5\t)\u0011+^3vKB\u0011\u0011E\t\u0007\u0001\t\u0015\u0019\u0003A1\u0001%\u0005\u0005\t\u0015CA\u0013)!\tya%\u0003\u0002(!\t9aj\u001c;iS:<\u0007CA\b*\u0013\tQ\u0003CA\u0002B]f\f!!\u001a<\u0011\u0007U1\u0002%\u0001\u0004=S:LGO\u0010\u000b\u0002_Q\u0011\u0001G\r\t\u0004c\u0001\u0001S\"A\u0003\t\u000b-\u0012\u00019\u0001\u0017\u0002\u0007\u0015\fh\u000fF\u00026qi\u0002\"a\u0004\u001c\n\u0005]\u0002\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006s\r\u0001\r\u0001G\u0001\u0003qNDQaO\u0002A\u0002a\t!!_:"
)
public class QueueEq implements Eq {
   private final Eq ev;

   public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
      return Eq.eqv$mcZ$sp$(this, x, y);
   }

   public boolean eqv$mcB$sp(final byte x, final byte y) {
      return Eq.eqv$mcB$sp$(this, x, y);
   }

   public boolean eqv$mcC$sp(final char x, final char y) {
      return Eq.eqv$mcC$sp$(this, x, y);
   }

   public boolean eqv$mcD$sp(final double x, final double y) {
      return Eq.eqv$mcD$sp$(this, x, y);
   }

   public boolean eqv$mcF$sp(final float x, final float y) {
      return Eq.eqv$mcF$sp$(this, x, y);
   }

   public boolean eqv$mcI$sp(final int x, final int y) {
      return Eq.eqv$mcI$sp$(this, x, y);
   }

   public boolean eqv$mcJ$sp(final long x, final long y) {
      return Eq.eqv$mcJ$sp$(this, x, y);
   }

   public boolean eqv$mcS$sp(final short x, final short y) {
      return Eq.eqv$mcS$sp$(this, x, y);
   }

   public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Eq.eqv$mcV$sp$(this, x, y);
   }

   public boolean neqv(final Object x, final Object y) {
      return Eq.neqv$(this, x, y);
   }

   public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
      return Eq.neqv$mcZ$sp$(this, x, y);
   }

   public boolean neqv$mcB$sp(final byte x, final byte y) {
      return Eq.neqv$mcB$sp$(this, x, y);
   }

   public boolean neqv$mcC$sp(final char x, final char y) {
      return Eq.neqv$mcC$sp$(this, x, y);
   }

   public boolean neqv$mcD$sp(final double x, final double y) {
      return Eq.neqv$mcD$sp$(this, x, y);
   }

   public boolean neqv$mcF$sp(final float x, final float y) {
      return Eq.neqv$mcF$sp$(this, x, y);
   }

   public boolean neqv$mcI$sp(final int x, final int y) {
      return Eq.neqv$mcI$sp$(this, x, y);
   }

   public boolean neqv$mcJ$sp(final long x, final long y) {
      return Eq.neqv$mcJ$sp$(this, x, y);
   }

   public boolean neqv$mcS$sp(final short x, final short y) {
      return Eq.neqv$mcS$sp$(this, x, y);
   }

   public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Eq.neqv$mcV$sp$(this, x, y);
   }

   public boolean eqv(final Queue xs, final Queue ys) {
      return xs == ys ? true : StaticMethods$.MODULE$.iteratorEq(xs.iterator(), ys.iterator(), this.ev);
   }

   public QueueEq(final Eq ev) {
      this.ev = ev;
      Eq.$init$(this);
   }
}
