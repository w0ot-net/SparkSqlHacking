package cats.kernel.instances;

import cats.kernel.Hash;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005i2A\u0001B\u0003\u0001\u0019!AA\u0006\u0001B\u0001B\u0003-Q\u0006C\u0003/\u0001\u0011\u0005q\u0006C\u00034\u0001\u0011\u0005AGA\u0004TKFD\u0015m\u001d5\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\tA\u0011\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0015\u0005!1-\u0019;t\u0007\u0001)\"!\u0004\u000b\u0014\u0007\u0001q\u0001\u0005E\u0002\u0010!Ii\u0011!B\u0005\u0003#\u0015\u0011QaU3r\u000bF\u0004\"a\u0005\u000b\r\u0001\u0011)Q\u0003\u0001b\u0001-\t\t\u0011)\u0005\u0002\u0018;A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t9aj\u001c;iS:<\u0007C\u0001\r\u001f\u0013\ty\u0012DA\u0002B]f\u00042!\t\u0012%\u001b\u00059\u0011BA\u0012\b\u0005\u0011A\u0015m\u001d5\u0011\u0007\u0015R##D\u0001'\u0015\t9\u0003&A\u0005j[6,H/\u00192mK*\u0011\u0011&G\u0001\u000bG>dG.Z2uS>t\u0017BA\u0016'\u0005\r\u0019V-]\u0001\u0003KZ\u00042!\t\u0012\u0013\u0003\u0019a\u0014N\\5u}Q\t\u0001\u0007\u0006\u00022eA\u0019q\u0002\u0001\n\t\u000b1\u0012\u00019A\u0017\u0002\t!\f7\u000f\u001b\u000b\u0003ka\u0002\"\u0001\u0007\u001c\n\u0005]J\"aA%oi\")\u0011h\u0001a\u0001I\u0005\u0011\u0001p\u001d"
)
public class SeqHash extends SeqEq implements Hash {
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

   public int hash(final Seq xs) {
      return StaticMethods$.MODULE$.orderedHash(xs, this.ev);
   }

   public SeqHash(final Hash ev) {
      super(ev);
      this.ev = ev;
   }
}
