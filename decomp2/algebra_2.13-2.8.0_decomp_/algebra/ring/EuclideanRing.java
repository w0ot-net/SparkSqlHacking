package algebra.ring;

import cats.kernel.Eq;
import scala.Tuple2;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}da\u0002\b\u0010!\u0003\r\t\u0001\u0006\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u0015\u00021\ta\u0013\u0005\u0006+\u00021\tA\u0016\u0005\u00065\u00021\ta\u0017\u0005\u0006=\u0002!\ta\u0018\u0005\u0006K\u0002!\tA\u001a\u0005\u0006g\u0002!\t\u0001^\u0004\u0006s>A\tA\u001f\u0004\u0006\u001d=A\ta\u001f\u0005\b\u0003/IA\u0011AA\r\u0011\u001d\tY\"\u0003C\u0003\u0003;Aq!a\r\n\t\u000b\t)\u0004C\u0005\u0002p%\t\t\u0011\"\u0003\u0002r\tiQ)^2mS\u0012,\u0017M\u001c*j]\u001eT!\u0001E\t\u0002\tILgn\u001a\u0006\u0002%\u00059\u0011\r\\4fEJ\f7\u0001A\u000b\u0003+\t\u001a2\u0001\u0001\f\u001d!\t9\"$D\u0001\u0019\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0019\u0005\r\te.\u001f\t\u0004;y\u0001S\"A\b\n\u0005}y!aB$D\tJKgn\u001a\t\u0003C\tb\u0001\u0001B\u0005$\u0001\u0001\u0006\t\u0011!b\u0001I\t\t\u0011)\u0005\u0002&-A\u0011qCJ\u0005\u0003Oa\u0011qAT8uQ&tw\r\u000b\u0004#S124\b\u0011\t\u0003/)J!a\u000b\r\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G5r\u0003g\f\b\u0003/9J!a\f\r\u0002\u0007%sG/\r\u0003%cUJbB\u0001\u001a6\u001b\u0005\u0019$B\u0001\u001b\u0014\u0003\u0019a$o\\8u}%\t\u0011$M\u0003$oaR\u0014H\u0004\u0002\u0018q%\u0011\u0011\bG\u0001\u0005\u0019>tw-\r\u0003%cUJ\u0012'B\u0012={}rdBA\f>\u0013\tq\u0004$A\u0003GY>\fG/\r\u0003%cUJ\u0012'B\u0012B\u0005\u0012\u001beBA\fC\u0013\t\u0019\u0005$\u0001\u0004E_V\u0014G.Z\u0019\u0005IE*\u0014$\u0001\u0004%S:LG\u000f\n\u000b\u0002\u000fB\u0011q\u0003S\u0005\u0003\u0013b\u0011A!\u00168ji\u0006\tR-^2mS\u0012,\u0017M\u001c$v]\u000e$\u0018n\u001c8\u0015\u00051\u001b\u0006CA'Q\u001d\t\td*\u0003\u0002P1\u00059\u0001/Y2lC\u001e,\u0017BA)S\u0005\u0019\u0011\u0015nZ%oi*\u0011q\n\u0007\u0005\u0006)\n\u0001\r\u0001I\u0001\u0002C\u0006)Q-];piR\u0019\u0001e\u0016-\t\u000bQ\u001b\u0001\u0019\u0001\u0011\t\u000be\u001b\u0001\u0019\u0001\u0011\u0002\u0003\t\fA!Z7pIR\u0019\u0001\u0005X/\t\u000bQ#\u0001\u0019\u0001\u0011\t\u000be#\u0001\u0019\u0001\u0011\u0002\u0011\u0015\fXo\u001c;n_\u0012$2\u0001Y2e!\u00119\u0012\r\t\u0011\n\u0005\tD\"A\u0002+va2,'\u0007C\u0003U\u000b\u0001\u0007\u0001\u0005C\u0003Z\u000b\u0001\u0007\u0001%A\u0002hG\u0012$2aZ9s)\t\u0001\u0003\u000eC\u0003j\r\u0001\u000f!.\u0001\u0002fmB\u00191N\u001c\u0011\u000f\u00051lW\"A\t\n\u0005=\u000b\u0012BA8q\u0005\t)\u0015O\u0003\u0002P#!)AK\u0002a\u0001A!)\u0011L\u0002a\u0001A\u0005\u0019AnY7\u0015\u0007U<\b\u0010\u0006\u0002!m\")\u0011n\u0002a\u0002U\")Ak\u0002a\u0001A!)\u0011l\u0002a\u0001A\u0005iQ)^2mS\u0012,\u0017M\u001c*j]\u001e\u0004\"!H\u0005\u0014\u000b%ax0a\u0002\u0011\u0005]i\u0018B\u0001@\u0019\u0005\u0019\te.\u001f*fMB)Q$!\u0001\u0002\u0006%\u0019\u00111A\b\u0003-\u0015+8\r\\5eK\u0006t'+\u001b8h\rVt7\r^5p]N\u0004\"!\b\u0001\u0011\t\u0005%\u00111C\u0007\u0003\u0003\u0017QA!!\u0004\u0002\u0010\u0005\u0011\u0011n\u001c\u0006\u0003\u0003#\tAA[1wC&!\u0011QCA\u0006\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\t!0A\u0003baBd\u00170\u0006\u0003\u0002 \u0005\u0015B\u0003BA\u0011\u0003O\u0001B!\b\u0001\u0002$A\u0019\u0011%!\n\u0005\u000b\rZ!\u0019\u0001\u0013\t\u000f\u0005%2\u0002q\u0001\u0002\"\u0005\tQ\rK\u0002\f\u0003[\u00012aFA\u0018\u0013\r\t\t\u0004\u0007\u0002\u0007S:d\u0017N\\3\u0002\r\u0015,8\r\\5e+\u0011\t9$!\u0010\u0015\r\u0005e\u0012QLA0)\u0019\tY$!\u0015\u0002XA\u0019\u0011%!\u0010\u0005\u0013\rb\u0001\u0015!A\u0001\u0006\u0004!\u0003fCA\u001fS\u0005\u0005\u0013QIA%\u0003\u001b\ndaI\u0017/\u0003\u0007z\u0013\u0007\u0002\u00132ke\tdaI\u001c9\u0003\u000fJ\u0014\u0007\u0002\u00132ke\tda\t\u001f>\u0003\u0017r\u0014\u0007\u0002\u00132ke\tdaI!C\u0003\u001f\u001a\u0015\u0007\u0002\u00132keA\u0011\"a\u0015\r\u0003\u0003\u0005\u001d!!\u0016\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0003l]\u0006m\u0002\"CA-\u0019\u0005\u0005\t9AA.\u0003))g/\u001b3f]\u000e,GE\r\t\u0005;\u0001\tY\u0004\u0003\u0004U\u0019\u0001\u0007\u00111\b\u0005\u000732\u0001\r!a\u000f)\u00071\t\u0019\u0007\u0005\u0003\u0002f\u0005-TBAA4\u0015\r\tI\u0007G\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA7\u0003O\u0012q\u0001^1jYJ,7-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002tA!\u0011QOA>\u001b\t\t9H\u0003\u0003\u0002z\u0005=\u0011\u0001\u00027b]\u001eLA!! \u0002x\t1qJ\u00196fGR\u0004"
)
public interface EuclideanRing extends GCDRing {
   static Object euclid(final Object a, final Object b, final Eq evidence$1, final EuclideanRing evidence$2) {
      return EuclideanRing$.MODULE$.euclid(a, b, evidence$1, evidence$2);
   }

   static EuclideanRing apply(final EuclideanRing e) {
      return EuclideanRing$.MODULE$.apply(e);
   }

   static Object defaultFromDouble(final double a, final Ring ringA, final MultiplicativeGroup mgA) {
      return EuclideanRing$.MODULE$.defaultFromDouble(a, ringA, mgA);
   }

   static Object defaultFromBigInt(final BigInt n, final Ring ev) {
      return EuclideanRing$.MODULE$.defaultFromBigInt(n, ev);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return EuclideanRing$.MODULE$.isMultiplicativeCommutative(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return EuclideanRing$.MODULE$.isAdditiveCommutative(ev);
   }

   BigInt euclideanFunction(final Object a);

   Object equot(final Object a, final Object b);

   Object emod(final Object a, final Object b);

   // $FF: synthetic method
   static Tuple2 equotmod$(final EuclideanRing $this, final Object a, final Object b) {
      return $this.equotmod(a, b);
   }

   default Tuple2 equotmod(final Object a, final Object b) {
      return new Tuple2(this.equot(a, b), this.emod(a, b));
   }

   // $FF: synthetic method
   static Object gcd$(final EuclideanRing $this, final Object a, final Object b, final Eq ev) {
      return $this.gcd(a, b, ev);
   }

   default Object gcd(final Object a, final Object b, final Eq ev) {
      return EuclideanRing$.MODULE$.euclid(a, b, ev, this);
   }

   // $FF: synthetic method
   static Object lcm$(final EuclideanRing $this, final Object a, final Object b, final Eq ev) {
      return $this.lcm(a, b, ev);
   }

   default Object lcm(final Object a, final Object b, final Eq ev) {
      return !this.isZero(a, ev) && !this.isZero(b, ev) ? this.times(this.equot(a, this.gcd(a, b, ev)), b) : this.zero();
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcD$sp$(final EuclideanRing $this, final double a) {
      return $this.euclideanFunction$mcD$sp(a);
   }

   default BigInt euclideanFunction$mcD$sp(final double a) {
      return this.euclideanFunction(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcF$sp$(final EuclideanRing $this, final float a) {
      return $this.euclideanFunction$mcF$sp(a);
   }

   default BigInt euclideanFunction$mcF$sp(final float a) {
      return this.euclideanFunction(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcI$sp$(final EuclideanRing $this, final int a) {
      return $this.euclideanFunction$mcI$sp(a);
   }

   default BigInt euclideanFunction$mcI$sp(final int a) {
      return this.euclideanFunction(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcJ$sp$(final EuclideanRing $this, final long a) {
      return $this.euclideanFunction$mcJ$sp(a);
   }

   default BigInt euclideanFunction$mcJ$sp(final long a) {
      return this.euclideanFunction(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static double equot$mcD$sp$(final EuclideanRing $this, final double a, final double b) {
      return $this.equot$mcD$sp(a, b);
   }

   default double equot$mcD$sp(final double a, final double b) {
      return BoxesRunTime.unboxToDouble(this.equot(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b)));
   }

   // $FF: synthetic method
   static float equot$mcF$sp$(final EuclideanRing $this, final float a, final float b) {
      return $this.equot$mcF$sp(a, b);
   }

   default float equot$mcF$sp(final float a, final float b) {
      return BoxesRunTime.unboxToFloat(this.equot(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b)));
   }

   // $FF: synthetic method
   static int equot$mcI$sp$(final EuclideanRing $this, final int a, final int b) {
      return $this.equot$mcI$sp(a, b);
   }

   default int equot$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.equot(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   // $FF: synthetic method
   static long equot$mcJ$sp$(final EuclideanRing $this, final long a, final long b) {
      return $this.equot$mcJ$sp(a, b);
   }

   default long equot$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.equot(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   // $FF: synthetic method
   static double emod$mcD$sp$(final EuclideanRing $this, final double a, final double b) {
      return $this.emod$mcD$sp(a, b);
   }

   default double emod$mcD$sp(final double a, final double b) {
      return BoxesRunTime.unboxToDouble(this.emod(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b)));
   }

   // $FF: synthetic method
   static float emod$mcF$sp$(final EuclideanRing $this, final float a, final float b) {
      return $this.emod$mcF$sp(a, b);
   }

   default float emod$mcF$sp(final float a, final float b) {
      return BoxesRunTime.unboxToFloat(this.emod(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b)));
   }

   // $FF: synthetic method
   static int emod$mcI$sp$(final EuclideanRing $this, final int a, final int b) {
      return $this.emod$mcI$sp(a, b);
   }

   default int emod$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.emod(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   // $FF: synthetic method
   static long emod$mcJ$sp$(final EuclideanRing $this, final long a, final long b) {
      return $this.emod$mcJ$sp(a, b);
   }

   default long emod$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.emod(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcD$sp$(final EuclideanRing $this, final double a, final double b) {
      return $this.equotmod$mcD$sp(a, b);
   }

   default Tuple2 equotmod$mcD$sp(final double a, final double b) {
      return this.equotmod(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b));
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcF$sp$(final EuclideanRing $this, final float a, final float b) {
      return $this.equotmod$mcF$sp(a, b);
   }

   default Tuple2 equotmod$mcF$sp(final float a, final float b) {
      return this.equotmod(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b));
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcI$sp$(final EuclideanRing $this, final int a, final int b) {
      return $this.equotmod$mcI$sp(a, b);
   }

   default Tuple2 equotmod$mcI$sp(final int a, final int b) {
      return this.equotmod(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b));
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcJ$sp$(final EuclideanRing $this, final long a, final long b) {
      return $this.equotmod$mcJ$sp(a, b);
   }

   default Tuple2 equotmod$mcJ$sp(final long a, final long b) {
      return this.equotmod(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b));
   }

   // $FF: synthetic method
   static double gcd$mcD$sp$(final EuclideanRing $this, final double a, final double b, final Eq ev) {
      return $this.gcd$mcD$sp(a, b, ev);
   }

   default double gcd$mcD$sp(final double a, final double b, final Eq ev) {
      return BoxesRunTime.unboxToDouble(this.gcd(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b), ev));
   }

   // $FF: synthetic method
   static float gcd$mcF$sp$(final EuclideanRing $this, final float a, final float b, final Eq ev) {
      return $this.gcd$mcF$sp(a, b, ev);
   }

   default float gcd$mcF$sp(final float a, final float b, final Eq ev) {
      return BoxesRunTime.unboxToFloat(this.gcd(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b), ev));
   }

   // $FF: synthetic method
   static int gcd$mcI$sp$(final EuclideanRing $this, final int a, final int b, final Eq ev) {
      return $this.gcd$mcI$sp(a, b, ev);
   }

   default int gcd$mcI$sp(final int a, final int b, final Eq ev) {
      return BoxesRunTime.unboxToInt(this.gcd(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b), ev));
   }

   // $FF: synthetic method
   static long gcd$mcJ$sp$(final EuclideanRing $this, final long a, final long b, final Eq ev) {
      return $this.gcd$mcJ$sp(a, b, ev);
   }

   default long gcd$mcJ$sp(final long a, final long b, final Eq ev) {
      return BoxesRunTime.unboxToLong(this.gcd(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b), ev));
   }

   // $FF: synthetic method
   static double lcm$mcD$sp$(final EuclideanRing $this, final double a, final double b, final Eq ev) {
      return $this.lcm$mcD$sp(a, b, ev);
   }

   default double lcm$mcD$sp(final double a, final double b, final Eq ev) {
      return BoxesRunTime.unboxToDouble(this.lcm(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b), ev));
   }

   // $FF: synthetic method
   static float lcm$mcF$sp$(final EuclideanRing $this, final float a, final float b, final Eq ev) {
      return $this.lcm$mcF$sp(a, b, ev);
   }

   default float lcm$mcF$sp(final float a, final float b, final Eq ev) {
      return BoxesRunTime.unboxToFloat(this.lcm(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b), ev));
   }

   // $FF: synthetic method
   static int lcm$mcI$sp$(final EuclideanRing $this, final int a, final int b, final Eq ev) {
      return $this.lcm$mcI$sp(a, b, ev);
   }

   default int lcm$mcI$sp(final int a, final int b, final Eq ev) {
      return BoxesRunTime.unboxToInt(this.lcm(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b), ev));
   }

   // $FF: synthetic method
   static long lcm$mcJ$sp$(final EuclideanRing $this, final long a, final long b, final Eq ev) {
      return $this.lcm$mcJ$sp(a, b, ev);
   }

   default long lcm$mcJ$sp(final long a, final long b, final Eq ev) {
      return BoxesRunTime.unboxToLong(this.lcm(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b), ev));
   }

   static void $init$(final EuclideanRing $this) {
   }
}
