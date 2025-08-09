package spire.std;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4A\u0001B\u0003\u0007\u0015!A\u0001\u000b\u0001B\u0002B\u0003-\u0011\u000bC\u0003S\u0001\u0011\u00051\u000bC\u0003Y\u0001\u0011\u0005\u0011LA\u0004BeJ\f\u00170R9\u000b\u0005\u00199\u0011aA:uI*\t\u0001\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u0005-93\u0003\u0002\u0001\r%)\u0003\"!\u0004\t\u000e\u00039Q\u0011aD\u0001\u0006g\u000e\fG.Y\u0005\u0003#9\u0011a!\u00118z%\u00164\u0007cA\n E9\u0011A\u0003\b\b\u0003+iq!AF\r\u000e\u0003]Q!\u0001G\u0005\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0011BA\u000e\b\u0003\u001d\tGnZ3ce\u0006L!!\b\u0010\u0002\u000fA\f7m[1hK*\u00111dB\u0005\u0003A\u0005\u0012!!R9\u000b\u0005uq\u0002cA\u0007$K%\u0011AE\u0004\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003M\u001db\u0001\u0001B\u0005)\u0001\u0001\u0006\t\u0011!b\u0001S\t\t\u0011)\u0005\u0002+[A\u0011QbK\u0005\u0003Y9\u0011qAT8uQ&tw\r\u0005\u0002\u000e]%\u0011qF\u0004\u0002\u0004\u0003:L\bFB\u00142im\u0002U\t\u0005\u0002\u000ee%\u00111G\u0004\u0002\fgB,7-[1mSj,G-M\u0003$kYBtG\u0004\u0002\u000em%\u0011qGD\u0001\u0004\u0013:$\u0018\u0007\u0002\u0013:u=q!A\u0006\u001e\n\u0003=\tTa\t\u001f>\u007fyr!!D\u001f\n\u0005yr\u0011!\u0002$m_\u0006$\u0018\u0007\u0002\u0013:u=\tTaI!C\t\u000es!!\u0004\"\n\u0005\rs\u0011\u0001\u0002'p]\u001e\fD\u0001J\u001d;\u001fE*1ER$J\u0011:\u0011QbR\u0005\u0003\u0011:\ta\u0001R8vE2,\u0017\u0007\u0002\u0013:u=\u0001\"aS'\u000f\u0005eb\u0015BA\u000f\u000f\u0013\tquJ\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002\u001e\u001d\u0005YQM^5eK:\u001cW\rJ\u001a1!\r\u0019r$J\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003Q#\"!V,\u0011\u0007Y\u0003Q%D\u0001\u0006\u0011\u0015\u0001&\u0001q\u0001R\u0003\r)\u0017O\u001e\u000b\u00045v{\u0006CA\u0007\\\u0013\tafBA\u0004C_>dW-\u00198\t\u000by\u001b\u0001\u0019\u0001\u0012\u0002\u0003aDQ\u0001Y\u0002A\u0002\t\n\u0011!\u001f\u0015\u0005\u0001\t,g\r\u0005\u0002\u000eG&\u0011AM\u0004\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012\u0001\u0001"
)
public class ArrayEq implements Eq {
   private static final long serialVersionUID = 0L;
   public final Eq evidence$30;

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
      return ArraySupport$.MODULE$.eqv(x, y, this.evidence$30);
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

   public ArrayEq(final Eq evidence$30) {
      this.evidence$30 = evidence$30;
      Eq.$init$(this);
   }
}
