package cats.kernel;

import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.hashing.Hashing;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=aa\u0002\u0006\f!\u0003\r\n\u0001\u0005\u0005\u0006i\u00011\t!N\u0004\u0006w-A\t\u0001\u0010\u0004\u0006\u0015-A\t!\u0010\u0005\u0006\u0013\u000e!\tA\u0013\u0005\u0006\u0017\u000e!)\u0001\u0014\u0005\u0006/\u000e!\t\u0001\u0017\u0005\u0006U\u000e!\ta\u001b\u0005\u0006s\u000e!\tA\u001f\u0005\t\u007f\u000e\t\t\u0011\"\u0003\u0002\u0002\t!\u0001*Y:i\u0015\taQ\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u001d\u0005!1-\u0019;t\u0007\u0001)\"!\u0005\u0010\u0014\t\u0001\u0011\u0002\u0004\u000b\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0004\u0003:L\bcA\r\u001b95\t1\"\u0003\u0002\u001c\u0017\t\u0011Q)\u001d\t\u0003;ya\u0001\u0001B\u0005 \u0001\u0001\u0006\t\u0011!b\u0001A\t\t\u0011)\u0005\u0002\"%A\u00111CI\u0005\u0003GQ\u0011qAT8uQ&tw\r\u000b\u0002\u001fKA\u00111CJ\u0005\u0003OQ\u00111b\u001d9fG&\fG.\u001b>fIB\u0011\u0011&\r\b\u0003U=r!a\u000b\u0018\u000e\u00031R!!L\b\u0002\rq\u0012xn\u001c;?\u0013\u0005)\u0012B\u0001\u0019\u0015\u0003\u001d\u0001\u0018mY6bO\u0016L!AM\u001a\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005A\"\u0012\u0001\u00025bg\"$\"AN\u001d\u0011\u0005M9\u0014B\u0001\u001d\u0015\u0005\rIe\u000e\u001e\u0005\u0006u\u0005\u0001\r\u0001H\u0001\u0002q\u0006!\u0001*Y:i!\tI2aE\u0002\u0004}\t\u00032!G B\u0013\t\u00015BA\u0007ICNDg)\u001e8di&|gn\u001d\t\u00033\u0001\u0001\"a\u0011%\u000e\u0003\u0011S!!\u0012$\u0002\u0005%|'\"A$\u0002\t)\fg/Y\u0005\u0003e\u0011\u000ba\u0001P5oSRtD#\u0001\u001f\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u00055\u0003FC\u0001(R!\rI\u0002a\u0014\t\u0003;A#QaH\u0003C\u0002\u0001BQAU\u0003A\u00049\u000b!!\u001a<)\u0005\u0015!\u0006CA\nV\u0013\t1FC\u0001\u0004j]2Lg.Z\u0001\u0003Ef,2!W/c)\tQV\r\u0006\u0002\\?B\u0019\u0011\u0004\u0001/\u0011\u0005uiF!C\u0010\u0007A\u0003\u0005\tQ1\u0001!Q\tiV\u0005C\u0003S\r\u0001\u000f\u0001\rE\u0002\u001a\u0001\u0005\u0004\"!\b2\u0005\u0013\r4\u0001\u0015!A\u0001\u0006\u0004\u0001#!\u0001\")\u0005\t,\u0003\"\u00024\u0007\u0001\u00049\u0017!\u00014\u0011\tMAG,Y\u0005\u0003SR\u0011\u0011BR;oGRLwN\\\u0019\u0002\u0017\u0019\u0014x.\u001c%bg\"LgnZ\u000b\u0003Y>$\"!\u001c9\u0011\u0007e\u0001a\u000e\u0005\u0002\u001e_\u0012)qd\u0002b\u0001A!)!k\u0002a\u0002cB\u0019!o\u001e8\u000e\u0003MT!\u0001^;\u0002\u000f!\f7\u000f[5oO*\u0011a\u000fF\u0001\u0005kRLG.\u0003\u0002yg\n9\u0001*Y:iS:<\u0017!\u00064s_6,f.\u001b<feN\fG\u000eS1tQ\u000e{G-Z\u000b\u0003wz,\u0012\u0001 \t\u00043\u0001i\bCA\u000f\u007f\t\u0015y\u0002B1\u0001!\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\u0019\u0001\u0005\u0003\u0002\u0006\u0005-QBAA\u0004\u0015\r\tIAR\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u000e\u0005\u001d!AB(cU\u0016\u001cG\u000f"
)
public interface Hash extends Eq {
   static Hash fromUniversalHashCode() {
      return Hash$.MODULE$.fromUniversalHashCode();
   }

   static Hash fromHashing(final Hashing ev) {
      return Hash$.MODULE$.fromHashing(ev);
   }

   static Hash by(final Function1 f, final Hash ev) {
      return Hash$.MODULE$.by(f, ev);
   }

   static Hash apply(final Hash ev) {
      return Hash$.MODULE$.apply(ev);
   }

   int hash(final Object x);

   // $FF: synthetic method
   static int hash$mcZ$sp$(final Hash $this, final boolean x) {
      return $this.hash$mcZ$sp(x);
   }

   default int hash$mcZ$sp(final boolean x) {
      return this.hash(BoxesRunTime.boxToBoolean(x));
   }

   // $FF: synthetic method
   static int hash$mcB$sp$(final Hash $this, final byte x) {
      return $this.hash$mcB$sp(x);
   }

   default int hash$mcB$sp(final byte x) {
      return this.hash(BoxesRunTime.boxToByte(x));
   }

   // $FF: synthetic method
   static int hash$mcC$sp$(final Hash $this, final char x) {
      return $this.hash$mcC$sp(x);
   }

   default int hash$mcC$sp(final char x) {
      return this.hash(BoxesRunTime.boxToCharacter(x));
   }

   // $FF: synthetic method
   static int hash$mcD$sp$(final Hash $this, final double x) {
      return $this.hash$mcD$sp(x);
   }

   default int hash$mcD$sp(final double x) {
      return this.hash(BoxesRunTime.boxToDouble(x));
   }

   // $FF: synthetic method
   static int hash$mcF$sp$(final Hash $this, final float x) {
      return $this.hash$mcF$sp(x);
   }

   default int hash$mcF$sp(final float x) {
      return this.hash(BoxesRunTime.boxToFloat(x));
   }

   // $FF: synthetic method
   static int hash$mcI$sp$(final Hash $this, final int x) {
      return $this.hash$mcI$sp(x);
   }

   default int hash$mcI$sp(final int x) {
      return this.hash(BoxesRunTime.boxToInteger(x));
   }

   // $FF: synthetic method
   static int hash$mcJ$sp$(final Hash $this, final long x) {
      return $this.hash$mcJ$sp(x);
   }

   default int hash$mcJ$sp(final long x) {
      return this.hash(BoxesRunTime.boxToLong(x));
   }

   // $FF: synthetic method
   static int hash$mcS$sp$(final Hash $this, final short x) {
      return $this.hash$mcS$sp(x);
   }

   default int hash$mcS$sp(final short x) {
      return this.hash(BoxesRunTime.boxToShort(x));
   }

   // $FF: synthetic method
   static int hash$mcV$sp$(final Hash $this, final BoxedUnit x) {
      return $this.hash$mcV$sp(x);
   }

   default int hash$mcV$sp(final BoxedUnit x) {
      return this.hash(x);
   }
}
