package spire.std;

import algebra.ring.EuclideanRing;
import cats.kernel.Eq;
import scala.Tuple2;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m4q\u0001E\t\u0011\u0002\u0007\u0005a\u0003C\u00031\u0001\u0011\u0005\u0011\u0007C\u00036\u0001\u0011\u0005c\u0007C\u0003<\u0001\u0011\u0005A\bC\u0003?\u0001\u0011\u0005q\bC\u0003A\u0001\u0011\u0005\u0011\tC\u0003E\u0001\u0011\u0005S\tC\u0003I\u0001\u0011\u0005\u0013\nC\u0003M\u0001\u0011\u0005q\bC\u0003N\u0001\u0011\u0005c\nC\u0003R\u0001\u0011\u0005!\u000bC\u0003]\u0001\u0011\u0005S\fC\u0003d\u0001\u0011\u0005A\rC\u0003h\u0001\u0011\u0005\u0001\u000eC\u0003l\u0001\u0011\u0005C\u000eC\u0003v\u0001\u0011\u0005cO\u0001\nJ]RL5/R;dY&$W-\u00198SS:<'B\u0001\n\u0014\u0003\r\u0019H\u000f\u001a\u0006\u0002)\u0005)1\u000f]5sK\u000e\u00011c\u0001\u0001\u0018;A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\u00042A\b\u0016.\u001d\tyrE\u0004\u0002!K9\u0011\u0011\u0005J\u0007\u0002E)\u00111%F\u0001\u0007yI|w\u000e\u001e \n\u0003QI!AJ\n\u0002\u000f\u0005dw-\u001a2sC&\u0011\u0001&K\u0001\ba\u0006\u001c7.Y4f\u0015\t13#\u0003\u0002,Y\tiQ)^2mS\u0012,\u0017M\u001c*j]\u001eT!\u0001K\u0015\u0011\u0005aq\u0013BA\u0018\u001a\u0005\rIe\u000e^\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003I\u0002\"\u0001G\u001a\n\u0005QJ\"\u0001B+oSR\fQ!\\5okN$2!L\u001c:\u0011\u0015A$\u00011\u0001.\u0003\u0005\t\u0007\"\u0002\u001e\u0003\u0001\u0004i\u0013!\u00012\u0002\r9,w-\u0019;f)\tiS\bC\u00039\u0007\u0001\u0007Q&A\u0002p]\u0016,\u0012!L\u0001\u0005a2,8\u000fF\u0002.\u0005\u000eCQ\u0001O\u0003A\u00025BQAO\u0003A\u00025\n1\u0001]8x)\rici\u0012\u0005\u0006q\u0019\u0001\r!\f\u0005\u0006u\u0019\u0001\r!L\u0001\u0006i&lWm\u001d\u000b\u0004[)[\u0005\"\u0002\u001d\b\u0001\u0004i\u0003\"\u0002\u001e\b\u0001\u0004i\u0013\u0001\u0002>fe>\fqA\u001a:p[&sG\u000f\u0006\u0002.\u001f\")\u0001+\u0003a\u0001[\u0005\ta.A\tfk\u000ed\u0017\u000eZ3b]\u001a+hn\u0019;j_:$\"aU.\u0011\u0005QCfBA+X\u001d\t\tc+C\u0001\u001b\u0013\tA\u0013$\u0003\u0002Z5\n1!)[4J]RT!\u0001K\r\t\u000baR\u0001\u0019A\u0017\u0002\u0011\u0015\fXo\u001c;n_\u0012$2AX1c!\u0011Ar,L\u0017\n\u0005\u0001L\"A\u0002+va2,'\u0007C\u00039\u0017\u0001\u0007Q\u0006C\u0003;\u0017\u0001\u0007Q&A\u0003fcV|G\u000fF\u0002.K\u001aDQ\u0001\u000f\u0007A\u00025BQA\u000f\u0007A\u00025\nA!Z7pIR\u0019Q&\u001b6\t\u000baj\u0001\u0019A\u0017\t\u000bij\u0001\u0019A\u0017\u0002\u0007\u001d\u001cG\rF\u0002ngR$\"!\f8\t\u000b=t\u00019\u00019\u0002\u0005\u00154\bc\u0001\u0010r[%\u0011!\u000f\f\u0002\u0003\u000bFDQ\u0001\u000f\bA\u00025BQA\u000f\bA\u00025\n1\u0001\\2n)\r9\u0018P\u001f\u000b\u0003[aDQa\\\bA\u0004ADQ\u0001O\bA\u00025BQAO\bA\u00025\u0002"
)
public interface IntIsEuclideanRing extends EuclideanRing.mcI.sp {
   // $FF: synthetic method
   static int minus$(final IntIsEuclideanRing $this, final int a, final int b) {
      return $this.minus(a, b);
   }

   default int minus(final int a, final int b) {
      return this.minus$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int negate$(final IntIsEuclideanRing $this, final int a) {
      return $this.negate(a);
   }

   default int negate(final int a) {
      return this.negate$mcI$sp(a);
   }

   // $FF: synthetic method
   static int one$(final IntIsEuclideanRing $this) {
      return $this.one();
   }

   default int one() {
      return this.one$mcI$sp();
   }

   // $FF: synthetic method
   static int plus$(final IntIsEuclideanRing $this, final int a, final int b) {
      return $this.plus(a, b);
   }

   default int plus(final int a, final int b) {
      return this.plus$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int pow$(final IntIsEuclideanRing $this, final int a, final int b) {
      return $this.pow(a, b);
   }

   default int pow(final int a, final int b) {
      return this.pow$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int times$(final IntIsEuclideanRing $this, final int a, final int b) {
      return $this.times(a, b);
   }

   default int times(final int a, final int b) {
      return this.times$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int zero$(final IntIsEuclideanRing $this) {
      return $this.zero();
   }

   default int zero() {
      return this.zero$mcI$sp();
   }

   // $FF: synthetic method
   static int fromInt$(final IntIsEuclideanRing $this, final int n) {
      return $this.fromInt(n);
   }

   default int fromInt(final int n) {
      return this.fromInt$mcI$sp(n);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$(final IntIsEuclideanRing $this, final int a) {
      return $this.euclideanFunction(a);
   }

   default BigInt euclideanFunction(final int a) {
      return this.euclideanFunction$mcI$sp(a);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$(final IntIsEuclideanRing $this, final int a, final int b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final int a, final int b) {
      return this.equotmod$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int equot$(final IntIsEuclideanRing $this, final int a, final int b) {
      return $this.equot(a, b);
   }

   default int equot(final int a, final int b) {
      return this.equot$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int emod$(final IntIsEuclideanRing $this, final int a, final int b) {
      return $this.emod(a, b);
   }

   default int emod(final int a, final int b) {
      return this.emod$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int gcd$(final IntIsEuclideanRing $this, final int a, final int b, final Eq ev) {
      return $this.gcd(a, b, ev);
   }

   default int gcd(final int a, final int b, final Eq ev) {
      return this.gcd$mcI$sp(a, b, ev);
   }

   // $FF: synthetic method
   static int lcm$(final IntIsEuclideanRing $this, final int a, final int b, final Eq ev) {
      return $this.lcm(a, b, ev);
   }

   default int lcm(final int a, final int b, final Eq ev) {
      return this.lcm$mcI$sp(a, b, ev);
   }

   // $FF: synthetic method
   static int minus$mcI$sp$(final IntIsEuclideanRing $this, final int a, final int b) {
      return $this.minus$mcI$sp(a, b);
   }

   default int minus$mcI$sp(final int a, final int b) {
      return a - b;
   }

   // $FF: synthetic method
   static int negate$mcI$sp$(final IntIsEuclideanRing $this, final int a) {
      return $this.negate$mcI$sp(a);
   }

   default int negate$mcI$sp(final int a) {
      return -a;
   }

   // $FF: synthetic method
   static int one$mcI$sp$(final IntIsEuclideanRing $this) {
      return $this.one$mcI$sp();
   }

   default int one$mcI$sp() {
      return 1;
   }

   // $FF: synthetic method
   static int plus$mcI$sp$(final IntIsEuclideanRing $this, final int a, final int b) {
      return $this.plus$mcI$sp(a, b);
   }

   default int plus$mcI$sp(final int a, final int b) {
      return a + b;
   }

   // $FF: synthetic method
   static int pow$mcI$sp$(final IntIsEuclideanRing $this, final int a, final int b) {
      return $this.pow$mcI$sp(a, b);
   }

   default int pow$mcI$sp(final int a, final int b) {
      return (int)spire.math.package$.MODULE$.pow((long)a, (long)b);
   }

   // $FF: synthetic method
   static int times$mcI$sp$(final IntIsEuclideanRing $this, final int a, final int b) {
      return $this.times$mcI$sp(a, b);
   }

   default int times$mcI$sp(final int a, final int b) {
      return a * b;
   }

   // $FF: synthetic method
   static int zero$mcI$sp$(final IntIsEuclideanRing $this) {
      return $this.zero$mcI$sp();
   }

   default int zero$mcI$sp() {
      return 0;
   }

   // $FF: synthetic method
   static int fromInt$mcI$sp$(final IntIsEuclideanRing $this, final int n) {
      return $this.fromInt$mcI$sp(n);
   }

   default int fromInt$mcI$sp(final int n) {
      return n;
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcI$sp$(final IntIsEuclideanRing $this, final int a) {
      return $this.euclideanFunction$mcI$sp(a);
   }

   default BigInt euclideanFunction$mcI$sp(final int a) {
      return .MODULE$.BigInt().apply(a).abs();
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcI$sp$(final IntIsEuclideanRing $this, final int a, final int b) {
      return $this.equotmod$mcI$sp(a, b);
   }

   default Tuple2 equotmod$mcI$sp(final int a, final int b) {
      return spire.math.package$.MODULE$.equotmod(a, b);
   }

   // $FF: synthetic method
   static int equot$mcI$sp$(final IntIsEuclideanRing $this, final int a, final int b) {
      return $this.equot$mcI$sp(a, b);
   }

   default int equot$mcI$sp(final int a, final int b) {
      return spire.math.package$.MODULE$.equot(a, b);
   }

   // $FF: synthetic method
   static int emod$mcI$sp$(final IntIsEuclideanRing $this, final int a, final int b) {
      return $this.emod$mcI$sp(a, b);
   }

   default int emod$mcI$sp(final int a, final int b) {
      return spire.math.package$.MODULE$.emod(a, b);
   }

   // $FF: synthetic method
   static int gcd$mcI$sp$(final IntIsEuclideanRing $this, final int a, final int b, final Eq ev) {
      return $this.gcd$mcI$sp(a, b, ev);
   }

   default int gcd$mcI$sp(final int a, final int b, final Eq ev) {
      return (int)spire.math.package$.MODULE$.gcd((long)a, (long)b);
   }

   // $FF: synthetic method
   static int lcm$mcI$sp$(final IntIsEuclideanRing $this, final int a, final int b, final Eq ev) {
      return $this.lcm$mcI$sp(a, b, ev);
   }

   default int lcm$mcI$sp(final int a, final int b, final Eq ev) {
      return (int)spire.math.package$.MODULE$.lcm((long)a, (long)b);
   }

   static void $init$(final IntIsEuclideanRing $this) {
   }
}
