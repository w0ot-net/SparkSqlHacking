package cats.kernel.instances;

import cats.kernel.Hash;
import scala.collection.immutable.Queue;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005i2A\u0001B\u0003\u0001\u0019!AA\u0006\u0001B\u0001B\u0003-Q\u0006C\u0003/\u0001\u0011\u0005q\u0006C\u00034\u0001\u0011\u0005AGA\u0005Rk\u0016,X\rS1tQ*\u0011aaB\u0001\nS:\u001cH/\u00198dKNT!\u0001C\u0005\u0002\r-,'O\\3m\u0015\u0005Q\u0011\u0001B2biN\u001c\u0001!\u0006\u0002\u000e)M\u0019\u0001A\u0004\u0011\u0011\u0007=\u0001\"#D\u0001\u0006\u0013\t\tRAA\u0004Rk\u0016,X-R9\u0011\u0005M!B\u0002\u0001\u0003\u0006+\u0001\u0011\rA\u0006\u0002\u0002\u0003F\u0011q#\b\t\u00031mi\u0011!\u0007\u0006\u00025\u0005)1oY1mC&\u0011A$\u0007\u0002\b\u001d>$\b.\u001b8h!\tAb$\u0003\u0002 3\t\u0019\u0011I\\=\u0011\u0007\u0005\u0012C%D\u0001\b\u0013\t\u0019sA\u0001\u0003ICND\u0007cA\u0013+%5\taE\u0003\u0002(Q\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003Se\t!bY8mY\u0016\u001cG/[8o\u0013\tYcEA\u0003Rk\u0016,X-\u0001\u0002fmB\u0019\u0011E\t\n\u0002\rqJg.\u001b;?)\u0005\u0001DCA\u00193!\ry\u0001A\u0005\u0005\u0006Y\t\u0001\u001d!L\u0001\u0005Q\u0006\u001c\b\u000e\u0006\u00026qA\u0011\u0001DN\u0005\u0003oe\u00111!\u00138u\u0011\u0015I4\u00011\u0001%\u0003\u0005A\b"
)
public class QueueHash extends QueueEq implements Hash {
   private final Hash ev;

   public int hash$mcZ$sp(final boolean x) {
      return Hash.hash$mcZ$sp$(this, x);
   }

   public int hash$mcB$sp(final byte x) {
      return Hash.hash$mcB$sp$(this, x);
   }

   public int hash$mcC$sp(final char x) {
      return Hash.hash$mcC$sp$(this, x);
   }

   public int hash$mcD$sp(final double x) {
      return Hash.hash$mcD$sp$(this, x);
   }

   public int hash$mcF$sp(final float x) {
      return Hash.hash$mcF$sp$(this, x);
   }

   public int hash$mcI$sp(final int x) {
      return Hash.hash$mcI$sp$(this, x);
   }

   public int hash$mcJ$sp(final long x) {
      return Hash.hash$mcJ$sp$(this, x);
   }

   public int hash$mcS$sp(final short x) {
      return Hash.hash$mcS$sp$(this, x);
   }

   public int hash$mcV$sp(final BoxedUnit x) {
      return Hash.hash$mcV$sp$(this, x);
   }

   public int hash(final Queue x) {
      return StaticMethods$.MODULE$.orderedHash(x, this.ev);
   }

   public QueueHash(final Hash ev) {
      super(ev);
      this.ev = ev;
   }
}
