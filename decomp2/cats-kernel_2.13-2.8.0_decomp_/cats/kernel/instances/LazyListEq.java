package cats.kernel.instances;

import cats.kernel.Eq;
import scala.collection.immutable.LazyList;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013A\u0001B\u0003\u0001\u0019!Aq\u0006\u0001B\u0001B\u0003-\u0001\u0007C\u00032\u0001\u0011\u0005!\u0007C\u00038\u0001\u0011\u0005\u0001H\u0001\u0006MCjLH*[:u\u000bFT!AB\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0005\n\u0003\u0019YWM\u001d8fY*\t!\"\u0001\u0003dCR\u001c8\u0001A\u000b\u0003\u001b\u0019\u001a2\u0001\u0001\b\u0015!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fMB\u0019QC\u0006\r\u000e\u0003\u001dI!aF\u0004\u0003\u0005\u0015\u000b\bcA\r\"I9\u0011!d\b\b\u00037yi\u0011\u0001\b\u0006\u0003;-\ta\u0001\u0010:p_Rt\u0014\"A\t\n\u0005\u0001\u0002\u0012a\u00029bG.\fw-Z\u0005\u0003E\r\u0012\u0001\u0002T1{s2K7\u000f\u001e\u0006\u0003AA\u0001\"!\n\u0014\r\u0001\u0011)q\u0005\u0001b\u0001Q\t\t\u0011)\u0005\u0002*YA\u0011qBK\u0005\u0003WA\u0011qAT8uQ&tw\r\u0005\u0002\u0010[%\u0011a\u0006\u0005\u0002\u0004\u0003:L\u0018AA3w!\r)b\u0003J\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003M\"\"\u0001\u000e\u001c\u0011\u0007U\u0002A%D\u0001\u0006\u0011\u0015y#\u0001q\u00011\u0003\r)\u0017O\u001e\u000b\u0004sqr\u0004CA\b;\u0013\tY\u0004CA\u0004C_>dW-\u00198\t\u000bu\u001a\u0001\u0019\u0001\r\u0002\u0005a\u001c\b\"B \u0004\u0001\u0004A\u0012AA=t\u0001"
)
public class LazyListEq implements Eq {
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

   public boolean eqv(final LazyList xs, final LazyList ys) {
      return xs == ys ? true : StaticMethods$.MODULE$.iteratorEq(xs.iterator(), ys.iterator(), this.ev);
   }

   public LazyListEq(final Eq ev) {
      this.ev = ev;
      Eq.$init$(this);
   }
}
