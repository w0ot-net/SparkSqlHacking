package spire.math;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005Q3Q!\u0002\u0004\u0001\r)A\u0001B\u000f\u0001\u0003\u0004\u0003\u0006Ya\u000f\u0005\u0006y\u0001!\t!\u0010\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\u0015\u0002!\te\u0013\u0002\n\u0007>l\u0007\u000f\\3y\u000bFT!a\u0002\u0005\u0002\t5\fG\u000f\u001b\u0006\u0002\u0013\u0005)1\u000f]5sKV\u00111\"K\n\u0005\u00011\u0011\"\u0007\u0005\u0002\u000e!5\taBC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0013\t\tbB\u0001\u0004B]f\u0014VM\u001a\t\u0004'\u0001\u001acB\u0001\u000b\u001e\u001d\t)2D\u0004\u0002\u001755\tqC\u0003\u0002\u00193\u00051AH]8piz\u001a\u0001!C\u0001\n\u0013\ta\u0002\"A\u0004bY\u001e,'M]1\n\u0005yy\u0012a\u00029bG.\fw-\u001a\u0006\u00039!I!!\t\u0012\u0003\u0005\u0015\u000b(B\u0001\u0010 !\r!SeJ\u0007\u0002\r%\u0011aE\u0002\u0002\b\u0007>l\u0007\u000f\\3y!\tA\u0013\u0006\u0004\u0001\u0005\u000b)\u0002!\u0019A\u0016\u0003\u0003\u0005\u000b\"\u0001L\u0018\u0011\u00055i\u0013B\u0001\u0018\u000f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0004\u0019\n\u0005Er!aA!osB\u00111g\u000e\b\u0003iYr!AF\u001b\n\u0003=I!A\b\b\n\u0005aJ$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u0010\u000f\u0003-)g/\u001b3f]\u000e,G%\r\u001b\u0011\u0007M\u0001s%\u0001\u0004=S:LGO\u0010\u000b\u0002}Q\u0011q\b\u0011\t\u0004I\u00019\u0003\"\u0002\u001e\u0003\u0001\bY\u0014aA3rmR\u00191I\u0012%\u0011\u00055!\u0015BA#\u000f\u0005\u001d\u0011un\u001c7fC:DQaR\u0002A\u0002\r\n\u0011\u0001\u001f\u0005\u0006\u0013\u000e\u0001\raI\u0001\u0002s\u0006!a.Z9w)\r\u0019E*\u0014\u0005\u0006\u000f\u0012\u0001\ra\t\u0005\u0006\u0013\u0012\u0001\ra\t\u0015\u0005\u0001=\u00136\u000b\u0005\u0002\u000e!&\u0011\u0011K\u0004\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012!\u0001"
)
public class ComplexEq implements Eq {
   private static final long serialVersionUID = 1L;
   private final Eq evidence$14;

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

   public boolean eqv(final Complex x, final Complex y) {
      return x.eqv(y, this.evidence$14);
   }

   public boolean neqv(final Complex x, final Complex y) {
      return x.neqv(y, this.evidence$14);
   }

   public ComplexEq(final Eq evidence$14) {
      this.evidence$14 = evidence$14;
      Eq.$init$(this);
   }
}
