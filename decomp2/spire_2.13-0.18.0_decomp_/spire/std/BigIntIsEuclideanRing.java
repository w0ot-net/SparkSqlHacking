package spire.std;

import algebra.ring.EuclideanRing;
import cats.kernel.Eq;
import scala.Tuple2;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m4q\u0001E\t\u0011\u0002\u0007\u0005a\u0003C\u00036\u0001\u0011\u0005a\u0007C\u0003;\u0001\u0011\u00053\bC\u0003A\u0001\u0011\u0005\u0011\tC\u0004D\u0001\t\u0007I\u0011\u0001#\t\u000b\u0015\u0003A\u0011\u0001$\t\u000b%\u0003A\u0011\t&\t\u000bA\u0003A\u0011I)\t\u000fQ\u0003!\u0019!C\u0001\t\")Q\u000b\u0001C!-\")\u0011\f\u0001C\u00015\")A\f\u0001C!;\")1\r\u0001C\u0001I\")q\r\u0001C\u0001Q\")1\u000e\u0001C!Y\")Q\u000f\u0001C!m\n)\")[4J]RL5/R;dY&$W-\u00198SS:<'B\u0001\n\u0014\u0003\r\u0019H\u000f\u001a\u0006\u0002)\u0005)1\u000f]5sK\u000e\u00011c\u0001\u0001\u0018;A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\u00042A\b\u0016.\u001d\tyrE\u0004\u0002!K9\u0011\u0011\u0005J\u0007\u0002E)\u00111%F\u0001\u0007yI|w\u000e\u001e \n\u0003QI!AJ\n\u0002\u000f\u0005dw-\u001a2sC&\u0011\u0001&K\u0001\ba\u0006\u001c7.Y4f\u0015\t13#\u0003\u0002,Y\tiQ)^2mS\u0012,\u0017M\u001c*j]\u001eT!\u0001K\u0015\u0011\u00059\u0012dBA\u00182\u001d\t\t\u0003'C\u0001\u001b\u0013\tA\u0013$\u0003\u00024i\t1!)[4J]RT!\u0001K\r\u0002\r\u0011Jg.\u001b;%)\u00059\u0004C\u0001\r9\u0013\tI\u0014D\u0001\u0003V]&$\u0018!B7j]V\u001cHcA\u0017=}!)QH\u0001a\u0001[\u0005\t\u0011\rC\u0003@\u0005\u0001\u0007Q&A\u0001c\u0003\u0019qWmZ1uKR\u0011QF\u0011\u0005\u0006{\r\u0001\r!L\u0001\u0004_:,W#A\u0017\u0002\tAdWo\u001d\u000b\u0004[\u001dC\u0005\"B\u001f\u0006\u0001\u0004i\u0003\"B \u0006\u0001\u0004i\u0013a\u00019poR\u0019Qf\u0013'\t\u000bu2\u0001\u0019A\u0017\t\u000b}2\u0001\u0019A'\u0011\u0005aq\u0015BA(\u001a\u0005\rIe\u000e^\u0001\u0006i&lWm\u001d\u000b\u0004[I\u001b\u0006\"B\u001f\b\u0001\u0004i\u0003\"B \b\u0001\u0004i\u0013\u0001\u0002>fe>\fqA\u001a:p[&sG\u000f\u0006\u0002./\")\u0001,\u0003a\u0001\u001b\u0006\ta.A\tfk\u000ed\u0017\u000eZ3b]\u001a+hn\u0019;j_:$\"!L.\t\u000buR\u0001\u0019A\u0017\u0002\u0011\u0015\fXo\u001c;n_\u0012$2AX1c!\u0011Ar,L\u0017\n\u0005\u0001L\"A\u0002+va2,'\u0007C\u0003>\u0017\u0001\u0007Q\u0006C\u0003@\u0017\u0001\u0007Q&A\u0003fcV|G\u000fF\u0002.K\u001aDQ!\u0010\u0007A\u00025BQa\u0010\u0007A\u00025\nA!Z7pIR\u0019Q&\u001b6\t\u000buj\u0001\u0019A\u0017\t\u000b}j\u0001\u0019A\u0017\u0002\u00071\u001cW\u000eF\u0002ngR$\"!\f8\t\u000b=t\u00019\u00019\u0002\u0005\u00154\bc\u0001\u0010r[%\u0011!\u000f\f\u0002\u0003\u000bFDQ!\u0010\bA\u00025BQa\u0010\bA\u00025\n1aZ2e)\r9\u0018P\u001f\u000b\u0003[aDQa\\\bA\u0004ADQ!P\bA\u00025BQaP\bA\u00025\u0002"
)
public interface BigIntIsEuclideanRing extends EuclideanRing {
   void spire$std$BigIntIsEuclideanRing$_setter_$one_$eq(final BigInt x$1);

   void spire$std$BigIntIsEuclideanRing$_setter_$zero_$eq(final BigInt x$1);

   // $FF: synthetic method
   static BigInt minus$(final BigIntIsEuclideanRing $this, final BigInt a, final BigInt b) {
      return $this.minus(a, b);
   }

   default BigInt minus(final BigInt a, final BigInt b) {
      return a.$minus(b);
   }

   // $FF: synthetic method
   static BigInt negate$(final BigIntIsEuclideanRing $this, final BigInt a) {
      return $this.negate(a);
   }

   default BigInt negate(final BigInt a) {
      return a.unary_$minus();
   }

   BigInt one();

   // $FF: synthetic method
   static BigInt plus$(final BigIntIsEuclideanRing $this, final BigInt a, final BigInt b) {
      return $this.plus(a, b);
   }

   default BigInt plus(final BigInt a, final BigInt b) {
      return a.$plus(b);
   }

   // $FF: synthetic method
   static BigInt pow$(final BigIntIsEuclideanRing $this, final BigInt a, final int b) {
      return $this.pow(a, b);
   }

   default BigInt pow(final BigInt a, final int b) {
      return a.pow(b);
   }

   // $FF: synthetic method
   static BigInt times$(final BigIntIsEuclideanRing $this, final BigInt a, final BigInt b) {
      return $this.times(a, b);
   }

   default BigInt times(final BigInt a, final BigInt b) {
      return a.$times(b);
   }

   BigInt zero();

   // $FF: synthetic method
   static BigInt fromInt$(final BigIntIsEuclideanRing $this, final int n) {
      return $this.fromInt(n);
   }

   default BigInt fromInt(final int n) {
      return .MODULE$.BigInt().apply(n);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$(final BigIntIsEuclideanRing $this, final BigInt a) {
      return $this.euclideanFunction(a);
   }

   default BigInt euclideanFunction(final BigInt a) {
      return a.abs();
   }

   // $FF: synthetic method
   static Tuple2 equotmod$(final BigIntIsEuclideanRing $this, final BigInt a, final BigInt b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final BigInt a, final BigInt b) {
      return spire.math.package$.MODULE$.equotmod(a, b);
   }

   // $FF: synthetic method
   static BigInt equot$(final BigIntIsEuclideanRing $this, final BigInt a, final BigInt b) {
      return $this.equot(a, b);
   }

   default BigInt equot(final BigInt a, final BigInt b) {
      return spire.math.package$.MODULE$.equot(a, b);
   }

   // $FF: synthetic method
   static BigInt emod$(final BigIntIsEuclideanRing $this, final BigInt a, final BigInt b) {
      return $this.emod(a, b);
   }

   default BigInt emod(final BigInt a, final BigInt b) {
      return spire.math.package$.MODULE$.emod(a, b);
   }

   // $FF: synthetic method
   static BigInt lcm$(final BigIntIsEuclideanRing $this, final BigInt a, final BigInt b, final Eq ev) {
      return $this.lcm(a, b, ev);
   }

   default BigInt lcm(final BigInt a, final BigInt b, final Eq ev) {
      return a.signum() != 0 && b.signum() != 0 ? a.$div(a.gcd(b)).$times(b) : this.zero();
   }

   // $FF: synthetic method
   static BigInt gcd$(final BigIntIsEuclideanRing $this, final BigInt a, final BigInt b, final Eq ev) {
      return $this.gcd(a, b, ev);
   }

   default BigInt gcd(final BigInt a, final BigInt b, final Eq ev) {
      return a.gcd(b);
   }

   static void $init$(final BigIntIsEuclideanRing $this) {
      $this.spire$std$BigIntIsEuclideanRing$_setter_$one_$eq(.MODULE$.BigInt().apply(1));
      $this.spire$std$BigIntIsEuclideanRing$_setter_$zero_$eq(.MODULE$.BigInt().apply(0));
   }
}
