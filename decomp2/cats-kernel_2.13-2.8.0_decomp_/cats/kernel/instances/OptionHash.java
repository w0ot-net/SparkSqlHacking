package cats.kernel.instances;

import cats.kernel.Hash;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005U2A\u0001B\u0003\u0001\u0019!Aq\u0005\u0001B\u0001B\u0003-\u0001\u0006C\u0003*\u0001\u0011\u0005!\u0006C\u0003/\u0001\u0011\u0005qF\u0001\u0006PaRLwN\u001c%bg\"T!AB\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0005\n\u0003\u0019YWM\u001d8fY*\t!\"\u0001\u0003dCR\u001c8\u0001A\u000b\u0003\u001bQ\u00192\u0001\u0001\b!!\ry\u0001CE\u0007\u0002\u000b%\u0011\u0011#\u0002\u0002\t\u001fB$\u0018n\u001c8FcB\u00111\u0003\u0006\u0007\u0001\t\u0015)\u0002A1\u0001\u0017\u0005\u0005\t\u0015CA\f\u001e!\tA2$D\u0001\u001a\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0007\u0010\n\u0005}I\"aA!osB\u0019\u0011E\t\u0013\u000e\u0003\u001dI!aI\u0004\u0003\t!\u000b7\u000f\u001b\t\u00041\u0015\u0012\u0012B\u0001\u0014\u001a\u0005\u0019y\u0005\u000f^5p]\u0006\t\u0011\tE\u0002\"EI\ta\u0001P5oSRtD#A\u0016\u0015\u00051j\u0003cA\b\u0001%!)qE\u0001a\u0002Q\u0005!\u0001.Y:i)\t\u00014\u0007\u0005\u0002\u0019c%\u0011!'\u0007\u0002\u0004\u0013:$\b\"\u0002\u001b\u0004\u0001\u0004!\u0013!\u0001="
)
public class OptionHash extends OptionEq implements Hash {
   private final Hash A;

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

   public int hash(final Option x) {
      int var2;
      if (.MODULE$.equals(x)) {
         var2 = .MODULE$.hashCode();
      } else {
         if (!(x instanceof Some)) {
            throw new MatchError(x);
         }

         Some var4 = (Some)x;
         Object xx = var4.value();
         var2 = StaticMethods$.MODULE$.product1HashWithPrefix(this.A.hash(xx), x.productPrefix());
      }

      return var2;
   }

   public OptionHash(final Hash A) {
      super(A);
      this.A = A;
   }
}
