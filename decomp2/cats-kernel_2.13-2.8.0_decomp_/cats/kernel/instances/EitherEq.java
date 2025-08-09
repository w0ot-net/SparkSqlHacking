package cats.kernel.instances;

import cats.kernel.Eq;
import scala.MatchError;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3A!\u0002\u0004\u0001\u001b!A1\u0007\u0001B\u0001B\u0003-A\u0007\u0003\u00056\u0001\t\u0005\t\u0015a\u00037\u0011\u00159\u0004\u0001\"\u00019\u0011\u0015q\u0004\u0001\"\u0001@\u0005!)\u0015\u000e\u001e5fe\u0016\u000b(BA\u0004\t\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\n\u0015\u000511.\u001a:oK2T\u0011aC\u0001\u0005G\u0006$8o\u0001\u0001\u0016\u000799\u0013gE\u0002\u0001\u001fU\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007c\u0001\f\u001835\t\u0001\"\u0003\u0002\u0019\u0011\t\u0011Q)\u001d\t\u00055\t*\u0003G\u0004\u0002\u001cA9\u0011AdH\u0007\u0002;)\u0011a\u0004D\u0001\u0007yI|w\u000e\u001e \n\u0003II!!I\t\u0002\u000fA\f7m[1hK&\u00111\u0005\n\u0002\u0007\u000b&$\b.\u001a:\u000b\u0005\u0005\n\u0002C\u0001\u0014(\u0019\u0001!Q\u0001\u000b\u0001C\u0002%\u0012\u0011!Q\t\u0003U5\u0002\"\u0001E\u0016\n\u00051\n\"a\u0002(pi\"Lgn\u001a\t\u0003!9J!aL\t\u0003\u0007\u0005s\u0017\u0010\u0005\u0002'c\u0011)!\u0007\u0001b\u0001S\t\t!)A\u0001B!\r1r#J\u0001\u0002\u0005B\u0019ac\u0006\u0019\u0002\rqJg.\u001b;?)\u0005IDc\u0001\u001e={A!1\bA\u00131\u001b\u00051\u0001\"B\u001a\u0004\u0001\b!\u0004\"B\u001b\u0004\u0001\b1\u0014aA3rmR\u0019\u0001iQ#\u0011\u0005A\t\u0015B\u0001\"\u0012\u0005\u001d\u0011un\u001c7fC:DQ\u0001\u0012\u0003A\u0002e\t\u0011\u0001\u001f\u0005\u0006\r\u0012\u0001\r!G\u0001\u0002s\u0002"
)
public class EitherEq implements Eq {
   private final Eq A;
   private final Eq B;

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

   public boolean eqv(final Either x, final Either y) {
      boolean var3;
      if (x instanceof Left) {
         Left var7 = (Left)x;
         Object xx = var7.value();
         boolean var5;
         if (y instanceof Left) {
            Left var10 = (Left)y;
            Object yy = var10.value();
            var5 = this.A.eqv(xx, yy);
         } else {
            if (!(y instanceof Right)) {
               throw new MatchError(y);
            }

            var5 = false;
         }

         var3 = var5;
      } else {
         if (!(x instanceof Right)) {
            throw new MatchError(x);
         }

         Right var12 = (Right)x;
         Object xx = var12.value();
         boolean var4;
         if (y instanceof Left) {
            var4 = false;
         } else {
            if (!(y instanceof Right)) {
               throw new MatchError(y);
            }

            Right var15 = (Right)y;
            Object yy = var15.value();
            var4 = this.B.eqv(xx, yy);
         }

         var3 = var4;
      }

      return var3;
   }

   public EitherEq(final Eq A, final Eq B) {
      this.A = A;
      this.B = B;
      Eq.$init$(this);
   }
}
