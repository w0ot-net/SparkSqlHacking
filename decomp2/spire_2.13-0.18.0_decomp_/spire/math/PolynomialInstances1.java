package spire.math;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Rig;
import algebra.ring.Rng;
import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.Option;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000514q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0016\u0001\u0011\u0005a\u0003C\u0003\u001b\u0001\u0011\r1\u0004C\u0003W\u0001\u0011\rqK\u0001\u000bQ_2Lhn\\7jC2Len\u001d;b]\u000e,7/\r\u0006\u0003\r\u001d\tA!\\1uQ*\t\u0001\"A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0007\u0001Y\u0011\u0003\u0005\u0002\r\u001f5\tQBC\u0001\u000f\u0003\u0015\u00198-\u00197b\u0013\t\u0001RB\u0001\u0004B]f\u0014VM\u001a\t\u0003%Mi\u0011!B\u0005\u0003)\u0015\u0011A\u0003U8ms:|W.[1m\u0013:\u001cH/\u00198dKN\u0004\u0014A\u0002\u0013j]&$H\u0005F\u0001\u0018!\ta\u0001$\u0003\u0002\u001a\u001b\t!QK\\5u\u0003\u001dyg/\u001a:SS\u001e,\"\u0001\b\u0012\u0015\tuI4)\u0015\t\u0004%y\u0001\u0013BA\u0010\u0006\u0005E\u0001v\u000e\\=o_6L\u0017\r\\(wKJ\u0014\u0016n\u001a\t\u0003C\tb\u0001\u0001B\u0005$\u0005\u0001\u0006\t\u0011!b\u0001I\t\t1)\u0005\u0002&QA\u0011ABJ\u0005\u0003O5\u0011qAT8uQ&tw\r\u0005\u0002\rS%\u0011!&\u0004\u0002\u0004\u0003:L\bf\u0001\u0012-_A\u0011A\"L\u0005\u0003]5\u00111b\u001d9fG&\fG.\u001b>fIF*1\u0005M\u00194e9\u0011A\"M\u0005\u0003e5\ta\u0001R8vE2,\u0017\u0007\u0002\u00135q9q!!\u000e\u001d\u000e\u0003YR!aN\u0005\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0001b\u0002\u001e\u0003\u0003\u0003\u0005\u001daO\u0001\fKZLG-\u001a8dK\u0012:\u0014\u0007E\u0002=\u0001\u0002r!!\u0010 \u000e\u0003\u001dI!aP\u0004\u0002\u000fA\f7m[1hK&\u0011\u0011I\u0011\u0002\t\u00072\f7o\u001d+bO*\u0011qh\u0002\u0005\b\t\n\t\t\u0011q\u0001F\u0003-)g/\u001b3f]\u000e,Ge\u000e\u001a\u0011\u0007\u0019s\u0005E\u0004\u0002H\u0019:\u0011\u0001J\u0013\b\u0003k%K\u0011\u0001C\u0005\u0003\u0017\u001e\tq!\u00197hK\n\u0014\u0018-\u0003\u0002@\u001b*\u00111jB\u0005\u0003\u001fB\u00131AU5h\u0015\tyT\nC\u0004S\u0005\u0005\u0005\t9A*\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$sg\r\t\u0004\rR\u0003\u0013BA+Q\u0005\t)\u0015/A\u0004pm\u0016\u0014(K\\4\u0016\u0005akF\u0003B-bI&\u00042A\u0005.]\u0013\tYVAA\tQ_2Lhn\\7jC2|e/\u001a:S]\u001e\u0004\"!I/\u0005\u0013\r\u001a\u0001\u0015!A\u0001\u0006\u0004!\u0003fA/-?F*1\u0005M\u0019aeE\"A\u0005\u000e\u001d\u000f\u0011\u001d\u00117!!AA\u0004\r\f1\"\u001a<jI\u0016t7-\u001a\u00138iA\u0019A\b\u0011/\t\u000f\u0015\u001c\u0011\u0011!a\u0002M\u0006YQM^5eK:\u001cW\rJ\u001c6!\r1u\rX\u0005\u0003QB\u00131A\u00158h\u0011\u001dQ7!!AA\u0004-\f1\"\u001a<jI\u0016t7-\u001a\u00138mA\u0019a\t\u0016/"
)
public interface PolynomialInstances1 extends PolynomialInstances0 {
   // $FF: synthetic method
   static PolynomialOverRig overRig$(final PolynomialInstances1 $this, final ClassTag evidence$71, final Rig evidence$72, final Eq evidence$73) {
      return $this.overRig(evidence$71, evidence$72, evidence$73);
   }

   default PolynomialOverRig overRig(final ClassTag evidence$71, final Rig evidence$72, final Eq evidence$73) {
      return new PolynomialOverRig(evidence$72, evidence$73, evidence$71) {
         private final Rig scalar;
         private final Eq eq;
         private final ClassTag ct;

         public Polynomial one() {
            return PolynomialOverRig.one$(this);
         }

         public Polynomial one$mcD$sp() {
            return PolynomialOverRig.one$mcD$sp$(this);
         }

         public boolean specInstance$() {
            return PolynomialOverRig.specInstance$$(this);
         }

         public Monoid multiplicative() {
            return MultiplicativeMonoid.multiplicative$(this);
         }

         public Monoid multiplicative$mcD$sp() {
            return MultiplicativeMonoid.multiplicative$mcD$sp$(this);
         }

         public Monoid multiplicative$mcF$sp() {
            return MultiplicativeMonoid.multiplicative$mcF$sp$(this);
         }

         public Monoid multiplicative$mcI$sp() {
            return MultiplicativeMonoid.multiplicative$mcI$sp$(this);
         }

         public Monoid multiplicative$mcJ$sp() {
            return MultiplicativeMonoid.multiplicative$mcJ$sp$(this);
         }

         public float one$mcF$sp() {
            return MultiplicativeMonoid.one$mcF$sp$(this);
         }

         public int one$mcI$sp() {
            return MultiplicativeMonoid.one$mcI$sp$(this);
         }

         public long one$mcJ$sp() {
            return MultiplicativeMonoid.one$mcJ$sp$(this);
         }

         public boolean isOne(final Object a, final Eq ev) {
            return MultiplicativeMonoid.isOne$(this, a, ev);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
         }

         public Object pow(final Object a, final int n) {
            return MultiplicativeMonoid.pow$(this, a, n);
         }

         public double pow$mcD$sp(final double a, final int n) {
            return MultiplicativeMonoid.pow$mcD$sp$(this, a, n);
         }

         public float pow$mcF$sp(final float a, final int n) {
            return MultiplicativeMonoid.pow$mcF$sp$(this, a, n);
         }

         public int pow$mcI$sp(final int a, final int n) {
            return MultiplicativeMonoid.pow$mcI$sp$(this, a, n);
         }

         public long pow$mcJ$sp(final long a, final int n) {
            return MultiplicativeMonoid.pow$mcJ$sp$(this, a, n);
         }

         public Object product(final IterableOnce as) {
            return MultiplicativeMonoid.product$(this, as);
         }

         public double product$mcD$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcD$sp$(this, as);
         }

         public float product$mcF$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcF$sp$(this, as);
         }

         public int product$mcI$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcI$sp$(this, as);
         }

         public long product$mcJ$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcJ$sp$(this, as);
         }

         public Option tryProduct(final IterableOnce as) {
            return MultiplicativeMonoid.tryProduct$(this, as);
         }

         public Eq eq$mcD$sp() {
            return PolynomialOverSemiring.eq$mcD$sp$(this);
         }

         public Polynomial zero() {
            return PolynomialOverSemiring.zero$(this);
         }

         public Polynomial zero$mcD$sp() {
            return PolynomialOverSemiring.zero$mcD$sp$(this);
         }

         public Polynomial plus(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.plus$(this, x, y);
         }

         public Polynomial plus$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.plus$mcD$sp$(this, x, y);
         }

         public Polynomial times(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.times$(this, x, y);
         }

         public Polynomial times$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.times$mcD$sp$(this, x, y);
         }

         public double times$mcD$sp(final double x, final double y) {
            return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
         }

         public float times$mcF$sp(final float x, final float y) {
            return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
         }

         public int times$mcI$sp(final int x, final int y) {
            return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
         }

         public long times$mcJ$sp(final long x, final long y) {
            return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
         }

         public Object positivePow(final Object a, final int n) {
            return MultiplicativeSemigroup.positivePow$(this, a, n);
         }

         public double positivePow$mcD$sp(final double a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
         }

         public float positivePow$mcF$sp(final float a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
         }

         public int positivePow$mcI$sp(final int a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
         }

         public long positivePow$mcJ$sp(final long a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
         }

         public CommutativeMonoid additive() {
            return AdditiveCommutativeMonoid.additive$(this);
         }

         public CommutativeMonoid additive$mcD$sp() {
            return AdditiveCommutativeMonoid.additive$mcD$sp$(this);
         }

         public CommutativeMonoid additive$mcF$sp() {
            return AdditiveCommutativeMonoid.additive$mcF$sp$(this);
         }

         public CommutativeMonoid additive$mcI$sp() {
            return AdditiveCommutativeMonoid.additive$mcI$sp$(this);
         }

         public CommutativeMonoid additive$mcJ$sp() {
            return AdditiveCommutativeMonoid.additive$mcJ$sp$(this);
         }

         public float zero$mcF$sp() {
            return AdditiveMonoid.zero$mcF$sp$(this);
         }

         public int zero$mcI$sp() {
            return AdditiveMonoid.zero$mcI$sp$(this);
         }

         public long zero$mcJ$sp() {
            return AdditiveMonoid.zero$mcJ$sp$(this);
         }

         public boolean isZero(final Object a, final Eq ev) {
            return AdditiveMonoid.isZero$(this, a, ev);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
         }

         public Object sumN(final Object a, final int n) {
            return AdditiveMonoid.sumN$(this, a, n);
         }

         public double sumN$mcD$sp(final double a, final int n) {
            return AdditiveMonoid.sumN$mcD$sp$(this, a, n);
         }

         public float sumN$mcF$sp(final float a, final int n) {
            return AdditiveMonoid.sumN$mcF$sp$(this, a, n);
         }

         public int sumN$mcI$sp(final int a, final int n) {
            return AdditiveMonoid.sumN$mcI$sp$(this, a, n);
         }

         public long sumN$mcJ$sp(final long a, final int n) {
            return AdditiveMonoid.sumN$mcJ$sp$(this, a, n);
         }

         public Object sum(final IterableOnce as) {
            return AdditiveMonoid.sum$(this, as);
         }

         public double sum$mcD$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcD$sp$(this, as);
         }

         public float sum$mcF$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcF$sp$(this, as);
         }

         public int sum$mcI$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcI$sp$(this, as);
         }

         public long sum$mcJ$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcJ$sp$(this, as);
         }

         public Option trySum(final IterableOnce as) {
            return AdditiveMonoid.trySum$(this, as);
         }

         public double plus$mcD$sp(final double x, final double y) {
            return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
         }

         public float plus$mcF$sp(final float x, final float y) {
            return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
         }

         public int plus$mcI$sp(final int x, final int y) {
            return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
         }

         public long plus$mcJ$sp(final long x, final long y) {
            return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
         }

         public Object positiveSumN(final Object a, final int n) {
            return AdditiveSemigroup.positiveSumN$(this, a, n);
         }

         public double positiveSumN$mcD$sp(final double a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
         }

         public float positiveSumN$mcF$sp(final float a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
         }

         public int positiveSumN$mcI$sp(final int a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
         }

         public long positiveSumN$mcJ$sp(final long a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
         }

         public Rig scalar() {
            return this.scalar;
         }

         public Eq eq() {
            return this.eq;
         }

         public ClassTag ct() {
            return this.ct;
         }

         public {
            AdditiveSemigroup.$init$(this);
            AdditiveMonoid.$init$(this);
            AdditiveCommutativeSemigroup.$init$(this);
            AdditiveCommutativeMonoid.$init$(this);
            MultiplicativeSemigroup.$init$(this);
            PolynomialOverSemiring.$init$(this);
            MultiplicativeMonoid.$init$(this);
            PolynomialOverRig.$init$(this);
            this.scalar = spire.algebra.package$.MODULE$.Rig().apply(evidence$72$1);
            this.eq = spire.algebra.package$.MODULE$.Eq().apply(evidence$73$1);
            this.ct = (ClassTag).MODULE$.implicitly(evidence$71$1);
         }
      };
   }

   // $FF: synthetic method
   static PolynomialOverRng overRng$(final PolynomialInstances1 $this, final ClassTag evidence$74, final Rng evidence$75, final Eq evidence$76) {
      return $this.overRng(evidence$74, evidence$75, evidence$76);
   }

   default PolynomialOverRng overRng(final ClassTag evidence$74, final Rng evidence$75, final Eq evidence$76) {
      return new PolynomialOverRng(evidence$75, evidence$76, evidence$74) {
         private final Rng scalar;
         private final Eq eq;
         private final ClassTag ct;

         public Polynomial timesl(final Object r, final Polynomial v) {
            return PolynomialOverRng.timesl$(this, r, v);
         }

         public Polynomial timesl$mcD$sp(final double r, final Polynomial v) {
            return PolynomialOverRng.timesl$mcD$sp$(this, r, v);
         }

         public Polynomial negate(final Polynomial x) {
            return PolynomialOverRng.negate$(this, x);
         }

         public Polynomial negate$mcD$sp(final Polynomial x) {
            return PolynomialOverRng.negate$mcD$sp$(this, x);
         }

         public boolean specInstance$() {
            return PolynomialOverRng.specInstance$$(this);
         }

         public CommutativeGroup additive() {
            return AdditiveCommutativeGroup.additive$(this);
         }

         public CommutativeGroup additive$mcD$sp() {
            return AdditiveCommutativeGroup.additive$mcD$sp$(this);
         }

         public CommutativeGroup additive$mcF$sp() {
            return AdditiveCommutativeGroup.additive$mcF$sp$(this);
         }

         public CommutativeGroup additive$mcI$sp() {
            return AdditiveCommutativeGroup.additive$mcI$sp$(this);
         }

         public CommutativeGroup additive$mcJ$sp() {
            return AdditiveCommutativeGroup.additive$mcJ$sp$(this);
         }

         public double negate$mcD$sp(final double x) {
            return AdditiveGroup.negate$mcD$sp$(this, x);
         }

         public float negate$mcF$sp(final float x) {
            return AdditiveGroup.negate$mcF$sp$(this, x);
         }

         public int negate$mcI$sp(final int x) {
            return AdditiveGroup.negate$mcI$sp$(this, x);
         }

         public long negate$mcJ$sp(final long x) {
            return AdditiveGroup.negate$mcJ$sp$(this, x);
         }

         public Object minus(final Object x, final Object y) {
            return AdditiveGroup.minus$(this, x, y);
         }

         public double minus$mcD$sp(final double x, final double y) {
            return AdditiveGroup.minus$mcD$sp$(this, x, y);
         }

         public float minus$mcF$sp(final float x, final float y) {
            return AdditiveGroup.minus$mcF$sp$(this, x, y);
         }

         public int minus$mcI$sp(final int x, final int y) {
            return AdditiveGroup.minus$mcI$sp$(this, x, y);
         }

         public long minus$mcJ$sp(final long x, final long y) {
            return AdditiveGroup.minus$mcJ$sp$(this, x, y);
         }

         public Object sumN(final Object a, final int n) {
            return AdditiveGroup.sumN$(this, a, n);
         }

         public double sumN$mcD$sp(final double a, final int n) {
            return AdditiveGroup.sumN$mcD$sp$(this, a, n);
         }

         public float sumN$mcF$sp(final float a, final int n) {
            return AdditiveGroup.sumN$mcF$sp$(this, a, n);
         }

         public int sumN$mcI$sp(final int a, final int n) {
            return AdditiveGroup.sumN$mcI$sp$(this, a, n);
         }

         public long sumN$mcJ$sp(final long a, final int n) {
            return AdditiveGroup.sumN$mcJ$sp$(this, a, n);
         }

         public Eq eq$mcD$sp() {
            return PolynomialOverSemiring.eq$mcD$sp$(this);
         }

         public Polynomial zero() {
            return PolynomialOverSemiring.zero$(this);
         }

         public Polynomial zero$mcD$sp() {
            return PolynomialOverSemiring.zero$mcD$sp$(this);
         }

         public Polynomial plus(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.plus$(this, x, y);
         }

         public Polynomial plus$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.plus$mcD$sp$(this, x, y);
         }

         public Polynomial times(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.times$(this, x, y);
         }

         public Polynomial times$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.times$mcD$sp$(this, x, y);
         }

         public Semigroup multiplicative() {
            return MultiplicativeSemigroup.multiplicative$(this);
         }

         public Semigroup multiplicative$mcD$sp() {
            return MultiplicativeSemigroup.multiplicative$mcD$sp$(this);
         }

         public Semigroup multiplicative$mcF$sp() {
            return MultiplicativeSemigroup.multiplicative$mcF$sp$(this);
         }

         public Semigroup multiplicative$mcI$sp() {
            return MultiplicativeSemigroup.multiplicative$mcI$sp$(this);
         }

         public Semigroup multiplicative$mcJ$sp() {
            return MultiplicativeSemigroup.multiplicative$mcJ$sp$(this);
         }

         public double times$mcD$sp(final double x, final double y) {
            return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
         }

         public float times$mcF$sp(final float x, final float y) {
            return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
         }

         public int times$mcI$sp(final int x, final int y) {
            return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
         }

         public long times$mcJ$sp(final long x, final long y) {
            return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
         }

         public Object pow(final Object a, final int n) {
            return MultiplicativeSemigroup.pow$(this, a, n);
         }

         public double pow$mcD$sp(final double a, final int n) {
            return MultiplicativeSemigroup.pow$mcD$sp$(this, a, n);
         }

         public float pow$mcF$sp(final float a, final int n) {
            return MultiplicativeSemigroup.pow$mcF$sp$(this, a, n);
         }

         public int pow$mcI$sp(final int a, final int n) {
            return MultiplicativeSemigroup.pow$mcI$sp$(this, a, n);
         }

         public long pow$mcJ$sp(final long a, final int n) {
            return MultiplicativeSemigroup.pow$mcJ$sp$(this, a, n);
         }

         public Object positivePow(final Object a, final int n) {
            return MultiplicativeSemigroup.positivePow$(this, a, n);
         }

         public double positivePow$mcD$sp(final double a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
         }

         public float positivePow$mcF$sp(final float a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
         }

         public int positivePow$mcI$sp(final int a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
         }

         public long positivePow$mcJ$sp(final long a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
         }

         public Option tryProduct(final IterableOnce as) {
            return MultiplicativeSemigroup.tryProduct$(this, as);
         }

         public float zero$mcF$sp() {
            return AdditiveMonoid.zero$mcF$sp$(this);
         }

         public int zero$mcI$sp() {
            return AdditiveMonoid.zero$mcI$sp$(this);
         }

         public long zero$mcJ$sp() {
            return AdditiveMonoid.zero$mcJ$sp$(this);
         }

         public boolean isZero(final Object a, final Eq ev) {
            return AdditiveMonoid.isZero$(this, a, ev);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
         }

         public Object sum(final IterableOnce as) {
            return AdditiveMonoid.sum$(this, as);
         }

         public double sum$mcD$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcD$sp$(this, as);
         }

         public float sum$mcF$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcF$sp$(this, as);
         }

         public int sum$mcI$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcI$sp$(this, as);
         }

         public long sum$mcJ$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcJ$sp$(this, as);
         }

         public Option trySum(final IterableOnce as) {
            return AdditiveMonoid.trySum$(this, as);
         }

         public double plus$mcD$sp(final double x, final double y) {
            return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
         }

         public float plus$mcF$sp(final float x, final float y) {
            return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
         }

         public int plus$mcI$sp(final int x, final int y) {
            return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
         }

         public long plus$mcJ$sp(final long x, final long y) {
            return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
         }

         public Object positiveSumN(final Object a, final int n) {
            return AdditiveSemigroup.positiveSumN$(this, a, n);
         }

         public double positiveSumN$mcD$sp(final double a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
         }

         public float positiveSumN$mcF$sp(final float a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
         }

         public int positiveSumN$mcI$sp(final int a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
         }

         public long positiveSumN$mcJ$sp(final long a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
         }

         public Rng scalar() {
            return this.scalar;
         }

         public Eq eq() {
            return this.eq;
         }

         public ClassTag ct() {
            return this.ct;
         }

         public {
            AdditiveSemigroup.$init$(this);
            AdditiveMonoid.$init$(this);
            AdditiveCommutativeSemigroup.$init$(this);
            AdditiveCommutativeMonoid.$init$(this);
            MultiplicativeSemigroup.$init$(this);
            PolynomialOverSemiring.$init$(this);
            AdditiveGroup.$init$(this);
            AdditiveCommutativeGroup.$init$(this);
            PolynomialOverRng.$init$(this);
            this.scalar = spire.algebra.package$.MODULE$.Rng().apply(evidence$75$1);
            this.eq = spire.algebra.package$.MODULE$.Eq().apply(evidence$76$1);
            this.ct = (ClassTag).MODULE$.implicitly(evidence$74$1);
         }
      };
   }

   // $FF: synthetic method
   static PolynomialOverRig overRig$mDc$sp$(final PolynomialInstances1 $this, final ClassTag evidence$71, final Rig evidence$72, final Eq evidence$73) {
      return $this.overRig$mDc$sp(evidence$71, evidence$72, evidence$73);
   }

   default PolynomialOverRig overRig$mDc$sp(final ClassTag evidence$71, final Rig evidence$72, final Eq evidence$73) {
      return new PolynomialOverRig$mcD$sp(evidence$72, evidence$73, evidence$71) {
         private final Rig scalar;
         private final Eq eq;
         private final ClassTag ct;

         public Polynomial one() {
            return PolynomialOverRig$mcD$sp.one$(this);
         }

         public Polynomial one$mcD$sp() {
            return PolynomialOverRig$mcD$sp.one$mcD$sp$(this);
         }

         public Polynomial zero() {
            return PolynomialOverSemiring$mcD$sp.zero$(this);
         }

         public Polynomial zero$mcD$sp() {
            return PolynomialOverSemiring$mcD$sp.zero$mcD$sp$(this);
         }

         public Polynomial plus(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.plus$(this, x, y);
         }

         public Polynomial plus$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.plus$mcD$sp$(this, x, y);
         }

         public Polynomial times(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.times$(this, x, y);
         }

         public Polynomial times$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.times$mcD$sp$(this, x, y);
         }

         public Monoid multiplicative() {
            return MultiplicativeMonoid.multiplicative$(this);
         }

         public Monoid multiplicative$mcD$sp() {
            return MultiplicativeMonoid.multiplicative$mcD$sp$(this);
         }

         public Monoid multiplicative$mcF$sp() {
            return MultiplicativeMonoid.multiplicative$mcF$sp$(this);
         }

         public Monoid multiplicative$mcI$sp() {
            return MultiplicativeMonoid.multiplicative$mcI$sp$(this);
         }

         public Monoid multiplicative$mcJ$sp() {
            return MultiplicativeMonoid.multiplicative$mcJ$sp$(this);
         }

         public float one$mcF$sp() {
            return MultiplicativeMonoid.one$mcF$sp$(this);
         }

         public int one$mcI$sp() {
            return MultiplicativeMonoid.one$mcI$sp$(this);
         }

         public long one$mcJ$sp() {
            return MultiplicativeMonoid.one$mcJ$sp$(this);
         }

         public boolean isOne(final Object a, final Eq ev) {
            return MultiplicativeMonoid.isOne$(this, a, ev);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
         }

         public Object pow(final Object a, final int n) {
            return MultiplicativeMonoid.pow$(this, a, n);
         }

         public double pow$mcD$sp(final double a, final int n) {
            return MultiplicativeMonoid.pow$mcD$sp$(this, a, n);
         }

         public float pow$mcF$sp(final float a, final int n) {
            return MultiplicativeMonoid.pow$mcF$sp$(this, a, n);
         }

         public int pow$mcI$sp(final int a, final int n) {
            return MultiplicativeMonoid.pow$mcI$sp$(this, a, n);
         }

         public long pow$mcJ$sp(final long a, final int n) {
            return MultiplicativeMonoid.pow$mcJ$sp$(this, a, n);
         }

         public Object product(final IterableOnce as) {
            return MultiplicativeMonoid.product$(this, as);
         }

         public double product$mcD$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcD$sp$(this, as);
         }

         public float product$mcF$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcF$sp$(this, as);
         }

         public int product$mcI$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcI$sp$(this, as);
         }

         public long product$mcJ$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcJ$sp$(this, as);
         }

         public Option tryProduct(final IterableOnce as) {
            return MultiplicativeMonoid.tryProduct$(this, as);
         }

         public double times$mcD$sp(final double x, final double y) {
            return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
         }

         public float times$mcF$sp(final float x, final float y) {
            return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
         }

         public int times$mcI$sp(final int x, final int y) {
            return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
         }

         public long times$mcJ$sp(final long x, final long y) {
            return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
         }

         public Object positivePow(final Object a, final int n) {
            return MultiplicativeSemigroup.positivePow$(this, a, n);
         }

         public double positivePow$mcD$sp(final double a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
         }

         public float positivePow$mcF$sp(final float a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
         }

         public int positivePow$mcI$sp(final int a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
         }

         public long positivePow$mcJ$sp(final long a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
         }

         public CommutativeMonoid additive() {
            return AdditiveCommutativeMonoid.additive$(this);
         }

         public CommutativeMonoid additive$mcD$sp() {
            return AdditiveCommutativeMonoid.additive$mcD$sp$(this);
         }

         public CommutativeMonoid additive$mcF$sp() {
            return AdditiveCommutativeMonoid.additive$mcF$sp$(this);
         }

         public CommutativeMonoid additive$mcI$sp() {
            return AdditiveCommutativeMonoid.additive$mcI$sp$(this);
         }

         public CommutativeMonoid additive$mcJ$sp() {
            return AdditiveCommutativeMonoid.additive$mcJ$sp$(this);
         }

         public float zero$mcF$sp() {
            return AdditiveMonoid.zero$mcF$sp$(this);
         }

         public int zero$mcI$sp() {
            return AdditiveMonoid.zero$mcI$sp$(this);
         }

         public long zero$mcJ$sp() {
            return AdditiveMonoid.zero$mcJ$sp$(this);
         }

         public boolean isZero(final Object a, final Eq ev) {
            return AdditiveMonoid.isZero$(this, a, ev);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
         }

         public Object sumN(final Object a, final int n) {
            return AdditiveMonoid.sumN$(this, a, n);
         }

         public double sumN$mcD$sp(final double a, final int n) {
            return AdditiveMonoid.sumN$mcD$sp$(this, a, n);
         }

         public float sumN$mcF$sp(final float a, final int n) {
            return AdditiveMonoid.sumN$mcF$sp$(this, a, n);
         }

         public int sumN$mcI$sp(final int a, final int n) {
            return AdditiveMonoid.sumN$mcI$sp$(this, a, n);
         }

         public long sumN$mcJ$sp(final long a, final int n) {
            return AdditiveMonoid.sumN$mcJ$sp$(this, a, n);
         }

         public Object sum(final IterableOnce as) {
            return AdditiveMonoid.sum$(this, as);
         }

         public double sum$mcD$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcD$sp$(this, as);
         }

         public float sum$mcF$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcF$sp$(this, as);
         }

         public int sum$mcI$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcI$sp$(this, as);
         }

         public long sum$mcJ$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcJ$sp$(this, as);
         }

         public Option trySum(final IterableOnce as) {
            return AdditiveMonoid.trySum$(this, as);
         }

         public double plus$mcD$sp(final double x, final double y) {
            return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
         }

         public float plus$mcF$sp(final float x, final float y) {
            return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
         }

         public int plus$mcI$sp(final int x, final int y) {
            return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
         }

         public long plus$mcJ$sp(final long x, final long y) {
            return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
         }

         public Object positiveSumN(final Object a, final int n) {
            return AdditiveSemigroup.positiveSumN$(this, a, n);
         }

         public double positiveSumN$mcD$sp(final double a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
         }

         public float positiveSumN$mcF$sp(final float a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
         }

         public int positiveSumN$mcI$sp(final int a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
         }

         public long positiveSumN$mcJ$sp(final long a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
         }

         public Rig scalar() {
            return this.scalar$mcD$sp();
         }

         public Eq eq() {
            return this.eq$mcD$sp();
         }

         public ClassTag ct() {
            return this.ct;
         }

         public Rig scalar$mcD$sp() {
            return this.scalar;
         }

         public Eq eq$mcD$sp() {
            return this.eq;
         }

         public boolean specInstance$() {
            return true;
         }

         public {
            AdditiveSemigroup.$init$(this);
            AdditiveMonoid.$init$(this);
            AdditiveCommutativeSemigroup.$init$(this);
            AdditiveCommutativeMonoid.$init$(this);
            MultiplicativeSemigroup.$init$(this);
            PolynomialOverSemiring.$init$(this);
            MultiplicativeMonoid.$init$(this);
            PolynomialOverRig.$init$(this);
            this.scalar = spire.algebra.package$.MODULE$.Rig().apply(evidence$72$2);
            this.eq = spire.algebra.package$.MODULE$.Eq().apply(evidence$73$2);
            this.ct = (ClassTag).MODULE$.implicitly(evidence$71$2);
         }
      };
   }

   // $FF: synthetic method
   static PolynomialOverRng overRng$mDc$sp$(final PolynomialInstances1 $this, final ClassTag evidence$74, final Rng evidence$75, final Eq evidence$76) {
      return $this.overRng$mDc$sp(evidence$74, evidence$75, evidence$76);
   }

   default PolynomialOverRng overRng$mDc$sp(final ClassTag evidence$74, final Rng evidence$75, final Eq evidence$76) {
      return new PolynomialOverRng$mcD$sp(evidence$75, evidence$76, evidence$74) {
         private final Rng scalar;
         private final Eq eq;
         private final ClassTag ct;

         public Polynomial timesl(final double r, final Polynomial v) {
            return PolynomialOverRng$mcD$sp.timesl$(this, r, v);
         }

         public Polynomial timesl$mcD$sp(final double r, final Polynomial v) {
            return PolynomialOverRng$mcD$sp.timesl$mcD$sp$(this, r, v);
         }

         public Polynomial negate(final Polynomial x) {
            return PolynomialOverRng$mcD$sp.negate$(this, x);
         }

         public Polynomial negate$mcD$sp(final Polynomial x) {
            return PolynomialOverRng$mcD$sp.negate$mcD$sp$(this, x);
         }

         public Polynomial zero() {
            return PolynomialOverSemiring$mcD$sp.zero$(this);
         }

         public Polynomial zero$mcD$sp() {
            return PolynomialOverSemiring$mcD$sp.zero$mcD$sp$(this);
         }

         public Polynomial plus(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.plus$(this, x, y);
         }

         public Polynomial plus$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.plus$mcD$sp$(this, x, y);
         }

         public Polynomial times(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.times$(this, x, y);
         }

         public Polynomial times$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.times$mcD$sp$(this, x, y);
         }

         public CommutativeGroup additive() {
            return AdditiveCommutativeGroup.additive$(this);
         }

         public CommutativeGroup additive$mcD$sp() {
            return AdditiveCommutativeGroup.additive$mcD$sp$(this);
         }

         public CommutativeGroup additive$mcF$sp() {
            return AdditiveCommutativeGroup.additive$mcF$sp$(this);
         }

         public CommutativeGroup additive$mcI$sp() {
            return AdditiveCommutativeGroup.additive$mcI$sp$(this);
         }

         public CommutativeGroup additive$mcJ$sp() {
            return AdditiveCommutativeGroup.additive$mcJ$sp$(this);
         }

         public double negate$mcD$sp(final double x) {
            return AdditiveGroup.negate$mcD$sp$(this, x);
         }

         public float negate$mcF$sp(final float x) {
            return AdditiveGroup.negate$mcF$sp$(this, x);
         }

         public int negate$mcI$sp(final int x) {
            return AdditiveGroup.negate$mcI$sp$(this, x);
         }

         public long negate$mcJ$sp(final long x) {
            return AdditiveGroup.negate$mcJ$sp$(this, x);
         }

         public Object minus(final Object x, final Object y) {
            return AdditiveGroup.minus$(this, x, y);
         }

         public double minus$mcD$sp(final double x, final double y) {
            return AdditiveGroup.minus$mcD$sp$(this, x, y);
         }

         public float minus$mcF$sp(final float x, final float y) {
            return AdditiveGroup.minus$mcF$sp$(this, x, y);
         }

         public int minus$mcI$sp(final int x, final int y) {
            return AdditiveGroup.minus$mcI$sp$(this, x, y);
         }

         public long minus$mcJ$sp(final long x, final long y) {
            return AdditiveGroup.minus$mcJ$sp$(this, x, y);
         }

         public Object sumN(final Object a, final int n) {
            return AdditiveGroup.sumN$(this, a, n);
         }

         public double sumN$mcD$sp(final double a, final int n) {
            return AdditiveGroup.sumN$mcD$sp$(this, a, n);
         }

         public float sumN$mcF$sp(final float a, final int n) {
            return AdditiveGroup.sumN$mcF$sp$(this, a, n);
         }

         public int sumN$mcI$sp(final int a, final int n) {
            return AdditiveGroup.sumN$mcI$sp$(this, a, n);
         }

         public long sumN$mcJ$sp(final long a, final int n) {
            return AdditiveGroup.sumN$mcJ$sp$(this, a, n);
         }

         public Semigroup multiplicative() {
            return MultiplicativeSemigroup.multiplicative$(this);
         }

         public Semigroup multiplicative$mcD$sp() {
            return MultiplicativeSemigroup.multiplicative$mcD$sp$(this);
         }

         public Semigroup multiplicative$mcF$sp() {
            return MultiplicativeSemigroup.multiplicative$mcF$sp$(this);
         }

         public Semigroup multiplicative$mcI$sp() {
            return MultiplicativeSemigroup.multiplicative$mcI$sp$(this);
         }

         public Semigroup multiplicative$mcJ$sp() {
            return MultiplicativeSemigroup.multiplicative$mcJ$sp$(this);
         }

         public double times$mcD$sp(final double x, final double y) {
            return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
         }

         public float times$mcF$sp(final float x, final float y) {
            return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
         }

         public int times$mcI$sp(final int x, final int y) {
            return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
         }

         public long times$mcJ$sp(final long x, final long y) {
            return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
         }

         public Object pow(final Object a, final int n) {
            return MultiplicativeSemigroup.pow$(this, a, n);
         }

         public double pow$mcD$sp(final double a, final int n) {
            return MultiplicativeSemigroup.pow$mcD$sp$(this, a, n);
         }

         public float pow$mcF$sp(final float a, final int n) {
            return MultiplicativeSemigroup.pow$mcF$sp$(this, a, n);
         }

         public int pow$mcI$sp(final int a, final int n) {
            return MultiplicativeSemigroup.pow$mcI$sp$(this, a, n);
         }

         public long pow$mcJ$sp(final long a, final int n) {
            return MultiplicativeSemigroup.pow$mcJ$sp$(this, a, n);
         }

         public Object positivePow(final Object a, final int n) {
            return MultiplicativeSemigroup.positivePow$(this, a, n);
         }

         public double positivePow$mcD$sp(final double a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
         }

         public float positivePow$mcF$sp(final float a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
         }

         public int positivePow$mcI$sp(final int a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
         }

         public long positivePow$mcJ$sp(final long a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
         }

         public Option tryProduct(final IterableOnce as) {
            return MultiplicativeSemigroup.tryProduct$(this, as);
         }

         public float zero$mcF$sp() {
            return AdditiveMonoid.zero$mcF$sp$(this);
         }

         public int zero$mcI$sp() {
            return AdditiveMonoid.zero$mcI$sp$(this);
         }

         public long zero$mcJ$sp() {
            return AdditiveMonoid.zero$mcJ$sp$(this);
         }

         public boolean isZero(final Object a, final Eq ev) {
            return AdditiveMonoid.isZero$(this, a, ev);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
         }

         public Object sum(final IterableOnce as) {
            return AdditiveMonoid.sum$(this, as);
         }

         public double sum$mcD$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcD$sp$(this, as);
         }

         public float sum$mcF$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcF$sp$(this, as);
         }

         public int sum$mcI$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcI$sp$(this, as);
         }

         public long sum$mcJ$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcJ$sp$(this, as);
         }

         public Option trySum(final IterableOnce as) {
            return AdditiveMonoid.trySum$(this, as);
         }

         public double plus$mcD$sp(final double x, final double y) {
            return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
         }

         public float plus$mcF$sp(final float x, final float y) {
            return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
         }

         public int plus$mcI$sp(final int x, final int y) {
            return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
         }

         public long plus$mcJ$sp(final long x, final long y) {
            return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
         }

         public Object positiveSumN(final Object a, final int n) {
            return AdditiveSemigroup.positiveSumN$(this, a, n);
         }

         public double positiveSumN$mcD$sp(final double a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
         }

         public float positiveSumN$mcF$sp(final float a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
         }

         public int positiveSumN$mcI$sp(final int a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
         }

         public long positiveSumN$mcJ$sp(final long a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
         }

         public Rng scalar() {
            return this.scalar$mcD$sp();
         }

         public Eq eq() {
            return this.eq$mcD$sp();
         }

         public ClassTag ct() {
            return this.ct;
         }

         public Rng scalar$mcD$sp() {
            return this.scalar;
         }

         public Eq eq$mcD$sp() {
            return this.eq;
         }

         public boolean specInstance$() {
            return true;
         }

         public {
            AdditiveSemigroup.$init$(this);
            AdditiveMonoid.$init$(this);
            AdditiveCommutativeSemigroup.$init$(this);
            AdditiveCommutativeMonoid.$init$(this);
            MultiplicativeSemigroup.$init$(this);
            PolynomialOverSemiring.$init$(this);
            AdditiveGroup.$init$(this);
            AdditiveCommutativeGroup.$init$(this);
            PolynomialOverRng.$init$(this);
            this.scalar = spire.algebra.package$.MODULE$.Rng().apply(evidence$75$2);
            this.eq = spire.algebra.package$.MODULE$.Eq().apply(evidence$76$2);
            this.ct = (ClassTag).MODULE$.implicitly(evidence$74$2);
         }
      };
   }

   static void $init$(final PolynomialInstances1 $this) {
   }
}
