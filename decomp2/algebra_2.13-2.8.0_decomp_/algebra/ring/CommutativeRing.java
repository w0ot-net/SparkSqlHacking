package algebra.ring;

import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4qAB\u0004\u0011\u0002G\u0005AbB\u0003D\u000f!\u0005AIB\u0003\u0007\u000f!\u0005Q\tC\u0003V\u0005\u0011\u0005a\u000bC\u0003X\u0005\u0011\u0015\u0001\fC\u0004d\u0005\u0005\u0005I\u0011\u00023\u0003\u001f\r{W.\\;uCRLg/\u001a*j]\u001eT!\u0001C\u0005\u0002\tILgn\u001a\u0006\u0002\u0015\u00059\u0011\r\\4fEJ\f7\u0001A\u000b\u0003\u001bi\u0019R\u0001\u0001\b\u0015{\u0001\u0003\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u00111!\u00118z!\r)b\u0003G\u0007\u0002\u000f%\u0011qc\u0002\u0002\u0005%&tw\r\u0005\u0002\u001a51\u0001A!C\u000e\u0001A\u0003\u0005\tQ1\u0001\u001d\u0005\u0005\t\u0015CA\u000f\u000f!\tya$\u0003\u0002 !\t9aj\u001c;iS:<\u0007F\u0002\u000e\"I9\u001a\u0004\b\u0005\u0002\u0010E%\u00111\u0005\u0005\u0002\fgB,7-[1mSj,G-M\u0003$K\u0019BsE\u0004\u0002\u0010M%\u0011q\u0005E\u0001\u0004\u0013:$\u0018\u0007\u0002\u0013*[Eq!AK\u0017\u000e\u0003-R!\u0001L\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0012'B\u00120aI\ndBA\b1\u0013\t\t\u0004#\u0001\u0003M_:<\u0017\u0007\u0002\u0013*[E\tTa\t\u001b6oYr!aD\u001b\n\u0005Y\u0002\u0012!\u0002$m_\u0006$\u0018\u0007\u0002\u0013*[E\tTaI\u001d;ymr!a\u0004\u001e\n\u0005m\u0002\u0012A\u0002#pk\ndW-\r\u0003%S5\n\u0002cA\u000b?1%\u0011qh\u0002\u0002\u000f\u0007>lW.\u001e;bi&4XMU5h!\r)\u0012\tG\u0005\u0003\u0005\u001e\u0011abQ8n[V$\u0018\r^5wKJsw-A\bD_6lW\u000f^1uSZ,'+\u001b8h!\t)\"a\u0005\u0003\u0003\r&k\u0005CA\bH\u0013\tA\u0005C\u0001\u0004B]f\u0014VM\u001a\t\u0004+)c\u0015BA&\b\u00055\u0011\u0016N\\4Gk:\u001cG/[8ogB\u0011Q\u0003\u0001\t\u0003\u001dNk\u0011a\u0014\u0006\u0003!F\u000b!![8\u000b\u0003I\u000bAA[1wC&\u0011Ak\u0014\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0011\u000bQ!\u00199qYf,\"!\u0017/\u0015\u0005ik\u0006cA\u000b\u00017B\u0011\u0011\u0004\u0018\u0003\u00067\u0011\u0011\r\u0001\b\u0005\u0006=\u0012\u0001\u001dAW\u0001\u0002e\"\u0012A\u0001\u0019\t\u0003\u001f\u0005L!A\u0019\t\u0003\r%tG.\u001b8f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005)\u0007C\u00014j\u001b\u00059'B\u00015R\u0003\u0011a\u0017M\\4\n\u0005)<'AB(cU\u0016\u001cG\u000f"
)
public interface CommutativeRing extends Ring, CommutativeRig, CommutativeRng {
   static CommutativeRing apply(final CommutativeRing r) {
      return CommutativeRing$.MODULE$.apply(r);
   }

   static Object defaultFromDouble(final double a, final Ring ringA, final MultiplicativeGroup mgA) {
      return CommutativeRing$.MODULE$.defaultFromDouble(a, ringA, mgA);
   }

   static Object defaultFromBigInt(final BigInt n, final Ring ev) {
      return CommutativeRing$.MODULE$.defaultFromBigInt(n, ev);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return CommutativeRing$.MODULE$.isMultiplicativeCommutative(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return CommutativeRing$.MODULE$.isAdditiveCommutative(ev);
   }
}
