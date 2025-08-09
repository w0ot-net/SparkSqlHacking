package spire.math;

import algebra.ring.Semiring;
import cats.kernel.Eq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t4q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u0003A\u0001\u0011\u0005\u0011\tC\u0003F\u0001\u0019\ra\tC\u0003I\u0001\u0019\r\u0011\nC\u0003N\u0001\u0019\ra\nC\u0003W\u0001\u0011\u0005q\u000bC\u0003Y\u0001\u0011\u0005\u0011\fC\u0003_\u0001\u0011\u0005qL\u0001\fQ_2Lhn\\7jC2|e/\u001a:TK6L'/\u001b8h\u0015\tQ1\"\u0001\u0003nCRD'\"\u0001\u0007\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0011q\u0002L\n\u0004\u0001A1\u0002CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g\rE\u0002\u0018G\u0019r!\u0001\u0007\u0011\u000f\u0005eqbB\u0001\u000e\u001e\u001b\u0005Y\"B\u0001\u000f\u000e\u0003\u0019a$o\\8u}%\tA\"\u0003\u0002 \u0017\u00059\u0011\r\\4fEJ\f\u0017BA\u0011#\u0003\u001d\u0001\u0018mY6bO\u0016T!aH\u0006\n\u0005\u0011*#\u0001C*f[&\u0014\u0018N\\4\u000b\u0005\u0005\u0012\u0003cA\u0014)U5\t\u0011\"\u0003\u0002*\u0013\tQ\u0001k\u001c7z]>l\u0017.\u00197\u0011\u0005-bC\u0002\u0001\u0003\n[\u0001\u0001\u000b\u0011!AC\u00029\u0012\u0011aQ\t\u0003_I\u0002\"!\u0005\u0019\n\u0005E\u0012\"a\u0002(pi\"Lgn\u001a\t\u0003#MJ!\u0001\u000e\n\u0003\u0007\u0005s\u0017\u0010K\u0002-me\u0002\"!E\u001c\n\u0005a\u0012\"aC:qK\u000eL\u0017\r\\5{K\u0012\fTa\t\u001e<{qr!!E\u001e\n\u0005q\u0012\u0012A\u0002#pk\ndW-\r\u0003%}}\u001abB\u0001\u000e@\u0013\u0005\u0019\u0012A\u0002\u0013j]&$H\u0005F\u0001C!\t\t2)\u0003\u0002E%\t!QK\\5u\u0003\u0019\u00198-\u00197beV\tq\tE\u0002\u0018G)\n!!Z9\u0016\u0003)\u00032aF&+\u0013\taUE\u0001\u0002Fc\u0006\u00111\r^\u000b\u0002\u001fB\u0019\u0001k\u0015\u0016\u000f\u0005E\u0013V\"A\u0006\n\u0005\u0005Z\u0011B\u0001+V\u0005!\u0019E.Y:t)\u0006<'BA\u0011\f\u0003\u0011QXM]8\u0016\u0003\u0019\nA\u0001\u001d7vgR\u0019aE\u0017/\t\u000bm3\u0001\u0019\u0001\u0014\u0002\u0003aDQ!\u0018\u0004A\u0002\u0019\n\u0011!_\u0001\u0006i&lWm\u001d\u000b\u0004M\u0001\f\u0007\"B.\b\u0001\u00041\u0003\"B/\b\u0001\u00041\u0003"
)
public interface PolynomialOverSemiring extends Semiring {
   Semiring scalar();

   Eq eq();

   ClassTag ct();

   // $FF: synthetic method
   static Polynomial zero$(final PolynomialOverSemiring $this) {
      return $this.zero();
   }

   default Polynomial zero() {
      return Polynomial$.MODULE$.zero(this.eq(), this.scalar(), this.ct());
   }

   // $FF: synthetic method
   static Polynomial plus$(final PolynomialOverSemiring $this, final Polynomial x, final Polynomial y) {
      return $this.plus(x, y);
   }

   default Polynomial plus(final Polynomial x, final Polynomial y) {
      return x.$plus(y, this.scalar(), this.eq());
   }

   // $FF: synthetic method
   static Polynomial times$(final PolynomialOverSemiring $this, final Polynomial x, final Polynomial y) {
      return $this.times(x, y);
   }

   default Polynomial times(final Polynomial x, final Polynomial y) {
      return x.$times(y, this.scalar(), this.eq());
   }

   // $FF: synthetic method
   static Semiring scalar$mcD$sp$(final PolynomialOverSemiring $this) {
      return $this.scalar$mcD$sp();
   }

   default Semiring scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Eq eq$mcD$sp$(final PolynomialOverSemiring $this) {
      return $this.eq$mcD$sp();
   }

   default Eq eq$mcD$sp() {
      return this.eq();
   }

   // $FF: synthetic method
   static Polynomial zero$mcD$sp$(final PolynomialOverSemiring $this) {
      return $this.zero$mcD$sp();
   }

   default Polynomial zero$mcD$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Polynomial plus$mcD$sp$(final PolynomialOverSemiring $this, final Polynomial x, final Polynomial y) {
      return $this.plus$mcD$sp(x, y);
   }

   default Polynomial plus$mcD$sp(final Polynomial x, final Polynomial y) {
      return this.plus(x, y);
   }

   // $FF: synthetic method
   static Polynomial times$mcD$sp$(final PolynomialOverSemiring $this, final Polynomial x, final Polynomial y) {
      return $this.times$mcD$sp(x, y);
   }

   default Polynomial times$mcD$sp(final Polynomial x, final Polynomial y) {
      return this.times(x, y);
   }

   static void $init$(final PolynomialOverSemiring $this) {
   }
}
