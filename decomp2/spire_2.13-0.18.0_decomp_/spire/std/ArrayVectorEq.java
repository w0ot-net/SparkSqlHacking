package spire.std;

import algebra.ring.AdditiveMonoid;
import cats.kernel.Eq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u000554A!\u0002\u0004\u0001\u0017!A\u0011\u000b\u0001B\u0002B\u0003-!\u000b\u0003\u0005T\u0001\t\r\t\u0015a\u0003U\u0011\u00159\u0006\u0001\"\u0001Y\u0011\u0015q\u0006\u0001\"\u0001`\u00055\t%O]1z-\u0016\u001cGo\u001c:Fc*\u0011q\u0001C\u0001\u0004gR$'\"A\u0005\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0011A\u0002K\n\u0005\u00015\u00192\n\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VM\u001a\t\u0004)\u0001\u001acBA\u000b\u001e\u001d\t12D\u0004\u0002\u001855\t\u0001D\u0003\u0002\u001a\u0015\u00051AH]8pizJ\u0011!C\u0005\u00039!\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\u001f?\u00059\u0001/Y2lC\u001e,'B\u0001\u000f\t\u0013\t\t#E\u0001\u0002Fc*\u0011ad\b\t\u0004\u001d\u00112\u0013BA\u0013\u0010\u0005\u0015\t%O]1z!\t9\u0003\u0006\u0004\u0001\u0005\u0013%\u0002\u0001\u0015!A\u0001\u0006\u0004Q#!A!\u0012\u0005-r\u0003C\u0001\b-\u0013\tisBA\u0004O_RD\u0017N\\4\u0011\u00059y\u0013B\u0001\u0019\u0010\u0005\r\te.\u001f\u0015\u0007QI*D(\u0011$\u0011\u00059\u0019\u0014B\u0001\u001b\u0010\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\r2t'\u000f\u001d\u000f\u000599\u0014B\u0001\u001d\u0010\u0003\rIe\u000e^\u0019\u0005IiZ\u0004C\u0004\u0002\u0018w%\t\u0001#M\u0003${y\u0002uH\u0004\u0002\u000f}%\u0011qhD\u0001\u0005\u0019>tw-\r\u0003%um\u0002\u0012'B\u0012C\u0007\u0016#eB\u0001\bD\u0013\t!u\"A\u0003GY>\fG/\r\u0003%um\u0002\u0012'B\u0012H\u0011*KeB\u0001\bI\u0013\tIu\"\u0001\u0004E_V\u0014G.Z\u0019\u0005IiZ\u0004\u0003\u0005\u0002M\u001d:\u0011!(T\u0005\u0003==I!a\u0014)\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005yy\u0011aC3wS\u0012,gnY3%gY\u00022\u0001\u0006\u0011'\u0003-)g/\u001b3f]\u000e,GeM\u001c\u0011\u0007Q)f%\u0003\u0002WE\tq\u0011\t\u001a3ji&4X-T8o_&$\u0017A\u0002\u001fj]&$h\bF\u0001Z)\rQF,\u0018\t\u00047\u00021S\"\u0001\u0004\t\u000bE\u001b\u00019\u0001*\t\u000bM\u001b\u00019\u0001+\u0002\u0007\u0015\fh\u000fF\u0002aG\u0016\u0004\"AD1\n\u0005\t|!a\u0002\"p_2,\u0017M\u001c\u0005\u0006I\u0012\u0001\raI\u0001\u0002q\")a\r\u0002a\u0001G\u0005\t\u0011\u0010\u000b\u0003\u0001Q.d\u0007C\u0001\bj\u0013\tQwB\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t\u0001\u0001"
)
public class ArrayVectorEq implements Eq {
   private static final long serialVersionUID = 0L;
   public final Eq evidence$36;
   public final AdditiveMonoid evidence$37;

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
      return ArraySupport$.MODULE$.vectorEqv(x, y, this.evidence$36, this.evidence$37);
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

   public ArrayVectorEq(final Eq evidence$36, final AdditiveMonoid evidence$37) {
      this.evidence$36 = evidence$36;
      this.evidence$37 = evidence$37;
      Eq.$init$(this);
   }
}
