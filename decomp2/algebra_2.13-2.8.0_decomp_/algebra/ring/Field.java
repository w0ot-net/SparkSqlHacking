package algebra.ring;

import cats.kernel.Eq;
import scala.Tuple2;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mca\u0002\b\u0010!\u0003\r\t\u0001\u0006\u0005\u0006\u0017\u0002!\t\u0001\u0014\u0005\u0006!\u0002!\t%\u0015\u0005\u0006C\u0002!\tE\u0019\u0005\u0006O\u0002!\t\u0001\u001b\u0005\u0006a\u0002!\t!\u001d\u0005\u0006i\u0002!\t!\u001e\u0005\u0006q\u0002!\t%\u001f\u0005\u0007\u007f\u0002!\t%!\u0001\b\u000f\u0005-q\u0002#\u0001\u0002\u000e\u00191ab\u0004E\u0001\u0003\u001fAq!a\f\u000b\t\u0003\t\t\u0004C\u0004\u00024)!)!!\u000e\t\u0013\u0005-#\"!A\u0005\n\u00055#!\u0002$jK2$'B\u0001\t\u0012\u0003\u0011\u0011\u0018N\\4\u000b\u0003I\tq!\u00197hK\n\u0014\u0018m\u0001\u0001\u0016\u0005U\u00113#\u0002\u0001\u00179\u0015C\u0005CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"aA!osB\u0019QD\b\u0011\u000e\u0003=I!aH\b\u0003\u001b\u0015+8\r\\5eK\u0006t'+\u001b8h!\t\t#\u0005\u0004\u0001\u0005\u0013\r\u0002\u0001\u0015!A\u0001\u0006\u0004!#!A!\u0012\u0005\u00152\u0002CA\f'\u0013\t9\u0003DA\u0004O_RD\u0017N\\4)\r\tJCFN\u001eA!\t9\"&\u0003\u0002,1\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019SF\f\u00190\u001d\t9b&\u0003\u000201\u0005\u0019\u0011J\u001c;2\t\u0011\nT'\u0007\b\u0003eUj\u0011a\r\u0006\u0003iM\ta\u0001\u0010:p_Rt\u0014\"A\r2\u000b\r:\u0004HO\u001d\u000f\u0005]A\u0014BA\u001d\u0019\u0003\u0011auN\\42\t\u0011\nT'G\u0019\u0006GqjtH\u0010\b\u0003/uJ!A\u0010\r\u0002\u000b\u0019cw.\u0019;2\t\u0011\nT'G\u0019\u0006G\u0005\u0013Ei\u0011\b\u0003/\tK!a\u0011\r\u0002\r\u0011{WO\u00197fc\u0011!\u0013'N\r\u0011\u0007u1\u0005%\u0003\u0002H\u001f\taA)\u001b<jg&|gNU5oOB\u0019Q$\u0013\u0011\n\u0005){!\u0001F\"p[6,H/\u0019;jm\u0016\u001cV-\\5gS\u0016dG-\u0001\u0004%S:LG\u000f\n\u000b\u0002\u001bB\u0011qCT\u0005\u0003\u001fb\u0011A!\u00168ji\u0006\u0019qm\u00193\u0015\u0007Ikv\f\u0006\u0002!'\")AK\u0001a\u0002+\u0006\u0019Q-]!\u0011\u0007YS\u0006E\u0004\u0002X16\t\u0011#\u0003\u0002Z#\u00059\u0001/Y2lC\u001e,\u0017BA.]\u0005\t)\u0015O\u0003\u0002Z#!)aL\u0001a\u0001A\u0005\t\u0011\rC\u0003a\u0005\u0001\u0007\u0001%A\u0001c\u0003\ra7-\u001c\u000b\u0004G\u00164GC\u0001\u0011e\u0011\u0015!6\u0001q\u0001V\u0011\u0015q6\u00011\u0001!\u0011\u0015\u00017\u00011\u0001!\u0003E)Wo\u00197jI\u0016\fgNR;oGRLwN\u001c\u000b\u0003S>\u0004\"A\u001b7\u000f\u0005EZ\u0017BA-\u0019\u0013\tigN\u0001\u0004CS\u001eLe\u000e\u001e\u0006\u00033bAQA\u0018\u0003A\u0002\u0001\nQ!Z9v_R$2\u0001\t:t\u0011\u0015qV\u00011\u0001!\u0011\u0015\u0001W\u00011\u0001!\u0003\u0011)Wn\u001c3\u0015\u0007\u00012x\u000fC\u0003_\r\u0001\u0007\u0001\u0005C\u0003a\r\u0001\u0007\u0001%\u0001\u0005fcV|G/\\8e)\rQXP \t\u0005/m\u0004\u0003%\u0003\u0002}1\t1A+\u001e9mKJBQAX\u0004A\u0002\u0001BQ\u0001Y\u0004A\u0002\u0001\n!B\u001a:p[\u0012{WO\u00197f)\r\u0001\u00131\u0001\u0005\u0007=\"\u0001\r!!\u0002\u0011\u0007]\t9!C\u0002\u0002\na\u0011a\u0001R8vE2,\u0017!\u0002$jK2$\u0007CA\u000f\u000b'\u001dQ\u0011\u0011CA\f\u0003?\u00012aFA\n\u0013\r\t)\u0002\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u000bu\tI\"!\b\n\u0007\u0005mqB\u0001\bGS\u0016dGMR;oGRLwN\\:\u0011\u0005u\u0001\u0001\u0003BA\u0011\u0003Wi!!a\t\u000b\t\u0005\u0015\u0012qE\u0001\u0003S>T!!!\u000b\u0002\t)\fg/Y\u0005\u0005\u0003[\t\u0019C\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003\u001b\tQ!\u00199qYf,B!a\u000e\u0002>Q!\u0011\u0011HA !\u0011i\u0002!a\u000f\u0011\u0007\u0005\ni\u0004B\u0003$\u0019\t\u0007A\u0005C\u0004\u0002B1\u0001\u001d!!\u000f\u0002\u0005\u00154\bf\u0001\u0007\u0002FA\u0019q#a\u0012\n\u0007\u0005%\u0003D\u0001\u0004j]2Lg.Z\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u001f\u0002B!!\u0015\u0002X5\u0011\u00111\u000b\u0006\u0005\u0003+\n9#\u0001\u0003mC:<\u0017\u0002BA-\u0003'\u0012aa\u00142kK\u000e$\b"
)
public interface Field extends EuclideanRing, DivisionRing, CommutativeSemifield {
   static Field apply(final Field ev) {
      return Field$.MODULE$.apply(ev);
   }

   static Object defaultFromDouble(final double a, final Ring ringA, final MultiplicativeGroup mgA) {
      return Field$.MODULE$.defaultFromDouble(a, ringA, mgA);
   }

   static Object defaultFromBigInt(final BigInt n, final Ring ev) {
      return Field$.MODULE$.defaultFromBigInt(n, ev);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return Field$.MODULE$.isMultiplicativeCommutative(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return Field$.MODULE$.isAdditiveCommutative(ev);
   }

   // $FF: synthetic method
   static Object gcd$(final Field $this, final Object a, final Object b, final Eq eqA) {
      return $this.gcd(a, b, eqA);
   }

   default Object gcd(final Object a, final Object b, final Eq eqA) {
      return this.isZero(a, eqA) && this.isZero(b, eqA) ? this.zero() : this.one();
   }

   // $FF: synthetic method
   static Object lcm$(final Field $this, final Object a, final Object b, final Eq eqA) {
      return $this.lcm(a, b, eqA);
   }

   default Object lcm(final Object a, final Object b, final Eq eqA) {
      return this.times(a, b);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$(final Field $this, final Object a) {
      return $this.euclideanFunction(a);
   }

   default BigInt euclideanFunction(final Object a) {
      return .MODULE$.BigInt().apply(0);
   }

   // $FF: synthetic method
   static Object equot$(final Field $this, final Object a, final Object b) {
      return $this.equot(a, b);
   }

   default Object equot(final Object a, final Object b) {
      return this.div(a, b);
   }

   // $FF: synthetic method
   static Object emod$(final Field $this, final Object a, final Object b) {
      return $this.emod(a, b);
   }

   default Object emod(final Object a, final Object b) {
      return this.zero();
   }

   // $FF: synthetic method
   static Tuple2 equotmod$(final Field $this, final Object a, final Object b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final Object a, final Object b) {
      return new Tuple2(this.div(a, b), this.zero());
   }

   // $FF: synthetic method
   static Object fromDouble$(final Field $this, final double a) {
      return $this.fromDouble(a);
   }

   default Object fromDouble(final double a) {
      return DivisionRing$.MODULE$.defaultFromDouble(a, this, this);
   }

   // $FF: synthetic method
   static double gcd$mcD$sp$(final Field $this, final double a, final double b, final Eq eqA) {
      return $this.gcd$mcD$sp(a, b, eqA);
   }

   default double gcd$mcD$sp(final double a, final double b, final Eq eqA) {
      return BoxesRunTime.unboxToDouble(this.gcd(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b), eqA));
   }

   // $FF: synthetic method
   static float gcd$mcF$sp$(final Field $this, final float a, final float b, final Eq eqA) {
      return $this.gcd$mcF$sp(a, b, eqA);
   }

   default float gcd$mcF$sp(final float a, final float b, final Eq eqA) {
      return BoxesRunTime.unboxToFloat(this.gcd(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b), eqA));
   }

   // $FF: synthetic method
   static int gcd$mcI$sp$(final Field $this, final int a, final int b, final Eq eqA) {
      return $this.gcd$mcI$sp(a, b, eqA);
   }

   default int gcd$mcI$sp(final int a, final int b, final Eq eqA) {
      return BoxesRunTime.unboxToInt(this.gcd(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b), eqA));
   }

   // $FF: synthetic method
   static long gcd$mcJ$sp$(final Field $this, final long a, final long b, final Eq eqA) {
      return $this.gcd$mcJ$sp(a, b, eqA);
   }

   default long gcd$mcJ$sp(final long a, final long b, final Eq eqA) {
      return BoxesRunTime.unboxToLong(this.gcd(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b), eqA));
   }

   // $FF: synthetic method
   static double lcm$mcD$sp$(final Field $this, final double a, final double b, final Eq eqA) {
      return $this.lcm$mcD$sp(a, b, eqA);
   }

   default double lcm$mcD$sp(final double a, final double b, final Eq eqA) {
      return BoxesRunTime.unboxToDouble(this.lcm(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b), eqA));
   }

   // $FF: synthetic method
   static float lcm$mcF$sp$(final Field $this, final float a, final float b, final Eq eqA) {
      return $this.lcm$mcF$sp(a, b, eqA);
   }

   default float lcm$mcF$sp(final float a, final float b, final Eq eqA) {
      return BoxesRunTime.unboxToFloat(this.lcm(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b), eqA));
   }

   // $FF: synthetic method
   static int lcm$mcI$sp$(final Field $this, final int a, final int b, final Eq eqA) {
      return $this.lcm$mcI$sp(a, b, eqA);
   }

   default int lcm$mcI$sp(final int a, final int b, final Eq eqA) {
      return BoxesRunTime.unboxToInt(this.lcm(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b), eqA));
   }

   // $FF: synthetic method
   static long lcm$mcJ$sp$(final Field $this, final long a, final long b, final Eq eqA) {
      return $this.lcm$mcJ$sp(a, b, eqA);
   }

   default long lcm$mcJ$sp(final long a, final long b, final Eq eqA) {
      return BoxesRunTime.unboxToLong(this.lcm(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b), eqA));
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcD$sp$(final Field $this, final double a) {
      return $this.euclideanFunction$mcD$sp(a);
   }

   default BigInt euclideanFunction$mcD$sp(final double a) {
      return this.euclideanFunction(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcF$sp$(final Field $this, final float a) {
      return $this.euclideanFunction$mcF$sp(a);
   }

   default BigInt euclideanFunction$mcF$sp(final float a) {
      return this.euclideanFunction(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcI$sp$(final Field $this, final int a) {
      return $this.euclideanFunction$mcI$sp(a);
   }

   default BigInt euclideanFunction$mcI$sp(final int a) {
      return this.euclideanFunction(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcJ$sp$(final Field $this, final long a) {
      return $this.euclideanFunction$mcJ$sp(a);
   }

   default BigInt euclideanFunction$mcJ$sp(final long a) {
      return this.euclideanFunction(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static double equot$mcD$sp$(final Field $this, final double a, final double b) {
      return $this.equot$mcD$sp(a, b);
   }

   default double equot$mcD$sp(final double a, final double b) {
      return BoxesRunTime.unboxToDouble(this.equot(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b)));
   }

   // $FF: synthetic method
   static float equot$mcF$sp$(final Field $this, final float a, final float b) {
      return $this.equot$mcF$sp(a, b);
   }

   default float equot$mcF$sp(final float a, final float b) {
      return BoxesRunTime.unboxToFloat(this.equot(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b)));
   }

   // $FF: synthetic method
   static int equot$mcI$sp$(final Field $this, final int a, final int b) {
      return $this.equot$mcI$sp(a, b);
   }

   default int equot$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.equot(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   // $FF: synthetic method
   static long equot$mcJ$sp$(final Field $this, final long a, final long b) {
      return $this.equot$mcJ$sp(a, b);
   }

   default long equot$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.equot(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   // $FF: synthetic method
   static double emod$mcD$sp$(final Field $this, final double a, final double b) {
      return $this.emod$mcD$sp(a, b);
   }

   default double emod$mcD$sp(final double a, final double b) {
      return BoxesRunTime.unboxToDouble(this.emod(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b)));
   }

   // $FF: synthetic method
   static float emod$mcF$sp$(final Field $this, final float a, final float b) {
      return $this.emod$mcF$sp(a, b);
   }

   default float emod$mcF$sp(final float a, final float b) {
      return BoxesRunTime.unboxToFloat(this.emod(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b)));
   }

   // $FF: synthetic method
   static int emod$mcI$sp$(final Field $this, final int a, final int b) {
      return $this.emod$mcI$sp(a, b);
   }

   default int emod$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.emod(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   // $FF: synthetic method
   static long emod$mcJ$sp$(final Field $this, final long a, final long b) {
      return $this.emod$mcJ$sp(a, b);
   }

   default long emod$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.emod(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcD$sp$(final Field $this, final double a, final double b) {
      return $this.equotmod$mcD$sp(a, b);
   }

   default Tuple2 equotmod$mcD$sp(final double a, final double b) {
      return this.equotmod(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b));
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcF$sp$(final Field $this, final float a, final float b) {
      return $this.equotmod$mcF$sp(a, b);
   }

   default Tuple2 equotmod$mcF$sp(final float a, final float b) {
      return this.equotmod(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b));
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcI$sp$(final Field $this, final int a, final int b) {
      return $this.equotmod$mcI$sp(a, b);
   }

   default Tuple2 equotmod$mcI$sp(final int a, final int b) {
      return this.equotmod(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b));
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcJ$sp$(final Field $this, final long a, final long b) {
      return $this.equotmod$mcJ$sp(a, b);
   }

   default Tuple2 equotmod$mcJ$sp(final long a, final long b) {
      return this.equotmod(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b));
   }

   // $FF: synthetic method
   static double fromDouble$mcD$sp$(final Field $this, final double a) {
      return $this.fromDouble$mcD$sp(a);
   }

   default double fromDouble$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(this.fromDouble(a));
   }

   // $FF: synthetic method
   static float fromDouble$mcF$sp$(final Field $this, final double a) {
      return $this.fromDouble$mcF$sp(a);
   }

   default float fromDouble$mcF$sp(final double a) {
      return BoxesRunTime.unboxToFloat(this.fromDouble(a));
   }

   // $FF: synthetic method
   static int fromDouble$mcI$sp$(final Field $this, final double a) {
      return $this.fromDouble$mcI$sp(a);
   }

   default int fromDouble$mcI$sp(final double a) {
      return BoxesRunTime.unboxToInt(this.fromDouble(a));
   }

   // $FF: synthetic method
   static long fromDouble$mcJ$sp$(final Field $this, final double a) {
      return $this.fromDouble$mcJ$sp(a);
   }

   default long fromDouble$mcJ$sp(final double a) {
      return BoxesRunTime.unboxToLong(this.fromDouble(a));
   }

   static void $init$(final Field $this) {
   }
}
