package spire.std;

import algebra.ring.EuclideanRing;
import cats.kernel.Eq;
import scala.Tuple2;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e4qa\u0004\t\u0011\u0002\u0007\u0005Q\u0003C\u00030\u0001\u0011\u0005\u0001\u0007C\u00035\u0001\u0011\u0005S\u0007C\u0003;\u0001\u0011\u00051\bC\u0003>\u0001\u0011\u0005a\bC\u0003@\u0001\u0011\u0005\u0001\tC\u0003D\u0001\u0011\u0005C\tC\u0003H\u0001\u0011\u0005a\bC\u0003I\u0001\u0011\u0005\u0013\nC\u0003P\u0001\u0011\u0005\u0001\u000bC\u0003[\u0001\u0011\u00053\fC\u0003b\u0001\u0011\u0005!\rC\u0003f\u0001\u0011\u0005a\rC\u0003j\u0001\u0011\u0005#\u000eC\u0003t\u0001\u0011\u0005CO\u0001\u000bTQ>\u0014H/S:Fk\u000ed\u0017\u000eZ3b]JKgn\u001a\u0006\u0003#I\t1a\u001d;e\u0015\u0005\u0019\u0012!B:qSJ,7\u0001A\n\u0004\u0001Ya\u0002CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"AB!osJ+g\rE\u0002\u001eS1r!A\b\u0014\u000f\u0005}!cB\u0001\u0011$\u001b\u0005\t#B\u0001\u0012\u0015\u0003\u0019a$o\\8u}%\t1#\u0003\u0002&%\u00059\u0011\r\\4fEJ\f\u0017BA\u0014)\u0003\u001d\u0001\u0018mY6bO\u0016T!!\n\n\n\u0005)Z#!D#vG2LG-Z1o%&twM\u0003\u0002(QA\u0011q#L\u0005\u0003]a\u0011Qa\u00155peR\fa\u0001J5oSR$C#A\u0019\u0011\u0005]\u0011\u0014BA\u001a\u0019\u0005\u0011)f.\u001b;\u0002\u000b5Lg.^:\u0015\u000712\u0004\bC\u00038\u0005\u0001\u0007A&A\u0001b\u0011\u0015I$\u00011\u0001-\u0003\u0005\u0011\u0017A\u00028fO\u0006$X\r\u0006\u0002-y!)qg\u0001a\u0001Y\u0005\u0019qN\\3\u0016\u00031\nA\u0001\u001d7vgR\u0019A&\u0011\"\t\u000b]*\u0001\u0019\u0001\u0017\t\u000be*\u0001\u0019\u0001\u0017\u0002\u000bQLW.Z:\u0015\u00071*e\tC\u00038\r\u0001\u0007A\u0006C\u0003:\r\u0001\u0007A&\u0001\u0003{KJ|\u0017a\u00024s_6Le\u000e\u001e\u000b\u0003Y)CQa\u0013\u0005A\u00021\u000b\u0011A\u001c\t\u0003/5K!A\u0014\r\u0003\u0007%sG/A\tfk\u000ed\u0017\u000eZ3b]\u001a+hn\u0019;j_:$\"!U-\u0011\u0005I3fBA*V\u001d\t\u0001C+C\u0001\u001a\u0013\t9\u0003$\u0003\u0002X1\n1!)[4J]RT!a\n\r\t\u000b]J\u0001\u0019\u0001\u0017\u0002\u0011\u0015\fXo\u001c;n_\u0012$2\u0001X0a!\u00119R\f\f\u0017\n\u0005yC\"A\u0002+va2,'\u0007C\u00038\u0015\u0001\u0007A\u0006C\u0003:\u0015\u0001\u0007A&A\u0003fcV|G\u000fF\u0002-G\u0012DQaN\u0006A\u00021BQ!O\u0006A\u00021\nA!Z7pIR\u0019Af\u001a5\t\u000b]b\u0001\u0019\u0001\u0017\t\u000beb\u0001\u0019\u0001\u0017\u0002\u0007\u001d\u001cG\rF\u0002lcJ$\"\u0001\f7\t\u000b5l\u00019\u00018\u0002\u0005\u00154\bcA\u000fpY%\u0011\u0001o\u000b\u0002\u0003\u000bFDQaN\u0007A\u00021BQ!O\u0007A\u00021\n1\u0001\\2n)\r)x\u000f\u001f\u000b\u0003YYDQ!\u001c\bA\u00049DQa\u000e\bA\u00021BQ!\u000f\bA\u00021\u0002"
)
public interface ShortIsEuclideanRing extends EuclideanRing {
   // $FF: synthetic method
   static short minus$(final ShortIsEuclideanRing $this, final short a, final short b) {
      return $this.minus(a, b);
   }

   default short minus(final short a, final short b) {
      return (short)(a - b);
   }

   // $FF: synthetic method
   static short negate$(final ShortIsEuclideanRing $this, final short a) {
      return $this.negate(a);
   }

   default short negate(final short a) {
      return (short)(-a);
   }

   // $FF: synthetic method
   static short one$(final ShortIsEuclideanRing $this) {
      return $this.one();
   }

   default short one() {
      return (short)1;
   }

   // $FF: synthetic method
   static short plus$(final ShortIsEuclideanRing $this, final short a, final short b) {
      return $this.plus(a, b);
   }

   default short plus(final short a, final short b) {
      return (short)(a + b);
   }

   // $FF: synthetic method
   static short times$(final ShortIsEuclideanRing $this, final short a, final short b) {
      return $this.times(a, b);
   }

   default short times(final short a, final short b) {
      return (short)(a * b);
   }

   // $FF: synthetic method
   static short zero$(final ShortIsEuclideanRing $this) {
      return $this.zero();
   }

   default short zero() {
      return (short)0;
   }

   // $FF: synthetic method
   static short fromInt$(final ShortIsEuclideanRing $this, final int n) {
      return $this.fromInt(n);
   }

   default short fromInt(final int n) {
      return (short)n;
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$(final ShortIsEuclideanRing $this, final short a) {
      return $this.euclideanFunction(a);
   }

   default BigInt euclideanFunction(final short a) {
      return .MODULE$.BigInt().apply(a).abs();
   }

   // $FF: synthetic method
   static Tuple2 equotmod$(final ShortIsEuclideanRing $this, final short a, final short b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final short a, final short b) {
      return spire.math.package$.MODULE$.equotmod(a, b);
   }

   // $FF: synthetic method
   static short equot$(final ShortIsEuclideanRing $this, final short a, final short b) {
      return $this.equot(a, b);
   }

   default short equot(final short a, final short b) {
      return spire.math.package$.MODULE$.equot(a, b);
   }

   // $FF: synthetic method
   static short emod$(final ShortIsEuclideanRing $this, final short a, final short b) {
      return $this.emod(a, b);
   }

   default short emod(final short a, final short b) {
      return spire.math.package$.MODULE$.emod(a, b);
   }

   // $FF: synthetic method
   static short gcd$(final ShortIsEuclideanRing $this, final short a, final short b, final Eq ev) {
      return $this.gcd(a, b, ev);
   }

   default short gcd(final short a, final short b, final Eq ev) {
      return (short)((int)spire.math.package$.MODULE$.gcd((long)a, (long)b));
   }

   // $FF: synthetic method
   static short lcm$(final ShortIsEuclideanRing $this, final short a, final short b, final Eq ev) {
      return $this.lcm(a, b, ev);
   }

   default short lcm(final short a, final short b, final Eq ev) {
      return (short)((int)spire.math.package$.MODULE$.lcm((long)a, (long)b));
   }

   static void $init$(final ShortIsEuclideanRing $this) {
   }
}
