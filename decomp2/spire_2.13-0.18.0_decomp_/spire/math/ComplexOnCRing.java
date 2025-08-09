package spire.math;

import algebra.ring.CommutativeRing;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.algebra.Involution;
import spire.algebra.RingAssociativeAlgebra;

@ScalaSignature(
   bytes = "\u0006\u0005m4\u0001\u0002D\u0007\u0011\u0002\u0007\u0005Q\"\u0005\u0005\u0006!\u0002!\t!\u0015\u0005\u0006+\u00021\u0019A\u0016\u0005\u00061\u0002!\t%\u0017\u0005\u0006=\u0002!\ta\u0018\u0005\u0006C\u0002!\tA\u0019\u0005\u0006G\u0002!\t\u0001\u001a\u0005\u0006O\u0002!\t\u0005\u001b\u0005\u0006W\u0002!\t\u0001\u001c\u0005\u0006a\u0002!\tA\u0019\u0005\u0006c\u0002!\tA\u001d\u0005\u0006i\u0002!\t%\u001e\u0002\u000f\u0007>l\u0007\u000f\\3y\u001f:\u001c%+\u001b8h\u0015\tqq\"\u0001\u0003nCRD'\"\u0001\t\u0002\u000bM\u0004\u0018N]3\u0016\u0005I\u00014#\u0002\u0001\u00143%k\u0005C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g\rE\u0002\u001bO)r!a\u0007\u0013\u000f\u0005q\u0011cBA\u000f\"\u001b\u0005q\"BA\u0010!\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\t\n\u0005\rz\u0011aB1mO\u0016\u0014'/Y\u0005\u0003K\u0019\nq\u0001]1dW\u0006<WM\u0003\u0002$\u001f%\u0011\u0001&\u000b\u0002\u0006\u0007JKgn\u001a\u0006\u0003K\u0019\u00022a\u000b\u0017/\u001b\u0005i\u0011BA\u0017\u000e\u0005\u001d\u0019u.\u001c9mKb\u0004\"a\f\u0019\r\u0001\u0011I\u0011\u0007\u0001Q\u0001\u0002\u0003\u0015\rA\r\u0002\u0002\u0003F\u00111G\u000e\t\u0003)QJ!!N\u000b\u0003\u000f9{G\u000f[5oOB\u0011AcN\u0005\u0003qU\u00111!\u00118zQ\u0011\u0001$(\u0010#\u0011\u0005QY\u0014B\u0001\u001f\u0016\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rrt(\u0011!\u000f\u0005Qy\u0014B\u0001!\u0016\u0003\u00151En\\1uc\u0011!#i\u0011\f\u000f\u0005u\u0019\u0015\"\u0001\f2\u000b\r*e\tS$\u000f\u0005Q1\u0015BA$\u0016\u0003\u0019!u.\u001e2mKF\"AEQ\"\u0017!\u0011Q5J\u000b\u0018\u000e\u0003\u0019J!\u0001\u0014\u0014\u0003-IKgnZ!tg>\u001c\u0017.\u0019;jm\u0016\fEnZ3ce\u0006\u00042A\u0013(+\u0013\tyeE\u0001\u0006J]Z|G.\u001e;j_:\fa\u0001J5oSR$C#\u0001*\u0011\u0005Q\u0019\u0016B\u0001+\u0016\u0005\u0011)f.\u001b;\u0002\rM\u001c\u0017\r\\1s+\u00059\u0006c\u0001\u000e(]\u0005)Q.\u001b8vgR\u0019!F\u0017/\t\u000bm\u001b\u0001\u0019\u0001\u0016\u0002\u0003\u0005DQ!X\u0002A\u0002)\n\u0011AY\u0001\u0007]\u0016<\u0017\r^3\u0015\u0005)\u0002\u0007\"B.\u0005\u0001\u0004Q\u0013aA8oKV\t!&\u0001\u0003qYV\u001cHc\u0001\u0016fM\")1L\u0002a\u0001U!)QL\u0002a\u0001U\u0005)A/[7fgR\u0019!&\u001b6\t\u000bm;\u0001\u0019\u0001\u0016\t\u000bu;\u0001\u0019\u0001\u0016\u0002\rQLW.Z:m)\rQSN\u001c\u0005\u00067\"\u0001\rA\f\u0005\u0006_\"\u0001\rAK\u0001\u0002m\u0006!!0\u001a:p\u0003\u001d\tGM[8j]R$\"AK:\t\u000bmS\u0001\u0019\u0001\u0016\u0002\u000f\u0019\u0014x.\\%oiR\u0011!F\u001e\u0005\u0006o.\u0001\r\u0001_\u0001\u0002]B\u0011A#_\u0005\u0003uV\u00111!\u00138u\u0001"
)
public interface ComplexOnCRing extends CommutativeRing, RingAssociativeAlgebra, Involution {
   CommutativeRing scalar();

   // $FF: synthetic method
   static Complex minus$(final ComplexOnCRing $this, final Complex a, final Complex b) {
      return $this.minus(a, b);
   }

   default Complex minus(final Complex a, final Complex b) {
      return a.$minus(b, this.scalar());
   }

   // $FF: synthetic method
   static Complex negate$(final ComplexOnCRing $this, final Complex a) {
      return $this.negate(a);
   }

   default Complex negate(final Complex a) {
      return a.unary_$minus(this.scalar());
   }

   // $FF: synthetic method
   static Complex one$(final ComplexOnCRing $this) {
      return $this.one();
   }

   default Complex one() {
      return Complex$.MODULE$.one(this.scalar());
   }

   // $FF: synthetic method
   static Complex plus$(final ComplexOnCRing $this, final Complex a, final Complex b) {
      return $this.plus(a, b);
   }

   default Complex plus(final Complex a, final Complex b) {
      return a.$plus(b, this.scalar());
   }

   // $FF: synthetic method
   static Complex times$(final ComplexOnCRing $this, final Complex a, final Complex b) {
      return $this.times(a, b);
   }

   default Complex times(final Complex a, final Complex b) {
      return a.$times(b, this.scalar());
   }

   // $FF: synthetic method
   static Complex timesl$(final ComplexOnCRing $this, final Object a, final Complex v) {
      return $this.timesl(a, v);
   }

   default Complex timesl(final Object a, final Complex v) {
      return (new Complex(a, this.scalar().zero())).$times(v, this.scalar());
   }

   // $FF: synthetic method
   static Complex zero$(final ComplexOnCRing $this) {
      return $this.zero();
   }

   default Complex zero() {
      return Complex$.MODULE$.zero(this.scalar());
   }

   // $FF: synthetic method
   static Complex adjoint$(final ComplexOnCRing $this, final Complex a) {
      return $this.adjoint(a);
   }

   default Complex adjoint(final Complex a) {
      return a.conjugate(this.scalar());
   }

   // $FF: synthetic method
   static Complex fromInt$(final ComplexOnCRing $this, final int n) {
      return $this.fromInt(n);
   }

   default Complex fromInt(final int n) {
      return Complex$.MODULE$.fromInt(n, this.scalar());
   }

   // $FF: synthetic method
   static CommutativeRing scalar$mcD$sp$(final ComplexOnCRing $this) {
      return $this.scalar$mcD$sp();
   }

   default CommutativeRing scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static CommutativeRing scalar$mcF$sp$(final ComplexOnCRing $this) {
      return $this.scalar$mcF$sp();
   }

   default CommutativeRing scalar$mcF$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Complex minus$mcD$sp$(final ComplexOnCRing $this, final Complex a, final Complex b) {
      return $this.minus$mcD$sp(a, b);
   }

   default Complex minus$mcD$sp(final Complex a, final Complex b) {
      return this.minus(a, b);
   }

   // $FF: synthetic method
   static Complex minus$mcF$sp$(final ComplexOnCRing $this, final Complex a, final Complex b) {
      return $this.minus$mcF$sp(a, b);
   }

   default Complex minus$mcF$sp(final Complex a, final Complex b) {
      return this.minus(a, b);
   }

   // $FF: synthetic method
   static Complex negate$mcD$sp$(final ComplexOnCRing $this, final Complex a) {
      return $this.negate$mcD$sp(a);
   }

   default Complex negate$mcD$sp(final Complex a) {
      return this.negate(a);
   }

   // $FF: synthetic method
   static Complex negate$mcF$sp$(final ComplexOnCRing $this, final Complex a) {
      return $this.negate$mcF$sp(a);
   }

   default Complex negate$mcF$sp(final Complex a) {
      return this.negate(a);
   }

   // $FF: synthetic method
   static Complex one$mcD$sp$(final ComplexOnCRing $this) {
      return $this.one$mcD$sp();
   }

   default Complex one$mcD$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Complex one$mcF$sp$(final ComplexOnCRing $this) {
      return $this.one$mcF$sp();
   }

   default Complex one$mcF$sp() {
      return this.one();
   }

   // $FF: synthetic method
   static Complex plus$mcD$sp$(final ComplexOnCRing $this, final Complex a, final Complex b) {
      return $this.plus$mcD$sp(a, b);
   }

   default Complex plus$mcD$sp(final Complex a, final Complex b) {
      return this.plus(a, b);
   }

   // $FF: synthetic method
   static Complex plus$mcF$sp$(final ComplexOnCRing $this, final Complex a, final Complex b) {
      return $this.plus$mcF$sp(a, b);
   }

   default Complex plus$mcF$sp(final Complex a, final Complex b) {
      return this.plus(a, b);
   }

   // $FF: synthetic method
   static Complex times$mcD$sp$(final ComplexOnCRing $this, final Complex a, final Complex b) {
      return $this.times$mcD$sp(a, b);
   }

   default Complex times$mcD$sp(final Complex a, final Complex b) {
      return this.times(a, b);
   }

   // $FF: synthetic method
   static Complex times$mcF$sp$(final ComplexOnCRing $this, final Complex a, final Complex b) {
      return $this.times$mcF$sp(a, b);
   }

   default Complex times$mcF$sp(final Complex a, final Complex b) {
      return this.times(a, b);
   }

   // $FF: synthetic method
   static Complex timesl$mcD$sp$(final ComplexOnCRing $this, final double a, final Complex v) {
      return $this.timesl$mcD$sp(a, v);
   }

   default Complex timesl$mcD$sp(final double a, final Complex v) {
      return this.timesl(BoxesRunTime.boxToDouble(a), v);
   }

   // $FF: synthetic method
   static Complex timesl$mcF$sp$(final ComplexOnCRing $this, final float a, final Complex v) {
      return $this.timesl$mcF$sp(a, v);
   }

   default Complex timesl$mcF$sp(final float a, final Complex v) {
      return this.timesl(BoxesRunTime.boxToFloat(a), v);
   }

   // $FF: synthetic method
   static Complex zero$mcD$sp$(final ComplexOnCRing $this) {
      return $this.zero$mcD$sp();
   }

   default Complex zero$mcD$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Complex zero$mcF$sp$(final ComplexOnCRing $this) {
      return $this.zero$mcF$sp();
   }

   default Complex zero$mcF$sp() {
      return this.zero();
   }

   // $FF: synthetic method
   static Complex adjoint$mcD$sp$(final ComplexOnCRing $this, final Complex a) {
      return $this.adjoint$mcD$sp(a);
   }

   default Complex adjoint$mcD$sp(final Complex a) {
      return this.adjoint(a);
   }

   // $FF: synthetic method
   static Complex adjoint$mcF$sp$(final ComplexOnCRing $this, final Complex a) {
      return $this.adjoint$mcF$sp(a);
   }

   default Complex adjoint$mcF$sp(final Complex a) {
      return this.adjoint(a);
   }

   // $FF: synthetic method
   static Complex fromInt$mcD$sp$(final ComplexOnCRing $this, final int n) {
      return $this.fromInt$mcD$sp(n);
   }

   default Complex fromInt$mcD$sp(final int n) {
      return this.fromInt(n);
   }

   // $FF: synthetic method
   static Complex fromInt$mcF$sp$(final ComplexOnCRing $this, final int n) {
      return $this.fromInt$mcF$sp(n);
   }

   default Complex fromInt$mcF$sp(final int n) {
      return this.fromInt(n);
   }

   static void $init$(final ComplexOnCRing $this) {
   }
}
