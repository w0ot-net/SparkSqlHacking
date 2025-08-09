package algebra.ring;

import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\raaB\u0005\u000b!\u0003\r\ta\u0004\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0011\u0002!\t!\u0013\u0005\u0006\u001f\u0002!\t\u0001U\u0004\u00063*A\tA\u0017\u0004\u0006\u0013)A\ta\u0017\u0005\u0006W\u0016!\t\u0001\u001c\u0005\u0006[\u0016!)A\u001c\u0005\bs\u0016\t\t\u0011\"\u0003{\u0005\u0011\u0011\u0016N\\4\u000b\u0005-a\u0011\u0001\u0002:j]\u001eT\u0011!D\u0001\bC2<WM\u0019:b\u0007\u0001)\"\u0001E\u000f\u0014\t\u0001\tr\u0003\u0011\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0004\u0003:L\bc\u0001\r\u001a75\t!\"\u0003\u0002\u001b\u0015\t\u0019!+[4\u0011\u0005qiB\u0002\u0001\u0003\n=\u0001\u0001\u000b\u0011!AC\u0002}\u0011\u0011!Q\t\u0003AE\u0001\"AE\u0011\n\u0005\t\u001a\"a\u0002(pi\"Lgn\u001a\u0015\u0007;\u0011:\u0013GN\u001e\u0011\u0005I)\u0013B\u0001\u0014\u0014\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rB\u0013f\u000b\u0016\u000f\u0005II\u0013B\u0001\u0016\u0014\u0003\rIe\u000e^\u0019\u0005I1\u0002DC\u0004\u0002.a5\taF\u0003\u00020\u001d\u00051AH]8pizJ\u0011\u0001F\u0019\u0006GI\u001aT\u0007\u000e\b\u0003%MJ!\u0001N\n\u0002\t1{gnZ\u0019\u0005I1\u0002D#M\u0003$oaR\u0014H\u0004\u0002\u0013q%\u0011\u0011hE\u0001\u0006\r2|\u0017\r^\u0019\u0005I1\u0002D#M\u0003$yuzdH\u0004\u0002\u0013{%\u0011ahE\u0001\u0007\t>,(\r\\32\t\u0011b\u0003\u0007\u0006\t\u00041\u0005[\u0012B\u0001\"\u000b\u0005\r\u0011fnZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0015\u0003\"A\u0005$\n\u0005\u001d\u001b\"\u0001B+oSR\fqA\u001a:p[&sG\u000f\u0006\u0002\u001c\u0015\")1J\u0001a\u0001\u0019\u0006\ta\u000e\u0005\u0002\u0013\u001b&\u0011aj\u0005\u0002\u0004\u0013:$\u0018A\u00034s_6\u0014\u0015nZ%oiR\u00111$\u0015\u0005\u0006\u0017\u000e\u0001\rA\u0015\t\u0003'Zs!\u0001\f+\n\u0005U\u001b\u0012a\u00029bG.\fw-Z\u0005\u0003/b\u0013aAQ5h\u0013:$(BA+\u0014\u0003\u0011\u0011\u0016N\\4\u0011\u0005a)1\u0003B\u0003]?\u000e\u0004\"AE/\n\u0005y\u001b\"AB!osJ+g\rE\u0002\u0019A\nL!!\u0019\u0006\u0003\u001bIKgn\u001a$v]\u000e$\u0018n\u001c8t!\tA\u0002\u0001\u0005\u0002eS6\tQM\u0003\u0002gO\u0006\u0011\u0011n\u001c\u0006\u0002Q\u0006!!.\u0019<b\u0013\tQWM\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u00025\u0006)\u0011\r\u001d9msV\u0011qN\u001d\u000b\u0003aN\u00042\u0001\u0007\u0001r!\ta\"\u000fB\u0003\u001f\u000f\t\u0007q\u0004C\u0003u\u000f\u0001\u000f\u0001/\u0001\u0002fm\"\u0012qA\u001e\t\u0003%]L!\u0001_\n\u0003\r%tG.\u001b8f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005Y\bC\u0001?\u0000\u001b\u0005i(B\u0001@h\u0003\u0011a\u0017M\\4\n\u0007\u0005\u0005QP\u0001\u0004PE*,7\r\u001e"
)
public interface Ring extends Rig, Rng {
   static Ring apply(final Ring ev) {
      return Ring$.MODULE$.apply(ev);
   }

   static Object defaultFromDouble(final double a, final Ring ringA, final MultiplicativeGroup mgA) {
      return Ring$.MODULE$.defaultFromDouble(a, ringA, mgA);
   }

   static Object defaultFromBigInt(final BigInt n, final Ring ev) {
      return Ring$.MODULE$.defaultFromBigInt(n, ev);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return Ring$.MODULE$.isMultiplicativeCommutative(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return Ring$.MODULE$.isAdditiveCommutative(ev);
   }

   // $FF: synthetic method
   static Object fromInt$(final Ring $this, final int n) {
      return $this.fromInt(n);
   }

   default Object fromInt(final int n) {
      return this.sumN(this.one(), n);
   }

   // $FF: synthetic method
   static Object fromBigInt$(final Ring $this, final BigInt n) {
      return $this.fromBigInt(n);
   }

   default Object fromBigInt(final BigInt n) {
      return Ring$.MODULE$.defaultFromBigInt(n, this);
   }

   // $FF: synthetic method
   static double fromInt$mcD$sp$(final Ring $this, final int n) {
      return $this.fromInt$mcD$sp(n);
   }

   default double fromInt$mcD$sp(final int n) {
      return BoxesRunTime.unboxToDouble(this.fromInt(n));
   }

   // $FF: synthetic method
   static float fromInt$mcF$sp$(final Ring $this, final int n) {
      return $this.fromInt$mcF$sp(n);
   }

   default float fromInt$mcF$sp(final int n) {
      return BoxesRunTime.unboxToFloat(this.fromInt(n));
   }

   // $FF: synthetic method
   static int fromInt$mcI$sp$(final Ring $this, final int n) {
      return $this.fromInt$mcI$sp(n);
   }

   default int fromInt$mcI$sp(final int n) {
      return BoxesRunTime.unboxToInt(this.fromInt(n));
   }

   // $FF: synthetic method
   static long fromInt$mcJ$sp$(final Ring $this, final int n) {
      return $this.fromInt$mcJ$sp(n);
   }

   default long fromInt$mcJ$sp(final int n) {
      return BoxesRunTime.unboxToLong(this.fromInt(n));
   }

   // $FF: synthetic method
   static double fromBigInt$mcD$sp$(final Ring $this, final BigInt n) {
      return $this.fromBigInt$mcD$sp(n);
   }

   default double fromBigInt$mcD$sp(final BigInt n) {
      return BoxesRunTime.unboxToDouble(this.fromBigInt(n));
   }

   // $FF: synthetic method
   static float fromBigInt$mcF$sp$(final Ring $this, final BigInt n) {
      return $this.fromBigInt$mcF$sp(n);
   }

   default float fromBigInt$mcF$sp(final BigInt n) {
      return BoxesRunTime.unboxToFloat(this.fromBigInt(n));
   }

   // $FF: synthetic method
   static int fromBigInt$mcI$sp$(final Ring $this, final BigInt n) {
      return $this.fromBigInt$mcI$sp(n);
   }

   default int fromBigInt$mcI$sp(final BigInt n) {
      return BoxesRunTime.unboxToInt(this.fromBigInt(n));
   }

   // $FF: synthetic method
   static long fromBigInt$mcJ$sp$(final Ring $this, final BigInt n) {
      return $this.fromBigInt$mcJ$sp(n);
   }

   default long fromBigInt$mcJ$sp(final BigInt n) {
      return BoxesRunTime.unboxToLong(this.fromBigInt(n));
   }

   static void $init$(final Ring $this) {
   }
}
