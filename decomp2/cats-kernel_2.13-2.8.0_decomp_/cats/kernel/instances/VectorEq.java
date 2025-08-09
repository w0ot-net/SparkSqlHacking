package cats.kernel.instances;

import cats.kernel.Eq;
import scala.collection.immutable.Vector;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013A\u0001B\u0003\u0001\u0019!Aq\u0006\u0001B\u0001B\u0003-\u0001\u0007C\u00032\u0001\u0011\u0005!\u0007C\u00038\u0001\u0011\u0005\u0001H\u0001\u0005WK\u000e$xN]#r\u0015\t1q!A\u0005j]N$\u0018M\\2fg*\u0011\u0001\"C\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003)\tAaY1ug\u000e\u0001QCA\u0007''\r\u0001a\u0002\u0006\t\u0003\u001fIi\u0011\u0001\u0005\u0006\u0002#\u0005)1oY1mC&\u00111\u0003\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007U1\u0002$D\u0001\b\u0013\t9rA\u0001\u0002FcB\u0019\u0011$\t\u0013\u000f\u0005iybBA\u000e\u001f\u001b\u0005a\"BA\u000f\f\u0003\u0019a$o\\8u}%\t\u0011#\u0003\u0002!!\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0012$\u0005\u00191Vm\u0019;pe*\u0011\u0001\u0005\u0005\t\u0003K\u0019b\u0001\u0001B\u0003(\u0001\t\u0007\u0001FA\u0001B#\tIC\u0006\u0005\u0002\u0010U%\u00111\u0006\u0005\u0002\b\u001d>$\b.\u001b8h!\tyQ&\u0003\u0002/!\t\u0019\u0011I\\=\u0002\u0005\u00154\bcA\u000b\u0017I\u00051A(\u001b8jiz\"\u0012a\r\u000b\u0003iY\u00022!\u000e\u0001%\u001b\u0005)\u0001\"B\u0018\u0003\u0001\b\u0001\u0014aA3rmR\u0019\u0011\b\u0010 \u0011\u0005=Q\u0014BA\u001e\u0011\u0005\u001d\u0011un\u001c7fC:DQ!P\u0002A\u0002a\t!\u0001_:\t\u000b}\u001a\u0001\u0019\u0001\r\u0002\u0005e\u001c\b"
)
public class VectorEq implements Eq {
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

   public boolean eqv(final Vector xs, final Vector ys) {
      return xs == ys ? true : StaticMethods$.MODULE$.iteratorEq(xs.iterator(), ys.iterator(), this.ev);
   }

   public VectorEq(final Eq ev) {
      this.ev = ev;
      Eq.$init$(this);
   }
}
