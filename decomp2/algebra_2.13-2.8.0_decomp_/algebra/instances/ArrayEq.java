package algebra.instances;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005!3A\u0001B\u0003\u0007\u0015!Aq\u0007\u0001B\u0002B\u0003-\u0001\bC\u0003:\u0001\u0011\u0005!\bC\u0003@\u0001\u0011\u0005\u0001IA\u0004BeJ\f\u00170R9\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\u0005A\u0011aB1mO\u0016\u0014'/Y\u0002\u0001+\tYqd\u0005\u0003\u0001\u0019Ia\u0003CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g\rE\u0002\u0014/iq!\u0001F\u000b\u000e\u0003\u001dI!AF\u0004\u0002\u000fA\f7m[1hK&\u0011\u0001$\u0007\u0002\u0003\u000bFT!AF\u0004\u0011\u00075YR$\u0003\u0002\u001d\u001d\t)\u0011I\u001d:bsB\u0011ad\b\u0007\u0001\t%\u0001\u0003\u0001)A\u0001\u0002\u000b\u0007\u0011EA\u0001B#\t\u0011S\u0005\u0005\u0002\u000eG%\u0011AE\u0004\u0002\b\u001d>$\b.\u001b8h!\tia%\u0003\u0002(\u001d\t\u0019\u0011I\\=)\u0005}I\u0003CA\u0007+\u0013\tYcBA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0007CA\u00175\u001d\tq3G\u0004\u00020e5\t\u0001G\u0003\u00022\u0013\u00051AH]8pizJ\u0011aD\u0005\u0003-9I!!\u000e\u001c\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005Yq\u0011AC3wS\u0012,gnY3%iA\u00191cF\u000f\u0002\rqJg.\u001b;?)\u0005YDC\u0001\u001f?!\ri\u0004!H\u0007\u0002\u000b!)qG\u0001a\u0002q\u0005\u0019Q-\u001d<\u0015\u0007\u0005#e\t\u0005\u0002\u000e\u0005&\u00111I\u0004\u0002\b\u0005>|G.Z1o\u0011\u0015)5\u00011\u0001\u001b\u0003\u0005A\b\"B$\u0004\u0001\u0004Q\u0012!A="
)
public class ArrayEq implements Eq {
   public final Eq evidence$4;

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

   public boolean eqv(final Object x, final Object y) {
      return ArraySupport$.MODULE$.eqv(x, y, this.evidence$4);
   }

   public boolean eqv$mcZ$sp(final boolean[] x, final boolean[] y) {
      return this.eqv(x, y);
   }

   public boolean eqv$mcB$sp(final byte[] x, final byte[] y) {
      return this.eqv(x, y);
   }

   public boolean eqv$mcC$sp(final char[] x, final char[] y) {
      return this.eqv(x, y);
   }

   public boolean eqv$mcD$sp(final double[] x, final double[] y) {
      return this.eqv(x, y);
   }

   public boolean eqv$mcF$sp(final float[] x, final float[] y) {
      return this.eqv(x, y);
   }

   public boolean eqv$mcI$sp(final int[] x, final int[] y) {
      return this.eqv(x, y);
   }

   public boolean eqv$mcJ$sp(final long[] x, final long[] y) {
      return this.eqv(x, y);
   }

   public boolean eqv$mcS$sp(final short[] x, final short[] y) {
      return this.eqv(x, y);
   }

   public boolean eqv$mcV$sp(final BoxedUnit[] x, final BoxedUnit[] y) {
      return this.eqv(x, y);
   }

   public ArrayEq(final Eq evidence$4) {
      this.evidence$4 = evidence$4;
      Eq.$init$(this);
   }
}
