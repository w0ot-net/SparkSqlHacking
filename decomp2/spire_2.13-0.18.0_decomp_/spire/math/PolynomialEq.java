package spire.math;

import algebra.ring.Semiring;
import cats.kernel.Eq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u3qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u0003?\u0001\u0011\u0005q\bC\u0003D\u0001\u0019\rA\tC\u0003I\u0001\u0019\r\u0011\nC\u0003L\u0001\u0019\rA\nC\u0003U\u0001\u0011\u0005QK\u0001\u0007Q_2Lhn\\7jC2,\u0015O\u0003\u0002\t\u0013\u0005!Q.\u0019;i\u0015\u0005Q\u0011!B:qSJ,7\u0001A\u000b\u0003\u001b)\u001a2\u0001\u0001\b\u0015!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fMB\u0019Q#\t\u0013\u000f\u0005YqbBA\f\u001d\u001d\tA2$D\u0001\u001a\u0015\tQ2\"\u0001\u0004=e>|GOP\u0005\u0002\u0015%\u0011Q$C\u0001\bC2<WM\u0019:b\u0013\ty\u0002%A\u0004qC\u000e\\\u0017mZ3\u000b\u0005uI\u0011B\u0001\u0012$\u0005\t)\u0015O\u0003\u0002 AA\u0019QE\n\u0015\u000e\u0003\u001dI!aJ\u0004\u0003\u0015A{G.\u001f8p[&\fG\u000e\u0005\u0002*U1\u0001A!C\u0016\u0001A\u0003\u0005\tQ1\u0001-\u0005\u0005\u0019\u0015CA\u00171!\tya&\u0003\u00020!\t9aj\u001c;iS:<\u0007CA\b2\u0013\t\u0011\u0004CA\u0002B]fD3A\u000b\u001b8!\tyQ'\u0003\u00027!\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019\u0003(O\u001e;\u001d\ty\u0011(\u0003\u0002;!\u00051Ai\\;cY\u0016\fD\u0001\n\u001f>#9\u0011\u0001$P\u0005\u0002#\u00051A%\u001b8ji\u0012\"\u0012\u0001\u0011\t\u0003\u001f\u0005K!A\u0011\t\u0003\tUs\u0017\u000e^\u0001\u0007g\u000e\fG.\u0019:\u0016\u0003\u0015\u00032!\u0006$)\u0013\t95E\u0001\u0005TK6L'/\u001b8h\u0003\t)\u0017/F\u0001K!\r)\u0012\u0005K\u0001\u0003GR,\u0012!\u0014\t\u0004\u001dFCcBA(Q\u001b\u0005I\u0011BA\u0010\n\u0013\t\u00116K\u0001\u0005DY\u0006\u001c8\u000fV1h\u0015\ty\u0012\"A\u0002fcZ$2AV-\\!\tyq+\u0003\u0002Y!\t9!i\\8mK\u0006t\u0007\"\u0002.\u0006\u0001\u0004!\u0013!\u0001=\t\u000bq+\u0001\u0019\u0001\u0013\u0002\u0003e\u0004"
)
public interface PolynomialEq extends Eq {
   Semiring scalar();

   Eq eq();

   ClassTag ct();

   // $FF: synthetic method
   static boolean eqv$(final PolynomialEq $this, final Polynomial x, final Polynomial y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final Polynomial x, final Polynomial y) {
      return spire.std.package.array$.MODULE$.ArrayEq(this.eq()).eqv(x.coeffsArray(this.scalar()), y.coeffsArray(this.scalar()));
   }

   // $FF: synthetic method
   static Semiring scalar$mcD$sp$(final PolynomialEq $this) {
      return $this.scalar$mcD$sp();
   }

   default Semiring scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Eq eq$mcD$sp$(final PolynomialEq $this) {
      return $this.eq$mcD$sp();
   }

   default Eq eq$mcD$sp() {
      return this.eq();
   }

   // $FF: synthetic method
   static boolean eqv$mcD$sp$(final PolynomialEq $this, final Polynomial x, final Polynomial y) {
      return $this.eqv$mcD$sp(x, y);
   }

   default boolean eqv$mcD$sp(final Polynomial x, final Polynomial y) {
      return this.eqv(x, y);
   }

   static void $init$(final PolynomialEq $this) {
   }
}
