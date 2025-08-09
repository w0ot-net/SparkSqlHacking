package cats.kernel.instances;

import cats.kernel.Hash;
import scala.collection.immutable.Vector;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005y2A\u0001B\u0003\u0001\u0019!A\u0001\u0007\u0001B\u0001B\u0003-\u0011\u0007C\u00033\u0001\u0011\u00051\u0007C\u00038\u0001\u0011\u0005\u0001H\u0001\u0006WK\u000e$xN\u001d%bg\"T!AB\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0005\n\u0003\u0019YWM\u001d8fY*\t!\"\u0001\u0003dCR\u001c8\u0001A\u000b\u0003\u001bQ\u00192\u0001\u0001\b!!\ry\u0001CE\u0007\u0002\u000b%\u0011\u0011#\u0002\u0002\t-\u0016\u001cGo\u001c:FcB\u00111\u0003\u0006\u0007\u0001\t\u0015)\u0002A1\u0001\u0017\u0005\u0005\t\u0015CA\f\u001e!\tA2$D\u0001\u001a\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0007\u0010\n\u0005}I\"aA!osB\u0019\u0011E\t\u0013\u000e\u0003\u001dI!aI\u0004\u0003\t!\u000b7\u000f\u001b\t\u0004K5\u0012bB\u0001\u0014,\u001d\t9#&D\u0001)\u0015\tI3\"\u0001\u0004=e>|GOP\u0005\u00025%\u0011A&G\u0001\ba\u0006\u001c7.Y4f\u0013\tqsF\u0001\u0004WK\u000e$xN\u001d\u0006\u0003Ye\t!!\u001a<\u0011\u0007\u0005\u0012##\u0001\u0004=S:LGO\u0010\u000b\u0002iQ\u0011QG\u000e\t\u0004\u001f\u0001\u0011\u0002\"\u0002\u0019\u0003\u0001\b\t\u0014\u0001\u00025bg\"$\"!\u000f\u001f\u0011\u0005aQ\u0014BA\u001e\u001a\u0005\rIe\u000e\u001e\u0005\u0006{\r\u0001\r\u0001J\u0001\u0003qN\u0004"
)
public class VectorHash extends VectorEq implements Hash {
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

   public int hash(final Vector xs) {
      return StaticMethods$.MODULE$.orderedHash(xs, this.ev);
   }

   public VectorHash(final Hash ev) {
      super(ev);
      this.ev = ev;
   }
}
