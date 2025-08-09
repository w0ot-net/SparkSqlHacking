package spire.math;

import algebra.ring.Field;
import cats.kernel.Eq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=4\u0001BD\b\u0011\u0002\u0007\u0005qb\u0005\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006i\u0001!\t%\u000e\u0005\u0006\u0001\u0002!\t%\u0011\u0005\u0006\r\u0002!\te\u0012\u0005\u0006\u0019\u0002!\t!\u0014\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0005\u0006#\u0002!\tA\u0015\u0005\u0006+\u0002!\tE\u0016\u0005\u00069\u0002!\t%\u0018\u0005\u0006A\u0002!\t\u0001\u0015\u0005\u0006C\u0002!\tE\u0019\u0005\u0006K\u0002!\tE\u001a\u0005\u0006W\u0002!\t\u0001\u001c\u0002\u0010%\u0006$\u0018n\u001c8bY&\u001bh)[3mI*\u0011\u0001#E\u0001\u0005[\u0006$\bNC\u0001\u0013\u0003\u0015\u0019\b/\u001b:f'\r\u0001AC\u0007\t\u0003+ai\u0011A\u0006\u0006\u0002/\u0005)1oY1mC&\u0011\u0011D\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0007mA3F\u0004\u0002\u001dK9\u0011Qd\t\b\u0003=\tj\u0011a\b\u0006\u0003A\u0005\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002%%\u0011A%E\u0001\bC2<WM\u0019:b\u0013\t1s%A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\u0011\n\u0012BA\u0015+\u0005\u00151\u0015.\u001a7e\u0015\t1s\u0005\u0005\u0002-[5\tq\"\u0003\u0002/\u001f\tA!+\u0019;j_:\fG.\u0001\u0004%S:LG\u000f\n\u000b\u0002cA\u0011QCM\u0005\u0003gY\u0011A!\u00168ji\u0006\u0019qm\u00193\u0015\u0007Ybd\b\u0006\u0002,o!)\u0001H\u0001a\u0002s\u0005\u0011QM\u001e\t\u00047iZ\u0013BA\u001e+\u0005\t)\u0015\u000fC\u0003>\u0005\u0001\u00071&A\u0001y\u0011\u0015y$\u00011\u0001,\u0003\u0005I\u0018a\u00017d[R\u0019!\tR#\u0015\u0005-\u001a\u0005\"\u0002\u001d\u0004\u0001\bI\u0004\"B\u001f\u0004\u0001\u0004Y\u0003\"B \u0004\u0001\u0004Y\u0013!B7j]V\u001cHcA\u0016I\u0015\")\u0011\n\u0002a\u0001W\u0005\t\u0011\rC\u0003L\t\u0001\u00071&A\u0001c\u0003\u0019qWmZ1uKR\u00111F\u0014\u0005\u0006\u0013\u0016\u0001\raK\u0001\u0004_:,W#A\u0016\u0002\tAdWo\u001d\u000b\u0004WM#\u0006\"B%\b\u0001\u0004Y\u0003\"B&\b\u0001\u0004Y\u0013a\u00019poR\u00191f\u0016-\t\u000b%C\u0001\u0019A\u0016\t\u000b-C\u0001\u0019A-\u0011\u0005UQ\u0016BA.\u0017\u0005\rIe\u000e^\u0001\u0006i&lWm\u001d\u000b\u0004Wy{\u0006\"B%\n\u0001\u0004Y\u0003\"B&\n\u0001\u0004Y\u0013\u0001\u0002>fe>\fqA\u001a:p[&sG\u000f\u0006\u0002,G\")Am\u0003a\u00013\u0006\ta.\u0001\u0006ge>lGi\\;cY\u0016$\"aK4\t\u000b\u0011d\u0001\u0019\u00015\u0011\u0005UI\u0017B\u00016\u0017\u0005\u0019!u.\u001e2mK\u0006\u0019A-\u001b<\u0015\u0007-jg\u000eC\u0003J\u001b\u0001\u00071\u0006C\u0003L\u001b\u0001\u00071\u0006"
)
public interface RationalIsField extends Field {
   // $FF: synthetic method
   static Rational gcd$(final RationalIsField $this, final Rational x, final Rational y, final Eq ev) {
      return $this.gcd(x, y, ev);
   }

   default Rational gcd(final Rational x, final Rational y, final Eq ev) {
      return x.gcd(y);
   }

   // $FF: synthetic method
   static Rational lcm$(final RationalIsField $this, final Rational x, final Rational y, final Eq ev) {
      return $this.lcm(x, y, ev);
   }

   default Rational lcm(final Rational x, final Rational y, final Eq ev) {
      return x.lcm(y);
   }

   // $FF: synthetic method
   static Rational minus$(final RationalIsField $this, final Rational a, final Rational b) {
      return $this.minus(a, b);
   }

   default Rational minus(final Rational a, final Rational b) {
      return a.$minus(b);
   }

   // $FF: synthetic method
   static Rational negate$(final RationalIsField $this, final Rational a) {
      return $this.negate(a);
   }

   default Rational negate(final Rational a) {
      return a.unary_$minus();
   }

   // $FF: synthetic method
   static Rational one$(final RationalIsField $this) {
      return $this.one();
   }

   default Rational one() {
      return Rational$.MODULE$.one();
   }

   // $FF: synthetic method
   static Rational plus$(final RationalIsField $this, final Rational a, final Rational b) {
      return $this.plus(a, b);
   }

   default Rational plus(final Rational a, final Rational b) {
      return a.$plus(b);
   }

   // $FF: synthetic method
   static Rational pow$(final RationalIsField $this, final Rational a, final int b) {
      return $this.pow(a, b);
   }

   default Rational pow(final Rational a, final int b) {
      return a.pow(b);
   }

   // $FF: synthetic method
   static Rational times$(final RationalIsField $this, final Rational a, final Rational b) {
      return $this.times(a, b);
   }

   default Rational times(final Rational a, final Rational b) {
      return a.$times(b);
   }

   // $FF: synthetic method
   static Rational zero$(final RationalIsField $this) {
      return $this.zero();
   }

   default Rational zero() {
      return Rational$.MODULE$.zero();
   }

   // $FF: synthetic method
   static Rational fromInt$(final RationalIsField $this, final int n) {
      return $this.fromInt(n);
   }

   default Rational fromInt(final int n) {
      return Rational$.MODULE$.apply(n);
   }

   // $FF: synthetic method
   static Rational fromDouble$(final RationalIsField $this, final double n) {
      return $this.fromDouble(n);
   }

   default Rational fromDouble(final double n) {
      return Rational$.MODULE$.apply(n);
   }

   // $FF: synthetic method
   static Rational div$(final RationalIsField $this, final Rational a, final Rational b) {
      return $this.div(a, b);
   }

   default Rational div(final Rational a, final Rational b) {
      return a.$div(b);
   }

   static void $init$(final RationalIsField $this) {
   }
}
