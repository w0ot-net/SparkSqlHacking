package cats.kernel.instances;

import cats.kernel.Hash;
import scala.collection.immutable.LazyList;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005y2A\u0001B\u0003\u0001\u0019!A\u0001\u0007\u0001B\u0001B\u0003-\u0011\u0007C\u00033\u0001\u0011\u00051\u0007C\u00038\u0001\u0011\u0005\u0001H\u0001\u0007MCjLH*[:u\u0011\u0006\u001c\bN\u0003\u0002\u0007\u000f\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0011%\taa[3s]\u0016d'\"\u0001\u0006\u0002\t\r\fGo]\u0002\u0001+\tiAcE\u0002\u0001\u001d\u0001\u00022a\u0004\t\u0013\u001b\u0005)\u0011BA\t\u0006\u0005)a\u0015M_=MSN$X)\u001d\t\u0003'Qa\u0001\u0001B\u0003\u0016\u0001\t\u0007aCA\u0001B#\t9R\u0004\u0005\u0002\u001975\t\u0011DC\u0001\u001b\u0003\u0015\u00198-\u00197b\u0013\ta\u0012DA\u0004O_RD\u0017N\\4\u0011\u0005aq\u0012BA\u0010\u001a\u0005\r\te.\u001f\t\u0004C\t\"S\"A\u0004\n\u0005\r:!\u0001\u0002%bg\"\u00042!J\u0017\u0013\u001d\t13F\u0004\u0002(U5\t\u0001F\u0003\u0002*\u0017\u00051AH]8pizJ\u0011AG\u0005\u0003Ye\tq\u0001]1dW\u0006<W-\u0003\u0002/_\tAA*\u0019>z\u0019&\u001cHO\u0003\u0002-3\u0005\u0011QM\u001e\t\u0004C\t\u0012\u0012A\u0002\u001fj]&$h\bF\u00015)\t)d\u0007E\u0002\u0010\u0001IAQ\u0001\r\u0002A\u0004E\nA\u0001[1tQR\u0011\u0011\b\u0010\t\u00031iJ!aO\r\u0003\u0007%sG\u000fC\u0003>\u0007\u0001\u0007A%\u0001\u0002yg\u0002"
)
public class LazyListHash extends LazyListEq implements Hash {
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

   public int hash(final LazyList xs) {
      return StaticMethods$.MODULE$.orderedHash(xs, this.ev);
   }

   public LazyListHash(final Hash ev) {
      super(ev);
      this.ev = ev;
   }
}
