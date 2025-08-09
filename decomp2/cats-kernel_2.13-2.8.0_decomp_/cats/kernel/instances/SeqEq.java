package cats.kernel.instances;

import cats.kernel.Eq;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005q2A\u0001B\u0003\u0001\u0019!A1\u0006\u0001B\u0001B\u0003-A\u0006C\u0003.\u0001\u0011\u0005a\u0006C\u00034\u0001\u0011\u0005AGA\u0003TKF,\u0015O\u0003\u0002\u0007\u000f\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0011%\taa[3s]\u0016d'\"\u0001\u0006\u0002\t\r\fGo]\u0002\u0001+\ti!eE\u0002\u0001\u001dQ\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007cA\u000b\u001715\tq!\u0003\u0002\u0018\u000f\t\u0011Q)\u001d\t\u00043y\u0001S\"\u0001\u000e\u000b\u0005ma\u0012!C5n[V$\u0018M\u00197f\u0015\ti\u0002#\u0001\u0006d_2dWm\u0019;j_:L!a\b\u000e\u0003\u0007M+\u0017\u000f\u0005\u0002\"E1\u0001A!B\u0012\u0001\u0005\u0004!#!A!\u0012\u0005\u0015B\u0003CA\b'\u0013\t9\u0003CA\u0004O_RD\u0017N\\4\u0011\u0005=I\u0013B\u0001\u0016\u0011\u0005\r\te._\u0001\u0003KZ\u00042!\u0006\f!\u0003\u0019a\u0014N\\5u}Q\tq\u0006\u0006\u00021eA\u0019\u0011\u0007\u0001\u0011\u000e\u0003\u0015AQa\u000b\u0002A\u00041\n1!Z9w)\r)\u0004H\u000f\t\u0003\u001fYJ!a\u000e\t\u0003\u000f\t{w\u000e\\3b]\")\u0011h\u0001a\u00011\u0005\u0011\u0001p\u001d\u0005\u0006w\r\u0001\r\u0001G\u0001\u0003sN\u0004"
)
public class SeqEq implements Eq {
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

   public boolean eqv(final Seq xs, final Seq ys) {
      return xs == ys ? true : StaticMethods$.MODULE$.iteratorEq(xs.iterator(), ys.iterator(), this.ev);
   }

   public SeqEq(final Eq ev) {
      this.ev = ev;
      Eq.$init$(this);
   }
}
