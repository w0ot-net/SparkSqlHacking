package cats.kernel.instances;

import cats.kernel.Eq;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005]2A\u0001B\u0003\u0001\u0019!Aa\u0005\u0001B\u0001B\u0003-q\u0005C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003/\u0001\u0011\u0005qF\u0001\u0005PaRLwN\\#r\u0015\t1q!A\u0005j]N$\u0018M\\2fg*\u0011\u0001\"C\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003)\tAaY1ug\u000e\u0001QCA\u0007\u001e'\r\u0001a\u0002\u0006\t\u0003\u001fIi\u0011\u0001\u0005\u0006\u0002#\u0005)1oY1mC&\u00111\u0003\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007U1\u0002$D\u0001\b\u0013\t9rA\u0001\u0002FcB\u0019q\"G\u000e\n\u0005i\u0001\"AB(qi&|g\u000e\u0005\u0002\u001d;1\u0001A!\u0002\u0010\u0001\u0005\u0004y\"!A!\u0012\u0005\u0001\u001a\u0003CA\b\"\u0013\t\u0011\u0003CA\u0004O_RD\u0017N\\4\u0011\u0005=!\u0013BA\u0013\u0011\u0005\r\te._\u0001\u0002\u0003B\u0019QCF\u000e\u0002\rqJg.\u001b;?)\u0005QCCA\u0016.!\ra\u0003aG\u0007\u0002\u000b!)aE\u0001a\u0002O\u0005\u0019Q-\u001d<\u0015\u0007A\u001aT\u0007\u0005\u0002\u0010c%\u0011!\u0007\u0005\u0002\b\u0005>|G.Z1o\u0011\u0015!4\u00011\u0001\u0019\u0003\u0005A\b\"\u0002\u001c\u0004\u0001\u0004A\u0012!A="
)
public class OptionEq implements Eq {
   private final Eq A;

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

   public boolean eqv(final Option x, final Option y) {
      boolean var3;
      if (.MODULE$.equals(x)) {
         var3 = y.isEmpty();
      } else {
         if (!(x instanceof Some)) {
            throw new MatchError(x);
         }

         Some var6 = (Some)x;
         Object a = var6.value();
         boolean var4;
         if (.MODULE$.equals(y)) {
            var4 = false;
         } else {
            if (!(y instanceof Some)) {
               throw new MatchError(y);
            }

            Some var9 = (Some)y;
            Object b = var9.value();
            var4 = this.A.eqv(a, b);
         }

         var3 = var4;
      }

      return var3;
   }

   public OptionEq(final Eq A) {
      this.A = A;
      Eq.$init$(this);
   }
}
