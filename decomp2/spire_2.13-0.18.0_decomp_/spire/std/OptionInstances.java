package spire.std;

import algebra.ring.AdditiveSemigroup;
import algebra.ring.MultiplicativeSemigroup;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003@\u0001\u0011\r\u0001\tC\u0003M\u0001\u0011\rQ\nC\u0003Z\u0001\u0011\r!LA\bPaRLwN\\%ogR\fgnY3t\u0015\tA\u0011\"A\u0002ti\u0012T\u0011AC\u0001\u0006gBL'/Z\u0002\u0001'\r\u0001Qb\u0005\t\u0003\u001dEi\u0011a\u0004\u0006\u0002!\u0005)1oY1mC&\u0011!c\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Q)R\"A\u0004\n\u0005Y9!\u0001E(qi&|g.\u00138ti\u0006t7-Z:1\u0003\u0019!\u0013N\\5uIQ\t\u0011\u0004\u0005\u0002\u000f5%\u00111d\u0004\u0002\u0005+:LG/A\u0007PaRLwN\\\"N_:|\u0017\u000eZ\u000b\u0003=\u0011\"\"aH\u0017\u0011\u0007Q\u0001#%\u0003\u0002\"\u000f\tiq\n\u001d;j_:\u001cUj\u001c8pS\u0012\u0004\"a\t\u0013\r\u0001\u0011)QE\u0001b\u0001M\t\t\u0011)\u0005\u0002(UA\u0011a\u0002K\u0005\u0003S=\u0011qAT8uQ&tw\r\u0005\u0002\u000fW%\u0011Af\u0004\u0002\u0004\u0003:L\bb\u0002\u0018\u0003\u0003\u0003\u0005\u001daL\u0001\u000bKZLG-\u001a8dK\u0012J\u0004c\u0001\u0019=E9\u0011\u0011'\u000f\b\u0003e]r!a\r\u001c\u000e\u0003QR!!N\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0011B\u0001\u001d\n\u0003\u001d\tGnZ3ce\u0006L!AO\u001e\u0002\u000fA\f7m[1hK*\u0011\u0001(C\u0005\u0003{y\u0012!bQ*f[&<'o\\;q\u0015\tQ4(\u0001\u000bPaRLwN\\!eI&$\u0018N^3N_:|\u0017\u000eZ\u000b\u0003\u0003\u001a#\"AQ$\u0011\u0007Q\u0019U)\u0003\u0002E\u000f\t!r\n\u001d;j_:\fE\rZ5uSZ,Wj\u001c8pS\u0012\u0004\"a\t$\u0005\u000b\u0015\u001a!\u0019\u0001\u0014\t\u000f!\u001b\u0011\u0011!a\u0002\u0013\u0006YQM^5eK:\u001cW\rJ\u00191!\r\u0001$*R\u0005\u0003\u0017z\u0012\u0011#\u00113eSRLg/Z*f[&<'o\\;q\u0003iy\u0005\u000f^5p]6+H\u000e^5qY&\u001c\u0017\r^5wK6{gn\\5e+\tq5\u000b\u0006\u0002P)B\u0019A\u0003\u0015*\n\u0005E;!AG(qi&|g.T;mi&\u0004H.[2bi&4X-T8o_&$\u0007CA\u0012T\t\u0015)CA1\u0001'\u0011\u001d)F!!AA\u0004Y\u000b1\"\u001a<jI\u0016t7-\u001a\u00132cA\u0019\u0001g\u0016*\n\u0005as$aF'vYRL\u0007\u000f\\5dCRLg/Z*f[&<'o\\;q\u0003-y\u0005\u000f^5p]>\u0013H-\u001a:\u0016\u0005m\u0003GC\u0001/b!\r!RlX\u0005\u0003=\u001e\u00111b\u00149uS>twJ\u001d3feB\u00111\u0005\u0019\u0003\u0006K\u0015\u0011\rA\n\u0005\bE\u0016\t\t\u0011q\u0001d\u0003-)g/\u001b3f]\u000e,G%\r\u001a\u0011\u0007A\"w,\u0003\u0002f}\t)qJ\u001d3fe\u0002"
)
public interface OptionInstances extends OptionInstances0 {
   // $FF: synthetic method
   static OptionCMonoid OptionCMonoid$(final OptionInstances $this, final CommutativeSemigroup evidence$9) {
      return $this.OptionCMonoid(evidence$9);
   }

   default OptionCMonoid OptionCMonoid(final CommutativeSemigroup evidence$9) {
      return new OptionCMonoid(evidence$9);
   }

   // $FF: synthetic method
   static OptionAdditiveMonoid OptionAdditiveMonoid$(final OptionInstances $this, final AdditiveSemigroup evidence$10) {
      return $this.OptionAdditiveMonoid(evidence$10);
   }

   default OptionAdditiveMonoid OptionAdditiveMonoid(final AdditiveSemigroup evidence$10) {
      return new OptionAdditiveMonoid(evidence$10);
   }

   // $FF: synthetic method
   static OptionMultiplicativeMonoid OptionMultiplicativeMonoid$(final OptionInstances $this, final MultiplicativeSemigroup evidence$11) {
      return $this.OptionMultiplicativeMonoid(evidence$11);
   }

   default OptionMultiplicativeMonoid OptionMultiplicativeMonoid(final MultiplicativeSemigroup evidence$11) {
      return new OptionMultiplicativeMonoid(evidence$11);
   }

   // $FF: synthetic method
   static OptionOrder OptionOrder$(final OptionInstances $this, final Order evidence$12) {
      return $this.OptionOrder(evidence$12);
   }

   default OptionOrder OptionOrder(final Order evidence$12) {
      return new OptionOrder(evidence$12);
   }

   static void $init$(final OptionInstances $this) {
   }
}
