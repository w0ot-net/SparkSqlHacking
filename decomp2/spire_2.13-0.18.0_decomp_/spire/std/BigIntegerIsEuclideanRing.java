package spire.std;

import algebra.ring.EuclideanRing;
import cats.kernel.Eq;
import java.math.BigInteger;
import scala.Tuple2;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001daa\u0002\t\u0012!\u0003\r\tA\u0006\u0005\u0006k\u0001!\tA\u000e\u0005\u0006u\u0001!\te\u000f\u0005\u0006\u0001\u0002!\t!\u0011\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u0013\u0002!\tE\u0013\u0005\u0006!\u0002!\t%\u0015\u0005\u0006)\u0002!\t\u0001\u0012\u0005\u0006+\u0002!\tE\u0016\u0005\u00063\u0002!\tA\u0017\u0005\u0006I\u0002!\t%\u001a\u0005\u0006W\u0002!\t\u0001\u001c\u0005\u0006_\u0002!\t\u0001\u001d\u0005\u0006g\u0002!\t\u0005\u001e\u0005\u0006{\u0002!\tE \u0002\u001a\u0005&<\u0017J\u001c;fO\u0016\u0014\u0018j]#vG2LG-Z1o%&twM\u0003\u0002\u0013'\u0005\u00191\u000f\u001e3\u000b\u0003Q\tQa\u001d9je\u0016\u001c\u0001aE\u0002\u0001/u\u0001\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0007c\u0001\u0010+[9\u0011qd\n\b\u0003A\u0015r!!\t\u0013\u000e\u0003\tR!aI\u000b\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0012B\u0001\u0014\u0014\u0003\u001d\tGnZ3ce\u0006L!\u0001K\u0015\u0002\u000fA\f7m[1hK*\u0011aeE\u0005\u0003W1\u0012Q\"R;dY&$W-\u00198SS:<'B\u0001\u0015*!\tq3'D\u00010\u0015\t\u0001\u0014'\u0001\u0003nCRD'\"\u0001\u001a\u0002\t)\fg/Y\u0005\u0003i=\u0012!BQ5h\u0013:$XmZ3s\u0003\u0019!\u0013N\\5uIQ\tq\u0007\u0005\u0002\u0019q%\u0011\u0011(\u0007\u0002\u0005+:LG/A\u0003nS:,8\u000fF\u0002.yyBQ!\u0010\u0002A\u00025\n\u0011!\u0019\u0005\u0006\u007f\t\u0001\r!L\u0001\u0002E\u00061a.Z4bi\u0016$\"!\f\"\t\u000bu\u001a\u0001\u0019A\u0017\u0002\u0007=tW-F\u0001.\u0003\u0011\u0001H.^:\u0015\u00075:\u0005\nC\u0003>\u000b\u0001\u0007Q\u0006C\u0003@\u000b\u0001\u0007Q&A\u0002q_^$2!L&M\u0011\u0015id\u00011\u0001.\u0011\u0015yd\u00011\u0001N!\tAb*\u0003\u0002P3\t\u0019\u0011J\u001c;\u0002\u000bQLW.Z:\u0015\u00075\u00126\u000bC\u0003>\u000f\u0001\u0007Q\u0006C\u0003@\u000f\u0001\u0007Q&\u0001\u0003{KJ|\u0017a\u00024s_6Le\u000e\u001e\u000b\u0003[]CQ\u0001W\u0005A\u00025\u000b\u0011A\\\u0001\u0012KV\u001cG.\u001b3fC:4UO\\2uS>tGCA.d!\ta\u0006M\u0004\u0002^?:\u0011\u0011EX\u0005\u00025%\u0011\u0001&G\u0005\u0003C\n\u0014aAQ5h\u0013:$(B\u0001\u0015\u001a\u0011\u0015i$\u00021\u0001.\u0003!)\u0017/^8u[>$Gc\u00014jUB!\u0001dZ\u0017.\u0013\tA\u0017D\u0001\u0004UkBdWM\r\u0005\u0006{-\u0001\r!\f\u0005\u0006\u007f-\u0001\r!L\u0001\u0006KF,x\u000e\u001e\u000b\u0004[5t\u0007\"B\u001f\r\u0001\u0004i\u0003\"B \r\u0001\u0004i\u0013\u0001B3n_\u0012$2!L9s\u0011\u0015iT\u00021\u0001.\u0011\u0015yT\u00021\u0001.\u0003\r97\r\u001a\u000b\u0004kndHCA\u0017w\u0011\u00159h\u0002q\u0001y\u0003\t)g\u000fE\u0002\u001fs6J!A\u001f\u0017\u0003\u0005\u0015\u000b\b\"B\u001f\u000f\u0001\u0004i\u0003\"B \u000f\u0001\u0004i\u0013a\u00017d[R)q0a\u0001\u0002\u0006Q\u0019Q&!\u0001\t\u000b]|\u00019\u0001=\t\u000buz\u0001\u0019A\u0017\t\u000b}z\u0001\u0019A\u0017"
)
public interface BigIntegerIsEuclideanRing extends EuclideanRing {
   // $FF: synthetic method
   static BigInteger minus$(final BigIntegerIsEuclideanRing $this, final BigInteger a, final BigInteger b) {
      return $this.minus(a, b);
   }

   default BigInteger minus(final BigInteger a, final BigInteger b) {
      return a.subtract(b);
   }

   // $FF: synthetic method
   static BigInteger negate$(final BigIntegerIsEuclideanRing $this, final BigInteger a) {
      return $this.negate(a);
   }

   default BigInteger negate(final BigInteger a) {
      return a.negate();
   }

   // $FF: synthetic method
   static BigInteger one$(final BigIntegerIsEuclideanRing $this) {
      return $this.one();
   }

   default BigInteger one() {
      return BigInteger.ONE;
   }

   // $FF: synthetic method
   static BigInteger plus$(final BigIntegerIsEuclideanRing $this, final BigInteger a, final BigInteger b) {
      return $this.plus(a, b);
   }

   default BigInteger plus(final BigInteger a, final BigInteger b) {
      return a.add(b);
   }

   // $FF: synthetic method
   static BigInteger pow$(final BigIntegerIsEuclideanRing $this, final BigInteger a, final int b) {
      return $this.pow(a, b);
   }

   default BigInteger pow(final BigInteger a, final int b) {
      return a.pow(b);
   }

   // $FF: synthetic method
   static BigInteger times$(final BigIntegerIsEuclideanRing $this, final BigInteger a, final BigInteger b) {
      return $this.times(a, b);
   }

   default BigInteger times(final BigInteger a, final BigInteger b) {
      return a.multiply(b);
   }

   // $FF: synthetic method
   static BigInteger zero$(final BigIntegerIsEuclideanRing $this) {
      return $this.zero();
   }

   default BigInteger zero() {
      return BigInteger.ZERO;
   }

   // $FF: synthetic method
   static BigInteger fromInt$(final BigIntegerIsEuclideanRing $this, final int n) {
      return $this.fromInt(n);
   }

   default BigInteger fromInt(final int n) {
      return BigInteger.valueOf((long)n);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$(final BigIntegerIsEuclideanRing $this, final BigInteger a) {
      return $this.euclideanFunction(a);
   }

   default BigInt euclideanFunction(final BigInteger a) {
      return .MODULE$.BigInt().apply(a).abs();
   }

   // $FF: synthetic method
   static Tuple2 equotmod$(final BigIntegerIsEuclideanRing $this, final BigInteger a, final BigInteger b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final BigInteger a, final BigInteger b) {
      return spire.math.package$.MODULE$.equotmod(a, b);
   }

   // $FF: synthetic method
   static BigInteger equot$(final BigIntegerIsEuclideanRing $this, final BigInteger a, final BigInteger b) {
      return $this.equot(a, b);
   }

   default BigInteger equot(final BigInteger a, final BigInteger b) {
      return spire.math.package$.MODULE$.equot(a, b);
   }

   // $FF: synthetic method
   static BigInteger emod$(final BigIntegerIsEuclideanRing $this, final BigInteger a, final BigInteger b) {
      return $this.emod(a, b);
   }

   default BigInteger emod(final BigInteger a, final BigInteger b) {
      return spire.math.package$.MODULE$.emod(a, b);
   }

   // $FF: synthetic method
   static BigInteger gcd$(final BigIntegerIsEuclideanRing $this, final BigInteger a, final BigInteger b, final Eq ev) {
      return $this.gcd(a, b, ev);
   }

   default BigInteger gcd(final BigInteger a, final BigInteger b, final Eq ev) {
      return a.gcd(b);
   }

   // $FF: synthetic method
   static BigInteger lcm$(final BigIntegerIsEuclideanRing $this, final BigInteger a, final BigInteger b, final Eq ev) {
      return $this.lcm(a, b, ev);
   }

   default BigInteger lcm(final BigInteger a, final BigInteger b, final Eq ev) {
      return a.signum() != 0 && b.signum() != 0 ? a.divide(a.gcd(b)).multiply(b) : this.zero();
   }

   static void $init$(final BigIntegerIsEuclideanRing $this) {
   }
}
