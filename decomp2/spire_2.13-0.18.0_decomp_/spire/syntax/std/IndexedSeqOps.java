package spire.syntax.std;

import cats.kernel.Order;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import spire.math.Searching$;

@ScalaSignature(
   bytes = "\u0006\u0005M3A\u0001B\u0003\u0003\u0019!AA\u0003\u0001B\u0001B\u0003%Q\u0003C\u00038\u0001\u0011\u0005\u0001\bC\u0003>\u0001\u0011\u0005aHA\u0007J]\u0012,\u00070\u001a3TKF|\u0005o\u001d\u0006\u0003\r\u001d\t1a\u001d;e\u0015\tA\u0011\"\u0001\u0004ts:$\u0018\r\u001f\u0006\u0002\u0015\u0005)1\u000f]5sK\u000e\u0001QcA\u00073/M\u0011\u0001A\u0004\t\u0003\u001fIi\u0011\u0001\u0005\u0006\u0002#\u0005)1oY1mC&\u00111\u0003\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\u0005\u0005\u001c\bc\u0001\f\u0018c1\u0001A!\u0002\r\u0001\u0005\u0004I\"AA\"D+\tQ2&\u0005\u0002\u001c=A\u0011q\u0002H\u0005\u0003;A\u0011qAT8uQ&tw\rE\u0002 O)r!\u0001I\u0013\u000f\u0005\u0005\"S\"\u0001\u0012\u000b\u0005\rZ\u0011A\u0002\u001fs_>$h(C\u0001\u0012\u0013\t1\u0003#A\u0004qC\u000e\\\u0017mZ3\n\u0005!J#AC%oI\u0016DX\rZ*fc*\u0011a\u0005\u0005\t\u0003--\"Q\u0001L\fC\u00025\u0012\u0011!Q\t\u000379\u0002\"aD\u0018\n\u0005A\u0002\"aA!osB\u0011aC\r\u0003\nY\u0001\u0001\u000b\u0011!AC\u00025B#A\r\u001b\u0011\u0005=)\u0014B\u0001\u001c\u0011\u0005-\u0019\b/Z2jC2L'0\u001a3\u0002\rqJg.\u001b;?)\tID\b\u0005\u0003;\u0001EZT\"A\u0003\u0011\u0005Y9\u0002\"\u0002\u000b\u0003\u0001\u0004)\u0012aB9tK\u0006\u00148\r\u001b\u000b\u0003\u007fE#\"\u0001Q\"\u0011\u0005=\t\u0015B\u0001\"\u0011\u0005\rIe\u000e\u001e\u0005\u0006\t\u000e\u0001\u001d!R\u0001\u0003KZ\u00042A\u0012(2\u001d\t9EJ\u0004\u0002I\u0015:\u0011\u0011%S\u0005\u0002\u0015%\u00111*C\u0001\bC2<WM\u0019:b\u0013\t1SJ\u0003\u0002L\u0013%\u0011q\n\u0015\u0002\u0006\u001fJ$WM\u001d\u0006\u0003M5CQAU\u0002A\u0002E\n\u0011!\u0019"
)
public class IndexedSeqOps {
   public final IndexedSeq spire$syntax$std$IndexedSeqOps$$as;

   public int qsearch(final Object a, final Order ev) {
      return Searching$.MODULE$.search(this.spire$syntax$std$IndexedSeqOps$$as, a, ev);
   }

   public int qsearch$mcZ$sp(final boolean a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToBoolean(a), ev);
   }

   public int qsearch$mcB$sp(final byte a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToByte(a), ev);
   }

   public int qsearch$mcC$sp(final char a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToCharacter(a), ev);
   }

   public int qsearch$mcD$sp(final double a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToDouble(a), ev);
   }

   public int qsearch$mcF$sp(final float a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToFloat(a), ev);
   }

   public int qsearch$mcI$sp(final int a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToInteger(a), ev);
   }

   public int qsearch$mcJ$sp(final long a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToLong(a), ev);
   }

   public int qsearch$mcS$sp(final short a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToShort(a), ev);
   }

   public int qsearch$mcV$sp(final BoxedUnit a, final Order ev) {
      return this.qsearch(a, ev);
   }

   public IndexedSeqOps(final IndexedSeq as) {
      this.spire$syntax$std$IndexedSeqOps$$as = as;
   }
}
