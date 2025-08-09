package algebra.ring;

import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3qAB\u0004\u0011\u0002G\u0005AbB\u0003$\u000f!\u0005AEB\u0003\u0007\u000f!\u0005Q\u0005C\u00036\u0005\u0011\u0005a\u0007C\u00038\u0005\u0011\u0015\u0001\bC\u0004D\u0005\u0005\u0005I\u0011\u0002#\u0003\u0011\t{w\u000e\u001c*j]\u001eT!\u0001C\u0005\u0002\tILgn\u001a\u0006\u0002\u0015\u00059\u0011\r\\4fEJ\f7\u0001A\u000b\u0003\u001bi\u0019B\u0001\u0001\b\u0015AA\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t\u0019\u0011I\\=\u0011\u0007U1\u0002$D\u0001\b\u0013\t9rAA\u0004C_>d'K\\4\u0011\u0005eQB\u0002\u0001\u0003\u00067\u0001\u0011\r\u0001\b\u0002\u0002\u0003F\u0011QD\u0004\t\u0003\u001fyI!a\b\t\u0003\u000f9{G\u000f[5oOB\u0019Q#\t\r\n\u0005\t:!aD\"p[6,H/\u0019;jm\u0016\u0014\u0016N\\4\u0002\u0011\t{w\u000e\u001c*j]\u001e\u0004\"!\u0006\u0002\u0014\t\t1\u0013&\f\t\u0003\u001f\u001dJ!\u0001\u000b\t\u0003\r\u0005s\u0017PU3g!\r)\"\u0006L\u0005\u0003W\u001d\u0011QBU5oO\u001a+hn\u0019;j_:\u001c\bCA\u000b\u0001!\tq3'D\u00010\u0015\t\u0001\u0014'\u0001\u0002j_*\t!'\u0001\u0003kCZ\f\u0017B\u0001\u001b0\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\tA%A\u0003baBd\u00170\u0006\u0002:yQ\u0011!(\u0010\t\u0004+\u0001Y\u0004CA\r=\t\u0015YBA1\u0001\u001d\u0011\u0015qD\u0001q\u0001;\u0003\u0005\u0011\bF\u0001\u0003A!\ty\u0011)\u0003\u0002C!\t1\u0011N\u001c7j]\u0016\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012!\u0012\t\u0003\r&k\u0011a\u0012\u0006\u0003\u0011F\nA\u0001\\1oO&\u0011!j\u0012\u0002\u0007\u001f\nTWm\u0019;"
)
public interface BoolRing extends BoolRng, CommutativeRing {
   static BoolRing apply(final BoolRing r) {
      return BoolRing$.MODULE$.apply(r);
   }

   static Object defaultFromDouble(final double a, final Ring ringA, final MultiplicativeGroup mgA) {
      return BoolRing$.MODULE$.defaultFromDouble(a, ringA, mgA);
   }

   static Object defaultFromBigInt(final BigInt n, final Ring ev) {
      return BoolRing$.MODULE$.defaultFromBigInt(n, ev);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return BoolRing$.MODULE$.isMultiplicativeCommutative(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return BoolRing$.MODULE$.isAdditiveCommutative(ev);
   }
}
