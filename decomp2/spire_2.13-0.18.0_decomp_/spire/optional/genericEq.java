package spire.optional;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005q;Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQaF\u0001\u0005\u0002a1A!G\u0001\u00055!)qc\u0001C\u0001\u0007\")ai\u0001C\u0001\u000f\")Q+\u0001C\u0002-\u0006Iq-\u001a8fe&\u001cW)\u001d\u0006\u0003\u0013)\t\u0001b\u001c9uS>t\u0017\r\u001c\u0006\u0002\u0017\u0005)1\u000f]5sK\u000e\u0001\u0001C\u0001\b\u0002\u001b\u0005A!!C4f]\u0016\u0014\u0018nY#r'\t\t\u0011\u0003\u0005\u0002\u0013+5\t1CC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0013\t12C\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00035\u0011\u0011bR3oKJL7-R9\u0016\u0005mq3\u0003B\u0002\u00129m\u00022!H\u0015-\u001d\tqbE\u0004\u0002 I9\u0011\u0001eI\u0007\u0002C)\u0011!\u0005D\u0001\u0007yI|w\u000e\u001e \n\u0003-I!!\n\u0006\u0002\u000f\u0005dw-\u001a2sC&\u0011q\u0005K\u0001\ba\u0006\u001c7.Y4f\u0015\t)#\"\u0003\u0002+W\t\u0011Q)\u001d\u0006\u0003O!\u0002\"!\f\u0018\r\u0001\u0011Iqf\u0001Q\u0001\u0002\u0003\u0015\r\u0001\r\u0002\u0002\u0003F\u0011\u0011\u0007\u000e\t\u0003%IJ!aM\n\u0003\u000f9{G\u000f[5oOB\u0011!#N\u0005\u0003mM\u00111!\u00118zQ\tq\u0003\b\u0005\u0002\u0013s%\u0011!h\u0005\u0002\fgB,7-[1mSj,G\r\u0005\u0002=\u0001:\u0011Qh\u0010\b\u0003AyJ\u0011\u0001F\u0005\u0003OMI!!\u0011\"\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u001d\u001aB#\u0001#\u0011\u0007\u0015\u001bA&D\u0001\u0002\u0003\r)\u0017O\u001e\u000b\u0004\u0011.k\u0005C\u0001\nJ\u0013\tQ5CA\u0004C_>dW-\u00198\t\u000b1+\u0001\u0019\u0001\u0017\u0002\u0003aDQAT\u0003A\u00021\n\u0011!\u001f\u0015\u0005\u0007A\u001bF\u000b\u0005\u0002\u0013#&\u0011!k\u0005\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012\u0001A\u0001\bO\u0016tWM]5d+\t9&,F\u0001Y!\ri\u0012&\u0017\t\u0003[i#\u0011b\f\u0004!\u0002\u0003\u0005)\u0019\u0001\u0019)\u0005iC\u0004"
)
public final class genericEq {
   public static Eq generic() {
      return genericEq$.MODULE$.generic();
   }

   private static class GenericEq implements Eq {
      private static final long serialVersionUID = 0L;

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
         return BoxesRunTime.equals(x, y);
      }

      public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
         return this.eqv(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
      }

      public boolean eqv$mcB$sp(final byte x, final byte y) {
         return this.eqv(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
      }

      public boolean eqv$mcC$sp(final char x, final char y) {
         return this.eqv(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
      }

      public boolean eqv$mcD$sp(final double x, final double y) {
         return this.eqv(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
      }

      public boolean eqv$mcF$sp(final float x, final float y) {
         return this.eqv(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
      }

      public boolean eqv$mcI$sp(final int x, final int y) {
         return this.eqv(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
      }

      public boolean eqv$mcJ$sp(final long x, final long y) {
         return this.eqv(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
      }

      public boolean eqv$mcS$sp(final short x, final short y) {
         return this.eqv(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
      }

      public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return this.eqv(x, y);
      }

      public GenericEq() {
         Eq.$init$(this);
      }
   }
}
